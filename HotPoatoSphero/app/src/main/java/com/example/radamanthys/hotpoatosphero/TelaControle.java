package com.example.radamanthys.hotpoatosphero;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.radamanthys.hotpoatosphero.Server.Cor;
import com.example.radamanthys.hotpoatosphero.Server.MessageTypes;
import com.orbotix.ConvenienceRobot;
import com.orbotix.Sphero;
import com.orbotix.classic.DiscoveryAgentClassic; // usando o sphero
import com.orbotix.classic.RobotClassic;// usando o sphero
import com.orbotix.command.RollCommand;
import com.orbotix.common.DiscoveryException;
import com.orbotix.common.Robot;
import com.orbotix.common.RobotChangedStateListener;
import com.thoughtworks.xstream.XStream;

import java.net.URISyntaxException;


public class TelaControle extends AppCompatActivity   {


    /* Variáveis  */
    private ConvenienceRobot mRobot; //obteto mRobot

    Button desligarSphero; //btn para desligar o sphero
    ToggleButton tgbConect; //btn referente a conexão do sphero

    SeekBar posSphero = null;// configurar a posição do sphero

    /* Variáveis  Auxiliares*/
    int angulo;

    public static String BALL_STATUS_FILTER = "com.survivingwithandroid.ball_filter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_controle);

        /* Conexão com sphero */
        DiscoveryAgentClassic.getInstance().addRobotStateListener(new RobotChangedStateListener() {
            @Override
            public void handleRobotChangedState(Robot robot, RobotChangedStateNotificationType type) {
                switch (type){
                    case Online:
                        Toast.makeText(getApplicationContext(), "Online", Toast.LENGTH_SHORT).show();
                        if (robot instanceof RobotClassic) {
                            mRobot = new Sphero(robot);
                        }
                        mRobot.setLed( 0.0f, 0.0f, 1.0f );
                        break;
                    case Disconnected:
                        Toast.makeText(getApplicationContext(), "desconectado", Toast.LENGTH_SHORT).show();
                        break;
            }
        }});


        /* Desligar o sphero
        * ocorre quando o butão Desligar é pressionado */
        desligarSphero = (Button) findViewById(R.id.btnDes);
        desligarSphero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Bye Bye =)", Toast.LENGTH_LONG).show();
                desligar();
            }
        });

        /* Conectar o sphero
        * Togle buttom = true -> sphero conectado
        * Togle buttom = true -> sphero desconectado*/
        tgbConect = (ToggleButton) findViewById(R.id.tgl_conect);
        tgbConect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    onStart();
                } else {
                    onStop();
                }
            }
        });


        /*btn teste
        * win
        * lose*/
        Button btnWin = (Button) findViewById(R.id.btnWin);
        btnWin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent( view.getContext(), TelaResultado_positivo.class);
                startActivityForResult(myIntent, 0);
            }
        });
        Button btnLose = (Button) findViewById(R.id.btnLose);
        btnLose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent( view.getContext(), TelaResultado_negativo.class);
                startActivityForResult(myIntent, 0);
            }
        });

        /*Movientação do sphero segundo o APP*/

        /*Caso o sndVermelho (send to Vermelho) seja acionado significa que este cliente deseja mandar o sphero para o cliente cuja a cor é vermelha*/
        Button sndVermelho = (Button) findViewById(R.id.sndVermelho); // R.id.sndVermelho
        sndVermelho.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try{
                    passaVez(Cor.VERMELHO);

                    //para teste do sphero faremos ele se movimentar de cor por cor
                    if(TelaCadastro.cli.getCor() == Cor.AZUL){
                        azulToVermelho();
                    }else if(TelaCadastro.cli.getCor() == Cor.ROSA){
                        azulToRosa();
                    }else if(TelaCadastro.cli.getCor() == Cor.BRANCO){
                        azulToBranco();
                    }
                    else if(TelaCadastro.cli.getCor() == Cor.ROXO){
                        azulToRoxo();
                    }
                    else if(TelaCadastro.cli.getCor() == Cor.AMARELO){
                        azulToAmarelo();
                    }

                }catch (URISyntaxException paramAnonymousView){

                    paramAnonymousView.printStackTrace();

                }
            }
        });

        /*Caso o sndAzul (send to Azul) seja acionado significa que este cliente deseja mandar o sphero para o cliente cuja a cor é azul*/
        Button sndAzul = (Button) findViewById(R.id.sndBlue);
        sndAzul.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try{
                    passaVez(Cor.AZUL);

                    //para teste do sphero faremos ele se movimentar de cor por cor
                    if(TelaCadastro.cli.getCor() == Cor.VERMELHO){
                        vermelhoToAzul();
                    }else if(TelaCadastro.cli.getCor() == Cor.ROSA){
                        rosaToAzul();
                    }else if(TelaCadastro.cli.getCor() == Cor.BRANCO){
                        brancoToAzul();
                    }
                    else if(TelaCadastro.cli.getCor() == Cor.ROXO){
                        roxoToAzul();
                    }
                    else if(TelaCadastro.cli.getCor() == Cor.AMARELO){
                        amareloToAzul();
                    }

                }catch (URISyntaxException paramAnonymousView){

                    paramAnonymousView.printStackTrace();

                }
            }
        });
        /*Caso o sndBranco (send to Branco) seja acionado significa que este cliente deseja mandar o sphero para o cliente cuja a cor é Branca*/
        Button sndBranco = (Button) findViewById(R.id.sndBranco); // R.id.sndBranco
        sndVermelho.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try{
                    passaVez(Cor.BRANCO);

                    //para teste do sphero faremos ele se movimentar de cor por cor
                    if(TelaCadastro.cli.getCor() == Cor.VERMELHO){
                        vermelhoToBranco();
                    }else if(TelaCadastro.cli.getCor() == Cor.ROSA){
                        rosaToBranco();
                    }else if(TelaCadastro.cli.getCor() == Cor.AZUL){
                        azulToBranco();
                    }
                    else if(TelaCadastro.cli.getCor() == Cor.ROXO){
                        roxoToBranco();
                    }
                    else if(TelaCadastro.cli.getCor() == Cor.AMARELO){
                        amareloToBranco();
                    }
                }catch (URISyntaxException paramAnonymousView){

                    paramAnonymousView.printStackTrace();

                }
            }
        });

        /*Caso o sndRosa (send to Rosa) seja acionado significa que este cliente deseja mandar o sphero para o cliente cuja a cor é rosa*/
        Button sndRosa = (Button) findViewById(R.id.sndRosa); // R.id.sndRosa
        sndVermelho.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try{
                    passaVez(Cor.ROSA);

                    //para teste do sphero faremos ele se movimentar de cor por cor
                    if(TelaCadastro.cli.getCor() == Cor.VERMELHO){
                        vermelhoToRosa();
                    }else if(TelaCadastro.cli.getCor() == Cor.BRANCO){
                        brancoToRosa();
                    }else if(TelaCadastro.cli.getCor() == Cor.AZUL){
                        azulToRosa();
                    }
                    else if(TelaCadastro.cli.getCor() == Cor.ROXO){
                        roxoToRosa();
                    }
                    else if(TelaCadastro.cli.getCor() == Cor.AMARELO){
                        amareloToRosa();
                    }
                }catch (URISyntaxException paramAnonymousView){

                    paramAnonymousView.printStackTrace();

                }
            }
        });

        /*Caso o sndAmarelo (send to Amarelo) seja acionado significa que este cliente deseja mandar o sphero para o cliente cuja a cor é Amarela*/
        Button sndAmarelo = (Button) findViewById(R.id.sndAmarelo); // R.id.sndAmarelo
        sndVermelho.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try{
                    passaVez(Cor.AMARELO);

                    //para teste do sphero faremos ele se movimentar de cor por cor
                    if(TelaCadastro.cli.getCor() == Cor.VERMELHO){
                        vermelhoToAmarelo();
                    }else if(TelaCadastro.cli.getCor() == Cor.BRANCO){
                        brancoToAmarelo();
                    }else if(TelaCadastro.cli.getCor() == Cor.AZUL){
                        azulToAmarelo();
                    }
                    else if(TelaCadastro.cli.getCor() == Cor.ROXO){
                        roxoToAmarelo();
                    }
                    else if(TelaCadastro.cli.getCor() == Cor.ROSA){
                        rosaToAmarelo();
                    }

                }catch (URISyntaxException paramAnonymousView){

                    paramAnonymousView.printStackTrace();

                }
            }
        });

        /*Caso o sndRoxo (send to Roxo) seja acionado significa que este cliente deseja mandar o sphero para o cliente cuja a cor é Roxa*/
        Button sndRoxo = (Button) findViewById(R.id.sndRoxo); // R.id.sndRoxo
        sndVermelho.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try{
                    passaVez(Cor.ROXO);

                    //para teste do sphero faremos ele se movimentar de cor por cor
                    if(TelaCadastro.cli.getCor() == Cor.VERMELHO){
                        vermelhoToRoxo();
                    }else if(TelaCadastro.cli.getCor() == Cor.BRANCO){
                        brancoToRoxo();
                    }else if(TelaCadastro.cli.getCor() == Cor.AZUL){
                        azulToRoxo();
                    }
                    else if(TelaCadastro.cli.getCor() == Cor.AMARELO){
                        amareloToRoxo();
                    }
                    else if(TelaCadastro.cli.getCor() == Cor.ROSA){
                        rosaToRoxo();
                    }

                }catch (URISyntaxException paramAnonymousView){

                    paramAnonymousView.printStackTrace();

                }
            }
        });



        /* Seek bar que controla a posição do sphero*/
        posSphero = (SeekBar) findViewById(R.id.seekBar);
        posSphero.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                rotateSphero(posSphero.getProgress());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                angulo = posSphero.getProgress();
                //Toast.makeText(TelaControle.this, "max "+ posSphero.getMax(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), "Stopped tracking seekbar "+ angulo , Toast.LENGTH_SHORT).show();
            }
        });



    }


    /*
    * A seguir estão os metodos que lidam:
    * com a conexão com o sphero
    * com a movimentação do sphero*/

    //===================================== < Conexão com Sphero> ==================================

    /*inicia procura por dispositivo (sphero)*/
    @Override
    protected void onStart() {
        super.onStart();
        // This line assumes that this object is a Context
        try {
            DiscoveryAgentClassic.getInstance().startDiscovery(this);
        } catch (DiscoveryException e) {
        }
    }


    /* desconecta o app do sphero */
    @Override
    protected void onStop() {
        //Toast.makeText(getApplicationContext(), "on_Stop", Toast.LENGTH_LONG).show();
        if (mRobot != null) {
            //if (mRobot instanceof RobotClassic) {
            mRobot.disconnect();
            //}
        }
        super.onStop();
    }

    /* coloca o sphero para dormir*/
    protected void desligar() {
        //Toast.makeText(getApplicationContext(), "on_Stop", Toast.LENGTH_LONG).show();
        if (mRobot != null) {
            //if (mRobot instanceof RobotClassic) {
            mRobot.sleep();
            //}
        }
        super.onStop();
    }


    //=================================== < Movimentação do Sphero> ================================

    public void iniciamoviemnto(){
        if (mRobot != null ){
             mRobot.drive( 180, 0 );
             mRobot.setZeroHeading();
        }
    }

    public void gofoward(){
        if(mRobot !=null) {
            rotateSphero(90);
            moveSphero(true, 90);
        }
        super.onStop();
    }

    private void goback() { // vai para trás angulo 270
        if(mRobot !=null) {
            rotateSphero(270);
            moveSphero(true, 270);
        }
        super.onStop();
    }

    public void  goleft(){

        if(mRobot !=null) {
            rotateSphero(0);
            moveSphero(true,0);
        }
        super.onStop();

    }

    private void goright() {// vai para a direita angulo = 180
        if(mRobot !=null) {
            rotateSphero(180);
            moveSphero(true,180);
        }
        super.onStop();

    }

    /*
    * Metodo passaVez atualiza o objeto cli
    * onde:
    * cli.setProx(corProx) -> indica que este cliente está mandando o sphero para o cliente que possui a cor corProx
    * cli.setMessage_type(MessageTypes.MOV_SPHERO) -> indica ao servidor que é uma solicitação de movimentação*/
    public void passaVez(int corProx) throws URISyntaxException
    {
        XStream xs;
        String toSend;
        TelaCadastro.cli.setMessage_type(MessageTypes.MOV_SPHERO); // seta a mensagem para MOV_SPHERO indicando que quer movimentar o sphero
        TelaCadastro.cli.setProx(corProx);// movimentar para o cliente azul

        xs = new XStream();
        toSend = xs.toXML(TelaCadastro.cli);

        TelaCadastro.wsCli.send(toSend);

    }
    /*
    * rotateSphero é um método q converte a posição do cursor no seek bar em angulos e, passa para o sphero
    * permitindo assim que o sphero rode sobre o seu eixo em angulos diferentes*/
    public void rotateSphero(int i){
        int ang;

        ang = (i*360)/100; /* 100 - 360
                            i - ang
                            */
        mRobot.drive(ang, 0 );

    }


    /*
    * moveSphero é o método que fará com que o sphero se locomova por um tempo para a frente */
    private void moveSphero(final boolean lit, final int ang) {// private void blink( final boolean lit, int ang ) {
        if (lit){
            // movendo -> rosa
            //move foward
            mRobot.setLed( 1.0f, 0.0f, 0.3f );


            Toast.makeText(TelaControle.this, "Angulo real: "+ang, Toast.LENGTH_SHORT).show();

            mRobot.drive(ang, (float) 0.2);// linha essencial
            //mRobot.sendCommand( new RollCommand( angulo, (float) 0.1, RollCommand.State.GO ) );
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    moveSphero(!lit, ang);
                }
            },100);
        }else{
            //mRobot.drive(90,0);
            mRobot.setLed( 0.0f, 1.f, 0.2f );
        }
    }

   public void girarSphero(int ang){
       mRobot.drive(ang, 0 );
   }

    //================================== <drive sphero into a color> ===============================
    /*
    * Neste momento faremos todas as combinações entre cores */

    /*vermelho para demais cores*/
    public void vermelhoToAzul(){
        gofoward();
    }
    public void vermelhoToBranco(){
        goleft();
    }
    public void vermelhoToRosa(){
        gofoward();
        goleft();
    }
    public void vermelhoToAmarelo(){
        goleft();
        goleft();
    }
    public void vermelhoToRoxo(){
        gofoward();
        goleft();
        goleft();
    }

    /*Azul para demais cores*/
    public void azulToVermelho(){
        goback();
    }
    public void azulToBranco(){
        goleft();
        goback();
    }
    public void azulToRosa(){
        goleft();
    }
    public void azulToAmarelo(){
        goback();
        goleft();
        goleft();
    }
    public void azulToRoxo(){
        goleft();
        goleft();
    }

    /*Branco para demais cores*/
    public void brancoToVermelho(){
        goright();
    }
    public void brancoToAzul(){
        gofoward();
        goright();
    }
    public void brancoToRosa(){
        gofoward();
    }
    public void brancoToAmarelo(){
        goleft();
    }
    public void brancoToRoxo(){
        gofoward();
        goleft();
    }


    /*Rosa para demais cores*/
    public void rosaToVermelho(){
        goright();
        goback();
    }
    public void rosaToAzul(){
        goright();
    }
    public void rosaToBranco(){
        goback();
    }
    public void rosaToAmarelo(){
        goleft();
        goback();
    }
    public void rosaToRoxo(){
        goleft();
    }

    /*Roxo para demais cores*/
    public void roxoToVermelho(){
        goback();
        goright();
        goright();
    }
    public void roxoToAzul(){
        goright();
        goright();
    }
    public void roxoToBranco(){
        goback();
        goright();
    }
    public void roxoToAmarelo(){
        goback();
    }
    public void roxoToRosa(){
        goright();
    }

    /*Amarelo para demais cores*/
    public void amareloToVermelho(){
        goright();
        goright();
    }
    public void amareloToAzul(){
        gofoward();
        goright();
        goright();
    }
    public void amareloToBranco(){
        goright();
    }
    public void amareloToRoxo(){
        gofoward();
    }
    public void amareloToRosa(){
        gofoward();
        goright();
    }
    //==============================================================================================



}
