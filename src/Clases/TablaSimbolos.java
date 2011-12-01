/* Contiene aparte del nodo, el tipo de dato de la variable */
package Clases;
import java.util.Hashtable;

/* @author Leonardo Gutiérrez Ramírez <leogutierrezramirez.gmail.com> */
/* Nov 30, 2011 */
public class TablaSimbolos {
    
    private String tokenID;
    private NodoArbol nodo;
    private Parser.ExpType tipoDeDato;
    private Hashtable tabla;
    
    public TablaSimbolos(String tokenID, NodoArbol nodo) {
        this.tokenID = tokenID;
        this.nodo = nodo;
        tipoDeDato = Parser.ExpType.Integer;
        tabla = new Hashtable();
    }
    
    public TablaSimbolos() {
        tokenID = "";
        nodo = null;
        tabla = new Hashtable();
    }
    
    public String getTokenID() {
        return tokenID;
    }
    
    public NodoArbol getNodo() {
        return nodo;
    }
    
    public Parser.ExpType getTipoExp() {
        return tipoDeDato;
    }
    
    public void put(String tokenID, NodoArbol t) {
        tabla.put(tokenID, t);
    }
    
    // GIT Tabla de simbolos con sus métodos implementados.
    public boolean existeVar(String id) {
        if(tabla.get(id) != null)
            return true;
        else
            return false;
    }
    
}
