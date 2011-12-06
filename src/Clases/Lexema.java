package Clases;

public class Lexema
{
    private String valor;
    private Parser.TokenType tipoTokenLexema;
    private int numeroLinea = 0;
    
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
    
    public Parser.TokenType getTipoToken() {
        return this.tipoTokenLexema;
    }
    
}