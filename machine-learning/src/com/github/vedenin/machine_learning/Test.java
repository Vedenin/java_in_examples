package com.github.vedenin.machine_learning;

import smile.data.AttributeDataset;
import smile.data.parser.ArffParser;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by vedenin on 07.04.16.
 */
public class Test {

    private void test() throws IOException, ParseException {
        ArffParser arffParser = new ArffParser();
        arffParser.setResponseIndex(4);
        AttributeDataset weather = arffParser.parse(this.getClass().getResourceAsStream("/smile/data/weka/weather.nominal.arff"));
        double[][] x = weather.toArray(new double[weather.size()][]);
        int[] y = weather.toArray(new int[weather.size()]);
    }

    public static void main(String[] s) throws Exception {
        Test cls = new Test();
        cls.test();
    }
}
