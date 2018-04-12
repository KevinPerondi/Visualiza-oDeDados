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
                if (!Operations.containsPescador(pescadores, cpf)) {
                    Pescador pescador = new Pescador(Long.parseLong(lineSplitter[0]), lineSplitter[1],
                            Long.parseLong(lineSplitter[2]), cpf,
                            Long.parseLong(lineSplitter[4]), lineSplitter[5].trim());
                    pescadores.add(pescador);
                }

                int codigoMunicipio = Integer.parseInt(lineSplitter[6].trim());
                if (!Operations.containsMunicipio(municipios, codigoMunicipio)){
                    Municipio municipio = new Municipio(codigoMunicipio, lineSplitter[7].trim(), lineSplitter[8]);
                    municipios.add(municipio);
                }
                
                
                /*Parcela parcela = new Parcela(lineSplitter[12], Integer.parseInt(lineSplitter[13]),
                        lineSplitter[14], Operations.getLongFromString(lineSplitter[15]),
                        lineSplitter[16], Operations.getLongFromString(lineSplitter[17]),
                        Integer.parseInt(lineSplitter[18]), lineSplitter[19]);*/

            }
        }
        for (Pescador p : pescadores) {
            p.imprimePescador();
        }
        System.out.println("------------------------------------------------------");
        for (Municipio m : municipios){
            m.imprimeMunicipio();
        }
    }
}

class Operations {

    public static boolean containsPescador(List<Pescador> list, int cpfPescador) {
        if (list.isEmpty()) {
            return false;
        } else {
            for (Pescador p : list) {
                if (p.getCpf() == cpfPescador) {
                    return true;
                }
            }
            return false;
        }
    }

    public static Long getLongFromString(String num) throws ParseException {
        return Long.parseLong(num.trim().substring(0, (num.indexOf(",") - 1)));
    }

    public static boolean containsMunicipio(List<Municipio> list, int codigoMunicipio) {
        if (list.isEmpty()) {
            return false;
        } else {
            for (Municipio m : list) {
                if (m.getCodigo() == codigoMunicipio) {
                    return true;
                }
            }
            return false;
        }
    }

}
