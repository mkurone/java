/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author kurone
 */
public class Quiz extends JPanel {

    JButton b1, b2, b3, nextb;
    JButton[] bt = new JButton[3];
    JLabel label;
    int mbutton = 0;
    int x = 20, y = 20;
    Image img = null;
    JPanel panel1,quizimg,ansp;
    ImageIcon icon;
    JFrame quiz;
    String[] quizData;
    int quizIndex,anscounter;
    String nowquiz,answer,answered,ansview;

    
    Quiz() {
        setSize(500, 500);
        setLayout(new GridLayout(2, 1));
        quizData = QuizList.getQuizData();
        quizIndex = 0;
        QuizComp(quizIndex);
    }
    
    private void QuizNew() {
        this.removeAll();//初期化
        setSize(500, 500);
        setLayout(new GridLayout(2, 1));
        anscounter = 0;
        quizData = QuizList.getQuizData();
        quizIndex = 0;
        QuizComp(quizIndex);
    }
    
    /**
     *
     */
    private void QuizComp(int quizIndex) {
        panel1 = new MyJPanel();
        quizimg = new MyJPanel();
        panel1.setLayout(new GridLayout(1, 3));
        nowquiz = QuizList.getQuiz(quizIndex);
        HashMap<String,String> buttonData = new HashMap<String,String>();
        buttonData = QuizList.getAnswerButton(nowquiz);
        int i = 0;
        for(String key : buttonData.keySet()){
           bt[i] = new JButton(buttonData.get(key));
           bt[i].addActionListener(new BtnListener());
           bt[i].setActionCommand(key);
           i++;
        }
        panel1.add(bt[0]);
        panel1.add(bt[1]);
        panel1.add(bt[2]);
        String path = "./src/img/" + nowquiz + ".png";
        ImageIcon img = new ImageIcon(path);
        label = new JLabel(img);
        quizimg.add(label);
        this.add(quizimg, "Center");
        this.add(panel1,"South");
    }
    
    public boolean hasNext() {
        return quizData.length > quizIndex + 1;            
    }
       
    private void answerTest() {
        this.removeAll();//初期化
        panel1 = new MyJPanel();
        quizimg = new MyJPanel();
        panel1.setLayout(new GridLayout(1, 3));
        nowquiz = QuizList.getQuiz(quizIndex);
        if(hasNext()) {
            b1 = new JButton("つぎのもんだいへ");
            b1.setActionCommand("next");
            b1.addActionListener(new BtnNext());  
        } else {
            b1 = new JButton("回答結果を確認");
            b1.setActionCommand("result");
            b1.addActionListener(new BtnNext()); 
        }
        panel1.add(b1);
        String path = "./src/img/" + nowquiz + ".png";
        ImageIcon img = new ImageIcon(path);

        JLabel label2 = new JLabel(answered);
        label2.setFont(new Font("Arial", Font.PLAIN, 28));

        quizimg.add(label);
        quizimg.add(label2);
        this.add(quizimg, "Center");
        this.add(panel1,"South");        
    }
    
    private void end() {
        this.removeAll();//初期化
        panel1 = new MyJPanel();
        quizimg = new MyJPanel();
        panel1.setLayout(new GridLayout(1, 3));
        b1 = new JButton("もう一回");
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                label.setText("");
                QuizNew();
            }
        });
        b2 = new JButton("おしまい");
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               System.exit(0);
            }
        });        
        
        
        panel1.add(b1);
        panel1.add(b2);
        String ansview = quizData.length + "問中" + anscounter + "問正解";
        label = new JLabel(ansview);
        quizimg.add(label);
        this.add(quizimg, "Center");
        this.add(panel1,"South");
    }

    private void next() {
        this.removeAll();//初期化
        QuizComp(++quizIndex);        
    }
    
    public static void main(String args[]){
        new QuizGame();
    }




class MyJPanel extends JPanel{

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(null, x, y, this);
        repaint();
    }
}

class BtnListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String ans = e.getActionCommand();
        if (ans == QuizList.getAnswer(quizIndex)) {
            ++anscounter;
            label.setText(" ");
            answered = "正解";
        }
        else {
            label.setText(" ");
            answered = "不正解";
        }
        answerTest();
    }
}

class BtnNext implements ActionListener {
    public void actionPerformed(ActionEvent a) {        
        String ans = a.getActionCommand();
        label.setText("");
        if (ans == "next"){
            next();
        } else {
            end();
        }
    }
}
}

