package br.com.tony.configuration.dao;

import br.com.tony.configuration.factory.ConnectionFactory;
import br.com.tony.model.cliente.Cliente;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public static void save(Cliente cliente) {

        String sql = "INSERT INTO clientes (nome, cpf, profissao, dataCadastro) VALUES (?, ?, ?, ?)";

        Connection conn = null;

        PreparedStatement ptsm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            ptsm = conn.prepareStatement(sql);
            ptsm.setString(1, cliente.getNome());
            ptsm.setString(2, cliente.getCpf());
            ptsm.setString(3, cliente.getProfissao());
            ptsm.setDate(4, new Date(cliente.getDataCadastro().getTime()));
            ptsm.execute();
            System.out.println("Cliente salvo com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptsm != null) {
                    ptsm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void update(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, cpf = ?, profissao = ?, dataCadastro = ?" +
                "WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getCpf());
            pstm.setString(3, cliente.getProfissao());
            pstm.setDate(3, new Date(cliente.getDataCadastro().getTime()));
            pstm.setInt(4, cliente.getIdCliente());

            pstm.execute();
            System.out.println("Contato atualizado com sucesso!");
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

    public static void deleteById(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, id);
            pstm.execute();
            System.out.println("Cliente deletado com sucesso!");
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

    public static List<Cliente> getClientes() {
        String sql = "SELECT * FROM clientes";

        List<Cliente> clientes = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();
            while (rset.next()) {
                Cliente cliente = new Cliente(rset.getString("nome"), rset.getString("cpf"), rset.getString("profissao"));
                cliente.setIdCliente(rset.getInt("id"));
                cliente.setDataCadastro(rset.getDate("dataCadastro"));
                Cliente.addTotalClientes(cliente);
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
        return clientes;
    }

    public static List<Cliente> getClienteByName(String nome) {

        String sql = "select * from clientes where nome = ? ";

        List <Cliente> cliente = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, nome);
            rset = pstm.executeQuery();
            while (rset.next()) {
                Cliente c = new Cliente(rset.getString("nome"), rset.getString("cpf"), rset.getString("profissao"));
                c.setIdCliente(rset.getInt("id"));
                c.setDataCadastro(rset.getDate("dataCadastro"));
                cliente.add(c);
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

        return cliente;
    }
}



