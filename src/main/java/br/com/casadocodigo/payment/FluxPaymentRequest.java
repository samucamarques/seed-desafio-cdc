package br.com.casadocodigo.payment;

import br.com.casadocodigo.commons.CountryOwnership;
import br.com.casadocodigo.commons.DocId;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//Intrinsic cognitive load: 1
@AllArgsConstructor
@Getter // for swagger to show the properties on request body example
@CountryOwnership(countryField = "countryId", stateField = "stateId")
public class FluxPaymentRequest {

    @NotEmpty
    @Email
    private final String mailAccount;

    @NotEmpty
    private final String firstName;

    @NotEmpty
    private final String lastName;

    @NotEmpty
    @DocId
    private final String docId;

    @NotEmpty
    private final String address;

    @NotEmpty
    private final String addressComplement;

    @NotEmpty
    private final String city;

    @NotNull
    private final Long countryId;

    private final Long stateId;

    @NotEmpty
    private final String phoneNumber;

    @NotEmpty
    private final String zipCode;

    //1
    public FluxPayment toDomain() {
        return FluxPayment.builder()
                .mailAccount(mailAccount)
                .firstName(firstName)
                .lastName(lastName)
                .docId(docId)
                .address(address)
                .addressComplement(addressComplement)
                .city(city)
                .countryId(countryId)
                .stateId(stateId)
                .phoneNumber(phoneNumber)
                .zipCode(zipCode)
                .build();
    }
}
