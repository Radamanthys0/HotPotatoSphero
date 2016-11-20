package com.example.radamanthys.hotpoatosphero.Server;

/**
 * Created by Radamanthys on 12/11/2016.
 */
public class PassaVez {

    String atual;
    String prox;

    public PassaVez(String atual, String prox){
        this.atual = atual;
        this.prox = prox;
    }

    public void   setProx(String prox){ this.prox = prox; }

    public  String getAtual (){return this.atual;}
    public String getProx(){ return this.prox; }

}
