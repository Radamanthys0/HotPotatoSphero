package com.example.radamanthys.hotpoatosphero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class TelaServerClient extends AppCompatActivity {

    /*Variáveis*/
    int oqueSou;// oquesou se refere ao fato de ser host ou client 0-> client, 1 -> host

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_server_client);

        /*se o btnHost é acionado, então desencadeará eventos próprios do Host*/
        Button btnHost = (Button) findViewById(R.id.btnHost);
        btnHost.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                host(view);
            }
        });

        /*se o btnClient é acionado, então desencadeará eventos próprios do Client*/
        Button btnClient = (Button) findViewById(R.id.btnClient);
        btnClient.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                cliente(view);
            }
        });



    }

    /*O método cliente cria informa a TelaCadastro se o jogador é cliente*/
    public void cliente(View view){

        oqueSou =0;
        Intent myIntent = new Intent( view.getContext(),TelaCadastro.class);

        Bundle bundle = new Bundle();
        bundle.putInt("oqueSou", oqueSou);
        // bundle./putString("txt", txt);

         myIntent.putExtras(bundle);
        startActivityForResult(myIntent, 0);

    }
    /*O método Host cria informa a TelaCadastro se o jogador é host*/
    public  void  host(View view){
        oqueSou =1;
        Intent myIntent = new Intent( view.getContext(),TelaCadastro.class);

        Bundle bundle = new Bundle();
        bundle.putInt("oqueSou", oqueSou);
       // bundle./putString("txt", txt);

        myIntent.putExtras(bundle);
        startActivityForResult(myIntent, 0);
    }

}
