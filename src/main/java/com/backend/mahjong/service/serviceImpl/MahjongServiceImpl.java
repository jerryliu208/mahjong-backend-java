package com.backend.mahjong.service.serviceImpl;

import com.backend.mahjong.data.mahjong.CheckHandCardsResData;
import com.backend.mahjong.data.mahjong.HandCardsData;
import com.backend.mahjong.data.response.ResponseData;
import com.backend.mahjong.service.MahjongService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MahjongServiceImpl implements MahjongService {

    public Object checkHandCards(HandCardsData handCardsData){
        if(handCardsData == null) {
            return new ResponseData(400, "參數不可為空", null);
        }
        CheckHandCardsResData resData = new CheckHandCardsResData(handCardsData);
        resData.checkIsHu(); //檢查是否和牌
        resData.scoring(); //算台數
        return resData;
    }

}
