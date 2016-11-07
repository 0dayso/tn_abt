package com.tuniu.vla.base.tsp;

import com.google.common.base.Charsets;
import com.tuniu.operation.platform.tsg.base.core.utils.ClientCompressFactory;
import com.tuniu.operation.platform.tsg.base.core.utils.IClientCompressUtils;
import com.tuniu.operation.platform.tsg.base.filter.FrameWorkFilter;
import com.tuniu.operation.platform.tsg.base.rest.RestClient;
import com.tuniu.operation.platform.tsg.base.rest.RestException;
import com.tuniu.operation.platform.tsg.client.caller.rest.RequestParam;
import com.tuniu.operation.platform.tsg.client.caller.rest.ResponseResult;
import com.tuniu.operation.platform.tsg.client.caller.rest.RestCaller;
import com.tuniu.operation.platform.tsg.client.exception.NoServiceAvailableException;
import com.tuniu.operation.platform.tsg.client.exception.ServiceForbidenException;
import com.tuniu.operation.platform.tsg.common.TSGProperties;
import com.tuniu.operation.platform.tsg.monitor.Monitor;
import com.tuniu.operation.platform.tsg.monitor.ServiceMonitorMsg;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.http.NoHttpResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.net.ConnectException;
import java.util.*;

/**
 * 本地tsp转rest测试caller
 *
 * Created by chengyao on 2015/12/25.
 */
public class LocalRestCaller extends RestCaller implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(RestCaller.class);

    private PropertiesConfiguration addressConfig;

    private String profile;

    /* connection timeout */
    private int connectTimeout = TSGProperties.DEFAULT_TIMEOUT;

    /* the time to wait for data from server */
    private int socketTimeout = TSGProperties.DEFAULT_TIMEOUT;

    private Random random = new Random();

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        addressConfig = new PropertiesConfiguration();
        try {
            addressConfig.load("tsp/tsp-" + profile + ".properties");
            addressConfig.setReloadingStrategy(new FileChangedReloadingStrategy());
        } catch (ConfigurationException e) {
            // do nothing
            LOG.debug("can't load tsp-" + profile + ".properties file. " + e.getMessage(), e);
        }
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getAddress(RequestParam request) {
        if (addressConfig == null) {
            return null;
        }
        String serviceName = request.getServiceName();
        return addressConfig.getString(serviceName);
    }

    public String getMethod(RequestParam request) {
        if (addressConfig == null) {
            return null;
        }
        String serviceName = request.getServiceName();
        String[] arr = addressConfig.getStringArray(serviceName);
        if (arr.length <= 1) {
            return "POST";
        } else {
            return arr[1];
        }
    }

    public ResponseResult call(RequestParam request, String address) throws NoServiceAvailableException, ServiceForbidenException,
            RestException {
        ResponseResult ret = execute(request, address);
        return ret;
    }

    private ResponseResult execute(RequestParam request, String address) throws NoServiceAvailableException, ServiceForbidenException {
        String serviceName = request.getServiceName();
        String method = request.getMethod();
        RestClient client = new RestClient(address, method, request.getData(), request.isRequestLog(),
                request.isResponseLog(), request.isBase64());

        //设置超时时间
        if (request.getConnectTimeout() != TSGProperties.TIMETOUT_NOT_DEFINE) {
            client.setConnectTimeout(request.getConnectTimeout());
        } else {
            client.setConnectTimeout(connectTimeout);
        }
        if (request.getSocketTimeout() != TSGProperties.TIMETOUT_NOT_DEFINE) {
            client.setSocketTimeout(request.getSocketTimeout());
        } else {
            client.setSocketTimeout(socketTimeout);
        }

        ServiceMonitorMsg monitorMsg = new ServiceMonitorMsg();
        monitorMsg.setConsumerIp(TSGProperties.LOCAL_IP);
        monitorMsg.setConsumerPort(TSGProperties.SERVICE_PORT);
        monitorMsg.setMsgType(TSGProperties.MONITORMESSAGE_CONSUMER);
        monitorMsg.setStartTime((new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
        monitorMsg.setAgent(TSGProperties.APPLICATION);
        String uuid = FrameWorkFilter.getSequenceId();
        String sequenceId = null;
        if (null == uuid) {
            Integer i = random.nextInt();
            sequenceId = TSGProperties.APPLICATION + "#" + new Date().getTime() + "#" + i.toString() + "$0";
        } else {
            // 设置跳数 liurui add 20140429
            int index = uuid.indexOf('$');
            if (-1 != index) {
                try {
                    String invokeIndexStr = null;
                    invokeIndexStr = uuid.substring(index + 1, uuid.length());
                    Integer invokeIndex = Integer.parseInt(invokeIndexStr);
                    invokeIndex++;
                    sequenceId = uuid.substring(0, index) + "$" + invokeIndex;
                } catch (Exception e) {
                    sequenceId = uuid;
                }
            } else {
                sequenceId = uuid + "$1";
            }
        }
        monitorMsg.setUUID(sequenceId);
        monitorMsg.setServiceName(serviceName);
        monitorMsg.setSrcServiceName(FrameWorkFilter.getMonitorMsg().getServiceName());
        ResponseResult result = null;
        List<String> usedURL = new ArrayList<String>();
        while (null != address) {
            //getServiceIp(monitorMsg, address);
            usedURL.add(address);
            Date startTime = new Date();
            try {
                client.setURL(address);
                if (null != request.getHeader()) {
                    client.setRequestHead(request.getHeader());
                } else {
                    client.setRequestHead(new HashMap<String, String>());
                }
                client.getRequestHead().put("X-Forwarded-For", TSGProperties.LOCAL_IP);

                //设置数据压缩
                String clientData = null;
                IClientCompressUtils iClientCompressUtils = null;
                //如果是get delete 则不对数据进行压缩处理
                if (method.compareToIgnoreCase("GET") == 0
                        || method.compareToIgnoreCase("DELETE") == 0) {
                    client.setData(request.getData());
                } else {
                    switch (request.getCompressType()) {
                    case ClientCompressFactory.COMPRESS_UNKNOW:
                        client.setData(request.getData());
                        break;
                    case ClientCompressFactory.COMPRESS_SNAPPY:
                        iClientCompressUtils = ClientCompressFactory.getCompressUtils(ClientCompressFactory.COMPRESS_SNAPPY);
                        if (request.getData() != null) {
                            client.setData( new String(iClientCompressUtils.compress(request.getData().getBytes(
                                    Charsets.UTF_8)), Charsets.UTF_8) );
                            if (null != client.getRequestHead()) {
                                client.getRequestHead().put(ClientCompressFactory.HTTP_HEAD_DATA_COMPRESSTYPE, ClientCompressFactory.COMPRESS_NAME_SNAPPY);
                            } else {
                                Map<String, String> requestHead = new HashMap<String, String>();
                                requestHead.put(ClientCompressFactory.HTTP_HEAD_DATA_COMPRESSTYPE, ClientCompressFactory.COMPRESS_NAME_SNAPPY);
                                client.setRequestHead(requestHead);
                            }
                        } else {
                            client.setData(request.getData());
                        }
                        break;
                    case ClientCompressFactory.COMPRESS_GZIP:
                        iClientCompressUtils = ClientCompressFactory.getCompressUtils(ClientCompressFactory.COMPRESS_GZIP);
                        if (request.getData() != null) {
                            client.setData( new String(iClientCompressUtils.compress(request.getData().getBytes(Charsets.UTF_8)), Charsets.UTF_8) );
                            if (null != client.getRequestHead()) {
                                client.getRequestHead().put(ClientCompressFactory.HTTP_HEAD_DATA_COMPRESSTYPE, ClientCompressFactory.COMPRESS_NAME_GZIP);
                            } else {
                                Map<String, String> requestHead = new HashMap<String, String>();
                                requestHead.put(ClientCompressFactory.HTTP_HEAD_DATA_COMPRESSTYPE, ClientCompressFactory.COMPRESS_NAME_GZIP);
                                client.setRequestHead(requestHead);
                            }
                        } else {
                            client.setData(request.getData());
                        }
                        break;
                    default:
                        client.setData(request.getData());
                        break;
                    }
                }

                result = client.execute();
                monitorMsg.setTimeTakes((int) (new Date().getTime() - startTime.getTime()));
                address = null;
                monitorMsg.setSuccess(true);
                Monitor.addServiceMonitorMsg(monitorMsg);
            } catch (RestException e) {
                // TODO Auto-generated catch block
                monitorMsg.setTimeTakes((int) (new Date().getTime() - startTime.getTime()));
                monitorMsg.setSuccess(false);
                Monitor.addServiceMonitorMsg(monitorMsg);
                Throwable root = e;
                while (root.getCause() != null) {
                    root = root.getCause();
                }
                LOG.error("[RestCaller][execute]", root);
                // 重新调用其他服务提供者的情况 1. 服务提供者宕机 ConnectException 2. 服务提供者繁忙
                // NoHttpResponseException
                if (root instanceof ConnectException || root instanceof NoHttpResponseException) {
                    LOG.error("[RestCaller][execute][ConnectException] Server {}  and retry other server", address);

                    //address = getAddress(serviceName, request.getRegion(), availableURL, usedURL, request.getUrlParam());
                } else {
                    LOG.error("[RestCaller][execute]", root);
                    throw new RuntimeException(root);
                }
            }
        }
        client = null;
        return result;
    }

}
