package com.github.vedenin.perfomace_test;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import org.apache.commons.io.IOUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by vvedenin on 2/15/2016.
 *
 *
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class ConvertInputStreamToStringBigBenchmark {
    private final static String test1 = "test184768612876481276487612876417826487216478216784621784672816478216784621784621786478216478216784261784621782178647281647821647821697421687126784621874621786478216478216874test184768612876481276487612876417826487216478216784621784672816478216784621784621786478216478216784261784621782178647281647821647821697421687126784621874621786478216478216874test184768612876481276487612876417826487216478216784621784672816478216784621784621786478216478216784261784621782178647281647821647821697421687126784621874621786478216478216874test184768612876481276487612876417826487216478216784621784672816478216784621784621786478216478216784261784621782178647281647821647821697421687126784621874621786478216478216874test184768612876481276487612876417826487216478216784621784672816478216784621784621786478216478216784261784621782178647281647821647821697421687126784621874621786478216478216874test184768612876481276487612876417826487216478216784621784672816478216784621784621786478216478216784261784621782178647281647821647821697421687126784621874621786478216478216874test184768612876481276487612876417826487216478216784621784672816478216784621784621786478216478216784261784621782178647281647821647821697421687126784621874621786478216478216874test184768612876481276487612876417826487216478216784621784672816478216784621784621786478216478216784261784621782178647281647821647821697421687126784621874621786478216478216874test184768612876481276487612876417826487216478216784621784672816478216784621784621786478216478216784261784621782178647281647821647821697421687126784621874621786478216478216874";
    private final static String test2 = test1 + test1 + test1 + test1 + test1 + test1 + test1 + test1 + test1 + test1 + test1 + test1 + test1 + test1;
    private final static InputStream inputStream = IOUtils.toInputStream(test2 + test2 + test1 + test1 + test1 + test1, StandardCharsets.UTF_8);

    /*             1. Using ToInputStream of Apache Utils */
    @Benchmark
    public String apacheToInputStream() throws IOException {
        inputStream.mark(0);
        String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        inputStream.reset();
        return result;
    }

    /*             2. Using JDK BufferedReader */
    @Benchmark
    public String jdkBufferedReader() throws IOException {
        inputStream.mark(0);
        BufferedReader str = new BufferedReader(new InputStreamReader(inputStream));
        String result = str.readLine();
        inputStream.reset();
        return result;
    }

    /*             3. Using guava InputStreamReader */
    @Benchmark
    public String guavaReaderInputStream() throws IOException {
        inputStream.mark(0);
        InputStream inputStream = IOUtils.toInputStream(test1, StandardCharsets.UTF_8);
        String result = CharStreams.toString(new InputStreamReader(inputStream, Charsets.UTF_8));
        inputStream.reset();
        return result;
    }

    /*             4. Using JDK Scanner */
    @Benchmark
    public String jdkScanner() throws IOException {
        inputStream.mark(0);
        java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        inputStream.reset();
        return result;
    }

    /*             5. Using Java 8 */
    @Benchmark
    public String jdkJava8() throws IOException {
        inputStream.mark(0);
        String result = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        inputStream.reset();
        return result;
    }

    /*             6. Using Java 8 */
    @Benchmark
    public String jfkJava8parallel() throws IOException {
        inputStream.mark(0);
        String result = new BufferedReader(new InputStreamReader(inputStream)).lines().parallel().collect(Collectors.joining("\n"));
        inputStream.reset();
        return result;
    }


    public static void main(String[] args) throws Exception {
        ConvertInputStreamToStringBigBenchmark test = new ConvertInputStreamToStringBigBenchmark();
        System.out.println();
        System.out.println("apacheToInputStream : " + test.apacheToInputStream().length());
        System.out.println("jdkBufferedReader : " + test.jdkBufferedReader().length());
        System.out.println("guavaReaderInputStream : " + test.guavaReaderInputStream().length());
        System.out.println("jdkScanner : " + test.jdkScanner().length());
        System.out.println("jdkJava8 : " + test.jdkJava8().length());
        System.out.println("jfkJava8parallel : " + test.jfkJava8parallel().length());
        System.out.println();

        Options opt = new OptionsBuilder()
                .include(ConvertInputStreamToStringBigBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

}
