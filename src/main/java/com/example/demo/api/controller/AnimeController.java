package com.example.demo.api.controller;
import com.example.demo.api.model.Anime;
import com.example.demo.service.AnimeService;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnimeController {

    private final AnimeService animeService;

    @Autowired
    public AnimeController(AnimeService userService){
        this.animeService = userService;
    }


    @GetMapping("/anime")
    public ResponseEntity<Anime> getUser(@RequestParam Integer id){
        Anime anime = animeService.getAnime(id).orElse(null);
        return ResponseEntity.ok(anime);
    }


    @PostMapping("/anime")
    public ResponseEntity<Anime> postUser(@RequestParam Integer id, @RequestParam String name, @RequestParam Integer age, @RequestParam String email){
        Anime anime = new Anime(id, name, age, email);
        animeService.createAnime(anime);
        return ResponseEntity.status(HttpStatus.CREATED).body(anime);
    }

    @PutMapping("/anime/{id}")
    public ResponseEntity<String> putUser(@PathVariable Integer id, @RequestBody Anime updatedUser) {
        Optional<Anime> existingUser = animeService.getAnime(id);

        if (existingUser.isPresent()) {
            Anime animeToUpdate = existingUser.get();
            animeToUpdate.setName(updatedUser.getName());
            animeToUpdate.setAge(updatedUser.getAge());
            animeToUpdate.setEmail(updatedUser.getEmail());

            animeService.updateAnime(animeToUpdate);
            return ResponseEntity.ok("User updated successfully !!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found !!");
        }
    }


    @DeleteMapping("/anime")
    public ResponseEntity<String> deleteUser(@RequestParam Integer id){
        boolean delete = animeService.deleteAnime(id);
        if (delete) {
            return ResponseEntity.ok("User deleted successfully !!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found !!");
        }
    }

}