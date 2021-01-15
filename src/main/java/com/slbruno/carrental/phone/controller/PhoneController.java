package com.slbruno.carrental.phone.controller;

import com.slbruno.carrental.phone.controller.docs.PhoneControllerDocs;
import com.slbruno.carrental.phone.dto.PhoneRequestDTO;
import com.slbruno.carrental.phone.dto.PhoneResponseDTO;
import com.slbruno.carrental.phone.service.PhoneService;
import com.slbruno.carrental.user.dto.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cr/phone")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PhoneController implements PhoneControllerDocs {

    private final PhoneService phoneService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PhoneResponseDTO create(@AuthenticationPrincipal AuthenticatedUser authenticatedUser, @RequestBody @Valid PhoneRequestDTO phoneRequestDTO) {
        return phoneService.create(authenticatedUser, phoneRequestDTO);
    }

    @GetMapping("/{id}")
    public PhoneResponseDTO findByIdAndUser(@AuthenticationPrincipal AuthenticatedUser authenticatedUser, @PathVariable Long id) {
        return phoneService.findByIdAndUser(authenticatedUser, id);
    }

    @GetMapping
    public List<PhoneResponseDTO> findAllByUser(@AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        return phoneService.findAllByUser(authenticatedUser);
    }

    @PutMapping("/{id}")
    public PhoneResponseDTO updateByUser(
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser,
            @PathVariable Long id,
            @RequestBody @Valid PhoneRequestDTO phoneRequestDTO) {
        return phoneService.updateByUser(authenticatedUser, id, phoneRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByIdAndUser(
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser,
            @PathVariable Long id) {
    	phoneService.deleteByIdAndUser(authenticatedUser, id);
    }

}
