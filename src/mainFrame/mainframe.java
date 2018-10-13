package mainFrame;

import jpanel.MainPanel;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;



public class mainframe {


        public static void main(String[] args) {

        JFrame mainFrame = new JFrame();
        MainPanel mainPanel = new MainPanel();

        mainFrame.add(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 600);
        mainFrame.setVisible(true);

        try {
            String[] args_1 = new String[]{"python", "source.py"};
            Process pr = Runtime.getRuntime().exec(args_1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
