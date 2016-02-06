package com.github.vedenin.rus.string_and_stream;

import com.google.common.base.Strings;
import org.apache.commons.lang.StringUtils;

import java.util.StringTokenizer;

/**
 * Тестирование разных способов получения isEmpty
 *
 * Created by vvedenin on 2/5/2016.
 */
public class StringUtilsTest {
    private static void isEmptyTest() {
        String emptyString = "";
        // Используя isEmpty из JDK
        boolean jdkIsEmpty = emptyString != null && emptyString.isEmpty();
        System.out.println("JDK isEmpty = " + jdkIsEmpty);

        // Используя equals из JDK
        boolean jdkEquals = emptyString != null && emptyString.isEmpty();
        System.out.println("JDK equals = " + jdkEquals);

        // Используя isEmpty из Apache Commons
        boolean apacheIsEmpty = StringUtils.isEmpty(emptyString);
        System.out.println("Apache isEmpty = " + apacheIsEmpty);

        // Используя isNullOrEmpty из Guava
        boolean guavaIsNullOrEmpty = Strings.isNullOrEmpty(emptyString);
        System.out.println("Guava isNullOrEmpty = " + guavaIsNullOrEmpty);
    }

    private static void isBlankTest() {
        String emptyString = "   ";

        // Используя isBlank из Apache Commons
        boolean apache = StringUtils.isBlank(emptyString);
        System.out.println("isBlank of Apache = " + apache);

        // Используя hasText из Spring Framework
        boolean spring = org.springframework.util.StringUtils.hasText(emptyString);
        System.out.println("hasText of Spring = " + spring);
    }

    private static void findCountOrOccurrencesTest() {
        String testString = "a.b.c.d";
        // Используя Apache Commons
        int apache = StringUtils.countMatches(testString, ".");
        System.out.println("apache = " + apache);

        // Используя Spring Framework's
        int spring = org.springframework.util.StringUtils.countOccurrencesOf(testString, ".");
        System.out.println("spring = " + spring);

        // Используя replace
        int replace = testString.length() - testString.replace(".", "").length();
        System.out.println("replace = " + replace);

        // Используя replaceAll case 1
        int replaceAll = testString.replaceAll("[^.]", "").length();
        System.out.println("replaceAll = " + replaceAll);

        // Используя replaceAll case 2
        int replaceAllCase2 = testString.length() - testString.replaceAll("\\.", "").length();
        System.out.println("replaceAll (second case) = " + replaceAllCase2);

        // Используя split
        int split = testString.split("\\.",-1).length-1;
        System.out.println("split = " + split);

        // Используя Java8
        long java8 = testString.chars().filter(ch -> ch =='.').count();
        System.out.println("java8 = " + java8);

        // Используя Java8 (case 2)
        long java8Case2 = testString.codePoints().filter(ch -> ch =='.').count();
        System.out.println("java8 (second case) = " + java8Case2);

        // Используя StringTokenizer
        int stringTokenizer = new StringTokenizer(" " +testString + " ", ".").countTokens()-1;
        System.out.println("stringTokenizer = " + stringTokenizer);
    }

    public static void main(String[] args) {
        isEmptyTest();
        isBlankTest();
        findCountOrOccurrencesTest();
    }
}
