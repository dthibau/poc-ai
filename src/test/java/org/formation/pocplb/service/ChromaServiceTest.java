package org.formation.pocplb.service;

import org.formation.pocplb.service.chroma.ChromaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChromaServiceTest {

    @Mock
    private ChromaService chromaService;

    @Test
    void testCountDocuments() {
        // Given
        when(chromaService.countDocuments()).thenReturn(42);

        // When
        int count = chromaService.countDocuments();

        // Then
        assertThat(count).isEqualTo(42);
    }
}