package br.com.tony.model.contas;

import br.com.tony.configuration.dao.ContaDAO;
import br.com.tony.excecoes.SaldoInsuficienteException;
import br.com.tony.model.cliente.Cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta extends Object implements Serializable {

    /**
     * Modelo padrão de conta
     * @author tony
     */

    private static final long serialVersionUID = 1L;



    protected double saldo; // protected, disponível apenas para filhos
    private int agencia;
    private int numeroDeConta;
    private Cliente titular;
    private static final List<Conta> contas = new ArrayList<>();


    public Conta(int agencia, int conta, Cliente titular){
        /**
         * Construtor de conta a partir de parâmetros "agencia" e "conta"
         * @param agencia
         * @param conta
         * @param cliente object
         */
            this.agencia = agencia;
            this.numeroDeConta = conta;
            this.titular = titular;
            if (!ContaDAO.dontSaveTwice(this)) {
                System.out.println("Don't save : " + ContaDAO.dontSaveTwice(this));
                ContaDAO.save(this);
            }
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setNumeroDeConta(int numeroDeConta) {
        this.numeroDeConta = numeroDeConta;
    }

    public int getNumeroDeConta() {
        return numeroDeConta;
    }

    public abstract void deposita(double saldo);

    public void saca (double valor) throws SaldoInsuficienteException {
        /**
         * Valor precisa ser maior de saque
         * @param valor
         * @throws SaldoInsuficienteException
         */
        if(this.saldo < valor){
            throw new SaldoInsuficienteException("Saldo: " + this.saldo + ", Valor: " + valor);
        }
    }

    public void transfere(double valor,  Conta destino) throws SaldoInsuficienteException{
            if(this.saldo < valor){
                throw new SaldoInsuficienteException("Saldo: " + this.getSaldo() + ", Valor: " + valor);
            }
    }

    public double getSaldo(){
        // usado para ver o saldo das contas encapsuladas.
        return this.saldo;
    }

    public void setAgencia(int numeroDaAgencia){
        if(numeroDaAgencia > 0 || numeroDaAgencia < 9999){
            this.agencia += numeroDaAgencia;
        } else{
            System.out.println("Número de agência inválido!");
        }

    }
    public int getAgencia(){
        return this.agencia;
    }


    @Override
    public boolean equals(Object obj) {
        /**
         * Verifica existência de duas contas idênticas
         * @param reference
         */
        Conta ref = (Conta) obj;
        if (this.numeroDeConta != ref.numeroDeConta){
            return false;
        }
        if (this.agencia != ref.agencia){
            return false;
        }
        if (this.getClass() != ref.getClass()){
            return false;
        }
        return true;
    }

    public boolean igualdade(Conta conta){
            /**
             * permite chamar o método "equals" do ArrayList de contas
             * através de instâncias gerênicas
             * @param reference
             */
            if (contas.contains(conta)) {
                return false;
            }
            System.out.println("Essa conta já existe!");
            return true;
        }



    @Override
    public String toString() {
        /**
         * Returns tipo da conta, número e agência
         */
        return  "Número: " + this.numeroDeConta + ", Agência: " + this.agencia + ", Cliente: " + this.getTitular().getNome()+ "\n";
    }

    public static void addContas(Conta a){
        /**
         * Adiciona conta ao Array de contas através da referência
         * @param reference
         */
        contas.add(a);
    }

    public static List<Conta> getContas() {
        /**
         * Array que guarda todas as contas por referência
         * @return unmodifiableList
         */
        ContaDAO.getContas();
        return contas;
    }


    public static String totalContas(){
        /**
         * retorna o total de contas no ArrayList de contas
         */
        return "Total de contas: " + contas.size();
    }

    public String getTipoConta(){
        String tipo = "Conta padrão";
        return tipo;
    }

}


