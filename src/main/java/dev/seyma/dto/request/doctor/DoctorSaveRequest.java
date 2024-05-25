package dev.seyma.dto.request.doctor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSaveRequest {
    @NotNull(message = "Doktor ismi boş olamaz")
    private String name;
    @NotNull(message = "Doktor telefonu boş olamaz")
    private String phone;

    @Email
    @NotNull(message = "Doktor maili boş olamaz")
    private String mail;
    @NotNull(message = "Doktor adresi boş olamaz")
    private String address;
    @NotNull(message = "Doktor şehri boş olamaz")
    private String city;
}
