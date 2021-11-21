package com.gabchak.budget.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabchak.budget.dto.RecordRequestDto;
import com.gabchak.budget.dto.RecordResponseDto;
import com.gabchak.budget.mock.MockDtoFactory;
import com.gabchak.budget.service.RecordService;
import java.util.List;
import java.util.NoSuchElementException;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = RecordController.class)
class RecordControllerTest {

  @MockBean RecordService recordService;
  @Autowired MockMvc mockMvc;
  @Autowired ObjectMapper objectMapper;

  @Test
  void findAll_success() throws Exception {
    String path = "/records";
    List<RecordResponseDto> expected = List.of(MockDtoFactory.buildRecordResponseDto());

    Mockito
        .when(recordService.findAll())
        .thenReturn(expected);

    mockMvc.perform(MockMvcRequestBuilders.get(path))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));
  }

  @Test
  void findById_success() throws Exception {
    String path = "/records/{id}";
    Integer id = 1;
    RecordResponseDto expected = MockDtoFactory.buildRecordResponseDto();

    Mockito
        .when(recordService.findById(id))
        .thenReturn(expected);

    mockMvc.perform(MockMvcRequestBuilders.get(path, id))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));
  }

  @Test
  void findById_notFound() throws Exception {
    String path = "/records/{id}";
    Integer id = 1;

    String exceptionMessage = "Not found";
    Mockito
        .when(recordService.findById(id))
        .thenThrow(new NoSuchElementException(exceptionMessage));

    mockMvc.perform(MockMvcRequestBuilders.get(path, id))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is(exceptionMessage)));
  }

  @Test
  void create_success() throws Exception {
    String path = "/records";
    RecordRequestDto requestDto = MockDtoFactory.buildRecordRequestDto();
    RecordResponseDto expected = MockDtoFactory.buildRecordResponseDto();

    Mockito
        .when(recordService.create(requestDto))
        .thenReturn(expected);

    mockMvc.perform(
            MockMvcRequestBuilders.post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto))
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));
  }

  @Test
  void update_success() throws Exception {
    String path = "/records/{id}";
    Integer id = 1;
    RecordRequestDto requestDto = MockDtoFactory.buildRecordRequestDto();
    RecordResponseDto expected = MockDtoFactory.buildRecordResponseDto();

    Mockito
        .when(recordService.update(id, requestDto))
        .thenReturn(expected);

    mockMvc.perform(
            MockMvcRequestBuilders.put(path, id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto))
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));
  }
}