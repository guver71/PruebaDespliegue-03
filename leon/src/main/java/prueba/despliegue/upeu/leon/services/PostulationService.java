package prueba.despliegue.upeu.leon.services;

import java.util.List;

import prueba.despliegue.upeu.leon.models.Postulation;

public interface PostulationService {
    Postulation save(Postulation postulation);
    List<Postulation> findAll();
    Postulation findById(Long id);
    Postulation update(Postulation postulation, Long id);
    void delete(Long id);
}
