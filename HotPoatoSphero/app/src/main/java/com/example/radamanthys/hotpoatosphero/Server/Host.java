package com.example.radamanthys.hotpoatosphero.Server;

/**
 * A classe Host herda de cliente, ja que o host nada mais é do que um cliente especial
 *
 * */

public class Host extends Cliente
{
	public Host(String nome)
	{
		/*
		 * Ja que Host herda de Cliente, ele precisa chamar o
		 * construtor dele.
		 */
		super(nome);
	}
}