package com.github.vedenin.rus.utils;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.impl.list.mutable.FastList;
import com.gs.collections.impl.map.mutable.UnifiedMap;
import com.gs.collections.impl.set.mutable.UnifiedSet;

import java.util.*;

/**
 *  Attention: version with english comments in "eng" package
 *
 * Пример создания коллекций разными библиотеками
 *
 * Created by vedenin on 17.10.15.
 */
public class CreateCollectionTest {
    public static void main(String[] args) {
        System.out.println("start");
        createArrayList();
        System.out.println();
        createHashSet();
        System.out.println();
        createHashMap();
        System.out.println("end");
    }

    // Сравним как создается ArrayList в Gs, Guava и JDK
    private static void createArrayList() {
        System.out.println("createArrayList start");
        // Простое создание пустых коллекций
        List<String> emptyGuava = Lists.newArrayList(); // c помощью guava
        List<String> emptyJDK = new ArrayList<>(); // аналог JDK
        MutableList<String>  emptyGS = FastList.newList(); // c помощью gs

        // Создать список ровно со 100 элементами
        List < String > exactly100 = Lists.newArrayListWithCapacity(100); // c помощью guava
        List<String> exactly100JDK = new ArrayList<>(100); // аналог JDK
        MutableList<String>  empty100GS = FastList.newList(100); // c помощью gs

        // Создать список в котором ожидается около 100 элементов (чуть больше чем 100)
        List<String> approx100 = Lists.newArrayListWithExpectedSize(100); // c помощью guava
        List<String> approx100JDK = new ArrayList<>(115); // аналог JDK
        MutableList<String>  approx100GS = FastList.newList(115); // c помощью gs

        // Создать список из заданных элементов
        List<String> withElements = Lists.newArrayList("alpha", "beta", "gamma"); // c помощью guava
        List<String> withElementsJDK = Arrays.asList("alpha", "beta", "gamma"); // аналог JDK
        MutableList<String>  withElementsGS = FastList.newListWith("alpha", "beta", "gamma"); // c помощью gs

        System.out.println(withElements);
        System.out.println(withElementsJDK);
        System.out.println(withElementsGS);

        // Создать список из любого объекта Iterable интерфейса (то есть любой коллекции)
        Collection<String> collection = new HashSet<>(3);
        collection.add("1");
        collection.add("2");
        collection.add("3");

        List<String> fromIterable = Lists.newArrayList(collection); // c помощью guava
        List<String> fromIterableJDK = new ArrayList<>(collection); // аналог JDK
        MutableList<String>  fromIterableGS = FastList.newList(collection); // c помощью gs

        System.out.println(fromIterable);
        System.out.println(fromIterableJDK);
        System.out.println(fromIterableGS);
        /* обратите внимание у JDK необходим объект Collection интерфейса, у guava и gs достаточно Iterable */

        // Создать список из Iterator'а
        Iterator<String> iterator = collection.iterator();
        List<String> fromIterator = Lists.newArrayList(iterator); // c помощью guava, аналога в JDK нет
        System.out.println(fromIterator);

        // Создать список из массива
        String[] array = {"4", "5", "6"};
        List<String> fromArray = Lists.newArrayList(array); // c помощью guava
        List<String> fromArrayJDK = Arrays.asList(array); // аналог JDK
        MutableList<String>  fromArrayGS = FastList.newListWith(array); // c помощью gs
        System.out.println(fromArray);
        System.out.println(fromArrayJDK);
        System.out.println(fromArrayGS);

        // Создать список из c помощью фабрики
        MutableList<String>  fromFabricGS = FastList.newWithNValues(10, () -> String.valueOf(Math.random())); // c помощью gs
        System.out.println(fromFabricGS);

        System.out.println("createArrayList end");

    }

    // Сравним как создается HashSet в Guava,Gs  и JDK
    private static void createHashSet() {
        System.out.println("createHashSet start");
        // Простое создание пустых коллекций
        Set<String> emptyGuava = Sets.newHashSet(); // c помощью guava
        Set<String> emptyJDK = new HashSet<>(); // аналог JDK
        Set<String> emptyGS = UnifiedSet.newSet(); // c помощью gs

        // Создать множество в котором ожидается около 100 элементов (чуть больше чем 100)
        Set<String> approx100 = Sets.newHashSetWithExpectedSize(100); // c помощью guava
        Set<String> approx100JDK = new HashSet<>(130); // аналог JDK
        Set<String> approx100GS = UnifiedSet.newSet(130); // c помощью gs

        // Создать множество из заданных элементов
        Set<String> withElements =  Sets.newHashSet("alpha", "beta", "gamma"); // c помощью guava
        Set<String> withElementsJDK = new HashSet<>(Arrays.asList("alpha", "beta", "gamma")); // аналог JDK
        Set<String> withElementsGS  = UnifiedSet.newSetWith("alpha", "beta", "gamma"); // c помощью gs

        System.out.println(withElements);
        System.out.println(withElementsJDK);
        System.out.println(withElementsGS);

        // Создать множество из любого объекта Iterable интерфейса (то есть любой коллекции)
        Collection<String> collection = new ArrayList<>(3);
        collection.add("1");
        collection.add("2");
        collection.add("3");

        Set<String> fromIterable = Sets.newHashSet(collection); // c помощью guava
        Set<String> fromIterableJDK = new HashSet<>(collection); // аналог JDK
        Set<String> fromIterableGS  = UnifiedSet.newSet(collection); // c помощью gs

        System.out.println(fromIterable);
        System.out.println(fromIterableJDK);
        System.out.println(fromIterableGS);
        /* обратите внимание у JDK необходим объект Collection интерфейса, у guava достаточно Iterable */

        // Создать множество из Iterator'а
        Iterator<String> iterator = collection.iterator();
        Set<String> fromIterator = Sets.newHashSet(iterator); // c помощью guava, аналога в JDK нет
        System.out.println(fromIterator);

        // Создать множество из массива
        String[] array = {"4", "5", "6"};
        Set<String> fromArray = Sets.newHashSet(array); // c помощью guava
        Set<String> fromArrayJDK = new HashSet<>(Arrays.asList(array)); // аналог JDK
        Set<String> fromArrayGS  = UnifiedSet.newSetWith(array); // c помощью gs
        System.out.println(fromArray);
        System.out.println(fromArrayJDK);
        System.out.println(fromArrayGS);

        System.out.println("createHashSet end");
    }

    // Сравним как создается HashMap в Guava,Gs и JDK
    private static void createHashMap() {
        System.out.println("createHashMap start");
        // Простое создание пустых коллекций
        Map<String, String> emptyGuava = Maps.newHashMap(); // c помощью guava
        Map<String, String> emptyJDK = new HashMap<>(); // аналог JDK
        Map<String, String> emptyGS = UnifiedMap.newMap(); // c помощью gs

        // Создать map'у в котором ожидается около 100 элементов (чуть больше чем 100)
        Map<String, String> approx100 = Maps.newHashMapWithExpectedSize(100); // c помощью guava
        Map<String, String> approx100JDK = new HashMap<>(130); // аналог JDK
        Map<String, String> approx100GS = UnifiedMap.newMap(130); // c помощью gs

        // Создать map'у из другой map'ы
        Map<String, String> map = new HashMap<>(3);
        map.put("k1","v1");
        map.put("k2","v2");
        Map<String, String> withMap =  Maps.newHashMap(map); // c помощью guava
        Map<String, String> withMapJDK = new HashMap<>(map); // аналог JDK
        Map<String, String> withMapGS = UnifiedMap.newMap(map); // c помощью gs

        System.out.println(withMap);
        System.out.println(withMapJDK);
        System.out.println(withMapGS);

        // Создать map'у из ключей
        Map<String, String> withKeys =  UnifiedMap.newWithKeysValues("1", "a", "2", "b");
        System.out.println(withKeys);

        System.out.println("createHashMap end");
    }
}
