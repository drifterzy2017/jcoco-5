package com.kkk.cocoapp.service.quiz.fact;


import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 13714 on 2018/12/21.
 */
@Service("SimplePlus")
@Transactional
public class QuizFactorySimplePlus extends QuizFact {

    @Override
    protected  void InitQuestionPool(){
        val Paras = LibName.split("-");


        val MinNumber = Integer.parseInt(Paras[1]);
        val MaxNumber = Integer.parseInt(Paras[2]);


        val AskType = Paras[0];

        for (int i = MinNumber; i < MaxNumber; i++) {
            for (int j = MinNumber; j < MaxNumber; j++) {
                if (i + j > 100)
                    continue;

                val ask = String.format("%s+%s", i, j);
                val answer = String.valueOf(i + j);
                addOneLibQuestion(ask, answer);
            }
        }
    }



}
