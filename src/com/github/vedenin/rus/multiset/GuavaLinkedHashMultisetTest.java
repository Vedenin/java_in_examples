package com.github.vedenin.rus.multiset;

import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.Multiset;

import java.util.Arrays;

public class GuavaLinkedHashMultisetTest {

    public static void main(String[] args) {
        // Разберем текст на слова
        String INPUT_TEXT = "Hello World! Hello All! Hi World!";
        // Создаем Multiset
        Multiset<String> multiset = LinkedHashMultiset.create(Arrays.asList(INPUT_TEXT.split(" ")));

        // Выводим кол-вом вхождений слов
        System.out.println(multiset); // напечатает [Hello x 2, World! x 2, All!, Hi]- в порядке первого добавления элемента
        // Выводим все уникальные слова
        System.out.println(multiset.elementSet());    // напечатает [Hello, World!, All!, Hi] - в порядке первого добавления элемента

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
