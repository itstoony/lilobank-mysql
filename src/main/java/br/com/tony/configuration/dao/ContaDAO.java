package br.com.tony.configuration.dao;

import br.com.tony.configuration.factory.ConnectionFactory;
import br.com.tony.model.contas.Conta;
import br.com.tony.model.contas.ContaCorrente;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {
    /**
     * CRUD Conta
     *
     * @author tony
     */


    public static void save(Conta conta) {
        /**
         * save "conta" in database
         * @param Conta
         */

        String sql = "INSERT INTO contas (saldo, agencia, numero_de_conta, clientes_id) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareCall(sql);
            pstm.setDouble(1, conta.getSaldo());
            pstm.setInt(2, conta.getAgencia());
            pstm.setInt(3, conta.getNumeroDeConta());
            pstm.setInt(4, conta.getTitular().getIdCliente());
            pstm.execute();
            System.out.println("Conta de: " + conta.getTitular().getNome() + ", salva com sucesso!");
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

//    public static void getContas() {
//
//        String sql = "SELECT * FROM contas";
//
//        Connection conn = null;
//        PreparedStatement pstm = null;
//        ResultSet rset = null;
//
//        try {
//            conn = ConnectionFactory.createConnectionToMySQL();
//            pstm = conn.prepareStatement(sql);
//            rset = pstm.executeQuery();
//            int contador = 1;
//            while (rset.next()) {
//                Cliente cliente = ClienteDAO.getClienteById(contador);
//                Conta conta = new ContaPoupanca(rset.getInt("agencia"), rset.getInt("numero_de_conta"), cliente);
////                Conta.addContas(conta);
//                contador++;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//                if (pstm != null) {
//                    pstm.close();
//                }
//                if (rset != null) {
//                    rset.close();
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//    }

    @NotNull
    public static List<Conta> getContaByClienteId(int id) {


        String sql = "select * from contas where clientes_id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        List<Conta> contas = new ArrayList<>();

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();
            rset.next();
            Conta conta = new ContaCorrente(rset.getInt("agencia"),
                    rset.getInt("numero_de_conta"),
                    ClienteDAO.getClienteById(rset.getInt("clientes_id")));
            contas.add(conta);

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

        return contas;

    }


    public static boolean dontSaveTwice(Conta conta) {
        /**
         * Prevents from saving account more than once on database using "account number" as reference
         * @param Conta
         * @return boolean
         */
        String sql = "select * from contas where numero_de_conta = ?";
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        boolean validador = false;
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


    public static void getContas() {
        /**
         * search for all client in database
         * @return arraylist with all clients
         */
        String sql = "SELECT * FROM contas";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Conta conta = new ContaCorrente(rset.getInt("agencia"),
                        rset.getInt("numero_de_conta"),
                        ClienteDAO.getClienteById(rset.getInt(("clientes_id"))));
                Conta.addContas(conta);
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
    }

}
