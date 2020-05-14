package horseRacing.window;

import horseRacing.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener{

    private int width = 800;
    private  int height = 500;
    private String startBtnText = "Start Game!";
    private String startBtnText2 = "Restart Race";

    JButton startBtn;
    JButton raceBtn;
    JButton exitBtn;
    JPanel southPane;
    Controller controller;
    GamePanel gamePanel;
    HorseDisplayPanel horseDisplayPanel;
    WelcomePanel welcomePanel;

    public Window(){
        this.controller = controller;
        setTitle("Darkotainment Horseracing");
        setLayout(new BorderLayout());


        setPreferredSize(new Dimension(width,height));
        setSize(new Dimension(width,height));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void init(){
        welcomePanel = new WelcomePanel();
        horseDisplayPanel = new HorseDisplayPanel();
        add(horseDisplayPanel, BorderLayout.NORTH);
        add(welcomePanel, BorderLayout.CENTER);
        southPane = new JPanel();

        startBtn = new JButton(startBtnText);
        startBtn.addActionListener(this);

        exitBtn = new JButton("Exit!");
        exitBtn.addActionListener(this);

        raceBtn = new JButton("Race|");
        raceBtn.addActionListener(this);
        raceBtn.setEnabled(false);


        southPane.add(startBtn);
        southPane.add(raceBtn);
        southPane.add(exitBtn);
        add(southPane, BorderLayout.SOUTH);

    }

    public void setController(Controller controller){
        this.controller = controller;
        init();

        horseDisplayPanel.setController(controller);
        welcomePanel.setController(controller);

    }

    public void updateRace(){
        if(controller.getRace() != null) {
            gamePanel.updateRace();
            refresh();
        }
    }


    private void refresh(){
        repaint();
        revalidate();
    }

    public void displayWinner(String winner){
        gamePanel.displayWinner(winner);
    }

    // private methods
    private void switchPanels(Component old, Component newComp){
        if(old != null ){
            System.out.println("rmoving old");
            remove(old);
            if(old == gamePanel){
                remove(horseDisplayPanel);
                horseDisplayPanel = new HorseDisplayPanel(controller);
                add(horseDisplayPanel,BorderLayout.NORTH);
            }
        }
        if(newComp != null){
            System.out.println("adding new");
            add(newComp);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startBtn){

            if(startBtn.getText().equals(startBtnText)){
                controller.startGame();
                if(welcomePanel.setHorsestoRace()){

                    gamePanel = new GamePanel(controller);
                    switchPanels(welcomePanel, gamePanel);

                    horseDisplayPanel.showHorse();
                    gamePanel.showHorse();
                    raceBtn.setEnabled(true);
                    startBtn.setText(startBtnText2);
                }

            }else if(startBtn.getText().equals(startBtnText2)){
                startBtn.setText(startBtnText);
                switchPanels(gamePanel, welcomePanel);
                gamePanel = null;
                raceBtn.setEnabled(false);

            }

            refresh();
        }

        if(e.getSource() == raceBtn){
            System.out.println(" RaceBtn: Clicked");
            controller.runRace();
        }
        if(e.getSource() == exitBtn){
            System.exit(0);
        }
    }

}
