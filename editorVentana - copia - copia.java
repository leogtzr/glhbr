// TODO Añadir funciones para obtener un archivo de texto
// TODO función para guardar
// TODO función para abrir y volcar al área de texto.
// TODO Implementar el diálogo cambiar Fuente... (done)
// TODO Explorar propiedades del dialogo de Guardar, para ver si se pueden cambiar titulos.
// TODO Guardar como...
// TODO Cambios en la "barra de estado"
// TODO checar que en la barra de título los títulos coincidan de acuerdo al archivo abierto.
// ITCH II.
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.text.BadLocationException;
import javax.swing.GroupLayout.*;
import javax.swing.JColorChooser;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class editorVentana extends javax.swing.JFrame {
    
    public editorVentana() {
        //EstadoLinea line = new EstadoLinea();
        initComponents();
    }
    
    // Variables para resaltar texto...
    Color colorfondodefault;
    Highlighter hilit;
    Highlighter.HighlightPainter painter;
    String []cadenas = {
        "begin",
        "inicio",
        "write",
        "decimal",
        "integer",
        "final"
    };

    public void resaltar(String s)
    {
        String cadena = "<font color=\"red\">" + s + "<font color=\"black\">";
        String remp = areaTexto.getText().replaceAll("" + s, cadena);
        areaTexto.setText(remp);
        
    }
    
    // public void buscarTexto(string txtABuscar)
     public void buscarTexto(String s) {
    
        hilit = new DefaultHighlighter();
        painter = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);
        areaTexto.setHighlighter(hilit);
    
        // Esta línea remueve todos los resaltados anteriores....
        //hilit.removeAllHighlights();
        
        if (s.length() > 0) {
            // texto = areaTexto
         String contenido = areaTexto.getText();
            int index = contenido.indexOf(s, 0);
            if (index >= 0) {
                try {
                    int end = index + s.length();
                    hilit.addHighlight(index, end, painter);
                    areaTexto.setCaretPosition(end);
                    //txtbuscar.setBackground(colorfondodefault);
              
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            } 
        }
    }
    
    // Contiene la posición del cursor.
    public int cursorPosicion = 0;
    public int lineaPosicion = 0;
    public int y = 1;
    //public int x = 1;
    //public String estadoLinea = "Línea :";
    // Nombre del archivo abierto
    public String archivoAbierto = null;
    public String directorioArchivo = null;
    public boolean haCambiado = false;
    public static File f;
    
    // Función para guardar en un archivo, devuelve true si se hizo el guardado,
    // false si no se hizo el guardado...
    public boolean guardarEnArchivo()
    {
        // Lo que vamos a devolver...
        boolean devuelto = false;
        int estado = selectorDeArchivos.showSaveDialog(this);
        // Si el usuario eligió que sí...
        // Guardar la información contenida en el area de texto al archivo elegido.
        if(estado == JFileChooser.APPROVE_OPTION)
        {
            // Obtenemos las propiedades del archivo...
            f = selectorDeArchivos.getSelectedFile();
            archivoAbierto = f.getName();
            directorioArchivo = f.getParent();
            BufferedWriter bw;
            
            try {
            
                bw = new BufferedWriter(new FileWriter(f));
                f = selectorDeArchivos.getSelectedFile();
                areaTexto.write(bw);
                bw.close();
                
            } catch (IOException ex) {
                Logger.getLogger(editorVentana.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            devuelto = true;
            
        } else 
            devuelto = false;
        
        return devuelto;
    }
    
    public void guardarSinAvisar()
    {
            f = selectorDeArchivos.getSelectedFile();
            archivoAbierto = f.getName();
            directorioArchivo = f.getParent();
            BufferedWriter bw;
            
            try {
            
                bw = new BufferedWriter(new FileWriter(f));
                f = selectorDeArchivos.getSelectedFile();
                
                areaTexto.write(bw);
                bw.close();
                
            } catch (IOException ex) {
                Logger.getLogger(editorVentana.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selectorDeArchivos = new javax.swing.JFileChooser();
        aboutDialog = new javax.swing.JDialog();
        popup = new javax.swing.JPopupMenu();
        seleccionarTodo = new javax.swing.JMenuItem();
        colorArea = new javax.swing.JColorChooser();
        dialogoColor = new javax.swing.JDialog();
        estadoDeLinea = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollareaTexto1 = new javax.swing.JScrollareaTexto();
        areaTexto = new javax.swing.JTextArea();
        jScrollareaTexto2 = new javax.swing.JScrollareaTexto();
        jTextArea1 = new javax.swing.JTextArea();
        buscarTextoTxt = new javax.swing.JTextField();
        resaltarBtn = new javax.swing.JButton();
        jScrollareaTexto3 = new javax.swing.JScrollareaTexto();
        areaTexto = new javax.swing.JTextareaTexto();
        resaltarareaTextoBtn = new javax.swing.JButton();
        menu = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        nuevoMenuItem = new javax.swing.JMenuItem();
        abrirMenuItem = new javax.swing.JMenuItem();
        guardarMenuItem = new javax.swing.JMenuItem();
        guardarComoMenuItem = new javax.swing.JMenuItem();
        cerrarArchivoMenuItem = new javax.swing.JMenuItem();
        imprimirMenuItem = new javax.swing.JMenuItem();
        salirMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        selectAllMenuItem = new javax.swing.JMenuItem();
        copiarMenuItem = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuFormato = new javax.swing.JMenu();
        cambiarTipoLetra = new javax.swing.JMenuItem();
        cambiarColorMenuItem = new javax.swing.JMenuItem();
        colorFuenteMenuItem = new javax.swing.JMenuItem();
        ayudaMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        selectorDeArchivos.setToolTipText("Elija donde guardar su archivo");
        selectorDeArchivos.setName(""); // NOI18N

        aboutDialog.setTitle("Acerca de New Language v1.0");

        javax.swing.GroupLayout aboutDialogLayout = new javax.swing.GroupLayout(aboutDialog.getContentareaTexto());
        aboutDialog.getContentareaTexto().setLayout(aboutDialogLayout);
        aboutDialogLayout.setHorizontalGroup(
            aboutDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );
        aboutDialogLayout.setVerticalGroup(
            aboutDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 326, Short.MAX_VALUE)
        );

        seleccionarTodo.setText("Seleccionar todo");
        seleccionarTodo.setName("Lala"); // NOI18N
        seleccionarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarTodoActionPerformed(evt);
            }
        });
        popup.add(seleccionarTodo);

        colorArea.setToolTipText("Seleccione el color del área de texto");
        colorArea.getAccessibleContext().setAccessibleParent(this);

        dialogoColor.setTitle("Elija el color");

        javax.swing.GroupLayout dialogoColorLayout = new javax.swing.GroupLayout(dialogoColor.getContentareaTexto());
        dialogoColor.getContentareaTexto().setLayout(dialogoColorLayout);
        dialogoColorLayout.setHorizontalGroup(
            dialogoColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        dialogoColorLayout.setVerticalGroup(
            dialogoColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("New Language v1.0");
        setName("ventanaPrincipal"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                alCerrar(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                alCerrar(evt);
            }
        });

        estadoDeLinea.setText("Abra o cree un nuevo archivo");

        areaTexto.setColumns(20);
        areaTexto.setFont(new java.awt.Font("Courier New", 1, 13));
        areaTexto.setRows(5);
        areaTexto.setToolTipText("Ingrese su texto");
        areaTexto.setWrapStyleWord(true);
        areaTexto.setCaretColor(new java.awt.Color(255, 0, 0));
        areaTexto.setComponentPopupMenu(popup);
        areaTexto.setDragEnabled(true);
        areaTexto.setEnabled(false);
        areaTexto.setInheritsPopupMenu(true);
        areaTexto.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                areaTextoCaretUpdate(evt);
            }
        });
        areaTexto.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                areaTextoCaretPositionChanged(evt);
            }
        });
        areaTexto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                areaTextoKeyPressed(evt);
            }
        });
        jScrollareaTexto1.setViewportView(areaTexto);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollareaTexto2.setViewportView(jTextArea1);

        resaltarBtn.setText("Resaltar");
        resaltarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resaltarBtnActionPerformed(evt);
            }
        });

        areaTexto.setContentType("text/html");
        jScrollareaTexto3.setViewportView(areaTexto);

        resaltarareaTextoBtn.setMnemonic('R');
        resaltarareaTextoBtn.setText("Resaltar areaTexto");
        resaltarareaTextoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resaltarareaTextoBtnActionPerformed(evt);
            }
        });

        menu.setToolTipText("Diferentes acciones a realizar con los archivos");

        menuArchivo.setMnemonic('A');
        menuArchivo.setText("Archivo");

        nuevoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        nuevoMenuItem.setMnemonic('N');
        nuevoMenuItem.setText("Nuevo");
        nuevoMenuItem.setName("nuevoMenuItem"); // NOI18N
        nuevoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoMenuItemActionPerformed(evt);
            }
        });
        menuArchivo.add(nuevoMenuItem);

        abrirMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        abrirMenuItem.setMnemonic('A');
        abrirMenuItem.setText("Abrir");
        abrirMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirMenuItemActionPerformed(evt);
            }
        });
        menuArchivo.add(abrirMenuItem);

        guardarMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        guardarMenuItem.setMnemonic('G');
        guardarMenuItem.setText("Guardar");
        guardarMenuItem.setEnabled(false);
        guardarMenuItem.setName("guardarMenuItem"); // NOI18N
        guardarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarMenuItemActionPerformed(evt);
            }
        });
        menuArchivo.add(guardarMenuItem);

        guardarComoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        guardarComoMenuItem.setText("Guardar como ...");
        guardarComoMenuItem.setEnabled(false);
        guardarComoMenuItem.setName("guardarComoMenuItem"); // NOI18N
        guardarComoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarComoMenuItemActionPerformed(evt);
            }
        });
        menuArchivo.add(guardarComoMenuItem);

        cerrarArchivoMenuItem.setMnemonic('C');
        cerrarArchivoMenuItem.setText("Cerrar archivo");
        cerrarArchivoMenuItem.setEnabled(false);
        cerrarArchivoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarArchivoMenuItemActionPerformed(evt);
            }
        });
        menuArchivo.add(cerrarArchivoMenuItem);

        imprimirMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        imprimirMenuItem.setText("Imprimir");
        imprimirMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirMenuItemActionPerformed(evt);
            }
        });
        menuArchivo.add(imprimirMenuItem);

        salirMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        salirMenuItem.setMnemonic('S');
        salirMenuItem.setText("Salir");
        salirMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirMenuItemActionPerformed(evt);
            }
        });
        menuArchivo.add(salirMenuItem);

        menu.add(menuArchivo);

        jMenu1.setMnemonic('E');
        jMenu1.setText("Edición");

        selectAllMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        selectAllMenuItem.setText("Seleccionar todo");
        selectAllMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(selectAllMenuItem);

        copiarMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        copiarMenuItem.setMnemonic('C');
        copiarMenuItem.setText("Copiar");
        copiarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copiarMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(copiarMenuItem);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Pegar");
        jMenu1.add(jMenuItem1);

        menu.add(jMenu1);

        menuFormato.setMnemonic('F');
        menuFormato.setText("Formato");
        menuFormato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFormatoActionPerformed(evt);
            }
        });

        cambiarTipoLetra.setMnemonic('f');
        cambiarTipoLetra.setText("Cambiar fuente");
        cambiarTipoLetra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarTipoLetraActionPerformed(evt);
            }
        });
        menuFormato.add(cambiarTipoLetra);

        cambiarColorMenuItem.setText("Cambiar color de fondo");
        cambiarColorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarColorMenuItemActionPerformed(evt);
            }
        });
        menuFormato.add(cambiarColorMenuItem);

        colorFuenteMenuItem.setText("Cambiar color de fuente");
        colorFuenteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorFuenteMenuItemActionPerformed(evt);
            }
        });
        menuFormato.add(colorFuenteMenuItem);

        menu.add(menuFormato);

        ayudaMenu.setMnemonic('u');
        ayudaMenu.setText("Ayuda");

        aboutMenuItem.setMnemonic('A');
        aboutMenuItem.setText("Acerca de...");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        ayudaMenu.add(aboutMenuItem);

        menu.add(ayudaMenu);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentareaTexto());
        getContentareaTexto().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(estadoDeLinea, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollareaTexto1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buscarTextoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(resaltarBtn)))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resaltarareaTextoBtn)
                    .addComponent(jScrollareaTexto3, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscarTextoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resaltarBtn)
                    .addComponent(resaltarareaTextoBtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollareaTexto1, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addComponent(jScrollareaTexto3, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(estadoDeLinea))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void nuevoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoMenuItemActionPerformed

     //
     // Si el usuario al estar editando un archivo, quiere crear un archivo Nuevo...
    // El archivo se debe guardar antes....
    
    // Ha dado clic en nuevo...
    // Así que habilitamos los botones guardar, guardarComo e Imprimir.
    
    // Checar si el area de texto está habilitada..., sino, quiere decir que no hay un archivo
    // Abierto o dió
    
    // Ya dió nuevo pero no ha guardado...
    // Así que hay que preguntar si quiere guardar...
    areaTexto.setTabSize(2);
    if(areaTexto.isEnabled() && (archivoAbierto == null) && (directorioArchivo == null))
    {
        // Mandamos guardar..., pero como se supone que ya es uno nuevo
        // Desasignamos las variables archivoAbierto y directorioArchivo
        guardarEnArchivo();
        archivoAbierto = null;
        directorioArchivo = null;
        // Limpiamos la caja de texto, puesto que ya estamos en otro archivo.
        areaTexto.setText("");
        
        return; // Salimos.
        // Ya hay un archivo asignado, primero guardamos lo que ya haya...
    } else if((archivoAbierto != null) && (directorioArchivo != null))
    {
        guardarSinAvisar();
        archivoAbierto = null;
        directorioArchivo = null;
        areaTexto.setText("");
    }
    
    setTitle("New Language v1.0 - Nuevo archivo");
    estadoDeLinea.setText("Línea: ");
    guardarMenuItem.setEnabled(true);
    guardarComoMenuItem.setEnabled(true);
    //imprimirMenuItem.setEnabled(true);
    
    // Habilitar el area de texto:
    areaTexto.setEnabled(true);
    areaTexto.setEditable(true);
    areaTexto.add(popup);
    cerrarArchivoMenuItem.setEnabled(true);
    
    // TODO Estas también pueden servir de banderas...
    
    archivoAbierto = null;
    directorioArchivo = null;
    areaTexto.setText("");
    
    // TODO Averiguar como habilitar el cursor y ponerlo en AreaDeTexto
    
}//GEN-LAST:event_nuevoMenuItemActionPerformed

private void abrirMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirMenuItemActionPerformed
    
    // Estamos abriendo un archivo desde 0
    if((archivoAbierto == null) && (directorioArchivo == null))
    {
        // Quiere decir que ya ha tecleado algo...
        // Por lo tanto hay que llamar al dialogo Guardar...
        if(areaTexto.getText().isEmpty() == false)
        {
            
            guardarEnArchivo();
            
        }
        // else {
        // TODO Llamar a el dialogo de abrir...
         int state = selectorDeArchivos.showOpenDialog(this);

         if(state == JFileChooser.APPROVE_OPTION) {    //si elige abrir el archivo
            File f = selectorDeArchivos.getSelectedFile();    //obtiene el archivo seleccionado

         try {
            //abre un flujo de datos desde el archivo seleccionado
            BufferedReader br = new BufferedReader(new FileReader(f));
            areaTexto.read(br, null);    //lee desde el flujo de datos hacia el area de edición
            br.close();    //cierra el flujo de datos

            //asigna el manejador de eventos para registrar los cambios en el nuevo documento actual
            setTitle("TextPad Demo - " + f.getName());

            archivoAbierto = f.getName();
            directorioArchivo = f.getParent();
            
            } catch (IOException ex) {    //en caso de que ocurra una excepción
            //presenta un dialogo modal con alguna información de la excepción
                JOptionareaTexto.showMessageDialog(this,ex.getMessage(),ex.toString(),JOptionareaTexto.ERROR_MESSAGE);
            }
        
            areaTexto.setEnabled(true);
            guardarMenuItem.setEnabled(true);
            guardarComoMenuItem.setEnabled(true);
            //imprimirMenuItem.setEnabled(true);
        
        }
         
        // Quiere decir que estamos abriendo otro archivo desde uno que ya estemos editando
        // Entonces primero hay que guardar lo que ya pusimos en el area de texto.
        // Y luego presentar el diálogo para que elija el archivo.
    } else {
        
            File f = selectorDeArchivos.getSelectedFile();    //se obtiene el archivo seleccionado
            
            archivoAbierto = f.getName();
            directorioArchivo = f.getParent();
            
            try {
                //abre un flujo de datos hacia el archivo asociado seleccionado
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                //escribe desde el flujo de datos hacia el archivo
                areaTexto.write(bw);
                bw.close();    //cierra el flujo
                
                //nuevo título de la ventana, 
                setTitle(f.getName());
                
                archivoAbierto = null;
                directorioArchivo = null;
                
                
                // TODO Llamar a el dialogo de abrir...
         int state = selectorDeArchivos.showOpenDialog(this);

         if(state == JFileChooser.APPROVE_OPTION) 
         {    //si elige abrir el archivo
            f = selectorDeArchivos.getSelectedFile();    //obtiene el archivo seleccionado

            try {
            //abre un flujo de datos desde el archivo seleccionado
            BufferedReader br = new BufferedReader(new FileReader(f));
            areaTexto.read(br, null);    //lee desde el flujo de datos hacia el area de edición
            br.close();    //cierra el flujo de datos

            //asigna el manejador de eventos para registrar los cambios en el nuevo documento actual
            setTitle("TextPad Demo - " + f.getName());

            archivoAbierto = f.getName();
            directorioArchivo = f.getParent();
            
            } catch (IOException ex) {    //en caso de que ocurra una excepción
            //presenta un dialogo modal con alguna información de la excepción
                JOptionareaTexto.showMessageDialog(this,ex.getMessage(),ex.toString(),JOptionareaTexto.ERROR_MESSAGE);
            }
        
            areaTexto.setEnabled(true);
            guardarMenuItem.setEnabled(true);
            guardarComoMenuItem.setEnabled(true);
        //    imprimirMenuItem.setEnabled(true);
        
        }
                
                //muestra la ubicación del archivo guardado
            } catch(IOException ex) {    //en caso de que ocurra una excepción
                //presenta un dialogo modal con alguna información de la excepción
                JOptionareaTexto.showMessageDialog(this,ex.getMessage(),ex.toString(),JOptionareaTexto.ERROR_MESSAGE);
            }
        }
        
}//GEN-LAST:event_abrirMenuItemActionPerformed

private void guardarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarMenuItemActionPerformed
    //JOptionareaTexto.showMessageDialog(this, "Item guardar ... Menú Archivo");
    // Quiere guardar un documento que no existe, o no ha dado a Nuevo
    if(areaTexto.isEnabled() == false)
        return;
    
    // Aquí quiere decir que ya tiene el area de texto habilitada, así que mandamos llamar al File Chooser...
    // Igual a Guardar como... puesto que es la primera vez que se da Guardar...
    
    // En caso de que no tengamos nombres de archivos asignados...
    // Significa que el archivo es guardado por primera vez.
    if(archivoAbierto == null && directorioArchivo == null)
    {
        
         guardarEnArchivo();
         // TODO Asignar nombre de archivo a barra de título:
         setTitle(archivoAbierto);
        // Sino solo guardamos...
    } else 
    {
        File f = selectorDeArchivos.getSelectedFile();
        BufferedWriter bw;
            try {
                
                bw = new BufferedWriter(new FileWriter(f));
                areaTexto.write(bw);
                bw.close();
                
            } catch (IOException ex) {
                Logger.getLogger(editorVentana.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        setTitle("New Language v1.0 - " + f.getName());
        haCambiado = false;
        
    } 
    
}//GEN-LAST:event_guardarMenuItemActionPerformed

private void guardarComoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarComoMenuItemActionPerformed
    
    guardarEnArchivo();
    // Cambiar el título de la ventana a el archivo.
    setTitle(archivoAbierto);
}//GEN-LAST:event_guardarComoMenuItemActionPerformed

private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
    
   aboutDialog.setVisible(true);
    
}//GEN-LAST:event_aboutMenuItemActionPerformed

private void salirMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirMenuItemActionPerformed
    // Quiere decir que el usuario eligió salir sin estar editando un archivo
    // Salimos y ya, sin ningún problema.
    if((archivoAbierto == null) && (directorioArchivo == null))
        System.exit(0);
    else
    {
        int option = JOptionareaTexto.showConfirmDialog(this, "¿Desea guardar los cambios?");
        
        switch (option) {
            case JOptionareaTexto.YES_OPTION:     //si elige que si
                
                int state = selectorDeArchivos.showSaveDialog(this);
        
        if(state == JFileChooser.APPROVE_OPTION) 
        {    //si elige guardar en el archivo
            File f = selectorDeArchivos.getSelectedFile();    //se obtiene el archivo seleccionado
            //JOptionareaTexto.showMessageDialog(this, f.getName());
            //JOptionareaTexto.showMessageDialog(this, f.getParent());

            archivoAbierto = f.getName();
            directorioArchivo = f.getParent();
            
            try {
                //abre un flujo de datos hacia el archivo asociado seleccionado
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                //escribe desde el flujo de datos hacia el archivo
                areaTexto.write(bw);
                bw.close();    //cierra el flujo
                
                System.exit(0);
                
            } catch(IOException ex) {    //en caso de que ocurra una excepción
                //presenta un dialogo modal con alguna información de la excepción
                JOptionareaTexto.showMessageDialog(this,ex.getMessage(),ex.toString(),JOptionareaTexto.ERROR_MESSAGE);
            }
        }
                
                break;
            case JOptionareaTexto.CANCEL_OPTION:  //si elige cancelar
                System.exit(0);             //se cancela esta operación
            case JOptionareaTexto.NO_OPTION:
                System.exit(0);
            //en otro caso se continúa con la operación y no se guarda el documento actual
        }

    }
}//GEN-LAST:event_salirMenuItemActionPerformed

private void areaTextoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_areaTextoCaretUpdate
    
    //estadoDeLinea.setText(areaTexto.getCaretPosition() + "");
        //JOptionareaTexto.showMessageDialog(this, y + "");
       cursorPosicion = areaTexto.getCaretPosition();
        try {
            y = areaTexto.getLineOfOffset(cursorPosicion) + 1;
            //x = cursorPosicion - areaTexto.getLineStartOffset(y);
        } catch (BadLocationException ex) {
            Logger.getLogger(editorVentana.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(y == 0)
            ++y;
        
        if(areaTexto.isEnabled() == true)
        estadoDeLinea.setText("Línea: " + y);
        //estadoDeLinea.setText("Línea: " + y + " x : " + (areaTexto.getCaretPosition() - areaTexto.getLineStartOffset(y)));
        
    
}//GEN-LAST:event_areaTextoCaretUpdate

private void areaTextoCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_areaTextoCaretPositionChanged
    //JOptionareaTexto.showMessageDialog(null, areaTexto.getLineCount() + "");
}//GEN-LAST:event_areaTextoCaretPositionChanged

private void areaTextoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_areaTextoKeyPressed
    
}//GEN-LAST:event_areaTextoKeyPressed

private void cerrarArchivoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarArchivoMenuItemActionPerformed
    
    // Quiere decir que el wey quiere cerrar algo y ni siquiera ha escrito algo
    // o está en un archivo...
    if((archivoAbierto == null) && (directorioArchivo == null) && areaTexto.getText().isEmpty())
        return;
    
    // Quiere decir que quiere cerrar el archivo pero ya ha escrito algo areaTexto.getText().isEmpty...
    // Guardamos y cerramos....
    else if((archivoAbierto == null) && (directorioArchivo == null) && !areaTexto.getText().isEmpty())
    {
       //presenta un dialogo modal para que el usuario seleccione un archivo
        int state = selectorDeArchivos.showSaveDialog(this);
        
        if (state == JFileChooser.APPROVE_OPTION)  //si elige guardar en el archivo
        {    
            // Obtenemos ciertas propiedades del archivo elegido, nombre y carpeta.
            File f = selectorDeArchivos.getSelectedFile();    //se obtiene el archivo seleccionado
            
            // Primero guardamos en el archivo
            archivoAbierto = null;
            directorioArchivo = null;
            
            try {
                //abre un flujo de datos hacia el archivo asociado seleccionado
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                //escribe desde el flujo de datos hacia el archivo
                areaTexto.write(bw);
                bw.close();    //cierra el flujo
                
                // Como eligió cerrar, volvemos a deshabilitar el area de texto y las opciones...
                areaTexto.setEditable(false);
                guardarComoMenuItem.setEnabled(false);
                guardarMenuItem.setEnabled(false);
                //imprimirMenuItem.setEnabled(false);
                cerrarArchivoMenuItem.setEnabled(false);
                areaTexto.setEnabled(false);
                areaTexto.setText("");
                estadoDeLinea.setText("Abra o cree un nuevo archivo");
                
            } catch(IOException ex) {    //en caso de que ocurra una excepción
                //presenta un dialogo modal con alguna información de la excepción
                JOptionareaTexto.showMessageDialog(this,ex.getMessage(),ex.toString(),JOptionareaTexto.ERROR_MESSAGE);
            }
        }
        // Quiere decir que el wey ya dió Guardar... y ya hay un archivo asignado...
        // Así que hay que guardar de nuevo...
    } else if((archivoAbierto != null) && (directorioArchivo != null))
    {
        // Cogemos el nombre del archivo.
        File f = selectorDeArchivos.getSelectedFile();
        BufferedWriter bw;
            try {
                
                bw = new BufferedWriter(new FileWriter(f));
                // Escribimos en el archivo.
                areaTexto.write(bw);
                bw.close();
                
            } catch (IOException ex) {
                Logger.getLogger(editorVentana.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Deshabilitamos todo...
            // Como eligió cerrar, volvemos a deshabilitar el area de texto y las opciones...
                areaTexto.setEditable(false);
                guardarComoMenuItem.setEnabled(false);
                guardarMenuItem.setEnabled(false);
                //imprimirMenuItem.setEnabled(false);
                cerrarArchivoMenuItem.setEnabled(false);
                areaTexto.setEnabled(false);
                areaTexto.setText("");
                estadoDeLinea.setText("Abra o cree un nuevo archivo");
    }
    
    archivoAbierto = null;
    directorioArchivo = null;
    
}//GEN-LAST:event_cerrarArchivoMenuItemActionPerformed

private void alCerrar(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_alCerrar
    
    // Quiere decir que hay un archivo asignado...
    if((archivoAbierto != null) && (directorioArchivo != null))
    {
        // Las opciones para el cuadro de pregunta.
        Object[] options = {"Sí", "No"};
        int n = JOptionareaTexto.showOptionDialog(this, "Desea guardar los cambios ?",
                "Guardar cambios en : " + archivoAbierto,
                 JOptionareaTexto.YES_NO_OPTION,
                 JOptionareaTexto.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
        
        JOptionareaTexto.showMessageDialog(this, n + "");
        
        // Quiere decir que el wey ya ha escrito algo pero no ha guardado...
    } else if((areaTexto.isEnabled() == true) && (areaTexto.getText().isEmpty() == false))
    {
        // Presentar el cuadro de diálogo....
        Object[] options = {"Sí", "No"};
        int n = JOptionareaTexto.showOptionDialog(this, "Desea guardar los cambios ?",
        "Guardar",JOptionareaTexto.YES_NO_OPTION,JOptionareaTexto.QUESTION_MESSAGE,
        null,options,options[0]);
        
        if(n == 0)
            guardarEnArchivo();
        System.exit(0);
    } else
        System.exit(0);
}//GEN-LAST:event_alCerrar

private void seleccionarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarTodoActionPerformed
    // Solo accionamos si el area de texto está habilitada...
    if(areaTexto.isEnabled())
    {
        areaTexto.requestFocusInWindow();
        areaTexto.selectAll(); 
    }
}//GEN-LAST:event_seleccionarTodoActionPerformed

private void cambiarTipoLetraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarTipoLetraActionPerformed
    FontChooser font = new FontChooser(this);
    font.setVisible(true);
    
    if(font.getSelectedFont() != null)
        areaTexto.setFont(font.getSelectedFont());
    
    repaint();
    
}//GEN-LAST:event_cambiarTipoLetraActionPerformed

private void resaltarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resaltarBtnActionPerformed
    buscarTexto(buscarTextoTxt.getText());
}//GEN-LAST:event_resaltarBtnActionPerformed

private void cambiarColorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarColorMenuItemActionPerformed
    
    Color newColor = JColorChooser.showDialog(this, "Dialog Title", Color.white);
    areaTexto.setBackground(newColor);
    
}//GEN-LAST:event_cambiarColorMenuItemActionPerformed

private void menuFormatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFormatoActionPerformed
   
}//GEN-LAST:event_menuFormatoActionPerformed

private void colorFuenteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorFuenteMenuItemActionPerformed
    Color newColor = JColorChooser.showDialog(this, "Dialog Title", Color.black);
    areaTexto.setForeground(newColor);
}//GEN-LAST:event_colorFuenteMenuItemActionPerformed

private void selectAllMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAllMenuItemActionPerformed
    // Si el área de Texto está habilitada...
    if(areaTexto.isEnabled())
    {
        areaTexto.requestFocusInWindow();
        areaTexto.selectAll(); 
    }
}//GEN-LAST:event_selectAllMenuItemActionPerformed

private void copiarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copiarMenuItemActionPerformed
    TextTransfer textTransfer = new TextTransfer();
    textTransfer.setClipboardContents(areaTexto.getSelectedText());
}//GEN-LAST:event_copiarMenuItemActionPerformed

private void imprimirMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirMenuItemActionPerformed
    boolean result = false;
    result = PrintAction.print(areaTexto, this);
}//GEN-LAST:event_imprimirMenuItemActionPerformed

private void resaltarareaTextoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resaltarareaTextoBtnActionPerformed
    for(int i = 0; i < cadenas.length; i++)
        resaltar(cadenas[i]);
}//GEN-LAST:event_resaltarareaTextoBtnActionPerformed

    public static void main(String args[]) {
        
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(editorVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editorVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editorVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editorVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new editorVentana().setVisible(true);
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog aboutDialog;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem abrirMenuItem;
    javax.swing.JTextArea areaTexto;
    private javax.swing.JMenu ayudaMenu;
    private javax.swing.JTextField buscarTextoTxt;
    private javax.swing.JMenuItem cambiarColorMenuItem;
    private javax.swing.JMenuItem cambiarTipoLetra;
    private javax.swing.JMenuItem cerrarArchivoMenuItem;
    private javax.swing.JColorChooser colorArea;
    private javax.swing.JMenuItem colorFuenteMenuItem;
    private javax.swing.JMenuItem copiarMenuItem;
    private javax.swing.JDialog dialogoColor;
    private javax.swing.JLabel estadoDeLinea;
    private javax.swing.JMenuItem guardarComoMenuItem;
    private javax.swing.JMenuItem guardarMenuItem;
    private javax.swing.JMenuItem imprimirMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollareaTexto jScrollareaTexto1;
    private javax.swing.JScrollareaTexto jScrollareaTexto2;
    private javax.swing.JScrollareaTexto jScrollareaTexto3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuFormato;
    private javax.swing.JMenuItem nuevoMenuItem;
    private javax.swing.JTextareaTexto areaTexto;
    private javax.swing.JPopupMenu popup;
    private javax.swing.JButton resaltarBtn;
    private javax.swing.JButton resaltarareaTextoBtn;
    private javax.swing.JMenuItem salirMenuItem;
    private javax.swing.JMenuItem seleccionarTodo;
    private javax.swing.JMenuItem selectAllMenuItem;
    private javax.swing.JFileChooser selectorDeArchivos;
    // End of variables declaration//GEN-END:variables
}
