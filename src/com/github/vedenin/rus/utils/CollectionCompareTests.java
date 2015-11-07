package com.github.vedenin.rus.utils;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.gs.collections.api.collection.MutableCollection;
import com.gs.collections.api.set.MutableSet;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.set.mutable.UnifiedSet;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *  Attention: version with english comments in "eng" package
 *
 * Created by vedenin on 23.10.15.
 */
public class CollectionCompareTests {
    // Проверить полное соответствие двух коллекций
    private static void testContainsAll() {
        Collection<String> collection1 = Lists.newArrayList("a1", "a2", "a3", "a1");
        Collection<String> collection2 = Lists.newArrayList("a1", "a2", "a3", "a1");
        Iterable<String> iterable1 = collection1;
        Iterable<String> iterable2 = collection2;
        MutableCollection<String> mutableCollection1 = FastList.newListWith("a1", "a2", "a3", "a1");
        MutableCollection<String> mutableCollection2 = FastList.newListWith("a1", "a2", "a3", "a1");

        // Проверить полное соответствие двух коллекций
        boolean jdk =  collection1.containsAll(collection2); // c помощью JDK
        boolean guava = Iterables.elementsEqual(iterable1, iterable2); // с помощью guava
        boolean apache = CollectionUtils.containsAll(collection1, collection2);  // c помощью Apache
        boolean gs = mutableCollection1.containsAll(mutableCollection2); // c помощью GS

        System.out.println("containsAll = " + jdk + ":" + guava + ":" + apache + ":" + gs); // напечатает containsAll = true:true:true:true
    }

    // Проверить наличие хотя бы одного общего элемента у двух коллекций
    private static void testContainsAny() {
        Collection<String> collection1 = Lists.newArrayList("a1", "a2", "a3", "a1");
        Collection<String> collection2 = Lists.newArrayList("a4", "a8", "a3", "a5");
        Set<String> set1 = Sets.newHashSet("a1", "a2", "a3", "a1");
        Set<String> set2 = Sets.newHashSet("a4", "a8", "a3", "a5");
        MutableSet<String> mutableSet1 = UnifiedSet.newSetWith("a1", "a2", "a3", "a1");
        MutableSet<String> mutableSet2 = UnifiedSet.newSetWith("a4", "a8", "a3", "a5");

        // Проверить наличие хотя бы одного общего элемента у двух коллекций
        boolean jdk = !Collections.disjoint(collection1, collection2); // c помощью JDK
        boolean guava = !Sets.intersection(set1, set2).isEmpty(); // с помощью guava
        boolean apache = CollectionUtils.containsAny(collection1, collection2);  // c помощью Apache
        boolean gs = !mutableSet1.intersect(mutableSet2).isEmpty(); // c помощью GS
        System.out.println("containsAny = " + jdk + ":" + guava + ":" + apache + ":" + gs); // напечатает containsAny = true:true:true:true
    }

    // Найти все общие элементы (пересечение) у двух коллекций
    private static void testIntersect() {
        Collection<String> collection1 = Lists.newArrayList("a1", "a2", "a3", "a1");
        Collection<String> collection2 = Lists.newArrayList("a4", "a8", "a3", "a5");
        Set<String> set1 = Sets.newHashSet("a1", "a2", "a3", "a1");
        Set<String> set2 = Sets.newHashSet("a4", "a8", "a3", "a5");
        MutableSet<String> mutableSet1 = UnifiedSet.newSetWith("a1", "a2", "a3", "a1");
        MutableSet<String> mutableSet2 = UnifiedSet.newSetWith("a4", "a8", "a3", "a5");

        // Найти все общие элементы у двух коллекций
        Set<String> jdk = new HashSet<>(set1); // c помощью JDK
        jdk.retainAll(set2);
        Set<String> guava = Sets.intersection(set1, set2); // с помощью guava
        Collection<String> apache = CollectionUtils.intersection(collection1, collection2);  // c помощью Apache
        Set<String> gs = mutableSet1.intersect(mutableSet2); // c помощью GS
        System.out.println("intersect = " + jdk + ":" + guava + ":" + apache + ":" + gs); // напечатает intersect = [a3]:[a3]:[a3]:[a3]
    }

    // Найти все элементы, которые есть в одной коллекции и нет в другой (difference)
    private static void testDifference() {
        Collection<String> collection1 = Lists.newArrayList("a2", "a3");
        Collection<String> collection2 = Lists.newArrayList("a8", "a3", "a5");
        Set<String> set1 = Sets.newHashSet("a2", "a3");
        Set<String> set2 = Sets.newHashSet("a8", "a3", "a5");
        MutableSet<String> mutableSet1 = UnifiedSet.newSetWith("a2", "a3");
        MutableSet<String> mutableSet2 = UnifiedSet.newSetWith("a8", "a3", "a5");

        // Найти все элементы, которые есть в одной коллекции и нет в другой (difference)
        Set<String> jdk = new HashSet<>(set1); // c помощью JDK
        jdk.removeAll(set2);
        Set<String> guava = Sets.difference(set1, set2); // с помощью guava
        Collection<String> apache = CollectionUtils.removeAll(collection1, collection2);  // c помощью Apache
        Set<String> gs = mutableSet1.difference(mutableSet2); // c помощью GS
        System.out.println("difference = " + jdk + ":" + guava + ":" + apache + ":" + gs); // напечатает difference = [a2]:[a2]:[a2]:[a2]
    }


    // Найти все различные элементы (symmetric difference) у двух коллекций
    private static void testSymmetricDifference() {
        Collection<String> collection1 = Lists.newArrayList("a2", "a3");
        Collection<String> collection2 = Lists.newArrayList("a8", "a3", "a5");
        Set<String> set1 = Sets.newHashSet("a2", "a3");
        Set<String> set2 = Sets.newHashSet("a8", "a3", "a5");
        MutableSet<String> mutableSet1 = UnifiedSet.newSetWith("a2", "a3");
        MutableSet<String> mutableSet2 = UnifiedSet.newSetWith("a8", "a3", "a5");

        // Найти все различные элементы (symmetric difference) у двух коллекций
        Set<String> intersect = new HashSet<>(set1); // c помощью JDK
        intersect.retainAll(set2);

        Set<String> jdk = new HashSet<>(set1);
        jdk.addAll(set2);
        jdk.removeAll(intersect);

        Set<String> guava = Sets.symmetricDifference(set1, set2); // с помощью guava
        Collection<String> apache = CollectionUtils.disjunction(collection1, collection2);  // c помощью Apache
        Set<String> gs = mutableSet1.symmetricDifference(mutableSet2); // c помощью GS
        System.out.println("symmetricDifference = " + jdk + ":" + guava + ":" + apache + ":" + gs); // напечатает symmetricDifference = [a2, a5, a8]:[a2, a5, a8]:[a2, a5, a8]:[a2, a5, a8]
    }

    // Получить объедение двух коллекций
    private static void testUnion() {
        Set<String> set1 = Sets.newHashSet("a1", "a2");
        Set<String> set2 = Sets.newHashSet("a4");
        MutableSet<String> mutableSet1 = UnifiedSet.newSetWith("a1", "a2");
        MutableSet<String> mutableSet2 = UnifiedSet.newSetWith("a4");
        Collection<String> collection1 = set1;
        Collection<String> collection2 = set2;
        // Получить объедение двух коллекций
        Set<String> jdk = new HashSet<>(set1); // c помощью JDK
        jdk.addAll(set2);
        Set<String> guava = Sets.union(set1, set2); // с помощью guava
        Collection<String> apache = CollectionUtils.union(collection1, collection2);  // c помощью Apache
        Set<String> gs = mutableSet1.union(mutableSet2); // c помощью GS
        System.out.println("union = " + jdk + ":" + guava + ":" + apache + ":" + gs); // напечатает union = [a1, a2, a4]:[a1, a2, a4]:[a1, a2, a4]:[a1, a2, a4]
    }

    public static void main(String[] args) {
        testContainsAll();
        testContainsAny();
        testIntersect();
        testDifference();
        testSymmetricDifference();
        testUnion();
    }
}
