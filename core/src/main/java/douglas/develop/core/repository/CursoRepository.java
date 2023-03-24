package douglas.develop.core.repository;

import douglas.develop.core.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByClient(Integer client);

    Optional<Curso> findByIdAndClient(Long id, Integer client);
}
