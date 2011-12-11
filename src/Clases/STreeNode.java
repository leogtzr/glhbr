package Clases;
import static Clases.Parser.*;
/* @author Leonardo Gutiérrez Ramírez <leogutierrezramirez.gmail.com> */
/* Dec 11, 2011 */
public class STreeNode {
    
    public NodeKind kind;
    public Optype op;          /* Usado con opkind */
    public STreeNode lchild;
    public STreeNode rchild;
    public int val;
    public String strval;
    public String codestr = "";
    
    public STreeNode() {
        codestr = "";
    }
    
    public void genCode(STreeNode t) {
        if(t != null) {
            switch(t.kind) {
                case OpKind:
                    switch(t.op) {
                        case Plus:
                            genCode(t.lchild);
                            genCode(t.rchild);
                            System.out.println("adi");
                            break;
                        case Assign:
                            codestr = "lda " + t.strval;
                            System.out.println(codestr);
                            genCode(t.lchild);
                            System.out.println("stn");
                            break;
                        default:
                            System.out.println("Error");
                            break;
                            
                    }
                    break;
                case ConstKind:
                    codestr = "ldc " + t.strval;
                    System.out.println(codestr);
                    break;
                case IdKind:
                    codestr = "lod " + t.strval;
                    System.out.println(codestr);
                    break;
                default:
                    System.out.println("Error");
                    break;
            }
        }
    }
    
}
