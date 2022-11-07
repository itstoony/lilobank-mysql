package br.com.tony.model.contas;

import br.com.tony.model.cliente.Cliente;
import br.com.tony.model.extra.OrdenaLista;

public class ContaPoupanca extends Conta {


    public ContaPoupanca(int agencia, int conta, Cliente cliente){
        super(agencia, conta, cliente);
        super.addContas(this);
        super.getContas().sort(new OrdenaLista());
    }

    @Override
    public void deposita(double saldo) {
        super.saldo += saldo;
    }

    @Override
    public String toString() {
        return "Conta Poupança, " + super.toString();
    }




}
