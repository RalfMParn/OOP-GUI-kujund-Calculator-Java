import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Controller implements ActionListener {
    private Model model;
    private JComboBox<String> comboBox;
    private JTextField radiusField;
    private JTextField heightField;
    private JButton button;
    private JTextArea textArea;
    private JLabel label3;


    public Controller( JComboBox<String> comboBox, JTextField radiusField, JTextField heightField, JButton button, JTextArea textArea, JLabel label3) {
        this.model = new Model();
        this.comboBox = comboBox;
        this.radiusField = radiusField;
        this.heightField = heightField;
        this.button = button;
        this.textArea = textArea;
        this.label3 = label3;


        this.comboBox.addActionListener(this);
        this.button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            handleCalculateButton();
        } else if (e.getSource() == comboBox) {
            label3.setText(model.getShortFilePath());
            handleComboBoxSelection();
        }
    }


    private void handleComboBoxSelection() {
        String selectedItem = (String) comboBox.getSelectedItem();  // Vaatab mis on valitud comboBoxi-st
        if ("Kera".equals(selectedItem)) {
            textArea.setText("Raadius: " +"\nPindala: \n" + "Ruumala: \n" + "Ümbemõõt: ");
            comboBox.removeItem("Vali"); // Ei ole "Vali valikut enam olemas"

            radiusField.setText("");  // Tühjendab jäänud info ära kastist
            heightField.setText("");  // Tühjendab jäänud info ära kastist

            button.setEnabled(true);  // Laseb vajutada "Arvuta" nuppu
            radiusField.setEditable(true);  // Laseb kirjutada "Raadius" kasti
            heightField.setEditable(false);  // Ei lase kirjutada "Kõrgus" kasti

        } else if ("Silinder".equals(selectedItem)) {
            textArea.setText("Raadius: " + "\nKõrgus: " +"\nKogu pindala: \n" + "Külg pindala: \n" + "Ruumala: ");
            comboBox.removeItem("Vali");  // Ei ole "Vali valikut enam olemas"

            radiusField.setText("");  // Tühjendab jäänud info ära kastist
            heightField.setText("");  // Tühjendab jäänud info ära kastist

            button.setEnabled(true);  // Laseb vajutada "Arvuta" nuppu
            radiusField.setEditable(true);  // Laseb kirjutada "Raadius" kasti
            heightField.setEditable(true);  // Laseb kirjutada "Kõrgus" kasti

        }
    }

    private void handleCalculateButton() {
        String selectedshape = (String) comboBox.getSelectedItem();

        try {
            double radius = Double.parseDouble(radiusField.getText());

            if ("Kera".equals(selectedshape)) {
                double surfaceArea = Model.SphereCalculator.calculateSurfaceArea(radius);
                double volume = Model.SphereCalculator.calculateVolume(radius);
                double circumference = Model.SphereCalculator.calculateCircumference(radius);

                textArea.setText("Raadius: " + radius + "\nPindala: " + surfaceArea + "\nRuumala: " + volume + "\nÜmbemõõt: " + circumference);
                String formattedData = String.format("Kera;%s;%s;%s;%s", radius, surfaceArea, volume, circumference);
                model.writeDataToFile(formattedData);
                radiusField.setText("");
            } else if ("Silinder".equals(selectedshape)) {
                try {
                    double height = Double.parseDouble(heightField.getText());
                    double totalSurfaceArea = Model.CylinderCalculator.calculateTotalSurfaceArea(radius, height);
                    double lateralSurfaceArea = Model.CylinderCalculator.calculateLateralSurfaceArea(radius, height);
                    double Volume = Model.CylinderCalculator.calculateVolume(radius, height);

                    textArea.setText("Raadius: " + radius + "\nKõrgus: " + height + "\nKogu pindala: " + totalSurfaceArea + "\nKülg pindala: " + lateralSurfaceArea + "\nRuumala: " + Volume);
                    String formattedData = String.format("Silinder;%s;%s;%s;%s;%s", radius, height , totalSurfaceArea, lateralSurfaceArea, Volume);
                    model.writeDataToFile(formattedData);
                    radiusField.setText("");
                    heightField.setText("");

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Palun sisesta sobiv number! \n'" + heightField.getText() + "' Ei sobi!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    radiusField.setText("");
                    heightField.setText("");
                }
                }

            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Palun sisesta sobiv number! \n'" + radiusField.getText() + "' Ei sobi!", "Input Error", JOptionPane.ERROR_MESSAGE);
                radiusField.setText("");
                heightField.setText("");

        }



    }
}







