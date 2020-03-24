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
public class ArrayQueue<T> implements QueueADT<T>{
    private T[] queue;
    private int front,rear;
    private final int DEFAULT_CAPACITY=100;
    
    
    public ArrayQueue(){
        queue=(T[])(new Object[DEFAULT_CAPACITY]);
        front=rear=0;
    }
    public ArrayQueue(int n){
        queue=(T[])(new Object[n]);
        front=rear=0;
    }
    
    public void enqueue(T dato){
        if(front==rear && queue[rear]!=null)
            expandCapacity();
            queue[rear]=dato;
          rear=(rear+1)%queue.length;
         System.out.println(rear);
        }
    
    
    private void expandCapacity(){
        T[] larger=(T[])(new Object[2*queue.length]);
        for(int i=front; i<queue.length; i=i+1)
            larger[i-front]=queue[i];
        for(int i=0;i<rear; i=i+1)
            larger[queue.length-rear+i]=queue[i];
        front=0;
        rear=queue.length;
        queue=larger;
    }
    
    public T dequeue(){
        T res;
        if(isEmpty()){
            throw new EmptyQueueException();
        }
        else{
            res=queue[front];
            queue[front]=null;
            front=(front+1)%queue.length;
        }
                    return res;
    }
       
    public T first(){
        T res;
        if(isEmpty())
            throw new EmptyQueueException(); 
            else {   
            res=queue[front];
            return res;

             }

        }
    
    public boolean isEmpty(){
        boolean res=false;
        if(front==rear && queue[front]==null)
        res=true;
        return res;
    }
    
    

}
