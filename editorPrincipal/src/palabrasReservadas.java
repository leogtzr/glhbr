/*
 * License GPL v3.
 * Esta clase contiene todas las palabras reservadas del lenguaje.
 * 
 * 
 */
/**
 *
 * @author Leo Gutiérrez R. <leogutierrezramirez@gmail.com> ITCH II
 */
public class palabrasReservadas {
// reservadas.length contiene el número de palabras...
    // TODO cambiar por palabras reservadas propias de nuestro lenguaje...
    String []reservadas = {
	"write", "read", "if", "do", "while", "for", "int", "long",
	"break", "case", "switch", "return", "default", "else", "struct",
	"goto", "float", "begin", "end"
    };

    // Checa si s forma parte del conjunto de palabras reservadas, si forma parte
    // devuelve true, sino false...
    public boolean esReservada(String s)
    {
        for(short i = 0; i < reservadas.length; i++)
            if(s.equals(reservadas[i]))
                return true;
        return false;
    }
    
}
