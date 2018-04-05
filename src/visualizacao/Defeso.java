package visualizacao;

public class Defeso {
    private final String portaria;
    private final String dataInicio;
    private final String dataFim;

    public Defeso(String portaria, String dataInicio, String dataFim) {
        this.portaria = portaria;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String getPortaria() {
        return portaria;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }
    
    
}
