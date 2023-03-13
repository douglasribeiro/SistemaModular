package douglas.develop.core.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
public class SystemLog extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;

    @Column(length = 30)
    private String nome;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date data;

    @Column(length = 30)
    private String proc;

    public SystemLog() {}

    public SystemLog(Long id, String nome, Date data, String proc) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.proc = proc;
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getProc() {
        return proc;
    }

    public void setProc(String proc) {
        this.proc = proc;
    }

}
