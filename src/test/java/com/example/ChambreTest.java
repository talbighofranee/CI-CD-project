package com.example;

import com.example.sprinprojet.SprinprojetApplication;
import com.example.sprinprojet.entity.Bloc;
import com.example.sprinprojet.entity.Chambre;
import com.example.sprinprojet.entity.TypeChambre;
import com.example.sprinprojet.repository.ChambreRepository;
import com.example.sprinprojet.services.ChambreServiceImp;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SprinprojetApplication.class)
public class ChambreTest {

    @InjectMocks
    private ChambreServiceImp chambreService; // Injecte la classe à tester

    @Mock
    private QRCodeWriter qrCodeWriter; // Mock pour le QRCodeWriter

    @Mock
    private ChambreRepository chambreRepository; // Mock pour ChambreRepository

    private Chambre chambre;

    @BeforeEach
    void setUp() {
        chambre = new Chambre();
    }

    @Test
    void testAddChambre() {
        // Given
        Chambre chambreToAdd = new Chambre();
        chambreToAdd.setTypeC(TypeChambre.SIMPLE);
        chambreToAdd.setNumeroChambre(101L);

        // Mocking behavior of ChambreRepository
        when(chambreRepository.save(any(Chambre.class))).thenReturn(chambreToAdd);

        // When
        Chambre addedChambre = chambreService.addChambre(chambreToAdd);

        // Then
        assertNotNull(addedChambre);
        assertEquals(TypeChambre.SIMPLE, addedChambre.getTypeC());
        assertEquals(101L, addedChambre.getNumeroChambre());
    }



    @Test
    void testRetrieveChambreByNumeroChambre() {
        // Given
        long numeroChambre = 101L;
        Chambre expectedChambre = new Chambre();
        expectedChambre.setNumeroChambre(numeroChambre);

        // Mocking behavior of ChambreRepository
        when(chambreRepository.findByNumeroChambre(numeroChambre)).thenReturn(expectedChambre);

        // When
        Chambre retrievedChambre = chambreService.getChambreByNumeroChambre(numeroChambre);

        // Then
        assertNotNull(retrievedChambre);
        assertEquals(expectedChambre, retrievedChambre);
    }



}
