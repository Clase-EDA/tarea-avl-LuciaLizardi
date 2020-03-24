/*
 * To ccuelgae this license header, choose License Headers in Project Properties.
 * To ccuelgae this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libraries;

import java.util.ArrayList;

/**
 *
 * @author lucializardi
 * :) 
 * Intento practicar un poco mi inglés. (Sorry for the Spanglish) 
 */
public class AVLTree<T extends Comparable>{
    
    private AVLNode<T> raiz;
    private int cont;

  
    //Constructores
    public AVLTree() {
        this.raiz=new AVLNode(null); //Centinela
        cont=0;
    }
    
    public AVLTree(AVLNode<T> nodo){
        this.raiz=new AVLNode(null); //Centinela
        this.raiz.setRight(nodo);
        cont=1;
        
    }

    //Getters
     public int getCont() { return cont; }
     public AVLNode<T> getRaiz(){return raiz;}


    //Predicados
    public boolean isEmpty() {
        return cont==0;
    }
    
    
    private int max(int a, int b){return a>b?a:b;}
    private int height(AVLNode<T> n){return n==null?0:n.getHeight();}
    
    //Calcular la altura de un árbol.
    public int getAltura(){
        AVLNode<T> actual=raiz;
        return altura(raiz.getRight());
    }
    private int altura(AVLNode<T> actual){
      if(actual==null)
          return 0;
       int izq=altura(actual.getLeft())+1;
       int der=altura(actual.getRight())+1;
       return max(izq,der);
        }

    public void insert(T value){
        try{
            AVLNode<T> nuevo= new AVLNode(value);
            
            if(isEmpty()){
                raiz.setRight(nuevo);
                nuevo.setFather(raiz);
                cont++;
                return;
            }
            else{
            AVLNode<T> prev= this.raiz.getRight();
            AVLNode<T> actual=this.raiz.getRight();
            while(actual!=null){
                prev=actual;
                if(value.compareTo(actual.getValue())<0){
                    actual=actual.getLeft();
                } else if (value.compareTo(actual.getValue())>0){
                   actual=actual.getRight();
                }else 
                {
                return;
                }
            }
            
            prev.cuelga(nuevo);
            actual=nuevo;
            cont++;
            
            while(actual.getFather()!=raiz){
              actual.setHeight(1+max(height(actual.getLeft()),height(actual.getRight())));
              
                if(actual.calculaFactorEquilibrio()>1){
                    if(actual.getRight().calculaFactorEquilibrio()==1)
                        actual=rotateRightRight(actual);
                
                    else if(actual.getRight().calculaFactorEquilibrio()==-1)
                    actual=rotateDerIzq(actual);
                    
                }
                else{
                    if(actual.calculaFactorEquilibrio()<-1){
                        if(actual.getLeft().calculaFactorEquilibrio()==1)
                            actual=rotateIzqDer(actual);
                        else if(actual.getLeft().calculaFactorEquilibrio()==-1){
                            actual=rotateLeftLeft(actual);
                        }
                    }
                    else{
                        actual=actual.getFather();
                    }
                    
                }
            
            }
            }
            
            
        }catch(Exception e){
            System.out.println("Error :("+e);
        }
    }
        
             
    //Rotaciones
    private AVLNode rotateRightRight(AVLNode n){
        AVLNode p=n.getFather();
        
        AVLNode alpha=n;
        AVLNode beta=n.getRight();
        AVLNode gamma=n.getRight().getRight();
        
        AVLNode a=alpha.getLeft();
        AVLNode b=beta.getLeft();
        
        alpha.setRight(b);
        if(b!=null)
            b.setFather(alpha);
        beta.cuelga(alpha);
        
        alpha.setHeight(1+max(height(a),height(b)));
        beta.setHeight(1+max(height(alpha),height(gamma)));
        
        if(p!=null)
            p.cuelga(beta);
        else{
            raiz.setRight(beta);
            beta.setFather(null);
        }
        
        return p;
    }
    private AVLNode rotateLeftLeft(AVLNode n){
        AVLNode p=n.getFather();
        
        AVLNode alpha=n;
        AVLNode beta=n.getLeft();
        AVLNode gamma=n.getLeft().getLeft();
        
        AVLNode a=alpha.getRight();
        AVLNode b=beta.getRight();
        
        alpha.setLeft(b);
        if(b!=null)
            b.setFather(b);
        beta.cuelga(alpha);
        
        alpha.setHeight(1+max(height(a),height(b)));
        beta.setHeight(1+max(height(alpha),height(gamma)));
        
        if(p!=null)
            p.cuelga(beta);
        else{
            raiz.setRight(beta);
            beta.setFather(null);
        }
        return p;
    }
    private AVLNode rotateIzqDer(AVLNode n){
        AVLNode p=n.getFather();
        
        AVLNode alpha=n;
        AVLNode beta=n.getLeft();
        AVLNode gamma=n.getLeft().getRight();
        
       
        
        AVLNode a=alpha.getRight();
        AVLNode b=beta.getLeft();
        AVLNode c=gamma.getLeft();
        AVLNode d=gamma.getRight();
        
        gamma.cuelga(alpha);
        gamma.cuelga(beta);
        beta.setRight(c);
        if(c!=null)
            c.setFather(beta);
        alpha.setLeft(d);
        if(d!=null)
            d.setFather(alpha);
        
        beta.setHeight(1+max(height(b),height(c)));
        alpha.setHeight(1+max(height(d),height(a)));
        gamma.setHeight(1+max(height(beta),height(alpha)));
        
        if(p!=null)
            p.cuelga(gamma);
        else{
            raiz.setRight(gamma);
            gamma.setFather(null);
        }
        return p;
    }
    private AVLNode rotateDerIzq(AVLNode n){
        AVLNode p=n.getFather();
        
        AVLNode alpha=n;
        AVLNode beta=n.getRight();
        AVLNode gamma=n.getRight().getLeft();
        
       
        
        AVLNode a=alpha.getLeft();
        AVLNode b=beta.getRight();
        AVLNode c=gamma.getRight();
        AVLNode d=gamma.getLeft();
        
        gamma.cuelga(alpha);
        gamma.cuelga(beta);
        beta.setLeft(c);
        if(c!=null)
            c.setFather(beta);
        alpha.setRight(d);
        if(d!=null)
            d.setFather(alpha);
        
        beta.setHeight(1+max(height(b),height(c)));
        alpha.setHeight(1+max(height(d),height(a)));
        gamma.setHeight(1+max(height(beta),height(alpha)));
        
        if(p!=null)
            p.cuelga(gamma);
        else{
            raiz.setRight(gamma);
            gamma.setFather(null);
        }
        return p;
    }
  
    //Busca y regresa un nodo
     public AVLNode<T> encuentra(T elem){
           AVLNode<T> actual = raiz.getRight();
        
            while(actual!=null) {
            if(actual.getValue().equals(elem)) {
                return actual;
            }
            if(elem.compareTo(actual.getValue())<0) {
                actual = actual.getLeft();
            }
            else {
                actual = actual.getRight();
            }
        }
        return null; //nunca lo encontró
    }
    
    /**public T buscaDato(T elem){
           AVLNode<T> actual = raiz.getRight();
        
            while(actual!=null) {
            if(actual.getValue().equals(elem)) {
                return actual.getValue();
            }
            if(elem.compareTo(actual.getValue())<0) {
                actual = actual.getLeft();
            }
            else {
                actual = actual.getRight();
            }
        }
        return null; //nunca lo encontró
    }
    
    */
    
   
    
    
    
    private AVLNode SucesorEnOrden(AVLNode n){
        if(n!=null){
            if(n.getRight()!=null){
                AVLNode aux=n.getRight();
                while(aux.getLeft()!=null){
                    aux=aux.getLeft();
                }
                return aux;
            }
            else if(n.getFather()!=null){
                while(n.getFather()!=null && n == n.getFather().getRight())
                        n=n.getFather();
                return n.getFather();
            }
        }
        return null;
    }

    //Eliminar un Nodo

 public  T borra1(T elem){
        T res=elem;
        AVLNode<T> aux= encuentra(elem);
        //No está en el árbol
        if(aux==null){
            res=null;
        }
        else{
                //Si es el primer elemento
                if(cont==1){
                raiz.setRight(null);
                cont--;
                return elem;
            }
                    //Si es una hoja.
                    if(aux.getLeft()==null && aux.getRight()==null){
                        
                        if(aux.getFather().getLeft().equals(aux)) 
                            aux.getFather().setLeft(null);
                            else
                            aux.getFather().setRight(null);
                    }
                    
                    else { //no es una hoja
                        //Cuando sólo es un hijo
                         if(aux.getLeft()==null || aux.getRight()==null) {
                            if(aux.getLeft()!=null) 
                                 aux.getFather().cuelga(aux.getLeft());
                                     else
                                    aux.getFather().cuelga(aux.getRight());
                         }
                         
                         else {
                             //Es una rama
                                    AVLNode<T> actual = aux;
                                    actual = actual.getRight();
                                    while(actual.getLeft()!=null) {
                                       actual = actual.getLeft();
                                     }
                                    if(actual.getRight()!=null) {
                                    actual.getFather().cuelga(actual.getRight());
                                    }
                                    else {
                                    actual.getFather().setLeft(null);
                                    }
                                    aux.setValue(actual.getValue());
                            }
                    }
        cont--;
                        
        }
        balanceaArbol(aux);
        
        return elem;
                    
  }


 private void balanceaArbol( AVLNode<T> actual){
      
        
        
         while(actual.getFather()!=raiz){
              actual.setHeight(1+max(height(actual.getLeft()),height(actual.getRight())));
              
                if(actual.calculaFactorEquilibrio()>1){
                    if(actual.getRight().calculaFactorEquilibrio()==1)
                        actual=rotateRightRight(actual);
                
                    else if(actual.getRight().calculaFactorEquilibrio()==-1)
                    actual=rotateDerIzq(actual);
                    
                }
                else{
                    if(actual.calculaFactorEquilibrio()<-1){
                        if(actual.getLeft().calculaFactorEquilibrio()==1)
                            actual=rotateIzqDer(actual);
                        else if(actual.getLeft().calculaFactorEquilibrio()==-1){
                            actual=rotateLeftLeft(actual);
                        }
                    }
                    else{
                        actual=actual.getFather();
                    }
                    
                }
            
            }
           
 }
 
    
       
  
        
       
        
 //IMPRIMIR 
        public void imprime(){
            ArrayQueue<AVLNode<T>> l= new ArrayQueue();
            l.enqueue(raiz.getRight());
            while(!l.isEmpty()){
                AVLNode<T> nodo=l.dequeue();
                System.out.println("Value: "+nodo.getValue().toString()+" factor: "+nodo.calculaFactorEquilibrio());
                if(nodo.getRight()!=null)
                    l.enqueue(nodo.getRight());
                if(nodo.getLeft()!=null)
                    l.enqueue(nodo.getLeft());
            }
        }
}
