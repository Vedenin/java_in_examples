package com.github.vedenin.rus.collections.multiset;

import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;

import java.util.Arrays;

//  Attention: version with english comments in "eng" package
public class GuavaTreeMultisetTest {

    public static void main(String[] args) {
        // Разберем текст на слова
        String INPUT_TEXT = "Hello World! Hello All! Hi World!";
        // Создаем Multiset
        Multiset<String> multiset = TreeMultiset.create(Arrays.asList(INPUT_TEXT.split(" ")));

        // Выводим кол-вом вхождений слов
        System.out.println(multiset); // напечатает [All!, Hello x 2, Hi, World! x 2]- в алфавитном порядке
        // Выводим все уникальные слова
        System.out.println(multiset.elementSet());    // напечатает [All!, Hello, Hi, World!]- в алфавитном порядке

        // Выводим количество по каждому слову
        System.out.println("Hello = " + multiset.count("Hello"));    // напечатает 2
        System.out.println("World = " + multiset.count("World!"));    // напечатает 2
        System.out.println("All = " + multiset.count("All!"));    // напечатает 1
        System.out.println("Hi = " + multiset.count("Hi"));    // напечатает 1
        System.out.println("Empty = " + multiset.count("Empty"));    // напечатает 0

        // Выводим общее количества всех слов в тексте
        System.out.println(multiset.size());    //напечатает 6

        // Выводим общее количество всех уникальных слов
        System.out.println(multiset.elementSet().size());    //напечатает 4
    }
}
