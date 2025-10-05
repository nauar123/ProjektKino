package com.example.projektkino.Controller;

import com.example.projektkino.Entity.Booking;
import com.example.projektkino.Entity.Show;
import com.example.projektkino.Repository.BookingRepo;
import com.example.projektkino.Repository.ShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("booking")
@CrossOrigin(origins = "*")

public class BookingController {
    @Autowired
    BookingRepo bookingRepo;
    @Autowired
    ShowRepo showRepo;

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    // Hent booking efter ID
    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable int bookingId) {
        Optional<Booking> bookingOpt = bookingRepo.findById(bookingId);
        return bookingOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addBooking")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        // Tjek at show findes
        if (booking.getShow() == null || booking.getShow().getShowId() == 0) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Show> showOpt = showRepo.findById(booking.getShow().getShowId());
        if (showOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        booking.setShow(showOpt.get());
        Booking savedBooking = bookingRepo.save(booking);
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }

    // Opdater booking
    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable int bookingId, @RequestBody Booking updatedBooking) {
        Optional<Booking> existingBookingOpt = bookingRepo.findById(bookingId);

        if (!existingBookingOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Booking existingBooking = existingBookingOpt.get();
        existingBooking.setNavn(updatedBooking.getNavn());
        existingBooking.setTlf_nr(updatedBooking.getTlf_nr());
        existingBooking.setAntal(updatedBooking.getAntal());

        if (updatedBooking.getShow() != null && updatedBooking.getShow().getShowId() != 0) {
            Optional<Show> showOpt = showRepo.findById(updatedBooking.getShow().getShowId());
            showOpt.ifPresent(existingBooking::setShow);
        }

        Booking savedBooking = bookingRepo.save(existingBooking);
        return ResponseEntity.ok(savedBooking);
    }

    // delete
    @DeleteMapping("/delete/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable int bookingId) {
        if (bookingRepo.existsById(bookingId)) {
            bookingRepo.deleteById(bookingId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}






