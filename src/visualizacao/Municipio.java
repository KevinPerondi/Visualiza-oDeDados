package visualizacao;

public class Municipio {

    private final Long codigo;
    private final String nome;
    private final String estado;

    public Municipio(Long codigo, String nome, String estado) {
        this.codigo = codigo;
        this.nome = nome;
        this.estado = estado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }
}
