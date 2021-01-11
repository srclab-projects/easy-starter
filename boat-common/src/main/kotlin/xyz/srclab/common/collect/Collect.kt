@file:JvmName("Collect")
@file:JvmMultifileClass

package xyz.srclab.common.collect

import xyz.srclab.common.base.*
import xyz.srclab.common.collection.BaseIterableOps.Companion.asToSet
import xyz.srclab.common.collection.BaseIterableOps.Companion.forEachIndexed
import xyz.srclab.common.collection.BaseIterableOps.Companion.toSet
import xyz.srclab.common.collection.arrayAsListOrNull
import xyz.srclab.common.collection.componentTypeToArray
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*
import java.util.stream.Stream
import java.util.stream.StreamSupport
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashMap
import kotlin.collections.LinkedHashSet
import kotlin.random.Random
import kotlin.streams.asStream
import kotlin.toBigDecimal
import kotlin.toBigInteger

import kotlin.collections.addAll as addAllKt
import kotlin.collections.all as allKt
import kotlin.collections.any as anyKt
import kotlin.collections.asSequence as asSequenceKt
import kotlin.collections.associate as associateKt
import kotlin.collections.associateBy as associateByKt
import kotlin.collections.associateByTo as associateByToKt
import kotlin.collections.associateTo as associateToKt
import kotlin.collections.associateWith as associateWithKt
import kotlin.collections.associateWithTo as associateWithToKt
import kotlin.collections.chunked as chunkedKt
import kotlin.collections.contains as containsKt
import kotlin.collections.count as countKt
import kotlin.collections.distinct as distinctKt
import kotlin.collections.distinctBy as distinctByKt
import kotlin.collections.drop as dropKt
import kotlin.collections.dropWhile as dropWhileKt
import kotlin.collections.elementAt as elementAtKt
import kotlin.collections.elementAtOrElse as elementAtOrElseKt
import kotlin.collections.elementAtOrNull as elementAtOrNullKt
import kotlin.collections.filter as filterKt
import kotlin.collections.filterIndexed as filterIndexedKt
import kotlin.collections.filterIndexedTo as filterIndexedToKt
import kotlin.collections.filterNotNull as filterNotNullKt
import kotlin.collections.filterTo as filterToKt
import kotlin.collections.find as findKt
import kotlin.collections.first as firstKt
import kotlin.collections.firstOrNull as firstOrNullKt
import kotlin.collections.flatMap as flatMapKt
import kotlin.collections.flatMapIndexed as flatMapIndexedKt
import kotlin.collections.flatMapIndexedTo as flatMapIndexedToKt
import kotlin.collections.flatMapTo as flatMapToKt
import kotlin.collections.fold as foldKt
import kotlin.collections.foldIndexed as foldIndexedKt
import kotlin.collections.forEachIndexed as forEachIndexedKt
import kotlin.collections.groupBy as groupByKt
import kotlin.collections.groupByTo as groupByToKt
import kotlin.collections.indexOf as indexOfKt
import kotlin.collections.indexOfFirst as indexOfFirstKt
import kotlin.collections.indexOfLast as indexOfLastKt
import kotlin.collections.intersect as intersectKt
import kotlin.collections.joinTo as joinToKt
import kotlin.collections.joinToString as joinToStringKt
import kotlin.collections.last as lastKt
import kotlin.collections.lastIndexOf as lastIndexOfKt
import kotlin.collections.lastOrNull as lastOrNullKt
import kotlin.collections.map as mapKt
import kotlin.collections.mapIndexed as mapIndexedKt
import kotlin.collections.mapIndexedNotNull as mapIndexedNotNullKt
import kotlin.collections.mapIndexedNotNullTo as mapIndexedNotNullToKt
import kotlin.collections.mapIndexedTo as mapIndexedToKt
import kotlin.collections.mapNotNull as mapNotNullKt
import kotlin.collections.mapNotNullTo as mapNotNullToKt
import kotlin.collections.mapTo as mapToKt
import kotlin.collections.maxWithOrNull as maxWithOrNullKt
import kotlin.collections.minWithOrNull as minWithOrNullKt
import kotlin.collections.minus as minusKt
import kotlin.collections.none as noneKt
import kotlin.collections.plus as plusKt
import kotlin.collections.random as randomKt
import kotlin.collections.randomOrNull as randomOrNullKt
import kotlin.collections.reduce as reduceKt
import kotlin.collections.reduceIndexed as reduceIndexedKt
import kotlin.collections.reduceIndexedOrNull as reduceIndexedOrNullKt
import kotlin.collections.reduceOrNull as reduceOrNullKt
import kotlin.collections.removeAll as removeAllKt
import kotlin.collections.retainAll as retainAllKt
import kotlin.collections.reversed as reversedKt
import kotlin.collections.shuffled as shuffledKt
import kotlin.collections.sortedWith as sortedWithKt
import kotlin.collections.subtract as subtractKt
import kotlin.collections.sumOf as sumOfKt
import kotlin.collections.take as takeKt
import kotlin.collections.takeWhile as takeWhileKt
import kotlin.collections.toCollection as toCollectionKt
import kotlin.collections.toHashSet as toHashSetKt
import kotlin.collections.toList as toListKt
import kotlin.collections.toMutableList as toMutableListKt
import kotlin.collections.toMutableSet as toMutableSetKt
import kotlin.collections.toSet as toSetKt
import kotlin.collections.toSortedSet as toSortedSetKt
import kotlin.collections.toTypedArray as toTypedArrayKt
import kotlin.collections.union as unionKt
import kotlin.collections.windowed as windowedKt
import kotlin.collections.zip as zipKt
import kotlin.collections.zipWithNext as zipWithNextKt

import kotlin.sequences.all as allKt
import kotlin.sequences.any as anyKt
import kotlin.sequences.associate as associateKt
import kotlin.sequences.associateBy as associateByKt
import kotlin.sequences.associateByTo as associateByToKt
import kotlin.sequences.associateTo as associateToKt
import kotlin.sequences.associateWith as associateWithKt
import kotlin.sequences.associateWithTo as associateWithToKt
import kotlin.sequences.chunked as chunkedKt
import kotlin.sequences.contains as containsKt
import kotlin.sequences.count as countKt
import kotlin.sequences.distinct as distinctKt
import kotlin.sequences.distinctBy as distinctByKt
import kotlin.sequences.drop as dropKt
import kotlin.sequences.dropWhile as dropWhileKt
import kotlin.sequences.elementAt as elementAtKt
import kotlin.sequences.elementAtOrElse as elementAtOrElseKt
import kotlin.sequences.elementAtOrNull as elementAtOrNullKt
import kotlin.sequences.filter as filterKt
import kotlin.sequences.filterIndexed as filterIndexedKt
import kotlin.sequences.filterIndexedTo as filterIndexedToKt
import kotlin.sequences.filterNotNull as filterNotNullKt
import kotlin.sequences.filterTo as filterToKt
import kotlin.sequences.find as findKt
import kotlin.sequences.first as firstKt
import kotlin.sequences.firstOrNull as firstOrNullKt
import kotlin.sequences.flatMap as flatMapKt
import kotlin.sequences.flatMapIndexed as flatMapIndexedKt
import kotlin.sequences.flatMapIndexedTo as flatMapIndexedToKt
import kotlin.sequences.flatMapTo as flatMapToKt
import kotlin.sequences.fold as foldKt
import kotlin.sequences.foldIndexed as foldIndexedKt
import kotlin.sequences.forEachIndexed as forEachIndexedKt
import kotlin.sequences.groupBy as groupByKt
import kotlin.sequences.groupByTo as groupByToKt
import kotlin.sequences.indexOf as indexOfKt
import kotlin.sequences.indexOfFirst as indexOfFirstKt
import kotlin.sequences.indexOfLast as indexOfLastKt
import kotlin.sequences.last as lastKt
import kotlin.sequences.lastIndexOf as lastIndexOfKt
import kotlin.sequences.lastOrNull as lastOrNullKt
import kotlin.sequences.map as mapKt
import kotlin.sequences.mapIndexed as mapIndexedKt
import kotlin.sequences.mapIndexedNotNull as mapIndexedNotNullKt
import kotlin.sequences.mapIndexedNotNullTo as mapIndexedNotNullToKt
import kotlin.sequences.mapIndexedTo as mapIndexedToKt
import kotlin.sequences.mapNotNull as mapNotNullKt
import kotlin.sequences.mapNotNullTo as mapNotNullToKt
import kotlin.sequences.mapTo as mapToKt
import kotlin.sequences.maxWithOrNull as maxWithOrNullKt
import kotlin.sequences.minWithOrNull as minWithOrNullKt
import kotlin.sequences.minus as minusKt
import kotlin.sequences.none as noneKt
import kotlin.sequences.plus as plusKt
import kotlin.sequences.reduce as reduceKt
import kotlin.sequences.reduceIndexed as reduceIndexedKt
import kotlin.sequences.reduceIndexedOrNull as reduceIndexedOrNullKt
import kotlin.sequences.reduceOrNull as reduceOrNullKt
import kotlin.sequences.shuffled as shuffledKt
import kotlin.sequences.sortedWith as sortedWithKt
import kotlin.sequences.sumOf as sumOfKt
import kotlin.sequences.take as takeKt
import kotlin.sequences.takeWhile as takeWhileKt
import kotlin.sequences.toCollection as toCollectionKt
import kotlin.sequences.toHashSet as toHashSetKt
import kotlin.sequences.toList as toListKt
import kotlin.sequences.toMutableList as toMutableListKt
import kotlin.sequences.toMutableSet as toMutableSetKt
import kotlin.sequences.toSet as toSetKt
import kotlin.sequences.toSortedSet as toSortedSetKt
import kotlin.sequences.windowed as windowedKt
import kotlin.sequences.zip as zipKt
import kotlin.sequences.zipWithNext as zipWithNextKt

fun <T> Iterable<T>.contains(element: T): Boolean {
    return this.containsKt(element)
}

fun <T> Iterable<T>.containsAll(elements: Array<out T>): Boolean {
    return this.containsAll(elements.toSetKt())
}

fun <T> Iterable<T>.containsAll(elements: Iterable<T>): Boolean {
    return this.containsAll(elements.asToSet())
}

fun <T> Iterable<T>.containsAll(elements: Collection<T>): Boolean {
    return this.asToSet().containsAll(elements)
}

fun <T> Iterable<T>.count(): Int {
    return this.countKt()
}

inline fun <T> Iterable<T>.count(predicate: (T) -> Boolean): Int {
    return this.countKt(predicate)
}

fun <T> Iterable<T>.isEmpty(): Boolean {
    var hasElement = false
    for (t in this) {
        hasElement = true
        break
    }
    return !hasElement
}

fun <T> Iterable<T>.isNotEmpty(): Boolean {
    return !this.isEmpty()
}

fun <T> Iterable<T>.any(): Boolean {
    return this.anyKt()
}

inline fun <T> Iterable<T>.any(predicate: (T) -> Boolean): Boolean {
    return this.anyKt(predicate)
}

fun <T> Iterable<T>.none(): Boolean {
    return this.noneKt()
}

inline fun <T> Iterable<T>.none(predicate: (T) -> Boolean): Boolean {
    return this.noneKt(predicate)
}

inline fun <T> Iterable<T>.all(predicate: (T) -> Boolean): Boolean {
    return this.allKt(predicate)
}

fun <T> Iterable<T>.first(): T {
    return this.firstKt()
}

inline fun <T> Iterable<T>.first(predicate: (T) -> Boolean): T {
    return this.firstKt(predicate)
}

fun <T> Iterable<T>.firstOrNull(): T? {
    return this.firstOrNullKt()
}

inline fun <T> Iterable<T>.firstOrNull(predicate: (T) -> Boolean): T? {
    return this.firstOrNullKt(predicate)
}

fun <T> Iterable<T>.last(): T {
    return this.lastKt()
}

inline fun <T> Iterable<T>.last(predicate: (T) -> Boolean): T {
    return this.lastKt(predicate)
}

fun <T> Iterable<T>.lastOrNull(): T? {
    return this.lastOrNullKt()
}

inline fun <T> Iterable<T>.lastOrNull(predicate: (T) -> Boolean): T? {
    return this.lastOrNullKt(predicate)
}

fun <T> Iterable<T>.random(): T {
    return this.asToList().randomKt()
}

fun <T> Iterable<T>.random(random: Random): T {
    return this.asToList().randomKt(random)
}

fun <T> Iterable<T>.randomOrNull(): T? {
    return this.asToList().randomOrNullKt()
}

fun <T> Iterable<T>.randomOrNull(random: Random): T? {
    return this.asToList().randomOrNullKt(random)
}

fun <T> Iterable<T>.elementAt(index: Int): T {
    return this.elementAtKt(index)
}

fun <T> Iterable<T>.elementAtOrElse(index: Int, defaultValue: (index: Int) -> T): T {
    return this.elementAtOrElseKt(index, defaultValue)
}

fun <T> Iterable<T>.elementAtOrNull(index: Int): T? {
    return this.elementAtOrNullKt(index)
}

inline fun <T> Iterable<T>.find(predicate: (T) -> Boolean): T? {
    return this.findKt(predicate)
}

inline fun <T> Iterable<T>.findLast(predicate: (T) -> Boolean): T? {
    return this.firstKt(predicate)
}

fun <T> Iterable<T>.indexOf(element: T): Int {
    return this.indexOfKt(element)
}

inline fun <T> Iterable<T>.indexOf(predicate: (T) -> Boolean): Int {
    return this.indexOfFirstKt(predicate)
}

fun <T> Iterable<T>.lastIndexOf(element: T): Int {
    return this.lastIndexOfKt(element)
}

inline fun <T> Iterable<T>.lastIndexOf(predicate: (T) -> Boolean): Int {
    return this.indexOfLastKt(predicate)
}

fun <T> Iterable<T>.take(n: Int): List<T> {
    return this.takeKt(n)
}

inline fun <T> Iterable<T>.takeWhile(predicate: (T) -> Boolean): List<T> {
    return this.takeWhileKt(predicate)
}

fun <T, C : MutableCollection<in T>> Iterable<T>.takeTo(n: Int, destination: C): C {
    destination.addAll(this.take(n))
    return destination
}

inline fun <T, C : MutableCollection<in T>> Iterable<T>.takeWhileTo(
    destination: C,
    predicate: (T) -> Boolean
): C {
    destination.addAll(this.takeWhile(predicate))
    return destination
}

fun <T, C : MutableCollection<in T>> Iterable<T>.takeAllTo(destination: C): C {
    destination.addAllKt(this)
    return destination
}

fun <T> Iterable<T>.drop(n: Int): List<T> {
    return this.dropKt(n)
}

inline fun <T> Iterable<T>.dropWhile(predicate: (T) -> Boolean): List<T> {
    return this.dropWhileKt(predicate)
}

fun <T, C : MutableCollection<in T>> Iterable<T>.dropTo(n: Int, destination: C): C {
    destination.addAll(this.drop(n))
    return destination
}

inline fun <T, C : MutableCollection<in T>> Iterable<T>.dropWhileTo(
    destination: C,
    predicate: (T) -> Boolean
): C {
    destination.addAll(this.dropWhile(predicate))
    return destination
}

inline fun <T> Iterable<T>.forEachIndexed(action: (index: Int, T) -> Unit) {
    return this.forEachIndexedKt(action)
}

inline fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T> {
    return this.filterKt(predicate)
}

inline fun <T> Iterable<T>.filterIndexed(predicate: (index: Int, T) -> Boolean): List<T> {
    return this.filterIndexedKt(predicate)
}

fun <T> Iterable<T>.filterNotNull(): List<T> {
    return this.filterNotNullKt()
}

inline fun <T, C : MutableCollection<in T>> Iterable<T>.filterTo(destination: C, predicate: (T) -> Boolean): C {
    return this.filterToKt(destination, predicate)
}

inline fun <T, C : MutableCollection<in T>> Iterable<T>.filterIndexedTo(
    destination: C,
    predicate: (index: Int, T) -> Boolean
): C {
    return this.filterIndexedToKt(destination, predicate)
}

fun <C : MutableCollection<in T>, T> Iterable<T>.filterNotNullTo(destination: C): C {
    return this.filterTo(destination, { it !== null })
}

inline fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R> {
    return this.mapKt(transform)
}

inline fun <T, R> Iterable<T>.mapIndexed(transform: (index: Int, T) -> R): List<R> {
    return this.mapIndexedKt(transform)
}

inline fun <T, R : Any> Iterable<T>.mapNotNull(transform: (T) -> R?): List<R> {
    return this.mapNotNullKt(transform)
}

inline fun <T, R : Any> Iterable<T>.mapIndexedNotNull(transform: (index: Int, T) -> R?): List<R> {
    return this.mapIndexedNotNullKt(transform)
}

inline fun <T, R, C : MutableCollection<in R>> Iterable<T>.mapTo(destination: C, transform: (T) -> R): C {
    return this.mapToKt(destination, transform)
}

inline fun <T, R, C : MutableCollection<in R>> Iterable<T>.mapIndexedTo(
    destination: C,
    transform: (index: Int, T) -> R
): C {
    return this.mapIndexedToKt(destination, transform)
}

inline fun <T, R : Any, C : MutableCollection<in R>> Iterable<T>.mapNotNullTo(
    destination: C,
    transform: (T) -> R?
): C {
    return this.mapNotNullToKt(destination, transform)
}

inline fun <T, R : Any, C : MutableCollection<in R>> Iterable<T>.mapIndexedNotNullTo(
    destination: C,
    transform: (index: Int, T) -> R?
): C {
    return this.mapIndexedNotNullToKt(destination, transform)
}

inline fun <T, R> Iterable<T>.flatMap(transform: (T) -> Iterable<R>): List<R> {
    return this.flatMapKt(transform)
}

inline fun <T, R> Iterable<T>.flatMapIndexed(transform: (index: Int, T) -> Iterable<R>): List<R> {
    return this.flatMapIndexedKt(transform)
}

inline fun <T, R, C : MutableCollection<in R>> Iterable<T>.flatMapTo(
    destination: C,
    transform: (T) -> Iterable<R>
): C {
    return this.flatMapToKt(destination, transform)
}

inline fun <T, R, C : MutableCollection<in R>> Iterable<T>.flatMapIndexedTo(
    destination: C,
    transform: (index: Int, T) -> Iterable<R>
): C {
    return this.flatMapIndexedToKt(destination, transform)
}

inline fun <T, K, V> Iterable<T>.associate(
    keySelector: (T) -> K,
    valueTransform: (T) -> V
): Map<K, V> {
    return this.associateByKt(keySelector, valueTransform)
}

inline fun <T, K, V> Iterable<T>.associate(transform: (T) -> Pair<K, V>): Map<K, V> {
    return this.associateKt(transform)
}

inline fun <T, K> Iterable<T>.associateKey(keySelector: (T) -> K): Map<K, T> {
    return this.associateByKt(keySelector)
}

fun <T, K> Iterable<T>.associateKey(keys: Iterable<K>): Map<K, T> {
    return this.associateKeyTo(LinkedHashMap(), keys)
}

inline fun <T, V> Iterable<T>.associateValue(valueSelector: (T) -> V): Map<T, V> {
    return this.associateWithKt(valueSelector)
}

fun <T, V> Iterable<T>.associateValue(values: Iterable<V>): Map<T, V> {
    return this.associateValueTo(LinkedHashMap(), values)
}

inline fun <T, K, V> Iterable<T>.associatePair(
    keySelector: (T) -> K,
    valueTransform: (T) -> V
): Map<K, V> {
    return this.associatePairTo(LinkedHashMap(), keySelector, valueTransform)
}

inline fun <T, K, V> Iterable<T>.associatePair(transform: (T, T) -> Pair<K, V>): Map<K, V> {
    return this.associatePairTo(LinkedHashMap(), transform)
}

inline fun <T, K, V> Iterable<T>.associatePair(
    complement: T,
    keySelector: (T) -> K,
    valueTransform: (T) -> V
): Map<K, V> {
    return this.associatePairTo(complement, LinkedHashMap(), keySelector, valueTransform)
}

inline fun <T, K, V> Iterable<T>.associatePair(
    complement: T,
    transform: (T, T) -> Pair<K, V>
): Map<K, V> {
    return this.associatePairTo(complement, LinkedHashMap(), transform)
}

inline fun <T, K, V, M : MutableMap<in K, in V>> Iterable<T>.associateTo(
    destination: M,
    keySelector: (T) -> K,
    valueTransform: (T) -> V
): M {
    return this.associateByToKt(destination, keySelector, valueTransform)
}

inline fun <T, K, V, M : MutableMap<in K, in V>> Iterable<T>.associateTo(
    destination: M,
    transform: (T) -> Pair<K, V>
): M {
    return this.associateToKt(destination, transform)
}

inline fun <T, K, M : MutableMap<in K, in T>> Iterable<T>.associateKeyTo(
    destination: M,
    keySelector: (T) -> K
): M {
    return this.associateByToKt(destination, keySelector)
}

fun <T, K, M : MutableMap<in K, in T>> Iterable<T>.associateKeyTo(
    destination: M,
    keys: Iterable<K>
): M {
    val valueIt = this.iterator()
    for (key in keys) {
        if (!valueIt.hasNext()) {
            break
        }
        destination[key] = valueIt.next()
    }
    return destination
}

inline fun <T, V, M : MutableMap<in T, in V>> Iterable<T>.associateValueTo(
    destination: M,
    valueSelector: (T) -> V
): M {
    return this.associateWithToKt(destination, valueSelector)
}

fun <T, V, M : MutableMap<in T, in V>> Iterable<T>.associateValueTo(
    destination: M,
    values: Iterable<V>
): M {
    val valueIt = values.iterator()
    for (key in this) {
        if (!valueIt.hasNext()) {
            break
        }
        destination[key] = valueIt.next()
    }
    return destination
}

inline fun <T, K, V, M : MutableMap<in K, in V>> Iterable<T>.associatePairTo(
    destination: M,
    keySelector: (T) -> K,
    valueTransform: (T) -> V
): M {
    val iterator = this.iterator()
    while (iterator.hasNext()) {
        val ik = iterator.next()
        if (iterator.hasNext()) {
            val iv = iterator.next()
            val k = keySelector(ik)
            val v = valueTransform(iv)
            destination.put(k, v)
        } else {
            break
        }
    }
    return destination
}

inline fun <T, K, V, M : MutableMap<in K, in V>> Iterable<T>.associatePairTo(
    destination: M,
    transform: (T, T) -> Pair<K, V>
): M {
    val iterator = this.iterator()
    while (iterator.hasNext()) {
        val ik = iterator.next()
        if (iterator.hasNext()) {
            val iv = iterator.next()
            val pair = transform(ik, iv)
            destination.put(pair.first, pair.second)
        } else {
            break
        }
    }
    return destination
}

inline fun <T, K, V, M : MutableMap<in K, in V>> Iterable<T>.associatePairTo(
    complement: T,
    destination: M,
    keySelector: (T) -> K,
    valueTransform: (T) -> V
): M {
    val iterator = this.iterator()
    while (iterator.hasNext()) {
        val ik = iterator.next()
        if (iterator.hasNext()) {
            val iv = iterator.next()
            val k = keySelector(ik)
            val v = valueTransform(iv)
            destination.put(k, v)
        } else {
            val k = keySelector(ik)
            val v = valueTransform(complement)
            destination.put(k, v)
            break
        }
    }
    return destination
}

inline fun <T, K, V, M : MutableMap<in K, in V>> Iterable<T>.associatePairTo(
    complement: T,
    destination: M,
    transform: (T, T) -> Pair<K, V>
): M {
    val iterator = this.iterator()
    while (iterator.hasNext()) {
        val ik = iterator.next()
        if (iterator.hasNext()) {
            val iv = iterator.next()
            val pair = transform(ik, iv)
            destination.put(pair.first, pair.second)
        } else {
            val pair = transform(ik, complement)
            destination.put(pair.first, pair.second)
            break
        }
    }
    return destination
}

inline fun <T, K> Iterable<T>.groupBy(keySelector: (T) -> K): Map<K, List<T>> {
    return this.groupByKt(keySelector)
}

inline fun <T, K, V> Iterable<T>.groupBy(
    keySelector: (T) -> K,
    valueTransform: (T) -> V
): Map<K, List<V>> {
    return this.groupByKt(keySelector, valueTransform)
}

inline fun <T, K, M : MutableMap<in K, MutableList<T>>> Iterable<T>.groupByTo(
    destination: M,
    keySelector: (T) -> K
): M {
    return this.groupByToKt(destination, keySelector)
}

inline fun <T, K, V, M : MutableMap<in K, MutableList<V>>> Iterable<T>.groupByTo(
    destination: M,
    keySelector: (T) -> K,
    valueTransform: (T) -> V
): M {
    return this.groupByToKt(destination, keySelector, valueTransform)
}

inline fun <S, T : S> Iterable<T>.reduce(operation: (S, T) -> S): S {
    return this.reduceKt(operation)
}

inline fun <S, T : S> Iterable<T>.reduceIndexed(operation: (index: Int, S, T) -> S): S {
    return this.reduceIndexedKt(operation)
}

inline fun <S, T : S> Iterable<T>.reduceOrNull(operation: (S, T) -> S): S? {
    return this.reduceOrNullKt(operation)
}

inline fun <S, T : S> Iterable<T>.reduceIndexedOrNull(operation: (index: Int, S, T) -> S): S? {
    return this.reduceIndexedOrNullKt(operation)
}

inline fun <T, R> Iterable<T>.reduce(initial: R, operation: (R, T) -> R): R {
    return this.foldKt(initial, operation)
}

inline fun <T, R> Iterable<T>.reduceIndexed(initial: R, operation: (index: Int, R, T) -> R): R {
    return this.foldIndexedKt(initial, operation)
}

inline fun <T, R, V> Iterable<T>.zip(other: Array<out R>, transform: (T, R) -> V): List<V> {
    return this.zipKt(other, transform)
}

inline fun <T, R, V> Iterable<T>.zip(other: Iterable<R>, transform: (T, R) -> V): List<V> {
    return this.zipKt(other, transform)
}

inline fun <T, R> Iterable<T>.zipWithNext(transform: (T, T) -> R): List<R> {
    return this.zipWithNextKt(transform)
}

inline fun <T, R, V> Iterable<V>.unzip(transform: (V) -> Pair<T, R>): Pair<List<T>, List<R>> {
    if (this is Collection<V>) {
        val listT = ArrayList<T>(this.size)
        val listR = ArrayList<R>(this.size)
        for (e in this) {
            val pair = transform(e)
            listT.add(pair.first)
            listR.add(pair.second)
        }
        return listT to listR
    } else {
        val listT = LinkedList<T>()
        val listR = LinkedList<R>()
        for (e in this) {
            val pair = transform(e)
            listT.add(pair.first)
            listR.add(pair.second)
        }
        return ArrayList(listT) to ArrayList(listR)
    }
}

inline fun <T, R> Iterable<R>.unzipWithNext(transform: (R) -> Pair<T, T>): List<T> {
    if (this is Collection<R>) {
        val listT = ArrayList<T>(this.size)
        for (e in this) {
            val pair = transform(e)
            listT.add(pair.first)
            listT.add(pair.second)
        }
        return listT
    } else {
        val listT = LinkedList<T>()
        for (e in this) {
            val pair = transform(e)
            listT.add(pair.first)
            listT.add(pair.second)
        }
        return ArrayList(listT)
    }
}

fun <T> Iterable<T>.chunked(size: Int): List<List<T>> {
    return this.chunkedKt(size)
}

fun <T, R> Iterable<T>.chunked(size: Int, transform: (List<T>) -> R): List<R> {
    return this.chunkedKt(size, transform)
}

fun <T> Iterable<T>.windowed(size: Int, step: Int = 1, partialWindows: Boolean = false): List<List<T>> {
    return this.windowedKt(size, step, partialWindows)
}

fun <T, R> Iterable<T>.windowed(
    size: Int,
    step: Int = 1,
    partialWindows: Boolean = false,
    transform: (List<T>) -> R
): List<R> {
    return this.windowedKt(size, step, partialWindows, transform)
}

fun <T> Iterable<T>.intersect(other: Iterable<T>): Set<T> {
    return this.intersectKt(other)
}

fun <T> Iterable<T>.union(other: Iterable<T>): Set<T> {
    return this.unionKt(other)
}

fun <T> Iterable<T>.subtract(other: Iterable<T>): Set<T> {
    return this.subtractKt(other)
}

fun <T> Iterable<T>.distinct(): List<T> {
    return this.distinctKt()
}

inline fun <T, K> Iterable<T>.distinct(selector: (T) -> K): List<T> {
    return this.distinctByKt(selector)
}

@JvmOverloads
fun <T> Iterable<T>.sorted(comparator: Comparator<in T> = castSelfComparableComparator()): List<T> {
    return this.sortedWithKt(comparator)
}

fun <T> Iterable<T>.reversed(): List<T> {
    return this.reversedKt()
}

fun <T> Iterable<T>.shuffled(): List<T> {
    return this.shuffledKt()
}

fun <T> Iterable<T>.shuffled(random: Random): List<T> {
    return this.shuffledKt(random)
}

@JvmOverloads
fun <T> Iterable<T>.max(comparator: Comparator<in T> = castSelfComparableComparator()): T {
    return this.maxOrNull(comparator).notNull()
}

@JvmOverloads
fun <T> Iterable<T>.maxOrNull(comparator: Comparator<in T> = castSelfComparableComparator()): T? {
    return this.maxWithOrNullKt(comparator)
}

@JvmOverloads
fun <T> Iterable<T>.min(comparator: Comparator<in T> = castSelfComparableComparator()): T {
    return this.minOrNull(comparator).notNull()
}

@JvmOverloads
fun <T> Iterable<T>.minOrNull(comparator: Comparator<in T> = castSelfComparableComparator()): T? {
    return this.minWithOrNullKt(comparator)
}

@JvmOverloads
inline fun <T> Iterable<T>.sumInt(selector: (T) -> Int = { it.toInt() }): Int {
    return this.sumOfKt(selector)
}

@JvmOverloads
inline fun <T> Iterable<T>.sumLong(selector: (T) -> Long = { it.toLong() }): Long {
    return this.sumOfKt(selector)
}

@JvmOverloads
fun <T> Iterable<T>.sumDouble(selector: (T) -> Double = { it.toDouble() }): Double {
    return this.sumOfKt(selector)
}

@JvmOverloads
fun <T> Iterable<T>.sumBigInteger(selector: (T) -> BigInteger = { it.toBigInteger() }): BigInteger {
    return this.sumOfKt(selector)
}

@JvmOverloads
fun <T> Iterable<T>.sumBigDecimal(selector: (T) -> BigDecimal = { it.toBigDecimal() }): BigDecimal {
    return this.sumOfKt(selector)
}

@JvmOverloads
inline fun <T> Iterable<T>.averageInt(selector: (T) -> Int = { it.toInt() }): Int {
    var sum = 0
    var count = 0
    for (t in this) {
        sum += selector(t)
        count++
    }
    return if (count == 0) 0 else sum / count
}

@JvmOverloads
inline fun <T> Iterable<T>.averageLong(selector: (T) -> Long = { it.toLong() }): Long {
    var sum = 0L
    var count = 0
    for (t in this) {
        sum += selector(t)
        count++
    }
    return if (count == 0) 0 else sum / count
}

@JvmOverloads
inline fun <T> Iterable<T>.averageDouble(selector: (T) -> Double = { it.toDouble() }): Double {
    var sum = 0.0
    var count = 0
    for (t in this) {
        sum += selector(t)
        count++
    }
    return if (count == 0) 0.0 else sum / count
}

@JvmOverloads
inline fun <T> Iterable<T>.averageBigInteger(selector: (T) -> BigInteger = { it.toBigInteger() }): BigInteger {
    var sum = BigInteger.ZERO
    var count = 0
    for (t in this) {
        sum += selector(t)
        count++
    }
    return if (count == 0) BigInteger.ZERO else sum / count.toBigInteger()
}

@JvmOverloads
inline fun <T> Iterable<T>.averageBigDecimal(selector: (T) -> BigDecimal = { it.toBigDecimal() }): BigDecimal {
    var sum = BigDecimal.ZERO
    var count = 0
    for (t in this) {
        sum += selector(t)
        count++
    }
    return if (count == 0) BigDecimal.ZERO else sum / count.toBigDecimal()
}

fun <T, C : MutableCollection<in T>> Iterable<T>.toCollection(destination: C): C {
    return this.toCollectionKt(destination)
}

fun <T> Iterable<T>.toSet(): Set<T> {
    return this.toSetKt()
}

fun <T> Iterable<T>.toMutableSet(): MutableSet<T> {
    return this.toMutableSetKt()
}

fun <T> Iterable<T>.toHashSet(): HashSet<T> {
    return this.toHashSetKt()
}

fun <T> Iterable<T>.toLinkedHashSet(): LinkedHashSet<T> {
    return if (this is Collection<T>) toCollection(LinkedHashSet(size)) else toCollection(LinkedHashSet())
}

fun <T> Iterable<T>.toTreeSet(): TreeSet<T> {
    return this.toCollection(TreeSet())
}

@JvmOverloads
fun <T> Iterable<T>.toSortedSet(comparator: Comparator<in T> = castSelfComparableComparator()): SortedSet<T> {
    return this.toSortedSetKt(comparator)
}

fun <T> Iterable<T>.toList(): List<T> {
    return this.toListKt()
}

fun <T> Iterable<T>.toMutableList(): MutableList<T> {
    return this.toMutableListKt()
}

fun <T> Iterable<T>.toArrayList(): ArrayList<T> {
    return if (this is Collection<T>) toCollection(ArrayList(size)) else toCollection(ArrayList())
}

fun <T> Iterable<T>.toLinkedList(): LinkedList<T> {
    return this.toCollection(LinkedList())
}

fun <T> Iterable<T>.asToCollection(): Collection<T> {
    return if (this is Collection<T>) this else this.toSet()
}

fun <T> Iterable<T>.asToMutableCollection(): MutableCollection<T> {
    return if (this is MutableCollection<T>) this else this.toLinkedHashSet()
}

fun <T> Iterable<T>.asToSet(): Set<T> {
    return if (this is Set<T>) this else this.toSet()
}

fun <T> Iterable<T>.asToMutableSet(): MutableSet<T> {
    return if (this is MutableSet<T>) this else this.toLinkedHashSet()
}

@JvmOverloads
fun <T> Iterable<T>.asToSortedSet(comparator: Comparator<in T> = castSelfComparableComparator()): SortedSet<T> {
    return if (this is SortedSet<T>) this else this.toSortedSet(comparator)
}

fun <T> Iterable<T>.asToList(): List<T> {
    return if (this is List<T>) this else this.toList()
}

fun <T> Iterable<T>.asToMutableList(): MutableList<T> {
    return if (this is MutableList<T>) this else this.toMutableList()
}

@JvmOverloads
fun <T> Iterable<T>.toStream(parallel: Boolean = false): Stream<T> {
    return StreamSupport.stream(this.spliterator(), parallel)
}

fun <T> Iterable<T>.toSequence(): Sequence<T> {
    return this.asSequenceKt()
}

fun <T> Iterable<T>.toArray(): Array<Any?> {
    val list = this.asToList()
    return JavaCollect.toArray(list)
}

fun <T> Iterable<T>.toArray(generator: (size: Int) -> Array<T>): Array<T> {
    val list = this.asToList()
    return JavaCollect.toArray(list, generator(list.size))
}

fun <T> Iterable<T>.toArray(componentType: Class<*>): Array<T> {
    val list = this.asToList()
    val array: Array<T> = componentType.componentTypeToArray(0)
    return JavaCollect.toArray(list, array)
}

fun <T> Iterable<T>.toAnyArray(componentType: Class<*>): Any {
    return when (componentType) {
        Boolean::class.javaPrimitiveType -> this.toBooleanArray()
        Byte::class.javaPrimitiveType -> this.toByteArray()
        Short::class.javaPrimitiveType -> this.toShortArray()
        Char::class.javaPrimitiveType -> this.toCharArray()
        Int::class.javaPrimitiveType -> this.toIntArray()
        Long::class.javaPrimitiveType -> this.toLongArray()
        Float::class.javaPrimitiveType -> this.toFloatArray()
        Double::class.javaPrimitiveType -> this.toDoubleArray()
        else -> this.toArray(componentType)
    }
}

inline fun <reified T> Iterable<T>.toTypedArray(): Array<T> {
    val list = this.asToList()
    return list.toTypedArrayKt()
}

@JvmOverloads
inline fun <T> Iterable<T>.toBooleanArray(selector: (T) -> Boolean = { it.toBoolean() }): BooleanArray {
    val list = this.asToList()
    val result = BooleanArray(list.size)
    list.forEachIndexed { i, t -> result[i] = selector(t) }
    return result
}

@JvmOverloads
inline fun <T> Iterable<T>.toByteArray(selector: (T) -> Byte = { it.toByte() }): ByteArray {
    val list = this.asToList()
    val result = ByteArray(list.size)
    list.forEachIndexed { i, t -> result[i] = selector(t) }
    return result
}

@JvmOverloads
inline fun <T> Iterable<T>.toShortArray(selector: (T) -> Short = { it.toShort() }): ShortArray {
    val list = this.asToList()
    val result = ShortArray(list.size)
    list.forEachIndexed { i, t -> result[i] = selector(t) }
    return result
}

@JvmOverloads
inline fun <T> Iterable<T>.toCharArray(selector: (T) -> Char = { it.toChar() }): CharArray {
    val list = this.asToList()
    val result = CharArray(list.size)
    list.forEachIndexed { i, t -> result[i] = selector(t) }
    return result
}

@JvmOverloads
inline fun <T> Iterable<T>.toIntArray(selector: (T) -> Int = { it.toInt() }): IntArray {
    val list = this.asToList()
    val result = IntArray(list.size)
    list.forEachIndexed { i, t -> result[i] = selector(t) }
    return result
}

@JvmOverloads
inline fun <T> Iterable<T>.toLongArray(selector: (T) -> Long = { it.toLong() }): LongArray {
    val list = this.asToList()
    val result = LongArray(list.size)
    list.forEachIndexed { i, t -> result[i] = selector(t) }
    return result
}

@JvmOverloads
inline fun <T> Iterable<T>.toFloatArray(selector: (T) -> Float = { it.toFloat() }): FloatArray {
    val list = this.asToList()
    val result = FloatArray(list.size)
    list.forEachIndexed { i, t -> result[i] = selector(t) }
    return result
}

@JvmOverloads
inline fun <T> Iterable<T>.toDoubleArray(selector: (T) -> Double = { it.toDouble() }): DoubleArray {
    val list = this.asToList()
    val result = DoubleArray(list.size)
    list.forEachIndexed { i, t -> result[i] = selector(t) }
    return result
}

fun <T> Iterable<T>.plus(element: T): List<T> {
    return this.plusKt(element)
}

fun <T> Iterable<T>.plus(elements: Array<out T>): List<T> {
    return this.plusKt(elements)
}

fun <T> Iterable<T>.plus(elements: Iterable<T>): List<T> {
    return this.plusKt(elements)
}

fun <T> Iterable<T>.plus(elements: Sequence<T>): List<T> {
    return this.plusKt(elements)
}

fun <T> Iterable<T>.minus(element: T): List<T> {
    return this.minusKt(element)
}

fun <T> Iterable<T>.minus(elements: Array<out T>): List<T> {
    return this.minusKt(elements)
}

fun <T> Iterable<T>.minus(elements: Iterable<T>): List<T> {
    return this.minusKt(elements)
}

fun <T> Iterable<T>.minus(elements: Sequence<T>): List<T> {
    return this.minusKt(elements)
}

fun <T> Iterable<T>.plusBefore(index: Int, element: T): List<T> {
    return this.plusBefore(index, listOf(element))
}

fun <T> Iterable<T>.plusBefore(index: Int, elements: Array<out T>): List<T> {
    return this.plusBefore(index, elements.toListKt())
}

fun <T> Iterable<T>.plusBefore(index: Int, elements: Iterable<T>): List<T> {
    if (index == 0) {
        return elements.plusKt(this)
    }
    val list = this.toList()
    val front = list.subList(0, index)
    val back = list.subList(index, list.size)
    return front.plusKt(elements).plusKt(back)
}

fun <T> Iterable<T>.plusBefore(index: Int, elements: Sequence<T>): List<T> {
    return this.plusBefore(index, elements.toList())
}

fun <T> Iterable<T>.plusAfter(index: Int, element: T): List<T> {
    return this.plusAfter(index, listOf(element))
}

fun <T> Iterable<T>.plusAfter(index: Int, elements: Array<out T>): List<T> {
    return this.plusAfter(index, elements.toListKt())
}

fun <T> Iterable<T>.plusAfter(index: Int, elements: Iterable<T>): List<T> {
    val list = this.toList()
    if (index == list.size - 1) {
        return list.plusKt(this)
    }
    val front = list.subList(0, index + 1)
    val back = list.subList(index + 1, list.size)
    return front.plusKt(elements).plusKt(back)
}

fun <T> Iterable<T>.plusAfter(index: Int, elements: Sequence<T>): List<T> {
    return this.plusAfter(index, elements.toList())
}

@JvmOverloads
fun <T> Iterable<T>.minusAt(index: Int, count: Int = 1): List<T> {
    val list = this.toList()
    if (index == 0) {
        return if (count < list.size) list.subList(count, list.size) else emptyList()
    }
    val front = list.subList(0, index)
    if (count >= list.size - index) {
        return front
    }
    return front.plusKt(list.subList(index + count, list.size))
}

fun <T> MutableIterable<T>.remove(element: T): Boolean {
    val iterator = this.iterator()
    while (iterator.hasNext()) {
        val next = iterator.next()
        if (next == element) {
            iterator.remove()
            return true
        }
    }
    return false
}

fun <T> MutableIterable<T>.remove(n: Int): Boolean {
    val iterator = this.iterator()
    var success = true
    for (i in 1..n) {
        if (iterator.hasNext()) {
            iterator.next()
            iterator.remove()
        } else {
            success = false
            break
        }
    }
    return success
}

fun <T> MutableIterable<T>.removeWhile(predicate: (T) -> Boolean): Boolean {
    return this.removeAllKt(predicate)
}

fun <T> MutableIterable<T>.removeAll(): Boolean {
    return this.removeAllKt { true }
}

fun <T> MutableIterable<T>.retainAll(predicate: (T) -> Boolean): Boolean {
    return this.retainAllKt(predicate)
}

// Others

@JvmOverloads
fun <T> Iterable<T>.joinToString(
    separator: CharSequence = ", ",
    transform: ((T) -> CharSequence)? = null
): String {
    return this.joinToStringKt(separator = separator, transform = transform)
}

fun <T> Iterable<T>.joinToString(
    separator: CharSequence = ", ",
    limit: Int = -1,
    truncated: CharSequence = "...",
    transform: ((T) -> CharSequence)? = null
): String {
    return this.joinToStringKt(
        separator = separator,
        limit = limit,
        truncated = truncated,
        transform = transform,
    )
}

fun <T> Iterable<T>.joinToString(
    separator: CharSequence = ", ",
    prefix: CharSequence = "",
    postfix: CharSequence = "",
    limit: Int = -1,
    truncated: CharSequence = "...",
    transform: ((T) -> CharSequence)? = null
): String {
    return this.joinToStringKt(separator, prefix, postfix, limit, truncated, transform)
}

@JvmOverloads
fun <T, A : Appendable> Iterable<T>.joinTo(
    buffer: A,
    separator: CharSequence = ", ",
    transform: ((T) -> CharSequence)? = null
): A {
    return this.joinToKt(buffer = buffer, separator = separator, transform = transform)
}

fun <T, A : Appendable> Iterable<T>.joinTo(
    buffer: A,
    separator: CharSequence = ", ",
    limit: Int = -1,
    truncated: CharSequence = "...",
    transform: ((T) -> CharSequence)? = null
): A {
    return this.joinToKt(
        buffer = buffer,
        separator = separator,
        limit = limit,
        truncated = truncated,
        transform = transform
    )
}

fun <T, A : Appendable> Iterable<T>.joinTo(
    buffer: A,
    separator: CharSequence = ", ",
    prefix: CharSequence = "",
    postfix: CharSequence = "",
    limit: Int = -1,
    truncated: CharSequence = "...",
    transform: ((T) -> CharSequence)? = null
): A {
    return this.joinToKt(buffer, separator, prefix, postfix, limit, truncated, transform)
}

fun <T> Any.anyAsIterable(): Iterable<T> {
    return this.anyAsIterableOrNull()
        ?: throw IllegalArgumentException("Cannot from any to Iterable: $this.")
}

fun <T> Any.anyAsMutableIterable(): MutableIterable<T> {
    return this.anyAsMutableIterableOrNull()
        ?: throw IllegalArgumentException("Cannot from any to MutableIterable: $this.")
}

fun <T> Any.anyAsIterableOrNull(): Iterable<T>? {
    if (this is Iterable<*>) {
        return this.asAny()
    }
    if (this.javaClass.isArray) {
        return this.arrayAsListOrNull()
    }
    return null
}

fun <T> Any.anyAsMutableIterableOrNull(): MutableIterable<T>? {
    if (this is MutableIterable<*>) {
        return this.asAny()
    }
    if (this.javaClass.isArray) {
        return this.arrayAsListOrNull()
    }
    return null
}

fun <T> Collection<T>.containsAll(elements: Array<out T>): Boolean {
    return this.containsAll(elements.toSetKt())
}

fun <T> Collection<T>.containsAll(elements: Iterable<T>): Boolean {
    return this.containsAll(elements.asToSet())
}

fun <T> Collection<T>.count(): Int {
    return this.countKt()
}

fun <T> Collection<T>.toMutableList(): MutableList<T> {
    return this.toMutableListKt()
}

fun <T> Collection<T>.plus(element: T): List<T> {
    return this.plusKt(element)
}

fun <T> Collection<T>.plus(elements: Array<out T>): List<T> {
    return this.plusKt(elements)
}

fun <T> Collection<T>.plus(elements: Iterable<T>): List<T> {
    return this.plusKt(elements)
}

fun <T> Collection<T>.plus(elements: Sequence<T>): List<T> {
    return this.plusKt(elements)
}

fun <T> MutableCollection<T>.addAll(elements: Array<out T>): Boolean {
    return this.addAllKt(elements)
}

fun <T> MutableCollection<T>.addAll(elements: Iterable<T>): Boolean {
    return this.addAllKt(elements)
}

fun <T> MutableCollection<T>.addAll(elements: Sequence<T>): Boolean {
    return this.addAllKt(elements)
}

fun <T> MutableCollection<T>.removeAll(elements: Array<out T>): Boolean {
    return this.removeAllKt(elements)
}

fun <T> MutableCollection<T>.removeAll(elements: Iterable<T>): Boolean {
    return this.removeAllKt(elements)
}

fun <T> MutableCollection<T>.removeAll(elements: Sequence<T>): Boolean {
    return this.removeAllKt(elements)
}

fun <T> MutableCollection<T>.retainAll(elements: Array<out T>): Boolean {
    return this.retainAllKt(elements)
}

fun <T> MutableCollection<T>.retainAll(elements: Iterable<T>): Boolean {
    return this.retainAllKt(elements)
}

fun <T> MutableCollection<T>.retainAll(elements: Sequence<T>): Boolean {
    return this.retainAllKt(elements)
}

// Others

fun <T> Any.anyAsCollection(): Collection<T> {
    return this.anyAsCollectionOrNull()
        ?: throw IllegalArgumentException("Cannot from any to Collection: $this.")
}

fun <T> Any.anyAsMutableCollection(): MutableCollection<T> {
    return this.anyAsMutableCollectionOrNull()
        ?: throw IllegalArgumentException("Cannot from any to MutableCollection: $this.")
}

fun <T> Any.anyAsCollectionOrNull(): Collection<T>? {
    if (this is Collection<*>) {
        return this.asAny()
    }
    if (this.javaClass.isArray) {
        return this.arrayAsListOrNull()
    }
    return null
}

fun <T> Any.anyAsMutableCollectionOrNull(): MutableCollection<T>? {
    if (this is MutableCollection<*>) {
        return this.asAny()
    }
    if (this.javaClass.isArray) {
        return this.arrayAsListOrNull()
    }
    return null
}

fun <T> List<T>.first(): T {
    return this.firstKt()
}

fun <T> List<T>.firstOrNull(): T? {
    return this.firstOrNullKt()
}

fun <T> List<T>.last(): T {
    return this.lastKt()
}

inline fun <T> List<T>.last(predicate: (T) -> Boolean): T {
    return this.lastKt(predicate)
}

fun <T> List<T>.lastOrNull(): T? {
    return this.lastOrNullKt()
}

inline fun <T> List<T>.lastOrNull(predicate: (T) -> Boolean): T? {
    return this.lastOrNullKt(predicate)
}

fun <T> List<T>.elementAt(index: Int): T {
    return this.elementAtKt(index)
}

inline fun <T> List<T>.elementAtOrElse(index: Int, defaultValue: (Int) -> T): T {
    return this.elementAtOrElseKt(index, defaultValue)
}

fun <T> List<T>.elementAtOrNull(index: Int): T? {
    return this.elementAtOrNullKt(index)
}

inline fun <T> List<T>.findLast(predicate: (T) -> Boolean): T? {
    return this.findLastKt(predicate)
}

fun <T> List<T>.indexOf(element: T): Int {
    return this.indexOfKt(element)
}

inline fun <T> List<T>.indexOf(predicate: (T) -> Boolean): Int {
    return this.indexOfFirstKt(predicate)
}

fun <T> List<T>.lastIndexOf(element: T): Int {
    return this.lastIndexOfKt(element)
}

inline fun <T> List<T>.lastIndexOf(predicate: (T) -> Boolean): Int {
    return this.indexOfLastKt(predicate)
}

fun <T> List<T>.takeLast(n: Int): List<T> {
    return this.takeLastKt(n)
}

inline fun <T> List<T>.takeLastWhile(predicate: (T) -> Boolean): List<T> {
    return this.takeLastWhileKt(predicate)
}

fun <T> List<T>.dropLast(n: Int): List<T> {
    return this.dropLastKt(n)
}

inline fun <T> List<T>.dropLastWhile(predicate: (T) -> Boolean): List<T> {
    return this.dropLastWhileKt(predicate)
}

inline fun <S, T : S> List<T>.reduceRight(operation: (T, S) -> S): S {
    return this.reduceRightKt(operation)
}

inline fun <S, T : S> List<T>.reduceRightIndexed(operation: (Int, T, S) -> S): S {
    return this.reduceRightIndexedKt(operation)
}

inline fun <S, T : S> List<T>.reduceRightOrNull(operation: (T, S) -> S): S? {
    return this.reduceRightOrNullKt(operation)
}

inline fun <S, T : S> List<T>.reduceRightIndexedOrNull(operation: (Int, T, S) -> S): S? {
    return this.reduceRightIndexedOrNullKt(operation)
}

inline fun <T, R> List<T>.reduceRight(initial: R, operation: (T, R) -> R): R {
    return this.foldRightKt(initial, operation)
}

inline fun <T, R> List<T>.reduceRightIndexed(initial: R, operation: (Int, T, R) -> R): R {
    return this.foldRightIndexedKt(initial, operation)
}

fun <T> List<T>.slice(indices: IntArray): List<T> {
    return this.sliceKt(indices.asIterable())
}

fun <T> List<T>.slice(indices: Iterable<Int>): List<T> {
    return this.sliceKt(indices)
}

fun <T> List<T>.slice(indices: IntRange): List<T> {
    return this.sliceKt(indices)
}

@JvmOverloads
fun <T> List<T>.binarySearch(element: T, comparator: Comparator<in T> = castSelfComparableComparator()): Int {
    return this.binarySearchKt(element, comparator)
}

fun <T> MutableList<T>.reverse() {
    this.reverseKt()
}

@JvmOverloads
fun <T> MutableList<T>.sort(comparator: Comparator<in T> = castSelfComparableComparator()) {
    this.sortWithKt(comparator)
}

fun <T> MutableList<T>.shuffle() {
    this.shuffleKt()
}

fun <T> MutableList<T>.shuffle(random: Random) {
    this.shuffleKt(random)
}

fun <T> MutableList<T>.removeAll(predicate: (T) -> Boolean): Boolean {
    return this.removeAllKt(predicate)
}

fun <T> MutableList<T>.removeFirst(): T {
    return this.removeFirstKt()
}

fun <T> MutableList<T>.removeFirstOrNull(): T? {
    return this.removeFirstOrNullKt()
}

fun <T> MutableList<T>.removeLast(): T {
    return this.removeLastKt()
}

fun <T> MutableList<T>.removeLastOrNull(): T? {
    return this.removeLastOrNullKt()
}

fun <T> MutableList<T>.retainAll(predicate: (T) -> Boolean): Boolean {
    return this.retainAllKt(predicate)
}

// Others

fun <T> Any.anyAsList(): List<T> {
    return this.anyAsListOrNull()
        ?: throw IllegalArgumentException("Cannot from any to List: $this.")
}

fun <T> Any.anyAsMutableList(): MutableList<T> {
    return this.anyAsMutableListOrNull()
        ?: throw IllegalArgumentException("Cannot from any to MutableList: $this.")
}

fun <T> Any.anyAsListOrNull(): List<T>? {
    if (this is List<*>) {
        return this.asAny()
    }
    if (this.javaClass.isArray) {
        return this.arrayAsListOrNull()
    }
    return null
}

fun <T> Any.anyAsMutableListOrNull(): MutableList<T>? {
    if (this is MutableList<*>) {
        return this.asAny()
    }
    if (this.javaClass.isArray) {
        return this.arrayAsListOrNull()
    }
    return null
}

fun <T> Set<T>.plus(element: T): Set<T> {
    return this.plusKt(element)
}

fun <T> Set<T>.plus(elements: Array<out T>): Set<T> {
    return this.plusKt(elements)
}

fun <T> Set<T>.plus(elements: Iterable<T>): Set<T> {
    return this.plusKt(elements)
}

fun <T> Set<T>.plus(elements: Sequence<T>): Set<T> {
    return this.plusKt(elements)
}

fun <T> Set<T>.minus(element: T): Set<T> {
    return this.minusKt(element)
}

fun <T> Set<T>.minus(elements: Array<out T>): Set<T> {
    return this.minusKt(elements)
}

fun <T> Set<T>.minus(elements: Iterable<T>): Set<T> {
    return this.minusKt(elements)
}

fun <T> Set<T>.minus(elements: Sequence<T>): Set<T> {
    return this.minusKt(elements)
}

fun <K, V> Map<K, V>.containsKeys(keys: Array<out K>): Boolean {
    return this.keys.containsAll(keys.toSetKt())
}

fun <K, V> Map<K, V>.containsKeys(keys: Iterable<K>): Boolean {
    return this.keys.containsAll(keys.toSetKt())
}

fun <K, V> Map<K, V>.containsKeys(keys: Collection<K>): Boolean {
    return this.keys.containsAll(keys)
}

fun <K, V> Map<K, V>.containsValues(values: Array<out V>): Boolean {
    return this.values.toSetKt().containsAll(values.toSetKt())
}

fun <K, V> Map<K, V>.containsValues(values: Iterable<V>): Boolean {
    return this.values.toSetKt().containsAll(values.toSetKt())
}

fun <K, V> Map<K, V>.containsValues(values: Collection<V>): Boolean {
    return this.values.toSetKt().containsAll(values)
}

inline fun <K, V> Map<K, V>.filter(predicate: (Map.Entry<K, V>) -> Boolean): Map<K, V> {
    return this.filterKt(predicate)
}

inline fun <K, V, M : MutableMap<in K, in V>> Map<K, V>.filterTo(
    destination: M,
    predicate: (Map.Entry<K, V>) -> Boolean
): M {
    return this.filterToKt(destination, predicate)
}

inline fun <K, V, R> Map<K, V>.map(transform: (Map.Entry<K, V>) -> R): List<R> {
    return this.mapKt(transform)
}

inline fun <K, V, RK, RV> Map<K, V>.map(
    crossinline keySelector: (K) -> RK,
    crossinline valueTransform: (V) -> RV
): Map<RK, RV> {
    return this.mapTo(LinkedHashMap(), keySelector, valueTransform)
}

inline fun <K, V, RK, RV> Map<K, V>.map(transform: (K, V) -> Pair<RK, RV>): Map<RK, RV> {
    return this.mapTo(LinkedHashMap(), transform)
}

inline fun <K, V, R, C : MutableCollection<in R>> Map<K, V>.mapTo(
    destination: C,
    transform: (Map.Entry<K, V>) -> R
): C {
    return this.mapToKt(destination, transform)
}

inline fun <K, V, RK, RV, C : MutableMap<in RK, in RV>> Map<K, V>.mapTo(
    destination: C,
    crossinline keySelector: (K) -> RK,
    crossinline valueTransform: (V) -> RV
): C {
    this.forEach { (k, v) ->
        val rk = keySelector(k)
        val rv = valueTransform(v)
        destination.put(rk, rv)
    }
    return destination
}

inline fun <K, V, RK, RV, C : MutableMap<in RK, in RV>> Map<K, V>.mapTo(
    destination: C,
    transform: (K, V) -> Pair<RK, RV>
): C {
    this.forEach { (k, v) ->
        val pair = transform(k, v)
        destination.put(pair.first, pair.second)
    }
    return destination
}

inline fun <K, V, R> Map<K, V>.flatMap(transform: (Map.Entry<K, V>) -> Iterable<R>): List<R> {
    return this.flatMapKt(transform)
}

inline fun <K, V, R, C : MutableCollection<in R>> Map<K, V>.flatMapTo(
    destination: C,
    transform: (Map.Entry<K, V>) -> Iterable<R>
): C {
    return this.flatMapToKt(destination, transform)
}

fun <K, V> Map<K, V>.plus(other: Map<out K, V>): Map<K, V> {
    return this.plusKt(other)
}

fun <K, V> Map<K, V>.minus(key: K): Map<K, V> {
    return this.minusKt(key)
}

fun <K, V> Map<K, V>.minus(keys: Array<out K>): Map<K, V> {
    return this.minusKt(keys)
}

fun <K, V> Map<K, V>.minus(keys: Iterable<K>): Map<K, V> {
    return this.minusKt(keys)
}

fun <K, V> MutableMap<K, V>.removeAll(keys: Iterable<K>) {
    for (key in keys) {
        this.remove(key)
    }
}

fun <T> Sequence<T>.contains(element: T): Boolean {
    return this.containsKt(element)
}

fun <T> Sequence<T>.containsAll(elements: Array<out T>): Boolean {
    return this.containsAll(elements.toSet())
}

fun <T> Sequence<T>.containsAll(elements: Iterable<T>): Boolean {
    return this.containsAll(elements.toSet())
}

fun <T> Sequence<T>.containsAll(elements: Collection<T>): Boolean {
    return this.toSet().containsAll(elements)
}

fun <T> Sequence<T>.count(): Int {
    return this.countKt()
}

inline fun <T> Sequence<T>.count(predicate: (T) -> Boolean): Int {
    return this.countKt(predicate)
}

fun <T> Sequence<T>.isEmpty(): Boolean {
    var hasElement = false
    for (t in this) {
        hasElement = true
        break
    }
    return !hasElement
}

fun <T> Sequence<T>.isNotEmpty(): Boolean {
    return !this.isEmpty()
}

fun <T> Sequence<T>.any(): Boolean {
    return this.anyKt()
}

inline fun <T> Sequence<T>.any(predicate: (T) -> Boolean): Boolean {
    return this.anyKt(predicate)
}

fun <T> Sequence<T>.none(): Boolean {
    return this.noneKt()
}

inline fun <T> Sequence<T>.none(predicate: (T) -> Boolean): Boolean {
    return this.noneKt(predicate)
}

inline fun <T> Sequence<T>.all(predicate: (T) -> Boolean): Boolean {
    return this.allKt(predicate)
}

fun <T> Sequence<T>.first(): T {
    return this.firstKt()
}

inline fun <T> Sequence<T>.first(predicate: (T) -> Boolean): T {
    return this.firstKt(predicate)
}

fun <T> Sequence<T>.firstOrNull(): T? {
    return this.firstOrNullKt()
}

inline fun <T> Sequence<T>.firstOrNull(predicate: (T) -> Boolean): T? {
    return this.firstOrNullKt(predicate)
}

fun <T> Sequence<T>.last(): T {
    return this.lastKt()
}

inline fun <T> Sequence<T>.last(predicate: (T) -> Boolean): T {
    return this.lastKt(predicate)
}

fun <T> Sequence<T>.lastOrNull(): T? {
    return this.lastOrNullKt()
}

inline fun <T> Sequence<T>.lastOrNull(predicate: (T) -> Boolean): T? {
    return this.lastOrNullKt(predicate)
}

fun <T> Sequence<T>.elementAt(index: Int): T {
    return this.elementAtKt(index)
}

fun <T> Sequence<T>.elementAtOrElse(index: Int, defaultValue: (index: Int) -> T): T {
    return this.elementAtOrElseKt(index, defaultValue)
}

fun <T> Sequence<T>.elementAtOrNull(index: Int): T? {
    return this.elementAtOrNullKt(index)
}

inline fun <T> Sequence<T>.find(predicate: (T) -> Boolean): T? {
    return this.findKt(predicate)
}

inline fun <T> Sequence<T>.findLast(predicate: (T) -> Boolean): T? {
    return this.firstKt(predicate)
}

fun <T> Sequence<T>.indexOf(element: T): Int {
    return this.indexOfKt(element)
}

inline fun <T> Sequence<T>.indexOf(predicate: (T) -> Boolean): Int {
    return this.indexOfFirstKt(predicate)
}

fun <T> Sequence<T>.lastIndexOf(element: T): Int {
    return this.lastIndexOfKt(element)
}

inline fun <T> Sequence<T>.lastIndexOf(predicate: (T) -> Boolean): Int {
    return this.indexOfLastKt(predicate)
}

fun <T> Sequence<T>.take(n: Int): Sequence<T> {
    return this.takeKt(n)
}

fun <T> Sequence<T>.takeWhile(predicate: (T) -> Boolean): Sequence<T> {
    return this.takeWhileKt(predicate)
}

fun <T, C : MutableCollection<in T>> Sequence<T>.takeTo(n: Int, destination: C): C {
    destination.addAll(this.take(n))
    return destination
}

fun <T, C : MutableCollection<in T>> Sequence<T>.takeWhileTo(
    destination: C,
    predicate: (T) -> Boolean
): C {
    destination.addAll(this.takeWhile(predicate))
    return destination
}

fun <T, C : MutableCollection<in T>> Sequence<T>.takeAllTo(destination: C): C {
    destination.addAll(this)
    return destination
}

fun <T> Sequence<T>.drop(n: Int): Sequence<T> {
    return this.dropKt(n)
}

fun <T> Sequence<T>.dropWhile(predicate: (T) -> Boolean): Sequence<T> {
    return this.dropWhileKt(predicate)
}

fun <T, C : MutableCollection<in T>> Sequence<T>.dropTo(n: Int, destination: C): C {
    destination.addAll(this.drop(n))
    return destination
}

fun <T, C : MutableCollection<in T>> Sequence<T>.dropWhileTo(
    destination: C,
    predicate: (T) -> Boolean
): C {
    destination.addAll(this.dropWhile(predicate))
    return destination
}

inline fun <T> Sequence<T>.forEachIndexed(action: (index: Int, T) -> Unit) {
    return this.forEachIndexedKt(action)
}

fun <T> Sequence<T>.filter(predicate: (T) -> Boolean): Sequence<T> {
    return this.filterKt(predicate)
}

fun <T> Sequence<T>.filterIndexed(predicate: (index: Int, T) -> Boolean): Sequence<T> {
    return this.filterIndexedKt(predicate)
}

fun <T> Sequence<T>.filterNotNull(): Sequence<T> {
    return this.filterNotNullKt()
}

inline fun <T, C : MutableCollection<in T>> Sequence<T>.filterTo(destination: C, predicate: (T) -> Boolean): C {
    return this.filterToKt(destination, predicate)
}

inline fun <T, C : MutableCollection<in T>> Sequence<T>.filterIndexedTo(
    destination: C,
    predicate: (index: Int, T) -> Boolean
): C {
    return this.filterIndexedToKt(destination, predicate)
}

fun <C : MutableCollection<in T>, T> Sequence<T>.filterNotNullTo(destination: C): C {
    return this.filterTo(destination, { it !== null })
}

fun <T, R> Sequence<T>.map(transform: (T) -> R): Sequence<R> {
    return this.mapKt(transform)
}

fun <T, R> Sequence<T>.mapIndexed(transform: (index: Int, T) -> R): Sequence<R> {
    return this.mapIndexedKt(transform)
}

fun <T, R : Any> Sequence<T>.mapNotNull(transform: (T) -> R?): Sequence<R> {
    return this.mapNotNullKt(transform)
}

fun <T, R : Any> Sequence<T>.mapIndexedNotNull(transform: (index: Int, T) -> R?): Sequence<R> {
    return this.mapIndexedNotNullKt(transform)
}

inline fun <T, R, C : MutableCollection<in R>> Sequence<T>.mapTo(destination: C, transform: (T) -> R): C {
    return this.mapToKt(destination, transform)
}

inline fun <T, R, C : MutableCollection<in R>> Sequence<T>.mapIndexedTo(
    destination: C,
    transform: (index: Int, T) -> R
): C {
    return this.mapIndexedToKt(destination, transform)
}

inline fun <T, R : Any, C : MutableCollection<in R>> Sequence<T>.mapNotNullTo(
    destination: C,
    transform: (T) -> R?
): C {
    return this.mapNotNullToKt(destination, transform)
}

inline fun <T, R : Any, C : MutableCollection<in R>> Sequence<T>.mapIndexedNotNullTo(
    destination: C,
    transform: (index: Int, T) -> R?
): C {
    return this.mapIndexedNotNullToKt(destination, transform)
}

fun <T, R> Sequence<T>.flatMap(transform: (T) -> Iterable<R>): Sequence<R> {
    return this.flatMapKt(transform)
}

fun <T, R> Sequence<T>.flatMapIndexed(transform: (index: Int, T) -> Iterable<R>): Sequence<R> {
    return this.flatMapIndexedKt(transform)
}

inline fun <T, R, C : MutableCollection<in R>> Sequence<T>.flatMapTo(
    destination: C,
    transform: (T) -> Iterable<R>
): C {
    return this.flatMapToKt(destination, transform)
}

inline fun <T, R, C : MutableCollection<in R>> Sequence<T>.flatMapIndexedTo(
    destination: C,
    transform: (index: Int, T) -> Iterable<R>
): C {
    return this.flatMapIndexedToKt(destination, transform)
}

inline fun <T, K, V> Sequence<T>.associate(
    keySelector: (T) -> K,
    valueTransform: (T) -> V
): Map<K, V> {
    return this.associateByKt(keySelector, valueTransform)
}

inline fun <T, K, V> Sequence<T>.associate(transform: (T) -> Pair<K, V>): Map<K, V> {
    return this.associateKt(transform)
}

inline fun <T, K> Sequence<T>.associateKey(keySelector: (T) -> K): Map<K, T> {
    return this.associateByKt(keySelector)
}

inline fun <T, V> Sequence<T>.associateValue(valueSelector: (T) -> V): Map<T, V> {
    return this.associateWithKt(valueSelector)
}

inline fun <T, K, V> Sequence<T>.associatePair(
    keySelector: (T) -> K,
    valueTransform: (T) -> V
): Map<K, V> {
    return this.associatePairTo(LinkedHashMap(), keySelector, valueTransform)
}

inline fun <T, K, V> Sequence<T>.associatePair(transform: (T, T) -> Pair<K, V>): Map<K, V> {
    return this.associatePairTo(LinkedHashMap(), transform)
}

inline fun <T, K, V> Sequence<T>.associatePair(
    complement: T,
    keySelector: (T) -> K,
    valueTransform: (T) -> V
): Map<K, V> {
    return this.associatePairTo(complement, LinkedHashMap(), keySelector, valueTransform)
}

inline fun <T, K, V> Sequence<T>.associatePair(
    complement: T,
    transform: (T, T) -> Pair<K, V>
): Map<K, V> {
    return this.associatePairTo(complement, LinkedHashMap(), transform)
}

inline fun <T, K, V, M : MutableMap<in K, in V>> Sequence<T>.associateTo(
    destination: M,
    keySelector: (T) -> K,
    valueTransform: (T) -> V
): M {
    return this.associateByToKt(destination, keySelector, valueTransform)
}

inline fun <T, K, V, M : MutableMap<in K, in V>> Sequence<T>.associateTo(
    destination: M,
    transform: (T) -> Pair<K, V>
): M {
    return this.associateToKt(destination, transform)
}

inline fun <T, K, M : MutableMap<in K, in T>> Sequence<T>.associateKeyTo(
    destination: M,
    keySelector: (T) -> K
): M {
    return this.associateByToKt(destination, keySelector)
}

inline fun <T, V, M : MutableMap<in T, in V>> Sequence<T>.associateValueTo(
    destination: M,
    valueSelector: (T) -> V
): M {
    return this.associateWithToKt(destination, valueSelector)
}

inline fun <T, K, V, M : MutableMap<in K, in V>> Sequence<T>.associatePairTo(
    destination: M,
    keySelector: (T) -> K,
    valueTransform: (T) -> V
): M {
    val iterator = this.iterator()
    while (iterator.hasNext()) {
        val ik = iterator.next()
        if (iterator.hasNext()) {
            val iv = iterator.next()
            val k = keySelector(ik)
            val v = valueTransform(iv)
            destination.put(k, v)
        } else {
            break
        }
    }
    return destination
}

inline fun <T, K, V, M : MutableMap<in K, in V>> Sequence<T>.associatePairTo(
    destination: M,
    transform: (T, T) -> Pair<K, V>
): M {
    val iterator = this.iterator()
    while (iterator.hasNext()) {
        val ik = iterator.next()
        if (iterator.hasNext()) {
            val iv = iterator.next()
            val pair = transform(ik, iv)
            destination.put(pair.first, pair.second)
        } else {
            break
        }
    }
    return destination
}

inline fun <T, K, V, M : MutableMap<in K, in V>> Sequence<T>.associatePairTo(
    complement: T,
    destination: M,
    keySelector: (T) -> K,
    valueTransform: (T) -> V
): M {
    val iterator = this.iterator()
    while (iterator.hasNext()) {
        val ik = iterator.next()
        if (iterator.hasNext()) {
            val iv = iterator.next()
            val k = keySelector(ik)
            val v = valueTransform(iv)
            destination.put(k, v)
        } else {
            val k = keySelector(ik)
            val v = valueTransform(complement)
            destination.put(k, v)
            break
        }
    }
    return destination
}

inline fun <T, K, V, M : MutableMap<in K, in V>> Sequence<T>.associatePairTo(
    complement: T,
    destination: M,
    transform: (T, T) -> Pair<K, V>
): M {
    val iterator = this.iterator()
    while (iterator.hasNext()) {
        val ik = iterator.next()
        if (iterator.hasNext()) {
            val iv = iterator.next()
            val pair = transform(ik, iv)
            destination.put(pair.first, pair.second)
        } else {
            val pair = transform(ik, complement)
            destination.put(pair.first, pair.second)
            break
        }
    }
    return destination
}

inline fun <T, K> Sequence<T>.groupBy(keySelector: (T) -> K): Map<K, List<T>> {
    return this.groupByKt(keySelector)
}

inline fun <T, K, V> Sequence<T>.groupBy(
    keySelector: (T) -> K,
    valueTransform: (T) -> V
): Map<K, List<V>> {
    return this.groupByKt(keySelector, valueTransform)
}

inline fun <T, K, M : MutableMap<in K, MutableList<T>>> Sequence<T>.groupByTo(
    destination: M,
    keySelector: (T) -> K
): M {
    return this.groupByToKt(destination, keySelector)
}

inline fun <T, K, V, M : MutableMap<in K, MutableList<V>>> Sequence<T>.groupByTo(
    destination: M,
    keySelector: (T) -> K,
    valueTransform: (T) -> V
): M {
    return this.groupByToKt(destination, keySelector, valueTransform)
}

inline fun <S, T : S> Sequence<T>.reduce(operation: (S, T) -> S): S {
    return this.reduceKt(operation)
}

inline fun <S, T : S> Sequence<T>.reduceIndexed(operation: (index: Int, S, T) -> S): S {
    return this.reduceIndexedKt(operation)
}

inline fun <S, T : S> Sequence<T>.reduceOrNull(operation: (S, T) -> S): S? {
    return this.reduceOrNullKt(operation)
}

inline fun <S, T : S> Sequence<T>.reduceIndexedOrNull(operation: (index: Int, S, T) -> S): S? {
    return this.reduceIndexedOrNullKt(operation)
}

inline fun <T, R> Sequence<T>.reduce(initial: R, operation: (R, T) -> R): R {
    return this.foldKt(initial, operation)
}

inline fun <T, R> Sequence<T>.reduceIndexed(initial: R, operation: (index: Int, R, T) -> R): R {
    return this.foldIndexedKt(initial, operation)
}

fun <T, R, V> Sequence<T>.zip(other: Sequence<R>, transform: (T, R) -> V): Sequence<V> {
    return this.zipKt(other, transform)
}

fun <T, R> Sequence<T>.zipWithNext(transform: (T, T) -> R): Sequence<R> {
    return this.zipWithNextKt(transform)
}

fun <T> Sequence<T>.chunked(size: Int): Sequence<List<T>> {
    return this.chunkedKt(size)
}

fun <T, R> Sequence<T>.chunked(size: Int, transform: (List<T>) -> R): Sequence<R> {
    return this.chunkedKt(size, transform)
}

fun <T> Sequence<T>.windowed(size: Int, step: Int = 1, partialWindows: Boolean = false): Sequence<List<T>> {
    return this.windowedKt(size, step, partialWindows)
}

fun <T, R> Sequence<T>.windowed(
    size: Int,
    step: Int = 1,
    partialWindows: Boolean = false,
    transform: (List<T>) -> R
): Sequence<R> {
    return this.windowedKt(size, step, partialWindows, transform)
}

fun <T> Sequence<T>.distinct(): Sequence<T> {
    return this.distinctKt()
}

fun <T, K> Sequence<T>.distinct(selector: (T) -> K): Sequence<T> {
    return this.distinctByKt(selector)
}

@JvmOverloads
fun <T> Sequence<T>.sorted(comparator: Comparator<in T> = castSelfComparableComparator()): Sequence<T> {
    return this.sortedWithKt(comparator)
}

fun <T> Sequence<T>.shuffled(): Sequence<T> {
    return this.shuffledKt()
}

fun <T> Sequence<T>.shuffled(random: Random): Sequence<T> {
    return this.shuffledKt(random)
}

@JvmOverloads
fun <T> Sequence<T>.max(comparator: Comparator<in T> = castSelfComparableComparator()): T {
    return this.maxOrNull(comparator).notNull()
}

@JvmOverloads
fun <T> Sequence<T>.maxOrNull(comparator: Comparator<in T> = castSelfComparableComparator()): T? {
    return this.maxWithOrNullKt(comparator)
}

@JvmOverloads
fun <T> Sequence<T>.min(comparator: Comparator<in T> = castSelfComparableComparator()): T {
    return this.minOrNull(comparator).notNull()
}

@JvmOverloads
fun <T> Sequence<T>.minOrNull(comparator: Comparator<in T> = castSelfComparableComparator()): T? {
    return this.minWithOrNullKt(comparator)
}

@JvmOverloads
inline fun <T> Sequence<T>.sumInt(selector: (T) -> Int = { it.toInt() }): Int {
    return this.sumOfKt(selector)
}

@JvmOverloads
inline fun <T> Sequence<T>.sumLong(selector: (T) -> Long = { it.toLong() }): Long {
    return this.sumOfKt(selector)
}

@JvmOverloads
fun <T> Sequence<T>.sumDouble(selector: (T) -> Double = { it.toDouble() }): Double {
    return this.sumOfKt(selector)
}

@JvmOverloads
fun <T> Sequence<T>.sumBigInteger(selector: (T) -> BigInteger = { it.toBigInteger() }): BigInteger {
    return this.sumOfKt(selector)
}

@JvmOverloads
fun <T> Sequence<T>.sumBigDecimal(selector: (T) -> BigDecimal = { it.toBigDecimal() }): BigDecimal {
    return this.sumOfKt(selector)
}

@JvmOverloads
inline fun <T> Sequence<T>.averageInt(selector: (T) -> Int = { it.toInt() }): Int {
    var sum = 0
    var count = 0
    for (t in this) {
        sum += selector(t)
        count++
    }
    return if (count == 0) 0 else sum / count
}

@JvmOverloads
inline fun <T> Sequence<T>.averageLong(selector: (T) -> Long = { it.toLong() }): Long {
    var sum = 0L
    var count = 0
    for (t in this) {
        sum += selector(t)
        count++
    }
    return if (count == 0) 0 else sum / count
}

@JvmOverloads
inline fun <T> Sequence<T>.averageDouble(selector: (T) -> Double = { it.toDouble() }): Double {
    var sum = 0.0
    var count = 0
    for (t in this) {
        sum += selector(t)
        count++
    }
    return if (count == 0) 0.0 else sum / count
}

@JvmOverloads
inline fun <T> Sequence<T>.averageBigInteger(selector: (T) -> BigInteger = { it.toBigInteger() }): BigInteger {
    var sum = BigInteger.ZERO
    var count = 0
    for (t in this) {
        sum += selector(t)
        count++
    }
    return if (count == 0) BigInteger.ZERO else sum / count.toBigInteger()
}

@JvmOverloads
inline fun <T> Sequence<T>.averageBigDecimal(selector: (T) -> BigDecimal = { it.toBigDecimal() }): BigDecimal {
    var sum = BigDecimal.ZERO
    var count = 0
    for (t in this) {
        sum += selector(t)
        count++
    }
    return if (count == 0) BigDecimal.ZERO else sum / count.toBigDecimal()
}

fun <T, C : MutableCollection<in T>> Sequence<T>.toCollection(destination: C): C {
    return this.toCollectionKt(destination)
}

fun <T> Sequence<T>.toMutableCollection(): MutableCollection<T> {
    return this.toMutableSet()
}

fun <T> Sequence<T>.toSet(): Set<T> {
    return this.toSetKt()
}

fun <T> Sequence<T>.toMutableSet(): MutableSet<T> {
    return this.toMutableSetKt()
}

fun <T> Sequence<T>.toHashSet(): HashSet<T> {
    return this.toHashSetKt()
}

fun <T> Sequence<T>.toLinkedHashSet(): LinkedHashSet<T> {
    return this.takeAllTo(LinkedHashSet())
}

@JvmOverloads
fun <T> Sequence<T>.toSortedSet(comparator: Comparator<in T> = castSelfComparableComparator()): SortedSet<T> {
    return this.toSortedSetKt(comparator)
}

fun <T> Sequence<T>.toList(): List<T> {
    return this.toListKt()
}

fun <T> Sequence<T>.toMutableList(): MutableList<T> {
    return this.toMutableListKt()
}

fun <T> Sequence<T>.toArrayList(): ArrayList<T> {
    return this.takeAllTo(ArrayList())
}

fun <T> Sequence<T>.toLinkedList(): LinkedList<T> {
    return this.takeAllTo(LinkedList())
}

@JvmOverloads
fun <T> Sequence<T>.toStream(parallel: Boolean = false): Stream<T> {
    return if (parallel) this.asStream().parallel() else this.asStream()
}

fun <T> Sequence<T>.toArray(): Array<Any?> {
    val list = this.toList()
    return JavaCollect.toArray(list)
}

fun <T> Sequence<T>.toArray(generator: (size: Int) -> Array<T>): Array<T> {
    val list = this.toList()
    return JavaCollect.toArray(list, generator(list.size))
}

fun <T> Sequence<T>.toArray(componentType: Class<*>): Array<T> {
    val list = this.toList()
    val array: Array<T> = componentType.componentTypeToArray(0)
    return JavaCollect.toArray(list, array)
}

fun <T> Sequence<T>.toAnyArray(componentType: Class<*>): Any {
    return when (componentType) {
        Boolean::class.javaPrimitiveType -> this.toBooleanArray()
        Byte::class.javaPrimitiveType -> this.toByteArray()
        Short::class.javaPrimitiveType -> this.toShortArray()
        Char::class.javaPrimitiveType -> this.toCharArray()
        Int::class.javaPrimitiveType -> this.toIntArray()
        Long::class.javaPrimitiveType -> this.toLongArray()
        Float::class.javaPrimitiveType -> this.toFloatArray()
        Double::class.javaPrimitiveType -> this.toDoubleArray()
        else -> this.toArray(componentType)
    }
}

@JvmOverloads
inline fun <T> Sequence<T>.toBooleanArray(selector: (T) -> Boolean = { it.toBoolean() }): BooleanArray {
    val list = this.toList()
    val result = BooleanArray(list.size)
    list.forEachIndexed { i, t -> result[i] = selector(t) }
    return result
}

@JvmOverloads
inline fun <T> Sequence<T>.toByteArray(selector: (T) -> Byte = { it.toByte() }): ByteArray {
    val list = this.toList()
    val result = ByteArray(list.size)
    list.forEachIndexed { i, t -> result[i] = selector(t) }
    return result
}

@JvmOverloads
inline fun <T> Sequence<T>.toShortArray(selector: (T) -> Short = { it.toShort() }): ShortArray {
    val list = this.toList()
    val result = ShortArray(list.size)
    list.forEachIndexed { i, t -> result[i] = selector(t) }
    return result
}

@JvmOverloads
inline fun <T> Sequence<T>.toCharArray(selector: (T) -> Char = { it.toChar() }): CharArray {
    val list = this.toList()
    val result = CharArray(list.size)
    list.forEachIndexed { i, t -> result[i] = selector(t) }
    return result
}

@JvmOverloads
inline fun <T> Sequence<T>.toIntArray(selector: (T) -> Int = { it.toInt() }): IntArray {
    val list = this.toList()
    val result = IntArray(list.size)
    list.forEachIndexed { i, t -> result[i] = selector(t) }
    return result
}

@JvmOverloads
inline fun <T> Sequence<T>.toLongArray(selector: (T) -> Long = { it.toLong() }): LongArray {
    val list = this.toList()
    val result = LongArray(list.size)
    list.forEachIndexed { i, t -> result[i] = selector(t) }
    return result
}

@JvmOverloads
inline fun <T> Sequence<T>.toFloatArray(selector: (T) -> Float = { it.toFloat() }): FloatArray {
    val list = this.toList()
    val result = FloatArray(list.size)
    list.forEachIndexed { i, t -> result[i] = selector(t) }
    return result
}

@JvmOverloads
inline fun <T> Sequence<T>.toDoubleArray(selector: (T) -> Double = { it.toDouble() }): DoubleArray {
    val list = this.toList()
    val result = DoubleArray(list.size)
    list.forEachIndexed { i, t -> result[i] = selector(t) }
    return result
}

fun <T> Sequence<T>.plus(element: T): Sequence<T> {
    return this.plusKt(element)
}

fun <T> Sequence<T>.plus(elements: Array<out T>): Sequence<T> {
    return this.plusKt(elements)
}

fun <T> Sequence<T>.plus(elements: Iterable<T>): Sequence<T> {
    return this.plusKt(elements)
}

fun <T> Sequence<T>.plus(elements: Sequence<T>): Sequence<T> {
    return this.plusKt(elements)
}

fun <T> Sequence<T>.minus(element: T): Sequence<T> {
    return this.minusKt(element)
}

fun <T> Sequence<T>.minus(elements: Array<out T>): Sequence<T> {
    return this.minusKt(elements)
}

fun <T> Sequence<T>.minus(elements: Iterable<T>): Sequence<T> {
    return this.minusKt(elements)
}

fun <T> Sequence<T>.minus(elements: Sequence<T>): Sequence<T> {
    return this.minusKt(elements)
}