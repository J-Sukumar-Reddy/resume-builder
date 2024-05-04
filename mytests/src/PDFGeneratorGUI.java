import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Rectangle;

public class PDFGeneratorGUI extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField imageField;
    private JLabel imageLabel;
    private File selectedImage;

    public PDFGeneratorGUI() {
        setTitle("PDF Generator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Panel for text input fields
        JPanel textInputPanel = new JPanel();
        textInputPanel.setLayout(new GridLayout(1, 2));

        textField1 = new JTextField();
        textInputPanel.add(new JLabel("Input 1:"));
        textInputPanel.add(textField1);

        textField2 = new JTextField();
        textInputPanel.add(new JLabel("Input 2:"));
        textInputPanel.add(textField2);

        mainPanel.add(textInputPanel, BorderLayout.NORTH);

        // Panel for image input
        JPanel imageInputPanel = new JPanel();
        imageInputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel imageInputLabel = new JLabel("Input Image:");
        imageInputPanel.add(imageInputLabel);

        imageField = new JTextField(20);
        imageField.setEditable(false);
        imageInputPanel.add(imageField);

        JButton selectImageButton = new JButton("Select Image");
        selectImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(PDFGeneratorGUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedImage = fileChooser.getSelectedFile();
                    imageField.setText(selectedImage.getAbsolutePath());
                    displayImage(selectedImage);
                }
            }
        });
        imageInputPanel.add(selectImageButton);

        mainPanel.add(imageInputPanel, BorderLayout.CENTER);

        // Label for displaying the selected image
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(imageLabel, BorderLayout.SOUTH);

        JButton generateButton = new JButton("Generate PDF");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input1 = textField1.getText();
                String input2 = textField2.getText();
                String imagePath = imageField.getText();
                generatePDF(input1, input2, imagePath);
            }
        });
        mainPanel.add(generateButton, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void displayImage(File imageFile) {
        try {
            ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
            Image image = Image.getInstance(imageFile.getAbsolutePath());
            image.scaleToFit(200, 200); // Adjust the dimensions as needed
            imageLabel.setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PDFGeneratorGUI();
            }
        });
    }

    public static void generatePDF(String input1, String input2, String imagePath) {
        String outputFilePath = "output.pdf";

        try {
            // Create a Document instance
            Document document = new Document();

            // Create a PdfWriter instance
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputFilePath));

            // Open the document
            document.open();

            // Add content to the document
            document.add(new Paragraph("Input 1: " + input1));
            document.add(new Paragraph("Input 2: " + input2));

            // Add image to the document
            if (!imagePath.isEmpty()) {
                Image image = Image.getInstance(imagePath);
                image.scaleToFit(100, 100); // Adjust the dimensions as needed
                Rectangle page = document.getPageSize();

                // Calculate the position with padding
                float x = page.getWidth() - image.getScaledWidth() - 20; // Adjust padding as needed
                float y = page.getHeight() - image.getScaledHeight() - 20; // Adjust padding as needed

                image.setAbsolutePosition(x, y);
                document.add(image);
            }

            // Close the document
            document.close();

            System.out.println("PDF created successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
