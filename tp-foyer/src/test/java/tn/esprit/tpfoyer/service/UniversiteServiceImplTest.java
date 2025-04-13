package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UniversiteServiceImplTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllUniversites() {
        // Arrange
        List<Universite> mockUniversites = Arrays.asList(
                new Universite(1L, "Universite A", "Address A"),
                new Universite(2L, "Universite B", "Address B")
        );
        when(universiteRepository.findAll()).thenReturn(mockUniversites);

        // Act
        List<Universite> result = universiteService.retrieveAllUniversites();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Universite A", result.get(0).getNomUniversite());
    }

    @Test
    void testRetrieveUniversite() {
        // Arrange
        Long universiteId = 1L;
        Universite mockUniversite = new Universite(universiteId, "Universite A", "Address A");
        when(universiteRepository.findById(universiteId)).thenReturn(Optional.of(mockUniversite));

        // Act
        Universite result = universiteService.retrieveUniversite(universiteId);

        // Assert
        assertNotNull(result);
        assertEquals(universiteId, result.getIdUniversite());
        assertEquals("Universite A", result.getNomUniversite());
    }

    @Test
    void testAddUniversite() {
        // Arrange
        Universite savedUniversite = new Universite(1L, "New Universite", "New Address");
        when(universiteRepository.save(savedUniversite)).thenReturn(savedUniversite);

        // Act
        Universite result = universiteService.addUniversite(savedUniversite);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getIdUniversite());
        assertEquals("New Universite", result.getNomUniversite());
    }

    @Test
    void testRemoveUniversite() {
        // Arrange
        Long universiteId = 1L;

        // Act
        universiteService.removeUniversite(universiteId);

        // Assert
        verify(universiteRepository, times(1)).deleteById(universiteId);
    }
}