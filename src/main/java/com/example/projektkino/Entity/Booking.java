package com.example.projektkino.Entity;

import jakarta.persistence.*;
import jakarta.persistence.JoinColumn;

import java.time.LocalTime;

@Entity
@Table(name ="booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "booking_id")
    private int bookingId;


    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    private String navn;
    private int tlf_nr;
    private int antal;


    public int getBookingId()
    {
        return bookingId;
    }

    public void setBookingId(int bookingId)
    {
        this.bookingId = bookingId;
    }

    public Show getShow()
    {
        return show;
    }

    public String getNavn()
    {
        return navn;
    }

    public void setNavn(String navn)
    {
        this.navn = navn;
    }

    public int getTlf_nr()
    {
        return tlf_nr;
    }

    public void setTlf_nr(int tlf_nr)

    {
        this.tlf_nr = tlf_nr;
    }

    public int getAntal()
    {
        return antal;
    }

    public void setAntal(int antal)
    {
        this.antal = antal;
    }


    public void setShow(Show show)
    {
        this.show = show;
    }
    public String getFilmTitel()
    {
        return show != null && show.getFilm() != null ? show.getFilm().getTitel() : null;
    }

    public LocalTime getFilmSpilletid()
    {
        return show != null && show.getFilm() != null ? show.getFilm().getSpilleTid() : null;
    }
    
}
