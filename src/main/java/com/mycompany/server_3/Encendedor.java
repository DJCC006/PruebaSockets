/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server_3;
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
public class Encendedor {
    
    
    
    
    public Encendedor(){
       JFrame pantalla = new JFrame();
       pantalla.setSize(880, 683);
       pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       pantalla.setLocationRelativeTo(null);
       pantalla.setResizable(false);
       pantalla.setLayout(null);
       
       
       JButton colorShow = new JButton();
       colorShow.setBounds(320, 142, 232, 206);
       colorShow.setEnabled(false);
       colorShow.setBackground(Color.GRAY);
       
       JButton changeButton = new JButton("CAMBIAR COLOR");
       changeButton.setBounds(320, 342, 232, 53);
       changeButton.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
                Random generador= new Random();
                int num= generador.nextInt(4);
                switch(num){
                    case 0:
                        colorShow.setBackground(Color.red);
                        break;

                    case 1:
                        colorShow.setBackground(Color.BLUE);
                        break;

                    case 2:
                        colorShow.setBackground(Color.YELLOW);
                        break;

                    case 3:
                        colorShow.setBackground(Color.MAGENTA);
                        break;
                }
                System.out.println(num);
          }
                    
        });
       
       
        JButton regresar = new JButton("Regresar");
       regresar.setBounds(320, 400, 232, 53);
          regresar.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
                Menu ventana = new Menu();
                pantalla.dispose();
          }
                    
        });
       
       
       
       
       pantalla.add(changeButton);
       pantalla.add(colorShow);
       pantalla.add(regresar);
       
       JTextField showNumHost = new JTextField();
       showNumHost.enable(false);
       showNumHost.setBounds(320, 42, 232, 60);
       showNumHost.setText(String.valueOf(NumberController.getChest().getNum()));
       
       
       pantalla.add(showNumHost);
       pantalla.setVisible(true);
    }
    
    
    
    public static void main(String[] args) {
        Encendedor screen = new Encendedor();
        
    }
  
    
    
    
}
