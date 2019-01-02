package com.kkk.cocoapp.service.quiz.fact;


import com.kkk.cocoapp.domain.LibQuestion;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * Created by 13714 on 2018/12/21.
 */
@Service("mix")
@Transactional
public class QuizFactMix extends QuizFact {


    protected  void InitQuestionPool(){
        int sizeOfLib = 5000;
        int MyQuestionPool=0;
        while(MyQuestionPool<sizeOfLib/2){
            val a = GetOneNumber();
            val b = GetOneNumber();
            val c = GetOneNumber();
            if (a == b || a == c || b == c)
                continue;

            if(a-b<0 || a-b+c< 0)
                continue;

            LibQuestion t = new LibQuestion();
            val ask= ( String.format("%s-%s+%s", a, b, c));
            val answer =  ( String.valueOf(a-b+c));
            addOneLibQuestion(ask, answer);
            MyQuestionPool++;

        }

        while(MyQuestionPool<sizeOfLib){
            val a = GetOneNumber();
            val b = GetOneNumber();
            val c = GetOneNumber();

            if (a == b || a == c || b == c)
                continue;

            if(a+b-c<0)
                continue;

            LibQuestion t = new LibQuestion();

            val ask=  ( String.format("%s+%s-%s", a, b, c));
            val answer = ( String.valueOf(a+b-c));
            addOneLibQuestion(ask, answer);
            MyQuestionPool++;
        }
    }

    private int GetOneNumber() {
        Random r= new Random();
        int a=0;
        while(true){
            a = r.nextInt(99);
            if (a < 10)
                continue;

            return a;
        }
    }

}
