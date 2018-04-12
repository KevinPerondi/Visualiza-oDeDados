package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    private String user;
    private String passwd;
    private final String url = "jdbc:postgresql://localhost:5432/baseVisualizacao";
    private Connection con;

    public Conexao() {
        try {
            this.user = new String();
            this.passwd = new String();
            this.getParams();
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro no construtor da conexão");
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getParams() {
        System.out.println("Synthax <user-password> for postgresql");
        Scanner scan = new Scanner(System.in);
        String readLine = scan.nextLine();
        String[] spliter = readLine.split("-");
        this.setUser(spliter[0]);
        this.setPasswd(spliter[1]);
    }

    public Connection getConnection() {
        try {
            con = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

}
