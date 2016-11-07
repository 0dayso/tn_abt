package com.tuniu.abt.intf.tsp;

import com.tuniu.abt.intf.tsp.dto.pla.SendEmailInternalRequest;
import com.tuniu.abt.intf.tsp.dto.pla.SendEmailRequest;
import com.tuniu.abt.intf.tsp.dto.pla.SendEmailResponse;
import com.tuniu.vla.base.tsp.annotation.TspMethod;
import com.tuniu.vla.base.tsp.intf.TspInterface;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PLA底层服务接口
 *
 * Created by chengyao on 2016/3/16.
 */
@Repository
public interface TspPlaInterface extends TspInterface {

    /**
     * edm发送邮件
     * @param requests 请求
     * @return 返回结果
     */
    @TspMethod(serviceName = "PLA.EMAIL.EdmController.sendEmail")
    List<SendEmailResponse> sendEmail(List<SendEmailRequest> requests);

    /**
     * edm发送邮件
     * @param request 请求
     * @return 返回结果
     */
    @TspMethod(serviceName = "PLA.EMAIL.EdmController.sendInternalEmail", wrapResp = false)
    String sendInternalEmail(SendEmailInternalRequest request);


}
