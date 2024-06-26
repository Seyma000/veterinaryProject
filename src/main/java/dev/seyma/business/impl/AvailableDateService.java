package dev.seyma.business.impl;

import dev.seyma.business.abstracts.IAvailableDateService;
import dev.seyma.core.config.modelMapper.IModelMapperService;
import dev.seyma.core.exception.DataAlreadyExistException;
import dev.seyma.core.exception.NotFoundException;
import dev.seyma.core.result.ResultData;
import dev.seyma.core.utilies.Msg;
import dev.seyma.core.utilies.ResultHelper;
import dev.seyma.repository.AvailableDateRepo;
import dev.seyma.dto.CursorResponse;
import dev.seyma.dto.request.availableDate.AvailableDateSaveRequest;
import dev.seyma.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.seyma.dto.response.availableDate.AvailableDateResponse;
import dev.seyma.entity.AvailableDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailableDateService implements IAvailableDateService {
    // Injected dependencies
    private final AvailableDateRepo availableDateRepo;
    private final IModelMapperService modelMapperService;

    @Override
    public ResultData<AvailableDateResponse> save(AvailableDateSaveRequest availableDateSaveRequest) {
        // Check for existing available dates with the same date and doctor
        List<AvailableDate> availableDateList = this.availableDateRepo.findByDateAndDoctor(
                availableDateSaveRequest.getDate(),
                availableDateSaveRequest.getDoctor());
        if (!availableDateList.isEmpty()){
            throw new DataAlreadyExistException(Msg.getEntityForMsg(AvailableDate.class));
        }
        // Map request to available date entity
        AvailableDate saveAvailableDate = this.modelMapperService.forRequest().map(availableDateSaveRequest, AvailableDate.class);
        // Save the available date and return the response
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.availableDateRepo.save(saveAvailableDate), AvailableDateResponse.class));
    }

    @Override
    public AvailableDate get(int id) {
        // Get available date by ID or throw not found exception
        return this.availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public ResultData<CursorResponse<AvailableDateResponse>> cursor(int page, int pageSize) {
        // Create pageable request
        Pageable pageable = PageRequest.of(page, pageSize);
        // Get paginated result of available dates
        Page<AvailableDate> availableDatePage = this.availableDateRepo.findAll(pageable);
        // Map available dates to response objects
        Page<AvailableDateResponse> availableDateResponsePage = availableDatePage.map(availableDate -> this.modelMapperService.forResponse().map(availableDate, AvailableDateResponse.class));
        return ResultHelper.cursor(availableDateResponsePage);
    }

    @Override
    public ResultData<AvailableDateResponse> update(AvailableDateUpdateRequest availableDateUpdateRequest) {
        // Get available date by ID to ensure it exists
        this.get(availableDateUpdateRequest.getId());
        // Map update request to available date entity and save updated available date
        AvailableDate updateAvailableDate = this.modelMapperService.forResponse().map(availableDateUpdateRequest, AvailableDate.class);
        return ResultHelper.success(this.modelMapperService.forResponse().map(this.availableDateRepo.save(updateAvailableDate), AvailableDateResponse.class));
    }

    @Override
    public boolean delete(int id) {
        // Get available date by ID to ensure it exists and delete
        AvailableDate availableDate = this.get(id);
        this.availableDateRepo.delete(availableDate);
        return true;
    }
}
