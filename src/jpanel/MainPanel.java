package jpanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.Timer;
import java.util.Vector;


public class MainPanel extends JPanel {
    private JTable urlTable = null;
    private JScrollPane urlTableScrollPane = null;
    private Vector<String> titleVector = null;
    private Vector<Vector<String>> urlVector = null;
    private JLabel urlNum = null;

    public MainPanel(){

        Vector<String> titleVector = new Vector<>();
        urlVector = new Vector<Vector<String>>();
        urlNum = new JLabel();

        titleVector.add("链接");
//        titleVector.add("描述");

        urlTable = new JTable(urlVector, titleVector){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        urlTable.setPreferredScrollableViewportSize(new Dimension(500,500));
        urlTableScrollPane = new JScrollPane(urlTable);
        urlTableScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        urlTableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

//        urlTable.setEnabled(false);
        urlTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JOptionPane.showConfirmDialog(null, "点击2次事件 ");
                }

            }

        });

        this.setLayout(new FlowLayout());
        this.add(urlTableScrollPane, BorderLayout.CENTER);
        this.add(urlNum, BorderLayout.SOUTH);
        this.setVisible(true);


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateGUI();
            }
        },2000,5000);
    }

    public void updateGUI()
    {
        urlVector.clear();
        try {
            System.setIn(new FileInputStream("urls.txt"));
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                Vector<String> vector = new Vector<>();
                vector.add(scanner.nextLine());
                urlVector.add(vector);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        urlTable.updateUI();
        urlNum.setText(""+urlVector.size());
        urlNum.updateUI();
    }
}
