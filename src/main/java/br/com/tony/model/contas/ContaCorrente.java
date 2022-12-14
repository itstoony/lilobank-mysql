package br.com.tony.model.contas;

import br.com.tony.configuration.dao.ContaDAO;
import br.com.tony.excecoes.SaldoInsuficienteException;
import br.com.tony.model.cliente.Cliente;
import br.com.tony.model.extra.Tributavel;

public class ContaCorrente extends Conta implements Tributavel {

    public ContaCorrente(int agencia, int conta, Cliente titular) {
            super(agencia, conta, titular);
            if (!ContaDAO.dontSaveTwice(this)) {
                System.out.println("Don't save : " + ContaDAO.dontSaveTwice(this));
                ContaDAO.save(this);
            }
//            getContas().sort(Comparator.comparingInt(Conta::getNumeroDeConta));
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


    public String getTipoDaConta() {
        String tipo = "Conta Corrente";
        return tipo;
    }
}
