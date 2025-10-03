package com.example.projektkino.Controller;

import com.example.projektkino.Entity.Film;
import com.example.projektkino.Repository.FilmRepo;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/film")
@CrossOrigin(origins = "*") // Det er denne der gør at frontend har adgang, ellers ville browseren bolkorer http request
public class FilmController {

    @Autowired
    private FilmRepo filmRepo;


    @GetMapping("/allFilm")
    public List<Film> getAllFilms() {
        return filmRepo.findAll();
    }

    @GetMapping("/{filmid}")
    public ResponseEntity<Film> getFilmById(@PathVariable int filmid) {
        Optional<Film> film = filmRepo.findById(filmid);
        return film.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/addFilm")
    public ResponseEntity<Film> createFilm(@RequestBody Film film)  // spring læser JSON fra requstens body og forbinder til et film objekt

    {
        Film savedFilm = filmRepo.save(film); // gemmes i databasen
        return new ResponseEntity<>(savedFilm, HttpStatus.CREATED); // ResponseEntity giver mulighed for styring af http status og body
        // (201 Created) er hvad HttpStatus.CREATED betyder. Det betyder noget nyt blev oprettet
    }



    @PutMapping("/{filmid}")
    public ResponseEntity<Film> updateFilm(@PathVariable int filmid, @RequestBody Film updatedFilm) {
        Optional<Film> existingFilmOpt = filmRepo.findById(filmid);

        if (!existingFilmOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Film existingFilm = existingFilmOpt.get();


        existingFilm.setTitel(updatedFilm.getTitel());
        existingFilm.setBeskrivelse(updatedFilm.getBeskrivelse());
        existingFilm.setSpilleTid(updatedFilm.getSpilleTid());
        existingFilm.setUdgivelsesAar(updatedFilm.getUdgivelsesAar());
        existingFilm.setAlderGraense(updatedFilm.getAlderGraense());

        Film savedFilm = filmRepo.save(existingFilm);

        return ResponseEntity.ok(savedFilm);
    }

    @DeleteMapping("/delete/{filmid}")
    public ResponseEntity<Void> deleteFilm(@PathVariable int filmid)
    {
        if (filmRepo.existsById(filmid)) // tjekker om filmen findes i databasen

        {
            filmRepo.deleteById(filmid); //sletter filmen med et bestemt ud, gennem JPA

            return ResponseEntity.noContent().build();// Returnerer en HTTP 204 No Content status. SLETTENINGEN SKER. men intet at returnere
        }
        return ResponseEntity.notFound().build(); // Hvis filmen ikke findes, returneres HTTP 404 Not Found.
    }
}
