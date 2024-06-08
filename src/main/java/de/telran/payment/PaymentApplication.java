//
//04.06.2024. Для всех сущностей вашего проекта разработать mapper-ы для передачи информации между Entity в Dto и обратно.
//Переделать все методы, представленные в Service слое на преобразование данных через mapper
//28.05.2024 Написать тесты  на чтение, запись, вставку и удаление данных для всех Repositories  проекта домашних заданий
//21.05.2024. Реализовать Entity сущности для всех таблиц. Построить связи между таблицами.  Создать Repository для всех таблиц. Подключить Н2 базу данных и сгенерить на основе Entity-сущностей таблицы БД.
//23.05.2024. В проекте команды добавить Liquibase, сделать скрипты создания таблиц, связей в таблицах и заполнения таблиц предварительными тестовыми данными.

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
