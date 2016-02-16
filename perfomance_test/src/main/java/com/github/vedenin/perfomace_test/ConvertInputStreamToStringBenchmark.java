package com.github.vedenin.perfomace_test;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.CharStreams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.ReaderInputStream;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by vvedenin on 2/15/2016.
 *
 * @BenchmarkMode(Mode.AverageTime)
 @OutputTimeUnit(TimeUnit.MICROSECONDS)
 @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
 @Measurement(iterations = 50, time = 1, timeUnit = TimeUnit.SECONDS)
 @Fork(1)
 @State(Scope.Benchmark)
 */

public class ConvertInputStreamToStringBenchmark {
    private static final String test1 = "test184768612876481276487612876417826487216478216784621784672816478216784621784621786478216478216784261784621782178647281647821647821697421687126784621874621786478216478216874";
    private static final InputStream inputStream = IOUtils.toInputStream(test1, StandardCharsets.UTF_8);

    /*             1. Using ToInputStream of Apache Utils */
    @Benchmark
    public String apacheToInputStream() throws IOException {
        return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    }

    /*             2. Using JDK BufferedReader */
    @Benchmark
    public String jdkBufferedReader() throws IOException {
        BufferedReader str = new BufferedReader(new InputStreamReader(inputStream));
        return str.readLine();
    }

    /*             3. Using guava InputStreamReader */
    @Benchmark
    public String guavaReaderInputStream() throws IOException {
        return CharStreams.toString(new InputStreamReader(inputStream, Charsets.UTF_8));

    }

    /*             4. Using JDK Scanner */
    @Benchmark
    public String jdkScanner() throws IOException {
        java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    /*             5. Using Java 8 */
    @Benchmark
    public String jdkJava8() throws IOException {
        return new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
    }

    /*             6. Using Java 8 */
    @Benchmark
    public String jfkJava8parallel() throws IOException {
        return new BufferedReader(new InputStreamReader(inputStream)).lines().parallel().collect(Collectors.joining("\n"));
    }


    public static void main(String[] args) throws RunnerException {
        /*Options opt = new OptionsBuilder()
                .include(ConvertInputStreamToStringBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();*/
    }

}
