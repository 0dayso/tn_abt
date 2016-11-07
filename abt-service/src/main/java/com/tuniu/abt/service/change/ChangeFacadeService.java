package com.tuniu.abt.service.change;

import com.tuniu.abt.intf.dto.change.ProcChangeData;
import com.tuniu.abt.intf.dto.change.ReqChange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/5/3.
 */
@Service
public class ChangeFacadeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeFacadeService.class);

    @Resource
    private ChangeAsyncTask changeAsyncTask;

    public Object change(ReqChange request) {
        ProcChangeData procChangeData = new ProcChangeData();
        procChangeData.setReqChange(request);
        ChangeDataSupport.setData(procChangeData);
        changeAsyncTask.execute(request.getCallback());
        return null;
    }


}
