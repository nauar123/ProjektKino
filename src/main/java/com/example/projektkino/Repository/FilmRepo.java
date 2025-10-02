package com.example.projektkino.Repository;

import com.example.projektkino.Entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepo  extends JpaRepository<Film,Integer>
{



}
