package Clases;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JOptionPane;

/* @author Leonardo Gutiérrez Ramírez <leogutierrezramirez.gmail.com> */
/* Dec 5, 2011 */

public class Generador {
    
    private int tope = 0;
    private ArrayList<String> pila = null;
    private String programName = null;
    private TablaSimbolos tablaSimbolosGenerador = null;
    private Stack<Double> pilaEvaluacion = null;
    private double a = 0.0;
    private double b = 0.0;
    private double resultado = 0.0;
    
    public String getProgramName() {
        return programName;
    }
    
    public void setProgramName(String programName) {
        this.programName = programName;
    }
    
    public TablaSimbolos getTablaSimbolosGenerador() {
        return tablaSimbolosGenerador;
    }
    
    public void setTablaSimbolosGenerador(TablaSimbolos table) {
        tablaSimbolosGenerador = table;
    }
    
    public Generador(ArrayList<String> pila_2, String programName) {
        tope = 0;
        pila = null;
        pila = (ArrayList<String>)pila_2.clone();
        this.programName = programName;
        pilaEvaluacion = new Stack<Double>();
    }
    
        public static boolean isOperator(String op) {
    	if(op.equals("+")) {
    		return true;
    	} else if(op.equals("-")) {
    		return true;
    	} else if(op.equals("*")) {
    		return true;
    	} else if(op.equals("/")) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    // PENDIENTE Correcto formateado con %s.
    public void generar() {
        
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        try {
            fichero = new FileWriter("source.asm");
            pw = new PrintWriter(fichero);
            pw.print("; Código ensamblador para el programa \"" + programName + "\"");
            pw.println();
            
        for(int i = 0; i < pila.size() - 1; i++) {
            
            if(!isOperator(pila.get(i))) {      // Si es falso, entonces es un operando.
                pw.println("MOV " + ("R" + tope) + ",#" + pila.get(i) + " ; Cargamos " + pila.get(i));
                pilaEvaluacion.push(Double.parseDouble(pila.get(i)));
                tope++;
            } else {
                switch(pila.get(i).charAt(0)) {
                    case '+':
                        pw.println("ADD R" + (tope - 2) + ",R" + (tope - 1) + " ; Sumamos");
                        
                        a = pilaEvaluacion.pop();
                        b = pilaEvaluacion.pop();
                        pilaEvaluacion.push(b + a);
                        
                        tope--;
                        break;
                    case '-':
                        pw.println("SUB R" + (tope - 2) + ",R" + (tope - 1) + "        ; Restamos");
                        a = pilaEvaluacion.pop();
                        b = pilaEvaluacion.pop();
                        pilaEvaluacion.push(b - a);
                        tope--;
                        break;
                        
                    case '*':
                        pw.println("MUL R" + (tope - 2) + ",R" + (tope - 1) + "     ;  Multiplicamos");
                        
                        a = pilaEvaluacion.pop();
                        b = pilaEvaluacion.pop();
                        pilaEvaluacion.push(b * a);
                        
                        tope--;
                        break;    
                    case '/':
                        pw.println("DIV R" + (tope - 2) + ",R" + (tope - 1) + "     ; Dividimos");
                        a = pilaEvaluacion.pop();
                        b = pilaEvaluacion.pop();
                        pilaEvaluacion.push(b / a);
                        
                        tope--;
                        break;
                }
            }
        }
        pw.println("MOV " + pila.get(pila.size() - 1) + ",R0    ; fin de la sentencia");
        pw.println("; Rutina para mostrar el resultado de " + pila.get(pila.size() - 1));
        
        setResultado(pilaEvaluacion.pop());
        pw.println(";        R = " + getResultado());
        
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
        
        tope = 0;
        
    }
    
    public double getResultado() {
        return resultado;
    }
    
    public void setResultado(double resultado) {
        this.resultado = resultado;
    }
    
    public void generarCodigoP() {
        
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        try {
            
            fichero = new FileWriter("CodigoPSentencia.txt");
            pw = new PrintWriter(fichero);
            
            pw.print(";Código P para las sentencias enteras sencilla para el programa " + programName + "\n");
            
            for(int i = 0; i < pila.size() - 1; i++) {
            
            if(isOperator(pila.get(i)) == false) {
                pw.print("ldc " + pila.get(i) + "\n");
                tope++;
            } else {
                switch(pila.get(i).charAt(0)) {
                    case '+':
                        pw.print("adi " + "\n");
                        tope--;
                        break;
                    case '-':
                        pw.print("sbi" + "\n");
                        tope--;
                        break;
                    case '*':
                        pw.print("mpi " +  "\n");
                        tope--;
                        break;    
                    case '/':
                        pw.print("dvi" + "\n");
                        tope--;
                        break;
                }
            }
        }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }

    }
    
}
