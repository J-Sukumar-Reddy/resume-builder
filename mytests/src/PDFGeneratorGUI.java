import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;

public class PDFGeneratorGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginSignupGUI());
    }
}

class LoginSignupGUI extends JFrame implements ActionListener {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel responseLabel;

    private final String DB_URL = "jdbc:mysql://localhost:3306/resume_builder";
    private final String DB_USER = "root";
    private final String DB_PASS = "Siddu@8642";

    public LoginSignupGUI() {
        setTitle("Login/Signup Form");
        setBounds(300, 90, 400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Container container = getContentPane();
        container.setLayout(null);

        JLabel title = new JLabel("Login/Signup Form");
        title.setBounds(100, 10, 200, 30);
        container.add(title);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 50, 100, 30);
        container.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 50, 150, 30);
        container.add(emailField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 100, 100, 30);
        container.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 150, 30);
        container.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(50, 150, 100, 30);
        loginButton.addActionListener(this);
        container.add(loginButton);

        JButton signupButton = new JButton("Signup");
        signupButton.setBounds(200, 150, 100, 30);
        signupButton.addActionListener(this);
        container.add(signupButton);

        responseLabel = new JLabel("");
        responseLabel.setBounds(50, 200, 300, 30);
        container.add(responseLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (e.getActionCommand().equals("Login")) {
            if (checkLogin(email, password)) {
                responseLabel.setText("Login successful!");
                new PDFGeneratorGUI();
                dispose();
            } else {
                responseLabel.setText("Invalid email or password.");
            }
        } else if (e.getActionCommand().equals("Signup")) {
            if (registerUser(email, password)) {
                responseLabel.setText("Signup successful! You can now login.");
            } else {
                responseLabel.setText("Email already exists.");
            }
        }
    }

    private boolean checkLogin(String email, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?")) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean registerUser(String email, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (email, password) VALUES (?, ?)")) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

class PDFGeneratorGUI extends JFrame {
    private JTextField input1Field;
    private JTextField input2Field;
    private JTextField input3Field;
    private JTextField input4Field;
    private JTextField input5Field;
    private JTextField input6Field;
    private JTextField input7Field;
    private JTextField input8Field;
    private JTextField input9Field;
    private JTextField input10Field;
    private JTextField input11Field;
    private JTextField input12Field;
    private JTextField input13Field;
    private JTextField input14Field;
    private JTextField imageField;
    private JLabel imageLabel;
    private File selectedImage;

    public PDFGeneratorGUI() {
        setTitle("PDF Generator");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GradientPanel());
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BorderLayout());

        JPanel textInputPanel = new JPanel();
        textInputPanel.setOpaque(false);
        textInputPanel.setLayout(new GridLayout(15, 2, 10, 10));

        input1Field = new JTextField();
        textInputPanel.add(new JLabel("Name:"));
        textInputPanel.add(input1Field);

        input2Field = new JTextField();
        textInputPanel.add(new JLabel("Description:"));
        textInputPanel.add(input2Field);

        input3Field = new JTextField();
        textInputPanel.add(new JLabel("E-Mail:"));
        textInputPanel.add(input3Field);

        input4Field = new JTextField();
        textInputPanel.add(new JLabel("Contact:"));
        textInputPanel.add(input4Field);

        input5Field = new JTextField();
        textInputPanel.add(new JLabel("Address:"));
        textInputPanel.add(input5Field);

        input6Field = new JTextField();
        textInputPanel.add(new JLabel("Skill 1:"));
        textInputPanel.add(input6Field);

        input7Field = new JTextField();
        textInputPanel.add(new JLabel("Skill 2:"));
        textInputPanel.add(input7Field);

        input8Field = new JTextField();
        textInputPanel.add(new JLabel("Skill 3:"));
        textInputPanel.add(input8Field);

        input9Field = new JTextField();
        textInputPanel.add(new JLabel("Skill 4:"));
        textInputPanel.add(input9Field);

        input10Field = new JTextField();
        textInputPanel.add(new JLabel("Experience:"));
        textInputPanel.add(input10Field);

        input11Field = new JTextField();
        textInputPanel.add(new JLabel("College:"));
        textInputPanel.add(input11Field);

        input12Field = new JTextField();
        textInputPanel.add(new JLabel("Intermediate:"));
        textInputPanel.add(input12Field);

        input13Field = new JTextField();
        textInputPanel.add(new JLabel("Schooling:"));
        textInputPanel.add(input13Field);

        input14Field = new JTextField();
        textInputPanel.add(new JLabel("Language Known:"));
        textInputPanel.add(input14Field);

        mainPanel.add(textInputPanel, BorderLayout.CENTER);

        JPanel imageInputPanel = new JPanel();
        imageInputPanel.setOpaque(false);
        imageInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel imageInputLabel = new JLabel("Select Image:");
        imageInputPanel.add(imageInputLabel);

        imageField = new JTextField(20);
        imageField.setEditable(false);
        imageInputPanel.add(imageField);

        JButton selectImageButton = new JButton("Select Image");
        selectImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(PDFGeneratorGUI.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedImage = fileChooser.getSelectedFile();
                imageField.setText(selectedImage.getAbsolutePath());
                displayImage(selectedImage);
            }
        });
        selectImageButton.setBackground(new Color(255, 215, 0));
        imageInputPanel.add(selectImageButton);

        mainPanel.add(imageInputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton generateButton = new JButton("Generate PDF");
        generateButton.addActionListener(e -> {
            String input1 = input1Field.getText();
            String input2 = input2Field.getText();
            String input3 = input3Field.getText();
            String input4 = input4Field.getText();
            String input5 = input5Field.getText();
            String input6 = input6Field.getText();
            String input7 = input7Field.getText();
            String input8 = input8Field.getText();
            String input9 = input9Field.getText();
            String input10 = input10Field.getText();
            String input11 = input11Field.getText();
            String input12 = input12Field.getText();
            String input13 = input13Field.getText();
            String input14 = input14Field.getText();
            String imagePath = imageField.getText();
            generatePDF(input1, input2, input3, input4, input5, input6, input7, input8, input9, input10, input11, input12, input13, input14, imagePath);
        });
        generateButton.setBackground(new Color(100, 149, 237));
        generateButton.setForeground(Color.WHITE);
        buttonPanel.add(generateButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void displayImage(File imageFile) {
        try {
            ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
            imageLabel.setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generatePDF(String input1, String input2, String input3, String input4, String input5, String input6, String input7, String input8, String input9, String input10, String input11, String input12, String input13, String input14, String imagePath) {
        String outputFilePath = "output.pdf";

        try {
            Document document = new Document();
            document.setMargins(36, 36, 36, 36);

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputFilePath));

            document.open();

            Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
            Font sectionFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
            Font contentFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

            PdfPTable headerTable = new PdfPTable(2);
            headerTable.setWidthPercentage(100);
            headerTable.setWidths(new float[]{30, 70});

            if (!imagePath.isEmpty()) {
                Image image = Image.getInstance(imagePath);
                image.scaleToFit(100, 100);
                PdfPCell imageCell = new PdfPCell(image);
                imageCell.setBorder(Rectangle.NO_BORDER);
                imageCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                headerTable.addCell(imageCell);
            } else {
                PdfPCell imageCell = new PdfPCell();
                imageCell.setBorder(Rectangle.NO_BORDER);
                headerTable.addCell(imageCell);
            }

            PdfPCell nameCell = new PdfPCell(new Phrase(input1, titleFont));
            nameCell.setBorder(Rectangle.NO_BORDER);
            nameCell.setVerticalAlignment(Element.ALIGN_TOP);
            headerTable.addCell(nameCell);

            document.add(headerTable);

            document.add(new Paragraph("\n" + input2, contentFont));

            document.add(new Paragraph("\n_______________________________________________________________________________________", contentFont));

            document.add(new Paragraph("\nE-Mail: " + input3, contentFont));
            document.add(new Paragraph("Contact: " + input4, contentFont));
            document.add(new Paragraph("Address: " + input5, contentFont));

            document.add(new Paragraph("\n_______________________________________________________________________________________", contentFont));

            document.add(new Paragraph("\nSkills", sectionFont));
            document.add(new Paragraph(input6, contentFont));
            document.add(new Paragraph(input7, contentFont));
            document.add(new Paragraph(input8, contentFont));
            document.add(new Paragraph(input9, contentFont));

            document.add(new Paragraph("\n_______________________________________________________________________________________", contentFont));

            document.add(new Paragraph("\nWork Experience", sectionFont));
            document.add(new Paragraph(input10, contentFont));

            document.add(new Paragraph("\n_______________________________________________________________________________________", contentFont));

            document.add(new Paragraph("\nEducation", sectionFont));
            document.add(new Paragraph("• " + input11, contentFont));
            document.add(new Paragraph("• " + input12, contentFont));
            document.add(new Paragraph("• " + input13, contentFont));

            document.add(new Paragraph("\n_______________________________________________________________________________________", contentFont));

            document.add(new Paragraph("\nLanguages Known", sectionFont));
            document.add(new Paragraph(input14, contentFont));

            document.close();

            System.out.println("PDF created successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class GradientPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();
        Color color1 = new Color(173, 216, 230);
        Color color2 = new Color(128, 0, 128);
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}
