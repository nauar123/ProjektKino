package com.example.projektkino.Entity;


import jakarta.persistence.*;


import java.time.LocalTime;


@Entity
@Table(name = "film")
public class Film
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id") // Column her bruges da navnene ikke stemmer overens med hvad der står i databasen
    private int filmId;
    private String titel;
    private String beskrivelse;
    @Column(name= "spille_tid")
    private LocalTime spilleTid;
    @Column(name= "udgivelses_aar")
    private int udgivelsesAar;
    @Column(name= "alder_graense")
    private int alderGraense;


    public Film(){}


    public Film(String titel, String beskrivelse, LocalTime spilleTid, int udgivelsesAar, int alderGraense)
    {
        this.titel = titel;
        this.beskrivelse = beskrivelse;
        this.spilleTid = spilleTid;
        this.udgivelsesAar =udgivelsesAar;
        this.alderGraense= alderGraense;
    }


    public int getFilmId() {
        return filmId;
    }


    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }
    public String getTitel() {
        return titel;
    }


    public void setTitel(String titel) {
        this.titel = titel;
    }


    public String getBeskrivelse() {
        return beskrivelse;
    }


    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }


    public LocalTime getSpilleTid() {
        return spilleTid;
    }


    public void setSpilleTid(LocalTime spilleTid) {
        this.spilleTid = spilleTid;
    }


    public int getUdgivelsesAar() {
        return udgivelsesAar;
    }


    public void setUdgivelsesAar(int udgivelsesAar) {
        this.udgivelsesAar = udgivelsesAar;
    }


    public int getAlderGraense() {
        return alderGraense;
    }


    public void setAlderGraense(int alderGraense) {
        this.alderGraense = alderGraense;
    }
}
