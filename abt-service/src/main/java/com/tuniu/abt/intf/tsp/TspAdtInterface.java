package com.tuniu.abt.intf.tsp;

import com.tuniu.abt.intf.tsp.dto.adt.InquiryPriceDetailVo;
import org.springframework.stereotype.Component;

import com.tuniu.abt.intf.tsp.dto.adt.FreightCalculateRequestVo;
import com.tuniu.vla.base.tsp.annotation.TspMethod;
import com.tuniu.vla.base.tsp.intf.TspInterface;

/**
 * 
 * <Description>运价系统TSP服务接口 <br> 
 *  
 * @author lanlugang<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016-3-24 <br>
 */
@Component
public interface TspAdtInterface  extends TspInterface {
    
    @TspMethod(serviceName = "ADT.SELPRI.SearchPriceAction.qryDetailPrice")
    InquiryPriceDetailVo qryDetailPrice(FreightCalculateRequestVo requestVo);

}
