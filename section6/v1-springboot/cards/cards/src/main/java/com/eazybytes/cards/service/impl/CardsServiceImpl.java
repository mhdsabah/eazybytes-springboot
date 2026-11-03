package com.eazybytes.cards.service.impl;

import com.eazybytes.cards.constants.CardsConstants;
import com.eazybytes.cards.dto.CardsDTO;
import com.eazybytes.cards.entity.Cards;
import com.eazybytes.cards.exception.CardAlreadyExistsException;
import com.eazybytes.cards.exception.ResourceNotFoundException;
import com.eazybytes.cards.mapper.CardsMapper;
import com.eazybytes.cards.repository.CardsRepository;
import com.eazybytes.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.smartcardio.Card;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {

        Optional<Cards> optionalCard = cardsRepository.findByMobileNumber(mobileNumber);
        if (optionalCard.isPresent()){
            throw new CardAlreadyExistsException("Card with number "+mobileNumber +" is already registered");
        }

        cardsRepository.save(createNewCard(mobileNumber));


    }


    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        newCard.setCreated_at(LocalDateTime.now());
        newCard.setCreated_by("Cards MS");
        return newCard;
    }

    @Override
    public CardsDTO fetchCard(String mobileNumber){
        Cards optionalCard = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Card", "mobileNumber",mobileNumber)
        );

        CardsDTO cardsDTO = CardsMapper.mapToCardsDTO(optionalCard,new CardsDTO());

        return cardsDTO;
    }

    @Override
    public Boolean updateCardDetails(CardsDTO cardsDTO){
        Boolean isUpdated = false;
        Cards cards = cardsRepository.findByCardNumber(cardsDTO.getCardNumber()).orElseThrow(
                ()-> new ResourceNotFoundException("Card", "cardNumber",cardsDTO.getCardNumber())
        );
        CardsMapper.mapToCards(cardsDTO,cards);
        cardsRepository.save(cards);
        return true;
    }

    @Override
    public Boolean deleteCardDetails(String mobileNumber) {
        Boolean isDeleted = false;
        Cards card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Card", "mobileNumber",mobileNumber)
        );
        cardsRepository.deleteById(card.getCardId());
        return true;
    }

}
