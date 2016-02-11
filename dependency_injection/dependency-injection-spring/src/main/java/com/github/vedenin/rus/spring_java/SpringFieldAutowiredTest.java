package com.github.vedenin.rus.spring_java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Пример использования DI Spring инъекции в приватное поле,
 * используя аннотации Spring и конфигурацию через Java классы
 *
 * Классу Notifier нужен класс реализующий интерфейс NotificationService,
 * чтобы отправить сообщение пользователю. Получим эту зависимость через DI
 *
 * Created by vvedenin on 11/14/2015.
 */
public class SpringFieldAutowiredTest {
    public static class Notifier {
        @Autowired
        private NotificationService service;

        public void send(String message) {
            service.send("email: " + message);
        }
    }

    public static class EMailService implements NotificationService {
        public void send(String message) {
            System.out.println("I send " + message);
        }
    }

    public interface NotificationService {
        void send(String message);
    }

    @Configuration
    public static class DIConfiguration {
        @Bean
        public Notifier getNotifier(NotificationService service){
            return new Notifier();
        }

        @Bean
        public NotificationService getNotificationService(){
            return new EMailService();
        }
    }

    public static void main(String[] args)  throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
        Notifier notifier =  context.getBean(Notifier.class);
        notifier.send("test email"); // Print "I send email: test email"
    }
}
