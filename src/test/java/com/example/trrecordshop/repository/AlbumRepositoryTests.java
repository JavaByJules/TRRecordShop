package com.example.trrecordshop.repository;

import com.example.trrecordshop.model.Album;
import com.example.trrecordshop.model.Genre;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AlbumRepositoryTests {
    @Autowired
    private AlbumRepository albumRepository;

    @Test
    public void testEnumPreservation() {
        Album album = Album.builder().artist("artist").title("title").releaseYear(1995).genre(Genre.HIPHOP).quantity(0).build();

        albumRepository.save(album);

        Album savedAlbum =  albumRepository.findById(1L).get();
        assertThat(savedAlbum.getGenre()).isEqualTo(Genre.HIPHOP);
    }

    @Test
    public void testGetAllAlbums() {

        Album album1 = Album.builder().artist("artist1").title("title1").releaseYear(1995).genre(Genre.HIPHOP).quantity(0).build();
        Album album2 = Album.builder().artist("artist2").title("title2").releaseYear(1996).genre(Genre.BLUES).quantity(1).build();
        Album album3 = Album.builder().artist("artist3").title("title3").releaseYear(1997).genre(Genre.CLASSICAL).quantity(2).build();

        albumRepository.save(album1);
        albumRepository.save(album2);
        albumRepository.save(album3);

        List<Album> albums = (List<Album>) albumRepository.findAll();
        assertThat (albums).hasSize(3);
        assertThat(albumRepository.count()).isEqualTo(3);
    }


    @Test
    public void testGetAlbumsByArtist() {
        Album album1 = Album.builder().artist("artist1").title("title1").releaseYear(1995).genre(Genre.HIPHOP).quantity(0).build();
        Album album2 = Album.builder().artist("artist2").title("title2").releaseYear(1996).genre(Genre.BLUES).quantity(1).build();
        Album album3 = Album.builder().artist("artist1").title("title3").releaseYear(1997).genre(Genre.CLASSICAL).quantity(2).build();

        albumRepository.save(album1);
        albumRepository.save(album2);
        albumRepository.save(album3);

        List<Album> albumByartist2 = albumRepository.getAlbumsByArtistContainsIgnoreCase("artist2");
        assertThat(albumByartist2.size()).isEqualTo(1);

        List<Album> albumByartist1 = albumRepository.getAlbumsByArtistContainsIgnoreCase("artist1");
        assertThat(albumByartist1.size()).isEqualTo(2);


        List<Album> albumByartistPartial = albumRepository.getAlbumsByArtistContainsIgnoreCase("TIST2");
        assertThat(albumByartistPartial.size()).isEqualTo(1);
    }


    @Test
    public void testAlbumsByReleaseYear() {
        Album album1 = Album.builder().artist("artist1").title("title1").releaseYear(1995).genre(Genre.HIPHOP).quantity(0).build();
        Album album2 = Album.builder().artist("artist2").title("title2").releaseYear(1996).genre(Genre.BLUES).quantity(1).build();
        Album album3 = Album.builder().artist("artist3").title("title3").releaseYear(1995).genre(Genre.CLASSICAL).quantity(2).build();


        albumRepository.save(album1);
        albumRepository.save(album2);
        albumRepository.save(album3);

        List<Album> albumByYear1995 = albumRepository.getAlbumsByReleaseYear(1995);
        assertThat(albumByYear1995.size()).isEqualTo(2);

        List<Album> albumByYear1996 = albumRepository.getAlbumsByReleaseYear(1996);
        assertThat(albumByYear1996.size()).isEqualTo(1);

        List<Album> albumByYear1997 = albumRepository.getAlbumsByReleaseYear(1997);
        assertThat(albumByYear1997.size()).isEqualTo(0);

    }

    @Test
    public void testAlbumsByGenre() {
        Album album1 = Album.builder().artist("artist1").title("title1").releaseYear(1995).genre(Genre.HIPHOP).quantity(0).build();
        Album album2 = Album.builder().artist("artist2").title("title2").releaseYear(1996).genre(Genre.BLUES).quantity(1).build();
        Album album3 = Album.builder().artist("artist3").title("title3").releaseYear(1995).genre(Genre.HIPHOP).quantity(2).build();


        albumRepository.save(album1);
        albumRepository.save(album2);
        albumRepository.save(album3);

        List<Album> albumByGenre = albumRepository.getAlbumsByGenre(Genre.HIPHOP);
        assertThat(albumByGenre.size()).isEqualTo(2);

        List<Album> albumByGenreBlues = albumRepository.getAlbumsByGenre(Genre.BLUES);
        assertThat(albumByGenreBlues.size()).isEqualTo(1);

        List<Album> albumByGenreOther = albumRepository.getAlbumsByGenre(Genre.OTHER);
        assertThat(albumByGenreOther.size()).isEqualTo(0);
    }

    @Test
    public void testAlbumByAlbumName() {
        Album album1 = Album.builder().id(1L).artist("artist1").title("title1").releaseYear(1995).genre(Genre.HIPHOP).quantity(0).build();
        Album album2 = Album.builder().id(2L).artist("artist2").title("title2").releaseYear(1996).genre(Genre.BLUES).quantity(1).build();
        Album album3 = Album.builder().id(3L).artist("artist3").title("title3").releaseYear(1995).genre(Genre.HIPHOP).quantity(2).build();

        albumRepository.save(album1);
        albumRepository.save(album2);
        albumRepository.save(album3);

        String titleToFind = "title2";
        List<Album> albumByTitle = albumRepository.getAlbumsByTitleContainsIgnoreCase(titleToFind);
        assertThat(albumByTitle).isNotNull();
        assertThat(albumByTitle.get(0).getTitle()).isEqualTo(titleToFind);

        String titleToFindUC = "LE2";
        List<Album> albumByTitleUC = albumRepository.getAlbumsByTitleContainsIgnoreCase(titleToFindUC);
        assertThat(albumByTitle).isNotNull();
        assertThat(albumByTitle.get(0).getTitle()).isEqualTo(titleToFind);


        String titleToFindNone = "title4";
        List<Album> albumByTitleNone = albumRepository.getAlbumsByTitleContainsIgnoreCase(titleToFindNone);
        assertThat(albumByTitleNone).isEqualTo(new ArrayList<>());

    }

    @Test
    public void testInsertAlbum() {
        Album album1 = Album.builder().artist("artist1").title("title1").releaseYear(1995).genre(Genre.HIPHOP).quantity(0).build();
        Album album2 = Album.builder().artist("artist2").title("title2").releaseYear(1996).genre(Genre.BLUES).quantity(1).build();
        Album album3 = Album.builder().artist("artist3").title("title3").releaseYear(1995).genre(Genre.HIPHOP).quantity(2).build();

        albumRepository.save(album1);
        albumRepository.save(album2);
        albumRepository.save(album3);
        assertThat(albumRepository.count()).isEqualTo(3);
    }



    @Test
    public void testUpdateStock() {
        Album album1 = Album.builder().artist("artist1").title("title1").releaseYear(1995).genre(Genre.HIPHOP).quantity(0).build();
        albumRepository.save(album1);

        Album albumToChangeStock = albumRepository.findById(1L).get();
        int stockLevel = albumToChangeStock.getQuantity();
        assertThat(stockLevel).isEqualTo(0);

        albumToChangeStock.setQuantity(10);
        albumRepository.save(albumToChangeStock);

        assertThat(albumRepository.findById(1L).get().getQuantity()).isEqualTo(10);
    }

    @Test
    public void testDeleteAlbumById() {
        Album album1 = Album.builder().artist("artist1").title("title1").releaseYear(1995).genre(Genre.HIPHOP).quantity(0).build();
        Album album2 = Album.builder().artist("artist2").title("title2").releaseYear(1996).genre(Genre.BLUES).quantity(1).build();
        Album album3 = Album.builder().artist("artist3").title("title3").releaseYear(1995).genre(Genre.HIPHOP).quantity(2).build();


        albumRepository.save(album1);
        albumRepository.save(album2);
        albumRepository.save(album3);

        albumRepository.deleteById(3L);

        assertThat(albumRepository.count()).isEqualTo(2);
        assertThat(albumRepository.findById(3L).isPresent()).isEqualTo(false);
    }



}
