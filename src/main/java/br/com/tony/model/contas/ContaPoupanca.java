package br.com.tony.model.contas;

import br.com.tony.model.cliente.Cliente;

import java.util.Comparator;

public class ContaPoupanca extends Conta {


    public ContaPoupanca(int agencia, int conta, Cliente cliente){
        super(agencia, conta, cliente);
        addContas(this);
        getContas().sort(Comparator.comparingInt(Conta::getNumeroDeConta));
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
