package com.github.vedenin.eng.other;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.CharStreams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.ReaderInputStream;

/**
 * Created by vedenin on 15.01.16.
 */
public class IOTest {
    public static void main(String[] s) throws Exception {
        convertStringToOrFromInputStream();
    }

    private static void convertStringToOrFromInputStream() throws IOException {
        /*             1. Using Apache Utils */
        // convert String to InputStream
        InputStream inputStreamApache = IOUtils.toInputStream("test1", StandardCharsets.UTF_8);
        // convert InputStream to String
        String stringApache = IOUtils.toString(inputStreamApache, StandardCharsets.UTF_8);
        System.out.println(stringApache); // print test1

        /*             2. Using JDK */
        // convert String to InputStream
        InputStream inputStreamJDK = new ByteArrayInputStream("test2".getBytes(StandardCharsets.UTF_8));

        // convert InputStream to String
        BufferedReader str = new BufferedReader(new InputStreamReader(inputStreamJDK));
        String stringJDK = str.readLine();
        System.out.println(stringJDK); // print test2

        /*             3. Using guava */
        // convert String to InputStream
        InputStream targetStreamGuava = new ReaderInputStream(CharSource.wrap("test3").openStream());

        // convert InputStream to String
        String stringGuava = CharStreams.toString(new InputStreamReader(targetStreamGuava, Charsets.UTF_8));
        System.out.println(stringGuava); // print test3


        /*             4. Using JDK and Scanner*/
        InputStream inputStreamForScanner = new ReaderInputStream(CharSource.wrap("test4").openStream());
        // convert InputStream to String
        java.util.Scanner s = new java.util.Scanner(inputStreamForScanner).useDelimiter("\\A");
        String stringScanner = s.hasNext() ? s.next() : "";
        System.out.println(stringScanner);

        /*             5. Using Java 8 */
        InputStream inputStreamForJava8 = new ReaderInputStream(CharSource.wrap("test5").openStream());
        // convert InputStream to String
        String stringJava8 = new BufferedReader(new InputStreamReader(inputStreamForJava8)).lines().collect(Collectors.joining("\n"));
        System.out.println(stringJava8);
    }
}
