package com.tuniu.abt.base;

import com.tuniu.vla.base.db.ReadWriteDbRoute;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 读写分离拦截
 * Created by rongzhiming on 2015/11/5.
 */
@Aspect
@Component
public class ReadWriteAspect {

    @Autowired
    private ReadWriteDbRoute route;

    @Around("within(com.tuniu.abt..*) && @annotation(com.tuniu.vla.base.db.ReadOnly)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        Object rst;
        route.setReadOnly(true);
        try {
            rst = pjp.proceed();
        } finally {
            route.setReadOnly(false);
        }
        return rst;
    }
}
