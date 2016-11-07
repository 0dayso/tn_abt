package com.tuniu.abt.service.cancel.impl;

import com.tuniu.abt.intf.dto.cancel.CancelResult;
import com.tuniu.abt.service.cancel.CancelProcessor;
import com.tuniu.vla.base.utils.ExceptionMessageUtils;

import javax.annotation.Resource;

/**
 * Created by chengyao on 2016/3/23.
 */
public abstract class AbstractCancelProcessor implements CancelProcessor {

    @Resource
    private ExceptionMessageUtils exceptionMessageUtils;

    void dealRemark(CancelResult ctripCancelResult) {
        if (ctripCancelResult.getThrowable() == null) {
            ctripCancelResult.setRemark("成功");
        } else {
            ctripCancelResult.setRemark("失败，原因="
                    + exceptionMessageUtils.findAllExString(ctripCancelResult.getThrowable()));
        }
    }

}
