package com.kkk.cocoapp.service.shadow;

import lombok.Data;

@Data
public class LinkState {

    private int coreSourceId;
    //0:未知 1：在线2：离线
    private int connectState;
}
