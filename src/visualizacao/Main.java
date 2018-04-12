package visualizacao;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        File file = new File("src/base/SeguroDefeso_01_2010.csv");

        Scanner scan = new Scanner(file);

        List<Pescador> pescadores = new ArrayList<>();
        List<Municipio> municipios = new ArrayList<>();
        List<Defeso> defesos = new ArrayList<>();
        
        Operations op = new Operations();
        
        boolean skipFirstLine = true;
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
                int cpf = Integer.parseInt(lineSplitter[3].replace("*", ""));
                int codigoMunicipio = Integer.parseInt(lineSplitter[6].trim());
                String portariaDefeso = lineSplitter[9].trim();
                
                if (!Operations.containsMunicipio(municipios, codigoMunicipio)) {
                    Municipio municipio = new Municipio(codigoMunicipio, lineSplitter[7].trim(), lineSplitter[8]);
                    municipios.add(municipio);
                    op.insertMunicipio(municipio);
                }

                if (!Operations.containsDefeso(defesos, portariaDefeso)){
                    Defeso defeso = new Defeso(portariaDefeso, lineSplitter[10], lineSplitter[11]);
                    defesos.add(defeso);
                    op.insertDefeso(defeso);
                }
                
                if (!Operations.containsPescador(pescadores, cpf)) {
                    Pescador pescador = new Pescador(Long.parseLong(lineSplitter[0]), lineSplitter[1],
                            Long.parseLong(lineSplitter[2]), cpf,
                            Long.parseLong(lineSplitter[4]), lineSplitter[5].trim());
                    pescadores.add(pescador);
                    op.insertPescador(pescador, codigoMunicipio, portariaDefeso);
                }

                Parcela parcela = new Parcela(lineSplitter[12], Integer.parseInt(lineSplitter[13]),
                        lineSplitter[14], Operations.getLongFromString(lineSplitter[15]),
                        lineSplitter[16], Operations.getLongFromString(lineSplitter[17]),
                        Integer.parseInt(lineSplitter[18]), lineSplitter[19]);
                op.insertParcela(parcela, cpf);
                
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