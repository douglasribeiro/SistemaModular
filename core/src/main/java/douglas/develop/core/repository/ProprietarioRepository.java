package douglas.develop.core.repository;

import douglas.develop.core.model.Proprietario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

public interface ProprietarioRepository extends PagingAndSortingRepository<Proprietario, Long> {
    @Transactional(readOnly=true)
    @Query("SELECT inq from Proprietario inq WHERE inq.id = :id")
    Optional<Proprietario> buscaPorId(Long id);

    @Transactional(readOnly=true)
    @Query("SELECT p from Proprietario p WHERE p.ativo = :ativo")
    List<Proprietario> buscaSomenteAtivos(Boolean ativo);

    Optional<Proprietario> findByCpfcnpj(String cpfcnpj);
}
