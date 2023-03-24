package douglas.develop.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import douglas.develop.core.enums.EstCivil;
import douglas.develop.core.enums.Tipo;
import douglas.develop.core.model.Proprietario;

import java.io.Serializable;
import java.util.Date;

public class ProprietarioUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dtNiver;
    private EstCivil estCivil;
    private String sexo;
    private String nacional;
    private String naturalidade;

    public ProprietarioUpdateDTO() {}

    public ProprietarioUpdateDTO(Proprietario proprietario) {
        this.nome = proprietario.getNome();
        this.dtNiver = proprietario.getDtNiver();
        this.estCivil = proprietario.getEstCivil();
        this.sexo = proprietario.getSexo();
        this.nacional = proprietario.getNacional();
        this.naturalidade = proprietario.getNaturalidade();
    }

    public ProprietarioUpdateDTO(Long id, String nome, Tipo pessoa, String cpfcnpj, String identinscr, String email, Date dtNiver,
                                 EstCivil estCivil, String sexo, Boolean ativo, String nacional, String naturalidade) {
        super();
        this.nome = nome;
        this.dtNiver = dtNiver;
        this.estCivil = estCivil;
        this.sexo = sexo;
        this.nacional = nacional;
        this.naturalidade = naturalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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


}
