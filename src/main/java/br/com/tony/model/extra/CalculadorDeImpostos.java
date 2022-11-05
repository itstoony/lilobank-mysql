package br.com.tony.model.extra;

public class CalculadorDeImpostos {

   private double totalImposto;

   public void registra(Tributavel t){
       double valor = t.getValorImposto();
       totalImposto += valor;
   }

   public double getTotalImposto(){
       return this.totalImposto;
   }
}
