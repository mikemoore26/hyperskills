package horseRacing.window;

import horseRacing.Controller;
import horseRacing.entity.Horse;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HorseDisplayPanel extends JPanel {


    private Controller controller;

    private GridBagConstraints cs;

    // CONSTRUCTORS
    public HorseDisplayPanel(){
        setLayout(new GridBagLayout());
        this.controller = controller;

        JPanel pane = new JPanel(new GridBagLayout());
        cs = new GridBagConstraints();
        setupGridConstraints();
        //showHorse();
        setBorder(BorderFactory.createEtchedBorder());
        setVisible(true);
    }

    public HorseDisplayPanel(Controller controller){
        this.controller = controller;
        setLayout(new GridBagLayout());
        this.controller = controller;

        JPanel pane = new JPanel(new GridBagLayout());
        cs = new GridBagConstraints();
        setupGridConstraints();
        //showHorse();
        setBorder(BorderFactory.createEtchedBorder());
        setVisible(true);
    }

    //GETTERS AND SETTERS

    public void setController(Controller controller){
        if(controller != null){
            this.controller = controller;
        }
    }

    public void showHorse(){
        System.out.println(controller.getRace().getHorses().size());
        for(Horse horse: controller.getRace().getHorses()){
            add(new JLabel(horse.getName() + ": id=" + horse.getHorseId()), cs);
            cs.gridy++;
            add( new JLabel("Top Speed: " + horse.getTopSpeed()), cs);
            cs.gridy++;
            add(new JLabel("Velocity: " + horse.getVelocity()),cs);
            cs.gridy++;

            cs.gridx++;
            cs.gridy = 0;
        }
    }

    private void setupGridConstraints(){
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        cs.gridheight = 1;
//        cs.ipadx = 20;
//        cs.ipady = 0;
       cs.insets= new Insets(5,5,0,5);

    }
}
