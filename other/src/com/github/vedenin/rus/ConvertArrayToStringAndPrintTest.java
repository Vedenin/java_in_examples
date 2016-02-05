package com.github.vedenin.eng.string_and_stream;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 *  ??????? ?????????????? ??????? ? ?????? ? ????? ???? ????????? ??????? ? ???
 *
 * Created by vedenin on 04.02.16.
 */
public class ConvertArrayToStringTest {
    public static void main(String[] s) throws Exception {

        /* (JDK 5) ????????? Arrays.toString ??? Arrays.deepToString */
        // simple array
        String[] array = new String[]{"John", "Mary", "Bob"};
        System.out.println(Arrays.toString(array)); // ??????????: [John, Mary, Bob]

        // nested array
        String[][] deepArray = new String[][]{{"John", "Mary"}, {"Alice", "Bob"}};
        System.out.println(Arrays.toString(deepArray)); // ??????????: [[Ljava.lang.String;@106d69c, [Ljava.lang.String;@52e922]
        System.out.println(Arrays.deepToString(deepArray)); // ??????????: [[John, Mary], [Alice, Bob]]

        //double Array
        double[] arrayGiven = {7.0, 9.0, 5.0, 1.0, 3.0};
        System.out.println(Arrays.toString(arrayGiven)); //  ??????????: [7.0, 9.0, 5.0, 1.0, 3.0 ]

        //int Array
        int[] arrayInt = {7, 9, 5, 1, 3};
        System.out.println(Arrays.toString(arrayInt)); //  ??????????: [7, 9, 5, 1, 3 ]

        /* (JDK 8) ????????? Stream API */
        Arrays.asList(array).stream().forEach(System.out::print); //  ??????????: JohnMaryBob
        System.out.println();
        Arrays.asList(deepArray).stream().forEach(s1 -> Arrays.asList(s1).stream().forEach(System.out::print));
        System.out.println();
        DoubleStream.of(arrayGiven).forEach((d) -> System.out.print(d + " "));  //  ??????????: 7.0 9.0 5.0 1.0 3.0
        System.out.println();
        IntStream.of(arrayInt).forEach(System.out::print);  //  ??????????: 79513


    }
}
