/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Libraries.AVLTree;

/**
 *
 * @author lucializarsi
 */
public class Tests {
    public static void main(String[] args) {
        AVLTree a = new AVLTree<Integer>();
        System.out.println(a.getAltura());
        System.out.println("IS IT EMPTY? "+a.isEmpty());
        
        a.insert(9);
      
        System.out.println("IS IT EMPTY? "+a.isEmpty());
        a.insert(0);
        a.insert(100);
        a.insert(89);
        a.insert(78);
        a.insert(6);
        
        System.out.println("IS IT EMPTY? "+a.isEmpty());
        System.out.println(a.getAltura());
        System.out.println(a.getCont());

        System.out.println("GIVE ME THE NUMBER IF IT'S IN THERE: "+a.encuentra(89).getValue());
        System.out.println("GIVE ME THE NUMBER IF IT'S IN THERE: "+a.encuentra(1));
        
        a.borra1(100);
        System.out.println(a.getCont()+"\n");
        
        
        
       a.imprime();
        
    }
     

}
