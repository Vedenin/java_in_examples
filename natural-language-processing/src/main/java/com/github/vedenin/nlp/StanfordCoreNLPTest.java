package com.github.vedenin.nlp;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;
import java.util.Properties;

/**
 * Created by vedenin on 30.01.16.
 */
public class StanfordCoreNLPTest {

    public static void main(String[] s) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // read some text in the text variable
        String text = "\"But I do not want to go among mad people,\" Alice remarked.\n" +
                "\"Oh, you can not help that,\" said the Cat: \"we are all mad here. I am mad. You are mad.\"\n" +
                "\"How do you know I am mad?\" said Alice.\n" +
                "\"You must be,\" said the Cat, \"or you would not have come here.\" This is awful, bad, disgusting";

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
            System.out.println(sentiment + "\t" + sentence);
        }
    }
}
