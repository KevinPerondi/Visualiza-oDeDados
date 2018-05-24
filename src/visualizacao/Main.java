package visualizacao;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, ParseException {

        List<Pescador> pescadores = new ArrayList<>();
        List<Municipio> municipios = new ArrayList<>();
        List<Defeso> defesos = new ArrayList<>();

        Operations operations = new Operations();
        boolean skipFirstLine = true;

        for (int i = 1; i <= 12; i++) {
            //File file = new File("src/base/SeguroDefeso_" + i + "_2010.csv");
            String filePath = "/home/suporte/Downloads/base/SeguroDefeso_" + i + "_2010.csv";
            System.out.println("Working on {" + filePath + "}");
            
            File file = new File(filePath);

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
                                operations.insertMunicipio(municipio);
                            }

                            if (!Operations.containsDefeso(defesos, portariaDefeso)) {
                                Defeso defeso = new Defeso(portariaDefeso, lineSplitter[10], lineSplitter[11]);
                                defesos.add(defeso);
                                operations.insertDefeso(defeso);
                            }

                            if (!Operations.containsPescador(pescadores, cpf)) {
                                Pescador pescador = new Pescador(Long.parseLong(lineSplitter[0]), lineSplitter[1],
                                        Long.parseLong(lineSplitter[2]), cpf,
                                        Long.parseLong(lineSplitter[4]), lineSplitter[5].trim());
                                pescadores.add(pescador);
                                operations.insertPescador(pescador, codigoMunicipio, portariaDefeso);
                            }

                            String valorParcela = lineSplitter[15].trim();
                            String valorRestituicao = lineSplitter[17].trim();

                            if (valorParcela.isEmpty() || valorRestituicao.isEmpty()) {

                            } else {

                                Parcela parcela = new Parcela(lineSplitter[12], Integer.parseInt(lineSplitter[13]),
                                        lineSplitter[14], Operations.getLongFromString(valorParcela),
                                        lineSplitter[16], Operations.getLongFromString(valorRestituicao),
                                        Integer.parseInt(lineSplitter[18]), lineSplitter[19]);
                                operations.insertParcela(parcela, cpf);
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

        }

        /*for (Pescador p : pescadores) {
            p.imprimePescador();
        }
        System.out.println("------------------------------------------------------");
        for (Municipio m : municipios) {
            m.imprimeMunicipio();
        }
        
        System.out.println("------------------------------------------------------");
        for (Defeso d : defesos) {
            d.imprimeDefeso();
        }*/
    }
}
