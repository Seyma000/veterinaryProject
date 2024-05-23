package dev.seyma.dto.request.animal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalUpdateRequest {

    @Positive(message = "ID pozitif olmalı")
    @NotNull(message = "Hayvan Id boş olamaz")
    private int id;

    @NotNull(message = "Hayvan ismi boş olamaz")
    private String name;

    @NotNull(message = "Hayvan cinsi boş olamaz")
    private String species;

    @NotNull(message = "Hayvan türü boş olamaz")
    private String breed;

    @NotNull(message = "Hayvan cinsiyeti boş olamaz")
    private String gender;

    @NotNull(message = "Hayvan rengi boş olamaz")
    private String color;

    @NotNull(message = "Hayvan doğum tarihi boş olamaz")
    private LocalDate birthday;
    private int customerId;
}
