package com.oliver.majordhomtest.backend.controller;

import com.oliver.majordhomtest.backend.dto.CreateRequest;
import com.oliver.majordhomtest.backend.dto.CreateResponse;
import com.oliver.majordhomtest.backend.service.ContactRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact-requests")
@CrossOrigin(origins = "http://localhost:5173")
public class ContactRequestController {

    private final ContactRequestService contactRequestService;

    public ContactRequestController(ContactRequestService contactRequestService) {
        this.contactRequestService = contactRequestService;
    }

    @PostMapping
    public ResponseEntity<CreateResponse> create(
            @RequestBody CreateRequest request
    ) {
        Long id = contactRequestService.createContactRequest(request);

        CreateResponse response =
                new CreateResponse(id, "Demande enregistrée avec succès.");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}