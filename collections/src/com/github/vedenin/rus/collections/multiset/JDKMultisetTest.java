package com.github.vedenin.rus.collections.multiset;

import java.util.*;

//  Attention: version with english comments in "eng" package
public class JDKMultisetTest {

    public static void main(String[] args) {
        // Разберем текст на слова
        String INPUT_TEXT = "Hello World! Hello All! Hi World!";
        List<String> listResult = Arrays.asList(INPUT_TEXT.split(" "));
        // Создаем эмуляцию Multiset c помощью HashMap и заполняем
        Map<String, Integer> fakeMultiset = new HashMap<String, Integer>(listResult.size());

        for(String word: listResult) {
            Integer cnt = fakeMultiset.get(word);
            fakeMultiset.put(word, cnt == null ? 1 : cnt + 1);
        }

        // Выводим кол-вом вхождений слов
        System.out.println(fakeMultiset); // напечатает {World!=2, Hi=1, Hello=2, All!=1}- в произвольном порядке
        // Выводим все уникальные слова
        System.out.println(fakeMultiset.keySet());    // напечатает [World!, Hi, Hello, All!] - в произвольном порядке

        // Выводим количество по каждому слову
        System.out.println("Hello = " + fakeMultiset.get("Hello"));    // напечатает 2
        System.out.println("World = " + fakeMultiset.get("World!"));    // напечатает 2
        System.out.println("All = " + fakeMultiset.get("All!"));    // напечатает 1
        System.out.println("Hi = " + fakeMultiset.get("Hi"));    // напечатает 1
        System.out.println("Empty = " + fakeMultiset.get("Empty"));    // напечатает null

        //  Выводим общее количества всех слов в тексте
        Integer cnt = 0;
        for (Integer wordCount : fakeMultiset.values()){
            cnt += wordCount;
        }
        System.out.println(cnt);    //напечатает 6

        // Выводим общее количество уникальных слов
        System.out.println(fakeMultiset.size());    //напечатает 4


    }
}
