package com.kkk.cocoapp.service.quiz.fact;


import com.kkk.cocoapp.config.ApplicationProperties;
import com.kkk.cocoapp.domain.LibQuestion;
import com.kkk.cocoapp.domain.Question;
import com.kkk.cocoapp.domain.Quiz;
import com.kkk.cocoapp.repository.QuizRepository;
import com.kkk.cocoapp.service.LibQuestionService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 13714 on 2018/12/21.
 */

public abstract class QuizFact {
    public String LibName;
    private Random r= new Random();


    @Autowired
    protected QuizRepository quizRepository;


    @Autowired
    protected LibQuestionService libQuestionService;

    @Autowired
    ApplicationProperties AppParameter;

    @Autowired
    private CacheManager cacheManager;


    public  QuizFact(){


    }
    public Quiz FactoryMethod_CreateTest() {
        this.LibName = AppParameter.getQLibNames();

        val newTest = new Quiz();
        newTest.setLibName(this.LibName);
        newTest.setTestKey(UUID.randomUUID().toString());
        newTest.setMyRound(AppParameter.getMyRound());
        newTest.setIsAutoSubmit(AppParameter.getIsAutoSubmit()==1);
        newTest.setStartTime(Instant.now());


        newTest.setPassCount(0);
        newTest.setFailCount(0);
        newTest.setCentCount(0);
        newTest.setUsed(false);

//        coinService.
        val topOne = quizRepository.getTopOne(LibName);
        val theTopmost = topOne.orElse(0);
        newTest.setTopmostSeconds(theTopmost);


        newTest.setTestName( this.CreateTestName());


        //acctually it is init
        List<LibQuestion> list = libQuestionService.findAllByLibName(LibName);
        if(list.isEmpty()) {
            InitQuestionPool();

//            val test = libQuestionService.findOne((long) 104451);

            //kkk 在此触发Save to db (flush)
            val cache = cacheManager.getCache(com.kkk.cocoapp.repository.LibQuestionRepository.LIBQ_BY_NAME_CACHE);
            cache.clear();
            list = libQuestionService.findAllByLibName(LibName);
        }

        if(!list.isEmpty()) {
            SetQuestions(newTest, list);
        }

        return newTest;
    }

    protected abstract void InitQuestionPool();

    protected  void addOneLibQuestion(String ask, String answer){
        LibQuestion t = new LibQuestion();
        t.setLibName(LibName);
        t.setAsk(ask);
        t.setAnswer(answer);

        t.setCountPass(0);
        t.setCountFail(0);
        t.setCountPassAgain(0);
        t.setCountRate(0);

        libQuestionService.save(t);
    }

    void SetQuestions(Quiz quiz, List<LibQuestion> MyQuestionPool) {
        val ques = new ArrayList<Question>();

        //one round from undo-rand
        List<LibQuestion> todos = MyQuestionPool.stream().filter(o->o.getCountRate()==0).collect(Collectors.toList());
        if(!todos.isEmpty())
            for (int i = 0; i < AppParameter.getMyRound()/2 ; i++) {
                val question = GetOneQuestionRandomly(todos);
                ques.add(question);
            }

        //one round from last-warong
        todos= MyQuestionPool.stream().filter(o->o.isIsPass()!=null &&o.isIsPass()==false).collect(Collectors.toList());
        if(!todos.isEmpty())
            for (int i = 0; i < AppParameter.getMyRound()/2 ; i++) {
                val question = GetOneQuestionRandomly(todos);
                ques.add(question);
            }

        //连续10次对，0.4的几率出题
//        int nTry = 0;
//        while (q.getCountPassAgain()!=null && q.getCountPassAgain() > 10 && r.nextInt(100) > 90) {
//            pos = r.nextInt(MyQuestionPool.size());
//            q =  MyQuestionPool.get(pos);
//
//            if (++nTry > 10)
//                break;
//        }

        //4倍题库
        val moreAddCnt = AppParameter.getMyRound()*4 - ques.size();
        if (moreAddCnt > 0) {
            val listSorted = MyQuestionPool.stream().sorted((a,b) ->  a.getCountRate()-b.getCountRate()).collect(Collectors.toList());

            for(int k=0;k<moreAddCnt;k++){
                val qk = listSorted.get(k);
                ques.add( LibQ2Q(qk));
            }
        }

//        Collections.shuffle(ques);

        for(int i=0;i<ques.size();i++) {
            val q=ques.get(i);
            quiz.addQuestion(q);
        }
    }

    String CreateTestName(){
        Date now = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss_");

        String qLibNames = AppParameter.getQLibNames();
        String TestName = ft.format(now )+ qLibNames;
        return  TestName;
    }
    Question GetOneQuestionRandomly(List<LibQuestion> candidators){
        //随机



        int pos = r.nextInt(candidators.size());
        LibQuestion q = candidators.get(pos) ;


        Question testq = LibQ2Q(q);

        return testq;
    }
    Question LibQ2Q(LibQuestion q){
        Question testq = new Question();

        testq.setCountPass(q.getCountPass());
        testq.setCountFail(q.getCountFail());
        testq.setCountPassAgain(q.getCountPassAgain());

        testq.setId(q.getId());
        testq.setAsk(q.getAsk());
        testq.setAnswer(q.getAnswer());
        testq.setCountRate(q.getCountRate());
        testq.setCountPassAgain(q.getCountPassAgain());

        return testq;
    }

}
