package com.example.sprinprojet.services;

import com.example.sprinprojet.entity.Etudiant;
import com.example.sprinprojet.repository.EtudiantRepository;
import com.example.sprinprojet.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EtudiantServiceImpTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private EtudiantServiceImp etudiantService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
     void testRetrieveAllEtudiants() {
        // Créer une liste de faux objets Etudiant
        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(new Etudiant(1L,"sofien","meddeb",123456L,"esprit","sof@gmail.com"));
        etudiants.add(new Etudiant(2L,"oussema","hnich",145263L,"esprit","ouss@gmail.com"));

        // Définir le comportement du repository mock
        when(etudiantRepository.findAll()).thenReturn(etudiants);

        // Appeler la méthode du service à tester
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        // Vérifier le résultat
        assertEquals(etudiants.size(), result.size());
    }

    @Test
     void testAddEtudiant() {
        // Créer un faux objet Etudiant
        Etudiant etudiant =new Etudiant(1L,"sofien","meddeb",123456L,"esprit","sof@gmail.com");

        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // Appeler la méthode du service à tester
        Etudiant result = etudiantService.addEtudiant(etudiant);

        // Vérifier le résultat
        assertNotNull(result);
    }

    @Test
     void testUpdateEtudiant() {
        Etudiant etudiant = new Etudiant(10L,"ahmed","meddeb",126456L,"esprit","ahmed@gmail.com");

        // Définir le comportement du repository mock
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // Appeler la méthode du service à tester
        Etudiant result = etudiantService.updateEtudiant(etudiant);

        // Vérifier le résultat
        assertNotNull(result);
    }

    @Test
     void testRetrieveEtudiant() {
        Etudiant etudiant = new Etudiant(16L,"toufa","meddeb",123456L,"esprit","sof@gmail.com");
        Long idEtudiant = 1L;

        // Définir le comportement du repository mock
        when(etudiantRepository.findById(idEtudiant)).thenReturn(Optional.of(etudiant));

        Etudiant result = etudiantService.retrieveEtudiant(idEtudiant);

        assertNotNull(result);
    }

    @Test
     void testRemoveEtudiant() {
        Long idEtudiant = 1L;

        etudiantService.removeEtudiant(idEtudiant);

        verify(etudiantRepository).deleteById(idEtudiant);
    }

}