package xyz.srclab.common.bean;

import xyz.srclab.annotation.Immutable;
import xyz.srclab.annotation.Nullable;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Optional;

@Immutable
public interface BeanProperty {

    String getName();

    Class<?> getType();

    Type getGenericType();

    boolean isReadable();

    @Nullable
    Object getValue(Object bean) throws UnsupportedOperationException;

    Optional<Method> getReadMethod();

    boolean isWriteable();

    void setValue(Object bean, @Nullable Object value) throws UnsupportedOperationException;

    Optional<Method> getWriteMethod();
}