package Clases;
import Clases.Parser.*;

// TreeNode. Nuestro prototipo de nodo.
public class NodoArbol {
    
    public NodoArbol() {
        child = new NodoArbol[3];
        child[0] = null;
        child[1] = null;
        child[2] = null;
    }
    
    public NodoArbol []child; /* Maximo de tres hijos MAXHIJOS */
    public NodoArbol sibling;   /* El nodo hermano */
    int lineno;                 /* Número de línea donde se encuentra el nodo */
    
    NodeKind nodeKind;    /* Sentencia o expresión */
    StmtKind stmt; /* Tipo de sentencia */
    ExpKind exp;  /* Tipo de expresion */
    TokenType op;      /* Tipo de operador */
    int valor;
    String nombre;
    ExpType type;         /* Para verificación de tipo de expresiones */

}
