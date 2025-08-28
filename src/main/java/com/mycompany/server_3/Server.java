/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server_3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class Server {
    private ServerSocket serverSocket;
    
    public Server(ServerSocket serverSocket){
        this.serverSocket=serverSocket;
    }//Constructor de la clase server.....crea un nuevo socket de server cada vez que se crea
    
    
    //Creacion del servidor que se estara ejecutando y corriendo
    public void startServer(){
        
        try{
            
            while(!serverSocket.isClosed()){//Ejecuta el servidor mientras este se encuentra abierto
                Socket socket = serverSocket.accept();//El servidor se mantiene en espera hasta que entre un socket nuevo de usuario
                System.out.println("A new user has connected! ");//Respuesta del servidor
                ClientHandler clientHandler = new ClientHandler(socket); //Esta clase hace que cada nueva instancia se maneje dentro de un "thread" por separado, lo que nos permite tener multiples usuarios
                
                
                Thread thread = new Thread(clientHandler); //Crea un nuevo thread en donde se va a estar manejando el nuevo usuario
                thread.start();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    public void closeServerSocket(){
        try{
            if(serverSocket!= null){
                serverSocket.close();//Esto va a hacer que, en caso de error, el servidor se cierre
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(777);//Creacion de objeto de tipo serversocket el cual espera la conexion y mandado de datos
            Server server = new Server(serverSocket);//Creacion de instancia de la clase server 
            server.startServer();//Inicio del servidor
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    }
    
    

