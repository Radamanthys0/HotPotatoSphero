package com.example.radamanthys.hotpoatosphero;

import com.example.radamanthys.hotpoatosphero.Server.Cliente;
import com.example.radamanthys.hotpoatosphero.Server.Host;
import com.example.radamanthys.hotpoatosphero.Server.MessageTypes;
import com.example.radamanthys.hotpoatosphero.Server.WebSocketCli;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.thoughtworks.xstream.XStream;
import java.net.URI;
import java.net.URISyntaxException;
import org.java_websocket.drafts.Draft_17;

public class TelaCadastro extends AppCompatActivity
{


    /*
       0 <item>Vermelho</item>
       1 <item>Azul</item>
       2 <item>Branco</item>
       3 <item>Rosa</item>
       4 <item>Amarelo</item>
       5 <item>Roxo</item>>
    */

    /*Variaveis*/
    public static Cliente cli;
    int oqueSou;// diz se sou host -> 1 ou cliente -> 0

    public static WebSocketCli wsCli;



    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        // Atualiza a variavel oquesou segundo a tela anterior (TelaClientServer)
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        oqueSou = bundle.getInt("oqueSou");

        //Toast.makeText(TelaCadastro.this, ""+oqueSou, Toast.LENGTH_SHORT).show();

        /*Popula a spinner_cor com o vetor de cores preste no Strings.xml*/
        final Spinner spinner_cor = (Spinner) findViewById(R.id.spinnerCor);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Cores, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_cor.setAdapter(adapter);

        /*
        * Quando o btnOK é acionado, é criado um cliente novo
        * */
        Button btnOK = (Button) findViewById(R.id.btnOk);
        btnOK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try{
                    TextView txtName = (TextView) findViewById(R.id.txtName);
                    String name = txtName.getText().toString();
                    int pos = spinner_cor.getSelectedItemPosition();

                    if (name.equals("")) {
                        Toast.makeText(TelaCadastro.this, "Por favor coloque um nome", Toast.LENGTH_SHORT).show();
                    }else{
                    if (oqueSou == 1){ // se for o host
                        cli = new Host(name); // crio host
                        cli.setVez(0); // ainda nao sei se é a minha vez de jogar, logo inicia em 0
                        cli.setProx(99); // ainda nao mandei pra ninguém, logo a prox cor é um numero invalido
                        cli.setCor(pos); // cor será dita pela posição do spinner_cor
                        cli.setMessage_type(MessageTypes.OPEN_CONNECTION); // se trata da 1 conversa entre este cliente e o servidor, portanto uma mensagem de conexão
                    }else { // se for cliente
                        cli = new Cliente(name); // crio cliente
                        cli.setVez(0); // ainda nao sei se é a minha vez de jogar, logo inicia em 0
                        cli.setProx(99);// ainda nao mandei pra ninguém, logo a prox cor é um numero invalido
                        cli.setCor(pos);// cor será dita pela posição do spinner_cor
                        cli.setMessage_type(MessageTypes.OPEN_CONNECTION); // se trata da 1 conversa entre este cliente e o servidor, portanto uma mensagem de conexão
                    }

                    conectar();
                    chamaProxTela(view);}

                }catch (URISyntaxException paramAnonymousView){

                    paramAnonymousView.printStackTrace();

                }
            }
        });


// teste
        Button btnbla = (Button) findViewById(R.id.bla);
        btnbla.setOnClickListener(new View.OnClickListener() {
            // conferir se todos os campos foram preenchidos!
            public void onClick(View view) {
                try{
                    //conectar();
                    //chamaProxTela(view);
                    passaVez();

                }catch (URISyntaxException paramAnonymousView){

                    paramAnonymousView.printStackTrace();

                }
            }
        });


    }

    /*
    * método chmaProxTela chama a proxima tela que será a de controle (TelaControle)*/
    public void chamaProxTela(View view)
    {
        //startActivityForResult(new Intent(paramView.getContext(), TelaControle.class), 0);
        Intent myIntent = new Intent(view.getContext(),TelaControle.class);
        startActivityForResult(myIntent, 0);
    }


    /*O método conectar lida com todas as peculiaridades da primeira conexão*/
    public void conectar() throws URISyntaxException
    {
        URI localURI = new URI("ws://192.168.1.2:8080/HotPotatoServer/server");// esta linha diz respeito ao endereço do servidor
        if (wsCli != null) {
            wsCli.close();
        }
        wsCli = new WebSocketCli(localURI, new Draft_17(), this);
        wsCli.connect();
    }

// teste
    public void passaVez() throws URISyntaxException
    {
        XStream xs;
        String toSend;
        cli.setProx(99);

        xs = new XStream();
        toSend = xs.toXML(cli);

        wsCli.send(toSend);

    }
}
