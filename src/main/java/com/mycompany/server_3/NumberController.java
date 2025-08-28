/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server_3;

/**
 *
 * Clase singleton que permitira sincronizar el numero random generado por el host
 */
public class NumberController {
    private static NumberController chest; //Para singleton siempre debe haber una instancia de si misma, para asi poder sacar la info guardada
    private int currentNum;
    
    public NumberController(){};
    
    
    public static NumberController getChest(){//Metodo para devolver la instancia propia que se guarda 
        if(chest==null){
            chest = new NumberController();//Necesario pues se debe que instanciar la instancia que hace funcionar la clase singleton 
        }
        return chest;
    }
    
    
    public int getNum(){
        return currentNum;
    }
    
        
    public void setNum(int num){
        currentNum=num;
    }
    
}
