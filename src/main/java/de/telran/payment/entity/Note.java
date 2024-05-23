// Реализовать Entity сущности для всех таблиц.
// Implement Entity entities for all tables.
        package de.telran.payment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="Notes") //запис
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="NoteId") //идентификатор
    private Long id;

    @Column(name="Parent") //источник
    private Long parent;

    @Column(name="Title", length = 100) //заголовок
    private String title;

    @Column(name="Content") //содержание
    private String content;

    @JoinColumn(name="Creator", nullable=false) //автор
    //private User creator;

    @Column(name="CreationTime") //время создания
    private LocalDateTime creationTime;

    @Column(name="Edited") //отредактировано
    private Boolean edited;

//    @OneToMany(mappedBy = "note", cascade = CascadeType.ALL)
//    private Set<Note> notes = new HashSet<>();
}
