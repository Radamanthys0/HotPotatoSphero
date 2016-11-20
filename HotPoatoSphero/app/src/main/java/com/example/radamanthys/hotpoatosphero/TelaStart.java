package com.example.radamanthys.hotpoatosphero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TelaStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_start);


        ImageButton telaPrincipal = (ImageButton) findViewById(R.id.imgBtnTelaPrincipal);
        telaPrincipal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent( view.getContext(),TelaServerClient.class);
                startActivityForResult(myIntent, 0);
            }
        });

    }
}
