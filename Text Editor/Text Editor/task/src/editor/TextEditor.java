package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextEditor extends JFrame implements  ActionListener{

    private int width = 700;
    private int height = 700;
    private int currentSearch;
    private String title = "Text Editor";
    private String search, foundSearch;
    private JPanel center, searchPanel;
    private JTextArea textArea ;
    private JPanel top;
    private JTextField fileNameField, searchField;
    private JButton saveBtn, openButton, startSearchBtn, previousBtn, nextBtn;
    private JCheckBox regexCheck;
    private JMenuBar menuBar;
    private JMenu file, searchMenu;
    private JMenuItem save, open,exit,startSearchItem, previousSearchItem,nextSearchItem,regExItem;
    private boolean nextPrevBool = true;
    private ArrayList<Integer> indexiesStart;
    private ArrayList<String> indexiesString;
    private JFileChooser fileChooser;

    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);

        fileChooser = new JFileChooser();
        fileChooser.setName("FileChooser");
        add(fileChooser,BorderLayout.PAGE_END);

        // menu bar
        menuBar = new JMenuBar();

        file = new JMenu("File");
        file.setName("MenuFile");
        save = new JMenuItem("Save");
        save.setName("MenuSave");
        save.addActionListener(this);

        open = new JMenuItem("Load");
        open.setName("MenuOpen");
        open.addActionListener(this);

        exit = new JMenuItem("Exit");
        exit.setName("MenuExit");
        exit.addActionListener(this);

        file.add(save);
        file.add(open);
        file.add(exit);
        menuBar.add(file);

        searchMenu = new JMenu("Search");
        searchMenu.setName("MenuSearch");
        startSearchItem = new JMenuItem("Start search");
        startSearchItem.setName("MenuStartSearch");
        startSearchItem.addActionListener(this);

        previousSearchItem = new JMenuItem("Previous search");
        previousSearchItem.setName("MenuPreviousMatch");
        previousSearchItem.addActionListener(this);

        nextSearchItem = new JMenuItem("Next Match");
        nextSearchItem.setName("MenuNextMatch");
        nextSearchItem.addActionListener(this);

        regExItem = new JMenuItem("Use regular Expression");
        regExItem.setName("MenuUseRegExp");
        regExItem.addActionListener(this);


        searchMenu.add(startSearchItem);
        searchMenu.add(previousSearchItem);
        searchMenu.add(nextSearchItem);
        searchMenu.add(regExItem);

        menuBar.add(searchMenu);
        setJMenuBar(menuBar);
        //Center
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setTitle(title);
        center = new JPanel();

        textArea  = new JTextArea();
        textArea.setLocation(0,1);
        textArea.setName("TextArea");

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setName("ScrollPane");
        add(scrollPane, BorderLayout.CENTER);

        // TOP

        top = new JPanel();
        top.setLayout(new FlowLayout());
        fileNameField = new JTextField(10);
        fileNameField.setName("FilenameField");
        saveBtn = new JButton(new ImageIcon("C:\\Users\\mikef\\Downloads\\gradle-demo (1)\\Text Editor\\Text Editor\\task\\src\\editor\\save.png"));
        saveBtn.setName("SaveButton");
        saveBtn.addActionListener(this);

        openButton = new JButton(new ImageIcon("C:\\Users\\mikef\\Downloads\\gradle-demo (1)\\Text Editor\\Text Editor\\task\\src\\editor\\load.png"));
        openButton.setName("OpenButton");
        openButton.addActionListener(this);



        //fileNameField.setLocation(0,0);
        //top.add(fileNameField);

        saveBtn.setLocation(1,0);
        top.add(saveBtn);
        openButton.setLocation(2,0);
        top.add(openButton);

        searchPanel = new JPanel();
        searchPanel.setName("SearchPanel");
        searchField = new JTextField(10);
        searchField.setName("SearchField");

        startSearchBtn = new JButton("Search");
        startSearchBtn.setName("StartSearchButton");
        startSearchBtn.addActionListener(this);

        regexCheck = new JCheckBox("regex");
        regexCheck.setName("UseRegExCheckbox");

        previousBtn = new JButton("<");
        previousBtn.addActionListener(this);
        previousBtn.setEnabled(nextPrevBool);
        previousBtn.setName("PreviousMatchButton");

        nextBtn = new JButton(">");
        nextBtn.setName("NextMatchButton");
        nextBtn.setEnabled(nextPrevBool);
        nextBtn.addActionListener(this);


        searchPanel.add(searchField);
        searchPanel.add(regexCheck);
        searchPanel.add(previousBtn);
        searchPanel.add(nextBtn);
        searchPanel.add(startSearchBtn);



        searchPanel.setLocation(0,0);

        top.add(searchPanel);
        add(top,BorderLayout.NORTH);
        //add(center, BorderLayout.CENTER);




        setVisible(true);
    }


    private void save(){
        //File file = new File(fileNameField.getText());

        int returnValue = fileChooser.showSaveDialog(null);
        File file = null;

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            System.out.println(file.getAbsolutePath());
        }

            try{
                FileWriter pw = new FileWriter(file);
                pw.write(textArea.getText());
                pw.close();
                System.out.println("Save Button complete");
            }catch (FileNotFoundException e){
                System.out.println("File not found");
            }catch (IOException e){
                System.out.println("IO");
            }
    }

    private void open(){

        int returnValue = fileChooser.showOpenDialog(null);
        File file = null;

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            System.out.println(file.getAbsolutePath());
        }

       // File file = new File(fileNameField.getText());
        String s = "";
        byte[] fileBytes = {};
        fileBytes = new byte[(int) file.length()];
        try {
            FileInputStream fis = new FileInputStream(file);
            fis.read(fileBytes);
            System.out.println(new String(fileBytes));
            textArea.setText(new String(fileBytes));
            fis.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found");
            textArea.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void search(){
        search = searchField.getText();
        currentSearch =0;
        indexiesStart = new ArrayList<Integer>();
        indexiesString = new ArrayList<String>();

        if(search.equals("")){
            System.out.println("Search is empty");
            setButton(false);
        }else{

            if(regexCheck.isSelected()){
                searchRegex(search);
            }else{
                searchUsingText(search);
            }

            highLight();
        }
    }

    private void searchRegex(String search){

        Pattern pattern = Pattern.compile(search);
        Matcher matcher = pattern.matcher(textArea.getText());

        while(matcher.find()){
            System.out.println("FOund");

            indexiesStart.add(matcher.start());
            indexiesString.add(matcher.group(0));

        }
        System.out.println(indexiesString);

    }

    private void searchUsingText(String search){

        int index = textArea.getText().indexOf(search);


        if(index == -1){
            System.out.println("No Result");
            //setButton(false);
        }else{
            //indexies = new ArrayList<>();
            setButton(true);

            while (index >= 0) {
                System.out.println(index);
                indexiesStart.add(index);
                indexiesString.add(search);

                index = textArea.getText().indexOf(search, index + 1);

            }




        }
    }
    private void highLight(){


        int len = indexiesStart.get(currentSearch) + indexiesString.get(currentSearch).length();

        System.out.println(len);
        textArea.setCaretPosition(len);
        textArea.select(indexiesStart.get(currentSearch), len);
        textArea.grabFocus();
    }

//    private void highLight(ArrayList<Integer> end){
//
//        int len = indexiesStart.get(currentSearch)+ search.length();
//
//        System.out.println(len);
//        textArea.setCaretPosition(len);
//        textArea.select(indexiesStart.get(currentSearch), len);
//        textArea.grabFocus();
//    }

    private int  nextMatch( ){
        if(indexiesStart != null) {


            currentSearch++;
            if (currentSearch >= indexiesStart.size()) {
                currentSearch = 0;
            }
        }

        return currentSearch;

    }

    private int previousMatch( ){
        if(indexiesStart != null) {
            indexiesStart.size();

            currentSearch--;
            if (currentSearch < 0) {
                currentSearch = indexiesStart.size()-1;
            }
        }

        return currentSearch;

    }

    private void setButton(boolean b){
        nextBtn.setEnabled(b);
        previousBtn.setEnabled(b);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == save || actionEvent.getSource() == saveBtn){
            save();
        }

        if(actionEvent.getSource() == open || actionEvent.getSource() == openButton){
            open();
        }

        if(actionEvent.getSource() == startSearchBtn|| actionEvent.getSource() == startSearchItem){
            search();
        }

        if(actionEvent.getSource() == exit){
            System.out.println("exit menu item");
            dispose();
        }

        if(actionEvent.getSource() == nextBtn || actionEvent.getSource() == nextSearchItem){
            nextMatch();
            highLight();
        }

        if(actionEvent.getSource() == previousBtn || actionEvent.getSource() == previousSearchItem){
            previousMatch();
            highLight();
        }

        if(actionEvent.getSource() == regExItem){
            regexCheck.setSelected(true);
            search();
        }

    }
}
