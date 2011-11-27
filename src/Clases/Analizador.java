// ITCH II.
// Analizador léxico. GLHBR
package Clases;
import static Clases.Alfabeto.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import Clases.Parser.*;

public class Analizador {
    
    private static int c;
    private static FileReader fr = null;
    private static String lexema = "";
    private static String operador = "";
    private static String asignacionCadena = "";
    private static int nLineas = 1;
    
    public static int getLineas()
    {
        return nLineas;
    }
    
    public static void AnalizadorLexico(File f, ArrayList<Lexema> palabras, boolean idioma) throws FileNotFoundException, IOException
    {
        int indice = 1;
        nLineas = 1;
        palabras.clear();
        
        fr = new FileReader(f);        
        c = fr.read();
        
        while(c != -1)
        {
            switch(c)
            {
                case SPACE:
                    c = fr.read();
                    continue;
                case TAB:
                    c = fr.read();
                    continue;
                case NEWLINE:
                    nLineas++;
                    c = fr.read();
                    continue;
            }
            
            /* A..Z | a..z Es probable que sea un identificador, comenzamos el scanner  */
            if(Character.isLetter(c) || (c == GUION_BAJO))
            {
                while(Character.isLetter(c) || Character.isDigit(c) || (c == GUION_BAJO))
                {
                    lexema += Character.toString((char)c);
                    c = fr.read();
                }
                //System.out.print("[" + lexema + "] --> " + nLineas + "\n");
                //palabras.add(new Lexema(lexema, TokenType.ID).setLineNo(nLineas)); // Por si no jala la de abajo...
                palabras.add(new Lexema(lexema, Reservadas.buscarPalabra(lexema)).setLineNo(nLineas));
                
                lexema = "";
            }
            
            /*Si después de filtrar el identificador hallamos un número, buscamos por un literal numérico */
            
            if(Character.isDigit(c))
            {
                // Concatenamos los digitos...
                while(Character.isDigit(c))
                {
                    lexema += Character.toString((char)c);
                    c = fr.read();
                }
                /* digito+(digito+|e)(E(+|-|e)digito+|e) */
                if((char)c == DOT)
                {
                    /* Avanzamos después del punto */
                    c = fr.read();
                    lexema += Character.toString('.');

                    if(!Character.isDigit(c))
                    {
                        //System.out.printf("Error : [%c], se esperaban uno o más dígitos ---> %d\n", c, nLineas);
                        lexema += "0"; // Tratar de corregir.
                    }
                    /* Concatenamos todos los dígitos... */
                    while(Character.isDigit((char)c))
                    {
                        lexema += Character.toString((char)c);
                        c = fr.read(); // fgetc(inputFILE);
                    }
                    /* Hacer comprobacion de numero con notación científica */
                    if((char)c == EXPONENCIAL)
                    {
                        lexema += Character.toString((char)c);
                        /* Hacer la comprobación de + ó - ó cadena vacía */
                        c = fr.read();
                        /* Si lo que sigue es un operador + ó - */
                        if((c == PLUS_OP) || (c == MINUS_OP))
                        {
                            lexema += Character.toString((char)c);
                            c = fr.read();
                            /* Avanzamos al caracter después del punto */
                            while( Character.isDigit(c) )
                            {
                                lexema += Character.toString((char)c);
                                c = fr.read();
                            }
                        } else
                            /* Los operadores +- no son obligatorios */
                            /* Concatenamos mientras que sean dígitos... */
                            while( Character.isDigit(c) )
                            {
                                lexema += Character.toString((char)c);
                                c = fr.read();//fgetc(inputFILE);
                            }
                    }
                } else {
                    //System.out.printf("Identificador (Literal numérico decimal) : [%s] ---> %d\n", lexema, nLineas);
                    palabras.add(new Lexema(lexema, TokenType.NUM).setLineNo(nLineas));
                    lexema = "";
                    continue;
                }

                    //System.out.printf("Identificador (Literal numérico decimal) : [%s] ---> %d\n", lexema, nLineas);
                    palabras.add(new Lexema(lexema, TokenType.NUM).setLineNo(nLineas));
                    lexema = "";
            }

            switch(c)
            {
                case SPACE: break;

                case EXP:
                    //System.out.printf("Operador (Exponenciación - ^) : %c ---> %d\n", c, nLineas);
                    palabras.add(new Lexema("^", TokenType.POW).setLineNo(nLineas));
                    break;

                case L_PARENTESIS:
                    palabras.add(new Lexema("(", TokenType.LPARENT).setLineNo(nLineas));
                    //System.out.printf("Delimitador (LParen - ( ) : %c ---> %d\n", c, nLineas);
                    break;

                case R_PARENTESIS:
                    palabras.add(new Lexema(")", TokenType.RPARENT).setLineNo(nLineas));
                    //System.out.printf("Delimitador (RParen - ) ) : %c ---> %d\n", c, nLineas);
                    break;

                case SEMI:
                    palabras.add(new Lexema(";", TokenType.SEMI).setLineNo(nLineas));
                    //System.out.printf("Identificador (SEMICOLON ; ) : %c ---> %d\n", c, nLineas);
                    indice++;
                    break;

                case COMA:
                    palabras.add(new Lexema(",", TokenType.COMA).setLineNo(nLineas));
                    //System.out.printf("Delimitador (COMA , ) : %c ---> %d\n", c, nLineas);
                    break;

                case PLUS_OP:
                    palabras.add(new Lexema("+", TokenType.PLUS).setLineNo(nLineas));
                    //System.out.printf("Operador (SUMA + ) : %c ---> %d\n", c, nLineas);
                    break;

                case MINUS_OP:
                    palabras.add(new Lexema("-", TokenType.MINUS).setLineNo(nLineas));
                    //System.out.printf("Operador (RESTA - ) : %c ---> %d\n", c, nLineas);
                    break;

                case MULTIPLICACION:
                    palabras.add(new Lexema("*", TokenType.TIMES).setLineNo(nLineas));
                    //System.out.printf("Operador (MULTIPLICACION * ) : %c ---> %d\n", c, nLineas);
                    break;

                case NEWLINE:
                    nLineas++;
                    break;

                case DIVISION:

                    c = fr.read();
                    if(c == DIVISION)
                    {
                        //System.out.println("Comentario // ... " + nLineas);
                        while(c != '\n')   /* Simplemente ignorar */
                        c = fr.read(); 
                        nLineas++;
                    } else {
                        palabras.add(new Lexema("/", TokenType.OVER).setLineNo(nLineas));
                        //System.out.printf("Operador (DIVISION / ) : [%c] ---> %d\n", DIVISION, nLineas);
                        continue;
                    }

                    break;

                case MOD:
                    palabras.add(new Lexema("%", TokenType.MOD).setLineNo(nLineas));
                    //System.out.printf("Operador (MODULO $) : %c ---> %d\n", c, nLineas);
                    break;

                case MENOR:

                    operador += Character.toString((char)c);
                    c = fr.read();
                    if(c == IGUAL)
                    {
                        operador += Character.toString((char)c);
                        palabras.add(new Lexema(operador, TokenType.LT).setLineNo(nLineas));
                        //System.out.printf("\nOperador (MENOR IGUAL) : [%s] ---> %d\n", operador, nLineas);
                    } else {        // Menor igual...
                        palabras.add(new Lexema(operador, TokenType.LT).setLineNo(nLineas));
                        //System.out.printf("\nOperador final : [%s] ---> %d\n", operador, nLineas);
                        operador = "";
                        continue;
                    }

                    operador = "";
                    break;

                case MAYOR:
                    operador += Character.toString((char)c);
                    c = fr.read();
                    if((char)c == IGUAL)
                    {
                        operador += Character.toString((char)c);
                        palabras.add(new Lexema(operador, TokenType.GEQ).setLineNo(nLineas));
                        //System.out.printf("\nOperador final : [%s] ---> %d\n", operador, nLineas);
                    } else {
                        palabras.add(new Lexema(operador, TokenType.GT).setLineNo(nLineas));
                        //System.out.printf("\nOperador final : [%s] ---> %d\n", operador, nLineas);
                        operador = "";
                        continue;
                    }

                    operador = "";

                break;

                case NOT:

                    operador += Character.toString((char)c);
                    c = fr.read();
                    if((char)c == IGUAL)
                    {
                        operador += Character.toString((char)c);
                        palabras.add(new Lexema(operador, TokenType.NEQ).setLineNo(nLineas));
                        //System.out.printf("\nOperador final != : [%s] ---> %d\n", operador, nLineas);
                    } else {
                        //System.out.printf("\nOperador final NOT : [%s] ---> %d\n", operador, nLineas);
                        palabras.add(new Lexema(operador, TokenType.NOT).setLineNo(nLineas));
                        operador = "";
                        continue;
                    }

                    operador = "";
                    break;

                    case IGUAL:

                    operador += Character.toString((char)c);

                    c = fr.read();
                    if((c == MATCH) || (c == IGUAL))
                    {
                        operador += Character.toString((char)c);
                        //System.out.printf("\nOperador final : [%s] ---> %d\n", operador, nLineas);
                        palabras.add(new Lexema(operador, TokenType.EQ).setLineNo(nLineas));
                    } else {
                        //System.out.printf("\nOperador final : [%s] ---> %d\n", operador, nLineas);
                        palabras.add(new Lexema(operador, TokenType.ASSIGN).setLineNo(nLineas));
                        operador = "";
                        continue;
                    }

                    operador = "";
                    break;


                case DOBLEQUOTE:
                    c = fr.read();
                    while(c != '"' )
                    {
                            /* Acumulamos */
                            switch(c)
                            {
                                case NEWLINE:           /* Ignoramos el new line */
                                    nLineas++;
                                    break;
                                default:
                                    asignacionCadena += Character.toString((char)c);
                            }

                            c = fr.read();
                            if(c == IGUAL)
                            {
                                c = fr.read();
                                break;
                            }

                    }
                        //System.out.printf("Asignación de cadena : [%s] ---> %d\n", asignacionCadena, nLineas);
                        palabras.add(new Lexema(asignacionCadena, TokenType.ID).setLineNo(nLineas));
                        asignacionCadena = "";

                    break;

                /* Reconocer comentarios */
                case GATO:
                    //System.out.println("Comentario multilinea... ---> " + nLineas);
                    c = fr.read();
                    while((c != GATO) && (c != -1))
                        c = fr.read();
                    break;

                case AMPERSAND:
                operador += Character.toString((char)c);
                    c = fr.read();
                    if(c == AMPERSAND)
                        operador += Character.toString((char)c);
                
                    palabras.add(new Lexema(operador, TokenType.AND).setLineNo(nLineas));
                    //System.out.printf("\nOperador final : [%s] ---> %d\n", operador, nLineas);
                    operador = "";
                    break;
                    
                case OR:
                operador += Character.toString((char)c);
                    c = fr.read();
                    if(c == OR)
                        operador += Character.toString((char)c);
                
                    palabras.add(new Lexema(operador, TokenType.OR).setLineNo(nLineas));
                    //dSystem.out.printf("\nOperador final : [%s] ---> %d\n", operador, nLineas);
                    operador = "";
                    break;
            }
            c = fr.read();
    }
        fr.close();
    }
   
    /* Devuelve <x, 1> <A, 2> <SUM, 2>     */
    public static String getLexForm(ArrayList<Lexema> palabras, boolean idioma)
    {
        String forma = "";
        // La tabla Hash a utilizar.
        HashMap<String, Integer> hash = new HashMap<String, Integer>();
        // Recorrer primero y agregar los no reservadas.
        int direccion_preambulo = 0;
        for(int i = 0; i < palabras.size(); i++)            // Recorremos todo el ArrayList.
            if(!Reservadas.esReservada(palabras.get(i).getValor(), idioma) && !hash.containsKey(palabras.get(i).getValor()))
                 hash.put(palabras.get(i).getValor(), hash.size());
            else ++direccion_preambulo; 
        
        for(int i = 0; i < palabras.size(); i++)
            if(Reservadas.esReservada(palabras.get(i).getValor(), idioma))
                forma += "< " + palabras.get(i).getValor() + " > " + (palabras.get(i).getValor().equals(";") ? "\n" : "");
            else
                forma += "< " + palabras.get(i).getValor() + ", " + hash.get(palabras.get(i).getValor()) + " > " + (palabras.get(i).getValor().equals(";") ? "\n" : "");
        
        return forma;
    }
    
}
