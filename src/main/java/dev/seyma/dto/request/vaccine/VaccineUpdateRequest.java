package dev.seyma.dto.request.vaccine;

import dev.seyma.entity.Animal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineUpdateRequest {
    @Positive(message = "ID pozitif olmalı")
    @NotNull(message = "Aşı Id boş olamaz")
    private int id;
    @NotNull(message = "Aşı adı boş olamaz")
    private String name;
    @NotNull(message = "Aşı kodu boş olamaz")
    private String code;
    @NotNull(message = "Aşı başlangıç tarihi boş olamaz")
    private LocalDate protectionStrtDate;
    @NotNull(message = "Aşı bitiş tarihi boş olamaz")
    private LocalDate protectionFnshDate;
    private Integer animalId;
}
