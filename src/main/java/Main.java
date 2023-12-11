import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class Main extends JFrame {

    JButton connectCreoBtn = new JButton("Connect");
    JButton disconnectCreoBtn = new JButton("Disconnect");
    JButton getModelBtn = new JButton("Get model");
    JButton dimSelection = new JButton("Izbor dimenzij");
    JButton placeBalloonsBtn = new JButton("Make Balloons");
    JButton writeExcel = new JButton("Write to excel");

    JButton exportPDF = new JButton("Export PDF");
    JButton senses = new JButton("Get Sense");

    public static JTextField imeModela = new JTextField("placeholder");
    public static JTextField sifraArtikla = new JTextField("placeholder");
    public static JTextField dimCount = new JTextField("placeholder");


    public static void main(String[] args) {
        System.loadLibrary("pfcasyncmt");


        //Properties
        if(propertiesFile.propertiesExist()) {
            propertiesFile.readPropertiesFile();
        } else {
            propertiesFile.createPropertiesFile();
        }


        //Zagon okna
        Main view = new Main();
        view.setupUI();
        view.setVisible(true);
    }

    public Main() {

        connectCreoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!pfcAsync.conn) {
                    pfcAsync.ConnectToCreo();
                }
                if (pfcAsync.conn) {
                    connectCreoBtn.setBackground(Color.green);
                } else {
                    connectCreoBtn.setBackground(Color.red);
                }
                if (SessionModel.checkModel(pfcAsync.session)) {
                    SessionModel.GetSessionModel();
                    getModelBtn.setBackground(Color.GREEN);
                } else {
                    getModelBtn.setBackground(Color.RED);
                }

            }
        });

        disconnectCreoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pfcAsync.conn) {
                    pfcAsync.DisconnectFromCreo();
                }

                if (!pfcAsync.conn) {
                    connectCreoBtn.setBackground(Color.RED);
                    getModelBtn.setBackground(Color.RED);
                }
            }
        });

        getModelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pfcAsync.conn) {
                    SessionModel.GetSessionModel();
                    if(SessionModel.model != null) {
                        getModelBtn.setBackground(Color.GREEN);
                    }
                }
            }
        });

        dimSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            drawingOperations.selectBox();

            }
        });

        writeExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        placeBalloonsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        exportPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        senses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(pfcAsync.conn || SessionModel.model != null) {
                    drawingOperations.exportDim();
                }

            }
        });

    }

    private void setupUI() {


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Definicija potrebnih komponent

        //Zgornji in spodnji del programa
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();


        // Oznake nad textfieldi
        JLabel modelName = new JLabel("Current working dir:");
        JLabel articleNumber = new JLabel("Šifra:");
        JLabel selectionCount = new JLabel("Število izbranih dimenzij");


        //Zgornji del programa
        top.setLayout(new GridLayout(1, 2));

        JPanel topLeft = new JPanel();
        topLeft.setLayout(new GridLayout(2, 1));
        topLeft.setMaximumSize(new Dimension(100, 100));
        connectCreoBtn.setSize(10, 10);
        connectCreoBtn.setPreferredSize(new java.awt.Dimension(10, 10));
        connectCreoBtn.setMaximumSize(new java.awt.Dimension(10, 10));
        topLeft.add(connectCreoBtn);
        topLeft.add(disconnectCreoBtn);
        top.add(topLeft);

        JPanel topRight = new JPanel();
        topRight.setLayout(new GridLayout(4, 1));
        topRight.setMaximumSize(new Dimension(200, 100));
        topRight.add(modelName);
        topRight.add(imeModela);
        topRight.add(articleNumber);
        topRight.add(sifraArtikla);
        top.add(topRight);


        //Spodnji del programa
        bottom.setLayout(new GridLayout(1, 2));

        JPanel bottomLeft = new JPanel();
        bottomLeft.setLayout(new GridLayout(2, 1));

        getModelBtn.setSize(10, 10);
        getModelBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        getModelBtn.setMaximumSize(new java.awt.Dimension(30, 30));
        dimSelection.setSize(10, 10);
        dimSelection.setPreferredSize(new java.awt.Dimension(30, 30));
        dimSelection.setMaximumSize(new java.awt.Dimension(30, 30));
        writeExcel.setSize(10, 10);
        writeExcel.setPreferredSize(new java.awt.Dimension(10, 10));
        writeExcel.setMaximumSize(new java.awt.Dimension(10, 10));
        placeBalloonsBtn.setSize(10, 10);
        placeBalloonsBtn.setPreferredSize(new java.awt.Dimension(10, 10));
        placeBalloonsBtn.setMaximumSize(new java.awt.Dimension(10, 10));
        exportPDF.setSize(10, 10);
        exportPDF.setPreferredSize(new java.awt.Dimension(10, 10));
        exportPDF.setMaximumSize(new java.awt.Dimension(10, 10));
        senses.setSize(10, 10);
        senses.setPreferredSize(new java.awt.Dimension(10, 10));
        senses.setMaximumSize(new java.awt.Dimension(10, 10));
        topLeft.add(connectCreoBtn);
        bottomLeft.add(getModelBtn);
        bottomLeft.add(dimSelection);
        bottomLeft.add(writeExcel);
        bottomLeft.add(placeBalloonsBtn);
        bottomLeft.add(exportPDF);
        //Testni gumb za testiranje sensov
        bottomLeft.add(senses);
        bottom.add(bottomLeft);


        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(top, "North");
        contentPane.add(bottom, "South");

        contentPane.setVisible(true);

        pack();
    }
}