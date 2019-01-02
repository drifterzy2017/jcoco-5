package com.kkk.cocoapp.service.quiz.fact;


import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 13714 on 2018/12/21.
 */
@Service("SimpleMinus")
@Transactional
public class QuizFactorySimpleMinus extends QuizFact {
    @Override
    protected  void InitQuestionPool(){
        val Paras = LibName.split("-");
        val MinNumber = Integer.parseInt(Paras[1]);
        val MaxNumber = Integer.parseInt(Paras[2]);


        for (int i = MaxNumber; i > MinNumber; i--) {
            for (int j = i - 1; j > 0; j--) {
                val ask =String.format("%s-%s", i, j);
                val answer =String.valueOf(i -  j);
                addOneLibQuestion(ask, answer);
            }
        }
    }

}
