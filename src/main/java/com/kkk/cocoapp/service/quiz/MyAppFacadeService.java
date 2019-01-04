package com.kkk.cocoapp.service.quiz;

import com.google.common.primitives.Ints;
import com.kkk.cocoapp.config.ApplicationProperties;
import com.kkk.cocoapp.domain.Bill;
import com.kkk.cocoapp.domain.Quiz;
import com.kkk.cocoapp.repository.BillRepository;
import com.kkk.cocoapp.repository.LibQuestionRepository;
import com.kkk.cocoapp.repository.QuizRepository;
import com.kkk.cocoapp.service.dto.CoinStat;
import com.kkk.cocoapp.service.dto.Product;
import com.kkk.cocoapp.service.quiz.fact.QuizFact;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

/**
 * Service Interface for managing Quiz.
 */
@Service
@Transactional
public class MyAppFacadeService {
     final ApplicationProperties AppParameter;

    private final QuizRepository quizRepository;
    private final BillRepository billRepository;
    private final LibQuestionRepository libQuestionRepository;

    private final Map<String, QuizFact> myQuizFacts;

    private ArrayList<Product> Products ;
    private HashSet<Integer> PriceList ;

    private final QuizFact quizFact;

    public MyAppFacadeService(ApplicationProperties appParameter, QuizRepository quizRepository, BillRepository billRepository, LibQuestionRepository libQuestionRepository, Map<String, QuizFact> myQuizFacts) {
        this.AppParameter = appParameter;
        this.quizRepository = quizRepository;
        this.billRepository = billRepository;
        this.libQuestionRepository = libQuestionRepository;
        this.myQuizFacts = myQuizFacts;

        String[] Paras = AppParameter.getQLibNames().split("-");
        String libName = Paras[0];
        quizFact = myQuizFacts.get(libName);
//        quizFact = myQuizFacts.get("mix");

        LoadData();
    }

    //    @Override
    public Quiz autoCreateOne(){
        //   qLibNames: "plus-2-12"

        return  quizFact.FactoryMethod_CreateTest();

    }

    private void LoadData() {
        if(this.Products != null)
            return;

        this.Products = new ArrayList<Product>();
        this.PriceList = new HashSet<Integer>();

        String strPath = "d:/Content/reward";
        val fileNames = getFiles(strPath);

        for(val f:fileNames){
            val s =f.substring(0,1);
            val si = Ints.tryParse(s);
            if(si==null)
                continue;
            if(!this.PriceList.contains(s))
                this.PriceList.add(si);

            val  product = new Product();
            product.Price = si;
            product.Name = f.toUpperCase().replace(".GIF", "");
            product.Url = "Content/reward/" + f;
            this.Products.add(product);
        }


    }
    public static ArrayList<String> getFiles(String path) {
        ArrayList<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
//                files.add(tempList[i].toString());
                files.add(tempList[i].getName());

            }
            if (tempList[i].isDirectory()) {
            }
        }
        return files;
    }



    public boolean save(Quiz quiz){
        val val = 100.0f * quiz.getPassCount() / (quiz.getPassCount() + quiz.getFailCount());
        quiz.setMark(val);

        quiz.setSuccessTime(Instant.now() );

        long span = Duration.between( quiz.getStartTime(), quiz.getSuccessTime()).toMillis()/1000;
        quiz.setCostSeconds((int)span);

        boolean isTopMost = false;
        if (quiz.getTopmostSeconds() > 0 && quiz.getCostSeconds()< quiz.getTopmostSeconds()) {
            isTopMost = true;
        }

        val point = isTopMost?2:1;
        quiz.setPoint(point);


        Quiz result = quizRepository.save(quiz);

        for(val q : result.getQuestions()){
            if(q.isIsPass()==null)
                continue;

            UpdatePassCount(q.getId(), q.isIsPass());
        }

        return isTopMost;
    }
    private void UpdatePassCount (Long id, boolean isPass){
        val q = libQuestionRepository.getOne(id);

        Boolean IsPass= q.isIsPass();
        Integer CountPass = q.getCountPass();
        Integer CountFail = q.getCountFail();
        Integer CountPassAgain = q.getCountPassAgain();
        if(CountPass==null) CountPass=0;
        if(CountFail==null) CountFail=0;
        if(CountPassAgain==null) CountPassAgain=0;
        if(isPass){
            CountPass++;        }
        else
            CountFail++;

        if(isPass) {
            CountPassAgain++;
        }
        else
            CountPassAgain=0;

        q.setCountPassAgain(CountPassAgain);
        q.setIsPass(isPass);

        val countRate = 100*CountPass/(CountPass+CountFail);

        q.setCountPass(CountPass);
        q.setCountFail(CountFail);
        q.setCountRate(countRate);

        libQuestionRepository.save(q);
    }

    public CoinStat getCoinStat(){
        LocalDateTime today_start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        ZoneId zone = ZoneId.systemDefault();
        Instant ins = today_start.atZone(zone).toInstant();


        val stat = new CoinStat();
        stat.AllCoinsCount = quizRepository.getAllCoinsCount();

        val cv = quizRepository.getCoinCntLeft();
        stat.CoinCntLeft = cv.orElse(0L).intValue();

        stat.CoinCntToday = quizRepository.getCoinCntToday(ins);


        stat.ProductBuyToday = this.billRepository.findAllToday(ins);

        stat.Products = this.Products;
        stat.PriceList = new ArrayList(this.PriceList);
        return stat;
    }

    public String buyProduct(int coinCount, String UseNote){
        val v = quizRepository.getCoinCntLeft().orElse(0L);
        if (coinCount > v)
            return "余额不足";

        val leftCoins = quizRepository.findAllByUsed(false);

        int toPay = coinCount;
        for (int i = 0; i < coinCount; i++) {
            val quiz = leftCoins.get(i);
            quiz.setUsed(true);
            quiz.setUseTime( Instant.now());
            quiz.setUseNote(UseNote);

            quizRepository.save(quiz);
            toPay -= quiz.getPoint();
            if(toPay<=0)
                break;
        }

        val bill = new Bill();
        bill.setName(UseNote);
        bill.setPrice(coinCount);
        bill.setBuyTime(Instant.now());

        val p = this.Products.stream().filter(o-> o.Name.equals(UseNote)).findFirst();
        if(p.isPresent()){
            Product pp = p.get();
            bill.setUrl(pp.Url);
        }
        else
            bill.setUrl("Content/reward/X-自定义.gif");


        billRepository.save(bill);

        return  "ok";
    }


}
