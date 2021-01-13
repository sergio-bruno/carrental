package com.slbruno.carrental.manufacturer.service;

import com.slbruno.carrental.manufacturer.builder.ManufacturerDTOBuilder;
import com.slbruno.carrental.manufacturer.dto.ManufacturerDTO;
import com.slbruno.carrental.manufacturer.entity.Manufacturer;
import com.slbruno.carrental.manufacturer.exception.ManufacturerAlreadyExistsException;
import com.slbruno.carrental.manufacturer.exception.ManufacturerNotFoundException;
import com.slbruno.carrental.manufacturer.mapper.ManufacturerMapper;
import com.slbruno.carrental.manufacturer.repository.ManufacturerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ManufacturerServiceTest {

    private final ManufacturerMapper manufacturerMapper = ManufacturerMapper.INSTANCE;

    @Mock
    private ManufacturerRepository manufacturerRepository;

    @InjectMocks
    private ManufacturerService manufacturerService;

    private ManufacturerDTOBuilder manufacturerDtoBuilder;

    @BeforeEach
    void setUp() {
        manufacturerDtoBuilder = ManufacturerDTOBuilder.builder().build();
    }

    @Test
    void whenNewManufacturerIsInformedThenItShouldBeCreated() {
        ManufacturerDTO expectedManufacturerToCreateDTO = manufacturerDtoBuilder.buildManufacturerDTO();
        Manufacturer expectedCreatedManufacturer = manufacturerMapper.toModel(expectedManufacturerToCreateDTO);

        when(manufacturerRepository.save(expectedCreatedManufacturer)).thenReturn(expectedCreatedManufacturer);
        when(manufacturerRepository.findByName(expectedManufacturerToCreateDTO.getName())).thenReturn(Optional.empty());

        ManufacturerDTO createdManufacturerDTO = manufacturerService.create(expectedManufacturerToCreateDTO);

        assertThat(createdManufacturerDTO, is(equalTo(expectedManufacturerToCreateDTO)));
    }

    @Test
    void whenExistingManufacturerIsInformedThenAnExceptionShouldBeThrown() {
        ManufacturerDTO expectedManufacturerToCreateDTO = manufacturerDtoBuilder.buildManufacturerDTO();
        Manufacturer expectedDuplicatedManufacturer = manufacturerMapper.toModel(expectedManufacturerToCreateDTO);

        when(manufacturerRepository.findByName(expectedManufacturerToCreateDTO.getName())).thenReturn(Optional.of(expectedDuplicatedManufacturer));

        assertThrows(ManufacturerAlreadyExistsException.class, () -> manufacturerService.create(expectedManufacturerToCreateDTO));
    }

    @Test
    void whenValidNameIsGivenThenAnManufacturerShouldBeReturned() {
        ManufacturerDTO expectedFoundManufacturerDTO = manufacturerDtoBuilder.buildManufacturerDTO();
        Manufacturer expectedFoundManufacturer = manufacturerMapper.toModel(expectedFoundManufacturerDTO);

        when(manufacturerRepository.findByName(expectedFoundManufacturerDTO.getName())).thenReturn(Optional.of(expectedFoundManufacturer));

        ManufacturerDTO foundManufacturer = manufacturerService.findByName(expectedFoundManufacturerDTO.getName());

        assertThat(foundManufacturer, is(equalTo(expectedFoundManufacturerDTO)));
    }

    @Test
    void whenInvalidNameIsGivenThenAnExceptionShouldBeThrown() {
        ManufacturerDTO expectedFoundManufacturerDTO = manufacturerDtoBuilder.buildManufacturerDTO();

        when(manufacturerRepository.findByName(expectedFoundManufacturerDTO.getName())).thenReturn(Optional.empty());

        assertThrows(ManufacturerNotFoundException.class, () -> manufacturerService.findByName(expectedFoundManufacturerDTO.getName()));
    }

    @Test
    void whenListManufacturersIsCalledThenItShouldBeReturned() {
        ManufacturerDTO expectedFoundManufacturerDTO = ManufacturerDTOBuilder.builder().build().buildManufacturerDTO();
        Manufacturer expectedFoundManufacturer = manufacturerMapper.toModel(expectedFoundManufacturerDTO);

        when(manufacturerRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundManufacturer));

        List<ManufacturerDTO> foundManufacturersDTO = manufacturerService.findAll();

        assertThat(foundManufacturersDTO.size(), is(1));
        assertThat(foundManufacturersDTO.get(0), is(equalTo(expectedFoundManufacturerDTO)));
    }

    @Test
    void whenListManufacturersIsCalledThenAndEmptyListShouldBeReturned() {
        ManufacturerDTO expectedFoundManufacturerDTO = manufacturerDtoBuilder.buildManufacturerDTO();

        when(manufacturerRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        List<ManufacturerDTO> foundManufacturersDTO = manufacturerService.findAll();

        assertThat(foundManufacturersDTO.size(), is(0));
    }

    @Test
    void whenExistingManufacturerIdIsGivenThenManufacturerEntityShouldBeReturned() {
        ManufacturerDTO expectedFoundManufacturerDTO = manufacturerDtoBuilder.buildManufacturerDTO();
        Manufacturer expectedFoundManufacturer = manufacturerMapper.toModel(expectedFoundManufacturerDTO);

        when(manufacturerRepository.findById(expectedFoundManufacturerDTO.getId()))
                .thenReturn(Optional.of(expectedFoundManufacturer));

        Manufacturer foundManufacturer = manufacturerService.verifyAndGetIfExists(expectedFoundManufacturerDTO.getId());

        assertThat(foundManufacturer, is(equalTo(expectedFoundManufacturer)));
    }

    @Test
    void whenNotExistingManufacturerIdIsGivenThenAndExceptionShouldBeThrown() {
        ManufacturerDTO expectedFoundManufacturerDTO = manufacturerDtoBuilder.buildManufacturerDTO();
        long invalidId = 1L;

        when(manufacturerRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(ManufacturerNotFoundException.class, () -> manufacturerService.verifyAndGetIfExists(expectedFoundManufacturerDTO.getId()));
    }

    @Test
    void whenValidManufacturerIdIsGivenThenItShouldBeReturned() {
        ManufacturerDTO expectedDeletedManufacturerDTO = manufacturerDtoBuilder.buildManufacturerDTO();
        Manufacturer expectedDeletedManufacturer = manufacturerMapper.toModel(expectedDeletedManufacturerDTO);

        Long expectedDeletedManufacturerId = expectedDeletedManufacturerDTO.getId();
        doNothing().when(manufacturerRepository).deleteById(expectedDeletedManufacturerId);
        when(manufacturerRepository.findById(expectedDeletedManufacturerId)).thenReturn(Optional.of(expectedDeletedManufacturer));

        manufacturerService.delete(expectedDeletedManufacturer.getId());

        verify(manufacturerRepository, times(1)).findById(expectedDeletedManufacturerId);
        verify(manufacturerRepository, times(1)).deleteById(expectedDeletedManufacturerId);
    }

    @Test
    void whenInvalidManufacturerIsGivenThenAnExceptionShouldBeThrown() {
        Long expectedNotFoundManufacturerId = 2L;

        when(manufacturerRepository.findById(expectedNotFoundManufacturerId)).thenReturn(Optional.empty());

        assertThrows(ManufacturerNotFoundException.class, () -> manufacturerService.delete(expectedNotFoundManufacturerId));
    }
}
