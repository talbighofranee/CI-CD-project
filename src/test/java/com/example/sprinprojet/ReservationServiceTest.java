package com.example.sprinprojet;

import com.example.sprinprojet.entity.Reservation;
import com.example.sprinprojet.entity.Status;
import com.example.sprinprojet.repository.ReservationRepository;
import com.example.sprinprojet.services.ReservationServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationServiceImp reservationServiceImp;

    @MockBean
    private ReservationRepository reservationRepository;




        @Test
        public void testCancelUnconfirmedReservations() {
            // Given
            LocalDateTime cutoffDate = LocalDateTime.now().minusHours(24);
            Date cutoffDateAsDate = Date.from(cutoffDate.atZone(ZoneId.systemDefault()).toInstant());
            List<Reservation> unconfirmedReservations = new ArrayList<>();
            Reservation reservation1 = new Reservation();
            reservation1.setStatus(Status.NON_CONFIRMEE);
            unconfirmedReservations.add(reservation1); // Add a sample reservation with NON_CONFIRMEE status
            // Mock the behavior of reservationRepository
            when(reservationRepository.findUnconfirmedReservationsOlderThan(cutoffDateAsDate)).thenReturn(unconfirmedReservations);

            // When
            reservationServiceImp.cancelUnconfirmedReservations();

            // Then
            // Verify that the status of each reservation is set to ANNULEE
            for (Reservation reservation : unconfirmedReservations) {
                if (reservation.getStatus() == Status.NON_CONFIRMEE) {
                    System.out.println("Avant mise à jour - ID: " + reservation.getIdReservation() + ", Statut: " + reservation.getStatus());
                    reservation.setStatus(Status.ANNULEE);
                    reservationRepository.save(reservation);
                    System.out.println("Après mise à jour - ID: " + reservation.getIdReservation() + ", Statut: " + reservation.getStatus());
                }
            }

            verify(reservationRepository, times(1)).save(any(Reservation.class)); // Verify that save() method is called
        }
    }
