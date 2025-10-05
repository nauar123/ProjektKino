
package com.example.projektkino.Controller;


import com.example.projektkino.Entity.Show;


import com.example.projektkino.Repository.ShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("show")
@CrossOrigin(origins = "*")
public class ShowController
{
    @Autowired
    ShowRepo showRepo; //hhhh


    @GetMapping("/AllShows")
    public List<Show> getAllShows()
    {
        return showRepo.findAll();
    }


    @GetMapping("/{showId}")
    public ResponseEntity<Show> getShowById(@PathVariable int showId)
    {
        Optional<Show> show = showRepo.findById(showId);
        return show.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/addShow")
    public ResponseEntity<Show> createShow(@RequestBody Show show)
    {
        Show savedShow = showRepo.save(show);
        return new ResponseEntity<>(savedShow, HttpStatus.CREATED);
    }


    @PutMapping("/{showId}")
    public ResponseEntity<Show> updateShow(@PathVariable("showId") int showId, @RequestBody Show show)
    {
        Optional<Show> existingShowOpt= showRepo.findById(showId);
        if (!existingShowOpt.isPresent())
        {
            return ResponseEntity.notFound().build();
        }


        Show existingShow = existingShowOpt.get();


        existingShow.setDato(show.getDato());
        existingShow.setTid(show.getTid());


        Show savedShow = showRepo.save(existingShow);
        return ResponseEntity.ok(savedShow);
    }


    @DeleteMapping("/delete/{showId}")
    public ResponseEntity<Void> deleteFilm(@PathVariable int showId) {
        if (showRepo.existsById(showId)) {
            showRepo.deleteById(showId);
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }




}
