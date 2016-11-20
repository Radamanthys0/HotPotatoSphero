package com.example.radamanthys.hotpoatosphero.Server;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import com.example.radamanthys.hotpoatosphero.R;
import com.example.radamanthys.hotpoatosphero.TelaCadastro;
import com.thoughtworks.xstream.XStream;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 *
 * A classe WebSocketCli lida com todas as peculiaridades do uso de websocket para fazer a comunicação
 * com o servidor
 *
 * Links uteis:
 * http://x-stream.github.io/javadoc/
 */

public class WebSocketCli extends WebSocketClient
{

	private Activity act;


	/* ========== Construtores padrao ========== */
	public WebSocketCli(URI serverURI)
	{
		super(serverURI);
	}
	
	public WebSocketCli(URI serverURI, Draft draft)
	{
		super(serverURI, draft);
	}
	
	public WebSocketCli(URI serverURI, Draft draft, Activity act)
	{
		super(serverURI, draft);
		this.act = act;
	}
	/* ========================================= */

	
	
	@Override
	public void onClose(int code, String reason, boolean remote)
	{
		//Log.i("WebSocket", "closed with exit code " + code + " additional info: " + reason);
		print("closed with exit code " + code + " additional info: " + reason);
	}

	@Override
	public void onError(Exception ex)
	{
		//Log.i("Websocket", "Error " + ex.getMessage());
		print("Error " + ex.getMessage());
	}

	/*O método onMensage, tratará de todas as mensagens que o servidor enviar para o host*/
	@Override
	public void onMessage(String message)
	{
		print("Message: " + message);

		if (message.charAt(0)!= 'E'){

			XStream xs = new XStream();
			Cliente cliente = (Cliente) xs.fromXML(message);
			mov(cliente);
		}

		//Log.i("Websocket", "Message: " + message);
	}
	/*no metodo mov o host chamará o método correspondente na classe TelaControle para movimentar o sphero de acordo com as cores*/
	public void mov(Cliente cliente){
		if (cliente.getCor() == Cor.VERMELHO){ // caso a cor de quem solicitou o movimento seja vermelho
			if(cliente.getProx() == Cor.AZUL){
				// manda o sphero mover para o vermelho -> azul
			}
			if(cliente.getProx() == Cor.AMARELO){
				// manda o sphero mover para o vermelho -> amarelo
			}
			if(cliente.getProx() == Cor.BRANCO){
				// manda o sphero mover para o vermelho -> branco
			}
			if(cliente.getProx() == Cor.ROSA){
				// manda o sphero mover para o vermelho -> rosa
			}
			if(cliente.getProx() == Cor.ROXO){
				// manda o sphero mover para o vermelho -> roxo
			}
		}else if (cliente.getCor() == Cor.AZUL){ // caso a cor de quem solicitou o movimento seja azul
			if(cliente.getProx() == Cor.VERMELHO){
				// manda o sphero mover para o azul -> vermelho
			}
			if(cliente.getProx() == Cor.AMARELO){
				// manda o sphero mover para o azul -> amarelo
			}
			if(cliente.getProx() == Cor.BRANCO){
				// manda o sphero mover para o azul -> branco
			}
			if(cliente.getProx() == Cor.ROSA){
				// manda o sphero mover para o azul -> rosa
			}
			if(cliente.getProx() == Cor.ROXO){
				// manda o sphero mover para o azul -> roxo
			}
		}else if (cliente.getCor() == Cor.AMARELO){ // caso a cor de quem solicitou o movimento seja amarelo
			if(cliente.getProx() == Cor.VERMELHO){
				// manda o sphero mover para o amarelo -> vermelho
			}
			if(cliente.getProx() == Cor.AZUL){
				// manda o sphero mover para o amarelo -> azul
			}
			if(cliente.getProx() == Cor.BRANCO){
				// manda o sphero mover para o amarelo -> branco
			}
			if(cliente.getProx() == Cor.ROSA){
				// manda o sphero mover para o amarelo -> rosa
			}
			if(cliente.getProx() == Cor.ROXO){
				// manda o sphero mover para o amarelo -> roxo
			}
		}else if (cliente.getCor() == Cor.BRANCO){ // caso a cor de quem solicitou o movimento seja branco
			if(cliente.getProx() == Cor.VERMELHO){
				// manda o sphero mover para o branco -> vermelho
			}
			if(cliente.getProx() == Cor.AZUL){
				// manda o sphero mover para o branco -> azul
			}
			if(cliente.getProx() == Cor.AMARELO){
				// manda o sphero mover para o branco -> amarelo
			}
			if(cliente.getProx() == Cor.ROSA){
				// manda o sphero mover para o branco -> rosa
			}
			if(cliente.getProx() == Cor.ROXO){
				// manda o sphero mover para o branco -> roxo
			}
		}else if (cliente.getCor() == Cor.ROSA){ // caso a cor de quem solicitou o movimento seja rosa
				if(cliente.getProx() == Cor.VERMELHO){
					// manda o sphero mover para o rosa -> vermelho
				}
				if(cliente.getProx() == Cor.AZUL){
					// manda o sphero mover para o rosa -> azul
				}
				if(cliente.getProx() == Cor.AMARELO){
					// manda o sphero mover para o rosa -> amarelo
				}
				if(cliente.getProx() == Cor.BRANCO){
					// manda o sphero mover para o rosa -> branco
				}
				if(cliente.getProx() == Cor.ROXO){
					// manda o sphero mover para o rosa -> roxo
				}
		}else if (cliente.getCor() == Cor.ROXO){ // caso a cor de quem solicitou o movimento seja roxo
			if(cliente.getProx() == Cor.VERMELHO){
				// manda o sphero mover para o roxo -> vermelho
			}
			if(cliente.getProx() == Cor.AZUL){
				// manda o sphero mover para o roxo -> azul
			}
			if(cliente.getProx() == Cor.AMARELO){
				// manda o sphero mover para o roxo -> amarelo
			}
			if(cliente.getProx() == Cor.BRANCO){
				// manda o sphero mover para o roxo -> branco
			}
			if(cliente.getProx() == Cor.ROSA){
				// manda o sphero mover para o roxo -> rosa
			}
		}

	}

	@Override
	public void onOpen(ServerHandshake arg0)
	{
		XStream xs;
		Cliente cli;
		String toSend;

		print("Opened");

		xs = new XStream();
		toSend = xs.toXML(TelaCadastro.cli);

		this.send(toSend);
	}

	public void print(final String s){

		act.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(act, s , Toast.LENGTH_SHORT).show();
			}
		});
	}
	
}
