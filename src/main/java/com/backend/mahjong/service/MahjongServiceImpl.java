package com.backend.mahjong.service;

import com.backend.mahjong.data.mahjong.CheckHandCardsResData;
import com.backend.mahjong.data.mahjong.HandCardsData;
import com.backend.mahjong.data.response.ResponseData;
import com.backend.mahjong.enums.MahjongCardEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Service
public class MahjongServiceImpl implements MahjongService {

    public ResponseData cardsSettle(HandCardsData handCardsData) {
        //檢查參數是否為空
        if (handCardsData == null) {
            return new ResponseData(400, "參數不可為空");
        }
        //檢查手牌數量是否正確
        if (handCardsData.getHandCards() == null || handCardsData.getLastCard() == null) {
            return new ResponseData(400, "手牌數量不正確！無法胡牌...");
        }
        if (handCardsData.getNotConcealedCards().isEmpty()) {
            if (handCardsData.getHandCards().size() != 16) {
                return new ResponseData(400, "手牌數量不正確！無法胡牌...");
            }
        } else {
            int amountOfChiPongKong = 0;
            List<Integer> notConcealedCards = new ArrayList<>(handCardsData.getNotConcealedCards());
            amountOfChiPongKong += HandCardsData.removeAllTriplet(notConcealedCards);
            amountOfChiPongKong += HandCardsData.removeAllSequence(notConcealedCards);
            if (!notConcealedCards.isEmpty() || handCardsData.getHandCards().size() + 3 * amountOfChiPongKong != 16) {
                return new ResponseData(400, "手牌數量不正確！無法胡牌...");
            }
        }
        //如果手牌含有非法參數，表示無法構成牌型
        if (!Arrays.stream(MahjongCardEnum.values())
                .map(MahjongCardEnum::getCode)
                .collect(Collectors.toSet())
                .containsAll(handCardsData.getHandCards()) ||
            !Arrays.stream(MahjongCardEnum.values())
                .map(MahjongCardEnum::getCode)
                .collect(Collectors.toSet())
                .containsAll(handCardsData.getNotConcealedCards()) ||
                MahjongCardEnum.findByCode(handCardsData.getLastCard()) == null) {
            return new ResponseData(400, "手牌資訊錯誤！無法胡牌...");
        }

        CheckHandCardsResData resData = new CheckHandCardsResData();
        if (handCardsData.checkIsHu()) {
            resData.setHu(true);
            resData.setPointsMap(handCardsData.scoring());
        }
        //回傳時將手牌全轉為中文，方便檢視
        resData.setHandCards(handCardsData.getHandCards().stream().sorted()
                .filter(card -> MahjongCardEnum.findByCode(card) != null)
                .map(card -> Objects.requireNonNull(MahjongCardEnum.findByCode(card)).getName())
                .collect(Collectors.toList()));
        resData.setNotConcealedCards(handCardsData.getNotConcealedCards().stream().sorted()
                .filter(card -> MahjongCardEnum.findByCode(card) != null)
                .map(card -> Objects.requireNonNull(MahjongCardEnum.findByCode(card)).getName())
                .collect(Collectors.toList()));
        resData.setLastCard(Objects.requireNonNull(MahjongCardEnum.findByCode(handCardsData.getLastCard())).getName());

        return new ResponseData(resData);
    }

}
