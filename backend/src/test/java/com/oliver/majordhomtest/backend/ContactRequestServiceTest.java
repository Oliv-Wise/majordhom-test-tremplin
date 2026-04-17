package com.oliver.majordhomtest.backend.service;

import com.oliver.majordhomtest.backend.dto.AvailabilityRequest;
import com.oliver.majordhomtest.backend.dto.CreateRequest;
import com.oliver.majordhomtest.backend.repository.ContactRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactRequestServiceTest {

    @Mock
    private ContactRequestRepository contactRequestRepository;

    @InjectMocks
    private ContactRequestService contactRequestService;

    @Test
    void shouldCreateContactRequestWhenRequestIsValid() {
        CreateRequest request = buildValidRequest();

        when(contactRequestRepository.saveContactRequest(request)).thenReturn(1L);

        Long result = contactRequestService.createContactRequest(request);

        assertEquals(1L, result);
        verify(contactRequestRepository).saveContactRequest(request);
        verify(contactRequestRepository).saveAvailabilities(1L, request.getAvailabilities());
    }

    @Test
    void shouldThrowWhenLastNameIsBlank() {
        CreateRequest request = buildValidRequest();
        request.setLastName("   ");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> contactRequestService.createContactRequest(request)
        );

        assertEquals("Le nom est requis.", exception.getMessage());
        verify(contactRequestRepository, never()).saveContactRequest(request);
        verify(contactRequestRepository, never()).saveAvailabilities(anyLong(), anyList());
    }

    @Test
    void shouldThrowWhenFirstNameIsBlank() {
        CreateRequest request = buildValidRequest();
        request.setFirstName("");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> contactRequestService.createContactRequest(request)
        );

        assertEquals("Le prénom est requis.", exception.getMessage());
        verify(contactRequestRepository, never()).saveContactRequest(request);
        verify(contactRequestRepository, never()).saveAvailabilities(anyLong(), anyList());
    }

    @Test
    void shouldThrowWhenEmailIsBlank() {
        CreateRequest request = buildValidRequest();
        request.setEmail("   ");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> contactRequestService.createContactRequest(request)
        );

        assertEquals("L'email est requis.", exception.getMessage());
        verify(contactRequestRepository, never()).saveContactRequest(request);
        verify(contactRequestRepository, never()).saveAvailabilities(anyLong(), anyList());
    }

    @Test
    void shouldThrowWhenPhoneIsBlank() {
        CreateRequest request = buildValidRequest();
        request.setPhone(null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> contactRequestService.createContactRequest(request)
        );

        assertEquals("Le téléphone est requis.", exception.getMessage());
        verify(contactRequestRepository, never()).saveContactRequest(request);
        verify(contactRequestRepository, never()).saveAvailabilities(anyLong(), anyList());
    }

    @Test
    void shouldThrowWhenAvailabilitiesIsNull() {
        CreateRequest request = buildValidRequest();
        request.setAvailabilities(null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> contactRequestService.createContactRequest(request)
        );

        assertEquals("Au moins une disponibilité est requise.", exception.getMessage());
        verify(contactRequestRepository, never()).saveContactRequest(request);
        verify(contactRequestRepository, never()).saveAvailabilities(anyLong(), anyList());
    }

    @Test
    void shouldThrowWhenAvailabilitiesIsEmpty() {
        CreateRequest request = buildValidRequest();
        request.setAvailabilities(List.of());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> contactRequestService.createContactRequest(request)
        );

        assertEquals("Au moins une disponibilité est requise.", exception.getMessage());
        verify(contactRequestRepository, never()).saveContactRequest(request);
        verify(contactRequestRepository, never()).saveAvailabilities(anyLong(), anyList());
    }

    private CreateRequest buildValidRequest() {
        AvailabilityRequest availability = new AvailabilityRequest();
        availability.setDay("Lundi");
        availability.setHour("9");
        availability.setMinute("45");

        CreateRequest request = new CreateRequest();
        request.setCivility("Mme");
        request.setLastName("Joseph");
        request.setFirstName("Marvens");
        request.setEmail("marvens@example.com");
        request.setPhone("0601020304");
        request.setRequestType("Demande de visite");
        request.setMessage("Bonjour");
        request.setAvailabilities(List.of(availability));

        return request;
    }
}