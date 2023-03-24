package douglas.develop.core.repository;

import douglas.develop.core.enums.EstCivil;
import douglas.develop.core.model.Proprietario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {
    @Transactional(readOnly=true)
    @Query("SELECT inq from Proprietario inq WHERE inq.id = :id")
    Optional<Proprietario> buscaPorId(Long id);

    List<Proprietario> findByAtivoAndClient(Boolean ativo, Integer client);

    @Transactional(readOnly=true)
    @Query("SELECT p from Proprietario p WHERE p.ativo = :ativo")
    List<Proprietario> buscaSomenteAtivos(Boolean ativo, Pageable pageable);

    Optional<Proprietario> findByCpfcnpj(String cpfcnpj);

    @Transactional
    @Modifying
    @Query("update Proprietario p set p.nome = :nome, p.dtNiver = :dtNiver, p.estCivil = :estCivil, " +
            "p.sexo = :sexo, p.nacional = :nacional, p.naturalidade = :naturalidade " +
            "where p.id = :id")
    void altera(@Param("nome") String nome,
                        @Param("dtNiver") Date dtNiver,
                        @Param("estCivil") EstCivil estCivil,
                        @Param("sexo") String sexo,
                        @Param("nacional") String nacional,
                        @Param("naturalidade") String naturalidade,
                        @Param("id") Long id);
}
