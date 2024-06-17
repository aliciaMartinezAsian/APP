
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

import controller.CtrTratamientos;
import model.Tratamiento;

/**
 * <h2>Clase para la interfaz gráfica de gestión de tratamientos.</h2>
 * <p>
 * Extiende JFrame para proporcionar una ventana con componentes Swing.
 * </p>
 * 
 * @author Alicia Martinez Asian
 * @version 1.0
 * @since 15/06/2024
 */
public class FrmTratamientos extends JFrame {
	/**
	 * Atributo para la serialización de objetos en Java.
	 */
	private static final long serialVersionUID = 1L;

	// Campos de texto para búsqueda y detalles del tratamiento
	public static JTextField txtBuscar, txtId, txtPrecio, txtDuracion, txtDescripcion, txtTipo;

	// ComboBox para seleccionar el atributo de búsqueda
	public static JComboBox<String> lstAtributos;

	// Botones para las operaciones CRUD
	private JButton btnNuevo, btnBorrar, btnActualizar, btnBuscar;

	// Modelo de la tabla para manejar los datos de los tratamientos
	private DefaultTableModel model;

	/**
	 * <h2>Constructor por defecto de la clase FrmTratamientos.</h2>
	 * <p>
	 * Configura la ventana y sus componentes.
	 * <p>
	 */
	// Contrusctor por defecto de la clase que configura la ventana y sus componentes
	public FrmTratamientos() {
		// Configuración de la ventana
		setTitle("Gestión tratamientos");
		setSize(750, 250);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra la ventana al presionar 'x'
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/icono.png"));

		// Controlador de tratamientos
		CtrTratamientos ctr = new CtrTratamientos();
		List<String> dataList = new ArrayList<>();

		// Recorrer la lista de tratamientos y agregar la representación en cadena de
		// cada tratamiento
		try {
			for (Tratamiento t : ctr.lstTratamientos()) {
				dataList.add(t.toString());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error lista tratamientos", "Error.", JOptionPane.ERROR_MESSAGE);
		}

		// Suponiendo que cada tratamiento tiene el mismo número de campos
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
				new DefaultComboBoxModel<>(new String[] { "Id", "Precio", "Duracion", "Descripcion", "Tipo" }));
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

		// Crear un panel para la ficha del tratamiento
		JPanel panFicha = new JPanel();

		// Etiquetas y campos de texto para los detalles del tratamiento
		JLabel lblId = new JLabel("Id:  ");
		panFicha.add(lblId);

		txtId = new JTextField();
		txtId.setColumns(10);
		panFicha.add(txtId);

		JLabel lblPrecio = new JLabel("Precio:  ");
		panFicha.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		panFicha.add(txtPrecio);

		JLabel lblDuracion = new JLabel("Duración:  ");
		panFicha.add(lblDuracion);

		txtDuracion = new JTextField();
		txtDuracion.setColumns(10);
		panFicha.add(txtDuracion);

		JLabel lblDescripcion = new JLabel("Descripción:  ");
		panFicha.add(lblDescripcion);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		panFicha.add(txtDescripcion);

		JLabel lblTipo = new JLabel("Tipo:  ");
		panFicha.add(lblTipo);

		txtTipo = new JTextField();
		txtTipo.setColumns(10);
		panFicha.add(txtTipo);

		// Añadir el panel de la ficha al este del contenedor principal
		this.getContentPane().add(panFicha, BorderLayout.EAST);
		panFicha.setLayout(new GridLayout(5, 2, 1, 5));
		panFicha.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Añadir listeners a los botones
		addListeners();

		// Hacer visible la ventana
		setVisible(true);
	}

	/**
	 * <h2>Método que configura las acciones a realizar cuando se presionan los
	 * botones.</h2>
	 */
	// Método para añadir los listeners a los botones
	private void addListeners() {
		CtrTratamientos ctr = new CtrTratamientos();

		// Listener para el botón 'Nuevo'
		btnNuevo.addActionListener(e -> {
			try {
				ctr.addTratamiento();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Error añadir tratamiento", "Error.", JOptionPane.ERROR_MESSAGE);
			}
		});

		// Listener para el botón 'Borrar'
		btnBorrar.addActionListener(e -> {
			try {
				ctr.delTratamiento();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Error eliminar tratamiento", "Error.", JOptionPane.ERROR_MESSAGE);
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

	/**
	 * <h2>Método para actualizar la tabla con los datos más recientes después de
	 * cambios.</h2>
	 *
	 * @throws Exception si ocurre un error al actualizar la tabla
	 */
	// Método para actualizar la tabla después de cambios
	private void actualizarTabla() throws Exception {
		CtrTratamientos ctr = new CtrTratamientos();
		List<String> dataList = new ArrayList<>();

		// Recorrer la lista de tratamientos y agregar la representación en cadena de
		// cada tratamiento
		for (Tratamiento t : ctr.lstTratamientos()) {
			dataList.add(t.toString());
		}

		// Suponiendo que cada tratamiento tiene el mismo número de campos
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

	/**
	 * <h2>Método para filtrar los datos de la tabla según el criterio de
	 * búsqueda.</h2>
	 *
	 * @throws Exception si ocurre un error al filtrar la tabla
	 */
	// Método para filtrar los datos de la tabla según el criterio de búsqueda.
	private void filtrarTabla() throws Exception {
		CtrTratamientos ctr = new CtrTratamientos();
		Tratamiento t = ctr.getTratamiento();

		if (t != null) {
			// Crear una matriz con los datos del tratamiento encontrado
			Object[][] datate = new Object[1][5];
			datate[0][0] = t.getId();
			datate[0][1] = t.getPrecio();
			datate[0][2] = t.getDuracion();
			datate[0][3] = t.getDescripcion();
			datate[0][4] = t.getTipo();

			// Actualizar el modelo de la tabla con los datos del tratamiento encontrado
			model.setDataVector(datate, ctr.obtenerColumnas());
		} else {
			// Si no se encuentra el tratamiento, vaciar la tabla
			model.setDataVector(new Object[0][5], ctr.obtenerColumnas());
		}
	}
}