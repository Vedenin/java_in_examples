package com.github.vedenin.rus.multiset;

import com.gs.collections.api.bag.sorted.MutableSortedBag;
import com.gs.collections.impl.bag.sorted.mutable.TreeBag;

import java.util.Arrays;

//  Attention: version with english comments in "eng" package
public class GsMutableSortedBagTest {

    public static void main(String[] args) {
        // Разберем текст на слова
        String INPUT_TEXT = "Hello World! Hello All! Hi World!";
        // Создаем Multiset
        MutableSortedBag<String> bag =  TreeBag.newBag(Arrays.asList(INPUT_TEXT.split(" ")));

        // Выводим кол-вом вхождений слов
        System.out.println(bag); // напечатает [All!, Hello, Hello, Hi, World!, World!]- в натуральном порядке
        // Выводим все уникальные слова
        System.out.println(bag.toSortedSet());    // напечатает [All!, Hello, Hi, World!]- в натуральном порядке

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
