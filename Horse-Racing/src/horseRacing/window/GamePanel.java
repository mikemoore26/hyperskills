package horseRacing.window;

import horseRacing.Controller;
import horseRacing.entity.Horse;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Controller controller;
    private GridBagConstraints cs;
    private JProgressBar[] horseProgressBar;
    private JPanel centerPanel;
    private JLabel winner;
    private JLabel amtofHorses;
    private JTextField horseNum;

    public GamePanel(Controller controller){
        this.controller = controller;
        setLayout(new BorderLayout());
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        add(centerPanel, BorderLayout.CENTER);

        winner = new JLabel("Race has not started", SwingConstants.CENTER);
        add(winner,BorderLayout.NORTH);


        cs = new GridBagConstraints();


        setupGridConstraints();
    }
    //GETTTERS AND SETTERS

    public void setController(Controller controller){
        if(controller != null){
            this.controller = controller;
        }
    }

    // public methods


    public void showHorse(){
        horseProgressBar = new JProgressBar[controller.getRace().getHorses().size()];
        winner.setText("Race in progress.");

        for(Horse horse: controller.getRace().getHorses()){
            centerPanel.add(new JLabel("" + horse.getHorseId()), cs);
            cs.gridx++;
            horseProgressBar[cs.gridy] = new JProgressBar();
            horseProgressBar[cs.gridy].setMaximum(controller.getRace().getTrack().getDistance());
            horseProgressBar[cs.gridy].setMinimum(0);


            centerPanel.add(horseProgressBar[cs.gridy], cs);

            cs.gridy++;
            cs.gridx = 0;

        }
    }

    public void displayWinner(String Swinner){
        Font font = new Font("Times new roman", Font.BOLD, 12);
        winner.setFont(font);
        winner.setText(" The Winner of Race ID#: " + controller.getRace().getRaceId() + " is " + Swinner);
    }

    public void updateRace(){
        for(int i = 0; i < controller.getRace().getHorses().size(); i++){
            horseProgressBar[i].setValue(controller.getRace().getHorses().get(i).getDistance());
        }
    }

    // private Methods
    private void setupGridConstraints() {
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        cs.gridheight = 1;
//        cs.ipadx = 20;
//        cs.ipady = 0;
        cs.insets = new Insets(5, 5, 0, 5);
    }
}
