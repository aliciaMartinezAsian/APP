package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * <h2>Interfaz gráfica inicial de la aplicación que funciona como un menú</h2>
 * 
 * @author Alicia Martinez Asian
 * @version 1.0
 * @since 14/06/2024
 */

public class FrmPrincipal extends JFrame {

	/**
	 * Atributo para la serialización de objetos en Java.
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * Atributos que hacen referencia a los botones de la interfaz.
	 */
	// Declaración de botones

	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;

	// Constructor de la clase FrmPrincipal

	public FrmPrincipal() {
		// Establece el layout del contenedor principal
		getContentPane().setLayout(new BorderLayout());

		// Configuración de la ventana
		setTitle("Gestión del SPA");
		setSize(500, 400);
		setResizable(false); // No permite redimensionar la ventana
		setLocationRelativeTo(null); // Centra la ventana
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/icono.png")); // Establece el ícono de la ventana

		// Inicialización de botones
		btn1 = new JButton("Clientes");
		btn2 = new JButton("Trabajadores");
		btn3 = new JButton("Pagos");
		btn4 = new JButton("Reservas");
		btn5 = new JButton("Sesiones");
		btn6 = new JButton("Productos");

		// Creación de un panel para los botones
		JPanel panel = new JPanel();

		panel.add(btn1);
		panel.add(btn2);
		panel.add(btn3);
		panel.add(btn4);
		panel.add(btn5);
		panel.add(btn6);

		// Añadir el panel de botones al centro del contenedor principal

		getContentPane().add(panel, BorderLayout.CENTER);

		// Creación y configuración de la etiqueta del título
		JLabel lblTitulo = new JLabel("Spa Zone");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18)); // Establece fuente y tamaño
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER); // Centra horizontalmente
		getContentPane().add(lblTitulo, BorderLayout.NORTH); // Añade al norte del contenedor principal

		// Creación y configuración de la etiqueta de la imagen
		JLabel lblImagen = new JLabel(new ImageIcon("img/logo.jpg"));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblImagen, BorderLayout.EAST);

		// Creación de un panel para contener el título y la imagen
		JPanel northPanel = new JPanel(new BorderLayout());
		northPanel.add(lblTitulo, BorderLayout.NORTH);
		northPanel.add(lblImagen, BorderLayout.CENTER);

		// Añadir el panel norte al contenedor principal

		getContentPane().add(northPanel, BorderLayout.NORTH);

		// Añadir listeners a los botones
		addListeners();

		// Hacer visible la ventana
		setVisible(true);

	}

	// Método para añadir listeners a los botones
	private void addListeners() {
		// Añadir un listener al botón btn1 que abre una nueva instancia de FrmClientes
		// al ser presionado
		btn1.addActionListener(e -> new FrmClientes());

	}
}
