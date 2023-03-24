package douglas.develop.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import douglas.develop.core.dto.ProprietarioDTO;
import douglas.develop.core.enums.EstCivil;
import douglas.develop.core.enums.Tipo;
import lombok.*;
import org.springframework.boot.configurationprocessor.json.JSONException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static douglas.develop.utils.Utils.clientUser;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(uniqueConstraints ={ @UniqueConstraint(name = "client_cpfcnpj",columnNames={"client", "cpfcnpj"}),
                            @UniqueConstraint(name = "client_email", columnNames ={"client", "email"}),
                            @UniqueConstraint(name = "client_identidade", columnNames = {"client", "identinscr"})})
public class Proprietario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Integer client;

    @Column(length = 100)
    private String nome;

    @Enumerated(EnumType.ORDINAL)
    private Tipo pessoa;

    @Column(length = 30)
    private String cpfcnpj;

    @Column(length = 25)
    private String identinscr;

    @Column(length = 100)
    private String email;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dtNiver;

    @Enumerated(EnumType.ORDINAL)
    private EstCivil estCivil;

    @Column(length = 20)
    private String sexo;

    @ToString.Exclude
    private Boolean ativo;

    @Column(length = 20)
    private String nacional;

    @Column(length = 20)
    private String naturalidade;

    @OneToMany(mappedBy="proprietario", cascade=CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(mappedBy="proprietario", cascade=CascadeType.ALL)
    private List<Telefone> telefones = new ArrayList<>();

    @OneToMany(mappedBy="proprietario", cascade=CascadeType.ALL)
    private List<Referencia> referencias = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "proprietario")
    private List<Imovel> imoveis = new ArrayList<>();

    public Proprietario(ProprietarioDTO proprietarioDTO) throws JSONException {
        this.id = proprietarioDTO.getId();
        this.client = clientUser();
        this.nome = proprietarioDTO.getNome();
        this.pessoa = proprietarioDTO.getPessoa();
        this.cpfcnpj = proprietarioDTO.getCpfcnpj();
        this.identinscr = proprietarioDTO.getIdentinscr();
        this.email = proprietarioDTO.getEmail();
        this.dtNiver = proprietarioDTO.getDtNiver();
        this.estCivil = proprietarioDTO.getEstCivil();
        this.sexo = proprietarioDTO.getSexo();
        this.nacional = proprietarioDTO.getNacional();
        this.naturalidade = proprietarioDTO.getNaturalidade();
        this.enderecos = proprietarioDTO.getEnderecos();
        this.telefones = proprietarioDTO.getTelefones();
        this.referencias = proprietarioDTO.getReferencias();
        this.imoveis = proprietarioDTO.getImoveis();
    }
}
