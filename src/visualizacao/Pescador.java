package visualizacao;

public class Pescador {
    private final Long numeroRequerimento;
    private final String dataRequerimento;
    private final Long pis;
    private final int cpf;
    private final Long registroGeralDaPesca;
    private final String nome;

    public Pescador(Long numeroRequerimento, String dataRequerimento, Long pis, int cpf, Long registroGeralDaPesca, String nome) {
        this.numeroRequerimento = numeroRequerimento;
        this.dataRequerimento = dataRequerimento;
        this.pis = pis;
        this.cpf = cpf;
        this.registroGeralDaPesca = registroGeralDaPesca;
        this.nome = nome;
    }

    public Long getNumeroRequerimento() {
        return numeroRequerimento;
    }

    public String getDataRequerimento() {
        return dataRequerimento;
    }

    public Long getPis() {
        return pis;
    }

    public int getCpf() {
        return cpf;
    }

    public Long getRegistroGeralDaPesca() {
        return registroGeralDaPesca;
    }

    public String getNome() {
        return nome;
    }

    public void imprimePescador(){
        System.out.println("Requerimento: "+this.getNumeroRequerimento()+
                            " Data Requerimento: "+this.getDataRequerimento()+
                            " PIS: "+this.getPis()+
                            " CPF: "+this.getCpf()+
                            " RGP: "+this.getRegistroGeralDaPesca()+
                            " Nome: "+this.getNome());
    }
    
}

