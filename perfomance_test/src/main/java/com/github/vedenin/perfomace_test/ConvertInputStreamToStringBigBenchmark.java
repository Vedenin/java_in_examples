package com.github.vedenin.perfomace_test;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import org.apache.commons.io.IOUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
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
 *  @BenchmarkMode(Mode.AverageTime)
 @OutputTimeUnit(TimeUnit.MICROSECONDS)
 @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
 @Measurement(iterations = 50, time = 1, timeUnit = TimeUnit.SECONDS)
 @Fork(1)
 @State(Scope.Benchmark)

 */
public class ConvertInputStreamToStringBigBenchmark {
    private static final String test = "test184768612876481276487612876417826487216478216784621784672816478216784621784621786478216478216784261784621782178647281647821647821697421687126784621874621786478216478216874";
    private static InputStream inputStream;

    /*             1. Using ToInputStream of Apache Utils */
    @Benchmark
    public void apacheToInputStream() throws IOException {
        String apacheToInputStream = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    }

    /*             2. Using JDK BufferedReader */
    @Benchmark
    public void jdkBufferedReader() throws IOException {
        BufferedReader str = new BufferedReader(new InputStreamReader(inputStream));
        String jdkBufferedReader = str.readLine();
    }

    /*             3. Using guava InputStreamReader */
    @Benchmark
    public void guavaReaderInputStream() throws IOException {
        String guavaReaderInputStream = CharStreams.toString(new InputStreamReader(inputStream, Charsets.UTF_8));
    }

    /*             4. Using JDK Scanner */
    @Benchmark
    public void jdkScanner() throws IOException {
        java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
        String stringScanner = s.hasNext() ? s.next() : "";
    }

    /*             5. Using Java 8 */
    @Benchmark
    public void jdkJava8() throws IOException {
        String stringJava8 = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
    }

    /*             6. Using Java 8 */
    @Benchmark
    public void jdkJava8parallel() throws IOException {
        String stringJava8 = new BufferedReader(new InputStreamReader(inputStream)).lines().parallel().collect(Collectors.joining("\n"));
    }


    public static void main(String[] args) throws RunnerException {
        StringBuilder builder = new StringBuilder(test);
        for(int i = 0; i< 1000; i++) {
            builder.append(test);
        }
        inputStream = IOUtils.toInputStream(builder.toString(), StandardCharsets.UTF_8);
        /*Options opt = new OptionsBuilder()
                .include(ConvertInputStreamToStringBigBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();*/
    }

}
