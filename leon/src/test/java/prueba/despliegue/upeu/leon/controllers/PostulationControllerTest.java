package prueba.despliegue.upeu.leon.controllers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import prueba.despliegue.upeu.leon.models.Postulation;
import prueba.despliegue.upeu.leon.services.PostulationService;

@ExtendWith(MockitoExtension.class)
public class PostulationControllerTest {

    @InjectMocks
    private PostulationController postulationController;

    @Mock
    private PostulationService postulationService;

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
    public void testGetAllPostulations() {
        List<Postulation> listaPostulations = Arrays.asList(postulation);

        // Simular el comportamiento del servicio
        given(postulationService.findAll()).willReturn(listaPostulations);

        // Llamar al controlador y verificar la respuesta
        ResponseEntity<List<Postulation>> response = postulationController.getAllPostulations();
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Desarrollador Java", response.getBody().get(0).getTitulo());
    }

    @Test
    public void testCreatePostulation() {
        given(postulationService.save(Mockito.any(Postulation.class))).willReturn(postulation);

        ResponseEntity<Postulation> response = postulationController.createPostulation(postulation);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Desarrollador Java", response.getBody().getTitulo());
    }

    @Test
    public void testGetPostulationByIdNotFound() {
        // Simular que el servicio lanza una excepción cuando la postulación no se encuentra
        given(postulationService.findById(1L)).willThrow(new RuntimeException("Postulación no encontrada"));

        // Llamar al controlador y verificar que responde con un error 404
        Exception exception = assertThrows(RuntimeException.class, () -> {
            postulationController.getPostulationById(1L);
        });

        assertEquals("Postulación no encontrada", exception.getMessage());
    }
}
