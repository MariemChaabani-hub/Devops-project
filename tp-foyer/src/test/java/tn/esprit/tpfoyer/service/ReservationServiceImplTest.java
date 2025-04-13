package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllReservations() {
        List<Reservation> mockReservations = Arrays.asList(
                new Reservation("R1", new Date(), true),
                new Reservation("R2", new Date(), false)
        );
        when(reservationRepository.findAll()).thenReturn(mockReservations);

        List<Reservation> result = reservationService.retrieveAllReservations();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("R1", result.get(0).getIdReservation());
    }

    @Test
    void testRetrieveReservation() {
        String reservationId = "R1";
        Reservation mockReservation = new Reservation(reservationId, new Date(), true);
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(mockReservation));

        Reservation result = reservationService.retrieveReservation(reservationId);

        assertNotNull(result);
        assertEquals(reservationId, result.getIdReservation());
    }

    @Test
    void testAddReservation() {
        Reservation newReservation = new Reservation("R3", new Date(), false);
        when(reservationRepository.save(newReservation)).thenReturn(newReservation);

        Reservation result = reservationService.addReservation(newReservation);

        assertNotNull(result);
        assertEquals("R3", result.getIdReservation());
    }

    @Test
    void testModifyReservation() {
        Reservation existingReservation = new Reservation("R4", new Date(), true);
        existingReservation.setEstValide(false); // simulate update

        when(reservationRepository.save(existingReservation)).thenReturn(existingReservation);

        Reservation result = reservationService.modifyReservation(existingReservation);

        assertNotNull(result);
        assertFalse(result.isEstValide());
    }

    @Test
    void testTrouverResSelonDateEtStatus() {
        Date date = new Date();
        boolean status = true;

        List<Reservation> filtered = Arrays.asList(
                new Reservation("R5", new Date(date.getTime() - 100000), true)
        );

        when(reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(date, status))
                .thenReturn(filtered);

        List<Reservation> result = reservationService.trouverResSelonDateEtStatus(date, status);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).isEstValide());
    }

    @Test
    void testRemoveReservation() {
        String reservationId = "R6";

        reservationService.removeReservation(reservationId);

        verify(reservationRepository, times(1)).deleteById(reservationId);
    }
}
