package view;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 * <h2>Clase para mostrar un manual de usuario en formato HTML dentro de un JDialog.</h2>
 * <p>Esta clase se asegura de que el manual solo se muestre una vez.</p>
 * 
 * @author Alicia Martinez Asian
 * @version 1.0
 * @since 17/06/2024
 */
public class Manual extends JDialog {

    private static final long serialVersionUID = 1L;

    // Indicador de si el manual ya ha sido mostrado
    private static boolean mostrada = false;

    // Ruta del archivo HTML del manual
    final String path = "manual/Manual.html";

    // Componente para mostrar el contenido del manual
    private JEditorPane txtTexto;

    /**
     * <h2>Constructor de la clase Manual.</h2>
     * <p>Inicializa el diálogo y carga el contenido del manual.</p>
     *
     * @param context El marco principal desde donde se invoca este diálogo.
     */
    //Contructor de la clase parametrizado
    public Manual(JFrame context) {
        if (!mostrada) {
            try {
                String contenido = loadManual();
                initComponents();
                txtTexto.setText(contenido);
                mostrada = true;
            } catch (Exception fallo) {
                JOptionPane.showMessageDialog(context, "Error a la hora de abrir el manual", "Error.",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * <h2>Carga el contenido del manual desde el archivo HTML.</h2>
     *
     * @return El contenido del archivo HTML como una cadena.
     * @throws Exception si ocurre un error al leer el archivo.
     */
    //Método qur carga el contenido del manual
    private String loadManual() throws Exception {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                contenido.append(line).append("\n");
            }
            if (contenido.length() > 0) { // Si hay contenido entonces eliminar el último salto de línea
                contenido.deleteCharAt(contenido.length() - 1);
            }
        } catch (IOException e) {
            throw new Exception(e);
        }
        return contenido.toString();
    }

    /**
     * <h2>Inicializa los componentes de la interfaz gráfica del diálogo.</h2>
     */
    //Método que inicializa los componentes gráficos
    private void initComponents() {
        setTitle("Manual");
        setSize(600, 300);
        setResizable(true);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mostrada = false;
                dispose();
            }
        });

        txtTexto = new JEditorPane();
        txtTexto.setContentType("text/html");
        txtTexto.setEditable(false);

        JScrollPane panScroll = new JScrollPane(txtTexto);
        getContentPane().add(panScroll, BorderLayout.CENTER);

        setVisible(true);
    }

}