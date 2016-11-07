package com.tuniu.abt.intf.tsp;

import com.tuniu.abt.intf.dto.aop.airline.AopBookingRequest;
import com.tuniu.abt.intf.dto.aop.airline.AopBookingResponse;
import com.tuniu.abt.intf.dto.aop.airline.GetVendorRequest;
import com.tuniu.abt.intf.dto.aop.airline.GetVendorResponse;
import com.tuniu.vla.base.tsp.annotation.TspMethod;
import com.tuniu.vla.base.tsp.intf.TspInterface;
import org.springframework.stereotype.Component;

/**
 * 运价直连-开放平台伪直连接口
 * @author tangaizhong
 *
 */
@Component
public interface TspAopAirlineInterface extends TspInterface {

	 @TspMethod(serviceName = "AOP.MAIN.directAirCp.booking" , socketTimeout=20, connectTimeout = 20)
	 AopBookingResponse book(AopBookingRequest aopBookingRequest);
	 
	 @TspMethod(serviceName = "AOP.MAIN.directAirCp.getVendor")
	 GetVendorResponse getVendor(GetVendorRequest getVendorRequest);
	 
//	 @TspMethod(serviceName = "AOP.MAIN.directAirCp.cancelBooking")
//	 GetVendorResponse cancel(ReqCancel reqCancel);
	 
}
