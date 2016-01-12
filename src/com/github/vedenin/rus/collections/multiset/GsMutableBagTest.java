package com.github.vedenin.rus.collections.multiset;

import com.gs.collections.api.bag.MutableBag;
import com.gs.collections.impl.bag.mutable.HashBag;

import java.util.Arrays;

//  Attention: version with english comments in "eng" package
public class GsMutableBagTest {

    public static void main(String[] args) {
        // Разберем текст на слова
        String INPUT_TEXT = "Hello World! Hello All! Hi World!";
        // Создаем Multiset
        MutableBag<String>  bag = HashBag.newBag(Arrays.asList(INPUT_TEXT.split(" ")));

        // Выводим кол-вом вхождений слов
        System.out.println(bag); // напечатает [Hi, World!, World!, Hello, Hello, All!]- в произвольном порядке
        // Выводим все уникальные слова
        System.out.println(bag.toSet());    // напечатает [Hi, Hello, World!, All!] - в произвольном порядке

        // Выводим количество по каждому слову
        System.out.println("Hello = " + bag.occurrencesOf("Hello"));    // напечатает 2
        System.out.println("World = " + bag.occurrencesOf("World!"));    // напечатает 2
        System.out.println("All = " + bag.occurrencesOf("All!"));    // напечатает 1
        System.out.println("Hi = " + bag.occurrencesOf("Hi"));    // напечатает 1
        System.out.println("Empty = " + bag.occurrencesOf("Empty"));    // напечатает 0

        // Выводим общее количества всех слов в тексте
        System.out.println(bag.size());    //напечатает 6

        // Выводим общее количество всех уникальных слов
        System.out.println(bag.toSet().size());    //напечатает 4
    }
}
