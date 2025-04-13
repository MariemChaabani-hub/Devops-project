package tn.esprit.tpfoyer;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.service.IFoyerService;
import tn.esprit.tpfoyer.service.FoyerServiceImpl;

import java.util.Optional;

public class FoyerServiceTest {

    @Mock
    private FoyerRepository foyerRepository;

    private IFoyerService foyerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialise les mocks
        foyerService = new FoyerServiceImpl(foyerRepository);  // Crée l'instance du service
    }

    @Test
    public void testAddFoyer() {
        Foyer foyer = new Foyer();
        foyer.setNomFoyer("Foyer Test");
        foyer.setCapaciteFoyer(100);

        when(foyerRepository.save(any(Foyer.class))).thenReturn(foyer);

        Foyer result = foyerService.addFoyer(foyer);

        assertNotNull(result);
        assertEquals("Foyer Test", result.getNomFoyer());
        assertEquals(100, result.getCapaciteFoyer());
    }

    @Test
    public void testRetrieveFoyer() {
        Foyer foyer = new Foyer();
        foyer.setIdFoyer(1L);
        foyer.setNomFoyer("Foyer Test");
        foyer.setCapaciteFoyer(100);

        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));

        Foyer result = foyerService.retrieveFoyer(1L);

        assertNotNull(result);
        assertEquals("Foyer Test", result.getNomFoyer());
    }

    @Test
    public void testRetrieveFoyerNotFound() {
        // Simuler que l'id du foyer n'existe pas
        when(foyerRepository.findById(1L)).thenReturn(Optional.empty());

        // Appeler la méthode et vérifier que le résultat est null
        Foyer result = foyerService.retrieveFoyer(1L);

        assertNull(result);  // Le résultat devrait être null
    }

    @Test
    public void testRemoveFoyer() {
        Foyer foyer = new Foyer();
        foyer.setIdFoyer(1L);

        doNothing().when(foyerRepository).deleteById(1L);

        foyerService.removeFoyer(1L);

        verify(foyerRepository, times(1)).deleteById(1L);
    }
}
