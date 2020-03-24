/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libraries;

/**
 *
 * @author lucializarsi
 */
public class AVLNode<T extends Comparable> {

    
        private T value;
        private AVLNode<T> left,right, father;
        private int height;
       
    //Constructor

    public AVLNode(T value) {
        this.value = value;
        left=null;
        right=null;
        father=null;
        height=1;
    }
    
    //Getters & Setters

    public T getValue() {
        return value;
    }

    public AVLNode<T> getLeft() {
        return left;
    }

    public AVLNode<T> getRight() {
        return right;
    }

    public AVLNode<T> getFather() {
        return father;
    }

    public int getHeight() {
        return height;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setLeft(AVLNode<T> left) {
        this.left = left;
    }

    public void setRight(AVLNode<T> right) {
        this.right = right;
    }

    public void setFather(AVLNode<T> father) {
        this.father = father;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    


//Cuantos Descendientes tiene este nodo
public int numDescendiente( ){   
    int res=0;
    if(left==null){
        if(right==null)
            return res;
        res++;
        right.numDescendiente();
    }
    if(right==null){
        if(left==null)
            return res;
        res++;
        left.numDescendiente();
    }
    return res;
}



public int numHijosDirectos(){
    int cont=0;
    if(left!=null)
        cont++;
    if(right!=null)
        cont++;
    return cont;
}

   

public void cuelga(AVLNode<T> n){
        if(n==null){
            return;
        }
        if(value==null){
            right=n;
            n.setFather(this);
            return;
        }
        if(n.getValue()==null){
            right=n;
        }
        if(n.getValue().compareTo(value)<0){
            left=n;
        }
        else
            right=n;
        n.setFather(this);
        
    }
    
    //Factor de Equilibrio
public int calculaFactorEquilibrio() {
        int fe=0;
        AVLNode m=getLeft();
        if(m!=null)
            fe-=m.getHeight();
        m=getRight();
        if(m!=null)
            fe+=m.getHeight();
        return fe;
    }
    
    
    
   
}
