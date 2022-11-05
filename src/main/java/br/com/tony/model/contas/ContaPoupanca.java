package br.com.tony.model.contas;

import br.com.tony.model.extra.OrdenaLista;

public class ContaPoupanca extends Conta {


    public ContaPoupanca(int agencia, int conta){
        super(agencia, conta);
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
