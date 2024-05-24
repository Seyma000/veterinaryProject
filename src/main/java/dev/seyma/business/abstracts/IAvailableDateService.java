package dev.seyma.business.abstracts;

import dev.seyma.core.result.ResultData;
import dev.seyma.dto.CursorResponse;
import dev.seyma.dto.request.availableDate.AvailableDateSaveRequest;
import dev.seyma.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.seyma.dto.response.availableDate.AvailableDateResponse;
import dev.seyma.entity.AvailableDate;
import org.springframework.data.domain.Page;


public interface IAvailableDateService {
    ResultData<AvailableDateResponse> save(AvailableDateSaveRequest availableDateSaveRequest);
    AvailableDate get(int id);
    ResultData<CursorResponse<AvailableDateResponse>> cursor(int page, int pageSize);
    ResultData<AvailableDateResponse> update(AvailableDateUpdateRequest availableDateUpdateRequest);
    boolean delete(int id);
}
