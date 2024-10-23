package prueba.despliegue.upeu.leon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import prueba.despliegue.upeu.leon.models.Postulation;

@Repository
public interface PostulationRepository extends JpaRepository<Postulation, Long> {
}
