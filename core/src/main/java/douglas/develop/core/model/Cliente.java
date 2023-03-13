package douglas.develop.core.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Cliente extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(length = 100)
    private String nome;

    @Column(unique=true, length = 20)
    private String cpfcnpj;

    @Column(unique=true)
    private String email;

    @JsonIgnore
    private String senha;

    public Cliente() {}

    public Cliente(String nome, String cpfcnpj, String email, String senha) {
        super();
        this.nome = nome;
        this.cpfcnpj = cpfcnpj;
        this.email = email;
        this.senha = senha;
    }

    @Override
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
