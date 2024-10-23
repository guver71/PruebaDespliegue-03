package prueba.despliegue.upeu.leon.repositories;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import prueba.despliegue.upeu.leon.models.Postulation;

@SpringBootTest
public class PostulationRepositoryIntegrationTest {

    @Autowired
    private PostulationRepository postulationRepository;

    private Postulation postulation;

    @BeforeEach
    public void setUp() {
        // Limpiar la base de datos antes de cada prueba
        postulationRepository.deleteAll();
        
        // Crear una postulación de ejemplo
        postulation = new Postulation();
        postulation.setTitulo("Desarrollador Java");
        postulation.setDescripcion("Desarrollador Java con experiencia.");
        postulation.setEmpresa("Empresa XYZ");
        postulation.setFechaPublicacion(new Date());
        postulation.setFechaCierre(new Date());
        postulation.setSalario(5000.0);
        
        postulationRepository.save(postulation);
    }

    @Test
    public void testFindAll() {
        List<Postulation> postulations = postulationRepository.findAll();
        assertEquals(1, postulations.size());
        assertEquals("Desarrollador Java", postulations.get(0).getTitulo());
    }

    @Test
    public void testFindById() {
        Postulation postulation = postulationRepository.findById(this.postulation.getId()).orElse(null);
        assertNotNull(postulation);
        assertEquals("Desarrollador Java", postulation.getTitulo());
    }

    @Test
    public void testCreatePostulation() {
        Postulation newPostulation = new Postulation();
        newPostulation.setTitulo("Analista de Datos");
        newPostulation.setDescripcion("Experiencia en SQL");
        newPostulation.setEmpresa("Empresa ABC");
        newPostulation.setFechaPublicacion(new Date());
        newPostulation.setFechaCierre(new Date());
        newPostulation.setSalario(4500.0);

        Postulation createdPostulation = postulationRepository.save(newPostulation);
        assertNotNull(createdPostulation);
        assertEquals("Analista de Datos", createdPostulation.getTitulo());
    }

    @Test
    public void testDeletePostulation() {
        postulationRepository.delete(postulation);
        List<Postulation> postulations = postulationRepository.findAll();
        assertEquals(0, postulations.size());
    }
}
