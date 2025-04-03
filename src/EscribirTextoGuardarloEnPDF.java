import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import javax.swing.JFileChooser;
import java.io.File;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EscribirTextoGuardarloEnPDF {

    private JFrame frame;
    private JTextArea textArea; // Mover a nivel de clase para acceso en el ActionListener

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EscribirTextoGuardarloEnPDF window = new EscribirTextoGuardarloEnPDF();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public EscribirTextoGuardarloEnPDF() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
        frame.getContentPane().setForeground(new Color(255, 255, 255));
        frame.getContentPane().setBackground(new Color(102, 205, 170));
        frame.setBounds(100, 100, 624, 564);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Guarda en PDF lo que escribas aquí");
        lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
        lblNewLabel.setBounds(58, 31, 511, 49);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("A continuación redacta un texto que quieras ver impreso en tu PDF:");
        lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
        lblNewLabel_1.setBounds(69, 129, 477, 55);
        frame.getContentPane().add(lblNewLabel_1);

        textArea = new JTextArea(); // Inicializar aquí
        textArea.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		textArea.setBackground(Color.white);
                textArea.setForeground(Color.black);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		textArea.setBackground(new Color(47, 79, 79));
        	}
        });
        textArea.setBackground(new Color(47, 79, 79));
        textArea.setBounds(58, 188, 486, 212);
        frame.getContentPane().add(textArea);

        JButton btnNewButton = new JButton("Generar PDF");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generarPDF();
            }
        });
        btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
        btnNewButton.setBounds(215, 439, 166, 37);
        frame.getContentPane().add(btnNewButton);
    }

    private void generarPDF() {
        String texto = textArea.getText();
        if (texto.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(frame, "¡El texto está vacío!", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar PDF como...");
        fileChooser.setSelectedFile(new File("documento.pdf"));
        int userSelection = fileChooser.showSaveDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf")) {
                filePath += ".pdf";
            }

            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage();
                document.addPage(page);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 700); // Posición (x, y)
                    contentStream.showText(texto);
                    contentStream.endText();
                }

                document.save(filePath);
                javax.swing.JOptionPane.showMessageDialog(frame, "PDF guardado en: " + filePath, "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                javax.swing.JOptionPane.showMessageDialog(frame, "Error al generar el PDF: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}