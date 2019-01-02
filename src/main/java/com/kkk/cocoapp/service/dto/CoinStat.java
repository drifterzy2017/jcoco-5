package com.kkk.cocoapp.service.dto;

import com.kkk.cocoapp.domain.Bill;
import com.kkk.cocoapp.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A DTO representing a user, with his authorities.
 */
public class CoinStat {
    public int AllCoinsCount=1;
    public int CoinCntToday=1;
    public int CoinCntLeft=1;

    public List<Product> Products = new ArrayList<Product>();
    public List<Integer> PriceList = new ArrayList<Integer>();

    public List<Bill> ProductBuyToday;

    public CoinStat() {
        // Empty constructor needed for Jackson.
    }

    public CoinStat(User user) {

    }


}
