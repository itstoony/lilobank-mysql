package br.com.tony.testes;

import br.com.tony.model.cliente.Cliente;
import br.com.tony.model.contas.Conta;
import br.com.tony.model.contas.ContaCorrente;
import br.com.tony.model.contas.ContaPoupanca;

public class TesteClientes {
    public static void main(String[] args) {


        // instance clients
        Cliente tony = new Cliente("Tony", "222.222.222-22", "DEV");
        Cliente leo = new Cliente("Leonardo Guedes", "111.111.111-22", "Kenga");
        Cliente carlos = new Cliente("Carlos da Silva", "333.333.333-33", "Porteiro");

        // instance contas
        Conta tonyConta = new ContaCorrente(2587, 2635178, tony);
        tonyConta.deposita(400);
        tonyConta.setTitular(tony);

        Conta tonycp = new ContaPoupanca(1312, 8172354, tony);
        tonycp.deposita(2238193);
        tonycp.setTitular(tony);

        String tipo = tonycp.getTipoConta();
        System.out.println("Tipo: " + tipo);


        int id = tony.getIdCliente();
        System.out.println(" id tony: " + id);


        System.out.println(Cliente.getClientes());

        Conta ccLeo = new ContaCorrente(2132, 982312, leo);








    }
}
