/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author kurone
 */
public class QuizList {
    static String [] quizData;
    static HashMap<String,String> answerData = new HashMap<String,String>();

    static String success;

    
    QuizList() {
        quizData = new String[] { "takoyaki", "hamburg", "nattou" };
        answerData.put("takoyaki", "たこやき");
        answerData.put("hamburg", "ハンバーグ");
        answerData.put("nikuman", "にくまん");
        answerData.put("nattou", "納豆");
        answerData.put("sandwitch", "サンドウィッチ");
        
    }
    
    static String[] getQuizData() {
        quizData = new String[] { "takoyaki", "hamburg", "nattou" };
        return quizData;
    }
    
    static String getQuiz(int num) {
        return quizData[num];
    }
    
    static String getAnswer(int num) {
        return quizData[num];
    }
    
    static HashMap<String, String> getAnswerButton(String ans) {
        //回答ボタン
        HashMap<String, String> data = getAnswerData();
        //data.remove(ans);//回答データを配列から削除
        String[] keys = data.keySet().toArray(new String[data.size()]);
        Random rnd = new Random();
        HashMap<String,String> button = new HashMap<String,String>();
      
//System.out.println(Arrays.toString(keys));
        //List<String> list = Arrays.asList(keys);
        //Listを削除するため
        List<String> list = new ArrayList<String>(Arrays.asList(keys));
        Collections.shuffle(list);
        for(int i=0; i<keys.length; ++i)
        {
            String key = list.get(i);
            if(key == ans)
              continue;

            list.remove(key);
            if(list.size() == 3)
                break;
        }
        String[] array2 =(String[])list.toArray(new String[list.size()]);
//System.out.println(Arrays.toString(array2));

        for(int num=0; num<array2.length; ++num) {
            button.put(array2[num], data.get(array2[num]));
        }
        return button;
    }
    
    static HashMap<String, String> getAnswerData(){
        answerData.put("takoyaki", "たこやき");
        answerData.put("hamburg", "ハンバーグ");
        answerData.put("nikuman", "にくまん");
        answerData.put("nattou", "納豆");
        answerData.put("sandwitch", "サンドウィッチ");
        return answerData;
    }

}