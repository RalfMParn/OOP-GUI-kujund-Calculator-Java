import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class Model {
    public class SphereCalculator {

        public static double calculateSurfaceArea(double radius) {
            return 4 * Math.PI * radius * radius;
        }

        public static double calculateVolume(double radius) {
            return (4.0 / 3) * Math.PI * radius * radius * radius;
        }

        public static double calculateCircumference(double radius) {
            return 2 * Math.PI * radius;
        }
    }
    public class CylinderCalculator {

    public static double calculateTotalSurfaceArea(double radius, double height) {
        return 2 * Math.PI * radius * (radius + height);
    }

    public static double calculateLateralSurfaceArea(double radius, double height) {
        return 2 * Math.PI * radius * height;
    }

    public static double calculateVolume(double radius, double height) {
        return Math.PI * radius * radius * height;
    }
}
    public void writeDataToFile(String data) {

        String filePath = Paths.get("src", "JavaKujundid.txt").toString();
        String dataformated = data.replaceAll("\\.0[0-4]*;",";");  // Kui on terve number siis võtab ".0" lõpust ära

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {

            writer.write(dataformated);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getShortFilePath() {
    File file = new File("src\\JavaKujundid.txt");

        if (file.exists()) {
            // Kui "JavaKujundid.txt" on olemas siis saadab tagasi selle tee
            return file.getPath();
        } else {
            // kui ei leija siis saadab teada, et ei leitud tagasi
            return "Fail ei leitud.";
        }
    }
}



