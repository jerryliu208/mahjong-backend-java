package com.backend.mahjong.service;

import com.backend.mahjong.data.mahjong.HandCardsData;
import com.backend.mahjong.data.response.ResponseData;

public interface MahjongService {
    ResponseData cardsSettle(HandCardsData handCardsData);

}
