package douglas.develop.core.repository;

import douglas.develop.core.model.Curso;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CursoRepository extends PagingAndSortingRepository<Curso, Long> {
}
