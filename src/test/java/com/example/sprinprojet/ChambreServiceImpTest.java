package com.example.sprinprojet;

import com.example.sprinprojet.entity.Chambre;
import com.example.sprinprojet.entity.TypeChambre;
import com.example.sprinprojet.repository.ChambreRepository;
import com.example.sprinprojet.services.ChambreServiceImp;
import com.google.zxing.WriterException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ChambreServiceTest {

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreServiceImp chambreService;

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
        // For example, you might want to set some properties of chambreWithQRCode that are relevant for generating the QR code

        // When
        byte[] qrCode = chambreService.generateQRCode(chambreWithQRCode);

        // Then
        assertNotNull(qrCode); // Ensure that the QR code is not null
        // You might want to add further assertions to validate the generated QR code, such as its size, format, etc.
        // For example:
        // assertTrue(qrCode.length > 0, "The QR code should have non-zero length");
        // Add more assertions as needed to validate the QR code
    }



    @Test
    void testCountChambresByType() {
        // Given
        TypeChambre typeToCount = TypeChambre.SIMPLE;
        long idBloc = 1L; // Provide a sample ID for the bloc
        long expectedCount = 5; // For example

        // Mocking behavior of ChambreRepository
        when(chambreRepository.countByTypeCAndBloc_IdBloc(typeToCount, idBloc)).thenReturn(expectedCount);

        // When
        long actualCount = chambreService.nbChambreParTypeEtBloc(typeToCount, idBloc);

        // Then
        assertEquals(expectedCount, actualCount);
        // Add more assertions as needed
    }



}
