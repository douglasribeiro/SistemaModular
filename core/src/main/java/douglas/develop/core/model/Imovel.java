package douglas.develop.core.model;


import douglas.develop.core.enums.SimNao;
import douglas.develop.core.enums.TipoEdificacao;
import douglas.develop.core.enums.TipoImovel;
import douglas.develop.core.enums.TipoServico;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Imovel extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @Column(length = 100)
    private String nomProprietario;

    @Column(length = 20)
    private String matricula;

    @Column(length = 256)
    private String complementoImovel;

    private Integer condominio;

    private Integer tipo; //TipoImovel;

    private Integer edificacao; //TipoEdificacao

    private Integer servico; //TipoServico

    @Column(length = 20)
    private String areaTotal;

    @Column(length = 20)
    private String areaConstruida;

    @Column(length = 20)
    private String banheiros;

    @Column(length = 20)
    private String quartos;

    @Column(length = 20)
    private String suites;

    @Column(length = 20)
    private String comodos;

    @Column(length = 20)
    private String vagas;

    @Column(length = 256)
    private String observacao;

    private List<Foto> fotos = new ArrayList<>();

    private Proprietario proprietario;

    // Endereco

    @Column(length = 80)
    private String logradouro;

    @Column(length = 8)
    private String numero;

    @Column(length = 100)
    private String complementoEndereco;

    @Column(length = 45)
    private String bairro;

    @Column(length = 10)
    private String cep;

    @Column(length = 75)
    private String cidade;

    @Column(length = 2)
    private String estado;

    private Cliente cliente;


    public Imovel() {}

    public Imovel(Long id, String nomProprietario, String matricula, String complementoImovel, Integer condominio,
                  Integer tipo, Integer edificacao, Integer servico, String areaTotal, String areaConstruida,
                  String banheiros, String quartos, String suites, String comodos, String vagas, String observacao,
                  Proprietario proprietario, String logradouro, String numero, String complementoEndereco,
                  String bairro, String cep, String cidade, String estado, Cliente cliente) {
        super();
        this.id = id;
        this.nomProprietario = nomProprietario;
        this.matricula = matricula;
        this.complementoImovel = complementoImovel;
        this.condominio = condominio;
        this.tipo = tipo;
        this.edificacao = edificacao;
        this.servico = servico;
        this.areaTotal = areaTotal;
        this.areaConstruida = areaConstruida;
        this.banheiros = banheiros;
        this.quartos = quartos;
        this.suites = suites;
        this.comodos = comodos;
        this.vagas = vagas;
        this.observacao = observacao;
        this.proprietario = proprietario;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complementoEndereco = complementoEndereco;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.cliente = cliente;
    }


    @Override
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public SimNao getCondominio() {
        return SimNao.toEnum(condominio);
    }

    public void setCondominio(SimNao condominio) {
        this.condominio = condominio.getCodigo();
    }

    public TipoImovel getTipo() {
        return TipoImovel.toEnum(tipo);
    }

    public void setTipo(TipoImovel tipo) {
        this.tipo = tipo.getCodigo();
    }

    public TipoEdificacao getEdificacao() {
        return TipoEdificacao.toEnum(edificacao);
    }

    public void setEdificacao(TipoEdificacao edificacao) {
        this.edificacao = edificacao.getCodigo();
    }

    public TipoServico getServico() {
        return TipoServico.toEnum(servico);
    }

    public void setServico(TipoServico servico) {
        this.servico = servico.getCodigo();
    }

    @Transient
    public String getNomProprietario() {
        return nomProprietario;
    }

    public void setNomProprietario(String nomProprietario) {
        this.nomProprietario = nomProprietario;
    }

    @ManyToOne
    @JoinColumn(name = "proprietario_id")
    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }



    public String getComplementoImovel() {
        return complementoImovel;
    }

    public void setComplementoImovel(String complementoImovel) {
        this.complementoImovel = complementoImovel;
    }

    public String getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(String areaTotal) {
        this.areaTotal = areaTotal;
    }

    public String getAreaConstruida() {
        return areaConstruida;
    }

    public void setAreaConstruida(String areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    public String getBanheiros() {
        return banheiros;
    }

    public void setBanheiros(String banheiros) {
        this.banheiros = banheiros;
    }

    public String getQuartos() {
        return quartos;
    }

    public void setQuartos(String quartos) {
        this.quartos = quartos;
    }

    public String getSuites() {
        return suites;
    }

    public void setSuites(String suites) {
        this.suites = suites;
    }

    public String getComodos() {
        return comodos;
    }

    public void setComodos(String comodos) {
        this.comodos = comodos;
    }

    public String getVagas() {
        return vagas;
    }

    public void setVagas(String vagas) {
        this.vagas = vagas;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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

    public String getComplementoEndereco() {
        return complementoEndereco;
    }

    public void setComplementoEndereco(String complementoEndereco) {
        this.complementoEndereco = complementoEndereco;
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

    @OneToMany(mappedBy="imovel", cascade = CascadeType.ALL)
    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }


}
