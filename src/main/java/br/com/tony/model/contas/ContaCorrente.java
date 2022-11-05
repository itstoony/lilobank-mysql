package br.com.tony.model.contas;

import br.com.tony.excecoes.SaldoInsuficienteException;
import br.com.tony.model.extra.CalculadorDeImpostos;
import br.com.tony.model.extra.Tributavel;

import java.util.Comparator;

public class ContaCorrente extends Conta implements Tributavel {

    public ContaCorrente(int agencia, int conta) {
            super(agencia, conta);
            CalculadorDeImpostos impostos = new CalculadorDeImpostos();
            super.addContas(this);
            super.getContas().sort(Comparator.comparingInt(Conta::getNumeroDeConta));
    }

    @Override
    public double getValorImposto() {
        return 0.20;
    }


    @Override
    public void deposita(double saldo) {
        super.saldo += saldo;
    }

    @Override
    public void saca(double valor) throws SaldoInsuficienteException {
        double sacaCorrente = valor - getValorImposto();
        super.saca(sacaCorrente);
    }

    @Override
    public void transfere(double valor, Conta destino) throws SaldoInsuficienteException {
        double transfereCorrente = valor - getValorImposto();
        super.transfere(transfereCorrente, destino);
    }

    public double getTaxaCorrente() {
        return getValorImposto();
    }

    public void setSaldo(double valor){
        this.saldo += valor;
    }

    @Override
    public String toString() {
        return "Conta Corrente, " + super.toString();
    }
}
