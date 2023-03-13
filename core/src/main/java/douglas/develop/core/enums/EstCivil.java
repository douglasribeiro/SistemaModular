package douglas.develop.core.enums;

public enum EstCivil {

    SOLTEIRO(0, "Solteiro"),
    CASADO(1, "Casado"),
    AMAZIADO(2,"Amaziado"),
    VIUVO(3,"Viuvo");

    private Integer codigo;
    private String descricao;

    private EstCivil(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EstCivil toEnum(Integer cod) {
        if(cod == null)
            return null;
        for (EstCivil x: EstCivil.values()) {
            if(cod.equals(x.getCodigo()))
                return x;
        }
        throw new IllegalArgumentException("EstCivil invalido.");
    }



}
