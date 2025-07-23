package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.lang.reflect.Array.get;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // 👇 This is the missing part
    @MockBean
    private PostRepository postRepository;

    @Test
    void testCreatePost() throws Exception {
        PostRequest request = new PostRequest();
        request.setMessage("Test Message");

        Post mockPost = new Post(1, "Test Message", LocalDateTime.now());

        when(postRepository.save("Test Message")).thenReturn(mockPost);

        mockMvc.perform(post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Test Message"))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetPostById() throws Exception {
        Post post = new Post(99, "Get Me", LocalDateTime.now());
        when(postRepository.findById(99)).thenReturn(Optional.of(post));

//        mockMvc.perform(get("/post/99"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(99))
//                .andExpect(jsonPath("$.message").value("Get Me"));
    }

    @Test
    void testDeletePost() throws Exception {
        when(postRepository.deleteById(42)).thenReturn(true);

        mockMvc.perform(delete("/post/42"))
                .andExpect(status().isNoContent());
    }

}
