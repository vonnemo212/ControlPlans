import java.io.*;
import java.util.Properties;

public class propertiesFile {

    private static String name = "ControlPlanes.properties";
    public static String propertiesLocation = System.getProperty("user.dir") + "\\Config\\" + name;
    public static Properties prop = new Properties();

    //Input & Output
    private static InputStream input = null;
    private static OutputStream output = null;

    public static Boolean propertiesExist() {
        File f = new File(propertiesLocation);
        return f.isFile();
    }

    public static void createPropertiesFile() {


        new File(System.getProperty("user.dir") + "\\Config\\").mkdirs();

        prop.setProperty("Template_za_kontrolni_list", System.getProperty("user.dir") + "\\Config\\kontrolni_list_template.xlsx");
        prop.setProperty("Pot_za_izvoz_kontrolnega_lista", System.getProperty("user.dir") + "\\Config\\");
        prop.setProperty("Pot_za_izvoz_PDF", System.getProperty("user.dir") + "\\Config\\");

        try {
            output = new FileOutputStream(propertiesLocation);
            prop.store(output,"Program properties");
        } catch (IOException e) {
            System.out.println("Tu je problem");
            throw new RuntimeException(e);
        }
    }

    public static void readPropertiesFile() {
        try {
            input = new FileInputStream(propertiesLocation);
            prop.load(input);

            System.out.println(prop.getProperty("Template_za_kontrolni_list"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void changePropertiesFile() {

    }



}
