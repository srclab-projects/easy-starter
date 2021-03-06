package xyz.srclab.common.reflect

import xyz.srclab.common.exception.ShouldNotException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

abstract class TypeRef<T> {

    @get:JvmName("type")
    val type: Type

    protected constructor() {
        type = reflectGenericSuperclass()
    }

    protected constructor(type: Type) {
        this.type = type
    }

    private fun reflectGenericSuperclass(): Type {
        val generic = this.javaClass.genericSuperclass(null, TypeRef::class.java)
        if (generic !is ParameterizedType) {
            throw ShouldNotException(
                "Here: " +
                        "[[val generic = this.javaClass.genericSuperclass(null, TypeRef::class.java)]] " +
                        "should return a ParameterizedType but a $generic."
            )
        }
        return generic.actualTypeArguments[0]
    }

    override fun toString(): String {
        return "TypeRef($type)"
    }

    companion object {

        @JvmStatic
        fun <T> of(type: Type): TypeRef<T> {
            return object : TypeRef<T>(type) {}
        }
    }
}

val <T> T.typeRef: TypeRef<T>
    get() {
        return object : TypeRef<T>() {}
    }

fun <T> typeRef(): TypeRef<T> {
    return object : TypeRef<T>() {}
}