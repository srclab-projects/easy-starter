package xyz.srclab.common.collection

import xyz.srclab.common.base.asAny

class IterableOps<T>(iterable: Iterable<T>) :
    BaseIterableOps<T, Iterable<T>, MutableIterable<T>, IterableOps<T>>(iterable) {

    fun plus(element: T): ListOps<T> {
        return finalIterable().plus(element).toListOps()
    }

    fun plus(elements: Array<out T>): ListOps<T> {
        return finalIterable().plus(elements).toListOps()
    }

    fun plus(elements: Iterable<T>): ListOps<T> {
        return finalIterable().plus(elements).toListOps()
    }

    fun plus(elements: Sequence<T>): ListOps<T> {
        return finalIterable().plus(elements).toListOps()
    }

    fun minus(element: T): ListOps<T> {
        return finalIterable().minus(element).toListOps()
    }

    fun minus(elements: Array<out T>): ListOps<T> {
        return finalIterable().minus(elements).toListOps()
    }

    fun minus(elements: Iterable<T>): ListOps<T> {
        return finalIterable().minus(elements).toListOps()
    }

    fun minus(elements: Sequence<T>): ListOps<T> {
        return finalIterable().minus(elements).toListOps()
    }

    override fun Iterable<T>.asThis(): IterableOps<T> {
        iterable = this
        return this@IterableOps
    }

    override fun <T> Iterable<T>.toIterableOps(): IterableOps<T> {
        iterable = this.asAny()
        return this@IterableOps.asAny()
    }

    override fun <T> List<T>.toListOps(): ListOps<T> {
        return ListOps.opsFor(this)
    }

    override fun <T> Set<T>.toSetOps(): SetOps<T> {
        return SetOps.opsFor(this)
    }

    override fun <K, V> Map<K, V>.toMapOps(): MapOps<K, V> {
        return MapOps.opsFor(this)
    }

    companion object {

        @JvmStatic
        fun <T> opsFor(iterable: Iterable<T>): IterableOps<T> {
            return IterableOps(iterable)
        }

        // Others

        @JvmStatic
        fun <T> Any.anyAsIterable(): Iterable<T> {
            return this.anyAsIterableOrNull()
                ?: throw IllegalArgumentException("Cannot from any to Iterable: $this.")
        }

        @JvmStatic
        fun <T> Any.anyAsMutableIterable(): MutableIterable<T> {
            return this.anyAsMutableIterableOrNull()
                ?: throw IllegalArgumentException("Cannot from any to MutableIterable: $this.")
        }

        @JvmStatic
        fun <T> Any.anyAsIterableOrNull(): Iterable<T>? {
            if (this is Iterable<*>) {
                return this.asAny()
            }
            if (this.javaClass.isArray) {
                return this.arrayAsListOrNull()
            }
            return null
        }

        @JvmStatic
        fun <T> Any.anyAsMutableIterableOrNull(): MutableIterable<T>? {
            if (this is MutableIterable<*>) {
                return this.asAny()
            }
            if (this.javaClass.isArray) {
                return this.arrayAsListOrNull()
            }
            return null
        }
    }
}