package prueba.despliegue.upeu.leon.repositories;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import prueba.despliegue.upeu.leon.models.Postulation;

@ExtendWith(MockitoExtension.class)
public class PostulationRepositoryTest {

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
    public void testFindById() {
        given(postulationRepository.findById(1L)).willReturn(Optional.of(postulation));

        Optional<Postulation> result = postulationRepository.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    public void testFindAll() {
        List<Postulation> postulations = List.of(postulation);

        given(postulationRepository.findAll()).willReturn(postulations);

        List<Postulation> result = postulationRepository.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}
