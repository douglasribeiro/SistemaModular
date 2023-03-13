package douglas.develop.core.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import douglas.develop.core.enums.TipoTelefone;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Telefone implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 2)
    private String ddd;

    @Column(length = 10)
    private String numero;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="inquilino_id")
    private Inquilino inquilino;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="proprietario_id")
    private Proprietario proprietario;

    private Integer tipo;

    public Telefone() {
        super();
    }

    public Telefone(Integer id, String ddd, String numero, TipoTelefone tipo,
                    Inquilino inquilino, Proprietario proprietario) {
        super();
        this.id = id;
        this.ddd = ddd;
        this.numero = numero;
        this.tipo = (tipo==null) ? null : tipo.getCodigo();
        this.inquilino = inquilino;
        this.proprietario = proprietario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public TipoTelefone getTipo() {
        return TipoTelefone.toEnum(tipo);
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo.getCodigo();
    }



}
