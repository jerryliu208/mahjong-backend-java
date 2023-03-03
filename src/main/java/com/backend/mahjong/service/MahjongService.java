package com.backend.mahjong.service;

import com.backend.mahjong.data.mahjong.CheckHandCardsResData;
import com.backend.mahjong.data.mahjong.HandCardsData;

public interface MahjongService {
    Object checkHandCards(HandCardsData handCardsData);

}
