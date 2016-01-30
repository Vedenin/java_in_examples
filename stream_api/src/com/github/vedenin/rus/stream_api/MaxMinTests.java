package com.github.vedenin.rus.stream_api;

import java.util.*;

/**
 *
 * Примеры работы методов Stream Api
 *
 * Created by vedenin on 17 .10.15.
 */
public class MaxMinTests {

    // Метод max вернет максимальный элемент, в качестве условия использует компаратор
    // Метод min вернет минимальный элемент, в качестве условия использует компаратор
    private static void testMinMax() {
        System.out.println();
        System.out.println("Test min and max start");
        // ************ Работа со строками
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");

        // найти максимальное значение
        String max = collection.stream().max(String::compareTo).get();
        System.out.println("max " + max); // напечатает a3

        // найти минимальное значение
        String min = collection.stream().min(String::compareTo).get();
        System.out.println("min " + min); // напечатает a1

        // ************ Работа со сложными объектами

        // Зададим коллекцию людей
        Collection<People> peoples = Arrays.asList(
                new People("Вася", 16, Sex.MAN),
                new People("Петя", 23, Sex.MAN),
                new People("Елена", 42, Sex.WOMEN),
                new People("Иван Иванович", 69, Sex.MAN)
        );

        // найти человека с максимальным возрастом
        People older = peoples.stream().max((p1, p2) -> p1.getAge().compareTo(p2.getAge())).get();
        System.out.println("older " + older); // напечатает {name='Иван Иванович', age=69, sex=MAN}

        // найти человека с минимальным возрастом
        People younger = peoples.stream().min((p1, p2) -> p1.getAge().compareTo(p2.getAge())).get();
        System.out.println("younger " + younger); // напечатает {name='Вася', age=16, sex=MAN}
    }


    private enum Sex {
        MAN,
        WOMEN
    }

    private static class People {
        private final String name;
        private final Integer age;
        private final Sex sex;

        public People(String name, Integer age, Sex sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        public Sex getSex() {
            return sex;
        }

        @Override
        public String toString() {
            return "{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", sex=" + sex +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof People)) return false;
            People people = (People) o;
            return Objects.equals(name, people.name) &&
                    Objects.equals(age, people.age) &&
                    Objects.equals(sex, people.sex);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age, sex);
        }
    }

    public static void main(String[] args)  throws Exception {
        testMinMax();
    }
}
