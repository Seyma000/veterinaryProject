package dev.seyma.business.abstracts;

import dev.seyma.core.result.ResultData;
import dev.seyma.dto.CursorResponse;
import dev.seyma.dto.request.animal.AnimalSaveRequest;
import dev.seyma.dto.request.animal.AnimalUpdateRequest;
import dev.seyma.dto.response.animal.AnimalResponse;
import dev.seyma.entity.Animal;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IAnimalService {
    ResultData<AnimalResponse> save(AnimalSaveRequest animalSaveRequest);
    Animal get(int id);
    ResultData<CursorResponse<AnimalResponse>> cursor(int page, int pageSize);
    ResultData<List<AnimalResponse>> findByName(String name);
    ResultData<List<AnimalResponse>> findByCustomerId(int id);
    List<Animal> findByNameAndSpeciesAndBreedAndGender(String name,String species,String breed,String gender);
    ResultData<AnimalResponse> update(AnimalUpdateRequest animalUpdateRequest);
    boolean delete(int id);
}
