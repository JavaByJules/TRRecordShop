package com.example.trrecordshop.service;

import com.example.trrecordshop.model.Album;
import com.example.trrecordshop.model.Genre;
import com.example.trrecordshop.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordShopServiceImpl  implements  RecordShopService {

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public List<Album> getAllAlbums() {
        List <Album> albums = new ArrayList<>();
        albumRepository.findAll().forEach(albums::add);

        return albums.stream()
                .filter(album -> album.getQuantity() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Album> getAlbumsByArtist(String artist) {
        List <Album> albums = new ArrayList<>();
        albumRepository.getAlbumsByArtistContainsIgnoreCase(artist).forEach(albums::add);
        return albums;
    }

    @Override
    public List<Album> getAlbumsByReleaseYear(int releaseYear) {
        return new ArrayList<>(albumRepository.getAlbumsByReleaseYear(releaseYear));
    }

    @Override
    public List<Album> getAlbumsByGenre(Genre genre) {
        return new ArrayList<>(albumRepository.getAlbumsByGenre(genre));
    }

    public List<Album> getAlbumsByTitle(String title) {
        return new ArrayList<>(albumRepository.getAlbumsByTitleContainsIgnoreCase(title));
    }

    @Override
    public Album insertAlbum(Album album) {
        return albumRepository.save( album);
    }

    @Override
    public boolean updateAlbum(Album album) {

        if (albumRepository.findById(album.getId()).isPresent()) {
            Album retrievedAlbum = albumRepository.findById(album.getId()).get();

            retrievedAlbum.setTitle(album.getTitle());
            retrievedAlbum.setGenre(album.getGenre());
            retrievedAlbum.setArtist(album.getArtist());
            retrievedAlbum.setReleaseYear(album.getReleaseYear());
            albumRepository.save(retrievedAlbum);
            return true;
        }
        return false;
    }

    @Override
    public int updateStock(Long id, int amount, boolean incrementing) {

        if (albumRepository.findById(id).isPresent()) {
            Album retrievedAlbum = albumRepository.findById(id).get();

            retrievedAlbum.setQuantity(retrievedAlbum.getQuantity() +
                    (incrementing ? amount : -amount));

            albumRepository.save(retrievedAlbum);
            return retrievedAlbum.getQuantity();
        }
        return 0;
    }

    @Override
    public boolean deleteByAlbumId(Long id) {
        if (albumRepository.findById(id).isPresent()) {
            albumRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
