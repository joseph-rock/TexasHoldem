package presentation.components;

import presentation.MainMenuGUI;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    public MenuBar() {
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        JMenuItem m11 = new JMenuItem("New Game");
        JMenuItem m12 = new JMenuItem("Quit");
        
        this.add(m1);
        this.add(m2);
        m1.add(m11);
        m1.add(m12);

        m11.addActionListener(e -> new MainMenuGUI());
        m12.addActionListener(e -> System.exit(0));
    }
}
