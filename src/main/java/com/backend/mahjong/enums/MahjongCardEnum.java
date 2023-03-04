package com.backend.mahjong.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MahjongCardEnum {
    FLOWER_ONE(1, "1花", 2),
    FLOWER_TWO(2, "2花", 2),
    FLOWER_THREE(3, "3花", 2),
    FLOWER_FOUR(4, "4花", 2),
    MILLION_ONE(11, "一萬", 4),
    MILLION_TWO(12, "二萬", 4),
    MILLION_THREE(13, "三萬", 4),
    MILLION_FOUR(14, "四萬", 4),
    MILLION_FIVE(15, "五萬", 4),
    MILLION_SIX(16, "六萬", 4),
    MILLION_SEVEN(17, "七萬", 4),
    MILLION_EIGHT(18, "八萬", 4),
    MILLION_NINE(19, "九萬", 4),
    TONG_ONE(21, "一筒", 4),
    TONG_TWO(22, "二筒", 4),
    TONG_THREE(23, "三筒", 4),
    TONG_FOUR(24, "四筒", 4),
    TONG_FIVE(25, "五筒", 4),
    TONG_SIX(26, "六筒", 4),
    TONG_SEVEN(27, "七筒", 4),
    TONG_EIGHT(28, "八筒", 4),
    TONG_NINE(29, "九筒", 4),
    TIAO_ONE(31, "一條", 4),
    TIAO_TWO(32, "二條", 4),
    TIAO_THREE(33, "三條", 4),
    TIAO_FOUR(34, "四條", 4),
    TIAO_FIVE(35, "五條", 4),
    TIAO_SIX(36, "六條", 4),
    TIAO_SEVEN(37, "七條", 4),
    TIAO_EIGHT(38, "八條", 4),
    TIAO_NINE(39, "九條", 4),
    EAST(101, "東風", 4),
    SOUTH(102, "南風", 4),
    WEST(103, "西風", 4),
    NORTH(104, "北風", 4),
    ZHONG(105, "紅中", 4),
    FA(106, "發財", 4),
    BAI(107, "白板", 4);

    private int code;
    private String name;
    private int amount;

    public int getCode(){
        return code;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public static MahjongCardEnum findByCode(Integer code) {
        for (MahjongCardEnum cardEnum : values()) {
            if (cardEnum.getCode() == code) {
                return cardEnum;
            }
        }
        return null;
    }
}
