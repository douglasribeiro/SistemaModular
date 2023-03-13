package douglas.develop.core.enums;

public enum TipoEndereco {

    RESIDENCIAL(0, "Residencial"),
    COMERCIAL(1, "Comercial"),
    COBRANÇA(2, "Cobrança"),
    ENTREGA(3, "Entrega"),
    RECADO(4, "Recado"),
    OUTROS(5, "Outros");

    private Integer codigo;
    private String descricao;

    private TipoEndereco(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoEndereco toEnum(Integer cod) {
        if(cod == null)
            return null;
        for (TipoEndereco x: TipoEndereco.values()) {
            if(cod.equals(x.getCodigo()))
                return x;
        }
        throw new IllegalArgumentException("TipoEndereco invalido.");
    }

}
