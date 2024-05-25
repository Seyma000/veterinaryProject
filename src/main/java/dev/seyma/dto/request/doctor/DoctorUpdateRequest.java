package dev.seyma.dto.request.doctor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorUpdateRequest {
    @Positive(message = "ID pozitif olmalı")
    @NotNull(message = "Doktor Id boş olamaz")
    private int id;
    @NotNull(message = "Doktor ismi boş olamaz")
    private String name;
    @NotNull(message = "Doktor telefonu boş olamaz")
    private String phone;
    @NotNull(message = "Doktor maili boş olamaz")
    private String mail;
    @NotNull(message = "Doktor adresi boş olamaz")
    private String address;
    @NotNull(message = "Doktor şehri boş olamaz")
    private String city;
}
