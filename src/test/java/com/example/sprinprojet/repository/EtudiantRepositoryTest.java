package com.example.sprinprojet.repository;

import com.example.sprinprojet.entity.Etudiant;
import com.example.sprinprojet.services.EtudiantServiceImp;
import com.example.sprinprojet.services.IEtudiantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EtudiantRepositoryTest {


    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImp etudiantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindBynomEt() {
        String nomEt = "meddeb";
        Etudiant expectedEtudiant = new Etudiant();
        expectedEtudiant.setNomEt(nomEt);
        when(etudiantRepository.findBynomEt(nomEt)).thenReturn(expectedEtudiant);

        Etudiant result = etudiantRepository.findBynomEt(nomEt);

        assertEquals(expectedEtudiant, result);
    }

    @Test
    void testFindByNomEtAndPrenomEt() {
        String nomEt = "sofien";
        String prenomEt = "meddeb";
        Etudiant expectedEtudiant = new Etudiant();
        expectedEtudiant.setNomEt(nomEt);
        expectedEtudiant.setPrenomEt(prenomEt);
        when(etudiantRepository.findByNomEtAndPrenomEt(nomEt, prenomEt)).thenReturn(expectedEtudiant);

        Etudiant result = etudiantRepository.findByNomEtAndPrenomEt(nomEt, prenomEt);

        assertEquals(expectedEtudiant, result);
    }
}