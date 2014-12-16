package main;

import com.shu_mj.datetime.DateTime;
import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;

/**
 * Created by Jun on 12/15/2014.
 */
public class Next extends JFrame {
    Scanner in = new Scanner(System.in);
    PrintWriter out = new PrintWriter(System.out);

    void run() {
        setBounds(200, 200, 230, 300);
        setTitle("计算下一天");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
        setVisible(true);
    }

    private void init() {
        setLayout(null);

        Label inputLabel = new Label("Please input month, month and day:");
        inputLabel.setAlignment(Label.CENTER);
        inputLabel.setBounds(0, 10, 220, 20);
        add(inputLabel);

        Label yLabel = new Label("Year");
        yLabel.setAlignment(Label.CENTER);
        yLabel.setBounds(0, 40, 100, 20);
        add(yLabel);

        Label mLabel = new Label("Month");
        mLabel.setAlignment(Label.CENTER);
        mLabel.setBounds(0, 70, 100, 20);
        add(mLabel);

        Label dLabel = new Label("Day");
        dLabel.setAlignment(Label.CENTER);
        dLabel.setBounds(0, 100, 100, 20);
        add(dLabel);


    }

    public static void main(String[] args) {
        new Next().run();
    }
}
