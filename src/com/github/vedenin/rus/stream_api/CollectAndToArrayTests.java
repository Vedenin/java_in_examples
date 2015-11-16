package com.github.vedenin.rus.stream_api;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Примеры работы методов Stream Api
 *
 * Created by vedenin on 17 .10.15.
 */
public class CollectAndToArrayTests {
    // Метод collect преобразует stream в коллекцию или другую структуру данных
    private static void testCollect() {
        System.out.println();
        System.out.println("Test distinct start");
        Collection<String> collection = Arrays.asList("a1", "a2", "a2", "a3", "a1", "a2", "a2");

        // Получение коллекции без дубликатов
        List<String> distinct = collection.stream().distinct().collect(Collectors.toList());
        System.out.println("distinct = " + distinct); // напечатает distinct = [a1, a2, a3]

        // Получение массива уникальных заначений
        String[] array = collection.stream().distinct().map(String::toUpperCase).toArray(String[]::new);
        System.out.println("array = " + Arrays.asList(array)); // напечатает array = [A1, A2, A3]
    }

    public static void main(String[] args)  throws Exception {
        testCollect();
    }
}
