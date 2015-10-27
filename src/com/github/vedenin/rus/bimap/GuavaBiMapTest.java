package com.github.vedenin.rus.bimap;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class GuavaBiMapTest {

    // Задача даны массивы польского-английского перевода,сделать коллекцию для перевода слова в двух напрявлениях
    public static void main(String[] args) {
        String[] englishWords = {"one", "two", "three","ball","snow"};
        String[] russianWords = {"jeden", "dwa", "trzy", "kula", "snieg"};

        // Создаем Multiset
        BiMap<String, String> biMap = HashBiMap.create(englishWords.length);
        // создаем англо-польский словарь
        int i = 0;
        for(String englishWord: englishWords) {
            biMap.put(englishWord, russianWords[i]);
            i++;
        }

        // Выводим кол-вом вхождений слов
        System.out.println(biMap); // напечатает {two=dwa, three=trzy, snow=snieg, ball=kula, one=jeden} - в произвольном порядке
        // Выводим все уникальные слова
        System.out.println(biMap.keySet());    // напечатает [two, three, snow, ball, one] - в произвольном порядке
        System.out.println(biMap.values());    // напечатает [dwa, trzy, snieg, kula, jeden]- в произвольном порядке

        // Выводим перевод по каждому слову
        System.out.println("one = " + biMap.get("one"));    // напечатает one = jeden
        System.out.println("two = " + biMap.get("two"));    // напечатает two = dwa
        System.out.println("kula = " + biMap.inverse().get("kula"));    // напечатает kula = ball
        System.out.println("snieg = " + biMap.inverse().get("snieg"));    // напечатает snieg = snow
        System.out.println("empty = " + biMap.get("empty"));    // напечатает empty = null

        // Выводим общее количество переводов в словаре
        System.out.println(biMap.size());    //напечатает 5

    }
}
