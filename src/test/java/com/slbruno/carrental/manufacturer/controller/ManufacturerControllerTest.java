package com.slbruno.carrental.manufacturer.controller;

import com.slbruno.carrental.manufacturer.builder.ManufacturerDTOBuilder;
import com.slbruno.carrental.manufacturer.dto.ManufacturerDTO;
import com.slbruno.carrental.manufacturer.service.ManufacturerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static com.slbruno.carrental.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ManufacturerControllerTest {

    private static final String AUTHOR_API_URL_PATH = "/api/cr/manufacturers";

    @Mock
    private ManufacturerService manufacturerService;

    @InjectMocks
    private ManufacturerController manufacturerController;

    private MockMvc mockMvc;

    private ManufacturerDTOBuilder manufacturerDTOBuilder;

    @BeforeEach
    void setUp() {
        manufacturerDTOBuilder = ManufacturerDTOBuilder.builder().build();
        mockMvc = MockMvcBuilders.standaloneSetup(manufacturerController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenStatusCreatedShouldBeInformed() throws Exception {
        ManufacturerDTO expectedCreatedManufacturerDTO = manufacturerDTOBuilder.buildManufacturerDTO();

        when(manufacturerService.create(expectedCreatedManufacturerDTO)).thenReturn(expectedCreatedManufacturerDTO);

        mockMvc.perform(post(AUTHOR_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(expectedCreatedManufacturerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(expectedCreatedManufacturerDTO.getId().intValue())))
                .andExpect(jsonPath("$.name", is(expectedCreatedManufacturerDTO.getName())));
    }

    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenBadRequestShouldBeInformed() throws Exception {
        ManufacturerDTO expectedCreatedManufacturerDTO = ManufacturerDTOBuilder.builder().build().buildManufacturerDTO();
        expectedCreatedManufacturerDTO.setName(null);

        mockMvc.perform(post(AUTHOR_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(expectedCreatedManufacturerDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenGETWithValidNameIsCalledThenOkStatusShouldBeReturned() throws Exception {
        ManufacturerDTO expectedCreatedManufacturerDTO = ManufacturerDTOBuilder.builder().build().buildManufacturerDTO();

        when(manufacturerService.findByName(expectedCreatedManufacturerDTO.getName())).thenReturn(expectedCreatedManufacturerDTO);

        mockMvc.perform(get(AUTHOR_API_URL_PATH + "/" + expectedCreatedManufacturerDTO.getName())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(expectedCreatedManufacturerDTO.getId().intValue())))
                .andExpect(jsonPath("$.name", is(expectedCreatedManufacturerDTO.getName())));
    }

    @Test
    void whenGETListIsCalledThenOkStatusShouldBeReturned() throws Exception {
        ManufacturerDTO expectedCreatedManufacturerDTO = ManufacturerDTOBuilder.builder().build().buildManufacturerDTO();

        when(manufacturerService.findAll()).thenReturn(Collections.singletonList(expectedCreatedManufacturerDTO));

        mockMvc.perform(get(AUTHOR_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(expectedCreatedManufacturerDTO.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(expectedCreatedManufacturerDTO.getName())));
    }

    @Test
    void whenDELETEWithValidIdIsCalledThenNoContentShouldBeReturned() throws Exception {
        ManufacturerDTO expectedCreatedManufacturerDTO = ManufacturerDTOBuilder.builder().build().buildManufacturerDTO();

        doNothing().when(manufacturerService).delete(expectedCreatedManufacturerDTO.getId());

        mockMvc.perform(delete(AUTHOR_API_URL_PATH + "/" + expectedCreatedManufacturerDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
