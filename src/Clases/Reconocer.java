/* Clase que reconoce patrone   s basándose en una cadena */
package Clases;

import java.util.regex.Pattern;

/* @author Leonardo Gutiérrez Ramírez <leogutierrezramirez.gmail.com> */
/* Oct 18, 2011 */
public class Reconocer {

    private static boolean reconocerElementoLexico(String token, String expresionRegular) {
        return Pattern.matches(token, expresionRegular);
    }
    
    public static boolean isId(String token) {
        return reconocerElementoLexico(token, "(_|[a-zA-Z])(\\d|[a-zA-z]|_)*");
    }
    
    public static boolean isInteger(String token) {
        return reconocerElementoLexico(token, "[+-]?\\d+");
    }
            
    public  boolean isCientific(String token ) { 
		 return reconocerElementoLexico(token,"[+-]?\\d*[\\.]?\\d+[eE][+-]?\\d+");
	}
	
	public  boolean isFloat(String token) { 
		 if(reconocerElementoLexico(token,"[+-]?\\d*\\.\\d+"))
			 return true;
		 return isCientific(token);
	}
		  
	public  boolean isParentL(String token) { 
		return reconocerElementoLexico(token,"\\({1}");
	}
	
	public  boolean isParentR(String token) { 
		return reconocerElementoLexico(token,"\\){1}");
	}
	
	public  boolean isSum(String token) { 
		return reconocerElementoLexico(token,"\\+{1}");
	}
	
	public  boolean isRest(String token) { 
		return reconocerElementoLexico(token,"\\-{1}");
	}
	
	public  boolean isMult(String token) { 
		return reconocerElementoLexico(token,"\\*{1}");
	}

	public  boolean isDiv(String token) { 
		return reconocerElementoLexico(token,"\\/{1}");
	}
	
	public  boolean isMod(String token) { 
		return reconocerElementoLexico(token,"\\%{1}");
	}
	
	public  boolean isExp(String token) { 
		return reconocerElementoLexico(token,"\\^{1}");
	}
        
        public static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
    
}
