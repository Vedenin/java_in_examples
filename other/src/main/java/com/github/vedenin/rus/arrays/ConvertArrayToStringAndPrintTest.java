package com.github.vedenin.rus.arrays;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 *  Тестирование превращения массива в строку для печати
 *
 * Created by vedenin on 04.02.16.
 */
public class ConvertArrayToStringAndPrintTest {
    public static void main(String[] s) throws Exception {

        /* (JDK 5) Использование Arrays.toString и Arrays.deepToString */
        // simple array
        String[] array = new String[]{"John", "Mary", "Bob"};
        System.out.println(Arrays.toString(array)); // Напечатает: [John, Mary, Bob]

        // nested array
        String[][] deepArray = new String[][]{{"John", "Mary"}, {"Alice", "Bob"}};
        System.out.println(Arrays.toString(deepArray)); // Напечатает: [[Ljava.lang.String;@106d69c, [Ljava.lang.String;@52e922]
        System.out.println(Arrays.deepToString(deepArray)); // Напечатает: [[John, Mary], [Alice, Bob]]

        //double Array
        double[] arrayGiven = {7.0, 9.0, 5.0, 1.0, 3.0};
        System.out.println(Arrays.toString(arrayGiven)); //  Напечатает: [7.0, 9.0, 5.0, 1.0, 3.0 ]

        //int Array
        int[] arrayInt = {7, 9, 5, 1, 3};
        System.out.println(Arrays.toString(arrayInt)); //  Напечатает: [7, 9, 5, 1, 3 ]

        /* (JDK 8) Использование Stream API */
        Arrays.asList(array).stream().forEach(System.out::print); //  Напечатает: JohnMaryBob
        System.out.println();
        Arrays.asList(deepArray).stream().forEach(s1 -> Arrays.asList(s1).stream().forEach(System.out::print));
        System.out.println();
        DoubleStream.of(arrayGiven).forEach((d) -> System.out.print(d + " "));  //  Напечатает: 7.0 9.0 5.0 1.0 3.0
        System.out.println();
        IntStream.of(arrayInt).forEach(System.out::print);  //  Напечатает: 79513


    }
}
