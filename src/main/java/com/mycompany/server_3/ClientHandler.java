/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * Esta clase runnable hace que todas sus instancias se ejecuten en un espacio aparte del principal....en otras palabras, en un "thread" aparte
 */
public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();//Este array list permite que los mensajes se implemente y envie a todos los clientes en el servidor
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;//nombre de cada cliente, lo representa y ayuda a ubicar dentro del array list.
    
    
    public ClientHandler(Socket socket){//Instancias de este tipo trabajan con la propiedad del socket del propio servidor, por lo que se pone como parametro
        try{
            this.socket=socket;
            //Buffers funcionan con lo que le llegan de los sockets....por lo que hay que obtener los streams de datos
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); //caracter stream---bytes stream   ---- we use this stream to read/get things
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));//we use this stream to get things from server
            this.clientUsername=bufferedReader.readLine();//Esto nos permite obtener la informacion del nombre de usuario que se le pide al usuario a la hora de entrar al servidor
            clientHandlers.add(this);//Esto nos permite agregar la informacion recolectada al arrayList, que vendria a simbolizar el agregar al usuario al gurpo de chat
            broadcastMessage("SERVIDOR: "+clientUsername +" a entrado al servidor!");
        }catch(IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter); //cierra todos los parametros en caso de algun error
            
        }
    }
    
    
    @Override
    public void run() {
        String messageFromClient;
        
        while(socket.isConnected()){
            try{
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);
                
            }catch(IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }   
        }
    }
    
    public void broadcastMessage(String messagetoSend){//Este metodo es el que se encarga de mandar los mensajes a todo los usuarios
        for(ClientHandler clientHandler : clientHandlers){
            try{
                if(!clientHandler.clientUsername.equals(clientUsername)){//Esto nos permite que se mande el mensaje a todos los usuarios excepto el mismo que lo mando
                    clientHandler.bufferedWriter.write(messagetoSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            }catch(IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }
    
    public void removeClientHandler(){//Este metodo se encarga de remover a un usuario del array list (grupo de mensaje) cuando decida cerrar sesion
        clientHandlers.remove(this);
        broadcastMessage("SERVIDOR: "+clientUsername+" ha dejado el chat!");
    }
    
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        removeClientHandler();
        try{
            if(bufferedReader!= null){
                bufferedReader.close();
            }
            if(bufferedWriter!= null){
                bufferedWriter.close();
            }
            if(socket!= null){
                socket.close();
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    
    
}
