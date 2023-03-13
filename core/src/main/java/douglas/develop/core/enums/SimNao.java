package douglas.develop.core.enums;


public enum SimNao {

    NAO(0, "Não"),
    SIM(1, "Sim");

    private Integer codigo;
    private String descricao;

    private SimNao(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static SimNao toEnum(Integer cod) {
        if(cod == null)
            return null;
        for (SimNao x: SimNao.values()) {
            if(cod.equals(x.getCodigo()))
                return x;
        }
        throw new IllegalArgumentException("Tipo invalido.");
    }


}
