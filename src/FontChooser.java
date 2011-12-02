import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FontChooser extends JDialog {
  String family; // The name of the font family
  int style; // The font style
  int size; // The font size
  Font selectedFont; // The Font they correspond to
  String[] fontFamilies;
  ItemChooser families, styles, sizes;
  JTextArea preview;
  JButton okay, cancel;

  static final String[] styleNames = new String[] { "Plain", "Italic",
      "Bold", "BoldItalic" };
  static final Integer[] styleValues = new Integer[] {
      new Integer(Font.PLAIN), new Integer(Font.ITALIC),
      new Integer(Font.BOLD), new Integer(Font.BOLD + Font.ITALIC) };
  static final String[] sizeNames = new String[] { "8", "10", "12", "14",
      "18", "20", "24", "28", "32", "40", "48", "56", "64", "72" };
  static final String defaultPreviewString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ\n"
      + "abcdefghijklmnopqrstuvwxyz\n"
      + "1234567890!@#$%^&*()_-=+[]{}<,.>\n"
      + "GLHBR Un lenguaje para los que se inician en este largo camino...";
  public FontChooser(Frame owner) {
    super(owner, "Escoja una fuente"); // Set dialog frame and title
    setModal(true);
// Figure out what fonts are available on the system
    GraphicsEnvironment env = GraphicsEnvironment
        .getLocalGraphicsEnvironment();
    fontFamilies = env.getAvailableFontFamilyNames();

    // Set initial values for the properties
    family = fontFamilies[0];
    style = Font.PLAIN;
    size = 18;
    selectedFont = new Font(family, style, size);

    // Create ItemChooser objects that allow the user to select font
    // family, style, and size.
    families = new ItemChooser("Family", fontFamilies, null, 0,
        ItemChooser.COMBOBOX);
    styles = new ItemChooser("Style", styleNames, styleValues, 0,
        ItemChooser.COMBOBOX);
    sizes = new ItemChooser("Size", sizeNames, null, 4,
        ItemChooser.COMBOBOX);

    // Now register event listeners to handle selections
    
    families.addItemChooserListener(new ItemChooser.Listener() {
        @Override
      public void itemChosen(ItemChooser.Event e) {
        setFontFamily((String) e.getSelectedValue());
      }
    });
    styles.addItemChooserListener(new ItemChooser.Listener() {
        @Override
      public void itemChosen(ItemChooser.Event e) {
        setFontStyle(((Integer) e.getSelectedValue()).intValue());
      }
    });
    sizes.addItemChooserListener(new ItemChooser.Listener() {
            @Override
      public void itemChosen(ItemChooser.Event e) {
        setFontSize(Integer.parseInt((String) e.getSelectedValue()));
      }
    });

    // Create a component to preview the font.
    preview = new JTextArea(defaultPreviewString, 5, 40);
    preview.setFont(selectedFont);

    // Create buttons to dismiss the dialog, and set handlers on them
    okay = new JButton("Okay");
    cancel = new JButton("Cancel");
    okay.addActionListener(new ActionListener() {
            @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
      }
    });
    cancel.addActionListener(new ActionListener() {
            @Override
      public void actionPerformed(ActionEvent e) {
        selectedFont = null;
        setVisible(false);
      }
    });

    // Put the ItemChoosers in a Box
    Box choosersBox = Box.createHorizontalBox();
    choosersBox.add(Box.createHorizontalStrut(15));
    choosersBox.add(families);
    choosersBox.add(Box.createHorizontalStrut(15));
    choosersBox.add(styles);
    choosersBox.add(Box.createHorizontalStrut(15));
    choosersBox.add(sizes);
    choosersBox.add(Box.createHorizontalStrut(15));
    choosersBox.add(Box.createGlue());

    // Put the dismiss buttons in another box
    Box buttonBox = Box.createHorizontalBox();
    buttonBox.add(Box.createGlue());
    buttonBox.add(okay);
    buttonBox.add(Box.createGlue());
    buttonBox.add(cancel);
    buttonBox.add(Box.createGlue());

    // Put the choosers at the top, the buttons at the bottom, and
    // the preview in the middle.
    Container contentPane = getContentPane();
    contentPane.add(new JScrollPane(preview), BorderLayout.CENTER);
    contentPane.add(choosersBox, BorderLayout.NORTH);
    contentPane.add(buttonBox, BorderLayout.SOUTH);

    // Set the dialog size based on the component size.
    pack();
  }

  /**
   * Call this method after show() to obtain the user's selection. If the user
   * used the "Cancel" button, this will return null
   */
  public Font getSelectedFont() {
    return selectedFont;
  }

  // These are other property getter methods
  public String getFontFamily() {
    return family;
  }

  public int getFontStyle() {
    return style;
  }

  public int getFontSize() {
    return size;
  }

  // The property setter methods are a little more complicated.
  // Note that none of these setter methods update the corresponding
  // ItemChooser components as they ought to.
  public void setFontFamily(String name) {
    family = name;
    changeFont();
  }

  public void setFontStyle(int style) {
    this.style = style;
    changeFont();
  }

  public void setFontSize(int size) {
    this.size = size;
    changeFont();
  }

  public void setSelectedFont(Font font) {
    selectedFont = font;
    family = font.getFamily();
    style = font.getStyle();
    size = font.getSize();
    preview.setFont(font);
  }

  // This method is called when the family, style, or size changes
  protected void changeFont() {
    selectedFont = new Font(family, style, size);
    preview.setFont(selectedFont);
  }

  // Override this inherited method to prevent anyone from making us modeless
    @Override
  public boolean isModal() {
    return true;
  }

  public static void main(String[] args) {
    // Create some components and a FontChooser dialog
    final JFrame frame = new JFrame("demo");
    final JButton button = new JButton("Push Me!");
    final FontChooser chooser = new FontChooser(frame);

    // Handle button clicks
    button.addActionListener(new ActionListener() {
            @Override
      public void actionPerformed(ActionEvent e) {
        // Pop up the dialog
        chooser.show();
        // Get the user's selection
        Font font = chooser.getSelectedFont();
        // If not cancelled, set the button font
        if (font != null)
          button.setFont(font);
      }
    });

    // Display the demo
    frame.getContentPane().add(button);
    frame.setSize(200, 100);
    frame.show();
  }
}

class ItemChooser extends JPanel {
  // These fields hold property values for this component
  String name; // The overall name of the choice

  String[] labels; // The text for each choice option

  Object[] values; // Arbitrary values associated with each option

  int selection; // The selected choice

  int presentation; // How the choice is presented

  // These are the legal values for the presentation field
  public static final int LIST = 1;

  public static final int COMBOBOX = 2;

  public static final int RADIOBUTTONS = 3;

  // These components are used for each of the 3 possible presentations
  JList list; // One type of presentation

  JComboBox combobox; // Another type of presentation

  JRadioButton[] radiobuttons; // Yet another type

  // The list of objects that are interested in our state
  ArrayList listeners = new ArrayList();

  // The constructor method sets everything up
  public ItemChooser(String name, String[] labels, Object[] values,
      int defaultSelection, int presentation) {
    // Copy the constructor arguments to instance fields
    this.name = name;
    this.labels = labels;
    this.values = values;
    this.selection = defaultSelection;
    this.presentation = presentation;

    // If no values were supplied, use the labels
    if (values == null)
      this.values = labels;

    // Now create content and event handlers based on presentation type
    switch (presentation) {
    case LIST:
      initList();
      break;
    case COMBOBOX:
      initComboBox();
      break;
    case RADIOBUTTONS:
      initRadioButtons();
      break;
    }
  }

  // Initialization for JList presentation
  void initList() {
    list = new JList(labels); // Create the list
    list.setSelectedIndex(selection); // Set initial state

    // Handle state changes
    list.addListSelectionListener(new ListSelectionListener() {
            @Override
      public void valueChanged(ListSelectionEvent e) {
        ItemChooser.this.select(list.getSelectedIndex());
      }
    });

    // Lay out list and name label vertically
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // vertical
    this.add(new JLabel(name)); // Display choice name
    this.add(new JScrollPane(list)); // Add the JList
  }

  // Initialization for JComboBox presentation
  void initComboBox() {
    combobox = new JComboBox(labels); // Create the combo box
    combobox.setSelectedIndex(selection); // Set initial state

    // Handle changes to the state
    combobox.addItemListener(new ItemListener() {
            @Override
      public void itemStateChanged(ItemEvent e) {
        ItemChooser.this.select(combobox.getSelectedIndex());
      }
    });

    // Lay out combo box and name label horizontally
    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    this.add(new JLabel(name));
    this.add(combobox);
  }

  // Initialization for JRadioButton presentation
  void initRadioButtons() {
    // Create an array of mutually exclusive radio buttons
    radiobuttons = new JRadioButton[labels.length]; // the array
    ButtonGroup radioButtonGroup = new ButtonGroup(); // used for exclusion
    ChangeListener listener = new ChangeListener() { // A shared listener
            @Override
      public void stateChanged(ChangeEvent e) {
        JRadioButton b = (JRadioButton) e.getSource();
        if (b.isSelected()) {
          // If we received this event because a button was
          // selected, then loop through the list of buttons to
          // figure out the index of the selected one.
          for (int i = 0; i < radiobuttons.length; i++) {
            if (radiobuttons[i] == b) {
              ItemChooser.this.select(i);
              return;
            }
          }
        }
      }
    };

    // Display the choice name in a border around the buttons
    this.setBorder(new TitledBorder(new EtchedBorder(), name));
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // Create the buttons, add them to the button group, and specify
    // the event listener for each one.
    for (int i = 0; i < labels.length; i++) {
      radiobuttons[i] = new JRadioButton(labels[i]);
      if (i == selection)
        radiobuttons[i].setSelected(true);
      radiobuttons[i].addChangeListener(listener);
      radioButtonGroup.add(radiobuttons[i]);
      this.add(radiobuttons[i]);
    }
  }

  // These simple property accessor methods just return field values
  // These are read-only properties. The values are set by the constructor
  // and may not be changed.
    @Override
  public String getName() {
    return name;
  }

  public int getPresentation() {
    return presentation;
  }

  public String[] getLabels() {
    return labels;
  }

  public Object[] getValues() {
    return values;
  }

  /** Return the index of the selected item */
  public int getSelectedIndex() {
    return selection;
  }

  /** Return the object associated with the selected item */
  public Object getSelectedValue() {
    return values[selection];
  }

  /**
   * Set the selected item by specifying its index. Calling this method
   * changes the on-screen display but does not generate events.
   */
  public void setSelectedIndex(int selection) {
    switch (presentation) {
    case LIST:
      list.setSelectedIndex(selection);
      break;
    case COMBOBOX:
      combobox.setSelectedIndex(selection);
      break;
    case RADIOBUTTONS:
      radiobuttons[selection].setSelected(true);
      break;
    }
    this.selection = selection;
  }

  /**
   * This internal method is called when the selection changes. It stores the
   * new selected index, and fires events to any registered listeners. The
   * event listeners registered on the JList, JComboBox, or JRadioButtons all
   * call this method.
   */
  protected void select(int selection) {
    this.selection = selection; // Store the new selected index
    if (!listeners.isEmpty()) { // If there are any listeners registered
      // Create an event object to describe the selection
      ItemChooser.Event e = new ItemChooser.Event(this, selection,
          values[selection]);
      // Loop through the listeners using an Iterator
      for (Iterator i = listeners.iterator(); i.hasNext();) {
        ItemChooser.Listener l = (ItemChooser.Listener) i.next();
        l.itemChosen(e); // Notify each listener of the selection
      }
    }
  }

  // These methods are for event listener registration and deregistration
  public void addItemChooserListener(ItemChooser.Listener l) {
    listeners.add(l);
  }

  public void removeItemChooserListener(ItemChooser.Listener l) {
    listeners.remove(l);
  }

  public static class Event extends java.util.EventObject {
    int selectedIndex; // index of the selected item

    Object selectedValue; // the value associated with it

    public Event(ItemChooser source, int selectedIndex, Object selectedValue) {
      super(source);
      this.selectedIndex = selectedIndex;
      this.selectedValue = selectedValue;
    }

    public ItemChooser getItemChooser() {
      return (ItemChooser) getSource();
    }

    public int getSelectedIndex() {
      return selectedIndex;
    }

    public Object getSelectedValue() {
      return selectedValue;
    }
  }

  /**
   * This inner interface must be implemented by any object that wants to be
   * notified when the current selection in a ItemChooser component changes.
   */
  public interface Listener extends java.util.EventListener {
    public void itemChosen(ItemChooser.Event e);
  }

  /**
   * This inner class is a simple demonstration of the ItemChooser component
   * It uses command-line arguments as ItemChooser labels and values.
   */
  public static class Demo {
    public static void main(String[] args) {
      // Create a window, arrange to handle close requests
      final JFrame frame = new JFrame("ItemChooser Demo");
      frame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
      });

      // A "message line" to display results in
      final JLabel msgline = new JLabel(" ");

      // Create a panel holding three ItemChooser components
      JPanel chooserPanel = new JPanel();
      final ItemChooser c1 = new ItemChooser("Choice #1", args, null, 0,
          ItemChooser.LIST);
      final ItemChooser c2 = new ItemChooser("Choice #2", args, null, 0,
          ItemChooser.COMBOBOX);
      final ItemChooser c3 = new ItemChooser("Choice #3", args, null, 0,
          ItemChooser.RADIOBUTTONS);

      // An event listener that displays changes on the message line
      ItemChooser.Listener l = new ItemChooser.Listener() {
        public void itemChosen(ItemChooser.Event e) {
          msgline.setText(e.getItemChooser().getName() + ": "
              + e.getSelectedIndex() + ": "
              + e.getSelectedValue());
        }
      };
      c1.addItemChooserListener(l);
      c2.addItemChooserListener(l);
      c3.addItemChooserListener(l);

      // Instead of tracking every change with a ItemChooser.Listener,
      // applications can also just query the current state when
      // they need it. Here's a button that does that.
      JButton report = new JButton("Report");
      report.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          // Note the use of multi-line italic HTML text
          // with the JOptionPane message dialog box.
          String msg = "<html><i>" + c1.getName() + ": "
              + c1.getSelectedValue() + "<br>" + c2.getName()
              + ": " + c2.getSelectedValue() + "<br>"
              + c3.getName() + ": " + c3.getSelectedValue()
              + "</i>";
          JOptionPane.showMessageDialog(frame, msg);
        }
      });

      // Add the 3 ItemChooser objects, and the Button to the panel
      chooserPanel.add(c1);
      chooserPanel.add(c2);
      chooserPanel.add(c3);
      chooserPanel.add(report);

      // Add the panel and the message line to the window
      Container contentPane = frame.getContentPane();
      contentPane.add(chooserPanel, BorderLayout.CENTER);
      contentPane.add(msgline, BorderLayout.SOUTH);

      // Set the window size and pop it up.
      frame.pack();
      frame.show();
    }
  }
}