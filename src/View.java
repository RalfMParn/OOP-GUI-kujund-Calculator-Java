import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {

    JComboBox<String> comboBox;
    JTextField radiusField;
    JTextField heightField;
    JTextArea textArea;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JButton button;


    public View() {

        this.setTitle("Geomeetrilise kujundi kalkulaator");  // paneb pealkirja
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        centerFrameOnScreen();  // paneb JFrame ekraani keskele

        String[] shapes = {"Vali","Kera", "Silinder"};
        comboBox = new JComboBox<>(shapes);


        radiusField = new JTextField(10);  // Lisab koht kus saab sisse raadius kirjutada
        heightField = new JTextField(10);  // Lisab koht kus saab sisse pikkus kirjutada

        radiusField.setEditable(false);
        heightField.setEditable(false);

        label1 = new JLabel("Raadius");  // Teeb "Raadius" text sisestuse kasti korvale
        label2 = new JLabel("Kõrgus");  // Teeb "Kõrgus" text sisestuse kasti korvale
        label3 = new JLabel("");  // Teeb "Kõrgus" text sisestuse kasti korvale
        label3.setForeground(Color.RED);


        button = new JButton("Arvuta");
        button.addActionListener(this);
        button.setEnabled(false);  // Ei sa valida "Arvuta" kuni kuju on valitud

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new GridBagLayout());
        comboBoxPanel.setBackground(Color.PINK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        comboBoxPanel.add(comboBox, gbc);

        gbc.gridx = 1;
        comboBoxPanel.add(label1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        comboBoxPanel.add(label2, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        comboBoxPanel.add(radiusField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        comboBoxPanel.add(heightField, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        comboBoxPanel.add(button, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        comboBoxPanel.add(label3, gbc);

        this.add(comboBoxPanel, BorderLayout.NORTH);  // Paneb komponentid paneel frami paika

        textArea = new JTextArea(10, 40);
        textArea.setEditable(false);

        JPanel textAreaPanel = new JPanel();
        textAreaPanel.setLayout(new BorderLayout());
        textAreaPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Paneb textarea panel frami paika
        this.add(textAreaPanel, BorderLayout.CENTER);
        Controller controller = new Controller(comboBox, radiusField, heightField, button, textArea, label3);

    }
    private void centerFrameOnScreen() {
        // Saab ekraani mõõtmed
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Arvutab ekraani keskkoht
        int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);


        setLocation(centerX, centerY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {

            // System.out.println(comboBox.getSelectedIndex());  // Testimeseks
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new View().setVisible(true);
            }
        });
    }
}
