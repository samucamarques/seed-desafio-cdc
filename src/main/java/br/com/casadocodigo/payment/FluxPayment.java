package br.com.casadocodigo.payment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Builder
public class FluxPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @NotEmpty
    @Email
    private String mailAccount;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String docId;

    @NotEmpty
    private String address;

    @NotEmpty
    private String addressComplement;

    @NotEmpty
    private String city;

    @NotNull
    private Long countryId;

    @Setter
    private Long stateId;

    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    private String zipCode;

    @Deprecated
    protected FluxPayment() {
    }

    private FluxPayment(
            @NotEmpty @Email String mailAccount,
            @NotEmpty String firstName,
            @NotEmpty String lastName,
            @NotEmpty String docId,
            @NotEmpty String address,
            @NotEmpty String addressComplement,
            @NotEmpty String city,
            @NotNull Long countryId,
            @NotEmpty String phoneNumber,
            @NotEmpty String zipCode) {

        Assert.hasLength(mailAccount, "there is no flux payment without mailAccount");
        Assert.hasLength(firstName, "there is no flux payment without firstName");
        Assert.hasLength(lastName, "there is no flux payment without lastName");
        Assert.hasLength(docId, "there is no flux payment without docId");
        Assert.hasLength(address, "there is no flux payment without address");
        Assert.hasLength(addressComplement, "there is no flux payment without addressComplement");
        Assert.hasLength(city, "there is no flux payment without city");
        Assert.notNull(countryId, "there is no flux payment without countryId");
        Assert.hasLength(phoneNumber, "there is no flux payment without phoneNumber");
        Assert.hasLength(zipCode, "there is no flux payment without zipCode");

        this.mailAccount = mailAccount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.docId = docId;
        this.address = address;
        this.addressComplement = addressComplement;
        this.city = city;
        this.countryId = countryId;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
    }

    public static class FluxPaymentBuilder {
        public FluxPayment build() {
            final FluxPayment fluxPayment =
                    new FluxPayment(
                            mailAccount,
                            firstName,
                            lastName,
                            docId,
                            address,
                            addressComplement,
                            city,
                            countryId,
                            phoneNumber,
                            zipCode);

            fluxPayment.setStateId(stateId);
            return fluxPayment;
        }
    }
}
