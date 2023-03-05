package com.backend.mahjong.data.mahjong;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CheckHandCardsResData {
    private List<String> handCards; //手牌

    private List<String> notConcealedCards; //門前牌

    private String lastCard; //最後胡到的牌

    private boolean isHu; //是否和牌

    private Map<String, Integer> pointsMap; //和牌的牌型以及台數列表
}
