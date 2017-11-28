package com.panz.service.rxpic;

import com.panz.entity.SPic;

import java.util.List;

public interface RxpicService {
    List<SPic> getRxPic(Integer userId);
    int addRxPic(SPic sPic);
}
