package com.example.sprinprojet.repository;

import com.example.sprinprojet.entity.Chambre;
import com.example.sprinprojet.entity.TypeChambre;
import com.example.sprinprojet.services.ChambreServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class ChambreRepositoryTest {



    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreServiceImp chambreService;

    @Test
    public void testFindByNumeroChambre() {
        long numChambre = 123;
        Chambre chambre = new Chambre();
        chambre.setNumeroChambre(numChambre);

        when(chambreRepository.findByNumeroChambre(numChambre)).thenReturn(chambre);

        Chambre foundChambre = chambreRepository.findByNumeroChambre(numChambre);
        assertEquals(numChambre, foundChambre.getNumeroChambre());
    }

    @Test
    public void testCountChambresByTypeC() {
        TypeChambre typeChambre = TypeChambre.SIMPLE;
        // Set up your repository to return the desired count
        when(chambreRepository.countChambresByTypeC(typeChambre)).thenReturn(5L);

        long count = chambreRepository.countChambresByTypeC(typeChambre);
        assertEquals(5, count);
    }

}

