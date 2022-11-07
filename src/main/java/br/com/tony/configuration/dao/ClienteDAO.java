package br.com.tony.configuration.dao;

import br.com.tony.configuration.factory.ConnectionFactory;
import br.com.tony.model.cliente.Cliente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    /**
     * CRUD clients
     *
     * @author tony
     */
    public static void save(Cliente cliente) {

        /**
         * method to save clients in database
         * @param reference to client
         */
        String sql = "INSERT INTO clientes (nome, cpf, profissao, dataCadastro) VALUES (?, ?, ?, ?)";

        Connection conn = null;

        PreparedStatement ptsm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            ptsm = conn.prepareStatement(sql);
            ptsm.setString(1, cliente.getNome());
            ptsm.setString(2, cliente.getCpf());
            ptsm.setString(3, cliente.getProfissao());
            ptsm.setDate(4, new Date(cliente.getDataCadastro().getTime()));
            ptsm.execute();
//            cliente.setIdCliente(rset.getInt("id"));
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

        /**
         * Update information from clients on database
         * @param reference to client
         */
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
        /**
         * Delete client from database using id as reference
         */
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
        /**
         * search for all client in database
         * @return arraylist with all clients
         */
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
                Cliente cliente = new Cliente(rset.getString("nome"),
                        rset.getString("cpf"),
                        rset.getString("profissao"));
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
        /**
         * search for client in database using the client's name as reference
         * @param name
         * @return arraylist with clients by id
         */


        String sql = "select * from clientes where nome = ? ";

        List<Cliente> cliente = new ArrayList<>();
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

    public static List<Cliente> getClienteById(int id) {

        /**
         * search for client in database using the id as reference
         * @param id
         * @return arraylist with clients by id
         */
        String sql = "SELECT * FROM clientes WHERE id = ?";
        List<Cliente> clients = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();
            while (rset.next()) {
                Cliente result = new Cliente(rset.getString("nome"), rset.getString("cpf"), rset.getString("profissao"));
                result.setIdCliente(rset.getInt("id"));
                result.setDataCadastro(rset.getDate("dataCadastro"));
                clients.add(result);
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
        return clients;
    }

    public static int getIdByReference(Cliente cliente) {

        /**
         * used to get ID AUTO-INCREMENT from database in order to set client's id bases on database's id
         * @param Cliente
         * @return Client's id
         */

        String sql = "select id from clientes where cpf = ?";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        int id = 0;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cliente.getCpf());
            rset = pstm.executeQuery();
            rset.next();
            id = rset.getInt("id");

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
        return id;
    }

    public static boolean dontSaveTwice(Cliente cliente) {
        /**
         * Prevents from saving client more than once on database using "cpf" as reference
         * @param Cliente
         * @return boolean
         */
        String sql = "select * from clientes where cpf = ?";
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        Boolean validador = false;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cliente.getCpf());
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




