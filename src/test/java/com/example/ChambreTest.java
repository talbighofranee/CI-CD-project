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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
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
    @Test
    void testUpdateChambre() {
        // Given
        Chambre chambreToUpdate = new Chambre();
        chambreToUpdate.setIdChambre(1L); // Set the ID of an existing chambre
        chambreToUpdate.setTypeC(TypeChambre.DOUBLE); // Change the type of chambre

        // Mocking behavior of ChambreRepository
        when(chambreRepository.save(any(Chambre.class))).thenReturn(chambreToUpdate);

        // When
        Chambre updatedChambre = chambreService.updateChambre(chambreToUpdate);

        // Then
        assertNotNull(updatedChambre);
        assertEquals(TypeChambre.DOUBLE, updatedChambre.getTypeC());
        // Add more assertions as needed
    }

    @Test
    void testDeleteChambre() {
        // Given
        long chambreIdToDelete = 1L;

        // When
        chambreService.removeChambre(chambreIdToDelete);

        // Then
        // Verify that the deleteChambre method was called with the correct ID
        verify(chambreRepository).deleteById(chambreIdToDelete);
        // Add more assertions as needed
    }

    @Test
    void testGetAllChambres() {
        // Given
        when(chambreRepository.findAll()).thenReturn(Arrays.asList(new Chambre(), new Chambre()));

        // When
        List<Chambre> allChambres = chambreService.retrieveAllChambres();

        // Then
        assertNotNull(allChambres);
        assertEquals(2, allChambres.size());
        // Add more assertions as needed
    }

    @Test
    void testGenerateQRCode() throws IOException, WriterException {
        // Given
        Chambre chambreWithQRCode = new Chambre();
        // Set up chambreWithQRCode as needed for QR code generation

        // When
        byte[] qrCode = chambreService.generateQRCode(chambreWithQRCode);

        // Then
        assertNotNull(qrCode);
        // Add more assertions as needed
    }

    @Test
    void testNbChambreParTypeEtBloc() {
        // Given
        long idBloc = 1L;
        TypeChambre typeChambre = TypeChambre.SIMPLE;
        long expectedCount = 5L;

        // Mocking behavior of ChambreRepository
        when(chambreRepository.countByTypeCAndBloc_IdBloc(typeChambre, idBloc)).thenReturn(expectedCount);

        // When
        long actualCount = chambreService.nbChambreParTypeEtBloc(typeChambre, idBloc);

        // Then
        assertEquals(expectedCount, actualCount);
    }




}
