import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class PrintAction implements Printable {    //clase publica que implementa la interface Printable

    private JTextArea jTextArea;    //área de edición donde se encuentra el documento actual
    private JDialog dialog;               //dialogo de estado, muestra el estado de la impresión
    private int[] pageBreaks;             //arreglo de quiebres de página
    private String[] textLines;           //arreglo de líneas de texto
    private int currentPage = -1;         //página actual impresa, por defecto inicilizada en -1
    private boolean result = false;       //resultado de la impresión, por defecto es negativo

    public PrintAction(JComponent jComponent) 
    { 
        //constructor de la clase PrintAction
        this.jTextArea = (JTextArea) jComponent;    //guarda la instancia del área de edición
    }

    //método estático conveniente para inicializar la clase PrintAction
    public static boolean print(JComponent jComponent, Frame owner) {
         PrintAction pa = new PrintAction(jComponent);   //construye una instancia de PrintAction
        return pa.printDialog(owner);   
    }

    //presenta el dialogo de impresión e inicia la impresión del documento
public boolean printDialog(Frame owner) {
    //construye un trabajo de impresión
    final PrinterJob pj = PrinterJob.getPrinterJob();
    //construye un conjunto de atributos para la impresion
    final PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
    //establece a la clase PrintAction como responsable de renderizar las páginas del documento
    pj.setPrintable(this);

    boolean option = pj.printDialog(pras);    //presenta un dialogo de impresión

    if (option == true) {    //si el usuario acepta
        //construye el dialogo modal de estado sobre la ventana padre
        dialog = new PrintingMessageBox(owner, pj);

        //crea un nuevo hilo para que se ocupe de la impresión, se utiliza una clase anónima
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    pj.print();                        //inicia la impresión
                    PrintAction.this.result = true;    //resultado positivo
                } catch (PrinterException ex) {        //en caso de que ocurra una excepción
                    System.err.println(ex);
                }

                dialog.setVisible(false);    //oculta el dialogo de estado
            }
        }).start();    //inicia el hilo de impresión

        dialog.setVisible(true);     //hace visible el dialogo de estado
    }

    return PrintAction.this.result;    //retorna el resultado de la impresión
}



    
public int print(Graphics g, PageFormat pf, int pageIndex) {
    Graphics2D g2d = (Graphics2D) g;                      //conversión de gráficos simples a gráficos 2D
    g2d.setFont(new Font("Serif", Font.PLAIN, 10));       //establece un fuente para todo el texto
    int lineHeight = g2d.getFontMetrics().getHeight();    //obtiene la altura del fuente

    if (pageBreaks == null) {    //si los quiebres de página no fueron calculados
        //construye un arreglo con las líneas de texto presentes en el área de edición
        textLines = jTextArea.getText().split("\n");
        //calcula el número de líneas que caben en cada página
        int linesPerPage = (int) (pf.getImageableHeight() / lineHeight);
        //calcula el número de quiebres de página necesarios para imprimir todo el documento
        int numBreaks = (textLines.length - 1) / linesPerPage;
        //construye un arreglo con los quiebres de página
        pageBreaks = new int[numBreaks];
        for (int i = 0 ; i < numBreaks ; i++) {
            //se calcula la posición para cada quiebre de página
            pageBreaks[i] = (i + 1) * linesPerPage;
        }
    }

    //si el índice de página solicitado es menor o igual que la cantidad de quiebres total
    if (pageIndex <= pageBreaks.length) {
        /* establece una igualdad entre el origen del espacio grafico (x:0,y:0) y el origen
        del área imprimible definido por el formato de página */
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        int y = 0;    //coordenada "y", inicializada en 0 (principio de página)
        //obtiene la primera línea para la página actual
        int startLine = (pageIndex == 0) ? 0 : pageBreaks[pageIndex - 1];
        //obtiene la última línea para la página actual
        int endLine = (pageIndex == pageBreaks.length) ? textLines.length : pageBreaks[pageIndex];

        //itera sobre las líneas que forman parte de la página actual
        for (int line = startLine ; line < endLine ; line++) {
            y += lineHeight;                          //aumenta la coordenada "y" para cada línea
            g2d.drawString(textLines[line], 0, y);    //imprime la linea en las coordenadas actuales
        }

        updateStatus(pageIndex);    //actualiza el estado de impresión

        return PAGE_EXISTS;     //la página solicitada será impresa
    } else {
        return NO_SUCH_PAGE;    //la pagina solicitada no es valida
    }
}


   
private void updateStatus(int pageIndex) {
    if (pageIndex != currentPage) {
        currentPage++;    //incrementa la página actual    

        //acceso seguro al EDT (Event Dispatch Thread) para actualizar la GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                //actualiza la información de la etiqueta lbStatusMsg
                ((PrintingMessageBox) dialog).setStatusMsg("Imprimiendo página " + (currentPage + 1) + " ...");
            }
        });
    }
}



    //clase que extiende JDialog, construye un dialogo modal
    private class PrintingMessageBox extends JDialog {

        private JLabel lbStatusMsg;    //etiqueta que muestra el estado de impresión

        public PrintingMessageBox(Frame owner, final PrinterJob pj) {    //constructor de la clase PrintingMessageBox
        }

        public void setStatusMsg(String statusMsg){    //establece el texto de la etiqueta lbStatusMsg
        }
    }
}