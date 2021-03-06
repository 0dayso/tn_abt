package com.tuniu.abt.utils;

import com.tuniu.abt.base.tracer.annotation.CommandTrace;
import com.tuniu.abt.base.tracer.pojo.TracerCmdEnum;
import com.tuniu.abt.intf.constants.BizzEx;
import com.tuniu.abt.intf.exception.BizzException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * REST接口调用统一服务组件
 *
 * Created by chengyao on 2016/3/12.
 */
@Component
public class RestClientComponent implements InitializingBean, DisposableBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestClientComponent.class);

    private static final ContentType TEXT_HTML = ContentType.create("text/html", Consts.UTF_8);

    private CloseableHttpClient httpClient;

    public void init() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(200); // MAX TOTAL
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(200); // MAX PER ROUTE

        //配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(1000)
                .setConnectTimeout(1000)
                .setSocketTimeout(1000).build();

        HttpRequestRetryHandler httpRequestRetryHandler = new DefaultHttpRequestRetryHandler(1, false);

        Collection<Header> defaultHeaders = new ArrayList<Header>();
        defaultHeaders.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate"));

        httpClient = HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager)
                .setDefaultRequestConfig(requestConfig)
                .disableCookieManagement()
                .disableConnectionState()
                .disableAuthCaching()
                .setDefaultSocketConfig(SocketConfig.custom()
                        .setSoKeepAlive(false)
                        .setSoReuseAddress(true)
                        .build())
                .setDefaultHeaders(defaultHeaders) // default header
                .setRetryHandler(httpRequestRetryHandler).build();
    }

    @CommandTrace(name = TracerCmdEnum.REST_CALL, type = "#url", input = "#requestData")
    public String query(String url, HttpMethod httpMethod, String requestData) {
        return queryInner(url, httpMethod, requestData, 0);
    }

    @CommandTrace(name = TracerCmdEnum.REST_CALL, type = "#url", input = "#requestData")
    public String query(String url, HttpMethod httpMethod, String requestData, int socketTimeout) {
        return queryInner(url, httpMethod, requestData, socketTimeout);
    }

    public String queryForAirline(String url, HttpMethod httpMethod, String requestData, int socketTimeout) {
        return queryInner(url, httpMethod, requestData, socketTimeout);
    }

    private String queryInner(String url, HttpMethod httpMethod, String requestData, int socketTimeout) {
        try {
            String requestString = Base64.encodeBase64String(requestData.getBytes(Consts.UTF_8));
            String responseString = request(httpClient, url, httpMethod, requestString, socketTimeout);
            if (responseString == null) {
                throw new BizzException(BizzEx.REST_INTF_RESULT_NONE);
            }
            return new String(Base64.decodeBase64(responseString), Consts.UTF_8);
        } catch (HttpClientErrorException ex) {
            throw new BizzException(BizzEx.REST_INTF_HTTP_CODE_EX, new Object[] {ex.getStatusCode(), ex.getStatusText()}, ex);
        } catch (Exception ex) {
            throw new BizzException(BizzEx.REST_INTF_EX, new Object[] {}, ex);
        }
    }

    private String request(CloseableHttpClient httpClient, String url, HttpMethod httpMethod, String requestString, int socketTimeout) throws
            IOException {
        HttpRequestBase httpUriRequest;

        if (httpMethod != null && httpMethod == HttpMethod.POST) {
            HttpPost httpPost = new HttpPost(url);
            if (socketTimeout > 0) {
                httpPost.setConfig(RequestConfig.custom().setSocketTimeout(socketTimeout).build());
            }
            HttpEntity entity = new StringEntity(requestString, TEXT_HTML);
            httpPost.setEntity(entity);
            httpUriRequest = httpPost;
        } else {
            httpUriRequest = new HttpGet(getURLWithData(url, requestString));
        }

        // custom socketTimeout
        if (socketTimeout > 0) {
            httpUriRequest.setConfig(RequestConfig.custom().setSocketTimeout(socketTimeout).build());
        }

        CloseableHttpResponse response = httpClient.execute(httpUriRequest);
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            String body = EntityUtils.toString(response.getEntity(), Consts.UTF_8);

            if (statusCode != HttpStatus.SC_OK) {
                String statusText = response.getStatusLine().getReasonPhrase();
                if (body != null) {
                    statusText = statusText + ", body: " + StringUtils.substring(body, 0, 50);
                }
                throw new HttpClientErrorException(org.springframework.http.HttpStatus.valueOf(statusCode),
                        statusText);
            }
            return body;
        } finally {
            response.close();
        }
    }

    private String getURLWithData(String url, String reqStr) {
        if (reqStr != null) {
            if (url.endsWith("?")) {
                return url + reqStr;
            } else {
                return url + "?" + reqStr;
            }
        }
        return url;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    @Override
    public void destroy() throws Exception {
        httpClient.close();
    }


}
