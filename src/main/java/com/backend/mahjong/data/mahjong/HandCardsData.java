package com.backend.mahjong.data.mahjong;

import com.backend.mahjong.enums.MahjongCardEnum;
import lombok.Data;

import java.util.*;

@Data
public class HandCardsData {
    private List<Integer> handCards; //手牌

    private List<Integer> notConcealedCards = new ArrayList<>(); //門前牌

    private List<Integer> flowerCards; //花牌

    private Integer lastCard; //最後一張牌(胡到的牌)

    private Integer prevailingWind; //場風

    private Integer dealersWind; //門風

    private boolean isDealer; //是否為莊家

    private Integer dealerCount; //連第幾次莊

    private boolean isSelfDrawn; //是否為自摸

    private boolean isDeadWallDraw; //是否槓上開花

    private boolean isBlessingOfHeaven; //是否天胡

    private boolean isBlessingOfEarth; //是否地胡

    private boolean isJenHu; //是否人胡

    /**
     * 檢查是否胡牌
     *
     * @return 是否胡牌
     */
    public boolean checkIsHu() {
        if (this.handCards == null || this.lastCard == null) {
            return false;
        }
        //從手牌中取出所有可能的雀頭
        List<Integer> handCardsToGetEye = new ArrayList<>(this.handCards);
        handCardsToGetEye.add(this.lastCard); //加入最後一張拿到的牌
        Set<Integer> eyes = getEyes(handCardsToGetEye); //取出所有可能的雀頭
        if (eyes.isEmpty()) {
            return false;
        }
        //依照所有可能的雀頭下去判斷是否胡牌
        for (Integer eye : eyes) {
            List<Integer> notConcealedCards = new ArrayList<>(this.notConcealedCards);
            List<Integer> handCards = new ArrayList<>(this.handCards);
            handCards.add(this.lastCard);
            //取出雀頭
            removeEye(handCards, eye);
            //取出刻子和槓子
            removeAllTriplet(notConcealedCards); //門前
            removeAllTriplet(handCards); //手牌
            //取出順子
            removeAllSequence(notConcealedCards); //門前
            removeAllSequence(handCards); //手牌
            if (notConcealedCards.isEmpty() && handCards.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 取出手牌中所有可能的雀頭
     *
     * @param handCards 手牌
     * @return 此手牌中所有可能的雀頭
     */
    private Set<Integer> getEyes(List<Integer> handCards) {
        Set<Integer> handCardsCategories = new HashSet<>(handCards);
        Set<Integer> eyesSet = new HashSet<>();
        handCardsCategories.forEach(category -> {
            if (Collections.frequency(handCards, category) >= 2) {
                eyesSet.add(category);
            }
        });
        return eyesSet;
    }

    /**
     * 移除手牌中的雀頭
     *
     * @param handCards 手牌
     * @param eye 雀頭
     */
    private void removeEye(List<Integer> handCards, Integer eye) {
        for (int i = 0; i < 2; i++) {
            handCards.remove(Integer.valueOf(eye));
        }
    }

    /**
     * 移除此副牌中的刻子、槓子
     *
     * @param cards 手牌 or 門前牌
     * @return 此副牌中刻子+槓子的數量
     */
    public static int removeAllTriplet(List<Integer> cards) {
        Set<Integer> categories = new HashSet<>(cards);
        int tripletCount = 0;
        for (Integer category : categories) {
            int frequency = Collections.frequency(cards, category);
            if (frequency == 3 || frequency == 4) {
                tripletCount++;
                cards.removeIf(card -> card == category);
            }
        }
        return tripletCount;
    }

    /**
     * 移除牌中的順子
     *
     * @param cards 手牌 or 門前牌
     * @return 此副牌中順子的數量
     */
    public static int removeAllSequence(List<Integer> cards) {
        List<Integer> cardsToRemove = new ArrayList<>();
        Collections.sort(cards);
        for (int index = 0; index < cards.size(); index++) {
            Integer card = cards.get(index);
            if (card >= MahjongCardEnum.MILLION_ONE.getCode() && card <= MahjongCardEnum.TIAO_NINE.getCode()) {
                if (index + 2 < cards.size() && cards.get(index + 1) - card == 1 && cards.get(index + 2) - card == 2) {
                    cardsToRemove.add(card);
                    cardsToRemove.add(cards.get(index + 1));
                    cardsToRemove.add(cards.get(index + 2));
                    if (index + 3 < cards.size()) {
                        index += 2;
                    } else {
                        break;
                    }
                }
            }
        }
        cards.removeAll(cardsToRemove);
        return cardsToRemove.size() / 3;
    }

    /**
     * 算台數
     */
    public Map<String, Integer> scoring() {
        //TODO: 實現計算台數的邏輯
        return new HashMap<>();
    }
}
