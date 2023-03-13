package douglas.develop.core.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import douglas.develop.core.enums.EstCivil;
import douglas.develop.core.enums.Tipo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Inquilino extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    @Column(length = 100)
    private String nome;

    @Enumerated(EnumType.ORDINAL)
    private Tipo pessoa;

    @Column(unique=true, length = 20)
    private String cpfcnpj;

    @Column(length = 25)
    private String identinscr;

    @Column(unique=true)
    private String email;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dtNiver;

    @Enumerated(EnumType.ORDINAL)
    private EstCivil estCivil;

    @Column(length = 20)
    private String sexo;

    private Boolean ativo;

    @Column(length = 20)
    private String nacional;

    @Column(length = 20)
    private String naturalidade;

    private List<Endereco> enderecos = new ArrayList<>();

    private List<Telefone> telefones = new ArrayList<>();

    private List<Referencia> referencias = new ArrayList<>();

    public Inquilino() {}

    public Inquilino(Long id, String nome, Tipo pessoa, String cpfcnpj, String identinscr, String email, Date dtNiver,
                     EstCivil estCivil, String sexo, Boolean ativo, String nacional, String naturalidade) {
        super();
        this.id = id;
        this.nome = nome;
        this.pessoa = pessoa;
        this.cpfcnpj = cpfcnpj;
        this.identinscr = identinscr;
        this.email = email;
        this.dtNiver = dtNiver;
        this.estCivil = estCivil;
        this.sexo = sexo;
        this.ativo = ativo;
        this.nacional = nacional;
        this.naturalidade = naturalidade;
    }

    @Override
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tipo getPessoa() {
        return pessoa;
    }

    public void setPessoa(Tipo pessoa) {
        this.pessoa = pessoa;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }

    public String getIdentinscr() {
        return identinscr;
    }

    public void setIdentinscr(String identinscr) {
        this.identinscr = identinscr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDtNiver() {
        return dtNiver;
    }

    public void setDtNiver(Date dtNiver) {
        this.dtNiver = dtNiver;
    }

    public EstCivil getEstCivil() {
        return estCivil;
    }

    public void setEstCivil(EstCivil estCivil) {
        this.estCivil = estCivil;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getNacional() {
        return nacional;
    }

    public void setNacional(String nacional) {
        this.nacional = nacional;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    @OneToMany(mappedBy="inquilino", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @OneToMany(mappedBy="inquilino", cascade=CascadeType.ALL)
    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    @OneToMany(mappedBy="inquilino", cascade=CascadeType.ALL)
    public List<Referencia> getReferencias() {
        return referencias;
    }

    public void setReferencias(List<Referencia> referencias) {
        this.referencias = referencias;
    }

}
