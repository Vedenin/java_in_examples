package com.github.vedenin.rus.multimap;

import com.gs.collections.api.multimap.sortedset.MutableSortedSetMultimap;
import com.gs.collections.impl.multimap.set.sorted.TreeSortedSetMultimap;

import java.util.Arrays;
import java.util.List;

public class GsTreeSortedSetMultimapTest {

    // Задача вывести разобрать текст и вывести все индексы для каждого слова
    public static void main(String[] args) {
        String INPUT_TEXT = "Hello World! Hello All! Hi World!";
        // Разберем текст на слова и индексы
        List<String> words = Arrays.asList(INPUT_TEXT.split(" "));
        // Создаем Multimap
        MutableSortedSetMultimap<String, Integer> multiMap = new TreeSortedSetMultimap<String, Integer>();


        // Заполним Multimap
        int i = 0;
        for(String word: words) {
            multiMap.put(word, i);
            i++;
        }

        // Выводим все вхождения слов в текст
        System.out.println(multiMap); // напечатает {Hi=[4], World!=[1, 5], Hello=[0, 2], All!=[3]}- в произвольном порядке
        // Выводим все уникальные слова
        System.out.println(multiMap.keysView());    // напечатает [Hi, Hello, World!, All!] - в произвольном порядке

        // Выводим все индексы вхождения слова в текст
        System.out.println("Hello = " + multiMap.get("Hello"));    // напечатает [0, 2]
        System.out.println("World = " + multiMap.get("World!"));    // напечатает [1, 5]
        System.out.println("All = " + multiMap.get("All!"));    // напечатает [3]
        System.out.println("Hi = " + multiMap.get("Hi"));    // напечатает [4]
        System.out.println("Empty = " + multiMap.get("Empty"));    // напечатает []

        // Выводим общее количество всех слов в тексте
        System.out.println(multiMap.size());    //напечатает 6

        // Выводим общее количество уникальных слов в тексте
        System.out.println(multiMap.keysView().size()); //напечатает 4
    }
}
