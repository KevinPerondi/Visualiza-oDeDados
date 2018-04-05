package visualizacao;

public class Pescador {
    private final Long numeroRequerimento;
    private final String dataRequerimento;
    private final Long pis;
    private final String cpf;
    private final Long registroGeralDaPesca;
    private final String nome;

    public Pescador(Long numeroRequerimento, String dataRequerimento, Long pis, String cpf, Long registroGeralDaPesca, String nome) {
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

    public String getCpf() {
        return cpf;
    }

    public Long getRegistroGeralDaPesca() {
        return registroGeralDaPesca;
    }

    public String getNome() {
        return nome;
    }

}

