package com.github.vedenin.dagger.rus;


import dagger.Component;
import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;

/**
 * Простейший пример использования DI dagger
 *
 * Классу Notifier нужен класс реализующий интерфейс NotificationService,
 * чтобы отправить сообщение пользователю. Получим эту зависимость через DI
 *
 * Created by vvedenin on 11/14/2015.
 */
public class SimpleTest {
    public static class Notifier {
        private final NotificationService service;

        @Inject
        public Notifier(NotificationService service) {
            this.service = service;
        }

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

    @Module
    public static class TestModule {
        @Provides
        NotificationService provideNotificationService() {
            return new EMailService();
        }

        @Provides Notifier provideNotifier(NotificationService service) {
            return new Notifier(service);
        }
    }

    @Component(modules = TestModule.class)
    interface TestComponent {
        Notifier getNotifier();
    }

    public static void main(String[] args)  throws Exception {
        TestComponent testComponent = DaggerSimpleTest_TestComponent.create();
        Notifier notifier = testComponent.getNotifier();
        notifier.send("test email");  // Напечатает "I send email: test email"
    }
}
