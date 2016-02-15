package com.github.vedenin.perfomace_test;

import com.google.common.io.CharSource;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.ReaderInputStream;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * Created by vvedenin on 2/15/2016.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class ConvertBigStringToInputStreamBenchmark {
    private static String test1;

    /*             1. Using ToInputStream of Apache Utils */
    @Benchmark
    public void apacheToInputStream() throws IOException {
        InputStream apacheToInputStream = IOUtils.toInputStream(test1, StandardCharsets.UTF_8);
    }

    /*             2. Using JDK */
    @Benchmark
    public void jdkByteArrayInputStream() throws IOException {
        InputStream jdkByteArrayInputStream = new ByteArrayInputStream(test1.getBytes(StandardCharsets.UTF_8));
    }

    /*             3. Using ReaderInputStream of Apache Utils */
    @Benchmark
    public void apacheReaderInputStream() throws IOException {
        InputStream apacheReaderInputStream = new ReaderInputStream(CharSource.wrap(test1).openStream());
    }

    /*             4. Using Apache Utils and InputStreamReader*/
    @Benchmark
    public void apacheInputStreamReader() throws IOException {
        InputStream apacheInputStreamReader = IOUtils.toInputStream(test1);
    }

    public static void main(String[] args) throws RunnerException {
        String test =  "test184768612876481276487612876417826487216478216784621784672816478216784621784621786478216478216784261784621782178647281647821647821697421687126784621874621786478216478216874";
        StringBuilder builder = new StringBuilder(test);
        for(int i = 0; i< 1000; i++) {
            builder.append(test);
        }
        test1 = builder.toString();
        System.out.println(test1.length());
        Options opt = new OptionsBuilder()
                .include(ConvertStringToInputStreamBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
