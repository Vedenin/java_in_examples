package com.github.vedenin.rus.multimap;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;

import java.util.Arrays;
import java.util.List;

//  Attention: version with english comments in "eng" package
public class ApacheMultiValueMapTest {

    // Задача вывести разобрать текст и вывести все индексы для каждого слова
    public static void main(String[] args) {
        String INPUT_TEXT = "Hello World! Hello All! Hi World!";
        // Разберем текст на слова и индексы
        List<String> words = Arrays.asList(INPUT_TEXT.split(" "));
        // Создаем Multimap
        MultiMap<String, Integer> multiMap = new MultiValueMap<String, Integer>();


        // Заполним Multimap
        int i = 0;
        for(String word: words) {
            multiMap.put(word, i);
            i++;
        }

        // Выводим все вхождения слов в текст
        System.out.println(multiMap); // напечатает {Hi=[4], Hello=[0, 2], World!=[1, 5], All!=[3]} - в произвольном порядке
        // Выводим все уникальные слова
        System.out.println(multiMap.keySet());    // напечатает [Hi, Hello, World!, All!] - в произвольном порядке

        // Выводим все индексы вхождения слова в текст
        System.out.println("Hello = " + multiMap.get("Hello"));    // напечатает [0, 2]
        System.out.println("World = " + multiMap.get("World!"));    // напечатает [1, 5]
        System.out.println("All = " + multiMap.get("All!"));    // напечатает [3]
        System.out.println("Hi = " + multiMap.get("Hi"));    // напечатает [4]
        System.out.println("Empty = " + multiMap.get("Empty"));    // напечатает null

        // Выводим общее количество всех уникальных слов
        System.out.println(multiMap.keySet().size());    //напечатает 4
    }
}
