package douglas.develop.core.repository;

import douglas.develop.core.model.Imovel;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ImovelRepository extends PagingAndSortingRepository<Imovel, Long> {
}
