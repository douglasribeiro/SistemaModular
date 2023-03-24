package douglas.develop.core.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    protected Long id;
    @NotNull
    private Integer client;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date includeDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date changeDate;

    @PreUpdate
    public void preUpdate(){
        Calendar brDate = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"),new Locale("pr", "BR"));
        this.setChangeDate(brDate.getTime());
    }

    @PrePersist
    public void prePersist(){
        if(this.getIncludeDate() == null){
            Calendar brDate = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"),new Locale("pr", "BR"));
            this.setIncludeDate(brDate.getTime());
        }
    }

    @Transient
    public abstract Long getId();

    public void setId(Long id){
        this.id = id;
    }

    @Column(name = "dt_inserido", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getIncludeDate() {
        return this.includeDate;
    }

    public void setIncludeDate(Date includeDate) {
        this.includeDate = includeDate;
    }

    @Column(name = "dt_atualizado")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getChangeDate() {
        return this.changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
//        return result;
//    }

}

