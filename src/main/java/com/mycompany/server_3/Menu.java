/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server_3;

import javax.swing.JFrame;
import java.awt.Frame;
import  javax.swing.*;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author David
 */
public class Menu {
    
    
    
    
    public Menu(){
       JFrame pantalla = new JFrame();
       pantalla.setSize(351, 478);
       pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       pantalla.setLocationRelativeTo(null);
       pantalla.setResizable(false);
       pantalla.setLayout(null);
       
       
       
       
       JButton openHost = new JButton("HOST");
       openHost.setBounds(70, 60, 200, 45);
       openHost.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
                String username="HOST";
                Host ventana2= new Host();
                pantalla.dispose();
          }
                    
        });
       
       
       
       JButton openColors = new JButton("Colores");
       openColors.setBounds(70, 260, 200, 45);
       openColors.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
            String username;
            username=JOptionPane.showInputDialog("Ingrese su nombre de usuario");
             System.out.println(username);
                   
            Encendedor ventana2= new Encendedor();
            pantalla.dispose();
          }
                    
        });
       
       pantalla.add(openHost);
       pantalla.add(openColors);
       pantalla.setVisible(true);
        
    }
    
    
    public static void main(String[] args) {
        Menu ventana = new Menu();
        NumberController.getChest();//Creacion de instancia solamente
    }
    
}
