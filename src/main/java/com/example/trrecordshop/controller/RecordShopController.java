package com.example.trrecordshop.controller;

import com.example.trrecordshop.model.Album;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RecordShopController {
    @GetMapping("/")
    public String welcome() {
        return "Welcome Page";
    }

    @GetMapping("/albums")
    public List<Album> getAllAlbums() {
        return null;
    }

    @GetMapping("/album")
    @ResponseBody
    public List<Album> getAlbumByCriteria(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "artist", required = false) String artist,
            @RequestParam(name = "releaseYear", required = false) String releaseYear,
            @RequestParam(name = "genre", required = false) String genre) {

        // if param is not null
        // implement logic to return response

        return null;
    }

}