package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.CtrClientes;
import model.Cliente;

public class FrmClientes extends JFrame {

	private static final long serialVersionUID = 1L;

	// Campos de texto para búsqueda y detalles del cliente
	public static JTextField txtBuscar, txtId, txtDni, txtNombre, txtApellidos, txtCorreo;

	// ComboBox para seleccionar el atributo de búsqueda
	public static JComboBox<String> lstAtributos;

	// Botones para las operaciones CRUD
	private JButton btnNuevo, btnBorrar, btnActualizar, btnBuscar;

	// Modelo de la tabla para manejar los datos de los clientes
	private DefaultTableModel model;

	// Constructor de la clase FrmClientes
	public FrmClientes() {

		// Configuración de la ventana
		setTitle("Gestión clientes");
		setSize(750, 250);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra la ventana al presionar 'x'
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/icono.png"));

		// Controlador de clientes
		CtrClientes ctr = new CtrClientes();
		List<String> dataList = new ArrayList<>();

		// Recorrer la lista de clientes y agregar la representación en cadena de cada
		// cliente
		try {
			for (Cliente c : ctr.lstClientes()) {
				dataList.add(c.toString());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error lista clientes", "Error.", JOptionPane.ERROR_MESSAGE);

		}

		// Suponiendo que cada cliente tiene el mismo número de campos
		int numeroColumnas = dataList.get(0).split(",").length;

		// Convertir la lista en una matriz
		Object[][] datate = new Object[dataList.size()][numeroColumnas];
		for (int i = 0; i < dataList.size(); i++) {
			String[] campos = dataList.get(i).split(",");
			datate[i] = campos;
		}
		// Obtener los nombres de las columnas
		String[] columnasNames = null;
		try {
			columnasNames = ctr.obtenerColumnas();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error columnas", "Error.", JOptionPane.ERROR_MESSAGE);
		}

		// Crear el modelo de la tabla con los datos y nombres de columnas
		model = new DefaultTableModel(datate, columnasNames);
		JTable table = new JTable(model);

		// Añadir la tabla a un JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);

		// Añadir el JScrollPane al centro del contenedor principal
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);

		// Crear un panel para los botones
		JPanel panBotones = new JPanel();

		// Etiqueta para la sección de búsqueda/eliminación
		JLabel lblBusqueda = new JLabel("Búsqueda/Eliminación");
		panBotones.add(lblBusqueda);

		// ComboBox para seleccionar el atributo de búsqueda
		lstAtributos = new JComboBox<>();
		lstAtributos.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Id", "DNI", "Nombre", "Apellidos", "Email" }));
		lstAtributos.setBounds(80, 45, 85, 20);
		panBotones.add(lstAtributos);

		// Campo de texto para ingresar el término de búsqueda
		txtBuscar = new JTextField();
		txtBuscar.setColumns(10);
		panBotones.add(txtBuscar);

		// Botones de búsqueda y eliminación
		btnBuscar = new JButton("Buscar");
		panBotones.add(btnBuscar);

		btnBorrar = new JButton("Borrar");
		panBotones.add(btnBorrar);

		btnNuevo = new JButton("Nuevo");
		panBotones.add(btnNuevo);

		btnActualizar = new JButton("Actualizar");
		panBotones.add(btnActualizar);

		// Añadir el panel de botones al sur del contenedor principal
		this.getContentPane().add(panBotones, BorderLayout.SOUTH);

		// Crear un panel para la ficha del cliente
		JPanel panFicha = new JPanel();

		// Etiquetas y campos de texto para los detalles del cliente
		JLabel lblId = new JLabel("Id:  ");
		panFicha.add(lblId);

		txtId = new JTextField();
		txtId.setColumns(10);
		panFicha.add(txtId);

		JLabel lblDni = new JLabel("DNI:  ");
		panFicha.add(lblDni);

		txtDni = new JTextField();
		txtDni.setColumns(10);
		panFicha.add(txtDni);

		JLabel lblNombre = new JLabel("Nombre:  ");
		panFicha.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		panFicha.add(txtNombre);

		JLabel lblApellidos = new JLabel("Apellidos:  ");
		panFicha.add(lblApellidos);

		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		panFicha.add(txtApellidos);

		JLabel lblCorreo = new JLabel("Email:  ");
		panFicha.add(lblCorreo);

		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		panFicha.add(txtCorreo);

		// Añadir el panel de la ficha al este del contenedor principal
		this.getContentPane().add(panFicha, BorderLayout.EAST);
		panFicha.setLayout(new GridLayout(5, 2, 1, 5));
		panFicha.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Añadir listeners a los botones
		addListeners();

		// Hacer visible la ventana
		setVisible(true);
	}

	// Método para añadir listeners a los botones
	private void addListeners() {
		CtrClientes ctr = new CtrClientes();

		// Listener para el botón 'Nuevo'
		btnNuevo.addActionListener(e -> {
			try {
				ctr.addCliente();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Error añadir persona", "Error.", JOptionPane.ERROR_MESSAGE);
			}
		});

		// Listener para el botón 'Borrar'
		btnBorrar.addActionListener(e -> {
			try {
				ctr.delPersona();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Error deletear persona", "Error.", JOptionPane.ERROR_MESSAGE);
			}
		});

		// Listener para el botón 'Actualizar'
		btnActualizar.addActionListener(e -> {
			try {
				actualizarTabla();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Error actualizar tabla", "Error.", JOptionPane.ERROR_MESSAGE);
			}
		});

		// Listener para el botón 'Buscar'
		btnBuscar.addActionListener(e -> {
			try {
				filtrarTabla();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Error buscar", "Error.", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	// Método para actualizar la tabla con los datos más recientes
	private void actualizarTabla() throws Exception {

		CtrClientes ctr = new CtrClientes();
		List<String> dataList = new ArrayList<>();

		// Recorrer la lista de clientes y agregar la representación en cadena de cada
		// cliente
		for (Cliente c : ctr.lstClientes()) {
			dataList.add(c.toString());
		}

		// Suponiendo que cada cliente tiene el mismo número de campos
		int numeroColumnas = dataList.get(0).split(",").length;

		// Convertir la lista en una matriz
		Object[][] datate = new Object[dataList.size()][numeroColumnas];
		for (int i = 0; i < dataList.size(); i++) {
			String[] campos = dataList.get(i).split(",");
			datate[i] = campos;
		}

		// Obtener los nombres de las columnas
		String[] columnasNames = ctr.obtenerColumnas();

		// Actualizar el modelo de la tabla existente
		model.setDataVector(datate, columnasNames);
	}

	// Método para filtrar los datos de la tabla según el criterio de búsqueda
	private void filtrarTabla() throws Exception {

		CtrClientes ctr = new CtrClientes();
		Cliente c = ctr.getCliente();

		if (c != null) {
			// Crear una matriz con los datos del cliente encontrado
			Object[][] datate = new Object[1][5];
			datate[0][0] = c.getId();
			datate[0][1] = c.getDni();
			datate[0][2] = c.getNombre();
			datate[0][3] = c.getApellidos();
			datate[0][4] = c.getEmail();

			// Actualizar el modelo de la tabla con los datos del cliente encontrado
			model.setDataVector(datate, ctr.obtenerColumnas());
		} else {
			// Si no se encuentra el cliente, vaciar la tabla
			model.setDataVector(new Object[0][5], ctr.obtenerColumnas());
		}
	}
}