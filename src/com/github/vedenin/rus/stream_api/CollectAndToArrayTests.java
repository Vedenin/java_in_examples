package com.github.vedenin.rus.stream_api;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Примеры работы методов Stream Api
 * <p>
 * Created by vedenin on 17 .10.15.
 */
public class CollectAndToArrayTests {
    // Метод collect преобразует stream в коллекцию или другую структуру данных
    // Полезные статические методы из Collectors:
    // toList, toCollection, toSet - представляют стрим в виде списка, коллекции или множества
    // toConcurrentMap, toMap - позволяют преобразовать стрим в map, используя указанные функции
    // averagingInt, averagingDouble, averagingLong - возвращают среднее значение
    // summingInt, summingDouble, summingLong - возвращает сумму
    // summarizingInt, summarizingDouble, summarizingLong - возвращают SummaryStatistics с разными агрегатными значениями
    // partitioningBy - разделяет коллекцию на две части по соотвествию условию и возвращает их как Map<Boolean, List>
    // groupingBy - разделить коллекцию по условию и вернуть Map<N, List<T>>, где T - тип последнего стрима, N - значение разделителя
    // mapping - дополнительные преобразования значений для сложных Collector'ов
    private static void testCollect() {
        System.out.println();
        System.out.println("Test distinct start");

        // ******** Работа с числами
        Collection<Integer> numbers = Arrays.asList(1, 2, 3, 4);

        // Получить сумму нечетных чисел
        long sumOdd = numbers.stream().collect(Collectors.summingInt(((p) -> p % 2 == 1 ? p : 0)));
        System.out.println("sumOdd = " + sumOdd); // напечатает sumEven = 4

        // Вычисть к каждого элемента 1 и получить среднее
        double average = numbers.stream().collect(Collectors.averagingInt((p) -> p - 1));
        System.out.println("average = " + average); // напечатает average = 1.5

        // Прибавить к числам 3 и получить статистику
        IntSummaryStatistics statistics = numbers.stream().collect(Collectors.summarizingInt((p) -> p + 3));
        System.out.println("statistics = " + statistics); // напечатает statistics = IntSummaryStatistics{count=4, sum=22, min=4, average=5.500000, max=7}

        // Получить сумму четных чисел через IntSummaryStatistics
        long sumEven = numbers.stream().collect(Collectors.summarizingInt((p) -> p % 2 == 0 ? p : 0)).getSum();
        System.out.println("sumEven = " + sumEven); // напечатает sumEven = 6

        // Разделить числа на четные и нечетные
        Map<Boolean, List<Integer>> parts = numbers.stream().collect(Collectors.partitioningBy((p) -> p % 2 == 0));
        System.out.println("parts = " + parts); // напечатает parts = {false=[1, 3], true=[2, 4]}

        // ******** Работа со строками
        Collection<String> strings = Arrays.asList("a1", "b2", "c3", "a1");

        // Получение списка из коллекции строк без дубликатов
        List<String> distinct = strings.stream().distinct().collect(Collectors.toList());
        System.out.println("distinct = " + distinct); // напечатает distinct = [a1, b2, c3]

        // Получение массива уникальных значений из коллекции строк
        String[] array = strings.stream().distinct().map(String::toUpperCase).toArray(String[]::new);
        System.out.println("array = " + Arrays.asList(array)); // напечатает array = [A1, B2, C3]

        // Объединить все элементы в одну строку через разделитель : и обернуть тегами <b> ... </b>
        String join = strings.stream().collect(Collectors.joining(" : ", "<b> ", " </b>"));
        System.out.println("join = " + join); // напечатает <b> a1 : b2 : c3 : a1 </b>

        // Преобразовать в map, где первый символ ключ, второй символ значение
        Map<String, String> map = strings.stream().distinct().collect(Collectors.toMap((p) -> p.substring(0, 1), (p) -> p.substring(1, 2)));
        System.out.println("map = " + map); // напечатает map = {a=1, b=2, c=3}

        // Преобразовать в map, сгрупировав по первому символу строки
        Map<String, List<String>> groups = strings.stream().collect(Collectors.groupingBy((p) -> p.substring(0, 1)));
        System.out.println("groups = " + groups); // напечатает groups = {a=[a1, a1], b=[b2], c=[c3]}

        // Преобразовать в map, сгрупировав по первому символу строки и в качестве значения взять второй символ объединим через :
        Map<String, String> groupJoin = strings.stream().collect(Collectors.groupingBy((p) -> p.substring(0, 1), Collectors.mapping((p) -> p.substring(1, 2), Collectors.joining(":"))));
        System.out.println("groupJoin = " + groupJoin); // напечатает groupJoin = groupJoin = {a=1/1, b=2, c=3}

        // Напишем собственный Collector, который будет выполнять объединение строк с помощью StringBuilder
        Collector<String,StringBuilder, String> stringBuilderCollector =  Collector.of(
                StringBuilder::new, // метод инициализации аккумулятора
                (b ,s) -> b.append(s).append(" , "), // метод обработки каждого элемента
                (b1, b2) -> b1.append(b2).append(" , "), // метод соединения двух аккумуляторов при параллельном выполнении
                StringBuilder::toString // метод выполняющися в самом конце
        );
        String joinBuilder = strings.stream().collect(stringBuilderCollector);
        System.out.println("joinBuilder = " + joinBuilder); // напечатает joinBuilder = a1 , b2 , c3 , a1 ,

        // Аналог Collector'а выше стилем JDK7 и ниже
        StringBuilder b = new StringBuilder(); // метод инициализации аккумулятора
        for(String s: strings) {
            b.append(s).append(" , "); // метод обработки каждого элемента
        }
        String joinBuilderOld = b.toString(); // метод выполняющися в самом конце
        System.out.println("joinBuilderOld = " + joinBuilderOld); // напечатает joinBuilderOld = a1 , b2 , c3 , a1 ,
    }

    public static void main(String[] args) throws Exception {
        testCollect();
    }
}
