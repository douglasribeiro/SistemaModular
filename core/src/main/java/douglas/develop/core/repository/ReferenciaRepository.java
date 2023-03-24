package douglas.develop.core.repository;

import douglas.develop.core.model.Referencia;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ReferenciaRepository extends PagingAndSortingRepository<Referencia, Long> {

    List<Referencia> findByClient(Integer client);

    Optional<Referencia> findByIdAndClient(Long id, Integer client);

}
