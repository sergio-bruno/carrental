package com.slbruno.carrental.carrental.controler;

import com.slbruno.carrental.carrental.controler.docs.CarrentalControllerDocs;
import com.slbruno.carrental.carrental.dto.CarrentalDTO;
import com.slbruno.carrental.carrental.service.CarrentalService;
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
@RequestMapping("/api/cr/carrental")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarrentalController implements CarrentalControllerDocs {

    private final CarrentalService carrentalService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarrentalDTO create(@RequestBody @Valid CarrentalDTO carrentalDTO) {
        return carrentalService.create(carrentalDTO);
    }

    @GetMapping("/prnome/{name}")
    public CarrentalDTO findByName(@PathVariable String name) {
        return carrentalService.findByName(name);
    }

    @GetMapping("/{id}")
    public CarrentalDTO findById(@PathVariable Long id) {
        return carrentalService. findById(id);
    }
    
    @GetMapping
    public List<CarrentalDTO> findAll() {
        return carrentalService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        carrentalService.delete(id);
    }
}
