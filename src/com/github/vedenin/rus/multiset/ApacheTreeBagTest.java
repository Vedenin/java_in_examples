package com.github.vedenin.rus.multiset;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.TreeBag;

import java.util.Arrays;

//  Attention: version with english comments in "eng" package
public class ApacheTreeBagTest {

    public static void main(String[] args) {
        // Разберем текст на слова
        String INPUT_TEXT = "Hello World! Hello All! Hi World!";
        // Создаем Multiset
        Bag bag = new TreeBag(Arrays.asList(INPUT_TEXT.split(" ")));

        // Выводим кол-вом вхождений слов
        System.out.println(bag); // напечатает [1:All!,2:Hello,1:Hi,2:World!]- в алфавитном порядке
        // Выводим все уникальные слова
        System.out.println(bag.uniqueSet());    // напечатает [All!, Hello, Hi, World!]- в алфавитном порядке

        // Выводим количество по каждому слову
        System.out.println("Hello = " + bag.getCount("Hello"));    // напечатает 2
        System.out.println("World = " + bag.getCount("World!"));    // напечатает 2
        System.out.println("All = " + bag.getCount("All!"));    // напечатает 1
        System.out.println("Hi = " + bag.getCount("Hi"));    // напечатает 1
        System.out.println("Empty = " + bag.getCount("Empty"));    // напечатает 0

        // Выводим общее количества всех слов в тексте
        System.out.println(bag.size());    //напечатает 6

        // Выводим общее количество всех уникальных слов
        System.out.println(bag.uniqueSet().size());    //напечатает 4
    }
}
