package com.slbruno.carrental.manufacturer.controller;

import com.slbruno.carrental.manufacturer.controller.docs.ManufacturerControllerDocs;
import com.slbruno.carrental.manufacturer.dto.ManufacturerDTO;
import com.slbruno.carrental.manufacturer.service.ManufacturerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cr/manufacturer")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ManufacturerController implements ManufacturerControllerDocs {

    private final ManufacturerService manufacturerService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ManufacturerDTO create(@RequestBody @Valid ManufacturerDTO manufacturerDTO) {
        return manufacturerService.create(manufacturerDTO);
    }

    @GetMapping("/{name}")
    public ManufacturerDTO findByName(@PathVariable String name) {
        return manufacturerService.findByName(name);
    }

    @GetMapping
    public List<ManufacturerDTO> findAll() {
        return manufacturerService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        manufacturerService.delete(id);
    }
}
