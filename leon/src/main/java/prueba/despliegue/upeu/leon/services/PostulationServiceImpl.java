package prueba.despliegue.upeu.leon.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import prueba.despliegue.upeu.leon.exceptions.ResourceNotFoundException;
import prueba.despliegue.upeu.leon.models.Postulation;
import prueba.despliegue.upeu.leon.repositories.PostulationRepository;

@Service
@Transactional
public class PostulationServiceImpl implements PostulationService {

    @Autowired
    private PostulationRepository postulationRepository;

    @Override
    public Postulation save(Postulation postulation) {
        return postulationRepository.save(postulation);
    }

    @Override
    public List<Postulation> findAll() {
        return postulationRepository.findAll();
    }

    @Override
    public Postulation findById(Long id) {
        return postulationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Postulación no encontrada con el id: " + id));
    }

    @Override
    public Postulation update(Postulation postulation, Long id) {
        Postulation existingPostulation = postulationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Postulación no encontrada con el id: " + id));
        
        existingPostulation.setTitulo(postulation.getTitulo());
        existingPostulation.setDescripcion(postulation.getDescripcion());
        existingPostulation.setEmpresa(postulation.getEmpresa());
        existingPostulation.setFechaPublicacion(postulation.getFechaPublicacion());
        existingPostulation.setFechaCierre(postulation.getFechaCierre());
        existingPostulation.setSalario(postulation.getSalario());

        return postulationRepository.save(existingPostulation);
    }

    @Override
    public void delete(Long id) {
        Postulation postulation = postulationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Postulación no encontrada con el id: " + id));
        
        postulationRepository.delete(postulation);
    }
}
