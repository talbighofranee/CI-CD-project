package com.example.sprinprojet.repository;

import com.example.sprinprojet.entity.Bloc;
import com.example.sprinprojet.services.BlocServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BlocRepositoryTest {
    @Mock
    private BlocRepository blocRepository;

    @InjectMocks
    private BlocServiceImp blocServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByNomBloc() {
        // Given
        String blocName = "TestBloc";
        Bloc bloc = new Bloc();
        bloc.setNomBloc(blocName);

        // Mocking repository method
        when(blocRepository.findByNomBloc(blocName)).thenReturn(bloc);

        // When
        Bloc foundBloc = blocRepository.findByNomBloc(blocName);

        // Then
        assertEquals(bloc, foundBloc);
    }

    @Test
    void testGetStatistiquesChambresParBloc() {
        // Given
        Object[] data1 = {"Bloc1", 10L};
        Object[] data2 = {"Bloc2", 15L};
        List<Object[]> expectedResult = Arrays.asList(data1, data2);

        // Mocking repository method
        when(blocRepository.getStatistiquesChambresParBloc()).thenReturn(expectedResult);

        // When
        List<Object[]> actualResult = blocRepository.getStatistiquesChambresParBloc();

        // Then
        assertEquals(expectedResult, actualResult);
    }
}