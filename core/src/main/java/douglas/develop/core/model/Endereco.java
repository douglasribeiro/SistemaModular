package douglas.develop.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import douglas.develop.core.enums.TipoEndereco;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer client;

    @Column(length = 80)
    private String logradouro;

    @Column(length = 8)
    private String numero;

    @Column(length = 100)
    private String complemento;

    @Column(length = 45)
    private String bairro;

    @Column(length = 10)
    private String cep;

    @Enumerated(EnumType.ORDINAL)
    private TipoEndereco tipoEndereco;

    @Column(length = 75)
    private String cidade;

    @Column(length = 2)
    private String estado;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="inquilino_id")
    private Inquilino inquilino;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="proprietario_id")
    private Proprietario proprietario;

    public Endereco() {}

    public Endereco(Integer id, String logradouro, String numero, String complemento, String bairro, String cep,
                    TipoEndereco tipoEndereco, String cidade, String estado, Inquilino inquilino, Proprietario proprietario) {
        super();
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.tipoEndereco = tipoEndereco;
        this.cidade = cidade;
        this.estado = estado;
        this.inquilino = inquilino;
        this.proprietario = proprietario;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public TipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Endereco other = (Endereco) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}