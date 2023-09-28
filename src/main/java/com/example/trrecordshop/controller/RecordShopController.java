package com.example.trrecordshop.controller;

import com.example.trrecordshop.model.Album;
import com.example.trrecordshop.model.Genre;
import com.example.trrecordshop.service.RecordShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RecordShopController {

    private final RecordShopService recordShopService;

    @Autowired
    public RecordShopController(RecordShopService recordShopService) {
        this.recordShopService = recordShopService;
    }

    @GetMapping("/")
    public String welcome() {
        return "Welcome to the TR Record Shop Backend";
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> albums = recordShopService.getAllAlbums();
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping("/album")
    @ResponseBody
    public ResponseEntity<List<Album>> getAlbumByCriteria(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "artist", required = false) String artist,
            @RequestParam(name = "releaseYear", required = false) String releaseYear,
            @RequestParam(name = "genre", required = false) String genre) {

        List<Album> albums = new ArrayList<>();

        if (title != null) {
            albums = recordShopService.getAlbumsByTitle(title);
        }

        if (artist != null) {
            albums = recordShopService.getAlbumsByArtist(artist);
        }

        if (releaseYear != null) {
            int releaseYearParsed = Integer.parseInt(releaseYear);
            albums = recordShopService.getAlbumsByReleaseYear(releaseYearParsed);
        }

        if (genre != null) {
            albums = recordShopService.getAlbumsByGenre(Genre.valueOf(genre));
        }

        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @PostMapping("/album")
    public ResponseEntity<Album> addAlbum(@RequestBody Album album) {
        Album newAlbum = recordShopService.insertAlbum(album);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create("/api/v1/album" + newAlbum.getId().toString()));

        return new ResponseEntity<>(newAlbum, httpHeaders, HttpStatus.CREATED);
    }

    @PatchMapping("/album/update")
    public ResponseEntity<Album> updateAlbum(@RequestBody Album updatedAlbum) {

        if (recordShopService.updateAlbum(updatedAlbum)) {
            return new ResponseEntity<>(updatedAlbum, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        }
    }

    @PatchMapping("/album/update/stock")
    public ResponseEntity<Album> updateStock(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "amount") int amount,
            @RequestParam(name = "increment") boolean increment) {
        recordShopService.updateStock(id, amount, increment);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/album/delete")
    public ResponseEntity<Album> deleteAlbum(@RequestParam(name = "id") Long id) {
        recordShopService.deleteByAlbumId(id);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}