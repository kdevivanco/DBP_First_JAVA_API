package com.example.demo.service;

import com.example.demo.api.model.Anime;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AnimeService {

    private List<Anime> animeList;

    public AnimeService() {
        animeList = new ArrayList<>();

        Anime user1 = new Anime(1,"Ida", 32, "ida@mail.com");
        Anime user2 = new Anime(2,"Hans", 26, "hans@mail.com");
        Anime user3 = new Anime(3,"Lars", 45, "lars@mail.com");
        Anime user4 = new Anime(4,"Ben", 32, "ben@mail.com");
        Anime user5 = new Anime(5,"Eva", 59, "eva@mail.com");

        animeList.addAll(Arrays.asList(user1,user2,user3,user4,user5));
    }

    public Optional<Anime> getAnime(Integer id) {
        Optional<Anime> optional = Optional.empty();
        for (Anime anime: animeList) {
            if(id == anime.getId()){
                System.out.print("User found: " + anime.getName() + "\n");
                optional = Optional.of(anime);
                return optional;
            }
        }
        return optional;
    }

    public Boolean deleteAnime(Integer id){
        for (Anime anime: animeList) {
            if(id == anime.getId()){
                System.out.print("User found: " + anime.getName() + "\n");
                animeList.remove(anime);
                return true;
            }
        }
        return false;
    }

    public void createAnime(Anime user){
        animeList.add(user);
    }

    public void updateAnime(Anime user){
        for (Anime u: animeList) {
            if(user.getId() == u.getId()){
                System.out.print("User found: " + u.getName() + "\n");
                u.setName(user.getName());
                u.setAge(user.getAge());
                u.setEmail(user.getEmail());
            }
        }
    }
}
