package com.github.vedenin.nlp;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by vedenin on 06.04.16.
 */
public class SentenceDetectors {
    private static final String RESOURCES_EN_SENT_BIN = "natural-language-processing/src/main/resources/en-sent.bin";

    private static String[] testOpenNLP(String text) throws Exception {
        try (InputStream modelIn = new FileInputStream(RESOURCES_EN_SENT_BIN)) {
            SentenceModel model = new SentenceModel(modelIn);
            SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
            return sentenceDetector.sentDetect(text);
        }
    }


    private static Span[] testOpenNLPPosition(String text) throws Exception {
        try (InputStream modelIn = new FileInputStream(RESOURCES_EN_SENT_BIN)) {
            SentenceModel model = new SentenceModel(modelIn);
            SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
            return sentenceDetector.sentPosDetect(text);
        }
    }

    private static String[] testStanfordCoreNLP(String text) throws Exception {
        StanfordCoreNLP coreNLP = getStanfordCoreNLP();
        Annotation document = new Annotation(text);
        coreNLP.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        String[] result = new String[sentences.size()];
        int i = 0;
        for (CoreMap sentence : sentences) {
            result[i] = sentence.toString();
            i++;
        }
        return result;
    }

    public static void main(String[] strings) throws Exception {
        String text = "'But I don’t want to go among mad people,' Alice remarked. " +
                "'Oh, you can’t help that,' said the Cat: 'we’re all mad here. I’m mad. You’re mad.' " +
                "'How do you know I’m mad?' said Alice. " +
                "'You must be,' said the Cat, 'or you wouldn’t have come here.'";


        String[] sentences = testStanfordCoreNLP(text);
        println();
        println("StanfordCoreNLP:");
        printArray(sentences);
        println();
        println("OpenNLP:");
        printArray(testOpenNLP(text));
        println();
        println("OpenNLP Positions:");
        printArray(testOpenNLPPosition(text));
    }

    private static <T> void printArray(T[] array) {
        Arrays.asList(array).stream().forEach(System.out::println);
    }

    private static <T> void println(T obj) {
        System.out.println(obj);
    }

    private static void println() {
        System.out.println();
    }

    private static StanfordCoreNLP getStanfordCoreNLP(){
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        return new StanfordCoreNLP(props);
    }
}
