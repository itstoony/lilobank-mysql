package br.com.tony.configuration.dao;

import br.com.tony.configuration.factory.ConnectionFactory;
import br.com.tony.model.contas.Conta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ContaDAO {
    /**
     * CRUD Conta
     * @author tony
     */



    public static void save(Conta conta){

        String sql = "INSERT INTO contas (saldo, agencia, numero_de_conta, clientes_id) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareCall(sql);
            pstm.setDouble(1, conta.getSaldo());
            pstm.setInt(2, conta.getAgencia());
            pstm.setInt(3, conta.getNumeroDeConta());
            pstm.setInt(4, conta.getTitular().getIdCliente());
            pstm.execute();
            System.out.println("Conta de: " + conta.getTitular().getNome() + ", salva com sucesso!");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (conn != null){
                    conn.close();
                }
                if (pstm != null){
                    pstm.close();
                }
                if (rset != null){
                    rset.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }



    }

    public static boolean dontSaveTwice(Conta conta) {
        /**
         * Prevents from saving client more than once on database using "account number" as reference
         * @param Conta
         * @return boolean
         */
        String sql = "select * from contas where numero_de_conta = ?";
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        Boolean validador = false;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, conta.getNumeroDeConta());
            rset = pstm.executeQuery();
            if (rset.next()) {
                validador = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (rset != null) {
                    rset.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return validador;
    }
}
