


package com.example.sprinprojet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.sprinprojet.entity.Bloc;
import com.example.sprinprojet.entity.Foyer;
import com.example.sprinprojet.entity.Universite;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FoyerTest {

    private Foyer foyer;

    @BeforeEach
    void setUp() {
        // Initialize the foyer object
        foyer = new Foyer();
        foyer.setIdFoyer(1L);
        foyer.setNomFoyer("Test Foyer");
        foyer.setCapaciteFoyer(100);
        foyer.setArchived(false);

        // Initialize an empty list for blocs
        List<Bloc> blocs = new ArrayList<>();
        foyer.setBlocs(blocs);

        // Initialize an empty universite object
        Universite universite = new Universite();
        foyer.setUniversite(universite);
    }

    @Test
    void testFoyerAttributes() {
        assertThat(foyer.getIdFoyer()).isEqualTo(1L);
        assertThat(foyer.getNomFoyer()).isEqualTo("Test Foyer");
        assertThat(foyer.getCapaciteFoyer()).isEqualTo(100);

    }

    @Test
    void testEmptyBlocsList() {
        assertThat(foyer.getBlocs()).isEmpty();
    }

    @Test
    void testUniversiteAssociation() {
        assertThat(foyer.getUniversite()).isNotNull();
    }
}
