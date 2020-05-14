package horseRacing.window;

import horseRacing.Controller;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {

    private Controller controller;
    private JLabel welcomeText;
    private JLabel amtofHorses;
    private JTextField horseNum;

    private int amtHor = 0;

    public WelcomePanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        amtofHorses = new JLabel("Horses Amount:");
        horseNum = new JTextField(2);


        Font font = new Font("Times new Roman",Font.BOLD,28);
        welcomeText = new JLabel("Welcome to Darkotainment's Horse Racing Game");
        welcomeText.setFont(font);

        gc.gridy = 0;
        gc.gridx = 0;
        gc.gridwidth = 2;
        add(welcomeText, gc);

        gc.gridy++;
        gc.gridwidth=1;
        gc.gridx=0;
        add(amtofHorses, gc);

        gc.gridx++;
        horseNum.setText("5");
        add(horseNum, gc);
    }

    // GETTER AND SETTERS

    public void setController(Controller controller){
        this.controller = controller;
    }

    public boolean setHorsestoRace(){
        try {
            amtHor = Integer.parseInt(horseNum.getText());
        }catch (NumberFormatException e){
            System.out.println("ERROR");
            //e.printStackTrace();
            return false;
        }
        if(amtHor == 0 ){
            System.out.println("Error: cannot == 0");
            return false;
        }
        controller.addHorsesToRace(amtHor);
        return true;

    }
}
