package dev.seyma.business.abstracts;

import dev.seyma.core.result.ResultData;
import dev.seyma.dto.CursorResponse;
import dev.seyma.dto.request.doctor.DoctorSaveRequest;
import dev.seyma.dto.request.doctor.DoctorUpdateRequest;
import dev.seyma.dto.response.doctor.DoctorResponse;
import dev.seyma.entity.Doctor;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IDoctorService {
    ResultData<DoctorResponse> save(DoctorSaveRequest doctorSaveRequest);
    Doctor get(int id);
    ResultData<CursorResponse<DoctorResponse>> cursor(int page, int pageSize);
    ResultData<DoctorResponse> update(DoctorUpdateRequest doctorUpdateRequest);
    boolean delete(int id);
    List<Doctor> findByIdAndAvailableDateDate(int id, LocalDate localDate);
    List<Doctor> findByNameAndMailAndPhone(String name, String mail, String phone);
}
