package com.github.vedenin.rus.multimap;

import java.util.*;

//  Attention: version with english comments in "eng" package
public class JDKMultimapTest {
    // Задача вывести разобрать текст и вывести все индексы для каждого слова
    public static void main(String[] args) {
        final int LIST_INDEXES_CAPACITY = 50;
        String INPUT_TEXT = "Hello World! Hello All! Hi World!";
        // Разберем текст на слова и индексы
        List<String> words = Arrays.asList(INPUT_TEXT.split(" "));
        // Создаем Multimap
        Map<String, List<Integer>> fakeMultiMap = new HashMap<String, List<Integer>>(words.size());


        // Заполним map
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

        // Выводим все вхождения слов в текст
        System.out.println(fakeMultiMap); // напечатает {Hi=[4], Hello=[0, 2], World!=[1, 5], All!=[3]} - в произвольном порядке
        // Выводим все уникальные слова
        System.out.println(fakeMultiMap.keySet());    // напечатает [Hi, Hello, World!, All!] - в произвольном порядке

        // Выводим все индексы вхождения слова в текст
        System.out.println("Hello = " + fakeMultiMap.get("Hello"));    // напечатает [0, 2]
        System.out.println("World = " + fakeMultiMap.get("World!"));    // напечатает [1, 5]
        System.out.println("All = " + fakeMultiMap.get("All!"));    // напечатает [3]
        System.out.println("Hi = " + fakeMultiMap.get("Hi"));    // напечатает [4]
        System.out.println("Empty = " + fakeMultiMap.get("Empty"));    // напечатает null

        // Выводим общее количество всех слов в тексте
        int cnt = 0;
        for(List<Integer> lists: fakeMultiMap.values()) {
            cnt += lists.size();
        }
        System.out.println(cnt);    //напечатает 6

        // Выводим общее количество уникальных слов в тексте
        System.out.println(fakeMultiMap.keySet().size()); //напечатает 4
    }
}
