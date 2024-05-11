package com.nael.library_management.controller;

import com.nael.library_management.dto.PatronDTO;
import com.nael.library_management.service.PatronService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping("/all")
    public List<PatronDTO> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/{id}")
    public PatronDTO getPatronById(@PathVariable Long id) {
        return patronService.getPatronById(id);
    }

    @GetMapping("/delete/{id}")
    public void deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
    }

    @GetMapping("/update/{id}")
    public PatronDTO updatePatron(@PathVariable Long id, PatronDTO patron) {
        return patronService.updatePatron(id, patron);
    }

    @GetMapping("/add")
    public PatronDTO addPatron(PatronDTO patron) {
        return patronService.savePatron(patron);
    }

}