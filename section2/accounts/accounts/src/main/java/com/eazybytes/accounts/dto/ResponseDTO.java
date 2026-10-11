package com.eazybytes.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Schema to hold successfull response information"
)
@Data
@AllArgsConstructor
public class ResponseDTO {

    @Schema(
            description = "Status code in the response",example = "200"
    )
    private String statusCode;

    @Schema(
            description = "Status message in the response",example = "Request processed succesfully"
    )
    private String statusMsg;
}
