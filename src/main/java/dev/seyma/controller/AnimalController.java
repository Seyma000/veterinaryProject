package dev.seyma.controller;

import dev.seyma.business.abstracts.IAnimalService;
import dev.seyma.core.result.Result;
import dev.seyma.core.result.ResultData;
import dev.seyma.core.utilies.ResultHelper;
import dev.seyma.dto.CursorResponse;
import dev.seyma.dto.request.animal.AnimalSaveRequest;
import dev.seyma.dto.request.animal.AnimalUpdateRequest;
import dev.seyma.dto.response.animal.AnimalResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/animals")
@RequiredArgsConstructor
public class AnimalController {
    private final IAnimalService animalService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest){
        return this.animalService.save(animalSaveRequest);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){
        return this.animalService.cursor(page, pageSize);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest){
        return this.animalService.update(animalUpdateRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable int id){
        this.animalService.delete(id);
        return ResultHelper.ok();
    }
    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> get(@PathVariable("name") String name){
        return this.animalService.findByName(name);
    }
    @GetMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getAnimalsByCustomerId(@PathVariable("id") int customerId){
        return this.animalService.findByCustomerId(customerId);
    }
}
