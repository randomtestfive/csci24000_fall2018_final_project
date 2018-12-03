package tmhorne.fairychess.graphics;

import tmhorne.fairychess.BoardRegistry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuFrame extends JFrame implements ActionListener {
    private JComboBox combo;
    private JButton go;
    private String[] boards;

    /**
     * initializes the main menu screen
     *
     * creates a combo box and button
     */
    public MainMenuFrame() {
        // get all the boards
        boards = BoardRegistry.listBoards();

        // create combobox from boards
        combo = new JComboBox(boards);

        // go button
        go = new JButton("go");
        go.addActionListener(this);

        // better than a flow layout
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.add(combo);
        this.add(go);

        // jframe setup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        new GameFrame(boards[combo.getSelectedIndex()]);
        this.setVisible(false);
        this.dispose();
    }
}
