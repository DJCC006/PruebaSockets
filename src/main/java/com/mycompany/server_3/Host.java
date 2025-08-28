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
public class Host {
    
    public Host(){
       JFrame pantalla = new JFrame();
       pantalla.setSize(880, 683);
       pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       pantalla.setLocationRelativeTo(null);
       pantalla.setResizable(false);
       pantalla.setLayout(null);
       
       
       JTextField numberShow = new JTextField();
       numberShow.setBounds(320, 142, 232, 206);
       numberShow.setEnabled(false);
       
       
       JButton genNum = new JButton("Generar Numero");
       genNum.setBounds(320, 342, 232, 53);
       genNum.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
                Random generador= new Random();
                int num= generador.nextInt();
                NumberController.getChest().setNum(num);
                System.out.println("Numero Guardado: "+NumberController.getChest().getNum());
                numberShow.setText(String.valueOf(num));
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
       
       pantalla.add(regresar);
       pantalla.add(genNum);
       pantalla.add(numberShow);
       pantalla.setVisible(true);
       
    }
    
    public static void main(String[] args) {
        Host ventana = new Host();
    }
}
