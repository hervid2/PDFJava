import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class ComandasCocina {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComandasCocina window = new ComandasCocina();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ComandasCocina() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 780, 624);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 140, 0));
		panel.setBounds(10, 11, 764, 585);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sistema Generación Comandas Cocina");
		lblNewLabel.setBounds(190, 0, 384, 78);
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		panel.add(lblNewLabel);
		
		JLabel lblSeleccioneElPlato = new JLabel("Seleccione el plato:");
		lblSeleccioneElPlato.setFont(new Font("Consolas", Font.BOLD, 12));
		lblSeleccioneElPlato.setBounds(10, 64, 148, 39);
		panel.add(lblSeleccioneElPlato);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Consolas", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Seleccione...", "", "ENTRADAS (Para abrir tu apetito):", "- Langostinos Tempura", "- Langostinos Samurai", "-Palmitos Crunchy Tokio", "", "SUSHI BURRITOS:", "- Karate Chicken", "- American Chicken", "- Chancho Power", "- El más Chido", "- Veggie", "- El Kappo (De la casa)", "- EBI", "- EL Tuna Salmón", "- Ninja Crunch", "- Tigre Siberiano", "", "POKE BOWLS:", "- Carnívoro", "- Katana Bakana", "- Monte Fuji", "- Salmón Emperador", "", "MENÚ INFANTIL:", "- Nuggets de pollo", "-Picadita Junior"}));
		comboBox.setBounds(181, 65, 556, 33);
		panel.add(comboBox);
		
		JLabel lblSeleccioneElNmero = new JLabel("Seleccione el número de platos:");
		lblSeleccioneElNmero.setFont(new Font("Consolas", Font.BOLD, 12));
		lblSeleccioneElNmero.setBounds(10, 106, 224, 39);
		panel.add(lblSeleccioneElNmero);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Consolas", Font.BOLD, 14));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"}));
		comboBox_1.setBounds(244, 109, 73, 33);
		panel.add(comboBox_1);
		
		JLabel lblSeleccioneLasBebida = new JLabel("Seleccione las bebida:");
		lblSeleccioneLasBebida.setFont(new Font("Consolas", Font.BOLD, 12));
		lblSeleccioneLasBebida.setBounds(10, 158, 162, 39);
		panel.add(lblSeleccioneLasBebida);
		
		JLabel lblSeleccioneElNmero_1 = new JLabel("Seleccione el número de bebidas:");
		lblSeleccioneElNmero_1.setFont(new Font("Consolas", Font.BOLD, 12));
		lblSeleccioneElNmero_1.setBounds(10, 208, 224, 39);
		panel.add(lblSeleccioneElNmero_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Consolas", Font.BOLD, 14));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Seleccione...", "", "BEBIDAS NATURALES (Granizadas):", "- Limonada", "- Naranjada", "- Mandarinada", "- Maracumango", "- Té tropical de Jamaica", "- Mango Piña Cereza", "- Limonada de  Yerbabuena", "- Mango Yerbabuena", "- Limonada de Patilla", "- Limonada de Coco", "", "BEBIDAS EMBOTELLADAS:", "- Agua", "- Agua con Gas", "- Gaseosa Personal", "- Soda", "- Te Hatsu", "- Hatsu Soda", "", "CERVEZAS:", "- Club Colombia", "- Corona", "- Coronita", "- Heineken"}));
		comboBox_2.setBounds(181, 156, 556, 39);
		panel.add(comboBox_2);
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setFont(new Font("Consolas", Font.BOLD, 14));
		comboBox_1_1.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"}));
		comboBox_1_1.setBounds(244, 209, 73, 33);
		panel.add(comboBox_1_1);
		
		JButton btnNewButton = new JButton("Agregar plato a la comanda");
		btnNewButton.setFont(new Font("Consolas", Font.BOLD, 14));
		btnNewButton.setBounds(421, 109, 263, 39);
		panel.add(btnNewButton);
		
		JButton btnAgregarBebidaA = new JButton("Agregar bebida a la comanda");
		btnAgregarBebidaA.setFont(new Font("Consolas", Font.BOLD, 14));
		btnAgregarBebidaA.setBounds(421, 207, 263, 39);
		panel.add(btnAgregarBebidaA);
		
		JList list = new JList();
		list.setBounds(10, 334, 492, 172);
		panel.add(list);
		
		JLabel lblSeleccioneElNmero_1_1 = new JLabel("Pedido a enviar a cocina:");
		lblSeleccioneElNmero_1_1.setFont(new Font("Consolas", Font.BOLD, 14));
		lblSeleccioneElNmero_1_1.setBounds(146, 295, 208, 39);
		panel.add(lblSeleccioneElNmero_1_1);
		
		JLabel lblSeleccioneLaMesa = new JLabel("Seleccione la mesa:");
		lblSeleccioneLaMesa.setFont(new Font("Consolas", Font.BOLD, 12));
		lblSeleccioneLaMesa.setBounds(10, 258, 148, 39);
		panel.add(lblSeleccioneLaMesa);
		
		JComboBox comboBox_1_1_1 = new JComboBox();
		comboBox_1_1_1.setFont(new Font("Consolas", Font.BOLD, 14));
		comboBox_1_1_1.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
		comboBox_1_1_1.setBounds(244, 253, 73, 33);
		panel.add(comboBox_1_1_1);
		
		JButton btnRemoverPedidoDe = new JButton("Remover pedido de la comanda");
		btnRemoverPedidoDe.setToolTipText("Primero seleccione el pedido específico en la lista para luego remover");
		btnRemoverPedidoDe.setFont(new Font("Consolas", Font.BOLD, 14));
		btnRemoverPedidoDe.setBounds(113, 517, 263, 39);
		panel.add(btnRemoverPedidoDe);
		
		textField = new JTextField();
		textField.setBounds(651, 295, 86, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblTotalAPagar = new JLabel("Total a pagar:\r\n");
		lblTotalAPagar.setFont(new Font("Consolas", Font.BOLD, 12));
		lblTotalAPagar.setBounds(537, 296, 104, 39);
		panel.add(lblTotalAPagar);
		
		JButton btnGenerarComandaEn = new JButton("Generar comanda en PDF");
		btnGenerarComandaEn.setToolTipText("Primero seleccione el pedido específico en la lista para luego remover");
		btnGenerarComandaEn.setFont(new Font("Consolas", Font.BOLD, 14));
		btnGenerarComandaEn.setBounds(520, 412, 217, 39);
		panel.add(btnGenerarComandaEn);
	}
}