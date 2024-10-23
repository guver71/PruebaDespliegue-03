package prueba.despliegue.upeu.leon.services;

import prueba.despliegue.upeu.leon.models.Postulation;
import prueba.despliegue.upeu.leon.repositories.PostulationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class PostulationServiceTest {

    @InjectMocks
    private PostulationServiceImpl postulationService;

    @Mock
    private PostulationRepository postulationRepository;

    private Postulation postulation;

    @BeforeEach
    public void setUp() {
        postulation = new Postulation();
        postulation.setId(1L);
        postulation.setTitulo("Desarrollador Java");
        postulation.setDescripcion("Con 3 años de experiencia.");
        postulation.setEmpresa("Empresa XYZ");
        postulation.setSalario(5000.0);
    }

    @Test
    public void testGetPostulationById() {
        // Simulamos que el repositorio devuelve una postulación
        given(postulationRepository.findById(1L)).willReturn(Optional.of(postulation));

        Postulation result = postulationService.findById(1L);
        
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Desarrollador Java", result.getTitulo());
    }

    @Test
    public void testCreatePostulation() {
        given(postulationRepository.save(Mockito.any(Postulation.class))).willReturn(postulation);

        Postulation result = postulationService.save(postulation);
        
        assertNotNull(result);
        assertEquals("Desarrollador Java", result.getTitulo());
    }
}
