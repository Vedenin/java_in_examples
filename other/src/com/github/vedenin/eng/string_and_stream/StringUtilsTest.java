package com.github.vedenin.eng.string_and_stream;

import com.google.common.base.Strings;
import org.apache.commons.lang.StringUtils;

/**
 * Created by vvedenin on 2/5/2016.
 */
public class StringUtilsTest {
    private static void isEmptyTest() {
        String emptyString = "";
        if(emptyString != null && emptyString.isEmpty()) {
            System.out.println("test isEmpty of jdk - passed");
        }
        if("".equals(emptyString)) {
            System.out.println("test equals of jdk - passed");
        }
        if(StringUtils.isEmpty(emptyString)) {
            System.out.println("test isEmpty of apache commons - passed");
        }
        if(Strings.isNullOrEmpty(emptyString)) {
            System.out.println("test isNullOrEmpty of Guava - passed");
        }
    }

    private static void isBlank() {
        String emptyString = "   ";
        boolean isApache = StringUtils.isBlank(emptyString);
        boolean hasText = org.springframework.util.StringUtils.hasText(emptyString);
        System.out.println("isBlank of Apache = " + isApache);
        System.out.println("hasText of Spring = " + hasText);
    }

    public static void main(String[] args) {
        isEmptyTest();
        isBlank();
    }
}
