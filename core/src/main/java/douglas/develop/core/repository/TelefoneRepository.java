package douglas.develop.core.repository;

import douglas.develop.core.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

    List<Telefone> findByClient(Integer client);

    Optional<Telefone> findByIdAndClient(Long id, Integer client);
}
