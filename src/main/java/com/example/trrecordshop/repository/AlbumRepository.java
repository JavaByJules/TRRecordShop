package com.example.trrecordshop.repository;

import com.example.trrecordshop.model.Album;
import com.example.trrecordshop.model.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {

    public List<Album> getAlbumsByArtistContainsIgnoreCase(String artist);

    public List<Album> getAlbumsByReleaseYear(int year);

    public List<Album> getAlbumsByGenre(Genre genre);

    public Album getAlbumByTitleContainsIgnoreCase (String title);

}