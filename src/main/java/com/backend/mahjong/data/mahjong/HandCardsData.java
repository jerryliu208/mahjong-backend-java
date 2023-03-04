package com.backend.mahjong.data.mahjong;

import com.backend.mahjong.enums.MahjongCardEnum;
import lombok.Data;

import java.util.*;

@Data
public class HandCardsData {
    private List<Integer> handCards; //手牌

    private List<Integer> flowerCards; //花牌

    private Integer lastCard; //最後一張牌(胡到的牌)

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

    /**
     * 檢查是否胡牌
     * @return
     */
    public boolean checkIsHu(){
        if(this.handCards == null || this.lastCard == null) {
            return false;
        }
        List<Integer> handCards = new ArrayList<>(this.handCards);
        //加入最後一張拿到的牌
        handCards.add(this.lastCard);
        //取出此手牌中包含哪些牌
        Set<Integer> handCardsCategories = new HashSet<>(handCards);
        //先取出所有可能的雀頭
        Set<Integer> eyes = getEyes(handCards, handCardsCategories);
        if(eyes.isEmpty()) {
            return false;
        }
        //依照所有可能的雀頭下去判斷
        boolean isHu = false;
        for(Integer eye : eyes) {
            handCards = new ArrayList<>(this.handCards);
            //取出雀頭
            removeEye(handCards, eye);
            //取出刻子
            removeAllTriplet(handCards, handCardsCategories);
            //取出順子
            removeAllSequence(handCards);
            if(handCards.isEmpty()) {
                isHu = true;
                break;
            }
        }
        return isHu;
    }

    private Set<Integer> getEyes(List<Integer> handCards, Set<Integer> categories) {
        Set<Integer> eyesSet = new HashSet<>();
        categories.forEach(category -> {
            if(Collections.frequency(handCards, category) >= 2) {
                eyesSet.add(category);
            }
        });
        return eyesSet;
    }

    private void removeEye(List<Integer> handCards, Integer eye) {
        for(int i = 0; i < 2; i++) {
            handCards.remove(Integer.valueOf(eye));
        }
    }

    private void removeAllTriplet(List<Integer> handCards, Set<Integer> categories) {
        categories.forEach(category -> {
            int frequency = Collections.frequency(handCards, category);
            if(frequency >= 3 && frequency <=4) {
                handCards.removeIf(card -> card == category);
            }
        });
    }

    private void removeAllSequence(List<Integer> handCards) {
        Collections.sort(handCards);
        List<Integer> cardsToRemove = new ArrayList<>();
        for(int index = 0; index < handCards.size(); index++) {
            Integer card = handCards.get(index);
            if(card >= MahjongCardEnum.MILLION_ONE.getCode() && card <= MahjongCardEnum.TIAO_NINE.getCode()) {
                if(index+2 < handCards.size() && handCards.get(index+1) - card == 1 && handCards.get(index+2) - card == 2) {
                    cardsToRemove.add(card);
                    cardsToRemove.add(handCards.get(index+1));
                    cardsToRemove.add(handCards.get(index+2));
                    if(index+3 < handCards.size()) {
                        index+=2;
                    } else {
                        break;
                    }
                }
            }
        }
        handCards.removeAll(cardsToRemove);
    }

    /**
     * 算台數
     */
    public Map<String, Integer> scoring() {
        //TODO: 實現計算台數的邏輯
        return new HashMap<>();
    }
}
