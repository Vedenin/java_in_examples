package com.github.vedenin.eng.stream_api;

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
public class FindAndMatchTests {
    // findFirst - возвращает первый Optional элемент из стрима
    // skip - пропускает N первых элементов (где N параметр метода)
    // collect преобразует stream в коллекцию или другую структуру данных
    private static void testFindFirstSkipCount() {
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");

        System.out.println("Test findFirst and skip start");
        // вернуть первый элемент коллекции
        String first = collection.stream().findFirst().orElse("1");
        System.out.println("first = " + first); // print  first = a1

        // вернуть последний элемент коллекции
        String last = collection.stream().skip(collection.size() - 1).findAny().orElse("1");
        System.out.println("last = " + last ); // print  last = a1

        // найти элемент в коллекции
        String find = collection.stream().filter("a3"::equals).findFirst().get();
        System.out.println("find = " + find); // print  find = a3

        // вернуть третий элемент коллекции по порядку
        String third = collection.stream().skip(2).findFirst().get();
        System.out.println("third = " + third); // print  third = a3

        System.out.println();
        System.out.println("Test collect start");
        // выбрать все элементы по шаблону
        List<String> select = collection.stream().filter((s) -> s.contains("1")).collect(Collectors.toList());
        System.out.println("select = " + select); // print  select = [a1, a1]
    }

    // Метод anyMatch - возвращает true, если условие выполняется хотя бы для одного элемента
    // Метод noneMatch - возвращает true, если условие не выполняется ни для одного элемента
    // Метод allMatch - возвращает true, если условие выполняется для всех элементов
    private static void testMatch() {
        System.out.println();
        System.out.println("Test anyMatch, allMatch, noneMatch  start");
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");

        // найти существуют ли хоть одно совпадение с шаблоном в коллекции
        boolean isAnyOneTrue = collection.stream().anyMatch("a1"::equals);
        System.out.println("anyOneTrue " + isAnyOneTrue); // print  true
        boolean isAnyOneFalse = collection.stream().anyMatch("a8"::equals);
        System.out.println("anyOneFlase " + isAnyOneFalse); // print  false

        // найти существуют ли все совпадения с шаблоном в коллекции
        boolean isAll = collection.stream().allMatch((s) -> s.contains("1"));
        System.out.println("isAll " + isAll); // print  false

        // сравнение на неравенство
        boolean isNotEquals = collection.stream().noneMatch("a7"::equals);
        System.out.println("isNotEquals " + isNotEquals); // print  true
    }

    public static void main(String[] args)  throws Exception {
        testFindFirstSkipCount();
        testMatch();
    }
}
