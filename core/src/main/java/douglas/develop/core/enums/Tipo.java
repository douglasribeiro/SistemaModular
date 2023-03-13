package douglas.develop.core.enums;

public enum Tipo {

    FISICO(0, "Fisico"),
    JURIDICO(1, "Juridico");

    private Integer codigo;
    private String descricao;

    private Tipo(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Tipo toEnum(Integer cod) {
        if(cod == null)
            return null;
        for (Tipo x: Tipo.values()) {
            if(cod.equals(x.getCodigo()))
                return x;
        }
        throw new IllegalArgumentException("Tipo invalido.");
    }


}

