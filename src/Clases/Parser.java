/* @author Leonardo Gutiérrez Ramírez <leogutierrezramirez.gmail.com> */
/* Nov 20, 2011 */
package Clases;

import java.util.ArrayList;

public class Parser {
    
    public static enum TokenType {
       ERROR, 
       IF, 
       THEN, 
       ELSE, 
       END, 
       REPETIR, 
       HASTA, 
       LEER, 
       WRITE, 
       ID, 
       NUM, 
       ASSIGN, 
       EQ, 
       LT, 
       PLUS,
       MINUS, 
       TIMES, 
       OVER, 
       LPARENT, 
       RPARENT, 
       SEMI, 
       POW, 
       COMA, 
       MOD, 
       LEQ, 
       GEQ, 
       GT, 
       NEQ, 
       NOT, 
       COMP, 
       AND, 
       OR,
       WHILE, 
       INFINITO, 
       INIBLOQUE, 
       FINBLOQUE, 
       INICIO, 
       FINALIZAR,
       INC,
       DEC,
       SENO,
       COSENO,
       ABS, 
       TAN,
       LN,   /* Logaritmo natural. Fin de las funciones trigonométricas */
       CLEAR,
       SALIR,
       FACTORIAL
    };
   
    public static enum NodeKind {
        StmtK, ExpK
     };
    
    public static enum StmtKind {
        IfK, 
        RepeatK,
        AssignK,
        ReadK,
        WriteK, 
        WhileK, 
        InfinitumK, 
        PowK, /* Elevar una variable a un valor */
        IncK,    /* Incrementar una variable */
        DecK,    /* Decrementar el valor de una variable a determinado valor */
        SenoK,
        CosenoK,
        AbsK,
        TanK,
        LnK,
        ClearK,
        SalirK,
        FactorialK
    };
    
    public static enum ExpKind {
        OpK, ConstK, IdK
    };
    
    // Enumeración para verificación de Tipo.
    public static enum ExpType {
        Void, Integer, Boolean, Binario
    };
    
    public static void indentar() {
        // indentno declarada más abajo.
        indentno += 2;
    }
    
    public static void unindent() {
        indentno -= 2;
    }
    
    public static void imprimirEspacios() {
        for(int i = 0; i < indentno; i++)
            System.out.print(" ");
    }
    
    public static void imprimirArbol(NodoArbol tree) {
        
        indentar();
        while(tree != null) {
            imprimirEspacios();
            if(tree.nodeKind == NodeKind.StmtK) {
                switch(tree.stmt) {
                    case IfK:
                        System.out.println("If");
                        break;
                    
                    case RepeatK:
                        System.out.println("Repetir");
                        break;
                    case AssignK:
                        System.out.println("Asignado a: " + tree.nombre);
                        break;
                    case ReadK:
                        System.out.println("Read: " + tree.nombre);
                        break;
                    case WriteK:
                        System.out.println("Write");
                        break;
                    case WhileK:
                        System.out.println("While");
                        break;
                    case InfinitumK:
                        System.out.println("Infinito");
                        break;
                    case PowK:
                        System.out.println("Pow");
                        break;
                    case IncK:
                        System.out.println("Inc");
                        break;             
                    case DecK:
                        System.out.println("Dec");
                        break;
                    case SenoK:
                        System.out.println("Seno");
                        break;
                    case CosenoK:
                        System.out.println("Coseno");
                        break;
                    case AbsK:
                        System.out.println("Abs");
                        break;
                    case TanK:
                        System.out.println("Tan");
                        break;
                    case LnK:
                        System.out.println("Ln");
                        break;
                    case ClearK:
                        System.out.println("Clear");
                        break;
                    case SalirK:
                        System.out.println("Salir");
                        break;
                    case FactorialK:
                        System.out.println("Factorial");
                        break;
           
                    default:
                        System.out.println("Nodo desconocido");
                }
            } else if(tree.nodeKind == NodeKind.ExpK) {
                
                switch(tree.exp) {
                    case OpK:
                        System.out.print("op: ");
                        printToken(tree.op, "\\0");
                        break;
                    case ConstK:
                        System.out.println("const: " + tree.valor);
                        break;
                    case IdK:
                        System.out.println("id: " + tree.nombre);
                        break;
                    default:
                        System.out.println("Nodo desconocido");
                        break;
                }
                
            } else {
                System.out.println("Nodo desconocido");
            }
            
            // Llamar recursivamente a la función.
            for(int j = 0; j < MAXCHILDREN; j++)
                imprimirArbol(tree.hijos[j]);
            
            tree = tree.hermano;
            
        }
        
        unindent();
        
    }
    
    private ArrayList<Lexema> palabras = null;   
    public static final int MAXCHILDREN = 3;
    public static int indentno = 0;
    private int indice = 0;
    
    public static TokenType token;              // El token actual.
    public static String tokenString;
    public static int lineno = 0;
   
   public Parser() {
       palabras = null;
       indice = 0;
   }
    
   public Parser(ArrayList<Lexema> palabras) {
       this.palabras = palabras;
       indice = 0;
   }
   
   public ArrayList<Lexema> getListaLexemas() {
       return palabras;
   }
   
   public static void printToken(TokenType token, String tokenString) {
       switch(token) {
           case IF:
           case THEN:
           case ELSE:
           case END:
           case REPETIR:
           case HASTA:
           case LEER:
           case WRITE:
           case WHILE:
           case INFINITO:
           case POW:
           case INC:
           case DEC:
           case SENO:
           case COSENO:
           case ABS:
           case TAN:
           case LN:
           case CLEAR:
           case SALIR:
           case FACTORIAL:
               // Escribir en un archivo.
               System.out.println("palabra reservada: " + tokenString);
               break;
           case ASSIGN:
               System.out.println(":=");
               break;
           case LT:
               System.out.println("<");
               break;
           case GT:
               System.out.println(">");
               break;
           case EQ:
               System.out.println("==");
               break;
           case LPARENT:
               System.out.println("(");
               break;
           case RPARENT:
               System.out.println(")");
               break;
           case SEMI:
               System.out.println(";");
               break;
           case PLUS:
               System.out.println("+");
               break;
           case MINUS:
               System.out.println("-");
               break;
           case TIMES:
               System.out.println("*");
               break;
           case OVER:
               System.out.println("/");
               break;
           case NUM:
               System.out.println("Num, valor=" + tokenString);
               break;
           case ID:
               System.out.println("Nombre=" + tokenString);
               break;
           case ERROR:
               System.out.println("Error: " + tokenString);
               break;
           default:
               System.out.println("Desconocido = " + token);
               break;
       }
   } 
   
   public NodoArbol newStmtNode(StmtKind kind) {
       NodoArbol t = new NodoArbol();
       if(t == null) {
           System.out.println("Error, no hay memoria");
           // Código para salir del método o el programa
       } else {
           for(int i = 0; i < MAXCHILDREN; i++)
               t.hijos[i] = null;
           t.hermano = null;
           
           t.nodeKind = NodeKind.StmtK;
           t.stmt = kind;
           t.lineno = lineno;
       }
       
       return t;
   }
   
   public NodoArbol newExpNode(ExpKind kind) {
       NodoArbol t = new NodoArbol();
       if(t == null) {
           System.out.println("Error, no hay memoria");
           // Código para salir del método o el programa
       } else {
           for(int i = 0; i < MAXCHILDREN; i++)
               t.hijos[i] = null;
           
           t.hermano = null;
           t.nodeKind = NodeKind.ExpK;
           t.exp = kind;
           t.lineno = lineno;
           t.type = ExpType.Void;
           
       }
       return t;
   }
   
   public static void syntaxError(String mensaje) {
       System.out.printf("Error de sintaxis en la línea: %d: %s", lineno, mensaje);
       // Código para salir.
   }
   
   public void coincidir(TokenType expected) {
       if(token == expected) {
           
           token = getToken();
           
       } else {
           
           syntaxError("Nodo no esperado --> ");
           printToken(expected, tokenString);
           System.out.print("      ");
           
       }
   }
   
   NodoArbol stmt_sequence() {
       
       NodoArbol t = statement();
       NodoArbol p = t;
       
       while(/*(token != TokenType.ENDFILE) && */(token != TokenType.FINALIZAR) && (token != TokenType.FINBLOQUE) && (token != TokenType.END) && (token != TokenType.ELSE) && (token != TokenType.HASTA) && (indice < this.palabras.size())) {
           
           NodoArbol q;
           coincidir(TokenType.SEMI);
           q = statement();
           if(q != null) {
               if(t == null)
                   t = p = q;
               else {       /* Ahora p ya no puede ser NULL */
                   p.hermano = q;
                   p = q;
               }
                   
           }
       }
       
       return t;
       
   }
   
   public NodoArbol statement() {
       NodoArbol t = null;
       switch(token) {
           case IF:
               
               t = if_stmt();
               break;
               
           case REPETIR:
               
               t = repeat_stmt();
               break;
               
           case ID:
               
               t = assign_stmt();
               break;
               
           case LEER:
               
               t = read_stmt();
               break;
               
           case WRITE:
               
               t = write_stmt();
               break;
           case POW:
               t = pow_stmt();
               break;
           case INC:
               t = inc_stmt();
               break;
           case DEC:
               t = dec_stmt();
               break;
           case SENO:
               t = seno_stmt();
               break;
           case COSENO:
               t = coseno_stmt();
               break;
           case ABS:
               t = abs_stmt();
               break;
               
           case TAN:
               t = tan_stmt();
               break;
           case LN:
               t = ln_stmt();
               break;
           case CLEAR:
               clear_stmt();
               break;
               
           case SALIR:
               t = salir_stmt();
               break;
           case FACTORIAL:
               t = factorial_stmt();
               break;
               
           case WHILE:
               // TODO Cambiar por while_stmt();
               t = while_stmt();
               break;
           case INFINITO:
               t = infinito_stmt();
               break;
               
           default:
               syntaxError("Token inesperado --> ");
               printToken(token, tokenString);
               token = getToken();
               break;
       }
       return t;
   }
   
   NodoArbol if_stmt() {
       NodoArbol t = newStmtNode(StmtKind.IfK);
       coincidir(TokenType.IF);
       if(t != null)
           t.hijos[0] = expresion();
       coincidir(TokenType.THEN);
       if(t != null)
           t.hijos[1] = stmt_sequence();
       if(token == TokenType.ELSE) {
           coincidir(TokenType.ELSE);
           if(t != null)
               t.hijos[2] = stmt_sequence();
       }
       
       // Cuidado Ver cómo va a terminar el IF
       coincidir(TokenType.END);
       return t;
       
   }
   
   public NodoArbol repeat_stmt() {
       NodoArbol t = newStmtNode(StmtKind.RepeatK);
       coincidir(TokenType.REPETIR);
       if(t != null)
           t.hijos[0] = stmt_sequence();
       coincidir(TokenType.HASTA);
       if(t != null)
           t.hijos[1] = expresion();
       
       return t;
       
   }
   
   public NodoArbol while_stmt() {
       NodoArbol t = newStmtNode(StmtKind.WhileK);
       coincidir(TokenType.WHILE);
       
       if(t != null)
           t.hijos[0] = expresion();
       coincidir(TokenType.THEN);
       if(t != null)
           t.hijos[1] = stmt_sequence();
       coincidir(token);
       return t;
   }
   
   public NodoArbol infinito_stmt() {
       
       NodoArbol t = newStmtNode(StmtKind.InfinitumK);
       coincidir(TokenType.INFINITO);
       coincidir(TokenType.THEN);
       if(t != null)
           t.hijos[0] = stmt_sequence();
       coincidir(TokenType.END);
       
       return t;
       
   }
   
   public NodoArbol pow_stmt() {
       NodoArbol t = newStmtNode(StmtKind.PowK);
       coincidir(TokenType.POW);
       coincidir(TokenType.LPARENT);
       //coincidir(TokenType.COMA);  /* Por lo pronto sin la coma: pow(x 1 * 2 + 34); */
       coincidir(TokenType.ID);
       coincidir(TokenType.COMA);
       if(t != null)
           t.hijos[0] = expresion();
       coincidir(TokenType.RPARENT);
       
       return t;
   }
   
   public NodoArbol inc_stmt() {
       NodoArbol t = newStmtNode(StmtKind.IncK);
       coincidir(TokenType.INC);
       coincidir(TokenType.LPARENT);
       //coincidir(TokenType.COMA);  /* Por lo pronto sin la coma: pow(x 1 * 2 + 34); */
       coincidir(TokenType.ID);
       coincidir(TokenType.COMA);
       if(t != null)
           t.hijos[0] = expresion();
       coincidir(TokenType.RPARENT);
       
       return t;
   }
   
   public NodoArbol dec_stmt() {
       NodoArbol t = newStmtNode(StmtKind.IncK);
       coincidir(TokenType.DEC);
       coincidir(TokenType.LPARENT);
       //coincidir(TokenType.COMA);  /* Por lo pronto sin la coma: pow(x 1 * 2 + 34); */
       coincidir(TokenType.ID);
       coincidir(TokenType.COMA);
       if(t != null)
           t.hijos[0] = expresion();
       coincidir(TokenType.RPARENT);
       
       return t;
   }
   
   public NodoArbol seno_stmt() {
       NodoArbol t = newStmtNode(StmtKind.SenoK);
       coincidir(TokenType.SENO);
       coincidir(TokenType.LPARENT);
       if(t != null)
           t.hijos[0] = expresion();
       coincidir(TokenType.RPARENT);
       
       return t;
   }
   public NodoArbol coseno_stmt() {
       NodoArbol t = newStmtNode(StmtKind.CosenoK);
       coincidir(TokenType.COSENO);
       coincidir(TokenType.LPARENT);
       if(t != null)
           t.hijos[0] = expresion();
       coincidir(TokenType.RPARENT);
       
       return t;
   }
   public NodoArbol abs_stmt() {
       NodoArbol t = newStmtNode(StmtKind.AbsK);
       coincidir(TokenType.ABS);
       coincidir(TokenType.LPARENT);
       if(t != null)
           t.hijos[0] = expresion();
       coincidir(TokenType.RPARENT);
       
       return t;
   }
   public NodoArbol tan_stmt() {
       NodoArbol t = newStmtNode(StmtKind.TanK);
       coincidir(TokenType.TAN);
       coincidir(TokenType.LPARENT);
       if(t != null)
           t.hijos[0] = expresion();
       coincidir(TokenType.RPARENT);
       
       return t;
   }
   
   public NodoArbol ln_stmt() {
       NodoArbol t = newStmtNode(StmtKind.LnK);
       coincidir(TokenType.LN);
       coincidir(TokenType.LPARENT);
       if(t != null)
           t.hijos[0] = expresion();
       coincidir(TokenType.RPARENT);
       
       return t;
   }
   
   public void clear_stmt() {
       coincidir(TokenType.CLEAR);
   }
   
   public NodoArbol salir_stmt() {
       NodoArbol t = newStmtNode(StmtKind.SalirK);
       coincidir(TokenType.SALIR);
       coincidir(TokenType.LPARENT);
       if(t != null)
           t.hijos[0] = expresion();
       coincidir(TokenType.RPARENT);
       
       return t;
   }
   
   public NodoArbol factorial_stmt() {
       NodoArbol t = newStmtNode(StmtKind.FactorialK);
       coincidir(TokenType.FACTORIAL);
       coincidir(TokenType.LPARENT);
       if(t != null)
           t.hijos[0] = expresion();
       coincidir(TokenType.RPARENT);
       
       return t;
   }
   
   public NodoArbol assign_stmt() {
       
       NodoArbol t = newStmtNode(StmtKind.AssignK);
       if((t != null) && (token == TokenType.ID))
           // Cuidado con esto!
           t.nombre = tokenString;
       coincidir(TokenType.ID);
       coincidir(TokenType.ASSIGN);
       if(t != null)
           t.hijos[0] = expresion();
           
       return t;
   }
   
   public NodoArbol read_stmt() {
       NodoArbol t = newStmtNode(StmtKind.ReadK);
       coincidir(TokenType.LEER);
       coincidir(TokenType.LPARENT);
       if((t != null) && (token == TokenType.ID))
           t.nombre = tokenString;
       coincidir(TokenType.ID);
       coincidir(TokenType.RPARENT);
       return t;
   }
   
   public NodoArbol write_stmt() {
       NodoArbol t = newStmtNode(StmtKind.WriteK);
       coincidir(TokenType.WRITE);
       coincidir(TokenType.LPARENT);
       if(t != null)
           t.hijos[0] = expresion();
       coincidir(TokenType.RPARENT);
       return t;
   }
   
   public NodoArbol expresion() {
       NodoArbol t = simple_expresion();
       if((token == TokenType.GT) || (token == TokenType.LT) || (token == TokenType.EQ)) {
           NodoArbol p = newExpNode(ExpKind.OpK);
           
           if(p != null) {
               p.hijos[0] = t;
               p.op = token;
               t = p;
           }
           
           coincidir(token);
           if(t != null)
               t.hijos[1] = simple_expresion();
           
       }
       return t;
   }
   
   public NodoArbol simple_expresion() {
       NodoArbol t = termino();
       while((token == TokenType.PLUS) || (token == TokenType.MINUS)) {
           NodoArbol p = newExpNode(ExpKind.OpK);
           if(p != null) {
               p.hijos[0] = t;
               p.op = token;
               t = p;
               coincidir(token);
               t.hijos[1] = termino();
           }
       }
       return t;
   }
   
   public NodoArbol termino() {
       NodoArbol t = factor();
       while((token == TokenType.TIMES) || (token == TokenType.OVER)) {
           NodoArbol p = newExpNode(ExpKind.OpK);
           if(p != null) {
               p.hijos[0] = t;
               p.op = token;
               t = p;
               coincidir(token);
               p.hijos[1] = factor();
           }
       }
       return t;
   }
   
   public NodoArbol factor() {
       NodoArbol t = null;
       switch(token) {
           case NUM:
               t = newExpNode(ExpKind.ConstK);
               if((t != null) && (token == TokenType.NUM))
                   t.valor = Integer.parseInt(tokenString);
               coincidir(TokenType.NUM);
               break;
           case ID:
               t = newExpNode(ExpKind.IdK);
               if((t != null) && (token == TokenType.ID))
                   t.nombre = tokenString;
               coincidir(TokenType.ID);
               break;
           case LPARENT:
               coincidir(TokenType.LPARENT);
               t = expresion();
               coincidir(TokenType.RPARENT);
               break;
           default:
               syntaxError("Token inesperado ---> ");
               printToken(token, tokenString);
               token = getToken();
               break;
       }
       return t;
   }
   
   public TokenType getToken() {
       
       tokenString = palabras.get(indice).getValor();
       token = palabras.get(indice).getTipoToken();
       lineno = palabras.get(indice).getLineNo();
       
       return palabras.get(indice++).getTipoToken();
       
   }
   
   public NodoArbol parse() {
       
       NodoArbol t = null;
       
       token = getToken();
       coincidir(TokenType.INICIO);
       System.out.println("Programa: " + tokenString);
       coincidir(TokenType.ID);
       
       t = stmt_sequence();
       //indice--;
       //coincidir(TokenType.SEMI);
       coincidir(TokenType.FINALIZAR);
       //System.out.println("Esto mero : " + "[" + token + "] = " + indice + "[->" + palabras.get(indice - 1).getValor() + "\n");
       
       return t;
   }
   // Solo para ir viendo el comportamiento..., auxiliar para comprobación.
   public void recorrer() {
       for(int i = 0; i < this.palabras.size(); i++)
           System.out.println(this.palabras.get(i).getValor() + ": " + this.palabras.get(i).getLineNo() + " - " + 
                   this.palabras.get(i).getTipoToken()
                   );
   }   
}