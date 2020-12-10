//package edu.roadmaps.core.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import edu.roadmaps.core.repository.LeafRepository;
//import edu.roadmaps.core.repository.LinkLectureRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@Testcontainers
//@AutoConfigureMockMvc
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ExtendWith(MockitoExtension.class)
//public class LectureControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private LinkLectureRepository linkRepository;
//
//    @Autowired
//    private LeafRepository leafRepository;
//
//    @Test
//    void getLectureNotFoundTest() throws Exception {
//        leafRepository.deleteAll();
//        mockMvc.perform(get("/v0.1/courses/modules/lectures/1"))
//                .andExpect(status().is(404));
//    }
//
//
//}
