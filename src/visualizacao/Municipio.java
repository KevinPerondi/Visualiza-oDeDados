package visualizacao;

public class Municipio {

    private final int codigo;
    private final String nome;
    private final String estado;

    public Municipio(int codigo, String nome, String estado) {
        this.codigo = codigo;
        this.nome = nome;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }
    
    public void imprimeMunicipio(){
        System.out.println("Código: "+this.getCodigo()+
                            " Nome do Municipio: "+this.getNome()+
                            " UF: "+this.getEstado());
    }
    
}
