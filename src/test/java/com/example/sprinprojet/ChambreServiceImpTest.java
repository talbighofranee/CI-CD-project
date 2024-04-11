package com.example.sprinprojet;

import com.example.sprinprojet.entity.Bloc;
import com.example.sprinprojet.entity.Chambre;
import com.example.sprinprojet.entity.TypeChambre;
import com.example.sprinprojet.repository.ChambreRepository;
import com.example.sprinprojet.services.ChambreServiceImp;
import com.google.zxing.WriterException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChambreServiceImpTest {

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
    @Test
    @DisplayName("Test Retrieve Chambre By ID")
    void testRetrieveChambreById() {
        // Given
        long chambreId = 1L;
        Chambre expectedChambre = new Chambre();
        expectedChambre.setIdChambre(chambreId);
        when(chambreRepository.findById(chambreId)).thenReturn(Optional.of(expectedChambre));

        // When
        Chambre retrievedChambre = chambreService.retrieveChambre(chambreId);

        // Then
        assertEquals(expectedChambre, retrievedChambre);
    }




    @Test
    @DisplayName("Test Retrieve All Chambres When Empty")
    void testRetrieveAllChambresWhenEmpty() {
        // Given
        when(chambreRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        List<Chambre> allChambres = chambreService.retrieveAllChambres();

        // Then
        assertEquals(0, allChambres.size());
    }
    @Test
    @DisplayName("Test Get Chambres By Nom Bloc")
    void testGetChambresByNomBloc() {
        // Given
        String nomBloc = "BlocA";
        Bloc bloc = new Bloc();
        bloc.setIdBloc(1L);
        bloc.setNomBloc(nomBloc);
        Chambre chambre1 = new Chambre();
        chambre1.setIdChambre(1L);
        chambre1.setBloc(bloc);
        Chambre chambre2 = new Chambre();
        chambre2.setIdChambre(2L);
        chambre2.setBloc(bloc);
        when(chambreRepository.findByBlocNomBloc(nomBloc)).thenReturn(Arrays.asList(chambre1, chambre2));

        // When
        List<Chambre> chambres = chambreService.getChambresByNomBloc(nomBloc);

        // Then
        assertEquals(2, chambres.size());
        assertTrue(chambres.contains(chambre1));
        assertTrue(chambres.contains(chambre2));
    }



}
