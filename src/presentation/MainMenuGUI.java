package presentation;

import presentation.components.MenuBar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuGUI implements ActionListener {

    JFrame frame;
    JButton start;
    JButton quit;
    JComboBox<Integer> numPlayerBox;
    
    public MainMenuGUI(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(640,640));
        frame.setLayout(new BorderLayout());
        frame.setTitle("Texas HoldEm");
        frame.pack();
        frame.setLocationRelativeTo(null);
        
        // select menu
        JPanel selectMenu = new JPanel();
        JLabel numPlayerLabel = new JLabel("Select number of opponents");
        Integer[] numPlayers = {1, 2, 3, 4, 5, 6, 7};
        start = new JButton("Start");
        quit = new JButton("Quit");
        numPlayerBox = new JComboBox<>(numPlayers);

        selectMenu.setPreferredSize(new Dimension(100, 50));
        selectMenu.add(numPlayerLabel);
        selectMenu.add(numPlayerBox);
        selectMenu.add(start);
        selectMenu.add(quit);

        // title panel
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Texas Holdem");
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setFont(new Font("MV Boli", Font.BOLD, 32));
        titlePanel.add(title);

        // add components to frame
        frame.add(new MenuBar(), BorderLayout.NORTH);
        frame.add(titlePanel, BorderLayout.CENTER);
        frame.add(selectMenu, BorderLayout.SOUTH);

        // action listeners
        start.addActionListener(this);
        quit.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==start){
            int num = (int) numPlayerBox.getSelectedItem();

            frame.dispose();
            new TexasHoldemGUI(num);
        }
    }

}
