package com.example.trrecordshop.service;

import com.example.trrecordshop.model.Album;
import com.example.trrecordshop.model.Genre;
import com.example.trrecordshop.repository.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
class RecordShopServiceTest {

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private RecordShopServiceImpl recordShopServiceImpl;

    @Test
    public void testGetAllAlbumsReturnsListOfAllAlbums() {
        List<Album> albumList = Arrays.asList(
                Album.builder().artist("Abba").title("The Album").releaseYear(1977).genre(Genre.POP).quantity(10).build(),
                Album.builder().artist("Tears For Fears").title("Songs From The Big Chair").releaseYear(1984).genre(Genre.POP).quantity(10).build(),
                Album.builder().artist("Tears For Fears").title("The Seeds Of Love").releaseYear(1989).genre(Genre.POP).quantity(10).build()
        );

        when(mockAlbumRepository.findAll()).thenReturn(albumList);

        List<Album> result = recordShopServiceImpl.getAllAlbums();

        assertThat(result).hasSize(3);
        assertThat(result).isEqualTo(albumList);
    }

    @Test
    public void testGetAlbumByTitleReturnsAlbum() {
        String targetTitle = "Rhythm Nation 1814";

        List<Album> albumList = Arrays.asList(
                Album.builder().artist("Abba").title("The Album").releaseYear(1977).genre(Genre.POP).quantity(10).build(),
                Album.builder().artist("Tears For Fears").title("The Seeds Of Love").releaseYear(1989).genre(Genre.POP).quantity(10).build(),
                Album.builder().artist("Janet Jackson").title("JRhythm Nation 1814").releaseYear(1989).genre(Genre.RNB).quantity(7).build()
        );

        List<Album> expectedAlbumList = albumList.stream()
                .filter(album -> album.getTitle().equals(targetTitle))
                .toList();

        when(mockAlbumRepository.getAlbumsByTitleContainsIgnoreCase(targetTitle)).thenReturn(expectedAlbumList);

        List<Album> result = recordShopServiceImpl.getAlbumsByTitle(targetTitle);

        assertThat(result).isEqualTo(expectedAlbumList);
    }

    @Test
    public void testGetAlbumsByArtistReturnsListOfAlbumsByArtist() {
        String targetArtist = "Tears For Fears";

        List<Album> albumList = Arrays.asList(
                Album.builder().artist("Abba").title("The Album").releaseYear(1977).genre(Genre.POP).quantity(10).build(),
                Album.builder().artist(targetArtist).title("Songs From The Big Chair").releaseYear(1984).genre(Genre.POP).quantity(10).build(),
                Album.builder().artist(targetArtist).title("The Seeds Of Love").releaseYear(1989).genre(Genre.POP).quantity(10).build()
        );

        when(mockAlbumRepository.getAlbumsByArtistContainsIgnoreCase(targetArtist)).thenReturn(albumList);

        List<Album> result = recordShopServiceImpl.getAlbumsByArtist(targetArtist);

        assertThat(result).isEqualTo(albumList);
    }

    @Test
    public void testGetAlbumsByReleaseYearReturnsListOfAlbumsByReleaseYear() {
        int targetReleaseYear = 1989;

        List<Album> albumList = Arrays.asList(
                Album.builder().artist("Abba").title("The Album").releaseYear(1977).genre(Genre.POP).quantity(10).build(),
                Album.builder().artist("Tears For Fears").title("The Seeds Of Love").releaseYear(1989).genre(Genre.POP).quantity(10).build(),
                Album.builder().artist("Janet Jackson").title("JRhythm Nation 1814").releaseYear(1989).genre(Genre.RNB).quantity(7).build()
        );

        List<Album> expectedAlbumList = albumList.stream()
                .filter(album -> album.getReleaseYear() == 1989)
                .toList();

        when(mockAlbumRepository.getAlbumsByReleaseYear(targetReleaseYear)).thenReturn(expectedAlbumList);

        List<Album> result = recordShopServiceImpl.getAlbumsByReleaseYear(targetReleaseYear);

        assertThat(result).isEqualTo(expectedAlbumList);

    }

    @Test
    public void testGetAlbumByGenreReturnsListOfAlbumsByGenre() {
        Genre targetGenre = Genre.OTHER;
        String targetGenreString = targetGenre.toString();

        List<Album> albumList = new ArrayList<>();
        albumList.add(new Album());
        albumList.add(new Album());
        albumList.add(new Album());

        when(mockAlbumRepository.getAlbumsByGenre(targetGenre)).thenReturn(albumList);

        List<Album> result = recordShopServiceImpl.getAlbumsByGenre(targetGenre);

        assertThat(result).isEqualTo(albumList);

    }
}