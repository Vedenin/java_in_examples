package com.github.vedenin.rus.bimap;


import com.gs.collections.api.bimap.MutableBiMap;
import com.gs.collections.impl.bimap.mutable.HashBiMap;

//  Attention: version with english comments in "eng" package
public class GsHashBiMapTest {

    // Задача даны массивы польского-английского перевода, сделать коллекцию для перевода слова в двух напрявлениях
    public static void main(String[] args) {
        String[] englishWords = {"one", "two", "three","ball","snow"};
        String[] russianWords = {"jeden", "dwa", "trzy", "kula", "snieg"};

        // Создаем Multiset
        MutableBiMap<String, String> biMap = new HashBiMap(englishWords.length);
        // создаем англо-польский словарь
        int i = 0;
        for(String englishWord: englishWords) {
            biMap.put(englishWord, russianWords[i]);
            i++;
        }

        // Выводим кол-вом вхождений слов
        System.out.println(biMap); // напечатает {two=dwa, ball=kula, one=jeden, snow=snieg, three=trzy} - в произвольном порядке
        // Выводим все уникальные слова
        System.out.println(biMap.keySet());    // напечатает [snow, two, one, three, ball] - в произвольном порядке
        System.out.println(biMap.values());    // напечатает [dwa, kula, jeden, snieg, trzy] - в произвольном порядке

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
