package com.github.vedenin.rus.multiset;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bag.SynchronizedBag;

import java.util.Arrays;

//  Attention: version with english comments in "eng" package
public class ApacheSynchronizedBagTest {

    public static void main(String[] args) {
        // Разберем текст на слова
        String INPUT_TEXT = "Hello World! Hello All! Hi World!";
        // Создаем Multiset
        Bag bag = SynchronizedBag.synchronizedBag(new HashBag(Arrays.asList(INPUT_TEXT.split(" "))));

        // Выводим кол-вом вхождений слов
        System.out.println(bag); // напечатает [1:Hi,2:Hello,2:World!,1:All!] - в произвольном порядке
        // Выводим все уникальные слова
        System.out.println(bag.uniqueSet());    // напечатает [Hi, Hello, World!, All!] - в произвольном порядке

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
