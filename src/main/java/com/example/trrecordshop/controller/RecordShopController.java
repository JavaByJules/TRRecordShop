package com.example.trrecordshop.controller;

import com.example.trrecordshop.model.Album;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/v1")
public class RecordShopController {
    @GetMapping("/")
    public String welcome() {
        return "Welcome Page";
    }

    @GetMapping("/albums")
    public List<Album> getAllAlbums() {
        return null;
    }

    @GetMapping("/albums")
    @ResponseBody
    public List<Album> getAlbumByTitle(@RequestParam(name = "title") String title) {
        return null;
    }

    @GetMapping("/albums")
    @ResponseBody
    public List<Album> getAlbumByArtist(@RequestParam(name = "artist") String artist) {
        return null;
    }

    @GetMapping("/albums")
    @ResponseBody
    public List<Album> getAlbumByReleaseYear(@RequestParam(name = "releaseYear") String releaseYear) {
        return null;
    }

    @GetMapping("/albums")
    @ResponseBody
    public List<Album> getAlbumByGenre(@RequestParam(name = "genre") String genre) {
        return null;
    }

}