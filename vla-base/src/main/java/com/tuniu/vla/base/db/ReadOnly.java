package com.tuniu.vla.base.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 读写分离部分，只读数据源选择注解
 * Created by rongzhiming on 2015/11/5.
 */
@Target(ElementType.METHOD)
public @interface ReadOnly {
}
