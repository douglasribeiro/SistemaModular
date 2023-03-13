package douglas.develop.core.repository;

import douglas.develop.core.model.Telefone;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TelefoneRepository extends PagingAndSortingRepository<Telefone, Long> {
}
