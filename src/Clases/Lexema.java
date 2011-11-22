// Leo Gutiérrez Ramírez.
// Manejarlo on the fly para saber si es reservado.
// Manejar un TipoToken aquí.
// Implementar una función para devolver el siguienteToken.

package Clases;

// Los valores de Tipo en Parser tienen el atributo static.

public class Lexema
{
    private String valor;
    private int tipo;
    private Parser.TokenType tipoTokenLexema;
    private int numeroLinea = 0;
    
    public Lexema(String valor, Parser.TokenType etiqueta)
    {
        this.valor = valor;
        this.tipoTokenLexema = etiqueta;
    }
    
    // PENDING Checar esto.
    public Lexema(String valor, int etiqueta, Parser.TokenType tipoTokenLexema) {
        this.valor = valor;
        this.tipo = etiqueta;
        this.tipoTokenLexema = tipoTokenLexema;
    }
    // Getters.
    
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

    // Setters
    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    
    
}