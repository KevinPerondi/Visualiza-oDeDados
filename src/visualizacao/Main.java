package visualizacao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/base/SeguroDefeso_01_2010.csv");

        Scanner scan = new Scanner(file);
        boolean skipFirstLine = true;
        while (scan.hasNext()) {
            String line = scan.nextLine();
            if (skipFirstLine) {
                skipFirstLine = false;
            } else {
                /*0-NumeroRequerimento 1-DataRequerimento 2-PIS 3-CPFPescador
                  4-NumeroRGP 5-NomePescador 6-CodigoMunicipioPescador 7-NomeMunicipioPescador
                  8-Ufpescador 9-Portaria Defeso 10-DataInicioDefeso 11-DataFimDefeso
                  12-DataEmissaoParcela 13-NumeroParcela 14-DataSaqueParcela 15-ValorParcela
                  16-DataRestituicao 17-ValorRestituicao 18-CodigoSituacaoParcela 19-DescricaoSituacaoParcela
                */
                String[] lineSplitter = line.split(";");
            }
        }
    }
}
