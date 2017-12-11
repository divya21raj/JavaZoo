package com.d2runltd;

import java.awt.image.BufferedImage;

public class Animal
{
    String animalName;

    Long animalID;

    Long cageNum;

    String trainerName;

    Boolean isHungry;

    Boolean healthy;

    String status;

    BufferedImage image;

    public Animal(String animalName, Long animalID, Long cageNum, String trainerName, Boolean isHungry, Boolean healthy, String status, BufferedImage image)
    {
        this.animalName = animalName;
        this.animalID = animalID;
        this.cageNum = cageNum;
        this.trainerName = trainerName;
        this.isHungry = isHungry;
        this.healthy = healthy;
        this.status = status;
        this.image = image;
    }
}
