package douglas.develop.core.enums;


public enum TipoEdificacao {

    CASA(0, "Casa"),
    APARTAMENTO(1, "Apartamento"),
    GALPAO(2, "Galpão"),
    TERRENO(3, "Terreno"),
    OUTROS(4, "Outros");

    private Integer codigo;
    private String descricao;

    private TipoEdificacao(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoEdificacao toEnum(Integer cod) {
        if(cod == null)
            return null;
        for (TipoEdificacao x: TipoEdificacao.values()) {
            if(cod.equals(x.getCodigo()))
                return x;
        }
        throw new IllegalArgumentException("tipo de edificação invalido.");
    }

}
