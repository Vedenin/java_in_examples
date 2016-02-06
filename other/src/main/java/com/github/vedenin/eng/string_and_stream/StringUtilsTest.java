package com.github.vedenin.eng.string_and_stream;

import com.google.common.base.Strings;
import org.apache.commons.lang.StringUtils;

import java.util.StringTokenizer;

/**
 * Testing some code examples working with String
 *
 * Created by vvedenin on 2/5/2016.
 */
public class StringUtilsTest {
    // Ways to check that string is empty
    private static void isEmptyTest() {
        String emptyString = "";
        // Using isEmpty from JDK
        boolean jdkIsEmpty = emptyString != null && emptyString.isEmpty();
        System.out.println("JDK isEmpty = " + jdkIsEmpty);

        // Using equals from JDK
        boolean jdkEquals = emptyString != null && emptyString.isEmpty();
        System.out.println("JDK equals = " + jdkEquals);

        // Using isEmpty from Apache Commons
        boolean apacheIsEmpty = StringUtils.isEmpty(emptyString);
        System.out.println("Apache isEmpty = " + apacheIsEmpty);

        // Using isNullOrEmpty from Guava
        boolean guavaIsNullOrEmpty = Strings.isNullOrEmpty(emptyString);
        System.out.println("Guava isNullOrEmpty = " + guavaIsNullOrEmpty);
    }

    // Ways to check that string without text
    private static void isBlankTest() {
        String emptyString = "   ";

        // Using isBlank from Apache Commons
        boolean apache = StringUtils.isBlank(emptyString);
        System.out.println("isBlank of Apache = " + apache);

        // Using hasText from Spring Framework
        boolean spring = org.springframework.util.StringUtils.hasText(emptyString);
        System.out.println("hasText of Spring = " + spring);
    }

    // Wayt to find count of the occurrences in string
    private static void findCountOfOccurrencesTest() {
        String testString = "a.b.c.d";
        // Using Apache Commons
        int apache = StringUtils.countMatches(testString, ".");
        System.out.println("apache = " + apache);

        // Using Spring Framework's
        int spring = org.springframework.util.StringUtils.countOccurrencesOf(testString, ".");
        System.out.println("spring = " + spring);

        // Using replace
        int replace = testString.length() - testString.replace(".", "").length();
        System.out.println("replace = " + replace);

        // Using replaceAll case 1
        int replaceAll = testString.replaceAll("[^.]", "").length();
        System.out.println("replaceAll = " + replaceAll);

        // Using replaceAll case 2
        int replaceAllCase2 = testString.length() - testString.replaceAll("\\.", "").length();
        System.out.println("replaceAll (second case) = " + replaceAllCase2);

        // Using split
        int split = testString.split("\\.",-1).length-1;
        System.out.println("split = " + split);

        // Using Java8
        long java8 = testString.chars().filter(ch -> ch =='.').count();
        System.out.println("java8 = " + java8);

        // Using Java8 (case 2)
        long java8Case2 = testString.codePoints().filter(ch -> ch =='.').count();
        System.out.println("java8 (second case) = " + java8Case2);

        // Using StringTokenizer
        int stringTokenizer = new StringTokenizer(" " +testString + " ", ".").countTokens()-1;
        System.out.println("stringTokenizer = " + stringTokenizer);
    }

    public static void main(String[] args) {
        isEmptyTest();
        isBlankTest();
        findCountOfOccurrencesTest();
    }
}
