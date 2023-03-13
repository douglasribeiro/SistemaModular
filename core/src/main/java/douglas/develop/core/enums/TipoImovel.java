package douglas.develop.core.enums;

public enum TipoImovel {

    RESIDENCIAL(0, "Residencial"),
    COMERCIAL(1, "Comercial"),
    INDUSTRIAL(2, "Industrial"),
    RURAL(3, "Rural"),
    OUTROS(4, "Outros");

    private Integer codigo;
    private String descricao;

    private TipoImovel(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoImovel toEnum(Integer cod) {
        if(cod == null)
            return null;
        for (TipoImovel x: TipoImovel.values()) {
            if(cod.equals(x.getCodigo()))
                return x;
        }
        throw new IllegalArgumentException("tipo de imovel invalido.");
    }

}

