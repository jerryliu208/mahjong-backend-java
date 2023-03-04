package com.backend.mahjong.data.mahjong;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CheckHandCardsResData {
    private List<String> allHandCards;

    private boolean isHu; //是否和牌

    private Map<String, Integer> pointsMap; //和牌的牌型以及台數列表
}
