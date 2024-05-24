package dev.seyma.dto.request.customer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {
    @Positive(message = "ID pozitif olmak zorunda")
    private int id;

    @NotNull(message = "Müşteri ismi boş olamaz")
    private String name;

    @NotNull(message = "Telefon boş olamaz")
    private String phone;

    @NotNull(message = "Müşteri maili boş olamaz")
    private String mail;

    @NotNull(message = "Müşteri adresi boş olamaz")
    private String address;

    @NotNull(message = "Şehir boş olamaz")
    private String city;
}
