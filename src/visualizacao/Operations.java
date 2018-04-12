package visualizacao;

import banco.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Operations {

    private Connection con;
    private Conexao conexao;

    public Operations() {
        this.conexao = new Conexao();
        this.con = this.conexao.getConnection();
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

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

    public static boolean containsDefeso(List<Defeso> list, String portaria) {
        if (list.isEmpty()) {
            return false;
        } else {
            for (Defeso p : list) {
                if (p.getPortaria().equals(portaria)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void insertPescador(Pescador p, int codMun, String portariaDefeso) {
        try {
            String sql = "INSERT INTO pescador (CPF_pescador, RGP, PIS,"
                    + "Numero_de_Requerimento, Data_de_Requerimento, Nome,"
                    + "Cod_Municipio, Portaria_Defeso) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement statement = this.getCon().prepareStatement(sql);
            statement.setInt(1, p.getCpf());
            statement.setLong(2, p.getRegistroGeralDaPesca());
            statement.setLong(3, p.getPis());
            statement.setLong(4, p.getNumeroRequerimento());
            statement.setString(5, p.getDataRequerimento());
            statement.setString(6, p.getNome());
            statement.setInt(7, codMun);
            statement.setString(8, portariaDefeso);
            statement.execute();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertMunicipio(Municipio m) {
        try {
            String sql = "INSERT INTO municipio (Nome_Mun, Cod_Municipio, Estado) VALUES (?,?,?)";
            PreparedStatement statement = this.getCon().prepareStatement(sql);
            statement.setString(1, m.getNome());
            statement.setInt(2, m.getCodigo());
            statement.setString(3, m.getEstado());
            statement.execute();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertDefeso(Defeso d) {
        try {
            String sql = "INSERT INTO defeso (Portaria, Data_Inicio, Data_Termino) VALUES (?,?,?)";
            PreparedStatement statement = this.getCon().prepareStatement(sql);
            statement.setString(1, d.getPortaria());
            statement.setString(2, d.getDataInicio());
            statement.setString(3, d.getDataFim());
            statement.execute();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertParcela(Parcela p, int cpfPescador) {
        try {
            String sql = "INSERT INTO parcela (Data_Emissao, Num_Parcela, Data_de_Saque,"
                    + " Valor_da_Parcela, Data_de_Restituicao,Valor_da_Restituicao,"
                    + " Cod_Situacao_Parcela, Descricao_Situacao_Parcela,CPF_pescador)"
                    + " VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = this.getCon().prepareStatement(sql);
            statement.setString(1, p.getDataEmissaoParcela());
            statement.setInt(2, p.getNumeroParcela());
            statement.setString(3, p.getDataSaqueParcela());
            statement.setLong(4, p.getValorParcela());
            statement.setString(5, p.getDataRestituicao());
            statement.setLong(6, p.getValorRestituicao());
            statement.setInt(7, p.getCodigoSituacaoParcela());
            statement.setString(8, p.getDescricaoSituacaoParcela());
            statement.setInt(9, cpfPescador);
            statement.execute();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
