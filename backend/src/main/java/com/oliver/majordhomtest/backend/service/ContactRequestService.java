package com.oliver.majordhomtest.backend.service;

import com.oliver.majordhomtest.backend.dto.AvailabilityRequest;
import com.oliver.majordhomtest.backend.dto.CreateRequest;
import com.oliver.majordhomtest.backend.repository.ContactRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactRequestService {

    private final ContactRequestRepository contactRequestRepository;

    public ContactRequestService(ContactRequestRepository contactRequestRepository) {
        this.contactRequestRepository = contactRequestRepository;
    }

    @Transactional
    public Long createContactRequest(CreateRequest request) {
        validateRequest(request);

        Long contactRequestId = contactRequestRepository.saveContactRequest(request);
        contactRequestRepository.saveAvailabilities(contactRequestId, request.getAvailabilities());

        return contactRequestId;
    }

    private void validateRequest(CreateRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("La requête est invalide.");
        }

        if (isBlank(request.getLastName())) {
            throw new IllegalArgumentException("Le nom est requis.");
        }

        if (isBlank(request.getFirstName())) {
            throw new IllegalArgumentException("Le prénom est requis.");
        }

        if (isBlank(request.getEmail())) {
            throw new IllegalArgumentException("L'email est requis.");
        }

        if (isBlank(request.getPhone())) {
            throw new IllegalArgumentException("Le téléphone est requis.");
        }

        if (request.getAvailabilities() == null || request.getAvailabilities().isEmpty()) {
            throw new IllegalArgumentException("Au moins une disponibilité est requise.");
        }

        for (AvailabilityRequest availability : request.getAvailabilities()) {
            if (availability == null
                    || isBlank(availability.getDay())
                    || isBlank(availability.getHour())
                    || isBlank(availability.getMinute())) {
                throw new IllegalArgumentException("Chaque disponibilité doit contenir un jour, une heure et des minutes.");
            }
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}