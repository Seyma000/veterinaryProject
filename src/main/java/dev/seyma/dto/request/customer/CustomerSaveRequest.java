package dev.seyma.dto.request.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest {
    @NotNull(message = "Müşteri ismi boş olamaz")
    private String name;

    @NotNull(message = "Telefon boş olamaz")
    private String phone;

    @Email
    @NotNull(message = "Müşteri maili boş olamaz")
    private String mail;

    @NotNull(message = "Müşteri adresi boş olamaz")
    private String address;

    @NotNull(message = "Şehir boş olamaz")
    private String city;
}
