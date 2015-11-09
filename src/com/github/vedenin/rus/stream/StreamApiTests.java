package com.github.vedenin.rus.stream;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * Примеры работы методов Stream Api
 *
 * Created by vedenin on 17 .10.15.
 */
public class StreamApiTests {
    // Способы создания стримов
    private static void testBuildStream() throws Exception {
        System.out.println("Test buildStream start");

        // Создание стрима из значений
        Stream<String> streamFromValues = Stream.of("a1", "a2", "a3");
        System.out.println("streamFromValues = " + streamFromValues.collect(Collectors.toList())); // напечатает streamFromValues = [a1, a2, a3]

        // Создание стрима из массива
        String[] array = {"a1","a2","a3"};
        Stream<String> streamFromArrays = Arrays.stream(array);
        System.out.println("streamFromArrays = " + streamFromArrays.collect(Collectors.toList())); // напечатает streamFromArrays = [a1, a2, a3]

        // Создание стрима из файла (каждая запись в файле будет отдельной строкой в стриме)
        File file = new File("1.tmp");
        file.deleteOnExit();
        PrintWriter out = new PrintWriter(file);
        out.println("a1");
        out.println("a2");
        out.println("a3");
        out.close();

        Stream<String> streamFromFiles = Files.lines(Paths.get(file.getAbsolutePath()));
        System.out.println("streamFromFiles = " + streamFromFiles.collect((Collectors.toList()))); // напечатает streamFromFiles = [a1, a2, a3]

        // Создание стрима из коллекции
        Collection<String> collection = Arrays.asList("a1", "a2", "a3");
        Stream<String> streamFromCollection = collection.stream();
        System.out.println("streamFromCollection = " + streamFromCollection.collect((Collectors.toList()))); // напечатает streamFromCollection = [a1, a2, a3]

        // Создание бесконечных стримов
        // С помощью Stream.iterate
        Stream<Integer> streamFromIterate = Stream.iterate(1, n -> n + 1);
        System.out.println("streamFromIterate = " + streamFromIterate.limit(3).collect(Collectors.toList())); // напечатает streamFromIterate = [1, 2, 3]

        // С помощью Stream.generate
        Stream<String> streamFromGenerate = Stream.generate(() -> "a1");
        System.out.println("streamFromGenerate = " + streamFromGenerate.limit(3).collect(Collectors.toList())); // напечатает streamFromGenerate = [a1, a1, a1]

    }


    // filter - возвращает stream, в котором есть только элементы, соответствующие условию фильтра
    // count - возвращает количество элементов в стриме
    // findFirst - возвращает первый Optional элемент из стрима
    // skip - пропускает N первых элементов (где N параметр метода)
    // Метод collect преобразует stream в коллекцию или другую структуру данных
    private static void testFilterFindFirstSkipCount() {
        System.out.println();
        System.out.println("Test filter and count start");
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");

        // Вернуть количество вхождений объекта
        long count = collection.stream().filter("a1"::equals).count();
        System.out.println("count = " + count); // напечатает count = 2

        System.out.println();
        System.out.println("Test findFirst and skip start");
        // вернуть первый элемент коллекции
        String first = collection.stream().findFirst().orElse("1");
        System.out.println("first = " + first); // напечатает first = a1

        // вернуть последний элемент коллекции
        String last = collection.stream().skip(collection.size() - 1).findAny().orElse("1");
        System.out.println("last = " + last ); // напечатает last = a1

        // найти элемент в коллекции
        String find = collection.stream().filter("a3"::equals).findFirst().get();
        System.out.println("find = " + find); // напечатает find = a3

        // вернуть третий элемент коллекции по порядку
        String third = collection.stream().skip(2).findFirst().get();
        System.out.println("third = " + third); // напечатает third = a3

        System.out.println();
        System.out.println("Test collect start");
        // выбрать все элементы по шаблону
        List<String> select = collection.stream().filter((s) -> s.contains("1")).collect(Collectors.toList());
        System.out.println("select = " + select); // напечатает select = [a1, a1]
    }

    // Метод distinct возвращает stream без дубликатов, при этом для упорядоченного стрима (например, коллекция на основе list) порядок стабилен , для неупорядоченного - порядок не гарантируется
    // Метод collect преобразует stream в коллекцию или другую структуру данных
    private static void testDistinct() {
        System.out.println();
        System.out.println("Test distinct start");
        Collection<String> ordered = Arrays.asList("a1", "a2", "a2", "a3", "a1", "a2", "a2");
        Collection<String> nonOrdered = new HashSet<>(ordered);

        // Получение коллекции без дубликатов
        List<String> distinct = nonOrdered.stream().distinct().collect(Collectors.toList());
        System.out.println("distinct = " + distinct); // напечатает distinct = [a1, a2, a3] - порядок не гарантируется

        List<String> distinctOrdered = ordered.stream().distinct().collect(Collectors.toList());
        System.out.println("distinctOrdered = " + distinctOrdered); // напечатает distinct = [a1, a2, a3] - порядок гарантируется
    }

    // Метод Map изменяет выборку по определенному правилу, возвращает stream с новой выборкой
    private static void testMap() {
        System.out.println();
        System.out.println("Test map start");
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");
        // Изменение всех элементов коллекции
        List<String> transform = collection.stream().map((s) -> s + "_1").collect(Collectors.toList());
        System.out.println("transform = " + transform); // напечатает transform = [a1_1, a2_1, a3_1, a1_1]
    }

    // Метод Peek возвращает тот же стрим, но при этом применяет указанный метод к каждому элементу стрима
    private static void testPeek() {
        System.out.println();
        System.out.println("Test peek start");
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");
        // Напечатать отладочную информацию по каждому элементу стрима
        List<String> peek = collection.stream().map(String::toUpperCase).peek((e) -> System.out.print("," + e)).
                collect(Collectors.toList());
        System.out.println("peek = " + peek); // напечатает transform = [a1_1, a2_1, a3_1, a1_1]
    }

    // Метод Limit позволяет ограничить выборку определенным количеством первых элементов
    private static void testLimit() {
        System.out.println();
        System.out.println("Test limit start");
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");

        // Вернуть первые два элемента
        List<String> limit = collection.stream().limit(2).collect(Collectors.toList());
        System.out.println("limit = " + limit); // напечатает limit = [a1, a2]

        // Вернуть два элемента начиная со второго
        List<String> fromTo = collection.stream().skip(1).limit(2).collect(Collectors.toList());
        System.out.println("fromTo = " + fromTo); // напечатает fromTo = [a2, a3]
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
        System.out.println("anyOneTrue " + isAnyOneTrue); // напечатает true
        boolean isAnyOneFalse = collection.stream().anyMatch("a8"::equals);
        System.out.println("anyOneFlase " + isAnyOneFalse); // напечатает false

        // найти существуют ли все совпадения с шаблоном в коллекции
        boolean isAll = collection.stream().allMatch((s) -> s.contains("1"));
        System.out.println("isAll " + isAll); // напечатает true

        // сравнение на неравенство
        boolean isNotEquals = collection.stream().noneMatch("a8"::equals);
        System.out.println("isNotEquals " + isNotEquals); // напечатает true
    }

    // Метод max вернет максимальный элемент, в качестве условия использует компаратор
    // Метод min вернет минимальный элемент, в качестве условия использует компаратор
    private static void testMinMax() {
        System.out.println();
        System.out.println("Test min and max start");
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");

        // найти максимальное значение
        String max = collection.stream().max(String::compareTo).get();
        System.out.println("max " + max); // напечатает a3

        // найти минимальное значение
        String min = collection.stream().min(String::compareTo).get();
        System.out.println("min " + min); // напечатает a1
    }

    // Метод reduce позволяет выполнять агрегатные функции на всей коллекцией (такие как сумма, нахождение минимального или максимального значение и т.п.)
    // Он возвращает одно Optional значение
    private static void testReduce() {
        System.out.println();
        System.out.println("Test reduce start");
        Collection<Integer> collection = Arrays.asList(1, 2, 3, 4, 2);

        // Вернуть сумму
        Integer sum = collection.stream().reduce((s1, s2) -> s1 + s2).orElse(0); // через stream Api
        Integer sumOld = 0; // по старому методу
        for(Integer i: collection) {
            sumOld += i;
        }
        System.out.println("sum = " + sum + " : " + sumOld); // напечатает sum = 12 : 12


        // Вернуть максимум
        Integer max1 = collection.stream().reduce((s1, s2) -> s1 > s2 ? s1 : s2).orElse(0); // через stream Api
        Integer max2 = collection.stream().reduce(Integer::max).orElse(0); // через stream Api используя Integer::max
        Integer maxOld = null; // по старому методу
        for(Integer i: collection) {
            maxOld = maxOld != null && maxOld > i? maxOld: i;
        }
        maxOld = maxOld == null? 0 : maxOld;
        System.out.println("max = " + max1 + " : " + max2 + " : " + maxOld); // напечатает max1 = 4 : 4 : 4

        // Вернуть минимум
        Integer min = collection.stream().reduce((s1, s2) -> s1 < s2 ? s1 : s2).orElse(0); // через stream Api
        Integer minOld = null; // по старому методу
        for(Integer i: collection) {
            minOld = minOld != null && minOld < i? minOld: i;
        }
        minOld = minOld == null? 0 : minOld;
        System.out.println("min = " + min+ " : " + minOld); // напечатает min = 1 : 1

        // Вернуть последний элемент
        Integer last = collection.stream().reduce((s1, s2) -> s2).orElse(0); // через stream Api
        Integer lastOld = null; // по старому методу
        for(Integer i: collection) {
            lastOld = i;
        }
        lastOld = lastOld == null? 0 : lastOld;
        System.out.println("last = " + last + " : " + lastOld); // напечатает last = 2 : 2

        // Вернуть сумму чисел, которые больше 2
        Integer sumMore2 = collection.stream().filter(o -> o > 2).reduce((s1, s2) -> s1 + s2).orElse(0);     // через stream Api
        Integer sumMore2Old = 0; // по старому методу
        for(Integer i: collection) {
            if(i > 2) {
                sumMore2Old += i;
            }
        }
        System.out.println("sumMore2 = " + sumMore2 + " : " + sumMore2Old); // напечатает sumMore2 = 7 : 7

        // Вернуть сумму нечетных чисел
        Integer sumOdd = collection.stream().filter(o -> o % 2 != 0).reduce((s1, s2) -> s1 + s2).orElse(0); // через stream Api
        Integer sumOddOld = 0; // по старому методу
        for(Integer i: collection) {
            if(i % 2 != 0) {
                sumOddOld += i;
            }
        }
        System.out.println("sumOdd = " + sumOdd + " : " + sumOddOld); // напечатает sumOdd = 4 : 4
    }

    // Метод Sorted позволяет сортировать значения либо в натуральном порядке, либо задавая Comparator
    private static void testSorted() {
        System.out.println();
        System.out.println("Test sorted start");
        Collection<String> collection = Arrays.asList("a1", "a4", "a3", "a2", "a1", "a4");

        // отсортировать значения по алфавиту
        List<String> sorted = collection.stream().sorted().collect(Collectors.toList());
        System.out.println("sorted = " + sorted); // напечатает sorted = [a1, a1, a2, a3, a4, a4]

        // отсортировать значения по алфавиту и убрать дубликаты
        List<String> sortedDistinct = collection.stream().sorted().distinct().collect(Collectors.toList());
        System.out.println("sortedDistinct = " + sortedDistinct); // напечатает sortedDistinct = [a1, a2, a3, a4]

        // отсортировать значения по алфавиту в обратном порядке
        List<String> sortedReverse = collection.stream().sorted((o1, o2) -> -o1.compareTo(o2)).collect(Collectors.toList());
        System.out.println("sortedReverse = " + sortedReverse); // напечатает sortedReverse = [a4, a4, a3, a2, a1, a1]

        // отсортировать значения по алфавиту в обратном порядке  и убрать дубликаты
        List<String> distinctReverse = collection.stream().sorted((o1, o2) -> -o1.compareTo(o2)).distinct().collect(Collectors.toList());
        System.out.println("distinctReverse = " + distinctReverse); // напечатает sortedReverse = [a4, a3, a2, a1]

    }

    public static void main(String[] args)  throws Exception {
        testBuildStream();
        testFilterFindFirstSkipCount();
        testPeek();
        testDistinct();
        testMap();
        testLimit();
        testMatch();
        testMinMax();
        testReduce();
        testSorted();

    }


}
