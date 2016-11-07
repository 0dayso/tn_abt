package com.tuniu.abt.base.patch;

import javassist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定制修改有问题的方法
 *
 * Created by chengyao on 2016/4/29.
 */
public class DefaultClassPatch {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultClassPatch.class);

    public void patchAll() {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(this.getClass()));
        try {
            patchPNRObject(pool);
        } catch(Exception ex) {
            LOGGER.warn("can't patch class. " + ex.getMessage(), ex);
        }
    }

    /**
     * com.travelsky.ibe.client.pnr.PNRObject 类会导致JSON.toJSONSTRING异常。
     *
     * 重新修改方法代码，规避问题
     *
     */
    private static void patchPNRObject(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass cc1 = classPool.get("com.travelsky.ibe.client.pnr.PNRObject");
        clearMethod(cc1, "getPsgrid", "null");
        clearMethod(cc1, "getIndex", "0");
        clearMethod(cc1, "getTextInPNR", "null");
        cc1.toClass(null, null);

        CtClass cc2 = classPool.get("com.travelsky.ibe.client.pnr.PNRFC");
        clearMethod(cc2, "getTextInPNR", "null");
        cc2.toClass(null, null);

        CtClass cc3 = classPool.get("com.travelsky.ibe.client.pnr.PNRFN");
        clearMethod(cc3, "getTextInPNR", "null");
        cc3.toClass(null, null);
    }

    private static void clearMethod(CtClass cc, String methodName, String returnValue) {
        try {
            CtMethod m = cc.getDeclaredMethod(methodName);
            m.setBody("{return " + returnValue + ";}");
        } catch (NotFoundException e) {
            LOGGER.warn("can't find " + cc.getName() + " method: " + methodName);
        } catch (Exception e) {
            LOGGER.warn("clear class: " + cc.getName() + ", method: " + methodName + " error.", e);
        }
    }
}
