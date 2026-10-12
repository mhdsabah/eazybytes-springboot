package com.eazybytes.cards.service;

import com.eazybytes.cards.dto.CardsDTO;

public interface ICardsService {

    public void createCard(String mobileNum);

    public CardsDTO fetchCard(String mobileNumber);

    public Boolean updateCardDetails(CardsDTO cardsDTO);

    public Boolean deleteCardDetails(String mobileNumber);
}
