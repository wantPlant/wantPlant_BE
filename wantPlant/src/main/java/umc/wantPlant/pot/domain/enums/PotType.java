package umc.wantPlant.pot.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PotType {
    PEPPERMINT, //페퍼민트
    ROSEMARY, //로즈마리
    BASIL, //바질
    TOMATO, //토마토
    GREEN_ONION, //대파
    LETTUCE, //상추
    LAVENDER, //라벤더
    GERANIUM, //제라늄
    JASMINE //자스민
    ;

//    @JsonCreator

    public static PotType getRandom(){
        return values()[(int) (Math.random() * values().length)];
    }
}
