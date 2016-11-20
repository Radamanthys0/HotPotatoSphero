package com.example.radamanthys.hotpoatosphero.Server;

/**
 *
 * Esta classe dita qual a natureza da mensagem para com o server
 */
public class MessageTypes {

    public static final int OPEN_CONNECTION = 0; // conetar ao servidor
    public static final int CLOSE_CONNECTION = 1; // desconectar do servidor
    public static final int MOV_SPHERO = 2; // mover sphero para opontente

    public MessageTypes(){};

}
