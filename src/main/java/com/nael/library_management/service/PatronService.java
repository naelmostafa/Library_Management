package com.nael.library_management.service;

import com.nael.library_management.dto.PatronDTO;
import com.nael.library_management.entity.Patron;
import com.nael.library_management.repository.PatronRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatronService {
    private final PatronRepository patronRepository;

    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public List<PatronDTO> getAllPatrons() {
        return patronRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PatronDTO getPatronById(Long id) {
        Patron patron = patronRepository.findById(id).orElse(null);
        return patron != null ? convertToDto(patron) : null;
    }

    public PatronDTO savePatron(PatronDTO patronDTO) {
        Patron patron = convertToEntity(patronDTO);
        Patron savedPatron = patronRepository.save(patron);
        return convertToDto(savedPatron);
    }

    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }

    public PatronDTO updatePatron(Long id, PatronDTO patronDTO) {
        Patron existingPatron = patronRepository.findById(id).orElse(null);
        if (existingPatron != null) {
            existingPatron.setName(patronDTO.getName());
            existingPatron.setEmail(patronDTO.getEmail());
            existingPatron.setPhoneNumber(patronDTO.getPhoneNumber());
            Patron updatedPatron = patronRepository.save(existingPatron);
            return convertToDto(updatedPatron);
        }
        return null;
    }

    private PatronDTO convertToDto(Patron patron) {
        PatronDTO dto = new PatronDTO();
        dto.setId(patron.getId());
        dto.setName(patron.getName());
        dto.setEmail(patron.getEmail());
        dto.setPhoneNumber(patron.getPhoneNumber());
        return dto;
    }

    private Patron convertToEntity(PatronDTO dto) {
        Patron patron = new Patron();
        patron.setId(dto.getId());
        patron.setName(dto.getName());
        patron.setEmail(dto.getEmail());
        patron.setPhoneNumber(dto.getPhoneNumber());
        return patron;
    }
}