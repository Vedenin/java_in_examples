package com.github.vedenin.eng.springcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;

/**
 *
 * Created by vvedenin on 11/14/2015.
 */
public class SpringSingletonBeanScopeTest {

    @Service
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public static class SingletonClass1 {
        private int value = 0;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    @Service
    @Scope("singleton")
    public static class SingletonClass2 {
        private int value = 0;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    @Service
    @Singleton
    public static class SingletonClass3 {
        private int value = 0;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    @Service
    public static class BeanForTestScope1 {
        @Autowired
        private SingletonClass1 singletonClass1;
        @Autowired
        private SingletonClass2 singletonClass2;
        @Autowired
        private SingletonClass3 singletonClass3;

        public void setValue(int value) {
            singletonClass1.setValue(value);
            singletonClass2.setValue(value);
            singletonClass3.setValue(value);
        }

        public void printValue() {
            System.out.println("Singleton");
            System.out.println(singletonClass1.getValue());
            System.out.println(singletonClass2.getValue());
            System.out.println(singletonClass3.getValue());
        }
    }


    @Service
    public static class BeanForTestScope2 {
        @Autowired
        private SingletonClass1 singletonClass1;
        @Autowired
        private SingletonClass2 singletonClass2;
        @Autowired
        private SingletonClass3 singletonClass3;

        public void setValue(int value) {
            singletonClass1.setValue(value);
            singletonClass2.setValue(value);
            singletonClass3.setValue(value);
        }

        public void printValue() {
            System.out.println("Singleton");
            System.out.println(singletonClass1.getValue());
            System.out.println(singletonClass2.getValue());
            System.out.println(singletonClass3.getValue());
        }
    }

    @Configuration
    public static class DIConfiguration {
        @Bean
        public BeanForTestScope1 getBeanForTestScope1(){
            return new BeanForTestScope1();
        }

        @Bean
        public BeanForTestScope2 getBeanForTestScope2(){
            return new BeanForTestScope2();
        }

        @Bean
        public SingletonClass1 getSingletonClass1(){
            return new SingletonClass1();
        }

        @Bean
        public SingletonClass2 getSingletonClass2(){
            return new SingletonClass2();
        }

        @Bean
        public SingletonClass3 getSingletonClass3(){
            return new SingletonClass3();
        }
    }

    public static void main(String[] args)  throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
        BeanForTestScope1 beanForTestScope1 =  context.getBean(BeanForTestScope1.class);
        BeanForTestScope2 beanForTestScope2 =  context.getBean(BeanForTestScope2.class);

        beanForTestScope1.setValue(1);
        beanForTestScope2.setValue(2);
        beanForTestScope1.printValue();
        beanForTestScope1.setValue(4);
        beanForTestScope2.printValue();
    }
}
