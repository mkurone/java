/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author kurone
 */


class QuizGame extends JFrame {
    Quiz qp = new Quiz();
    static QuizGame sp;
    JButton btn = new JButton("スタート");
    JLabel paneltitle = new JLabel("クイズをはじめる");
    public QuizGame(){
        this.setLayout(null);
        this.add(qp);
        qp.setVisible(false);
        paneltitle.setBounds(200, 100, 100, 100);
        this.add(paneltitle);
        btn.setBounds(150, 200, 200, 40);
        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                qp.setVisible(true);
            }
        });
        this.add(btn);
        this.setTitle("たべものクイズ");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
    }
    public static void main(String[] args) {
        sp = new QuizGame();
        sp.setVisible(true);
    }
}
