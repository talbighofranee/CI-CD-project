package com.example.sprinprojet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.sprinprojet.entity.Foyer;
import com.example.sprinprojet.repository.FoyerRepository;
import com.example.sprinprojet.services.FoyerServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FoyerServiceTest {
    @Mock
private FoyerRepository foyerRepository;

    @InjectMocks
    private FoyerServiceImp foyerService;

    private Foyer foyer;

    @BeforeEach
    void setUp() {
        foyer = new Foyer();
        foyer.setIdFoyer(1L);
        foyer.setNomFoyer("Test Foyer");
        foyer.setCapaciteFoyer(10);
    }

    @Test
    void addFoyerTest() {
        // Mock the behavior of foyerRepository.save()
        when(foyerRepository.save(foyer)).thenReturn(foyer);

        // Call the method under test
        Foyer addedFoyer = foyerService.addFoyer(foyer);

        // Assertions
        assertThat(addedFoyer).isNotNull();
        assertThat(addedFoyer.getNomFoyer()).isEqualTo(foyer.getNomFoyer());
        verify(foyerRepository).save(foyer);
    }

    @Test
    void retrieveFoyerTest() {
        Long foyerId = 1L;
        // Mock the behavior of foyerRepository.findById()
        when(foyerRepository.findById(foyerId)).thenReturn(Optional.of(foyer));

        // Call the method under test
        Foyer retrievedFoyer = foyerService.retrieveFoyer(foyerId);

        // Assertions
        assertThat(retrievedFoyer).isNotNull();
        assertThat(retrievedFoyer.getNomFoyer()).isEqualTo(foyer.getNomFoyer());
        verify(foyerRepository).findById(foyerId);
    }

    @Test
    void removeFoyerTest() {
        Long foyerId = 1L;
        // Call the method under test
        foyerService.removeFoyer(foyerId);

        // Verify that foyerRepository.deleteById() is called with the correct id
        verify(foyerRepository).deleteById(foyerId);
    }

    // Mock tests

    @Test
    void retrieveAllFoyersMockTest() {
        // Create a list of foyers for testing
        List<Foyer> foyers = new ArrayList<>();
        foyers.add(foyer);

        // Mock the behavior of foyerRepository.findAll()
        when(foyerRepository.findAll()).thenReturn(foyers);

        // Call the method under test
        List<Foyer> retrievedFoyers = foyerService.retrieveAllFoyers();

        // Assertions
        assertThat(retrievedFoyers).isNotNull();
        assertThat(retrievedFoyers).hasSize(1);
        assertThat(retrievedFoyers.get(0).getNomFoyer()).isEqualTo(foyer.getNomFoyer());
        verify(foyerRepository).findAll();
    }

    @Test
    void addFoyerMockTest() {
        // Mock the behavior of foyerRepository.save()
        when(foyerRepository.save(foyer)).thenReturn(foyer);

        // Call the method under test
        Foyer addedFoyer = foyerService.addFoyer(foyer);

        // Assertions
        assertThat(addedFoyer).isNotNull();
        assertThat(addedFoyer.getNomFoyer()).isEqualTo(foyer.getNomFoyer());
        verify(foyerRepository).save(foyer);
    }

    @Test
    void retrieveFoyerMockTest() {
        Long foyerId = 1L;
        // Mock the behavior of foyerRepository.findById()
        when(foyerRepository.findById(foyerId)).thenReturn(Optional.of(foyer));

        // Call the method under test
        Foyer retrievedFoyer = foyerService.retrieveFoyer(foyerId);

        // Assertions
        assertThat(retrievedFoyer).isNotNull();
        assertThat(retrievedFoyer.getNomFoyer()).isEqualTo(foyer.getNomFoyer());
        verify(foyerRepository).findById(foyerId);
    }
}