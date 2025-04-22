import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ComandasCocina {
    private JFrame frame;
    private JTextField textFieldTotal;
    private DefaultListModel<String> listModel;
    private List<Pedido> pedidos;
    private DecimalFormat df = new DecimalFormat("#,###.00");
    
    // Clase interna para manejar los pedidos
    class Pedido {
        String tipo;
        String descripcion;
        int cantidad;
        double precioUnitario;
        int mesa;

        public Pedido(String tipo, String descripcion, int cantidad, double precioUnitario, int mesa) {
            this.tipo = tipo;
            this.descripcion = descripcion;
            this.cantidad = cantidad;
            this.precioUnitario = precioUnitario;
            this.mesa = mesa;
        }

        public double getTotal() {
            return cantidad * precioUnitario;
        }

        @Override
        public String toString() {
            return String.format("Mesa %02d: %dx %s - $%s", 
                mesa, cantidad, descripcion, df.format(getTotal()));
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ComandasCocina window = new ComandasCocina();
                window.frame.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                    "Error al iniciar la aplicación: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });
    }

    public ComandasCocina() {
        pedidos = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        configurarFramePrincipal();
        JPanel panel = crearPanelPrincipal();
        
        agregarTitulo(panel);
        agregarSeccionPlatos(panel);
        agregarSeccionBebidas(panel);
        agregarSeccionMesa(panel);
        agregarListaPedidos(panel);
        agregarTotal(panel);
        agregarBotonesAcciones(panel);
    }

    private void configurarFramePrincipal() {
        frame = new JFrame();
        frame.setBounds(100, 100, 780, 624);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
    }

    private JPanel crearPanelPrincipal() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 140, 0));
        panel.setBounds(10, 11, 764, 585);
        panel.setLayout(null);
        frame.getContentPane().add(panel);
        return panel;
    }

    private void agregarTitulo(JPanel panel) {
        JLabel lblTitulo = new JLabel("Sistema Generación Comandas Cocina");
        lblTitulo.setBounds(196, 0, 384, 78);
        lblTitulo.setFont(new Font("Consolas", Font.BOLD, 20));
        panel.add(lblTitulo);
    }

    private void agregarSeccionPlatos(JPanel panel) {
        // Selección de plato
        agregarLabel(panel, "Seleccione el plato:", 10, 64, 148, 39);
        
        JComboBox<String> comboPlatos = new JComboBox<>(getPlatosMenu());
        comboPlatos.setFont(new Font("Consolas", Font.BOLD, 14));
        comboPlatos.setBounds(181, 65, 556, 33);
        panel.add(comboPlatos);
        
        // Cantidad de platos
        agregarLabel(panel, "Seleccione el número de platos:", 10, 106, 224, 39);
        
        JComboBox<String> comboCantidadPlatos = new JComboBox<>(getCantidades());
        comboCantidadPlatos.setFont(new Font("Consolas", Font.BOLD, 14));
        comboCantidadPlatos.setBounds(244, 109, 73, 33);
        panel.add(comboCantidadPlatos);
        
        // Botón agregar plato
        JButton btnAgregarPlato = new JButton("Agregar plato a la comanda");
        btnAgregarPlato.setFont(new Font("Consolas", Font.BOLD, 14));
        btnAgregarPlato.setBounds(421, 109, 263, 39);
        btnAgregarPlato.addActionListener(e -> agregarPedido(comboPlatos, comboCantidadPlatos, "Plato"));
        panel.add(btnAgregarPlato);
    }

    private void agregarSeccionBebidas(JPanel panel) {
        // Selección de bebida
        agregarLabel(panel, "Seleccione las bebida:", 10, 158, 162, 39);
        
        JComboBox<String> comboBebidas = new JComboBox<>(getBebidasMenu());
        comboBebidas.setFont(new Font("Consolas", Font.BOLD, 14));
        comboBebidas.setBounds(181, 156, 556, 39);
        panel.add(comboBebidas);
        
        // Cantidad de bebidas
        agregarLabel(panel, "Seleccione el número de bebidas:", 10, 208, 224, 39);
        
        JComboBox<String> comboCantidadBebidas = new JComboBox<>(getCantidades());
        comboCantidadBebidas.setFont(new Font("Consolas", Font.BOLD, 14));
        comboCantidadBebidas.setBounds(244, 209, 73, 33);
        panel.add(comboCantidadBebidas);
        
        // Botón agregar bebida
        JButton btnAgregarBebida = new JButton("Agregar bebida a la comanda");
        btnAgregarBebida.setFont(new Font("Consolas", Font.BOLD, 14));
        btnAgregarBebida.setBounds(421, 207, 263, 39);
        btnAgregarBebida.addActionListener(e -> agregarPedido(comboBebidas, comboCantidadBebidas, "Bebida"));
        panel.add(btnAgregarBebida);
    }

    private void agregarSeccionMesa(JPanel panel) {
        agregarLabel(panel, "Seleccione la mesa:", 10, 258, 148, 39);
        
        JComboBox<String> comboMesa = new JComboBox<>(getMesas());
        comboMesa.setFont(new Font("Consolas", Font.BOLD, 14));
        comboMesa.setBounds(244, 253, 73, 33);
        panel.add(comboMesa);
    }

    private void agregarListaPedidos(JPanel panel) {
        agregarLabel(panel, "Pedido a enviar a cocina:", 146, 295, 208, 39);
        
        listModel = new DefaultListModel<>();
        JList<String> listaPedidos = new JList<>(listModel);
        listaPedidos.setBounds(10, 334, 492, 172);
        panel.add(listaPedidos);
        
        // Botón remover pedido
        JButton btnRemoverPedido = new JButton("Remover pedido de la comanda");
        btnRemoverPedido.setToolTipText("Primero seleccione el pedido específico en la lista para luego remover");
        btnRemoverPedido.setFont(new Font("Consolas", Font.BOLD, 14));
        btnRemoverPedido.setBounds(113, 517, 263, 39);
        btnRemoverPedido.addActionListener(e -> removerPedido(listaPedidos));
        panel.add(btnRemoverPedido);
    }

    private void agregarTotal(JPanel panel) {
        agregarLabel(panel, "Total a pagar:", 537, 296, 104, 39);
        
        textFieldTotal = new JTextField();
        textFieldTotal.setBounds(651, 295, 86, 28);
        textFieldTotal.setEditable(false);
        panel.add(textFieldTotal);
    }

    private void agregarBotonesAcciones(JPanel panel) {
        JButton btnGenerarPDF = new JButton("Generar comanda en PDF");
        btnGenerarPDF.setFont(new Font("Consolas", Font.BOLD, 14));
        btnGenerarPDF.setBounds(520, 412, 217, 39);
        btnGenerarPDF.addActionListener(e -> generarPDF());
        panel.add(btnGenerarPDF);
    }

    private void agregarLabel(JPanel panel, String texto, int x, int y, int width, int height) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Consolas", Font.BOLD, 12));
        label.setBounds(x, y, width, height);
        panel.add(label);
    }

    private String[] getPlatosMenu() {
        return new String[] {
            "Seleccione...", "",
            "ENTRADAS (Para abrir tu apetito):",
            "- Langostinos Tempura    $27500",
            "- Langostinos Samurai    $33.500",
            "- Palmitos Crunchy Tokio  $31.100", "",
            "SUSHI BURRITOS:",
            "- Karate Chicken     $41.700",
            "- American Chicken    $41.700",
            "- Chancho Power     $45.200",
            "- El más Chido    $45.200",
            "- Veggie    $46.500",
            "- El Kappo (De la casa)    $48.900",
            "- EBI    $48.900",
            "- EL Tuna Salmón    40.900",
            "- Ninja Crunch     $40.900",
            "- Tigre Siberiano   $39.800", "",
            "POKE BOWLS:",
            "- Carnívoro      $34.600",
            "- Katana Bakana     $37.800",
            "- Monte Fuji     $37.800",
            "- Salmón Emperador    $39.800", "", 
            "MENÚ INFANTIL:",
            "- Nuggets de pollo    $20.800",
            "-Picadita Junior     $21.200"
        };
    }

    private String[] getBebidasMenu() {
        return new String[] {
            "Seleccione...", "",
            "BEBIDAS NATURALES (Granizadas):",
            "- Limonada  $6.800",
            "- Naranjada  $7.000",
            "- Mandarinada  $7.000",
            "- Maracumango  $8.000",
            "- Té tropical de Jamaica  $8.000",
            "- Mango Piña Cereza  $8.000",
            "- Limonada de  Yerbabuena  $8.000",
            "- Mango Yerbabuena  $8.000",
            "- Limonada de Patilla  $8.000",
            "- Limonada de Coco  $11.000", "",
            "BEBIDAS EMBOTELLADAS:",
            "- Agua  $4.500",
            "- Agua con Gas  $4.500",
            "- Gaseosa Personal  $4.800",
            "- Soda  $4.800",
            "- Te Hatsu  $7.500",
            "- Hatsu Soda  $6.500", "",
            "CERVEZAS:",
            "- Club Colombia  $7.500",
            "- Corona  $8.500",
            "- Coronita  $7.000",
            "- Heineken  $7.500"
        };
    }

    private String[] getCantidades() {
        String[] cantidades = new String[30];
        for (int i = 0; i < 30; i++) {
            cantidades[i] = String.format("%02d", i + 1);
        }
        return cantidades;
    }

    private String[] getMesas() {
        String[] mesas = new String[20];
        for (int i = 0; i < 20; i++) {
            mesas[i] = String.format("%02d", i + 1);
        }
        return mesas;
    }

    private void agregarPedido(JComboBox<String> comboItem, JComboBox<String> comboCantidad, String tipo) {
        try {
            String seleccion = (String) comboItem.getSelectedItem();
            if (seleccion == null || seleccion.isEmpty() || seleccion.equals("Seleccione...")) {
                throw new Exception("Por favor seleccione un " + tipo.toLowerCase());
            }
            
            // Extraer precio del texto seleccionado
            double precio = extraerPrecio(seleccion);
            if (precio <= 0) {
                throw new Exception("No se pudo determinar el precio del " + tipo.toLowerCase());
            }
            
            int cantidad = Integer.parseInt((String) comboCantidad.getSelectedItem());
            int mesa = 1; // Por defecto, deberías obtener esto de un comboBox de mesas
            
            Pedido pedido = new Pedido(tipo, seleccion.split("\\$")[0].trim(), cantidad, precio, mesa);
            pedidos.add(pedido);
            listModel.addElement(pedido.toString());
            actualizarTotal();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, 
                "Error al agregar pedido: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double extraerPrecio(String texto) {
        try {
            String[] partes = texto.split("\\$");
            if (partes.length < 2) return 0;
            
            String precioStr = partes[1].trim().replace(".", "").replace(",", ".");
            return Double.parseDouble(precioStr);
        } catch (Exception e) {
            return 0;
        }
    }

    private void removerPedido(JList<String> listaPedidos) {
        try {
            int selectedIndex = listaPedidos.getSelectedIndex();
            if (selectedIndex == -1) {
                throw new Exception("Por favor seleccione un pedido para remover");
            }
            
            pedidos.remove(selectedIndex);
            listModel.remove(selectedIndex);
            actualizarTotal();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, 
                "Error al remover pedido: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTotal() {
        double total = pedidos.stream().mapToDouble(Pedido::getTotal).sum();
        textFieldTotal.setText("$" + df.format(total));
    }

    private void generarPDF() {
        try {
            if (pedidos.isEmpty()) {
                throw new Exception("No hay pedidos para generar el PDF");
            }

            // Crear un JFileChooser para seleccionar la ubicación de guardado
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Comanda como PDF");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setSelectedFile(new java.io.File("comanda.pdf")); // Nombre de archivo por defecto

            int userSelection = fileChooser.showSaveDialog(frame);
            if (userSelection != JFileChooser.APPROVE_OPTION) {
                return; // Si el usuario cancela, no hacer nada
            }

            java.io.File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getName().endsWith(".pdf")) {
                fileToSave = new java.io.File(fileToSave.getAbsolutePath() + ".pdf");
            }

            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 16);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 750);
                contentStream.showText("Comanda de Cocina");
                contentStream.endText();

                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);

                for (Pedido pedido : pedidos) {
                    contentStream.showText(pedido.toString());
                    contentStream.newLineAtOffset(0, -15); // Mover hacia abajo para el siguiente texto
                }

                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Total: $" + df.format(pedidos.stream().mapToDouble(Pedido::getTotal).sum()));
                contentStream.endText();
            }

            document.save(fileToSave);
            document.close();

            JOptionPane.showMessageDialog(frame,
                    "PDF generado exitosamente en " + fileToSave.getAbsolutePath(),
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame,
                    "Error al generar PDF: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}