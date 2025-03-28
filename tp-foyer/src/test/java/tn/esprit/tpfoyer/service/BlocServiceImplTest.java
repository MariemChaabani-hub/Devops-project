package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BlocServiceImplTest {

    @Mock
    private BlocRepository blocRepository;

    @InjectMocks
    private BlocServiceImpl blocService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testRetrieveAllBlocs() {
        // Arrange
        List<Bloc> mockBlocs = Arrays.asList(
                new Bloc(1L, "Bloc A", 100),
                new Bloc(2L, "Bloc B", 200)
        );
        when(blocRepository.findAll()).thenReturn(mockBlocs);

        // Act
        List<Bloc> result = blocService.retrieveAllBlocs();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Bloc A", result.get(0).getNomBloc());
    }

    @Test
    void testRetrieveBloc() {
        // Arrange
        Long blocId = 1L;
        Bloc mockBloc = new Bloc(blocId, "Bloc A", 100);
        when(blocRepository.findById(blocId)).thenReturn(Optional.of(mockBloc));

        // Act
        Bloc result = blocService.retrieveBloc(blocId);

        // Assert
        assertNotNull(result);
        assertEquals(blocId, result.getIdBloc());
        assertEquals("Bloc A", result.getNomBloc());
    }

    @Test
    void testAddBloc() {
        // Arrange
        Bloc newBloc = new Bloc( "New Bloc", 300);
        Bloc savedBloc = new Bloc(1L, "New Bloc", 300); // Simulate assigning an ID
        when(blocRepository.save(newBloc)).thenReturn(savedBloc);

        // Act
        Bloc result = blocService.addBloc(newBloc);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getIdBloc());
        assertEquals("New Bloc", result.getNomBloc());
    }

    @Test
    void testRemoveBloc() {
        // Arrange
        Long blocId = 1L;

        // Act
        blocService.removeBloc(blocId);

        // Assert
        verify(blocRepository, times(1)).deleteById(blocId); // Verify that deleteById was called
    }
}