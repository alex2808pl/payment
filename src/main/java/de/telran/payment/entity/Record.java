// Реализовать Entity сущности для всех таблиц.
// Implement Entity entities for all tables.
        package de.telran.payment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.time.LocalDateTime;


@Entity
@Table(name="records") //записи
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id") //идентификатор
    private Long id;

    @Column(name="parent") //источник
    private Long parent;

    @Column(name="title", length = 100) //заголовок
    private String title;

    @Column(name="content") //содержание
    private String content;

    @ManyToOne
    @JoinColumn(name="creator", nullable=false) //автор
    private User creator;

    @Column(name="creation_time") //время создания
    private LocalDateTime creationTime;

    @Column(name="edited") //отредактировано
    private Boolean edited;


}
