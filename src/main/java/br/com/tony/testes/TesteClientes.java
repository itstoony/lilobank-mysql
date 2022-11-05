package br.com.tony.testes;

import br.com.tony.configuration.dao.ClienteDAO;
import br.com.tony.model.cliente.Cliente;

public class TesteClientes {
    public static void main(String[] args) {

//        Cliente tony = new Cliente("Tony", "222.222.222-22", "DEV");
//        Cliente leo = new Cliente("Leonardo Guedes", "111.111.111-22", "Kenga");
//        Cliente carlos = new Cliente("Carlos da Silva", "333.333.333-33", "Porteiro");
//        ClienteDAO.save(carlos);
//        ClienteDAO.save(leo);
//        ClienteDAO.save(tony);
//
//        Cliente luiz = new Cliente("Luiz Otávio", "444.444.444-44", "Atendimento");
////        Cliente.addClienteDatabase(luiz);
//        ClienteDAO.deleteById(6);
//        System.out.println(Cliente.getClientes());

        System.out.println(ClienteDAO.getClienteByName("Leonardo Guedes"));

    }
}
