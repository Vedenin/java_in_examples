package com.github.vedenin.nlp;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by vedenin on 04.02.16.
 */
public class OpenNLPSentenceDetectionTest {
    public static void main(String[] strings) throws FileNotFoundException {
        String text = "“But I don’t want to go among mad people,” Alice remarked.\n" +
                "“Oh, you can’t help that,” said the Cat: “we’re all mad here. I’m mad. You’re mad.”\n" +
                "“How do you know I’m mad?” said Alice.\n" +
                "“You must be,” said the Cat, “or you wouldn’t have come here.”";

        InputStream modelIn = new FileInputStream("natural-language-processing/src/main/resources/en-sent.bin");

        try {
            SentenceModel model = new SentenceModel(modelIn);
            SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
            String sentences[] = sentenceDetector.sentDetect(text);
            Span sentences2[] = sentenceDetector.sentPosDetect(text);
            for(String sentence: sentences) {
                System.out.println(sentence);
            }
            System.out.println(Arrays.deepToString(sentences2));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (modelIn != null) {
                try {
                    modelIn.close();
                }
                catch (IOException e) {
                }
            }
        }
    }
}
