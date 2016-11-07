package org.springframework.cache.interceptor;

import com.tuniu.vla.base.exception.RuntimeWrapException;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.cache.Cache;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * Useful in combination with a {@link net.sf.ehcache.constructs.blocking.BlockingCache}. When the intercepted method
 * call fails, removes the lock created during the get() operation so other threads can try again.
 *
 *
 * 参考 http://henningpetersen.com/post/17/thundering-herds-spring-cache-and-ehcache
 *
 * @Author 14080241
 */
public class UnblockingOnExceptionCacheInterceptor extends CacheInterceptor {

    private static final long serialVersionUID = 4904806278182659121L;

    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();

        CacheOperationInvoker aopAllianceInvoker = new CacheOperationInvoker() {
            public Object invoke() {
                try {
                    return invocation.proceed();
                } catch (Throwable ex) {
                    throw new ThrowableWrapper(ex);
                }
            }
        };

        try {
            return execute(aopAllianceInvoker, invocation.getThis(), method, invocation.getArguments());
        } catch (RuntimeWrapException ex) {
            Throwable e = ex.getCause();
            if (e instanceof CacheOperationInvoker.ThrowableWrapper) {
                throw ((CacheOperationInvoker.ThrowableWrapper) e).getOriginal();
            } else {
                throw ex;
            }
        } catch (CacheOperationInvoker.ThrowableWrapper th) {
            throw th.getOriginal();
        }
    }

    @Override
    protected Object execute(CacheOperationInvoker invoker, Object target, Method method, Object[] args) {
        try {
            return super.execute(invoker, target, method, args);
        } catch (Exception e) {
            // get backing class
            Class<?> targetClass = AopProxyUtils.ultimateTargetClass(target);
            if (targetClass == null) {
                targetClass = target.getClass();
            }

            final Collection<CacheOperation> cacheOps =
                    getCacheOperationSource().getCacheOperations(method, targetClass);

            for (final CacheOperation cacheOp : cacheOps) {
                //generate key
                CacheOperationContext context = getOperationContext(cacheOp, method, args, target, targetClass);
                Object key = context.generateKey(cacheOp.getKey());
                for (final Cache c : this.getCaches(context, this.getCacheResolver())) {
                    c.put(key, null);
                }
            }
            throw new RuntimeWrapException(e);
        }
    }

    protected CacheOperationContext getOperationContext(
            CacheOperation operation, Method method, Object[] args, Object target, Class<?> targetClass) {

        CacheOperationMetadata metadata = getCacheOperationMetadata(operation, method, targetClass);
        return new CacheOperationContext(metadata, args, target);
    }
}