package com.example.trrecordshop.repository;

import com.example.trrecordshop.model.Album;
import com.example.trrecordshop.model.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AlbumRepositoryTests {
    @Autowired
    private AlbumRepository albumRepository;

    @Test
    public void testEnumPreservation() {

        Album album = new Album(1L, "artist", "title", 1995, Genre.HIPHOP, 0 );
        albumRepository.save(album);

        Album savedAlbum =  albumRepository.findById(1L).get();
        assertThat(savedAlbum.getGenre()).isEqualTo(Genre.HIPHOP);

    }

    @Test
    public void testGetAllAlbums() {

        Album album1 = new Album(1L, "artist1", "title1", 1995, Genre.HIPHOP, 0 );
        Album album2 = new Album(2L, "artist2", "title2", 1996, Genre.BLUES, 1 );
        Album album3 = new Album(3L, "artist3", "title3", 1997, Genre.CLASSICAL, 2 );

        albumRepository.save(album1);
        albumRepository.save(album2);
        albumRepository.save(album3);

        Iterable<Album> albums = albumRepository.findAll();
        assertThat (albums).hasSize(3);
        assertThat(albumRepository.count()).isEqualTo(3);
    }


    @Test
    public void testGetAlbumsByArtist() {
        Album album1 = new Album(1L, "artist1", "title1", 1995, Genre.HIPHOP, 0 );
        Album album2 = new Album(2L, "artist2", "title2", 1996, Genre.BLUES, 1 );
        Album album3 = new Album(3L, "artist1", "title3", 1997, Genre.CLASSICAL, 2 );

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
        Album album1 = new Album(1L, "artist1", "title1", 1995, Genre.HIPHOP, 0 );
        Album album2 = new Album(2L, "artist2", "title2", 1996, Genre.BLUES, 1 );
        Album album3 = new Album(3L, "artist3", "title3", 1995, Genre.CLASSICAL, 2 );

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
        Album album1 = new Album(1L, "artist1", "title1", 1995, Genre.HIPHOP, 0 );
        Album album2 = new Album(2L, "artist2", "title2", 1996, Genre.BLUES, 1 );
        Album album3 = new Album(3L, "artist3", "title3", 1995, Genre.HIPHOP, 2 );

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
    public void tesAlbumByAlbumName() {
        Album album1 = new Album(1L, "artist1", "title1", 1995, Genre.HIPHOP, 0 );
        Album album2 = new Album(2L, "artist2", "title2", 1996, Genre.BLUES, 1 );
        Album album3 = new Album(3L, "artist3", "title3", 1995, Genre.HIPHOP, 2 );

        albumRepository.save(album1);
        albumRepository.save(album2);
        albumRepository.save(album3);

        String titleToFind = "title2";
        Album albumByTitle = albumRepository.getAlbumByTitleContainsIgnoreCase(titleToFind);
        assertThat(albumByTitle).isNotNull();
        assertThat(albumByTitle.getTitle()).isEqualTo(titleToFind);

        String titleToFindUC = "LE2";
        Album albumByTitleUC = albumRepository.getAlbumByTitleContainsIgnoreCase(titleToFindUC);
        assertThat(albumByTitle).isNotNull();
        assertThat(albumByTitle.getTitle()).isEqualTo(titleToFind);


        String titleToFindNone = "title4";
        Album albumByTitleNone = albumRepository.getAlbumByTitleContainsIgnoreCase(titleToFindNone);
        assertThat(albumByTitleNone).isNull();

    }

    @Test
    public void testInsertAlbum() {
        Album album1 = new Album(1L, "artist1", "title1", 1995, Genre.HIPHOP, 0 );
        Album album2 = new Album(2L, "artist2", "title2", 1996, Genre.BLUES, 1 );
        Album album3 = new Album(3L, "artist3", "title3", 1995, Genre.HIPHOP, 2 );

        albumRepository.save(album1);
        albumRepository.save(album2);
        albumRepository.save(album3);
        assertThat(albumRepository.count()).isEqualTo(3);
    }



    @Test
    public void testUpdateStock() {
        Album album1 = new Album(1L, "artist1", "title1", 1995, Genre.HIPHOP, 0 );
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
        Album album1 = new Album(1L, "artist1", "title1", 1995, Genre.HIPHOP, 0 );
        Album album2 = new Album(2L, "artist2", "title2", 1996, Genre.BLUES, 1 );
        Album album3 = new Album(3L, "artist3", "title3", 1995, Genre.HIPHOP, 2 );

        albumRepository.save(album1);
        albumRepository.save(album2);
        albumRepository.save(album3);

        albumRepository.deleteById(3L);

        assertThat(albumRepository.count()).isEqualTo(2);
        assertThat(albumRepository.findById(3L).isPresent()).isEqualTo(false);
    }



}
