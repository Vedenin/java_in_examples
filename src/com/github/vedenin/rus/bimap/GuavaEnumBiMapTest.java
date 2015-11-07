package com.github.vedenin.rus.bimap;

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumBiMap;
import com.google.common.collect.HashBiMap;

//  Attention: version with english comments in "eng" package
public class GuavaEnumBiMapTest {
    enum ENGLISH_WORD {
        ONE, TWO, THREE, BALL, SNOW
    }
    enum POLISH_WORD {
        JEDEN, DWA, TRZY, KULA, SNIEG
    }
    // Задача даны массивы польского-английского перевода, сделать коллекцию для перевода слова в двух напрявлениях
    public static void main(String[] args) {
        ENGLISH_WORD[] englishWords = ENGLISH_WORD.values();
        POLISH_WORD[] polishWords = POLISH_WORD.values();

        // Создаем Multiset
        BiMap<ENGLISH_WORD, POLISH_WORD> biMap = EnumBiMap.create(ENGLISH_WORD.class, POLISH_WORD.class);
        // создаем англо-польский словарь
        int i = 0;
        for(ENGLISH_WORD englishWord: englishWords) {
            biMap.put(englishWord, polishWords[i]);
            i++;
        }

        // Выводим кол-вом вхождений слов
        System.out.println(biMap); // напечатает {ONE=JEDEN, TWO=DWA, THREE=TRZY, BALL=KULA, SNOW=SNIEG}
        // Выводим все уникальные слова
        System.out.println(biMap.keySet());    // напечатает [ONE, TWO, THREE, BALL, SNOW]
        System.out.println(biMap.values());    // напечатает [JEDEN, DWA, TRZY, KULA, SNIEG]

        // Выводим перевод по каждому слову
        System.out.println("one = " + biMap.get(ENGLISH_WORD.ONE));    // напечатает one = JEDEN
        System.out.println("two = " + biMap.get(ENGLISH_WORD.TWO));    // напечатает two = DWA
        System.out.println("kula = " + biMap.inverse().get(POLISH_WORD.KULA));    // напечатает kula = BALL
        System.out.println("snieg = " + biMap.inverse().get(POLISH_WORD.SNIEG));    // напечатает snieg = SNOW
        System.out.println("empty = " + biMap.get("empty"));    // напечатает empty = null

        // Выводим общее количество переводов в словаре
        System.out.println(biMap.size());    //напечатает 5

    }
}
