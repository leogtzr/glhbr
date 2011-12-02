/* Contiene aparte del nodo, el tipo de dato de la variable */
package Clases;
import java.util.Enumeration;
import java.util.Hashtable;

/* @author Leonardo Gutiérrez Ramírez <leogutierrezramirez.gmail.com> */
/* Nov 30, 2011 */
public class TablaSimbolos {
    
    private String tokenID;
    private NodoArbol nodo;
    private Parser.ExpType tipoDeDato;
    public Hashtable tabla;
    
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
    
    // PENDIENTE Implementar un método para que muestre correctamente el tipo de dato.
    public void mostrarTabla() {
        Enumeration e = this.tabla.keys();
        Object obj;
        System.out.println("Identificador          No. de Línea");
        System.out.println("------------------------------------");
            
        while (e.hasMoreElements()) {
            obj = e.nextElement();
            NodoArbol x = (NodoArbol)this.tabla.get(obj); // Mostramos...
            System.out.printf("%5s              %10d\n", x.nombre, x.lineno);
            
        }
    }
    
}
