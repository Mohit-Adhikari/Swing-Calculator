import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
public class Main {
    public static JTextField display;

    public static void main(String[] args) {


        JFrame frame=new JFrame("Calculator");
        frame.setLayout(new BorderLayout());
        frame.setSize(460,420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar=new JMenuBar();
        JMenu file=new JMenu("File");
        JMenu description=new JMenu("description");
        JMenuItem history=new JMenuItem("History");
        JMenuItem credits=new JMenuItem("Credits");

        file.add(history);
        file.add(credits);

        menuBar.add(file);
        menuBar.add(description);

        frame.setJMenuBar(menuBar);
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(new File("history.txt"));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error opening history file: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        JPanel clickables=new JPanel();
        clickables.setLayout(new BorderLayout());


        JPanel numbers=new JPanel();
        numbers.setLayout(new GridLayout(4,3));
        numbers.setPreferredSize(new Dimension(200,250));
        numbers.setBackground(Color.BLACK);
        for(int i=9;i>=0;i--)
        {
            add_button(numbers,String.valueOf(i));
        }
        add_button(numbers,"O.O");
        add_button(numbers,"=");
        JPanel trigonometry=new JPanel();
        trigonometry.setLayout(new GridLayout(4,3));
        trigonometry.setPreferredSize(new Dimension(250,250));
        trigonometry.setBackground(Color.black);
        String trig[]={"sin","cos","tan","+","-","*","/","^","sqrt","(",")","AC"};
        for(int i=0;i<12;i++)
        {
            add_button(trigonometry,trig[i]);

        }

        JPanel screen=new JPanel();
        //screen.setLayout(new FlowLayout());
        screen.setPreferredSize(new Dimension(450,100));
        screen.setBackground(Color.blue);
        display=new JTextField();
        display.setBackground(Color.BLUE);
        display.setPreferredSize(new Dimension(450,100));
        Font text=new Font("Ariel",Font.PLAIN,20);
        display.setFont(text);
        screen.add(display);
        display.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    showLastHistoryLine();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });




        clickables.add(numbers,BorderLayout.EAST);
        clickables.add(trigonometry,BorderLayout.WEST);
        frame.add(clickables,BorderLayout.SOUTH);
        frame.add(screen,BorderLayout.NORTH);
        frame.setVisible(true);
    }
    public static void add_button(JPanel panel,String printable)
    {
        JButton button=new JButton(printable);
        Font buttonfont=new Font("Ariel",Font.PLAIN,20);
        button.setFont(buttonfont);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String x=display.getText();
                System.out.println(x);
                if(printable.equals("=")==false)
                {
                    display.setText(display.getText()+"" + printable);
                }

                if(printable.equals("=")==true)
                {
                    fileSave save=new fileSave();
                    save.openWrite(display.getText());
                   double result= Functions.getResult(display.getText());
                   display.setText(String.valueOf(result));

                } else if (printable.equals("AC")==true) {
                    display.setText(" ");

                }

            }
        });



    }
    public static void showLastHistoryLine() {
        try {
            File historyFile = new File("history.txt");
            if (!historyFile.exists()) {
                // Create history file if it doesn't exist
                historyFile.createNewFile();
            }

            FileReader reader = new FileReader(historyFile);
            BufferedReader bufferedReader = new BufferedReader(reader);

            StringBuilder historyContent = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                historyContent.append(line).append(System.lineSeparator());
            }

            bufferedReader.close();
            reader.close();

            String lastLine = null;
            String[] lines = historyContent.toString().split(System.lineSeparator());

            if (lines.length > 0) {
                lastLine = lines[lines.length - 1];
                // Remove the last line from historyContent
                historyContent.delete(historyContent.length() - lastLine.length() - System.lineSeparator().length(), historyContent.length());
            }

            if (lastLine != null) {
                display.setText(lastLine);

                // Write the updated history back to the file
                Files.write(Paths.get("history.txt"), historyContent.toString().getBytes());
            } else {
                display.setText("History is empty.");
            }

        } catch (IOException e) {
            // Handle file access errors gracefully
            display.setText("Error reading history: " + e.getMessage());
        }
    }

}