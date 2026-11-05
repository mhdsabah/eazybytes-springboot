package com.eazybytes.cards.mapper;

import com.eazybytes.cards.dto.CardsDTO;
import com.eazybytes.cards.entity.Cards;

public class CardsMapper {

    public static CardsDTO mapToCardsDTO(Cards cards, CardsDTO cardsDTO){
        cardsDTO.setCardNumber(cards.getCardNumber());
        cardsDTO.setCardType(cards.getCardType());
        cardsDTO.setMobileNumber(cards.getMobileNumber());
        cardsDTO.setAmountUsed(cards.getAmountUsed());
        cardsDTO.setAvailableAmount(cards.getAvailableAmount());
        cardsDTO.setTotalLimit(cards.getTotalLimit());
        return cardsDTO;
    }


    public static Cards mapToCards(CardsDTO cardsDTO, Cards cards){
        cards.setAmountUsed(cardsDTO.getAmountUsed());
        cards.setCardNumber(cardsDTO.getCardNumber());
        cards.setMobileNumber(cardsDTO.getMobileNumber());
        cards.setCardType(cardsDTO.getCardType());
        cards.setAvailableAmount(cardsDTO.getAvailableAmount());
        cards.setTotalLimit(cardsDTO.getTotalLimit());
        return cards;

    }
}
