package com.backend.mahjong.data.mahjong;

import lombok.Data;

import java.util.Map;

@Data
public class CheckHandCardsResData {
    private HandCardsData handCardsData;
    private boolean isHu; //是否和牌
    private Map<String, Integer> pointsMap; //和牌牌型以及台數

    public CheckHandCardsResData(HandCardsData handCardsData){
        this.handCardsData = handCardsData;
    }

    public void checkIsHu(){
        // TODO: 實現判斷牌型的邏輯
        // 如果手牌的數量不能被3整除，表示無法構成牌型
        if (this.handCardsData.getHandCards().size() % 3 != 0) {
            this.isHu = false;
        }

        this.isHu = false;
    }

    public void scoring(){
        //TODO: 實現計算台數的邏輯
    }
}
