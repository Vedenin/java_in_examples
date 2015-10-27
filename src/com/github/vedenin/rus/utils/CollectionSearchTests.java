package com.github.vedenin.rus.utils;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.gs.collections.api.collection.MutableCollection;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.api.ordered.OrderedIterable;
import com.gs.collections.impl.list.mutable.FastList;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Тестирование методов поиска в коллекциях от guava, apache, jdk, gs
 *
 * Created by vedenin on 17 .10.15.
 */
public class CollectionSearchTests {
    // Вернуть количество вхождений объекта
    private static void testCount() {
        Collection<String> collection = Lists.newArrayList("a1", "a2", "a3", "a1");
        Iterable<String> iterable = collection;
        MutableCollection<String> collectionGS = FastList.newListWith("a1", "a2", "a3", "a1");

        // Вернуть количество вхождений объекта
        int i1 = Iterables.frequency(iterable, "a1"); // с помощью guava
        int i2 = Collections.frequency(collection, "a1"); // c помощью JDK
        int i3 = CollectionUtils.cardinality("a1", iterable); // c помощью Apache
        int i4 = collectionGS.count("a1"::equals);
        long i5 = collection.stream().filter("a1"::equals).count(); // c помощью stream JDK

        System.out.println("count = " + i1 + ":" + i2 + ":" + i3 + ":" + i4 + ":" + i5); // напечатает count = 2:2:2:2:2
    }

    // вернуть первый элемент коллекции
    private static void testGetFirst() {
        Collection<String> collection = Lists.newArrayList("a1", "a2", "a3", "a1");
        OrderedIterable<String> orderedIterable = FastList.newListWith("a1", "a2", "a3", "a1");
        Iterable<String> iterable = collection;

        // вернуть первый элемент коллекции
        Iterator<String> iterator = collection.iterator(); // c помощью JDK
        String jdk = iterator.hasNext() ? iterator.next(): "1";
        String guava = Iterables.getFirst(iterable, "1"); // с помощью guava
        String apache = CollectionUtils.get(iterable, 0);  // c помощью Apache
        String gs = orderedIterable.getFirst(); // c помощью GS
        String stream = collection.stream().findFirst().orElse("1"); // c помощью Stream API
        System.out.println("first = " + jdk + ":" + guava + ":" + apache + ":" + gs + ":" + stream); // напечатает first = a1:a1:a1:a1:a1
    }

    // вернуть последней элемент коллекции
    private static void testGetLast() {
        Collection<String> collection = Lists.newArrayList("a1", "a2", "a3", "a8");
        OrderedIterable<String> orderedIterable = FastList.newListWith("a1", "a2", "a3", "a8");
        Iterable<String> iterable = collection;

        // вернуть последней элемент коллекции
        Iterator<String> iterator = collection.iterator(); // c помощью JDK
        String jdk = "1";
        while(iterator.hasNext()) {
            jdk = iterator.next();
        }
        String guava = Iterables.getLast(iterable, "1"); // с помощью guava
        String apache = CollectionUtils.get(collection, collection.size()-1);  // c помощью Apache
        String gs = orderedIterable.getLast(); // c помощью GS
        String stream = collection.stream().skip(collection.size()-1).findFirst().orElse("1"); // c помощью Stream API
        System.out.println("last = " + jdk + ":" + guava + ":" + apache + ":" + gs + ":" + stream); // напечатает last = a8:a8:a8:a8:a8
    }

    // вернуть единственный элемент коллекции
    private static void testGetSingle() {
        Collection<String> collection = Lists.newArrayList("a3");
        OrderedIterable<String> orderedIterable = FastList.newListWith("a3");
        Iterable<String> iterable = collection;

        // вернуть единственный элемент коллекции
        String guava = Iterables.getOnlyElement(iterable); // с помощью guava
        String jdk = collection.iterator().next(); // c помощью JDK
        String apache = CollectionUtils.extractSingleton(collection); // c помощью Apache
        assert(orderedIterable.size() != 1);// c помощью GS
        String gs =  orderedIterable.getFirst();

        System.out.println("single = " + jdk + ":" + guava + ":" + apache + ":" + gs); // напечатает single = a3:a3:a3:a3
    }

    // вернуть максимальный элемент коллекции
    private static void testGetMax() {
        Collection<String> collection = Lists.newArrayList("5", "1", "3", "8", "4");
        OrderedIterable<String> orderedIterable = FastList.newListWith("5", "1", "3", "8", "4");
        Iterable<String> iterable = collection;

        // вернуть максимальный элемент коллекции
        String jdk = Collections.max(collection); // c помощью JDK
        String gs = orderedIterable.max(); // c помощью GS
        String guava = Ordering.natural().max(iterable); // с помощью guava

        System.out.println("max = " + jdk + ":" + guava + ":" + gs); // напечатает max = 8:8:8
    }

    // вернуть минимальный элемент коллекции
    private static void testGetMin() {
        Collection<String> collection = Lists.newArrayList("5", "1", "3", "8", "4");
        OrderedIterable<String> orderedIterable = FastList.newListWith("5", "1", "3", "8", "4");
        Iterable<String> iterable = collection;

        // вернуть минимальный элемент коллекции
        String jdk = Collections.min(collection); // c помощью JDK
        String gs = orderedIterable.min(); // c помощью GS
        String guava = Ordering.natural().min(iterable); // с помощью guava
        System.out.println("min = " + jdk + ":" + guava + ":" + gs); // напечатает min = 1:1:1
    }

    // найти элемент в отсортированом списке
    private static void testBinarySearch() {
        List<String> list = Lists.newArrayList("2", "4", "13", "31", "43");
        MutableList<String> mutableList = FastList.newListWith("2", "4","13", "31", "43");

        // найти элемент в отсортированом списке
        int jdk = Collections.binarySearch(list, "13");
        int guava = Ordering.natural().binarySearch(list, "13");
        int gs = mutableList.binarySearch("13");

        System.out.println("find = " + jdk + ":" + guava + ":" + gs); // напечатает find = 2:2:2
    }

    // найти элемент в неотсортированной коллекции
    private static void testSearch() {
        Collection<String> collection = Lists.newArrayList("2", "14", "3", "13", "43");
        MutableList<String> mutableList = FastList.newListWith("2", "14", "3", "13", "43");
        Iterable<String> iterable = collection;

        // найти элемент в неотсортированной коллекции
        String jdk = collection.stream().filter("13"::equals).findFirst().get();
        String guava = Iterables.find(iterable, "13"::equals);
        String apache = CollectionUtils.find(iterable, "13"::equals);
        String gs = mutableList.select("13"::equals).get(0);

        System.out.println("find = " + jdk + ":" + guava + ":" + apache + ":" + gs); // напечатает find = 13:13:13:13
    }

    // вернуть третий элемент коллекции по порядку
    private static void testGetByIndex() {
        Collection<String> collection = Lists.newArrayList("a1", "a2", "a3", "a1");
        MutableCollection<String> orderedIterable = FastList.newListWith("a1", "a2", "a3", "a1");
        Iterable<String> iterable = collection;

        // вернуть третий элемент коллекции по порядку
        String jdk = collection.stream().skip(2).findFirst().get(); // c помощью JDK
        String guava = Iterables.get(iterable, 2); // с помощью guava
        String apache = CollectionUtils.get(iterable, 2);  // c помощью Apache
        System.out.println("third = " + jdk + ":" + guava + ":" + apache); // напечатает third = a3:a3:a3
    }

    // выбрать все элементы по шаблону
    private static void testSelect() {
        Collection<String> collection = Lists.newArrayList("2", "14", "3", "13", "43");
        MutableCollection<String> mutableCollection = FastList.newListWith("2", "14", "3", "13", "43");
        Iterable<String> iterable = collection;

        // выбрать все элементы по шаблону
        List<String> jdk = collection.stream().filter((s) -> s.contains("1")).collect(Collectors.toList()); // c помощью JDK
        Iterable<String>  guava = Iterables.filter(iterable, (s) -> s.contains("1")); // с помощью guava
        Collection<String> apache = CollectionUtils.select(iterable, (s) -> s.contains("1")); // c помощью Apache
        MutableCollection<String> gs = mutableCollection.select((s) -> s.contains("1")); // c помощью GS

        System.out.println("select = " + jdk + ":" + guava + ":" + apache + ":" + gs); // напечатает select = [14, 13]:[14, 13]:[14, 13]:[14, 13]
    }

    public static void main(String[] args) {
        testCount();         // Вернуть количество вхождений объекта
        testGetFirst(); // Вернуть первый элемент
        testGetLast(); // Вернуть последний элемент
        testGetSingle(); // вернуть единственный элемент коллекции
        testGetMax();     // вернуть максимальный элемент коллекции
        testGetMin();    // вернуть минимальный элемент коллекции
        testBinarySearch();     // найти элемент в отсортированом списке
        testSearch();     // найти элемент в неотсортированной коллекции
        testGetByIndex();         // вернуть третий элемент коллекции по порядку
        testSelect();
    }

}
