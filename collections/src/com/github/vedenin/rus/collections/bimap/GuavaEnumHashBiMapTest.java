package com.github.vedenin.rus.collections.bimap;

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumHashBiMap;

//  Attention: version with english comments in "eng" package
public class GuavaEnumHashBiMapTest {
    enum ENGLISH_WORD {
        ONE, TWO, THREE, BALL, SNOW
    }
    // Задача даны массивы польского-английского перевода, сделать коллекцию для перевода слова в двух напрявлениях
    public static void main(String[] args) {
        ENGLISH_WORD[] englishWords = ENGLISH_WORD.values();
        String[] russianWords = {"jeden", "dwa", "trzy", "kula", "snieg"};

        // Создаем Multiset
        BiMap<ENGLISH_WORD, String> biMap = EnumHashBiMap.create(ENGLISH_WORD.class);
        // создаем англо-польский словарь
        int i = 0;
        for(ENGLISH_WORD englishWord: englishWords) {
            biMap.put(englishWord, russianWords[i]);
            i++;
        }

        // Выводим кол-вом вхождений слов
        System.out.println(biMap); // напечатает {ONE=один, TWO=dwa, THREE=trzy, BALL=kula, SNOW=snieg}
        // Выводим все уникальные слова
        System.out.println(biMap.keySet());    // напечатает [ONE, TWO, THREE, BALL, SNOW]
        System.out.println(biMap.values());    // напечатает [jeden, dwa, trzy, kula, snieg]

        // Выводим перевод по каждому слову
        System.out.println("one = " + biMap.get(ENGLISH_WORD.ONE));    // напечатает one = jeden
        System.out.println("two = " + biMap.get(ENGLISH_WORD.TWO));    // напечатает two = dwa
        System.out.println("kula = " + biMap.inverse().get("kula"));    // напечатает kula = BALL
        System.out.println("snieg = " + biMap.inverse().get("snieg"));    // напечатает snieg = SNOW
        System.out.println("empty = " + biMap.get("empty"));    // напечатает empty = null

        // Выводим общее количество переводов в словаре
        System.out.println(biMap.size());    //напечатает 5

    }
}
