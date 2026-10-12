package com.eazybytes.cards.controller;


import com.eazybytes.cards.constants.CardsConstants;
import com.eazybytes.cards.dto.CardsDTO;
import com.eazybytes.cards.dto.ResponseDTO;
import com.eazybytes.cards.service.ICardsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class CardsController {

    private ICardsService cardsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCard(@RequestParam
                                                  @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number must be a 10-digit number")
                                                  String mobileNumber){

        cardsService.createCard(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED).
                body(new ResponseDTO(CardsConstants.STATUS_201,CardsConstants.MESSAGE_201));

    }

    @GetMapping("/fetch")
    public ResponseEntity<CardsDTO> fetchCardDetails(@RequestParam
                                                     @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number must be a 10-digit number")
                                                     String mobileNumber){
        CardsDTO cardsDTO = cardsService.fetchCard(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK)
                .body(cardsDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCardDetails(@Valid  @RequestBody CardsDTO cardsDTO){
        Boolean isUpdated = cardsService.updateCardDetails(cardsDTO);

        if (isUpdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200));

        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(CardsConstants.STATUS_417 , CardsConstants.MESSAGE_417_UPDATE));

        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteCardDetails(@RequestParam

                                                         String mobileNumber){
        Boolean isDeleted = cardsService.deleteCardDetails(mobileNumber);
        if (isDeleted){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200));

        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(CardsConstants.STATUS_417 , CardsConstants.MESSAGE_417_DELETE));

        }
    }

}
