package com.github.vedenin.eng.bimap;

import java.util.HashMap;
import java.util.Map;

//  Attention: version with russian comments in "rus" package
public class JDKBiMapTest {

    // Task: create collection to translate Polish-English words in two ways
    public static void main(String[] args) {
        String[] englishWords = {"one", "two", "three","ball","snow"};
        String[] russianWords = {"jeden", "dwa", "trzy", "kula", "snieg"};

        // Create analog BiMap
        Map<String, String> biMapKeys = new HashMap(englishWords.length);
        Map<String, String> biMapValues = new HashMap(russianWords.length);
        // Create English-Polish dictionary
        int i = 0;
        for(String englishWord: englishWords) {
            biMapKeys.put(englishWord, russianWords[i]);
            biMapValues.put(russianWords[i], englishWord);
            i++;
        }

        // Print count words
        System.out.println(biMapKeys); // print {ball=kula, two=dwa, three=trzy, snow=snieg, one=jeden}- in random orders
        // Print all unique words
        System.out.println(biMapKeys.keySet());    // print [ball, two, three, snow, one] - in random orders
        System.out.println(biMapValues.keySet());    // print [dwa, trzy, kula, snieg, jeden] - in random orders

        // Print translate by words
        System.out.println("one = " + biMapKeys.get("one"));    // print one = jeden
        System.out.println("two = " + biMapKeys.get("two"));    // print two = dwa
        System.out.println("kula = " + biMapValues.get("kula"));    // print kula = ball
        System.out.println("snieg = " + biMapValues.get("snieg"));    // print snieg = snow
        System.out.println("empty = " + biMapValues.get("empty"));    // print empty = null

        // Print count word's pair
        System.out.println(biMapKeys.size());    //print 5

    }
}
