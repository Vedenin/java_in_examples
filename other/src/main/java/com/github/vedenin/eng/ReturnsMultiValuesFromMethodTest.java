package com.github.vedenin.eng;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

/**
 * All ways to return multiValues from Method
 *
 * Created by vvedenin on 2/18/2016.
 */
public class ReturnsMultiValuesFromMethodTest {
    private static final int RETURN_COUNT = 2;
    private static final int VALUE_A = 0;
    private static final int VALUE_B = 1;
    private static final String A = "a";
    private static final String B = "b";

    /** 1. Using Array **/
    private static String[] methodWithArrayResult() {
        //...
        return new String[]{"valueA", "valueB"};
    }

    private static void usingArrayResultTest() {
        String[] result = methodWithArrayResult();
        System.out.println();
        System.out.println("A = " + result[VALUE_A]);
        System.out.println("B = " + result[VALUE_B]);
    }

    /** 2. Using ArrayList **/
    private static List<String> methodWithListResult() {
        //...
        return Arrays.asList("valueA", "valueB");
    }

    private static void usingListResultTest() {
        List<String> result = methodWithListResult();
        System.out.println();
        System.out.println("A = " + result.get(VALUE_A));
        System.out.println("B = " + result.get(VALUE_B));
    }

    /** 3. Using HashMap **/
    private static Map<String, String> methodWithMapResult() {
        Map<String, String> result = new HashMap<>(RETURN_COUNT);
        result.put(A, "valueA");
        result.put(B, "valueB");
        //...
        return result;
    }

    private static void usingMapResultTest() {
        Map<String, String> result = methodWithMapResult();
        System.out.println();
        System.out.println("A = " + result.get(A));
        System.out.println("B = " + result.get(B));
    }

    /** 4. Using your custom container class **/
    private static class MyContainer<M,N> {
        private final M first;
        private final N second;

        public MyContainer(M first, N second) {
            this.first = first;
            this.second = second;
        }

        public M getFirst() {
            return first;
        }

        public N getSecond() {
            return second;
        }

        // + hashcode, equals, toString if need
    }

    private static MyContainer<String, String> methodWithContainerResult() {
        //...
        return new MyContainer("valueA", "valueB");
    }

    private static void usingContainerResultTest() {
        MyContainer<String, String> result = methodWithContainerResult();
        System.out.println();
        System.out.println("A = " + result.getFirst());
        System.out.println("B = " + result.getSecond());
    }

    /** 5. Using AbstractMap.simpleEntry     **/
    private static AbstractMap.SimpleEntry<String, String> methodWithAbstractMapSimpleEntryResult() {
        //...
        return new AbstractMap.SimpleEntry<>("valueA", "valueB");
    }

    private static void usingAbstractMapSimpleResultTest() {
        AbstractMap.SimpleEntry<String, String> result = methodWithAbstractMapSimpleEntryResult();
        System.out.println();
        System.out.println("A = " + result.getKey());
        System.out.println("B = " + result.getValue());
    }

    /** 6. Using Pair of Apache Commons **/
    private static Pair<String, String> methodWithPairResult() {
        //...
        return new ImmutablePair<>("valueA", "valueB");
    }

    private static void usingPairResultTest() {
        Pair<String, String> result = methodWithPairResult();
        System.out.println();
        System.out.println("A = " + result.getKey());
        System.out.println("B = " + result.getValue());
    }

    public static void main(String[] args) throws Exception {
        usingArrayResultTest();
        usingListResultTest();
        usingMapResultTest();
        usingContainerResultTest();
        usingAbstractMapSimpleResultTest();
        usingPairResultTest();
    }
}
