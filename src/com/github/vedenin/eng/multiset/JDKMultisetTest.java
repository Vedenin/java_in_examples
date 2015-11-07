package com.github.vedenin.eng.multiset;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//  Attention: version with russian comments in "rus" package
public class JDKMultisetTest {

    public static void main(String[] args) {
        // Parse text to separate words
        String INPUT_TEXT = "Hello World! Hello All! Hi World!";
        List<String> listResult = Arrays.asList(INPUT_TEXT.split(" "));
        // Create analog Multiset using HashMap and fill it
        Map<String, Integer> fakeMultiset = new HashMap<String, Integer>(listResult.size());

        for(String word: listResult) {
            Integer cnt = fakeMultiset.get(word);
            fakeMultiset.put(word, cnt == null ? 1 : cnt + 1);
        }

        // Print count words
        System.out.println(fakeMultiset); // print {World!=2, Hi=1, Hello=2, All!=1}- in random orders
        // Print all unique words
        System.out.println(fakeMultiset.keySet());    // print [World!, Hi, Hello, All!] - in random orders

        // Print count occurrences of words
        System.out.println("Hello = " + fakeMultiset.get("Hello"));    // print 2
        System.out.println("World = " + fakeMultiset.get("World!"));    // print 2
        System.out.println("All = " + fakeMultiset.get("All!"));    // print 1
        System.out.println("Hi = " + fakeMultiset.get("Hi"));    // print 1
        System.out.println("Empty = " + fakeMultiset.get("Empty"));    // print null

        //  Print count all words
        Integer cnt = 0;
        for (Integer wordCount : fakeMultiset.values()){
            cnt += wordCount;
        }
        System.out.println(cnt);    //print 6

        // Print count all unique words
        System.out.println(fakeMultiset.size());    //print 4


    }
}
