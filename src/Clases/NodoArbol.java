package Clases;
import Clases.Parser.*;

public class NodoArbol {
    
    public NodoArbol() {
        hijos = new NodoArbol[3];
        hijos[0] = null;
        hijos[1] = null;
        hijos[2] = null;
        valor = 0;
        valorDecimal = 0.0;         /* Solo para tipos decimales */
    }
    
    public NodoArbol []hijos; /* Maximo de tres hijos MAXHIJOS */
    public NodoArbol hermano;   /* El nodo hermano */
    int lineno;                 /* Número de línea donde se encuentra el nodo */
    
    NodeKind nodeKind;    /* Sentencia o expresión */
    StmtKind stmt; /* Tipo de sentencia */
    ExpKind exp;  /* Tipo de expresion */
    TokenType op;      /* Tipo de operador */
    int valor = 0;
    double valorDecimal = 0.0;
    String nombre;
    ExpType type;         /* Para verificación de tipo de expresiones */

}
