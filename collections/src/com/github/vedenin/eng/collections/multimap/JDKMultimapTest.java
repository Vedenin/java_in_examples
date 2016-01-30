package com.github.vedenin.eng.collections.multimap;

import java.util.*;

//  Attention: version with russian comments in "rus" package
public class JDKMultimapTest {

    // Task: parser string with text and show all indexes of all words
    public static void main(String[] args) {
        final int LIST_INDEXES_CAPACITY = 50;
        String INPUT_TEXT = "Hello World! Hello All! Hi World!";
        // Parse text to words and index
        List<String> words = Arrays.asList(INPUT_TEXT.split(" "));
        // Create analog Multimap
        Map<String, List<Integer>> fakeMultiMap = new HashMap<String, List<Integer>>(words.size());


        // Fill map
        int i = 0;
        for(String word: words) {
            List<Integer> indexes = fakeMultiMap.get(word);
            if(indexes == null) {
                indexes = new ArrayList<Integer>(LIST_INDEXES_CAPACITY);
                fakeMultiMap.put(word, indexes);
            }
            indexes.add(i);
            i++;
        }

        // Print all words
        System.out.println(fakeMultiMap); // print {Hi=[4], Hello=[0, 2], World!=[1, 5], All!=[3]} - in random orders
        // Print all unique words
        System.out.println(fakeMultiMap.keySet());    // print [Hi, Hello, World!, All!] - in random orders

        // Print all indexes
        System.out.println("Hello = " + fakeMultiMap.get("Hello"));    // print [0, 2]
        System.out.println("World = " + fakeMultiMap.get("World!"));    // print [1, 5]
        System.out.println("All = " + fakeMultiMap.get("All!"));    // print [3]
        System.out.println("Hi = " + fakeMultiMap.get("Hi"));    // print [4]
        System.out.println("Empty = " + fakeMultiMap.get("Empty"));    // print null

        // Print count all words
        int cnt = 0;
        for(List<Integer> lists: fakeMultiMap.values()) {
            cnt += lists.size();
        }
        System.out.println(cnt);    //print 6

        // Print count all unique words
        System.out.println(fakeMultiMap.keySet().size()); //print 4
    }
}
