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
public class EmptyQueueException extends RuntimeException {
    EmptyQueueException(){
            super("LA COLA ESTÁ VACÍA");
}
}
