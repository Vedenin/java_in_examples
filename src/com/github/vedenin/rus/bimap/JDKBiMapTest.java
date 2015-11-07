package com.github.vedenin.rus.bimap;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.HashMap;
import java.util.Map;

//  Attention: version with english comments in "eng" package
public class JDKBiMapTest {

    // Задача даны массивы польского-английского перевода, сделать коллекцию для перевода слова в двух напрявлениях
    public static void main(String[] args) {
        String[] englishWords = {"one", "two", "three","ball","snow"};
        String[] russianWords = {"jeden", "dwa", "trzy", "kula", "snieg"};

        // Создаем аналог BiMap
        Map<String, String> biMapKeys = new HashMap(englishWords.length);
        Map<String, String> biMapValues = new HashMap(russianWords.length);
        // создаем англо-польский словарь
        int i = 0;
        for(String englishWord: englishWords) {
            biMapKeys.put(englishWord, russianWords[i]);
            biMapValues.put(russianWords[i], englishWord);
            i++;
        }

        // Выводим кол-вом вхождений слов
        System.out.println(biMapKeys); // напечатает {ball=kula, two=dwa, three=trzy, snow=snieg, one=jeden}- в произвольном порядке
        // Выводим все уникальные слова
        System.out.println(biMapKeys.keySet());    // напечатает [ball, two, three, snow, one] - в произвольном порядке
        System.out.println(biMapValues.keySet());    // напечатает [dwa, trzy, kula, snieg, jeden] - в произвольном порядке

        // Выводим перевод по каждому слову
        System.out.println("one = " + biMapKeys.get("one"));    // напечатает one = jeden
        System.out.println("two = " + biMapKeys.get("two"));    // напечатает two = dwa
        System.out.println("kula = " + biMapValues.get("kula"));    // напечатает kula = ball
        System.out.println("snieg = " + biMapValues.get("snieg"));    // напечатает snieg = snow
        System.out.println("empty = " + biMapValues.get("empty"));    // напечатает empty = null

        // Выводим общее количество переводов в словаре
        System.out.println(biMapKeys.size());    //напечатает 5

    }
}
