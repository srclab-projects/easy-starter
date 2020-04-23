package xyz.srclab.common.bean.provider.defaults;

import xyz.srclab.common.bean.*;
import xyz.srclab.common.bean.provider.BeanProvider;
import xyz.srclab.common.bean.provider.BeanProviderManager;

/**
 * @author sunqian
 */
final class DefaultBeanSupport {

    private static final BeanProvider beanProvider = BeanProviderManager.INSTANCE.getProvider();

    static BeanOperator getBeanOperator() {
        return beanProvider.getBeanOperator();
    }

    static BeanConverter getBeanConverter() {
        return beanProvider.getBeanConverter();
    }

    static BeanConverterHandler getBeanConverterHandler() {
        return beanProvider.getBeanConverterHandler();
    }

    static BeanResolver getBeanResolver() {
        return beanProvider.getBeanResolver();
    }

    static BeanResolverHandler getBeanResolverHandler() {
        return beanProvider.getBeanResolverHandler();
    }
}