package com.tuniu.abt.service.book.work;

import com.tuniu.abt.intf.exception.BizzException;
import org.springframework.core.Ordered;

/**
 * 处理占位的工作项目接口
 *
 * Created by chengyao on 2016/1/12.
 */
public interface BookWorkHandler extends Ordered {

    /**
     * 是否进入该工作项目
     * @return true进入
     */
    boolean apply();

    /**
     * 处理方法
     * @return 是否继续下一个流程，true继续
     */
    boolean processing() throws Exception;

    /**
     * 处理业务异常
     * @param ex 业务异常
     * @return 是否继续，true继续
     */
    boolean onBusinessException(BizzException ex);

}
