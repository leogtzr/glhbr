// Leo Gutiérrez Ramírez.
package Clases;

public class Lexema
{
    private String valor;
    private int tipo;
    private Parser.TokenType tipoTokenLexema;
    private int numeroLinea = 0;
    
    // Pendiente, se usa este constructor?
    public Lexema(String valor, Parser.TokenType etiqueta)
    {
        this.valor = valor;
        this.tipoTokenLexema = etiqueta;
    }
     
    public Lexema setLineNo(int numeroLinea) {
        this.numeroLinea = numeroLinea;
        return this;
    }
    
    public int getLineNo() {
        return numeroLinea;
    }
    
    public String getValor() {
        return this.valor;
    }
    
    public int getTipo() {
        return this.tipo;
    }
    
    public Parser.TokenType getTipoToken() {
        return this.tipoTokenLexema;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
}