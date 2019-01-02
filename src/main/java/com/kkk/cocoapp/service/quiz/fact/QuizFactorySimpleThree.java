package com.kkk.cocoapp.service.quiz.fact;


import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 13714 on 2018/12/21.
 */
@Service("SimpleThree")
@Transactional
public class QuizFactorySimpleThree extends QuizFact {


    @Override
    protected  void InitQuestionPool(){
        int MinNumber;
        int MaxNumber;

        val Paras = LibName.split("-");

        //   qLibNames: "plus-2-12"
        MinNumber = Integer.parseInt(Paras[1]);
        MaxNumber = Integer.parseInt(Paras[2]);

        val first = MinNumber;

        for (int i = first; i >= 4; i--) {
            for (int j = i - 1; j > 0; j--) {
                for (int k = j - 1; k > 0; k--) {
                    if (i - j - k < 0)
                        continue;

                    val ask = String.format("%s-%s-%s", i, j, k);
                    val answer = String.valueOf(i - j - k);
                    addOneLibQuestion(ask, answer);
                }
            }
        }

        for (int i = first; i >0; i--) {
            for (int j = first; j > 0; j--) {
                for (int k = 9; k > 0; k--) {
                    if (i + j - k < 0)
                        continue;

                    val ask = String.format("%s+%s-%s", i, j, k);
                    val answer = String.valueOf(i + j - k);
                    addOneLibQuestion(ask, answer);
                }
            }
        }

        for (int i = first; i > 0; i--) {
            for (int j = i-1; j > 0; j--) {
                for (int k = first; k > 0; k--) {
                    if (i - j + k < 0)
                        continue;

                    val ask = String.format("%s-%s+%s", i, j, k);
                    val answer = String.valueOf(i - j + k);
                    addOneLibQuestion(ask, answer);
                }
            }
        }
    }

}
