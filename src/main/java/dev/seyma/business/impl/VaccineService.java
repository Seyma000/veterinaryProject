package dev.seyma.business.impl;

import dev.seyma.business.abstracts.IAnimalService;
import dev.seyma.business.abstracts.IVaccineService;
import dev.seyma.core.config.ConvertEntityToResponse;
import dev.seyma.core.config.modelMapper.IModelMapperService;
import dev.seyma.core.exception.DataAlreadyExistException;
import dev.seyma.core.exception.NotFoundException;
import dev.seyma.core.result.ResultData;
import dev.seyma.core.utilies.Msg;
import dev.seyma.core.utilies.ResultHelper;
import dev.seyma.repository.VaccineRepo;
import dev.seyma.dto.CursorResponse;
import dev.seyma.dto.request.vaccine.VaccineSaveRequest;
import dev.seyma.dto.request.vaccine.VaccineUpdateRequest;
import dev.seyma.dto.response.vaccine.VaccineResponse;
import dev.seyma.entity.Animal;
import dev.seyma.entity.Vaccine;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccineService implements IVaccineService {
    private final VaccineRepo vaccineRepo; // Vaccine repository dependency
    private final IModelMapperService modelMapperService; // Model mapper service dependency
    private final IAnimalService animalService; // Animal service dependency
    private final ConvertEntityToResponse<Vaccine, VaccineResponse> convert; // Entity to response converter

    @Override
    public ResultData<VaccineResponse> save(VaccineSaveRequest vaccineSaveRequest) {
        // Check for existing vaccines with same code and name
        List<Vaccine> existVaccines = this.findByCodeAndName(vaccineSaveRequest.getCode(), vaccineSaveRequest.getName());
        if (!existVaccines.isEmpty() && existVaccines.get(0).getProtectionFnshDate().isAfter(LocalDate.now())) {
            return ResultHelper.error("Aynı koda sahip aşının bitiş tarihi bitmemiş! ");
        }
        if (!existVaccines.isEmpty()) {
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Vaccine.class));
        }
        // Get the animal associated with the vaccine
        Animal animal = this.animalService.get(vaccineSaveRequest.getAnimalId());
        vaccineSaveRequest.setAnimalId(null);

        // Map request to vaccine entity and save
        Vaccine saveVaccine = this.modelMapperService.forRequest().map(vaccineSaveRequest, Vaccine.class);
        saveVaccine.setAnimal(animal);

        return ResultHelper.created(this.modelMapperService.forResponse().map(this.vaccineRepo.save(saveVaccine), VaccineResponse.class));
    }

    @Override
    public Vaccine get(int id) {
        // Get vaccine by ID, throw exception if not found
        return this.vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public ResultData<CursorResponse<VaccineResponse>> cursor(int page, int pageSize) {
        // Get paginated list of vaccines
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Vaccine> vaccinePage = this.vaccineRepo.findAll(pageable);
        Page<VaccineResponse> vaccineResponsePage = vaccinePage.map(vaccine -> this.modelMapperService.forResponse().map(vaccine, VaccineResponse.class));
        return ResultHelper.cursor(vaccineResponsePage);
    }

    @Override
    public ResultData<List<VaccineResponse>> findByAnimalId(int id) {
        // Find vaccines by animal ID
        List<Vaccine> vaccineList = this.vaccineRepo.findByAnimalId(id);
        List<VaccineResponse> vaccineResponseList = this.convert.convertToResponseList(vaccineList, VaccineResponse.class);
        return ResultHelper.success(vaccineResponseList);
    }

    @Override
    public ResultData<List<VaccineResponse>> findByDate(LocalDate entryDate, LocalDate exitDate) {
        // Find vaccines between the given dates
        List<Vaccine> vaccineList = this.vaccineRepo.findByprotectionFnshDateBetween(entryDate, exitDate);
        if (vaccineList.isEmpty()) {
            return ResultHelper.error("No vaccines found within the specified date range.");
        }
        // Convert the list of Vaccine entities to response DTOs
        List<VaccineResponse> vaccineResponseList = this.convert.convertToResponseList(vaccineList, VaccineResponse.class);
        return ResultHelper.success(vaccineResponseList);
    }

    @Override
    public List<Vaccine> findByCodeAndName(String code, String name) {
        // Find vaccines by code and name
        return this.vaccineRepo.findByCodeAndName(code, name);
    }

    @Override
    public ResultData<VaccineResponse> update(VaccineUpdateRequest vaccineUpdateRequest) {
        // Get existing vaccine, map update request and save
        Vaccine existingVaccine = this.get(vaccineUpdateRequest.getId());
        this.modelMapperService.forRequest().map(vaccineUpdateRequest, existingVaccine);
        Vaccine savedVaccine = this.vaccineRepo.save(existingVaccine);
        return ResultHelper.success(this.modelMapperService.forResponse().map(savedVaccine, VaccineResponse.class));
    }

    @Override
    public boolean delete(int id) {
        // Delete vaccine by ID
        Vaccine vaccine = this.get(id);
        this.vaccineRepo.delete(vaccine);
        return true;
    }

    public boolean isTrue() {
        return true;
    }
}
