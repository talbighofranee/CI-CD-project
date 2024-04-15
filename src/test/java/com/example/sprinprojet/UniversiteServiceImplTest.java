package com.example.sprinprojet;

import com.example.sprinprojet.entity.Foyer;

import com.example.sprinprojet.entity.Universite;
import com.example.sprinprojet.repository.FoyerRepository;
import com.example.sprinprojet.repository.UniversiteRepository;
import com.example.sprinprojet.services.UniversiteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UniversiteServiceImplTest  {

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;





    @Test
    public void testRetrieveAllUniversites() {
        // Créer une liste de faux objets Université
        List<Universite> universites = new ArrayList<>();
        universites.add(new Universite(1L, "Universite 1","adreess1"));
        universites.add(new Universite(2L, "Universite 2","address2"));

        // Définir le comportement du repository mock
        when(universiteRepository.findAll()).thenReturn(universites);

        // Appeler la méthode du service à tester
        List<Universite> result = universiteService.retrieveAllUniversites();

        // Vérifier le résultat
        assertEquals(2, result.size());
        assertEquals("Universite 1", result.get(0).getNomUniversite());
        assertEquals("Universite 2", result.get(1).getNomUniversite());
    }
    @Test
    void testGetbyidUni() {
        Universite universite = new Universite();
        Long id = 1L;
        when(universiteRepository.findById(id)).thenReturn(java.util.Optional.of(universite));

        Universite result = universiteService.getbyidUni(id);

        assertEquals(universite, result);
    }

    @Test
    public void testAddUniversites() {
        // Créer un faux objet Université
        Universite universite = new Universite(1L, "Universite 1","adr");

        // Définir le comportement du repository mock
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Appeler la méthode du service à tester
        Universite result = universiteService.addUniversites(universite);

        // Vérifier le résultat
        assertNotNull(result);
        assertEquals("Universite 1", result.getNomUniversite());
    }


    @Test
    public void testRemoveUniversites() {
        Long id = 1L;
        universiteService.removeUniversites(id);

        verify(universiteRepository, times(1)).deleteById(id);
    }

    @Test
    public void testAffecterFoyerAUniversite() {
        Long idFoyer = 1L;
        String nomUniversite = "Universite1";
        Foyer foyer = new Foyer();
        Universite universite = new Universite();

        when(foyerRepository.findById(idFoyer)).thenReturn(Optional.of(foyer));
        when(universiteRepository.findByNomUniversite(nomUniversite)).thenReturn(universite);

        Universite result = universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);

        assertEquals(universite, result);
        assertEquals(universite, foyer.getUniversite());
    }

    @Test
    public void testDesaffecterFoyerAUniversite() {
        Long idFoyer = 1L;
        Foyer foyer = new Foyer();
        Universite universite = new Universite();
        foyer.setUniversite(universite);

        when(foyerRepository.findById(idFoyer)).thenReturn(Optional.of(foyer));

        Universite result = universiteService.desaffecterFoyerAUniversite(idFoyer);

        assertTrue(foyer.getUniversite() == null);
        assertEquals(universite, result);
    }

    @Test
    public void testRechercheParNom() {
        // Créer une liste de faux objets Université
        List<Universite> universites = new ArrayList<>();
        universites.add(new Universite(1L, "Universite 1","adr1"));
        universites.add(new Universite(2L, "Universite 2","adr2"));

        // Définir le comportement du repository mock
        when(universiteRepository.rechercheParNom("Universite")).thenReturn(universites);

        // Appeler la méthode du service à tester
        List<Universite> result = universiteService.rechercheParNom("Universite");

        // Vérifier le résultat
        assertEquals(2, result.size());
        assertEquals("Universite 1", result.get(0).getNomUniversite());
        assertEquals("Universite 2", result.get(1).getNomUniversite());
    }

    @Test
    public void testCountUniversites() {
        // Définir le comportement du repository mock
        when(universiteRepository.countUniversites()).thenReturn(5);

        // Appeler la méthode du service à tester
        Integer result = universiteService.countUniversites();

        // Vérifier le résultat
        assertEquals(5, result.intValue());
    }



    }







