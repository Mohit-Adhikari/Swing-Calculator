import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static JTextField display;

    public static void main(String[] args) {

        JFrame frame=new JFrame("Calculator");
        frame.setLayout(new BorderLayout());
        frame.setSize(460,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        String trig[]={"sin","cos","tan","+","-","*","/","^","sqrt","x100","Del","AC"};
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
                display.setText(display.getText()+" " + printable);

            }
        });



    }
}