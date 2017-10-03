package cs245.v1.pkg0.pkg1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author FelixZhang
 */
public class MainMenu extends JPanel {
    
    private JButton play = new JButton("Play");
    private JButton hScore = new JButton("High Score");
    private JButton credit = new JButton("Credits");
    private JPanel menuButtons = new JPanel();
    private JPanel buttons = new JPanel();
    private CardLayout slideShow = new CardLayout();
    private JPanel pictures = new JPanel(slideShow);
    private boolean show = false;
    private Timer timer;
    private MainFrame main;
    
    
    public MainMenu() {
        setBackground(Color.black);
        pictures.setBackground(Color.black);
        setLayout(new BorderLayout());
        
        add(menuBtns(), BorderLayout.LINE_END);
        add(pictures, BorderLayout.LINE_START);
        
        displayLogo();
        
        
    }
    
    public void setMain(MainFrame panel) {
        this.main = panel;
    
    }
    
    public void stopTimer() {
        timer.stop();
    }
    
    public void startTimer() {
        timer.start();
    }
    
    
    /*
    I used a java swing timer to slideshow the two 
    logo pictures.
    */
    private void displayLogo() {
        ImageIcon img = new ImageIcon(new ImageIcon("thunking.png").getImage().getScaledInstance(375, 375, Image.SCALE_SMOOTH));
        JLabel label = new JLabel("", img, JLabel.CENTER);
        ImageIcon img2 = new ImageIcon(new ImageIcon("thunking2.png").getImage().getScaledInstance(375, 375, Image.SCALE_SMOOTH));
        JLabel label2 = new JLabel("", img2, JLabel.CENTER);
        
        pictures.add(label, "one");
        pictures.add(label2, "two");
        
        
        timer = new Timer(1000, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (show) {
                    slideShow.show(pictures, "one");
                    show = false;
                } else {
                    slideShow.show(pictures, "two");
                    show = true;
                }
            }
            
        });
        timer.start();
    }
    
    
    private JComponent menuBtns() {
        
        //setting layout of inner panel
        menuButtons.setLayout(new BorderLayout());

        //setting up layout of panel that goes inside menuButtons
        buttons.setPreferredSize(new Dimension(200, 150));
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        buttons.add(play);
        buttons.add(Box.createRigidArea(new Dimension(0, 10)));
        buttons.add(hScore);
        buttons.add(Box.createRigidArea(new Dimension(0, 10)));
        buttons.add(credit);

        //resizing the buttons so it looks like the example
        //also setting all of the buttons to be the same size
        hScore.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        play.setMaximumSize(hScore.getMaximumSize());
        credit.setMaximumSize(hScore.getMaximumSize());

        //right aligningt the buttons
        play.setAlignmentX(Component.RIGHT_ALIGNMENT);
        hScore.setAlignmentX(Component.RIGHT_ALIGNMENT);
        credit.setAlignmentX(Component.RIGHT_ALIGNMENT);

        //finally add the inner panel to menu panel and return.
        menuButtons.add(buttons, BorderLayout.PAGE_END);
        
        //menuButtons.setBackground(Color.BLACK);
        //buttons.setBackground(Color.BLACK);
        
        hScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTimer();
                main.swapView("High Score");
            }
        });
        
        credit.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                stopTimer();
                main.swapView("credits");
            }
        });
        
        
        play.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                stopTimer();
                main.swapView("play");
            }
        });
        
        menuButtons.setBackground(Color.black);
        buttons.setBackground(Color.black);
        menuButtons.setOpaque(false);
        buttons.setOpaque(false);

        return menuButtons;
        
        
        
    }
    
    
    
    
    
}