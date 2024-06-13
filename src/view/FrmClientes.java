package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.CtrClientes;
import model.Cliente;

public class FrmClientes extends JFrame {

	private static final long serialVersionUID = 1L;

	public static JTextField txtBuscar, txtId, txtDni, txtNombre, txtApellidos, txtCorreo;

	private JButton btnNuevo, btnBorrar;

	public FrmClientes() throws Exception {


		setTitle("Gestión clientes");
		setSize(700, 220);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/icono.png"));
		
		CtrClientes ctr = new CtrClientes();
		List<String> dataList = new ArrayList<>();
	

		  // Recorrer la lista de clientes y agregar la representación en cadena de cada cliente
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

        String[] columnasNames = ctr.obtenerColumnas();

       
     		DefaultTableModel model = new DefaultTableModel(datate, columnasNames);
     		JTable table = new JTable(model);

     		JScrollPane scrollPane = new JScrollPane(table);

        
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);

		JPanel panBotones = new JPanel();

		JLabel lblBusqueda = new JLabel("Búsqueda por id:  ");
		panBotones.add(lblBusqueda);

		txtBuscar = new JTextField();
		txtBuscar.setColumns(10);
		panBotones.add(txtBuscar);

		btnNuevo = new JButton("Nuevo");
		panBotones.add(btnNuevo);

		btnBorrar = new JButton("Borrar");
		panBotones.add(btnBorrar);

		this.getContentPane().add(panBotones, BorderLayout.SOUTH);

		JPanel panFicha = new JPanel();

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

		this.getContentPane().add(panFicha, BorderLayout.EAST);
		panFicha.setLayout(new GridLayout(5, 2, 1, 5));

		addListeners();

		setVisible(true);
	}


	private void addListeners() {
		CtrClientes ctr = new CtrClientes();
		
		btnNuevo.addActionListener(e -> ctr.addCliente());
		btnBorrar.addActionListener(e -> ctr.delPersona());

	}

}
