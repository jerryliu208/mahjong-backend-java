package com.backend.mahjong.controller;

import com.backend.mahjong.data.mahjong.CheckHandCardsResData;
import com.backend.mahjong.data.mahjong.HandCardsData;
import com.backend.mahjong.data.response.ResponseData;
import com.backend.mahjong.service.MahjongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.backend.mahjong.constant.UrlConstant.CHECK_HAND_CARDS;

@RestController
@RequestMapping("/mahjong")
public class MahjongController {
    @Autowired
    private MahjongService mahjongService;

    /**
     *  檢查手牌是否已經和牌，並計算相對應的台數
     */
    @PostMapping(CHECK_HAND_CARDS)
    public Object checkHandCards(@RequestBody HandCardsData handCardsData){
        return new ResponseData(mahjongService.checkHandCards(handCardsData));
    }

    /**
     *  檢查聽哪些牌
     */

}
