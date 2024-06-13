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

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;

	public FrmPrincipal() {

		getContentPane().setLayout(new BorderLayout());

		setTitle("Gestión del SPA");
		setSize(500, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/icono.png"));

		btn1 = new JButton("Clientes");
		btn2 = new JButton("Trabajadores");
		btn3 = new JButton("Pagos");
		btn4 = new JButton("Reservas");
		btn5 = new JButton("Sesiones");
		btn6 = new JButton("Productos");

		JPanel panel = new JPanel();

		panel.add(btn1);
		panel.add(btn2);
		panel.add(btn3);
		panel.add(btn4);
		panel.add(btn5);
		panel.add(btn6);
		
		
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblTitulo = new JLabel("Spa Zone");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));	
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER); 
		getContentPane().add(lblTitulo, BorderLayout.NORTH);

		JLabel lblImagen = new JLabel(new ImageIcon("img/logo.jpg"));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER); 
		getContentPane().add(lblImagen, BorderLayout.EAST);
		
		

		addListeners();

		setVisible(true);

	}

	private void addListeners() {

		btn1.addActionListener(e -> {
			try {
				new FrmClientes();
			} catch (Exception e1) {
				e1.getStackTrace();
			}
		});

	}
}
