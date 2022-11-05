package br.com.tony.model.extra;

import br.com.tony.model.contas.Conta;

import java.util.Comparator;

public class OrdenaLista implements Comparator<Conta> {

    /**
     * Function object - Usado para encapsular um único método
     * @param c1 primeiro objeto a ser comparado.
     * @param c2 segundo objeto a ser comparado.
     */
    /*
    * Classe é instanciada ao instânciar uma conta "Poupança ou Corrente"
    * ordenando a conta por número da Conta.
    * */
    @Override
    public int compare(Conta c1, Conta c2) {
        if(c1.getNumeroDeConta() > c2.getNumeroDeConta()){
            return 1;
        }
        if(c1.getNumeroDeConta() < c2.getNumeroDeConta()){
            return -1;
        }
        return 0;
    }



}
