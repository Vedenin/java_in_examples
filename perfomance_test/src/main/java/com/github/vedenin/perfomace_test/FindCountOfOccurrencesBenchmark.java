package com.github.vedenin.perfomace_test;

import org.apache.commons.lang.StringUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class FindCountOfOccurrencesBenchmark {
    public static final String testString = "a.b.c.d";

    @Benchmark
    public void countMatches() {
        // Using Apache Commons
        int apache = StringUtils.countMatches(testString, ".");
    }


    @Benchmark
    public void countOccurrencesOf() {
        // Using Spring Framework's
        int spring = org.springframework.util.StringUtils.countOccurrencesOf(testString, ".");
    }

    @Benchmark
    public void replace() {
        // Using replace
        int replace = testString.length() - testString.replace(".", "").length();
    }

    @Benchmark
    public void replaceAll1() {
        // Using replaceAll case 1
        int replaceAll = testString.replaceAll("[^.]", "").length();
    }

    @Benchmark
    public void replaceAll2() {
        // Using replaceAll case 2
        int replaceAllCase2 = testString.length() - testString.replaceAll("\\.", "").length();
    }

    @Benchmark
    public void split() {
        // Using split
        int split = testString.split("\\.",-1).length-1;
    }

    @Benchmark
    public void java8() {
        // Using Java8
        long java8 = testString.chars().filter(ch -> ch =='.').count();
    }

    @Benchmark
    public void java8_1() {
        // Using Java8 (case 2)
        long java8Case2 = testString.codePoints().filter(ch -> ch == '.').count();
    }

    @Benchmark
    public void stringTokenizer() {
        // Using StringTokenizer
        int stringTokenizer = new StringTokenizer(" " + testString + " ", ".").countTokens() - 1;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(FindCountOfOccurrencesBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
