package visualizacao;

public class Parcela {

    private final String dataEmissaoParcela;
    private final int numeroParcela;
    private final String dataSaqueParcela;
    private final Long valorParcela;
    private final String dataRestituicao;
    private final Long valorRestituicao;
    private final int codigoSituacaoParcela;
    private final String descricaoSituacaoParcela;

    public Parcela(String dataEmissaoParcela, int numeroParcela, String dataSaqueParcela, Long valorParcela, String dataRestituicao, Long valorRestituicao, int codigoSituacaoParcela, String descricaoSituacaoParcela) {
        this.dataEmissaoParcela = dataEmissaoParcela;
        this.numeroParcela = numeroParcela;
        this.dataSaqueParcela = dataSaqueParcela;
        this.valorParcela = valorParcela;
        this.dataRestituicao = dataRestituicao;
        this.valorRestituicao = valorRestituicao;
        this.codigoSituacaoParcela = codigoSituacaoParcela;
        this.descricaoSituacaoParcela = descricaoSituacaoParcela;
    }

    public String getDataEmissaoParcela() {
        return dataEmissaoParcela;
    }

    public int getNumeroParcela() {
        return numeroParcela;
    }

    public String getDataSaqueParcela() {
        return dataSaqueParcela;
    }

    public Long getValorParcela() {
        return valorParcela;
    }

    public String getDataRestituicao() {
        return dataRestituicao;
    }

    public Long getValorRestituicao() {
        return valorRestituicao;
    }

    public int getCodigoSituacaoParcela() {
        return codigoSituacaoParcela;
    }

    public String getDescricaoSituacaoParcela() {
        return descricaoSituacaoParcela;
    }

}
