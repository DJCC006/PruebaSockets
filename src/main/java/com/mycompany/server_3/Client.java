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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;//Lee señales entrantes
    private BufferedWriter bufferedWriter; //Escribe señales   al final todo esto se dirige al flujo del socket
    private String username;
    
    
    public Client(Socket socket, String username){//Constructor de cliente, donde se inicializa su respectivo socket y readers y writers
        try{
            this.socket=socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username=username;
        }catch(IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    
    public void sendMessage(){
        try{
            bufferedWriter.write(username);//Escribe el nombre de usuario en chat //Lo escribe dentro del buffered writer
            bufferedWriter.newLine();
            bufferedWriter.flush();
            
            Scanner lea = new Scanner(System.in);
            lea.useDelimiter("\n");
            while(socket.isConnected()){//Funcionalidad de mandar el mensaje, mostrar en pantalla
                String messageToSend = lea.nextLine();
                bufferedWriter.write(username+ ": "+messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }catch(IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    
    public void listenForMessage(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                String msgFromGroupChat;
                
                while(socket.isConnected()){
                    try{
                        msgFromGroupChat = bufferedReader.readLine();
                        System.out.println(msgFromGroupChat);
                    }catch (IOException e){
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }   
                }
            } 
        }).start();
        
    }
    
    
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
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
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        System.out.println("Ingrese su nombre de usuario para entrar al chat: ");
        String username = scanner.next();
        Socket socket;
        try {
            socket = new Socket("Localhost", 777);
            Client client = new Client(socket,username);
            client.listenForMessage();
            client.sendMessage();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
}
