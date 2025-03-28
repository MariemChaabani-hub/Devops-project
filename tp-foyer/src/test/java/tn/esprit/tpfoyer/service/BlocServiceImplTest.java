package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;

import java.util.List;

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