package dev.seyma.dto.request.animal;

import dev.seyma.entity.Customer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalSaveRequest {
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
    private Integer  customerId;
}
