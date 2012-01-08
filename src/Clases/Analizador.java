// PENDIENTE Cambiar String's y concatenaciones por operaciones con StringBuilder.

package Clases;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Clases.Parser.*;

public class Analizador {
    
    static {
        fr = null;
        lexema = "";
        operador = "";
        asignacionCadena = "";
        nLineas = 1;
        c = 0;
    }
    
    private static int c = 0;
    private static FileReader fr;
    private static String lexema;
    private static String operador;
    private static String asignacionCadena;
    private static int nLineas;
    
    public static void AnalizadorLexico(File f, ArrayList<Lexema> palabras, boolean idioma) throws FileNotFoundException, IOException {
        int indice = 1;
        nLineas = 1;
        palabras.clear();
        
        fr = new FileReader(f);        
        c = fr.read();
        
        while(c != -1) {
            switch(c) {
                case ' ':
                    c = fr.read();
                    continue;
                case '\t':
                    c = fr.read();
                    continue;
                case '\n':
                    nLineas++;
                    c = fr.read();
                    continue;
            }
            
            /* A..Z | a..z Es probable que sea un identificador, comenzamos el escaneo  */
            if(Character.isLetter(c) || (c == '_')) {
                while(Character.isLetter(c) || Character.isDigit(c) || (c == '_')) {
                    lexema += Character.toString((char)c);
                    c = fr.read();
                }
                palabras.add(new Lexema(lexema, Reservadas.buscarPalabra(lexema)).setLineNo(nLineas));
                lexema = "";
            }
            
            /*Si después de filtrar el identificador hallamos un número, buscamos por un literal numérico */           
            if(Character.isDigit(c)) {
                while(Character.isDigit(c)) {
                    lexema += Character.toString((char)c);
                    c = fr.read();
                }
                
                if((char)c == '.') {        /* digito+(digito+|e)(E(+|-|e)digito+|e) */
                    /* Avanzamos después del punto */
                    c = fr.read();
                    lexema += Character.toString('.');

                    if(!Character.isDigit(c)) {
                        lexema += "0"; // Tratar de corregir.
                    }
                    /* Concatenamos todos los dígitos... */
                    while(Character.isDigit((char)c)) {
                        lexema += Character.toString((char)c);
                        c = fr.read();
                    }
                    /* Hacer comprobacion de numero con notación científica */
                    if((char)c == 'E') {
                        lexema += Character.toString((char)c);
                        /* Hacer la comprobación de + ó - ó cadena vacía */
                        c = fr.read();
                        /* Si lo que sigue es un operador + ó - */
                        if((c == '+') || (c == '-')) {
                            lexema += Character.toString((char)c);
                            c = fr.read();
                            /* Avanzamos al caracter después del punto */
                            while( Character.isDigit(c) ) {
                                lexema += Character.toString((char)c);
                                c = fr.read();
                            }
                        } else
                            /* Los operadores +- no son obligatorios. Concatenamos mientras que sean dígitos... */
                            while( Character.isDigit(c) ) {
                                lexema += Character.toString((char)c);
                                c = fr.read();
                            }
                    }
                } else {
                    palabras.add(new Lexema(lexema, TokenType.NUM).setLineNo(nLineas));
                    lexema = "";
                    continue;
                }
                    palabras.add(new Lexema(lexema, TokenType.NUM).setLineNo(nLineas));
                    lexema = "";
            }

            switch(c) {
                case ' ': break;
                case '^':
                    palabras.add(new Lexema("^", TokenType.POW).setLineNo(nLineas));
                    break;

                case '(':
                    palabras.add(new Lexema("(", TokenType.LPARENT).setLineNo(nLineas));
                    break;
                case ')':
                    palabras.add(new Lexema(")", TokenType.RPARENT).setLineNo(nLineas));
                    break;

                case ';':
                    palabras.add(new Lexema(";", TokenType.SEMI).setLineNo(nLineas));
                    indice++;
                    break;

                case ',':
                    palabras.add(new Lexema(",", TokenType.COMA).setLineNo(nLineas));
                    break;

                case '+':
                    palabras.add(new Lexema("+", TokenType.PLUS).setLineNo(nLineas));
                    break;

                case '-':
                    palabras.add(new Lexema("-", TokenType.MINUS).setLineNo(nLineas));
                    break;

                case '*':
                    palabras.add(new Lexema("*", TokenType.TIMES).setLineNo(nLineas));
                    break;

                case '\n':
                    nLineas++;
                    break;

                case '/':

                    c = fr.read();
                    if(c == '/') {
                        while(c != '\n')   /* Simplemente ignorar */
                        c = fr.read(); 
                        nLineas++;
                    } else {
                        palabras.add(new Lexema("/", TokenType.OVER).setLineNo(nLineas));
                        continue;
                    }

                    break;

                case '%':
                    palabras.add(new Lexema("%", TokenType.MOD).setLineNo(nLineas));
                    break;

                case '<':

                    operador += Character.toString((char)c);
                    c = fr.read();
                    if(c == '=') {
                        operador += Character.toString((char)c);
                        palabras.add(new Lexema(operador, TokenType.LT).setLineNo(nLineas));
                    } else {        // Menor igual...
                        palabras.add(new Lexema(operador, TokenType.LT).setLineNo(nLineas));
                        operador = "";
                        continue;
                    }

                    operador = "";
                    break;

                case '>':
                    operador += Character.toString((char)c);
                    c = fr.read();
                    if((char)c == '=') {
                        operador += Character.toString((char)c);
                        palabras.add(new Lexema(operador, TokenType.GEQ).setLineNo(nLineas));
                    } else {
                        palabras.add(new Lexema(operador, TokenType.GT).setLineNo(nLineas));
                        operador = "";
                        continue;
                    }
                    operador = "";
                break;

                case '!':

                    operador += Character.toString((char)c);
                    c = fr.read();
                    if((char)c == '=') {
                        operador += Character.toString((char)c);
                        palabras.add(new Lexema(operador, TokenType.NEQ).setLineNo(nLineas));
                    } else {
                        palabras.add(new Lexema(operador, TokenType.NOT).setLineNo(nLineas));
                        operador = "";
                        continue;
                    }

                    operador = "";
                    break;

                    case '=':

                    operador += Character.toString((char)c);

                    c = fr.read();
                    if((c == '?') || (c == '=')) {
                        operador += Character.toString((char)c);
                        palabras.add(new Lexema(operador, TokenType.EQ).setLineNo(nLineas));
                    } else {
                        palabras.add(new Lexema(operador, TokenType.ASSIGN).setLineNo(nLineas));
                        operador = "";
                        continue;
                    }

                    operador = "";
                    break;
                        
                case '"':
                    c = fr.read();
                    while(c != '"') {
                            /* Acumulamos */
                            switch(c) {
                                case '\n':           /* Ignoramos el new line */
                                    nLineas++;
                                    break;
                                default:
                                    asignacionCadena += Character.toString((char)c);
                            }

                            c = fr.read();
                            if(c == '=') {
                                c = fr.read();
                                break;
                            }
                    }
                        palabras.add(new Lexema(asignacionCadena, TokenType.CADENA).setLineNo(nLineas));
                        asignacionCadena = "";

                    break;

                /* Reconocer comentarios */
                case '#':
                    c = fr.read();
                    while((c != '#') && (c != -1))
                        c = fr.read();
                    break;

                case '&':
                operador += Character.toString((char)c);
                    c = fr.read();
                    if(c == '&')
                        operador += Character.toString((char)c);
                
                    palabras.add(new Lexema(operador, TokenType.AND).setLineNo(nLineas));
                    operador = "";
                    break;
                    
                case '|':
                operador += Character.toString((char)c);
                    c = fr.read();
                    if(c == '|')
                        operador += Character.toString((char)c);
                
                    palabras.add(new Lexema(operador, TokenType.OR).setLineNo(nLineas));
                    operador = "";
                    break;
            }
            c = fr.read();
    }
        fr.close();
    }
   
}
