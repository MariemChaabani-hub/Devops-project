package tn.esprit.tpfoyer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EtudiantServiceImplTest {

    @InjectMocks
    private EtudiantServiceImpl etudiantService; // Service à tester

    @Mock
    private EtudiantRepository etudiantRepository; // Repository à simuler

    private Etudiant etudiantTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        etudiantTest = new Etudiant(); // ✅ Ne pas redéclarer, juste l'initialiser
        etudiantTest.setNomEtudiant("John");
        etudiantTest.setPrenomEtudiant("Doe");
        etudiantTest.setCinEtudiant(123456789);
        etudiantTest.setDateNaissance(null);
    }

    @Test
    void testRetrieveAllEtudiants() {
        // Test de la méthode retrieveAllEtudiants
        when(etudiantRepository.findAll()).thenReturn(List.of(etudiantTest));  // Simulation de la réponse du repository

        var result = etudiantService.retrieveAllEtudiants();  // Appel de la méthode

        // Vérification des résultats
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getNomEtudiant());
    }

    @Test
    void testRetrieveEtudiant() {
        // Test de la méthode retrieveEtudiant
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiantTest));  // Simulation de la réponse du repository

        var result = etudiantService.retrieveEtudiant(1L);  // Appel de la méthode

        // Vérification des résultats
        assertNotNull(result);
        assertEquals("John", result.getNomEtudiant());
    }

    @Test
    void testAddEtudiant() {
        // Test de la méthode addEtudiant
        when(etudiantRepository.save(etudiantTest)).thenReturn(etudiantTest);  // Simulation de l'ajout de l'étudiant

        var result = etudiantService.addEtudiant(etudiantTest);  // Appel de la méthode

        // Vérification des résultats
        assertNotNull(result);
        assertEquals("John", result.getNomEtudiant());
    }

    @Test
    void testModifyEtudiant() {
        // Test de la méthode modifyEtudiant
        etudiantTest.setNomEtudiant("Johnathan");  // Modification du nom
        when(etudiantRepository.save(etudiantTest)).thenReturn(etudiantTest);  // Simulation de la modification de l'étudiant

        var result = etudiantService.modifyEtudiant(etudiantTest);  // Appel de la méthode

        // Vérification des résultats
        assertNotNull(result);
        assertEquals("Johnathan", result.getNomEtudiant());
    }

    @Test
    void testRemoveEtudiant() {
        // Test de la méthode removeEtudiant
        doNothing().when(etudiantRepository).deleteById(1L);  // Simulation de la suppression de l'étudiant

        etudiantService.removeEtudiant(1L);  // Appel de la méthode

        // Vérification que la suppression a été effectuée (en s'assurant que la méthode a été appelée)
        verify(etudiantRepository, times(1)).deleteById(1L);
    }

    @Test
    void testRecupererEtudiantParCin() {
        // Test de la méthode recupererEtudiantParCin
        when(etudiantRepository.findEtudiantByCinEtudiant(123456789)).thenReturn(etudiantTest);  // Simulation du retour de l'étudiant par CIN

        var result = etudiantService.recupererEtudiantParCin(123456789);  // Appel de la méthode

        // Vérification des résultats
        assertNotNull(result);
        assertEquals("John", result.getNomEtudiant());
    }
}
