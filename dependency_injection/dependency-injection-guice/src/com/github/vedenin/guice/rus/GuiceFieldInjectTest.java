package com.github.vedenin.guice.rus;


import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import javax.inject.Inject;

/**
 * Пример использования инъекции в приватное поле в DI guice
 *
 * Классу Notifier нужен класс реализующий интерфейс NotificationService,
 * чтобы отправить сообщение пользователю. Получим эту зависимость через
 * инъекцию в приватное поле
 *
 * Created by vvedenin on 11/14/2015.
 */
public class GuiceFieldInjectTest {
    public static class Notifier {
        @Inject
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


    public static class TestModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(NotificationService.class).to(EMailService.class);
        }
    }

    public static void main(String[] args)  throws Exception {
        Injector injector = Guice.createInjector(new TestModule());
        Notifier notifier = injector.getInstance(Notifier.class);
        notifier.send("test email"); // Print "I send email: test email"
    }
}
