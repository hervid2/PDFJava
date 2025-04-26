import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class AseguradoraAnimal {

    private JFrame frame;
    private JTextField textField_1;
    private JTextField textField;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JPanel panel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AseguradoraAnimal window = new AseguradoraAnimal();
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
    public AseguradoraAnimal() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 579, 683);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        panel = new JPanel();
        panel.setBackground(new Color(100, 149, 237));
        panel.setBounds(0, 0, 563, 644);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Seguros y pólizas para Animales");
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        lblNewLabel.setBounds(158, 51, 374, 46);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(AseguradoraAnimal.class.getResource("/img/gato.png")));
        lblNewLabel_1.setBounds(10, 11, 138, 152);
        panel.add(lblNewLabel_1);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(149, 95, 383, 2);
        panel.add(separator);
        
        JLabel lblAquPuedeGenerar = new JLabel("Aquí puede generar un contrato de póliza todoriesgo para mascotas");
        lblAquPuedeGenerar.setFont(new Font("Verdana", Font.PLAIN, 11));
        lblAquPuedeGenerar.setBounds(149, 106, 398, 46);
        panel.add(lblAquPuedeGenerar);
        
        JLabel lblInformacinDelAnimal = new JLabel("Información del animal:");
        lblInformacinDelAnimal.setFont(new Font("Verdana", Font.BOLD, 13));
        lblInformacinDelAnimal.setBounds(10, 163, 179, 46);
        panel.add(lblInformacinDelAnimal);
        
        JLabel lblNombre = new JLabel("Nombre: ");
        lblNombre.setFont(new Font("Verdana", Font.BOLD, 11));
        lblNombre.setBounds(10, 203, 67, 33);
        panel.add(lblNombre);
        
        JLabel lblEspecie = new JLabel("Especie:\r\n");
        lblEspecie.setFont(new Font("Verdana", Font.BOLD, 11));
        lblEspecie.setBounds(295, 203, 58, 33);
        panel.add(lblEspecie);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(363, 210, 173, 20);
        panel.add(textField_1);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(72, 210, 173, 20);
        panel.add(textField);
        
        JLabel lblRaza = new JLabel("Raza:");
        lblRaza.setFont(new Font("Verdana", Font.BOLD, 11));
        lblRaza.setBounds(10, 238, 43, 33);
        panel.add(lblRaza);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(72, 245, 173, 20);
        panel.add(textField_2);
        
        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setFont(new Font("Verdana", Font.BOLD, 11));
        lblEdad.setBounds(295, 238, 43, 33);
        panel.add(lblEdad);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(336, 245, 53, 20);
        panel.add(textField_3);
        
        JLabel lblPesokg = new JLabel("Peso (kg):");
        lblPesokg.setFont(new Font("Verdana", Font.BOLD, 11));
        lblPesokg.setBounds(408, 238, 72, 33);
        panel.add(lblPesokg);
        
        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(478, 245, 57, 20);
        panel.add(textField_4);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(10, 282, 537, 2);
        panel.add(separator_1);
        
        JLabel lblInformacinDelPropietario = new JLabel("Información del propietario:");
        lblInformacinDelPropietario.setFont(new Font("Verdana", Font.BOLD, 13));
        lblInformacinDelPropietario.setBounds(10, 282, 203, 46);
        panel.add(lblInformacinDelPropietario);
        
        JLabel lblNombre_1 = new JLabel("Nombre: ");
        lblNombre_1.setFont(new Font("Verdana", Font.BOLD, 11));
        lblNombre_1.setBounds(10, 321, 67, 33);
        panel.add(lblNombre_1);
        
        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(72, 328, 215, 20);
        panel.add(textField_5);
        
        textField_6 = new JTextField();
        textField_6.setColumns(10);
        textField_6.setBounds(399, 328, 133, 20);
        panel.add(textField_6);
        
        JLabel lblNombre_1_1 = new JLabel("Identificación:");
        lblNombre_1_1.setFont(new Font("Verdana", Font.BOLD, 11));
        lblNombre_1_1.setBounds(295, 321, 96, 33);
        panel.add(lblNombre_1_1);
        
        JLabel lblNombre_1_2 = new JLabel("Teléfono:");
        lblNombre_1_2.setFont(new Font("Verdana", Font.BOLD, 11));
        lblNombre_1_2.setBounds(10, 355, 67, 33);
        panel.add(lblNombre_1_2);
        
        textField_7 = new JTextField();
        textField_7.setColumns(10);
        textField_7.setBounds(72, 362, 133, 20);
        panel.add(textField_7);
        
        JSeparator separator_1_1 = new JSeparator();
        separator_1_1.setBounds(10, 399, 537, 2);
        panel.add(separator_1_1);
        
        JLabel lblNombre_1_2_1 = new JLabel("Selecciona un paquete: ");
        lblNombre_1_2_1.setFont(new Font("Verdana", Font.BOLD, 11));
        lblNombre_1_2_1.setBounds(10, 439, 155, 33);
        panel.add(lblNombre_1_2_1);
        
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setFont(new Font("Verdana", Font.PLAIN, 11));
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Seleccione...", "Básico ($25.900 + cuota peso/edad)", "Plus (32.900 + cuota peso/edad)", "Premium (39.900 + cuota peso/edad)"}));
        comboBox.setBounds(204, 444, 343, 22);
        panel.add(comboBox);
        
        JLabel lblNombre_1_2_1_1 = new JLabel("Selecciona rango peso/edad: ");
        lblNombre_1_2_1_1.setFont(new Font("Verdana", Font.BOLD, 11));
        lblNombre_1_2_1_1.setBounds(10, 469, 195, 33);
        panel.add(lblNombre_1_2_1_1);
        
        JComboBox<String> comboBox_1 = new JComboBox<>();
        comboBox_1.setModel(new DefaultComboBoxModel<>(new String[] {"Seleccione...", "0 a 8 años / peso normal", "0 a 8 años / obesidad declarada en revisión veterinaria", "9 a 14 años / peso normal", "9 a 14 años / obesidad declarada en revisión veterinaria", "14 años + / peso normal", "14 años + / obesidad declarada en revisión veterinaria"}));
        comboBox_1.setFont(new Font("Verdana", Font.PLAIN, 11));
        comboBox_1.setBounds(204, 477, 343, 22);
        panel.add(comboBox_1);
        
        JButton btnNewButton = new JButton("Generar contrato");
        btnNewButton.setFont(new Font("Verdana", Font.BOLD, 14));
        btnNewButton.setBounds(295, 551, 196, 33);
        
        // Agregar ActionListener al botón
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Validar que todos los campos estén completos
                if (textField.getText().isEmpty() || textField_1.getText().isEmpty() || 
                    textField_2.getText().isEmpty() || textField_3.getText().isEmpty() || 
                    textField_4.getText().isEmpty() || textField_5.getText().isEmpty() || 
                    textField_6.getText().isEmpty() || textField_7.getText().isEmpty()) {
                    
                    JOptionPane.showMessageDialog(frame, "Por favor complete todos los campos antes de generar el contrato.", 
                        "Campos incompletos", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if (comboBox.getSelectedIndex() == 0 || comboBox_1.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(frame, "Por favor seleccione un paquete y un rango peso/edad válidos.", 
                        "Selección requerida", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // Generar el PDF
                generarContratoPDF(comboBox, comboBox_1);
            }
        });
        
        panel.add(btnNewButton);
        
        JList<String> list = new JList<>();
        list.setBackground(new Color(100, 149, 237));
        list.setModel(new javax.swing.AbstractListModel<String>() {
            String[] values = new String[] {
            		"0 a 8 años / peso promedio = $2000",
            		"0 a 8 años / sobrepeso = $4000",
            		"9 a 14 años / peso promedio = $4000",
            		"9 a 14 años / sobrepeso = $8000",
            		"14 años + / peso promedio = $6000",
            		"14 años + / sobrepeso = $10000"
            		};
            public int getSize() {
                return values.length;
            }
            public String getElementAt(int index) {
                return values[index];
            }
        });
        list.setFont(new Font("Verdana", Font.PLAIN, 11));
        list.setBounds(10, 531, 228, 102);
        panel.add(list);
        
        JLabel lblInformacinDePliza = new JLabel("Información de póliza:\r\n");
        lblInformacinDePliza.setFont(new Font("Verdana", Font.BOLD, 13));
        lblInformacinDePliza.setBounds(10, 401, 203, 40);
        panel.add(lblInformacinDePliza);
        
        JLabel lblNombre_1_2_1_2 = new JLabel("Tarifa peso / edad:");
        lblNombre_1_2_1_2.setFont(new Font("Verdana", Font.BOLD, 11));
        lblNombre_1_2_1_2.setBounds(10, 499, 155, 33);
        panel.add(lblNombre_1_2_1_2);
    }
    
    private void generarContratoPDF(JComboBox<String> comboBox, JComboBox<String> comboBox_1) {
        try {
            // Crear un nuevo documento PDF
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            
            // Configurar el flujo de contenido
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            
            // Cargar la imagen del logo
            PDImageXObject logo = PDImageXObject.createFromFile("src/img/gato.png", document);
            
            // Agregar el logo en la esquina superior derecha
            contentStream.drawImage(logo, 500, 700, 75, 75);
            
            // Configurar fuentes
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 18);
            
            // Título del contrato
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 750);
            contentStream.showText("CONTRATO DE SEGURO PARA MASCOTAS");
            contentStream.endText();
            
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 730);
            contentStream.showText("Fecha: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            contentStream.endText();
            
            // Dibujar recuadro para información del animal
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 14);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 680);
            contentStream.showText("INFORMACIÓN DEL ANIMAL");
            contentStream.endText();
            
            dibujarRecuadro(contentStream, 50, 600, 500, 80);
            
            // Información del animal dentro del recuadro
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(60, 660);
            contentStream.showText("Nombre: " + textField.getText());
            contentStream.newLineAtOffset(200, 0);
            contentStream.showText("Especie: " + textField_1.getText());
            contentStream.newLineAtOffset(-200, -20);
            contentStream.showText("Raza: " + textField_2.getText());
            contentStream.newLineAtOffset(200, 0);
            contentStream.showText("Edad: " + textField_3.getText() + " años");
            contentStream.newLineAtOffset(-200, -20);
            contentStream.showText("Peso: " + textField_4.getText() + " kg");
            contentStream.endText();
            
            // Dibujar recuadro para información del propietario
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 14);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 550);
            contentStream.showText("INFORMACIÓN DEL PROPIETARIO");
            contentStream.endText();
            
            dibujarRecuadro(contentStream, 50, 470, 500, 80);
            
            // Información del propietario dentro del recuadro
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(60, 530);
            contentStream.showText("Nombre: " + textField_5.getText());
            contentStream.newLineAtOffset(250, 0);
            contentStream.showText("Identificación: " + textField_6.getText());
            contentStream.newLineAtOffset(-250, -20);
            contentStream.showText("Teléfono: " + textField_7.getText());
            contentStream.endText();
            
            // Dibujar recuadro para información de la póliza
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 14);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 420);
            contentStream.showText("INFORMACIÓN DE LA PÓLIZA");
            contentStream.endText();
            
            dibujarRecuadro(contentStream, 50, 340, 500, 80);
            
            // Información de la póliza dentro del recuadro
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(60, 400);
            contentStream.showText("Paquete seleccionado: " + comboBox.getSelectedItem().toString());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Rango peso/edad: " + comboBox_1.getSelectedItem().toString());
            contentStream.endText();
            
            // Términos y condiciones
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 14);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 300);
            contentStream.showText("TÉRMINOS Y CONDICIONES");
            contentStream.endText();
            
            dibujarRecuadro(contentStream, 50, 100, 500, 200);
            
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 10);
            String terminos = "1. Este contrato cubre gastos veterinarios hasta el límite establecido en el paquete seleccionado.\n"
                    + "2. No cubre condiciones preexistentes.\n"
                    + "3. El asegurado debe presentar revisión veterinaria anual.\n"
                    + "4. Cualquier fraude anulará el contrato.\n"
                    + "5. El contrato tiene una vigencia de 1 año renovable.";
            
            escribirTextoConSaltos(contentStream, 60, 280, terminos, 15);
            
            // Firma
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(300, 60);
            contentStream.showText("Firma del propietario: ___________________");
            contentStream.endText();
            
            contentStream.close();
            
            // Mostrar diálogo para guardar el archivo
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar contrato como PDF");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Documentos PDF", "pdf"));
            fileChooser.setSelectedFile(new File("ContratoMascota_" + textField.getText() + ".pdf"));
            
            int userSelection = fileChooser.showSaveDialog(frame);
            
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                // Asegurarse de que tenga la extensión .pdf
                String filePath = fileToSave.getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".pdf")) {
                    filePath += ".pdf";
                }
                
                document.save(filePath);
                document.close();
                
                JOptionPane.showMessageDialog(frame, "Contrato generado exitosamente en:\n" + filePath);
            } else {
                document.close();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al generar el PDF: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método auxiliar para dibujar recuadros
    private void dibujarRecuadro(PDPageContentStream contentStream, float x, float y, float width, float height) throws IOException {
        contentStream.setLineWidth(1f);
        contentStream.addRect(x, y, width, height);
        contentStream.stroke();
    }

    // Método auxiliar para escribir texto con saltos de línea
    private void escribirTextoConSaltos(PDPageContentStream contentStream, float x, float y, String text, float leading) throws IOException {
        String[] lines = text.split("\n");
        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        for (String line : lines) {
            contentStream.showText(line);
            contentStream.newLineAtOffset(0, -leading);
        }
        contentStream.endText();
    }
}
