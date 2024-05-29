//28.05.2023 Написать тесты  на чтение, запись, вставку и удаление данных для всех Repositories  проекта домашних заданий
//21.05.2023. Реализовать Entity сущности для всех таблиц. Построить связи между таблицами.  Создать Repository для всех таблиц. Подключить Н2 базу данных и сгенерить на основе Entity-сущностей таблицы БД.
//23.05.2023. В проекте команды добавить Liquibase, сделать скрипты создания таблиц, связей в таблицах и заполнения таблиц предварительными тестовыми данными.

package de.telran.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentApplication {

	public static void main(String[] args) {
		//Payment system for the store
		SpringApplication.run(PaymentApplication.class, args);
	}
}
