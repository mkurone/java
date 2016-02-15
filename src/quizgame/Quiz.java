/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
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
    int x = 10, y = 10;
    Image img = null;
    JPanel panel1,panel2,ansp;
    ImageIcon icon;
    JFrame quiz;
    String[] quizData;
    int quizIndex,anscounter;
    String nowquiz,answer;

    
    Quiz() {
        setSize(500, 500);
        setLayout(new GridLayout(2, 1));
        quizData = QuizList.getQuizData();
        quizIndex = 0;
        QuizComp(quizIndex);
    }
    
    /**
     *
     */
    private void QuizComp(int quizIndex) {
        panel1 = new MyJPanel();
        panel2 = new MyJPanel();
        panel1.setLayout(new GridLayout(1, 3));
        nowquiz = QuizList.getQuiz(quizIndex);
        HashMap<String,String> buttonData = new HashMap<String,String>();
        buttonData = QuizList.getAnswerButton(nowquiz);
        int i = 0;
        for(String key : buttonData.keySet()){
                            //System.out.println(buttonData.get(key));

           bt[i] = new JButton(buttonData.get(key));
           bt[i].addActionListener(new BtnListener());
           bt[i].setActionCommand(key);
           i++;
        }
        //System.out.println(Arrays.toString(bt));

        //String ansv = buttonData.get(nowquiz);
        //b1 = new JButton(ansv);
        //b2 = new JButton("travis");
        //b3 = new JButton("slack");
        //b1.addActionListener(new BtnListener());
        //b2.addActionListener(new BtnListener());
        //b3.addActionListener(new BtnListener());
        //panel1.add(b1);
        //panel1.add(b2);
        //panel1.add(b3);
        panel1.add(bt[0]);
        panel1.add(bt[1]);
        panel1.add(bt[2]);


        String path = "./src/img/" + nowquiz + ".png";
        ImageIcon img = new ImageIcon(path);
        label = new JLabel(img);
        panel2.add(label);

        this.add(panel2, "Center");
        this.add(panel1,"South");
    }
    
    public boolean hasNext() {
        return quizData.length > quizIndex + 1;            
    }
    
    private void answer() {
        this.removeAll();//初期化
        answerTest();
    }
    
    private void answerTest() {
        panel1 = new MyJPanel();
        panel2 = new MyJPanel();
        panel1.setLayout(new GridLayout(1, 3));
        nowquiz = QuizList.getQuiz(quizIndex);
        b1 = new JButton("次へ");
        b1.addActionListener(new BtnNext());
        panel1.add(b1);
        
        String path = "./src/img/" + nowquiz + ".png";
        ImageIcon img = new ImageIcon(path);

        label = new JLabel(img);
        JLabel label2 = new JLabel(answer);
        panel2.add(label);
        panel2.add(label2);
        this.add(panel2, "Center");
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
        JButton tmp = (JButton) e.getSource();
        String ans = tmp.getText();
                        //System.out.println(ans);

        if (ans == QuizList.getAnswer(quizIndex)) {
            ++anscounter;
            label.setText("正解");
        }
        else {
            label.setText("不正解");
        }

        if(hasNext()) {
            answer();            
        }
            
    }
}

class BtnNext implements ActionListener {
    public void actionPerformed(ActionEvent a) {
        JButton tmp = (JButton) a.getSource();
        String ans = tmp.getText();
        if (ans == QuizList.getAnswer(quizIndex)) {
            label.setText("正解");
        }
        else {
            label.setText("不正解");

        }
        next();
    }
}
}

