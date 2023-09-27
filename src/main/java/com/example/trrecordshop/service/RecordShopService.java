package com.example.trrecordshop.service;

import com.example.trrecordshop.model.Album;
import com.example.trrecordshop.model.Genre;

import java.util.List;

public interface RecordShopService {
    List<Album> getAllAlbums();

    List<Album> getAlbumsByArtist (String artist);

    List <Album> getAlbumsByReleaseYear (String releaseYear);

    List <Album> getAlbumsByGenre (Genre genre);

    Album getAlbumByAlbumName (String albumName);

    Album insertAlbum (Album album);

    void updateAlbumById (Long id);

    int updateStock (Long id, int amount, boolean incrementing);

    boolean deleteByAlbumId (Long id);

}
