import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BarberShop {

    private JFrame frame;
    private JTextField textFieldNombre;
    private JTextField textFieldTotal;
    private JCheckBox corteCheckBox;
    private JCheckBox afeitadoCheckBox;
    private JCheckBox tinteCheckBox;
    private JCheckBox barbaCheckBox;
    private JCheckBox keratinaCheckBox;
    private JComboBox<String> comboBoxMetodoPago;

    // Precios de los servicios
    private static final float PRECIO_CORTE = 20000;
    private static final float PRECIO_AFEITADO = 18000;
    private static final float PRECIO_TINTE = 60000;
    private static final float PRECIO_BARBA = 15000;
    private static final float PRECIO_KERATINA = 150000;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BarberShop window = new BarberShop();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public BarberShop() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 628, 607);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setBounds(0, 0, 612, 568);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.RED);
        panel_1.setBounds(0, -12, 634, 147);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(BarberShop.class.getResource("/img/peluqueria (1).png")));
        lblNewLabel_2.setBounds(56, 24, 83, 112);
        panel_1.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon(BarberShop.class.getResource("/img/corte-de-pelo.png")));
        lblNewLabel_3.setBounds(504, 37, 83, 85);
        panel_1.add(lblNewLabel_3);
        
        JLabel lblNewLabel = new JLabel("Venezuelan BarberShop");
        lblNewLabel.setBounds(142, 49, 347, 73);
        panel_1.add(lblNewLabel);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        
        JLabel lblGeneracinDeFactura = new JLabel("Generación de factura de servicios");
        lblGeneracinDeFactura.setForeground(Color.WHITE);
        lblGeneracinDeFactura.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
        lblGeneracinDeFactura.setBounds(109, 133, 400, 73);
        panel.add(lblGeneracinDeFactura);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(67, 193, 473, 2);
        panel.add(separator);
        
        JLabel lblNombreCliente = new JLabel("Nombre Cliente:");
        lblNombreCliente.setForeground(Color.WHITE);
        lblNombreCliente.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        lblNombreCliente.setBounds(29, 227, 122, 42);
        panel.add(lblNombreCliente);
        
        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(188, 239, 321, 20);
        panel.add(textFieldNombre);
        textFieldNombre.setColumns(10);
        
        JLabel lblServiciosPrestados = new JLabel("Servicios prestados:");
        lblServiciosPrestados.setForeground(Color.WHITE);
        lblServiciosPrestados.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        lblServiciosPrestados.setBounds(29, 280, 129, 42);
        panel.add(lblServiciosPrestados);
        
        JLabel lblCorte = new JLabel("Corte");
        lblCorte.setForeground(Color.WHITE);
        lblCorte.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
        lblCorte.setBounds(198, 280, 44, 42);
        panel.add(lblCorte);
        
        corteCheckBox = new JCheckBox("corteCheckBox");
        corteCheckBox.setBounds(239, 292, 20, 20);
        panel.add(corteCheckBox);
        
        JLabel lblAfeitado = new JLabel("Afeitado");
        lblAfeitado.setForeground(Color.WHITE);
        lblAfeitado.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
        lblAfeitado.setBounds(265, 280, 62, 42);
        panel.add(lblAfeitado);
        
        afeitadoCheckBox = new JCheckBox("afeitadoCheckBox");
        afeitadoCheckBox.setBounds(315, 292, 20, 20);
        panel.add(afeitadoCheckBox);
        
        JLabel lblTinte = new JLabel("Tinte");
        lblTinte.setForeground(Color.WHITE);
        lblTinte.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
        lblTinte.setBounds(351, 280, 35, 42);
        panel.add(lblTinte);
        
        tinteCheckBox = new JCheckBox("tinteCheckBox");
        tinteCheckBox.setBounds(382, 292, 20, 20);
        panel.add(tinteCheckBox);
        
        JLabel lblBarba = new JLabel("Barba");
        lblBarba.setForeground(Color.WHITE);
        lblBarba.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
        lblBarba.setBounds(417, 280, 44, 42);
        panel.add(lblBarba);
        
        barbaCheckBox = new JCheckBox("barbaCheckBox");
        barbaCheckBox.setBounds(452, 291, 20, 20);
        panel.add(barbaCheckBox);
        
        JLabel lblKeratina = new JLabel("Keratina");
        lblKeratina.setForeground(Color.WHITE);
        lblKeratina.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
        lblKeratina.setBounds(486, 280, 54, 42);
        panel.add(lblKeratina);
        
        keratinaCheckBox = new JCheckBox("keratinaCheckBox");
        keratinaCheckBox.setBounds(535, 292, 20, 20);
        panel.add(keratinaCheckBox);
        
        JLabel lblMtodoDePago = new JLabel("Método de pago:");
        lblMtodoDePago.setForeground(Color.WHITE);
        lblMtodoDePago.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        lblMtodoDePago.setBounds(29, 343, 129, 42);
        panel.add(lblMtodoDePago);
        
        comboBoxMetodoPago = new JComboBox<>();
        comboBoxMetodoPago.setModel(new DefaultComboBoxModel(new String[] {"Seleccione...", "Tarjeta de crédito", "Efectivo", "Tarjeta débito", "Nequi", "DaviPlata"}));
        comboBoxMetodoPago.setBounds(188, 354, 180, 22);
        panel.add(comboBoxMetodoPago);
        
        JTextArea txtrListaDePrecios = new JTextArea();
        txtrListaDePrecios.setForeground(Color.WHITE);
        txtrListaDePrecios.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        txtrListaDePrecios.setText("Lista de precios:\r\n\r\nCorte:        $20.000\r\nAfeitado:   $18.000\r\nTinte:        $60.000\r\nBarba:        $15.000\r\nKeratina:   $150.000");
        txtrListaDePrecios.setBackground(Color.BLUE);
        txtrListaDePrecios.setBounds(397, 338, 174, 161);
        panel.add(txtrListaDePrecios);
        
        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setForeground(Color.WHITE);
        lblTotal.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        lblTotal.setBounds(29, 430, 44, 42);
        panel.add(lblTotal);
        
        textFieldTotal = new JTextField();
        textFieldTotal.setEditable(false);
        textFieldTotal.setBounds(103, 437, 99, 30);
        panel.add(textFieldTotal);
        textFieldTotal.setColumns(10);
        
        JButton btnCalcular = new JButton("Calcular Total");
        btnCalcular.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        btnCalcular.setBounds(225, 430, 143, 42);
        btnCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularTotal();
            }
        });
        panel.add(btnCalcular);
        
        JButton btnNewButton = new JButton("Generar Factura");
        btnNewButton.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        btnNewButton.setBounds(225, 497, 143, 42);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generarFacturaPDF();
            }
        });
        panel.add(btnNewButton);
    }
    
    private void calcularTotal() {
        float total = 0;
        
        if (corteCheckBox.isSelected()) {
            total += PRECIO_CORTE;
        }
        if (afeitadoCheckBox.isSelected()) {
            total += PRECIO_AFEITADO;
        }
        if (tinteCheckBox.isSelected()) {
            total += PRECIO_TINTE;
        }
        if (barbaCheckBox.isSelected()) {
            total += PRECIO_BARBA;
        }
        if (keratinaCheckBox.isSelected()) {
            total += PRECIO_KERATINA;
        }
        
        textFieldTotal.setText(String.format("$%,.0f", total));
    }
    
    private void generarFacturaPDF() {
        // Validar campos obligatorios
        if (textFieldNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Por favor ingrese el nombre del cliente", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (comboBoxMetodoPago.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(frame, "Por favor seleccione un método de pago", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (textFieldTotal.getText().isEmpty() || textFieldTotal.getText().equals("$0")) {
            JOptionPane.showMessageDialog(frame, "Por favor calcule el total primero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Crear el documento PDF
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            
            // Obtener la fecha y hora actual
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String fechaHora = ahora.format(formatter);
            
            // Crear el contenido del PDF
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Venezuelan BarberShop");
                contentStream.endText();
                
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 670);
                contentStream.showText("Factura de servicios - " + fechaHora);
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 640);
                contentStream.showText("Cliente: " + textFieldNombre.getText());
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 610);
                contentStream.showText("Servicios:");
                contentStream.endText();
                
                int y = 580;
                if (corteCheckBox.isSelected()) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(120, y);
                    contentStream.showText("- Corte de cabello: $" + String.format("%,.0f", PRECIO_CORTE));
                    contentStream.endText();
                    y -= 30;
                }
                if (afeitadoCheckBox.isSelected()) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(120, y);
                    contentStream.showText("- Afeitado: $" + String.format("%,.0f", PRECIO_AFEITADO));
                    contentStream.endText();
                    y -= 30;
                }
                if (tinteCheckBox.isSelected()) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(120, y);
                    contentStream.showText("- Tinte: $" + String.format("%,.0f", PRECIO_TINTE));
                    contentStream.endText();
                    y -= 30;
                }
                if (barbaCheckBox.isSelected()) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(120, y);
                    contentStream.showText("- Barba: $" + String.format("%,.0f", PRECIO_BARBA));
                    contentStream.endText();
                    y -= 30;
                }
                if (keratinaCheckBox.isSelected()) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(120, y);
                    contentStream.showText("- Keratina: $" + String.format("%,.0f", PRECIO_KERATINA));
                    contentStream.endText();
                    y -= 30;
                }
                
                contentStream.beginText();
                contentStream.newLineAtOffset(100, y - 20);
                contentStream.showText("Método de pago: " + comboBoxMetodoPago.getSelectedItem());
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(100, y - 50);
                contentStream.showText("TOTAL: " + textFieldTotal.getText());
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(100, y - 100);
                contentStream.showText("¡Gracias por su preferencia!");
                contentStream.endText();
            }
            
            // Guardar el PDF
            String nombreArchivo = "Factura_" + textFieldNombre.getText().replace(" ", "_") + "_" + 
                                 ahora.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".pdf";
            document.save(nombreArchivo);
            document.close();
            
            JOptionPane.showMessageDialog(frame, "Factura generada exitosamente: " + nombreArchivo, 
                                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error al generar el PDF: " + ex.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
