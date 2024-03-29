package com.example.sprinprojet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Set;
import java.util.Collections;

import java.util.HashSet;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;

import com.example.sprinprojet.entity.Bloc;
import com.example.sprinprojet.entity.Chambre;
import com.example.sprinprojet.entity.TypeChambre;

import com.example.sprinprojet.repository.BlocRepository;
import com.example.sprinprojet.repository.ChambreRepository;
import com.example.sprinprojet.services.BlocServiceImp;

@ExtendWith(MockitoExtension.class)
public class BlocServiceImpTest {

    @Mock
    private BlocRepository blocRepository;

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private BlocServiceImp blocService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllBlocs() {
        // Mocking repository behavior
        when(blocRepository.findAll()).thenReturn(Arrays.asList(new Bloc(), new Bloc()));

        // Testing service method
        List<Bloc> blocs = blocService.retrieveAllBlocs();

        // Assertion
        assertEquals(2, blocs.size());
    }

    // Write similar test methods for other service methods

    @Test
    public void testAffecterChambresABloc() {
        // Mocking repository behavior
        Bloc bloc = new Bloc();
        when(blocRepository.findByNomBloc(anyString())).thenReturn(bloc);

        Chambre chambre1 = new Chambre();
        chambre1.setIdChambre(1L);
        chambre1.setNumeroChambre(101L);

        Chambre chambre2 = new Chambre();
        chambre2.setIdChambre(2L);
        chambre2.setNumeroChambre(102L);

        List<Long> chambreIds = Arrays.asList(101L, 102L);
        when(chambreRepository.findByNumeroChambre(anyLong())).thenReturn(chambre1, chambre2);

        // Testing service method
        Bloc resultBloc = blocService.affecterChambresABloc(chambreIds, "TestBloc");

        // Assertions
        assertNotNull(resultBloc);
        assertEquals(bloc, resultBloc);
        assertEquals(bloc, chambre1.getBloc());
        assertEquals(bloc, chambre2.getBloc());
    }

    @Test
    public void testAddBloc() {
        // Mocking repository behavior
        Bloc blocToAdd = new Bloc();
        Bloc savedBloc = new Bloc();
        savedBloc.setIdBloc(1L);
        when(blocRepository.save(any(Bloc.class))).thenReturn(savedBloc);

        // Testing service method
        Bloc resultBloc = blocService.addBloc(blocToAdd);

        // Assertions
        assertNotNull(resultBloc);
        assertEquals(savedBloc, resultBloc);
    }

    @Test
    public void testUpdateBloc() {
        // Mocking repository behavior
        Bloc blocToUpdate = new Bloc();
        Bloc updatedBloc = new Bloc();
        updatedBloc.setIdBloc(1L);
        when(blocRepository.save(any(Bloc.class))).thenReturn(updatedBloc);

        // Testing service method
        Bloc resultBloc = blocService.updateBloc(blocToUpdate);

        // Assertions
        assertNotNull(resultBloc);
        assertEquals(updatedBloc, resultBloc);
    }

    @Test
    public void testRetrieveBloc() {
        // Mocking repository behavior
        Long blocId = 1L;
        Bloc bloc = new Bloc();
        bloc.setIdBloc(blocId);
        when(blocRepository.findById(anyLong())).thenReturn(Optional.of(bloc));

        // Testing service method
        Bloc resultBloc = blocService.retrieveBloc(blocId);

        // Assertions
        assertNotNull(resultBloc);
        assertEquals(bloc, resultBloc);
    }

    @Test
    public void testRemoveBloc() {
        // Mocking repository behavior
        Long blocId = 1L;

        // Testing service method
        assertDoesNotThrow(() -> blocService.removeBloc(blocId));

        // Verify that deleteById method was called with correct argument
        verify(blocRepository, times(1)).deleteById(blocId);
    }

    // Write similar test methods for other service methods
    @Test
    public void testListeChambresParBloc() {
        // Prepare test data
        Bloc bloc1 = new Bloc();
        bloc1.setNomBloc("Bloc1");
        bloc1.setCapacitebloc(10L);

        Bloc bloc2 = new Bloc();
        bloc2.setNomBloc("Bloc2");
        bloc2.setCapacitebloc(20L);

        Chambre chambre1 = new Chambre();
        chambre1.setNumeroChambre(101L);
        chambre1.setTypeC(TypeChambre.SIMPLE); // Use enum constant TypeChambre.SIMPLE
        Chambre chambre2 = new Chambre();
        chambre2.setNumeroChambre(102L);
        chambre2.setTypeC(TypeChambre.DOUBLE); // Use enum constant TypeChambre.DOUBLE

        bloc1.setChambres(Set.of(chambre1, chambre2));
        bloc2.setChambres(Set.of());

        when(blocRepository.findAll()).thenReturn(Arrays.asList(bloc1, bloc2));

        // Call the method
        BlocServiceImp blocService = new BlocServiceImp(blocRepository, null); // Passing null for the chambreRepository as it's not required for this test
        blocService.listeChambresParBloc(); // This will execute the scheduled method

        // You can add assertions here to check the expected behavior, e.g., verifying that log statements are called correctly
        // For example, you can use verify to check if log.info is called with expected arguments based on your business logic
        verify(blocRepository, times(1)).findAll(); // Verifying that findAll method of blocRepository is called once
        // Add more assertions as per your business logic and expected behavior
    }


    @Test
    public void testTrierBlocsParNomBloc() {
        // Prepare test data
        Bloc bloc1 = new Bloc();
        bloc1.setNomBloc("B");
        Bloc bloc2 = new Bloc();
        bloc2.setNomBloc("A");
        List<Bloc> blocs = new ArrayList<>();
        blocs.add(bloc1);
        blocs.add(bloc2);

        // Call the method
        BlocServiceImp blocService = new BlocServiceImp(blocRepository, null);
        blocService.trierBlocsParNomBloc(blocs);

        // Assert that the list is sorted correctly
        List<String> sortedNames = new ArrayList<>();
        for (Bloc bloc : blocs) {
            sortedNames.add(bloc.getNomBloc());
        }
        List<String> expectedSortedNames = List.of("A", "B");
        assertIterableEquals(expectedSortedNames, sortedNames);
    }

    @Test
    public void testCheckAvailableRooms() {
        // Prepare test data
        Bloc bloc1 = new Bloc();
        bloc1.setNomBloc("Bloc1");
        bloc1.setCapacitebloc(10L);

        // Create chambres for bloc1
        Set<Chambre> chambresBloc1 = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            Chambre chambre = new Chambre();
            chambresBloc1.add(chambre);
        }
        bloc1.setChambres(chambresBloc1);

        // Mock blocRepository
        when(blocRepository.findAll()).thenReturn(Collections.singletonList(bloc1));

        // Call the method
        BlocServiceImp blocService = new BlocServiceImp(blocRepository, null);
        blocService.checkAvailableRooms(); // This will execute the scheduled method

        // Verify the number of available rooms for bloc1
        long expectedAvailableRoomsBloc1 = bloc1.getCapacitebloc() - chambresBloc1.size();
        long actualAvailableRoomsBloc1 = bloc1.getCapacitebloc() - bloc1.getChambres().size();
        assertEquals(expectedAvailableRoomsBloc1, actualAvailableRoomsBloc1, "Incorrect number of available rooms for bloc1");
    }


}

