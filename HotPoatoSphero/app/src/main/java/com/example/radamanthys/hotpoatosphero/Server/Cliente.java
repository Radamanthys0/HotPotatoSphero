package com.example.radamanthys.hotpoatosphero.Server;

/**
 * A classe Cliente lida com todos os atributos que um cliente possui
 */

public class Cliente
{
	private int    vez; // sabe se é a vez do cliente jogar
	private String nome; // sabe o nome deste cliente
	private int prox; // sabe a cor para a qual este cliente mandará o sphero
	private int cor; // sabe a cor deste cliente
	private int message_type; // sabe qual tipo de conversa tem com o servidor

	public Cliente(String nome){ this.nome = nome; }
	
	public void setVez(int vez){ this.vez = vez; }
	public int  getVez(){ return this.vez; }
	
	public void   setNome(String nome){ this.nome = nome; }
	public String getNome(){ return this.nome; }

	public void   setProx(int prox){ this.prox = prox; }
	public int getProx(){ return this.prox; }

	public void setCor(int cor){ this.cor = cor; }
	public int  getCor(){ return this.cor; }

	public void setMessage_type(int message){ this.message_type= message; }
	public int getMessage_type(){ return this.message_type; }
}

/*
 * Tome nota, qualquer alteracao em Cliente/Host ou em qualquer objeto
 * que seja codificado via XStream e enviado, deve ser refletida no cliente
 * e no servidor para que nao haja inconsistencias entre os objetos ;)
 */
