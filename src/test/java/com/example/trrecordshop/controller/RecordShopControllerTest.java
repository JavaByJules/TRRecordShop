package com.example.trrecordshop.controller;

import com.example.trrecordshop.model.Album;
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
import java.util.List;

import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
class RecordShopControllerTest {

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
    public void testWelcomeReturnsMessageString() throws Exception {
        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/api/v1/"))
                .andExpect(MockMvcResultMatchers.content().string("Welcome Page"));
    }

    @Disabled
    @Test
    public void testGetAllAlbumsReturnsAlbumList() throws Exception {
        List<Album> albumList = new ArrayList<>();
        albumList.add(new Album());
        albumList.add(new Album());
        albumList.add(new Album());

        when(mockRecordShopServiceImpl.getAllAlbums()).thenReturn(albumList);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/albums"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].artist").value("Artist One"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Album One"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].releaseYear").value("2021"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].artist").value("Artist Two"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Album Two"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].releaseYear").value("2022"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].artist").value("Artist Three"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].title").value("Album Three"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].releaseYear").value("2023"));

    }

    @Disabled
    @Test
    public void testGetAlbumByTitle() throws Exception {
        Album album = new Album();

        when(mockRecordShopServiceImpl.getAlbumByTitle()).thenReturn(album);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/api/v1/album?title=" + album.getTitle()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Album One"));
    }

    @Disabled
    @Test
    public void testGetAlbumByArtist() throws Exception {
        Album album = new Album();

        when(mockRecordShopServiceImpl.getAlbumByArtist()).thenReturn(album);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/album?artist=" + album.getArtist()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist").value("Artist One"));
    }

    @Disabled
    @Test
    public void testGetAlbumByReleaseYear() throws Exception {
        Album album = new Album();

        when(mockRecordShopServiceImpl.getAlbumByReleaseYear()).thenReturn(album);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/album?releaseYear=" + album.getReleaseYear()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.releaseYear").value("2020"));
    }

    @Disabled
    @Test
    public void testGetAlbumByGenre() throws Exception {
        Album album = new Album();

        when(mockRecordShopServiceImpl.getAlbumByGenre()).thenReturn(album);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/album?genre=" + album.getGenre()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value("Genre One"));
    }

}
