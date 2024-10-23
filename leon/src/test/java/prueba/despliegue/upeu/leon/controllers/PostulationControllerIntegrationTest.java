package prueba.despliegue.upeu.leon.controllers;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import prueba.despliegue.upeu.leon.models.Postulation;
import prueba.despliegue.upeu.leon.repositories.PostulationRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostulationControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private PostulationRepository postulationRepository;

    private Postulation postulation;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        
        // Limpiar la base de datos antes de cada prueba
        postulationRepository.deleteAll();
        
        // Crear una postulación para usar en las pruebas
        postulation = new Postulation();
        postulation.setTitulo("Desarrollador Java");
        postulation.setDescripcion("Desarrollador Java con 3 años de experiencia.");
        postulation.setEmpresa("Empresa XYZ");
        postulation.setFechaPublicacion(new Date());
        postulation.setFechaCierre(new Date());
        postulation.setSalario(5000.0);
        postulationRepository.save(postulation);
    }

    @Test
    public void testGetAllPostulations() {
        given()
            .when().get("/api/postulations")
            .then()
            .statusCode(200)
            .body("size()", is(1))
            .body("[0].titulo", equalTo("Desarrollador Java"));
    }

    @Test
    public void testGetPostulationById() {
        Long id = postulation.getId();
        given()
            .when().get("/api/postulations/{id}", id)
            .then()
            .statusCode(200)
            .body("titulo", equalTo("Desarrollador Java"))
            .body("empresa", equalTo("Empresa XYZ"));
    }

    @Test
    public void testCreatePostulation() {
        Postulation newPostulation = new Postulation();
        newPostulation.setTitulo("Analista de Datos");
        newPostulation.setDescripcion("Analista con experiencia en Python y SQL.");
        newPostulation.setEmpresa("Empresa ABC");
        newPostulation.setFechaPublicacion(new Date());
        newPostulation.setFechaCierre(new Date());
        newPostulation.setSalario(4500.0);

        given()
            .contentType(ContentType.JSON)
            .body(newPostulation)
            .when().post("/api/postulations")
            .then()
            .statusCode(200)
            .body("titulo", equalTo("Analista de Datos"))
            .body("empresa", equalTo("Empresa ABC"));
    }

    @Test
    public void testUpdatePostulation() {
        Long id = postulation.getId();
        postulation.setTitulo("Desarrollador Full Stack");

        given()
            .contentType(ContentType.JSON)
            .body(postulation)
            .when().put("/api/postulations/{id}", id)
            .then()
            .statusCode(200)
            .body("titulo", equalTo("Desarrollador Full Stack"));
    }

    @Test
    public void testDeletePostulation() {
        Long id = postulation.getId();

        given()
            .when().delete("/api/postulations/{id}", id)
            .then()
            .statusCode(204);

        List<Postulation> allPostulations = postulationRepository.findAll();
        assertEquals(0, allPostulations.size());
    }
}
