package com.simpleproject;

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.apache.deltaspike.core.api.provider.BeanProvider;

import javax.enterprise.context.ApplicationScoped;
import java.lang.annotation.Annotation;

/**
 * Created by otavio on 09/05/2017.
 */
public class ApplicationManager {

    public static CdiContainer container = CdiContainerLoader.getCdiContainer();

    public static boolean started = false;

    public static <T> T getBean(Class<T> type) {
        return BeanProvider.getContextualReference(type);
    }

    public static <T> T getBean(Class<T> type, Annotation... qualifiers) {
        return BeanProvider.getContextualReference(type, qualifiers);
    }

    public static void startContext(Class<? extends Annotation> context) {
        getContextControl().startContext(context);
    }

    public static void stopContext(Class<? extends Annotation> context) {
        getContextControl().stopContext(context);
    }

    public static void boot() {
        if (!started) {
            container.boot();
            getContextControl().startContext(ApplicationScoped.class);
        }
    }

    private static ContextControl getContextControl() {
        return BeanProvider.getContextualReference(ContextControl.class);
    }

    public static void runInContext(Class<? extends Annotation> context, CodeBlock runBlock) {
        ApplicationManager.startContext(context);
        runBlock.run();
        ApplicationManager.stopContext(context);
    }

    public static boolean isProxy(Object obj) {
        try {
            return Class.forName("org.jboss.weld.bean.proxy.ProxyObject").isInstance(obj);
        } catch (Exception e) {
        }
        return false;
    }

}
