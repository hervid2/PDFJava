import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Calendar;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

public class FacturaPDF {

    private JFrame frame;
    private final JPanel panel = new JPanel();
    private JTable table;
    private JTextField totalField;
    private JTextField numeroFacturaField;
    private JTextArea clienteTextArea;
    private JSpinner fechaSpinner;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FacturaPDF window = new FacturaPDF();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FacturaPDF() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Generador de Facturas Profesional");
        frame.setBounds(100, 100, 721, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        // Panel de encabezado
        panel.setBackground(new Color(59, 89, 152));
        panel.setBounds(0, 0, 834, 100);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel tituloLabel = new JLabel("FACTURA DE VENTA No.");
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 28));
        tituloLabel.setBounds(20, 20, 350, 60);
        panel.add(tituloLabel);
        
        numeroFacturaField = new JTextField();
        numeroFacturaField.setFont(new Font("Arial", Font.BOLD, 28));
        numeroFacturaField.setBounds(458, 26, 150, 50);
        panel.add(numeroFacturaField);
        numeroFacturaField.setColumns(10);
        
        // Sección de datos
        JLabel fechaLabel = new JLabel("Fecha:");
        fechaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        fechaLabel.setBounds(20, 120, 80, 20);
        frame.getContentPane().add(fechaLabel);
        
        fechaSpinner = new JSpinner();
        fechaSpinner.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
        fechaSpinner.setEditor(new JSpinner.DateEditor(fechaSpinner, "dd/MM/yyyy"));
        fechaSpinner.setBounds(100, 120, 150, 25);
        frame.getContentPane().add(fechaSpinner);
        
        JLabel clienteLabel = new JLabel("Cliente:");
        clienteLabel.setFont(new Font("Arial", Font.BOLD, 14));
        clienteLabel.setBounds(20, 160, 80, 20);
        frame.getContentPane().add(clienteLabel);
        
        clienteTextArea = new JTextArea();
        clienteTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        clienteTextArea.setBounds(100, 160, 400, 25);
        frame.getContentPane().add(clienteTextArea);
        
        // Configuración de la tabla
        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{"CANTIDAD", "DESCRIPCI\u00D3N", "PRECIO UNITARIO", "PRECIO TOTAL"},
        		{null, null, null, null},
        		{null, null, null, null},
        		{null, null, null, null},
        		{null, null, null, null},
        		{null, null, null, null},
        		{null, "", null, null},
        		{null, null, null, null},
        		{null, "", null, null},
        		{null, null, null, null},
        	},
        	new String[] {
        		"Cantidad", "Descripci\u00F3n", "Precio Unitario", "Precio Total"
        	}
        ));
        table.getColumnModel().getColumn(1).setPreferredWidth(273);
        table.getColumnModel().getColumn(2).setPreferredWidth(106);
        table.getColumnModel().getColumn(3).setPreferredWidth(90);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setBounds(30, 195, 568, 350);
        frame.getContentPane().add(table);
        
        // Listener para cálculo automático
        table.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                calcularTotal();
            }
        });
        
        // Sección de total
        JLabel totalLabel = new JLabel("TOTAL:");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setBounds(421, 569, 80, 30);
        frame.getContentPane().add(totalLabel);
        
        totalField = new JTextField();
        totalField.setEditable(false);
        totalField.setFont(new Font("Arial", Font.BOLD, 16));
        totalField.setBounds(531, 570, 150, 30);
        frame.getContentPane().add(totalField);
        
        // Botón para generar PDF
        JButton generarPDFButton = new JButton("Generar PDF");
        generarPDFButton.setFont(new Font("Arial", Font.BOLD, 14));
        generarPDFButton.setBounds(169, 612, 150, 40);
        generarPDFButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generarPDFConBordes();
            }
        });
        frame.getContentPane().add(generarPDFButton);
    }
    
    private void calcularTotal() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        double total = 0.0;
        
        for (int i = 1; i < model.getRowCount(); i++) {
            try {
                Object cantidadObj = model.getValueAt(i, 0);
                Object precioObj = model.getValueAt(i, 2);
                
                if (cantidadObj != null && precioObj != null && 
                    !cantidadObj.toString().isEmpty() && !precioObj.toString().isEmpty()) {
                    
                    int cantidad = Integer.parseInt(cantidadObj.toString());
                    double precioUnitario = Double.parseDouble(precioObj.toString());
                    double precioTotal = cantidad * precioUnitario;
                    
                    model.setValueAt(String.format("$%,.2f", precioTotal), i, 3);
                    total += precioTotal;
                }
            } catch (NumberFormatException e) {
                // Ignorar celdas con formato incorrecto
            }
        }
        
        totalField.setText(String.format("$%,.2f", total));
    }
    
    private void generarPDFConBordes() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Factura como PDF");
        fileChooser.setSelectedFile(new File("Factura_" + numeroFacturaField.getText() + ".pdf"));
        
        if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            
            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    // Configuración inicial
                    float margin = 40;
                    float yPosition = page.getMediaBox().getHeight() - margin;
                    float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
                    
                    // Título
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 18);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin, yPosition);
                    contentStream.showText("FACTURA DE VENTA N° " + numeroFacturaField.getText());
                    contentStream.endText();
                    yPosition -= 30;
                    
                    // Información de la factura
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String fecha = sdf.format(((Date) fechaSpinner.getValue()));
                    
                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin, yPosition);
                    contentStream.showText("Fecha: " + fecha);
                    contentStream.endText();
                    
                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin, yPosition - 20);
                    contentStream.showText("Cliente: " + clienteTextArea.getText());
                    contentStream.endText();
                    yPosition -= 50;
                    
                    // Dibujar tabla con bordes
                    float[] columnWidths = {80, 200, 70, 80}; // Ajusta los anchos según sea necesario
                    float tableHeight = 20 * (table.getRowCount() + 1);
                    
                    // Dibujar bordes de la tabla
                    contentStream.setLineWidth(1f);
                    
                    // Borde exterior
                    contentStream.moveTo(margin, yPosition);
                    contentStream.lineTo(margin + tableWidth, yPosition);
                    contentStream.lineTo(margin + tableWidth, yPosition - tableHeight);
                    contentStream.lineTo(margin, yPosition - tableHeight);
                    contentStream.closePath();
                    contentStream.stroke();
                    
                    // Encabezados de la tabla
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
                    float nextX = margin;
                    
                    // Dibujar líneas verticales
                    for (int i = 0; i <= columnWidths.length; i++) {
                        contentStream.moveTo(nextX, yPosition);
                        contentStream.lineTo(nextX, yPosition - tableHeight);
                        contentStream.stroke();
                        if (i < columnWidths.length) {
                            nextX += columnWidths[i];
                        }
                    }
                    
                    // Dibujar líneas horizontales
                    float nextY = yPosition - 20;
                    contentStream.moveTo(margin, nextY);
                    contentStream.lineTo(margin + tableWidth, nextY);
                    contentStream.stroke();
                    
                    // Texto de encabezados
                    String[] headers = {"Cantidad", "Descripción", "P. Unitario", "Total"};
                    nextX = margin + 5;
                    
                    for (int i = 0; i < headers.length; i++) {
                        contentStream.beginText();
                        contentStream.newLineAtOffset(nextX, yPosition - 15);
                        contentStream.showText(headers[i]);
                        contentStream.endText();
                        nextX += columnWidths[i];
                    }
                    
                    // Datos de la tabla
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    nextY -= 20;
                    
                    for (int i = 1; i < model.getRowCount(); i++) {
                        boolean filaVacia = true;
                        
                        // Verificar si la fila tiene datos
                        for (int j = 0; j < model.getColumnCount(); j++) {
                            if (model.getValueAt(i, j) != null && !model.getValueAt(i, j).toString().isEmpty()) {
                                filaVacia = false;
                                break;
                            }
                        }
                        
                        if (!filaVacia) {
                            nextX = margin + 5;
                            
                            for (int j = 0; j < model.getColumnCount(); j++) {
                                Object value = model.getValueAt(i, j);
                                contentStream.beginText();
                                contentStream.newLineAtOffset(nextX, nextY);
                                contentStream.showText(value != null ? value.toString() : "");
                                contentStream.endText();
                                nextX += columnWidths[j];
                            }
                            
                            // Dibujar línea horizontal entre filas
                            contentStream.moveTo(margin, nextY - 5);
                            contentStream.lineTo(margin + tableWidth, nextY - 5);
                            contentStream.stroke();
                            
                            nextY -= 20;
                        }
                    }
                    
                    // Total
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin + tableWidth - 100, nextY - 180); // Ajusta la posición del total
                    contentStream.showText("TOTAL: " + totalField.getText());
                    contentStream.endText();
                }
                
                // Guardar el PDF
                document.save(archivo);
                
                JOptionPane.showMessageDialog(frame, 
                    "Factura generada exitosamente en:\n" + archivo.getAbsolutePath(),
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, 
                    "Error al generar el PDF:\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}