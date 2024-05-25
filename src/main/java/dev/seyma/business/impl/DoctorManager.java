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
public class DoctorManager implements IDoctorService {
    public final DoctorRepo doctorRepo;
    public final IModelMapperService modelMapperService;
    @Override
    public ResultData<DoctorResponse> save(DoctorSaveRequest doctorSaveRequest) {
        List<Doctor> doctorList = this.findByNameAndMailAndPhone(
                doctorSaveRequest.getName(),
                doctorSaveRequest.getMail(),
                doctorSaveRequest.getPhone()
        );
        if (!doctorList.isEmpty()){
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Doctor.class));
        }
        Doctor saveDoctor = this.modelMapperService.forRequest().map(doctorSaveRequest, Doctor.class);
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.doctorRepo.save(saveDoctor), DoctorResponse.class));
    }
    @Override
    public Doctor get(int id) {
        return doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }
    @Override
    public ResultData<CursorResponse<DoctorResponse>> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Doctor> doctorPage =  this.doctorRepo.findAll(pageable);
        Page<DoctorResponse> doctorResponsePage = doctorPage.map(doctor -> this.modelMapperService.forResponse().map(doctor, DoctorResponse.class));
        return ResultHelper.cursor(doctorResponsePage);
    }

    @Override
    public ResultData<DoctorResponse> update(DoctorUpdateRequest doctorUpdateRequest) {
        this.get(doctorUpdateRequest.getId());
        Doctor updateDoctor = this.modelMapperService.forRequest().map(doctorUpdateRequest, Doctor.class);
        return ResultHelper.success(this.modelMapperService.forResponse().map(this.doctorRepo.save(updateDoctor), DoctorResponse.class));
    }

    @Override
    public boolean delete(int id) {
        Doctor doctor = this.get(id);
        this.doctorRepo.delete(doctor);
        return true;
    }

    @Override
    public List<Doctor> findByIdAndAvailableDateDate(int id, LocalDate localDate) {
        return this.doctorRepo.findByIdAndAvailableDateDate(id, localDate);
    }

    @Override
    public List<Doctor> findByNameAndMailAndPhone(String name, String mail, String phone) {
        return this.doctorRepo.findByNameAndMailAndPhone(name, mail, phone);
    }
}
