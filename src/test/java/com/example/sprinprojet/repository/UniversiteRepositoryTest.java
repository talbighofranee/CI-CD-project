package com.example.sprinprojet.repository;

import com.example.sprinprojet.entity.Universite;
import com.example.sprinprojet.services.UniversiteServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UniversiteRepositoryTest {


    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
     void testFindByNomUniversite() {
        String nomUniversite = "esprit";
        Universite universite = new Universite();
        when(universiteRepository.findByNomUniversite(nomUniversite)).thenReturn(universite);

        Universite result = universiteRepository.findByNomUniversite(nomUniversite);

        assertEquals(universite, result);
    }

    @Test
     void testCountUniversites() {
        int count = 5;
        when(universiteRepository.countUniversites()).thenReturn(count);

        int result = universiteRepository.countUniversites();

        assertEquals(count, result);
    }

    @Test
     void testRechercheParNom() {
        String nomUniversite = "sesame";
        List<Universite> universites = new ArrayList<>();
        universites.add(new Universite());
        when(universiteRepository.rechercheParNom(nomUniversite)).thenReturn(universites);

        List<Universite> result = universiteRepository.rechercheParNom(nomUniversite);

        assertEquals(universites.size(), result.size());
        assertTrue(result.containsAll(universites));
    }

}