/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author kurone
 */


class QuizGame extends JFrame {
    Quiz qp = new Quiz();
    static QuizGame sp;
    JButton btn = new JButton("スタート");
    JLabel paneltitle = new JLabel("クイズ");
    public QuizGame(){
        this.setLayout(null);
        this.add(qp);
        qp.setVisible(false);
        paneltitle.setBounds(50, 50, 400, 40);
        this.add(paneltitle);
        btn.setBounds(50, 50, 200, 40);
        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                qp.setVisible(true);
                //btn.setVisible(false);
            }
        });
        this.add(btn);
        this.setTitle("クイズタイトル");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
    }
    public static void main(String[] args) {
        sp = new QuizGame();
        sp.setVisible(true);
    }
}
