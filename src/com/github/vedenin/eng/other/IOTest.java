package com.github.vedenin.eng.other;

import java.io.*;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

/**
 * Created by vedenin on 15.01.16.
 */
public class IOTest {
    public static void main(String[] s) throws Exception {
        // convert String to InputStream using Apache Utils

        InputStream in = IOUtils.toInputStream("test1", StandardCharsets.UTF_8);
        // convert InputStream to String using Apache Utils
        String myString = IOUtils.toString(in, StandardCharsets.UTF_8);
        System.out.println(myString); // print test1

        // convert String to InputStream
        InputStream stream = new ByteArrayInputStream("test2".getBytes(StandardCharsets.UTF_8));

        // convert InputStream to String using Apache Utils
        StringWriter writer = new StringWriter();
        IOUtils.copy(stream, writer, StandardCharsets.UTF_8);
        String theString = writer.toString();
        System.out.println(theString); // print test2

        InputStream myInputStream = IOUtils.toInputStream("test3", "UTF-8");
        // convert InputStream to String
        InputStreamReader i = new InputStreamReader(myInputStream);
        BufferedReader str = new BufferedReader(i);
        String msg = str.readLine();
        System.out.println(msg);
    }
}
