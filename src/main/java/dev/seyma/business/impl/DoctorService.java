package dev.seyma.business.impl;

import dev.seyma.business.abstracts.IDoctorService;
import dev.seyma.core.config.modelMapper.IModelMapperService;
import dev.seyma.core.exception.DataAlreadyExistException;
import dev.seyma.core.exception.NotFoundException;
import dev.seyma.core.result.ResultData;
import dev.seyma.core.utilies.Msg;
import dev.seyma.core.utilies.ResultHelper;
import dev.seyma.repository.DoctorRepo;
import dev.seyma.dto.CursorResponse;
import dev.seyma.dto.request.doctor.DoctorSaveRequest;
import dev.seyma.dto.request.doctor.DoctorUpdateRequest;
import dev.seyma.dto.response.doctor.DoctorResponse;
import dev.seyma.entity.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService implements IDoctorService {
    public final DoctorRepo doctorRepo; // Doctor repository dependency
    public final IModelMapperService modelMapperService; // Model mapper service dependency

    @Override
    public ResultData<DoctorResponse> save(DoctorSaveRequest doctorSaveRequest) {
        // Check for existing doctor with same name, mail, and phone
        List<Doctor> doctorList = this.findByNameAndMailAndPhone(
                doctorSaveRequest.getName(),
                doctorSaveRequest.getMail(),
                doctorSaveRequest.getPhone()
        );
        if (!doctorList.isEmpty()){
            // Throw exception if doctor already exists
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Doctor.class));
        }
        // Map request to doctor entity and save
        Doctor saveDoctor = this.modelMapperService.forRequest().map(doctorSaveRequest, Doctor.class);
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.doctorRepo.save(saveDoctor), DoctorResponse.class));
    }

    @Override
    public Doctor get(int id) {
        // Get doctor by ID, throw exception if not found
        return doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public ResultData<CursorResponse<DoctorResponse>> cursor(int page, int pageSize) {
        // Get paginated list of doctors
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Doctor> doctorPage = this.doctorRepo.findAll(pageable);
        Page<DoctorResponse> doctorResponsePage = doctorPage.map(doctor -> this.modelMapperService.forResponse().map(doctor, DoctorResponse.class));
        return ResultHelper.cursor(doctorResponsePage);
    }

    @Override
    public ResultData<DoctorResponse> update(DoctorUpdateRequest doctorUpdateRequest) {
        // Get existing doctor, map update request and save
        this.get(doctorUpdateRequest.getId());
        Doctor updateDoctor = this.modelMapperService.forRequest().map(doctorUpdateRequest, Doctor.class);
        return ResultHelper.success(this.modelMapperService.forResponse().map(this.doctorRepo.save(updateDoctor), DoctorResponse.class));
    }

    @Override
    public boolean delete(int id) {
        // Delete doctor by ID
        Doctor doctor = this.get(id);
        this.doctorRepo.delete(doctor);
        return true;
    }

    @Override
    public List<Doctor> findByIdAndAvailableDateDate(int id, LocalDate localDate) {
        // Find doctor by ID and available date
        return this.doctorRepo.findByIdAndAvailableDateDate(id, localDate);
    }

    @Override
    public List<Doctor> findByNameAndMailAndPhone(String name, String mail, String phone) {
        // Find doctor by name, mail, and phone
        return this.doctorRepo.findByNameAndMailAndPhone(name, mail, phone);
    }
}
