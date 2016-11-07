package com.tuniu.abt.service.ctrip;

import com.google.common.base.Charsets;
import com.tuniu.abt.utils.SystemConfig;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.common.RequestHeader;
import com.tuniu.adapter.flightTicket.vendor.ctrip.domain.pay.FlightAlipayRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by chengyao on 2016/2/25.
 */
@Component
public class CtripHeaderSupport {

    @Resource
    private SystemConfig systemConfig;


    public RequestHeader build(String requestType) {
        RequestHeader requestHeader = new RequestHeader();
        requestHeader.setAllianceID(systemConfig.getCtripAuthAllianceID());
        requestHeader.setSID(systemConfig.getCtripAuthSID());
        requestHeader.setTimeStamp(getTimeStampString());
        requestHeader.setRequestType(requestType);
        requestHeader.setSignature(calculationSignature(requestHeader.getTimeStamp(),
                requestHeader.getAllianceID(), systemConfig.getCtripAuthKey(), requestHeader.getSID(), requestType));
        return requestHeader;
    }

    public FlightAlipayRequest buildAlipayRequest(String requestType) {
        FlightAlipayRequest request = new FlightAlipayRequest();
        request.setAllianceID(systemConfig.getCtripAuthAllianceID());
        request.setSID(systemConfig.getCtripAuthSID());
        request.setTimeStamp(getTimeStampString());
        request.setRequestType(requestType);
        request.setSignature(paySignature(request.getTimeStamp(), request.getAllianceID(),
                systemConfig.getCtripAuthKey(), request.getSID(), requestType));
        return request;
    }

    public static String getTimeStampString(){
        return String.valueOf(System.currentTimeMillis()/1000);
    }

    // 获得签名
    public static String calculationSignature(String timeStamp, String allianceID, String secretKey, String sid, String requestType) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(timeStamp);
        if (Integer.parseInt(allianceID) > 0) {
            stringBuilder.append(allianceID);
        }
        String signature = "";
        if (secretKey != null && !secretKey.equals("")) {
            String sectetKeyMD5 = "";
            try {
                sectetKeyMD5 = MD5Encoding(secretKey).toUpperCase();
            } catch (NoSuchAlgorithmException e) {
                return "";
            }
            stringBuilder.append(sectetKeyMD5);
        } else {
            return "";
        }
        if (Integer.parseInt(sid) > 0) {
            stringBuilder.append(sid);
        }
        if (requestType != null && !requestType.trim().equals("")) {
            stringBuilder.append(requestType);
        }
        String result = stringBuilder.toString();
        try {
            signature = MD5Encoding(result).toUpperCase();
        } catch (NoSuchAlgorithmException e) {

        }
        return signature;
    }


    // MD5加密
    private static String MD5Encoding(String source) throws NoSuchAlgorithmException {
        MessageDigest mdInst = MessageDigest.getInstance("MD5");
        byte[] input = source.getBytes(Charsets.UTF_8);
        mdInst.update(input);
        byte[] output = mdInst.digest();

        int i = 0;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < output.length; offset++) {
            i = output[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }



    // 获得支付签名
    public static String paySignature(String timeStamp, String allianceID, String secretKey, String sid, String requestType) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(timeStamp);
        if (Integer.parseInt(allianceID) > 0) {
            stringBuilder.append(allianceID);
        }
        String signature = "";
        if (secretKey != null && !secretKey.equals("")) {
            stringBuilder.append(secretKey);
        } else {
            return "";
        }
        if (Integer.parseInt(sid) > 0) {
            stringBuilder.append(sid);
        }
        if (requestType != null && !requestType.trim().equals("")) {
            stringBuilder.append(requestType);
        }
        String result = stringBuilder.toString();
        try {
            signature = MD5Encoding(result).toUpperCase();
        } catch (NoSuchAlgorithmException e) {

        }
        return signature;
    }

}
