package com.backend.mahjong.data.mahjong;

import lombok.Data;

import java.util.List;

@Data
public class HandCardsData {
    private List<Integer> handCards; //手牌

    private Integer lastCard; //最後一張牌

    private Integer prevailingWind; //場風

    private Integer dealersWind; //門風

    private boolean isDealer; //是否為莊家

    private Integer dealerCount; //連第幾次莊

    private boolean isSelfDrawn; //是否為自摸

    private boolean isDeadWallDraw; //是否槓上開花

    private boolean isAllConcealedHand; //是否為門清

    private boolean isBlessingOfHeaven; //是否天胡

    private boolean isBlessingOfEarth; //是否地胡

    private boolean isJenHu; //是否人胡
}
