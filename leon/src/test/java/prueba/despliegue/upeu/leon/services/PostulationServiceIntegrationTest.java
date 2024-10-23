package prueba.despliegue.upeu.leon.services;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import prueba.despliegue.upeu.leon.models.Postulation;
import prueba.despliegue.upeu.leon.repositories.PostulationRepository;

@SpringBootTest
public class PostulationServiceIntegrationTest {

    @Autowired
    private PostulationService postulationService;

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
        
        postulationService.save(postulation);
    }

    @Test
    public void testFindAll() {
        List<Postulation> postulations = postulationService.findAll();
        assertEquals(1, postulations.size());
        assertEquals("Desarrollador Java", postulations.get(0).getTitulo());
    }

    @Test
    public void testFindById() {
        Postulation postulation = postulationService.findById(this.postulation.getId());
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

        Postulation createdPostulation = postulationService.save(newPostulation);
        assertNotNull(createdPostulation);
        assertEquals("Analista de Datos", createdPostulation.getTitulo());
    }

    @Test
    public void testUpdatePostulation() {
        Postulation updatedPostulation = new Postulation();
        updatedPostulation.setTitulo("Desarrollador Full Stack");
        updatedPostulation.setDescripcion("Con experiencia en JavaScript");
        updatedPostulation.setEmpresa("Empresa XYZ");
        updatedPostulation.setFechaPublicacion(new Date());
        updatedPostulation.setFechaCierre(new Date());
        updatedPostulation.setSalario(6000.0);

        Postulation result = postulationService.update(updatedPostulation, postulation.getId());
        assertEquals("Desarrollador Full Stack", result.getTitulo());
    }

    @Test
    public void testDeletePostulation() {
        postulationService.delete(postulation.getId());
        List<Postulation> postulations = postulationService.findAll();
        assertEquals(0, postulations.size());
    }
}
