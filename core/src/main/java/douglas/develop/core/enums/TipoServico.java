package douglas.develop.core.enums;


public enum TipoServico {

    ALUGUEL(0, "Aluguel"),
    VENDA(1, "Venda"),
    OUTROS(2, "Outros");

    private Integer codigo;
    private String descricao;

    private TipoServico(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoServico toEnum(Integer cod) {
        if(cod == null)
            return null;
        for (TipoServico x: TipoServico.values()) {
            if(cod.equals(x.getCodigo()))
                return x;
        }
        throw new IllegalArgumentException("tipo de serviço invalido.");
    }

}
