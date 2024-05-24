package dev.seyma.dto.request.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentUpdateRequest {
    @Positive(message = "ID pozitif olmalı")
    @NotNull(message = "Randevu Id boş olamaz")
    private int id;

    @NotNull(message = "Randevu tarihi boş olamaz")
    @JsonFormat(pattern = "dd.MM.yyyy HH:00", shape = JsonFormat.Shape.STRING)
    @Schema(example = "01.01.2024 14:00")
    private LocalDateTime dateTime;

    @Positive(message = "Hayvan ID pozitif olmalı")
    @NotNull(message = "Hayvan ID boş olamaz")
    private Integer animalId;

    @Positive(message = "Doktor ID pozitif olmalı")
    @NotNull(message = "Doktor ID boş olamaz")
    private Integer doctorId;
}
