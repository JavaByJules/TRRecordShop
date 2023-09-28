package com.example.trrecordshop.controller;

import com.example.trrecordshop.model.Album;
import com.example.trrecordshop.model.Genre;
import com.example.trrecordshop.service.RecordShopServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
class RecordShopControllerTests {

    @Mock
    private RecordShopServiceImpl mockRecordShopServiceImpl;

    @InjectMocks
    private RecordShopController recordShopController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(recordShopController).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void testWelcomeReturnsMessage() throws Exception {
        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/api/v1/"))
                .andExpect(MockMvcResultMatchers.content().string("Welcome to the TR Record Shop Backend"));
    }

    @Test
    public void testGetAllAlbumsReturnsAllAlbums() throws Exception {
        List<Album> albumList = Arrays.asList(
                Album.builder().artist("Abba").title("The Album").releaseYear(1977).genre(Genre.POP).quantity(10).build(),
                Album.builder().artist("Tears For Fears").title("The Seeds Of Love").releaseYear(1989).genre(Genre.POP).quantity(10).build(),
                Album.builder().artist("Janet Jackson").title("Rhythm Nation 1814").releaseYear(1989).genre(Genre.RNB).quantity(7).build()
        );

        when(mockRecordShopServiceImpl.getAllAlbums()).thenReturn(albumList);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/albums"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].artist").value("Abba"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("The Album"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].releaseYear").value(1977))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].artist").value("Tears For Fears"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("The Seeds Of Love"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].releaseYear").value(1989))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].artist").value("Janet Jackson"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].title").value("Rhythm Nation 1814"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].releaseYear").value(1989));

    }

    @Test
    public void testGetAlbumsByTitleReturnsMatchedAlbums() throws Exception {
        String targetTitle = "Rhythm Nation 1814";

        List<Album> albumList = Arrays.asList(
                Album.builder().artist("Abba").title("The Album").releaseYear(1977).genre(Genre.POP).quantity(10).build(),
                Album.builder().artist("Tears For Fears").title("The Seeds Of Love").releaseYear(1989).genre(Genre.POP).quantity(10).build(),
                Album.builder().artist("Janet Jackson").title(targetTitle).releaseYear(1989).genre(Genre.RNB).quantity(7).build()
        );

        List<Album> expectedAlbumList = albumList.stream()
                .filter(album -> album.getTitle().equals(targetTitle))
                .collect(Collectors.toList());

        when(mockRecordShopServiceImpl.getAlbumsByTitle(targetTitle)).thenReturn(expectedAlbumList);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/album?title=" + targetTitle))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(targetTitle));
    }

    @Test
    public void testGetAlbumsByArtistReturnsAlbums() throws Exception {
        String targetArtist = "Tears For Fears";

        List<Album> albumList = Arrays.asList(
                Album.builder().artist("Abba").title("The Album").releaseYear(1977).genre(Genre.POP).quantity(10).build(),
                Album.builder().artist("Tears For Fears").title("Songs From The Big Chair").releaseYear(1984).genre(Genre.POP).quantity(10).build(),
                Album.builder().artist("Tears For Fears").title("The Seeds Of Love").releaseYear(1989).genre(Genre.POP).quantity(10).build()
        );

        List<Album> expectedAlbumList = albumList.stream()
                .filter(album -> album.getArtist().equals(targetArtist))
                .collect(Collectors.toList());

        when(mockRecordShopServiceImpl.getAlbumsByArtist(targetArtist)).thenReturn(expectedAlbumList);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/api/v1/album?artist=" + targetArtist))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].artist").value(targetArtist))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].artist").value(targetArtist));
    }

    @Test
    public void testGetAlbumsByReleaseYearReturnsAlbums() throws Exception {
        int targetReleaseYear = 1989;

        List<Album> albumList = Arrays.asList(
                Album.builder().artist("Abba").title("The Album").releaseYear(1977).genre(Genre.POP).quantity(10).build(),
                Album.builder().artist("Tears For Fears").title("The Seeds Of Love").releaseYear(targetReleaseYear).genre(Genre.POP).quantity(10).build(),
                Album.builder().artist("Janet Jackson").title("Rhythm Nation 1814").releaseYear(targetReleaseYear).genre(Genre.RNB).quantity(7).build()
        );

        List<Album> expectedAlbumList = albumList.stream()
                        .filter(album -> album.getReleaseYear() == targetReleaseYear)
                                .collect(Collectors.toList());

        when(mockRecordShopServiceImpl.getAlbumsByReleaseYear(targetReleaseYear)).thenReturn(expectedAlbumList);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/album?releaseYear=" + targetReleaseYear))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].releaseYear").value(targetReleaseYear))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].releaseYear").value(targetReleaseYear));
    }

    @Test
    public void testGetAlbumsByGenreReturnsAlbums() throws Exception {
        Genre targetGenre = Genre.POP;
        String targetGenreString = targetGenre.toString();

        List<Album> albumList = Arrays.asList(
                Album.builder().artist("Abba").title("The Album").releaseYear(1977).genre(targetGenre).quantity(10).build(),
                Album.builder().artist("Tears For Fears").title("The Seeds Of Love").releaseYear(1989).genre(targetGenre).quantity(10).build(),
                Album.builder().artist("Janet Jackson").title("Rhythm Nation 1814").releaseYear(1989).genre(Genre.RNB).quantity(7).build()
        );

        List<Album> expectedAlbumList = albumList.stream()
                        .filter(album -> album.getGenre().equals(targetGenre)).toList();

        when(mockRecordShopServiceImpl.getAlbumsByGenre(targetGenre)).thenReturn(expectedAlbumList);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/album?genre=" + targetGenreString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].genre").value(targetGenreString))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].genre").value(targetGenreString));
    }

}
