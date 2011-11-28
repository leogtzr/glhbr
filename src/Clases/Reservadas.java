package Clases;
import Clases.Parser.TokenType;

public class Reservadas {
     
    public static final String []palabrasEspanol = {
        "si", 
        "entonces", 
        "mientras", 
        "entero", 
        "caracter", 
        "cadena",
        "booleano",
        "decimal",
        "binario",
        "romper",
        "siguiente",
        "analizar",
        "caso",
        "salir",
        "inicio",
        "fin",
        "funcion",
        "regresar",
        "cierto",
        "verdadero",
        "imprimir",
        "=",
        "!",
        "!=",
        "<",
        ">",
        "<=",
        ">=",
        "%",
        "^",
        "/", "*", "+", "-", "(",")", "==", ";"
        
           
   };
    
    public static final String []palabrasIngles = {
        "if",
        "then",
        "while",
        "int",
        "char",
        "string",
        "boolean",
        "decimal",
        "binary",
        "break",
        "continue",
        "switch",
        "case",
        "exit",
        "begin",
        "end",
        "function",
        "return",
        "true",
        "false",
        "write",
       
            /* Operadores */
        "=",
        "!",
        "!=",
        "<",
        ">",
        "<=",
        ">=",
        "%",
        "^",
        "/", "*", "+", "-", "(",")", "==", ";"
    };
    
    public static final String reservedWords[] = {
        /* Solo 8 por lo pronto */
        "if",
        "then", 
        "else", 
        "end", 
        "repetir", 
        "hasta", 
        "read", 
        "write", 
        "while",
        "infinito",
        "hacer",
        "porsiempre",
        "inicio",
        "fin",
        "pow",
        ",",
        "inc",
        "dec",
        "seno",
        "coseno"
            
    };
    
    public static final TokenType tokens[] = {
        TokenType.IF, 
        TokenType.THEN, 
        TokenType.ELSE, 
        TokenType.END, 
        TokenType.REPETIR, 
        TokenType.HASTA, 
        TokenType.LEER, 
        TokenType.WRITE,
        TokenType.WHILE,
        TokenType.INFINITO,
        TokenType.THEN,
        TokenType.END,
        TokenType.INICIO,
        TokenType.FINALIZAR,
        TokenType.POW,
        TokenType.COMA,
        TokenType.INC,
        TokenType.DEC,
        TokenType.SENO,
        TokenType.COSENO
        
    };
    
    // Método que devuelve un TokenType.
    // Sino encuentra la cadena "s" en la lista de palabras reservadas, devuelve un ID.
    public static TokenType buscarPalabra(String s) {
        
        for(int i = 0; i < reservedWords.length; i++)
            if(s.equals(reservedWords[i]))
                return tokens[i];       // Devolvemos el token que es de acuerdo a lo que nos manden
        
        return TokenType.ID;
    }
    
    // Busca si una variable (s) es reservada, 
    // idioma nos dice en qué arreglo de cadenas buscar.
    public static boolean esReservada(String s, boolean idioma) {
        
        for(int i = 0; i < palabrasEspanol.length; i++)
            // buscar en el arreglo español
            if(idioma) {
                if(s.equals(palabrasEspanol[i]) == true)
                    return true;
            } else {
                if(s.equals(palabrasIngles[i]) == true)
                    return true;
            }
        return false;
    }
    
    // Devuelve el código Hash de una cadena.
    public int codigoHash(String s) {
        int v = 0;
        for(int i = 0; i < s.length(); v += (int)s.charAt(i));
        return v % palabrasIngles.length;
    }
    
}