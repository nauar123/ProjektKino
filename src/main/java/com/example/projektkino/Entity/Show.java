package com.example.projektkino.Entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name ="show")

public class Show {//kk

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name ="showfilm_id")
    private int showId;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    private LocalDate dato;
    private LocalTime tid;

    public int getShowId() { return showId; }
    public void setShowId(int showId) { this.showId = showId; }

    public Film getFilm() { return film; }
    public void setFilm(Film film) { this.film = film; }

    public LocalDate getDato() { return dato; }
    public void setDato(LocalDate dato) { this.dato = dato; }

    public LocalTime getTid() { return tid; }
    public void setTid(LocalTime tid) { this.tid = tid; }
}


