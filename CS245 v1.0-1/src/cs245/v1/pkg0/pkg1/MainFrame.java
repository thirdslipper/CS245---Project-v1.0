package cs245.v1.pkg0.pkg1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author FelixZhang
 */
public class MainFrame  extends JFrame{
    private CardLayout layout = new CardLayout();
    private JPanel mainP = new JPanel(layout);
    private MainMenu mainMenu = new MainMenu();
    private HighScore hScore = new HighScore();
    private Credits credit = new Credits();
    private TitlePanel title = new TitlePanel();
    private PlayGame play = new PlayGame();
    private Timer timer;
    
    /*
    Constructor for main frame.
    */
    public MainFrame() {
        initUI();
    }
    
    
    public void stopMenuTimer() {
        mainMenu.stopTimer();
    }
    
    public void startMenuTimer() {
        mainMenu.startTimer();
    }
    
    
    
    
    /*
    initializer method to draw the frame and add elements to it.
    */
    private void initUI() {
        
        startLayout();
        introTimer();
        
        
        setTitle("PlaceHolder title");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        add(mainP);
        //pack();
    }
    /*
    I moved the panel initializations into another method
    to clean up the initUI() method.
    */
    private void startLayout() {
        mainP.add(title, "title");
        mainP.add(mainMenu, "menu");
        mainP.add(hScore, "High Score");
        mainP.add(credit, "credits");
        mainP.add(play, "play");
        
        mainMenu.setMain(this);
        credit.setMain(this);
        hScore.setMain(this);

    }
    
    
    /*
    Timer to set the 3 second delay between the title panel
    and the main menu.
    */
    private void introTimer() {
        
        int delay = 3000;
        
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                layout.show(mainP, "menu");
            }
            
        });
        timer.start();
    }
    /*
    This is used by the sub panels inside the layout to be able to change to
    other panels. Not sure if this is the correct way to do this but it works.
    */
    public void swapView(String key) {
        layout.show(mainP, key);
    }
    
    
   
    

    /**
     * 
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainFrame menu = new MainFrame();
            menu.setVisible(true);
        });
    }
    
}