package douglas.develop.core.enums;


public enum TipoTelefone {

    PESSOAL(0, "Pessoal"),
    RECADO(1, "Recado"),
    RESIDENCIAL(2, "Residencial"),
    COMERCIAL(3, "Comercial");

    private Integer codigo;
    private String descricao;

    private TipoTelefone(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoTelefone toEnum(Integer cod) {
        if(cod == null)
            return null;
        for (TipoTelefone x: TipoTelefone.values()) {
            if(cod.equals(x.getCodigo()))
                return x;
        }
        throw new IllegalArgumentException("Tipo invalido.");
    }


}
