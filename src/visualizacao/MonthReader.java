package visualizacao;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MonthReader extends Thread {

    private final Operations operations;
    private final String filePath;
    private List<Pescador> pescadores;
    private List<Municipio> municipios;
    private List<Defeso> defesos;

    public MonthReader(Operations operations, String filePath, List<Pescador> pescadores, List<Municipio> municipios, List<Defeso> defesos) {
        this.operations = operations;
        this.filePath = filePath;
        this.pescadores = pescadores;
        this.municipios = municipios;
        this.defesos = defesos;
    }

    public List<Pescador> getPescadores() {
        return pescadores;
    }

    public void setPescadores(List<Pescador> pescadores) {
        this.pescadores = pescadores;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public List<Defeso> getDefesos() {
        return defesos;
    }

    public void setDefesos(List<Defeso> defesos) {
        this.defesos = defesos;
    }

    public Operations getOperations() {
        return operations;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public void run() {
        System.out.println("Thread(" + this.getId() + ") Working on {" + this.getFilePath() + "}");

        File file = new File(filePath);
        boolean skipFirstLine = true;

        Scanner scan;
        try {
            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                line = line.replace("\"", "");
                line = line.trim();
                if (skipFirstLine) {
                    skipFirstLine = false;
                } else {

                    /*0-NumeroRequerimento 1-DataRequerimento 2-PIS 3-CPFPescador
                  4-NumeroRGP 5-NomePescador 6-CodigoMunicipioPescador 7-NomeMunicipioPescador
                  8-Ufpescador 9-Portaria Defeso 10-DataInicioDefeso 11-DataFimDefeso
                  12-DataEmissaoParcela 13-NumeroParcela 14-DataSaqueParcela 15-ValorParcela
                  16-DataRestituicao 17-ValorRestituicao 18-CodigoSituacaoParcela 19-DescricaoSituacaoParcela
                     */
                    //System.out.println(line);
                    String[] lineSplitter = line.split(";");

                    String cpfString = lineSplitter[3].trim();
                    String codMunString = lineSplitter[6].trim();
                    String portariaDefeso = lineSplitter[9].trim();

                    if (cpfString.isEmpty() || codMunString.isEmpty() || portariaDefeso.isEmpty()) {

                    } else {
                        int cpf = Integer.parseInt(cpfString.replace("*", ""));
                        int codigoMunicipio = Integer.parseInt(codMunString);
                        if (!Operations.containsMunicipio(municipios, codigoMunicipio)) {
                            Municipio municipio = new Municipio(codigoMunicipio, lineSplitter[7].trim(), lineSplitter[8]);
                            municipios.add(municipio);
                            this.getOperations().insertMunicipio(municipio);
                        }

                        if (!Operations.containsDefeso(defesos, portariaDefeso)) {
                            Defeso defeso = new Defeso(portariaDefeso, lineSplitter[10], lineSplitter[11]);
                            defesos.add(defeso);
                            this.getOperations().insertDefeso(defeso);
                        }

                        if (!Operations.containsPescador(pescadores, cpf)) {
                            Pescador pescador = new Pescador(Long.parseLong(lineSplitter[0]), lineSplitter[1],
                                    Long.parseLong(lineSplitter[2]), cpf,
                                    Long.parseLong(lineSplitter[4]), lineSplitter[5].trim());
                            pescadores.add(pescador);
                            this.getOperations().insertPescador(pescador, codigoMunicipio, portariaDefeso);
                        }

                        String valorParcela = lineSplitter[15].trim();
                        String valorRestituicao = lineSplitter[17].trim();

                        if (valorParcela.isEmpty() || valorRestituicao.isEmpty()) {

                        } else {

                            Parcela parcela = new Parcela(lineSplitter[12], Integer.parseInt(lineSplitter[13]),
                                    lineSplitter[14], Operations.getLongFromString(valorParcela),
                                    lineSplitter[16], Operations.getLongFromString(valorRestituicao),
                                    Integer.parseInt(lineSplitter[18]), lineSplitter[19]);
                            this.getOperations().insertParcela(parcela, cpf);
                        }

                    }

                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(MonthReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MonthReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Thread(" + this.getId() + ") Working on {" + this.getFilePath() + "} finished!");
    }

}
