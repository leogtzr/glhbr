// PENDIENTE El generador debe de establecer una comunicación directa con la tabla de símbolos.
package Clases;

import java.util.ArrayList;

/* @author Leonardo Gutiérrez Ramírez <leogutierrezramirez.gmail.com> */
/* Dec 5, 2011 */
public class Generador {
    private int tope;
    private String stackString;
    ArrayList<NodoArbol> pila;
    public Generador() {
        tope = 0;
        pila = null;
    }
    public Generador(String stackString) {
        this.stackString = stackString;
        tope = 0;
        pila = null;
    }
    public Generador(ArrayList<NodoArbol> pila) {
        this.pila = pila;
        tope = 0;
    }
    public Generador(TablaSimbolos table) {
        
    }
    
}
