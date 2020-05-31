package xyz.srclab.common.convert;

import xyz.srclab.annotation.Immutable;
import xyz.srclab.annotation.Nullable;

import java.lang.reflect.Type;

@Immutable
public interface ConvertHandler {

    @Nullable
    Object convert(Object from, Type to, Converter converter);
}
