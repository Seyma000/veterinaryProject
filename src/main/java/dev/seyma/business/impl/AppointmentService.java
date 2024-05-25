package dev.seyma.business.impl;

import dev.seyma.business.abstracts.IAnimalService;
import dev.seyma.business.abstracts.IAppointmentService;
import dev.seyma.business.abstracts.IDoctorService;
import dev.seyma.core.config.ConvertEntityToResponse;
import dev.seyma.core.config.modelMapper.IModelMapperService;
import dev.seyma.core.exception.DataAlreadyExistException;
import dev.seyma.core.exception.NotFoundException;
import dev.seyma.core.result.ResultData;
import dev.seyma.core.utilies.Msg;
import dev.seyma.core.utilies.ResultHelper;
import dev.seyma.entity.Animal;
import dev.seyma.entity.Doctor;
import dev.seyma.repository.AppointmentRepo;
import dev.seyma.dto.CursorResponse;
import dev.seyma.dto.request.appointment.AppointmentSaveRequest;
import dev.seyma.dto.request.appointment.AppointmentUpdateRequest;
import dev.seyma.dto.response.appointment.AppointmentResponse;
import dev.seyma.entity.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService {
    // Injected dependencies
    private final AppointmentRepo appointmentRepo;
    private final IAnimalService animalService;
    private final IDoctorService doctorService;
    private final IModelMapperService modelMapperService;
    private final ConvertEntityToResponse<Appointment, AppointmentResponse> convert;

    @Override
    public ResultData<AppointmentResponse> save(AppointmentSaveRequest appointmentSaveRequest) {
        // Check if minutes are 00
        LocalDateTime dateTime = appointmentSaveRequest.getDateTime();
        System.out.println(dateTime.getMinute());
        if (dateTime.getMinute() != 0){
            return ResultHelper.error("Lütfen dakika bilgisini '00' giriniz.");
        }

        // Check for existing appointment with the same date, doctor, and animal
        Optional<Appointment> appointmentOptional = this.findByValueForValid(
                appointmentSaveRequest.getDateTime(),
                appointmentSaveRequest.getDoctorId(),
                appointmentSaveRequest.getAnimalId()
        );
        if (appointmentOptional.isPresent()){
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Appointment.class));
        }

        // Get animal and doctor by their IDs
        Animal animal = this.animalService.get(appointmentSaveRequest.getAnimalId());
        Doctor doctor = this.doctorService.get(appointmentSaveRequest.getDoctorId());

        // Check doctor's available dates
        List<Doctor> doctorList =  this.doctorService.findByIdAndAvailableDateDate(appointmentSaveRequest.getDoctorId(), LocalDate.from(dateTime));

        // Check for conflicting appointments
        List<Appointment> appointmentByDate = this.findByDateTime(dateTime);

        // Set IDs to null to avoid update
        appointmentSaveRequest.setAnimalId(null);
        appointmentSaveRequest.setDateTime(null);
        appointmentSaveRequest.setDoctorId(null);

        // Map request to appointment entity
        Appointment saveAppointment = this.modelMapperService.forRequest().map(appointmentSaveRequest, Appointment.class);
        saveAppointment.setAnimal(animal);
        saveAppointment.setDoctor(doctor);
        saveAppointment.setDateTime(dateTime);

        // Check for conflicts and save appointment
        if (doctorList.isEmpty()){
            return ResultHelper.error("Doktor bu tarihte müsait değildir.");
        } else if (!appointmentByDate.isEmpty()) {
            return ResultHelper.error("Doktorun bu saatte randevusu bulunmaktadır.");
        } else {
            return ResultHelper.created(this.modelMapperService.forResponse().map(this.appointmentRepo.save(saveAppointment), AppointmentResponse.class));
        }
    }

    @Override
    public Appointment get(int id) {
        // Get appointment by ID or throw not found exception
        return this.appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public ResultData<CursorResponse<AppointmentResponse>> cursor(int page, int pageSize) {
        // Create pageable request
        Pageable pageable = PageRequest.of(page, pageSize);
        // Get paginated result of appointments
        Page<Appointment> appointmentPage =  this.appointmentRepo.findAll(pageable);
        // Map appointments to response objects
        Page<AppointmentResponse> appointmentResponsePage = appointmentPage.map(appointment -> this.modelMapperService.forResponse().map(appointment, AppointmentResponse.class));
        return ResultHelper.cursor(appointmentResponsePage);
    }

    @Override
    public ResultData<AppointmentResponse> update(AppointmentUpdateRequest appointmentUpdateRequest) {
        // Get appointment by ID to ensure it exists
        this.get(appointmentUpdateRequest.getId());
        // Map update request to appointment entity and save updated appointment
        Appointment updateAppointment = this.modelMapperService.forRequest().map(appointmentUpdateRequest, Appointment.class);
        return ResultHelper.success(this.modelMapperService.forResponse().map(this.appointmentRepo.save(updateAppointment), AppointmentResponse.class));
    }

    @Override
    public boolean delete(int id) {
        // Get appointment by ID to ensure it exists
        Appointment appointment = this.get(id);
        // Delete the appointment
        this.appointmentRepo.delete(appointment);
        return true;
    }

    @Override
    public List<Appointment> findByDateTime(LocalDateTime localDateTime) {
        // Find appointments by date and time
        return this.appointmentRepo.findByDateTime(localDateTime);
    }

    @Override
    public ResultData<List<AppointmentResponse>> findByDoctorIdAndDateTimeBetween(int id, LocalDate entryDate, LocalDate exitDate) {
        // Convert dates to date-time
        LocalDateTime convertedEntryDate = entryDate.atStartOfDay();
        LocalDateTime convertedExitDate = exitDate.atStartOfDay().plusDays(1);
        // Find appointments within the date range
        List<Appointment> appointmentList = this.appointmentRepo.findByDoctorIdAndDateTimeBetween(id, convertedEntryDate, convertedExitDate);
        // Convert appointments to response objects
        List<AppointmentResponse> appointmentResponseList = this.convert.convertToResponseList(appointmentList, AppointmentResponse.class);
        return ResultHelper.success(appointmentResponseList);
    }

    @Override
    public ResultData<List<AppointmentResponse>> findByAnimalIdAndDateTimeBetween(int id, LocalDate entryDate, LocalDate exitDate) {
        // Convert dates to date-time
        LocalDateTime convertedEntryDate = entryDate.atStartOfDay();
        LocalDateTime convertedExitDate = exitDate.atStartOfDay().plusDays(1);
        // Find appointments within the date range
        List<Appointment> appointmentList = this.appointmentRepo.findByAnimalIdAndDateTimeBetween(id, convertedEntryDate, convertedExitDate);
        // Convert appointments to response objects
        List<AppointmentResponse> appointmentResponseList = this.convert.convertToResponseList(appointmentList, AppointmentResponse.class);
        return ResultHelper.success(appointmentResponseList);
    }

    @Override
    public Optional<Appointment> findByValueForValid(LocalDateTime dateTime, Integer doctorId, Integer animalId) {
        // Find appointment by date-time, doctor ID, and animal ID
        return this.appointmentRepo.findByDateTimeAndDoctorIdAndAnimalId(dateTime, doctorId, animalId);
    }
}
