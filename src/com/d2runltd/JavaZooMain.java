package com.d2runltd;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JavaZooMain
{
    private JPanel mainPanel;
    private ArrayList<JPanel> animalPanels;

    private static ArrayList<Animal> animals;

    public static void main(String[] args) throws FileNotFoundException, org.json.simple.parser.ParseException
    {
        JavaZooMain javaZooMain = new JavaZooMain();

        animalsInit();

        setUpGUI(javaZooMain);

        JFrame frame = new JFrame("MyFirstForm");

        frame.setVisible(true);
        frame.setContentPane(javaZooMain.mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JavaZooMain()
    {
        animalPanels = new ArrayList<>();
        mainPanel = new JPanel();
        animals = new ArrayList<>();
    }

    private static void animalsInit() throws FileNotFoundException, org.json.simple.parser.ParseException
    {
        JSONParser parser = new JSONParser();

        try
        {
            JSONArray a = (JSONArray) parser.parse(new FileReader("src\\com\\d2runltd\\animals.json"));
            for (Object obj : a)
            {
                JSONObject jsonObject = (JSONObject) obj;

                String name = (String) jsonObject.get("animalName");
                Long animalId = (Long) jsonObject.get("animalId");
                Long cageNum = (Long) jsonObject.get("cageNo");
                String trainerName = (String) jsonObject.get("trainerName");
                Boolean isHungry = (Boolean) jsonObject.get("isHungry");
                Boolean isHealthy = (Boolean) jsonObject.get("isHealthy");
                String status = (String) jsonObject.get("status");
                BufferedImage bufferedImage = ImageIO.read(new File((String)jsonObject.get("imageLoc")));

                Animal animal = new Animal(name, animalId, cageNum, trainerName, isHungry, isHealthy, status, bufferedImage);

                animals.add(animal);
            }
        } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public static void setUpGUI(JavaZooMain javaZooMain)
    {
        animalPanelsInit(javaZooMain);

        for (JPanel panel: javaZooMain.animalPanels)
            javaZooMain.mainPanel.add(panel);

    }

    private static void animalPanelsInit(JavaZooMain javaZooMain)
    {
        for(Animal animal:animals)
        {
            JPanel panel = new JPanel();

            JTextField nameField = new JTextField();
            nameField.setText("Name: "+ animal.animalName);
            nameField.setEditable(false);

            JTextField animalIdField = new JTextField();
            animalIdField.setText("ID: "+ animal.animalID.toString());
            animalIdField.setEditable(false);

            JTextField cageNumField = new JTextField();
            cageNumField.setText("Cage no.: " + animal.cageNum.toString());
            cageNumField.setEditable(false);

            JTextField statusField = new JTextField();
            statusField.setText("Status: "+ animal.status);
            statusField.setEditable(false);

            JTextField trainerField = new JTextField();
            trainerField.setText("Trainer: "+ animal.trainerName);
            trainerField.setEditable(false);

            JRadioButton isHungry = new JRadioButton();
            isHungry.setEnabled(animal.isHungry);

            JRadioButton isHealthy = new JRadioButton();
            isHealthy.setEnabled(animal.healthy);

            JLabel picLabel = new JLabel(new ImageIcon(animal.image));

            panel.add(picLabel);
            panel.add(nameField);
            panel.add(animalIdField);
            panel.add(cageNumField);
            panel.add(trainerField);
            panel.add(statusField);
            panel.add(isHealthy);
            panel.add(isHungry);

            javaZooMain.animalPanels.add(panel);
        }
    }
}
