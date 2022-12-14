package br.com.tony.model.cliente;

import br.com.tony.configuration.dao.ClienteDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Modelo de classe para representar um Cliente
     *
     */
    private int idCliente;
    private String nome;
    private String cpf;
    private String profissao;
    private Date dataCadastro;


    private static List<Cliente> totalClientes = new ArrayList<>();


    public Cliente(String nome, String cpf, String profissao){
        this.nome = nome;
        this.cpf = cpf;
        this.profissao = profissao;
        setDataCadastro(new Date());
        if (!ClienteDAO.dontSaveTwice(this)) {
            System.out.println("Don't save : " + ClienteDAO.dontSaveTwice(this));
            ClienteDAO.save(this);
        }
        setIdCliente(ClienteDAO.getIdByReference(this));
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getProfissao() {
        return profissao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public static List<Cliente> getClientes() {
        ClienteDAO.getClientes();
        return totalClientes;
    }

    public static void addTotalClientes(Cliente c){
        totalClientes.add(c);
    }

    @Override
    public String toString() {
        return   "Id: " + this.idCliente + ", Nome: " + this.nome + ", Profissão: "
                + this.getProfissao()+ ", CPF: " + this.getCpf() + ", Data de Cadastro: " + this.getDataCadastro() +  "\n";
    }

}

