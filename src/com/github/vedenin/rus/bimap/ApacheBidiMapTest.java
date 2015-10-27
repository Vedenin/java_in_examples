package com.github.vedenin.rus.bimap;


import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

public class ApacheBidiMapTest {

    // Задача даны массивы польского-английского перевода, сделать коллекцию для перевода слова в двух напрявлениях
    public static void main(String[] args) {
        String[] englishWords = {"one", "two", "three","ball","snow"};
        String[] russianWords = {"jeden", "dwa", "trzy", "kula", "snieg"};

        // Создаем Multiset
        BidiMap<String, String> biMap = new DualHashBidiMap();
        // создаем англо-польский словарь
        int i = 0;
        for(String englishWord: englishWords) {
            biMap.put(englishWord, russianWords[i]);
            i++;
        }

        // Выводим кол-вом вхождений слов
        System.out.println(biMap); // напечатает {ball=kula, snow=snieg, one=jeden, two=dwa, three=trzy}- в произвольном порядке
        // Выводим все уникальные слова
        System.out.println(biMap.keySet());    // напечатает [ball, snow, one, two, three]- в произвольном порядке
        System.out.println(biMap.values());    // напечатает [kula, snieg, jeden, dwa, trzy]- в произвольном порядке

        // Выводим перевод по каждому слову
        System.out.println("one = " + biMap.get("one"));    // напечатает one = jeden
        System.out.println("two = " + biMap.get("two"));    // напечатает two = dwa
        System.out.println("kula = " + biMap.getKey("kula"));    // напечатает kula = ball
        System.out.println("snieg = " + biMap.getKey("snieg"));    // напечатает snieg = snow
        System.out.println("empty = " + biMap.get("empty"));    // напечатает empty = null

        // Выводим общее количество переводов в словаре
        System.out.println(biMap.size());    //напечатает 5

    }
}
