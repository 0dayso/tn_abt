package com.tuniu.vla.base.tsp;

import com.google.common.reflect.Reflection;
import org.springframework.beans.factory.FactoryBean;

/**
 * BeanFactory that enables injection of Tsp Client Interfaces.
 *
 * Created by chengyao on 2016/2/5.
 */
public class TspClientFactoryBean<T> implements FactoryBean<T> {

    private Class<T> clientInterface;

    private TspClientInvocationListener tspClientInvocationListener;

    public void setTspClientInvocationListener(TspClientInvocationListener tspClientInvocationListener) {
        this.tspClientInvocationListener = tspClientInvocationListener;
    }

    public void setClientInterface(Class<T> clientInterface) {
        this.clientInterface = clientInterface;
    }

    @Override
    public T getObject() throws Exception {
        return Reflection.newProxy(this.clientInterface, new TspClientInvocationHandler(tspClientInvocationListener));
    }

    @Override
    public Class<?> getObjectType() {
        return this.clientInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
