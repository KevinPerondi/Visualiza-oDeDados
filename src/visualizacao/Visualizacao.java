package visualizacao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Visualizacao {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/base/SeguroDefeso_01_2010.csv");

        Scanner scan = new Scanner(file);

        while (scan.hasNext()) {
            System.out.println(scan.nextLine());
        }
    }
}
