package com.example.sprinprojet;

import com.example.sprinprojet.entity.Reservation;
import com.example.sprinprojet.entity.Status;
import com.example.sprinprojet.repository.ReservationRepository;
import com.example.sprinprojet.services.ReservationServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImp reservationServiceImp;

    @Test
    public void testCancelUnconfirmedReservations() {
        // Given
        LocalDateTime cutoffDate = LocalDateTime.now().minusMinutes(1);
        Date cutoffDateAsDate = Date.from(cutoffDate.atZone(ZoneId.systemDefault()).toInstant());
        List<Reservation> unconfirmedReservations = new ArrayList<>();
        Reservation reservation1 = new Reservation();
        reservation1.setStatus(Status.NON_CONFIRMEE);
        reservation1.setDateCreation(Date.from(LocalDateTime.now().minusMinutes(2).atZone(ZoneId.systemDefault()).toInstant())); // La date de création est plus ancienne d'au moins 2 minutes
        unconfirmedReservations.add(reservation1); // Ajoutez une réservation d'exemple avec le statut NON_CONFIRMEE et une date de création ancienne

        // Configurer le comportement simulé du repository
        when(reservationRepository.findUnconfirmedReservationsOlderThan(any(Date.class)))
                .thenReturn(unconfirmedReservations);

        // When
        reservationServiceImp.cancelUnconfirmedReservations();

        // Then
// Vérifier que le statut de la réservation a été mis à jour
        for (Reservation reservation : unconfirmedReservations) {
            if (reservation.getStatus() == Status.NON_CONFIRMEE && reservation.getDateCreation().before(cutoffDateAsDate)) {
                assertEquals(Status.ANNULEE, reservation.getStatus());
            }
        }
    }




}

