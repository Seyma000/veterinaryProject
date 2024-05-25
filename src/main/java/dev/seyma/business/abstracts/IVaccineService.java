package dev.seyma.business.abstracts;

import dev.seyma.core.result.ResultData;
import dev.seyma.dto.CursorResponse;
import dev.seyma.dto.request.vaccine.VaccineSaveRequest;
import dev.seyma.dto.request.vaccine.VaccineUpdateRequest;
import dev.seyma.dto.response.vaccine.VaccineResponse;
import dev.seyma.entity.Vaccine;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {
    ResultData<VaccineResponse> save(VaccineSaveRequest vaccineSaveRequest);
    Vaccine get(int id);
    ResultData<CursorResponse<VaccineResponse>> cursor(int page, int pageSize);
    ResultData<List<VaccineResponse>> findByAnimalId(int id);
    ResultData<List<VaccineResponse>> findByDate(LocalDate entryDate, LocalDate exitDate);
    List<Vaccine> findByCodeAndName(String code, String name);
    ResultData<VaccineResponse> update(VaccineUpdateRequest vaccineUpdateRequest);
    boolean delete(int id);
}
