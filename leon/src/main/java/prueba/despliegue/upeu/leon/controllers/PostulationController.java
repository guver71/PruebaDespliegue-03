package prueba.despliegue.upeu.leon.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prueba.despliegue.upeu.leon.models.Postulation;
import prueba.despliegue.upeu.leon.services.PostulationService;

@RestController
@RequestMapping("/api/postulations")
public class PostulationController {

    @Autowired
    private PostulationService postulationService;

    @PostMapping
    public ResponseEntity<Postulation> createPostulation(@RequestBody Postulation postulation) {
        Postulation createdPostulation = postulationService.save(postulation);
        return ResponseEntity.ok(createdPostulation);
    }

    @GetMapping
    public ResponseEntity<List<Postulation>> getAllPostulations() {
        List<Postulation> postulations = postulationService.findAll();
        return ResponseEntity.ok(postulations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postulation> getPostulationById(@PathVariable Long id) {
        Postulation postulation = postulationService.findById(id);
        return ResponseEntity.ok(postulation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Postulation> updatePostulation(@RequestBody Postulation postulation, @PathVariable Long id) {
        Postulation updatedPostulation = postulationService.update(postulation, id);
        return ResponseEntity.ok(updatedPostulation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostulation(@PathVariable Long id) {
        postulationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
