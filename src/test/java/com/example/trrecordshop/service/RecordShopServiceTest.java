package com.example.trrecordshop.service;

import com.example.trrecordshop.model.Album;
import com.example.trrecordshop.model.Genre;
import com.example.trrecordshop.repository.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
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
        List<Album> expected = new ArrayList<>();
        expected.add(new Album());
        expected.add(new Album());
        expected.add(new Album());

        when(mockAlbumRepository.findAll()).thenReturn(expected);

        List<Album> result = recordShopServiceImpl.getAllAlbums();

        assertThat(result).hasSize(3);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testGetAlbumsByTitleReturnsListOfAlbumsByTitle() {
        String targetTitle = "Title";

        List<Album> expected = new ArrayList<>();
        // add albums with the same title
        expected.add(new Album());
        expected.add(new Album());
        expected.add(new Album());

        when(mockAlbumRepository.findByTitle(expected)).thenReturn(expected);

        List<Album> result = recordShopServiceImpl.getAlbumsByTitle(targetTitle);

        assertThat(result).isEqualTo(expected);

    }

    @Test
    public void testGetAlbumsByArtistReturnsListOfAlbumsByArtist() {
        String targetArtist = "Artist";

        List<Album> expected = new ArrayList<>();
        // add albums with the same artist
        expected.add(new Album());
        expected.add(new Album());
        expected.add(new Album());

        when(mockAlbumRepository.findByArtist(expected)).thenReturn(expected);

        List<Album> result = recordShopServiceImpl.getAlbumsByArtist(targetArtist);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testGetAlbumsByReleaseYearReturnsListOfAlbumsByReleaseYear() {
        int releaseYear = 2020;

        List<Album> expected = new ArrayList<>();
        // add albums with the same release year
        expected.add(new Album());
        expected.add(new Album());
        expected.add(new Album());

        when(mockAlbumRepository.findByArtist(expected)).thenReturn(expected);

        List<Album> result = recordShopServiceImpl.getAlbumsByArtist(targetArtist);

        assertThat(result).isEqualTo(expected);

    }

    @Test
    public void testGetAlbumByGenreReturnsListOfAlbumsByGenre() {
        Genre targetGenre = Genre.OTHER;

        List<Album> expected = new ArrayList<>();
        // add albums with the same genre
        expected.add(new Album());
        expected.add(new Album());
        expected.add(new Album());

        when(mockAlbumRepository.findByGenre(expected)).thenReturn(expected);

        List<Album> result = recordShopServiceImpl.getAlbumsByGenre(targetGenre);

        assertThat(result).isEqualTo(expected);

    }
}