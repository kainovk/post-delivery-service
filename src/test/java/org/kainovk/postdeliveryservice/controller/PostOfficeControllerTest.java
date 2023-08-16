package org.kainovk.postdeliveryservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.kainovk.postdeliveryservice.dto.PostOfficeRequest;
import org.kainovk.postdeliveryservice.model.Status;
import org.kainovk.postdeliveryservice.service.PostOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostOfficeController.class)
class PostOfficeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostOfficeService postOfficeService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testCreatePostOffice() throws Exception {
        PostOfficeRequest request = prepareValidPostOfficeRequest();

        mockMvc.perform(post("/api/post-offices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        verify(postOfficeService, times(1)).createPostOffice(any(PostOfficeRequest.class));
    }

    @Test
    void testDeparture() throws Exception {
        Long id = 1L;

        mockMvc.perform(patch("/api/post-offices/{id}/departure", id))
                .andExpect(status().isOk());

        verify(postOfficeService, times(1)).departure(id);
    }

    private PostOfficeRequest prepareValidPostOfficeRequest() {
        return new PostOfficeRequest(
                "123-321",
                "post office 1",
                "post office 2",
                Status.IN_PROGRESS,
                1L
        );
    }
}