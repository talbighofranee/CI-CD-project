package com.example.sprinprojet.repository;

import com.example.sprinprojet.entity.Reservation;
import com.example.sprinprojet.services.ReservationServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class ReservationRepositoryTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImp reservationService;

    @Test
    void testFindByAnneeUniversitaireBetween() {
        // Mocking data
        Date beginDate = new Date();
        Date endDate = new Date();
        List<Reservation> reservations = new ArrayList<>();
        // Add some reservations to the list

        // Mocking repository behavior
        when(reservationRepository.findByAnneeUniversitaireBetween(beginDate, endDate)).thenReturn(reservations);

        // Call the method to be tested
        List<Reservation> result = reservationRepository.findByAnneeUniversitaireBetween(beginDate, endDate);

        // Verify the result
        assertEquals(reservations, result);
    }

    @Test
    void testFindByChambreNumeroChambreAndEstValideAndAnneeUniversitaireBetween() {
        // Mocking data
        long chambreNumeroChambre = 123;
        Boolean estValide = true;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(7);
        List<Reservation> reservations = new ArrayList<>();
        // Add some reservations to the list

        // Mocking repository behavior
        when(reservationRepository.findByChambreNumeroChambreAndEstValideAndAnneeUniversitaireBetween(chambreNumeroChambre, estValide, startDate, endDate)).thenReturn(reservations);

        // Call the method to be tested
        List<Reservation> result = reservationRepository.findByChambreNumeroChambreAndEstValideAndAnneeUniversitaireBetween(chambreNumeroChambre, estValide, startDate, endDate);

        // Verify the result
        assertEquals(reservations, result);
    }

    @Test
    void testFindUnconfirmedReservationsOlderThan() {
        // Mocking data
        Date cutoffDate = new Date();
        List<Reservation> reservations = new ArrayList<>();
        // Add some reservations to the list

        // Mocking repository behavior
        when(reservationRepository.findUnconfirmedReservationsOlderThan(cutoffDate)).thenReturn(reservations);

        // Call the method to be tested
        List<Reservation> result = reservationRepository.findUnconfirmedReservationsOlderThan(cutoffDate);

        // Verify the result
        assertEquals(reservations, result);
    }

    
}
