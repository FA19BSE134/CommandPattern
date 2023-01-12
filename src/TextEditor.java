import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Stack;

import javax.swing.*;

public class TextEditor extends JFrame {


    JTextArea textArea;
    JButton undoBtn;
    JButton redoBtn;
    JScrollPane scrollPane;

    public StringBuilder text;
    public  CommandManager commandManager;
    public Stack<Command> undoStack;
    public Stack<Command> redoStack;
    TextEditor(){

        this.setTitle("Text Editor");
        this.setLayout(new FlowLayout());
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        text = new StringBuilder();
        commandManager = new CommandManager();
        undoStack = new Stack();
        redoStack = new Stack();


        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(450, 450));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        undoBtn = new JButton("Undo");
        redoBtn = new JButton("Redo");

        this.add(undoBtn);
        this.add(redoBtn);
        this.add(scrollPane);
        this.setVisible(true);

        textArea.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String newValue = textArea.getText();
                commandManager.executeCommand(new AddTextCommand(text, newValue));
            }
        });
        undoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                commandManager.undo();
                textArea.setText(text.toString());
            }
        });
        redoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                commandManager.redo();
                textArea.setText(text.toString());
            }
        });
    }
    public String deletedText;
    private void undo() {
        commandManager.undo();
        deletedText = textArea.getText().substring(text.toString().length());
        textArea.setText(text.toString());
    }

    private void redo() {
        commandManager.redo();
        textArea.append(deletedText);
    }
}