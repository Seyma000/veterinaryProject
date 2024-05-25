package dev.seyma.business.impl;

import dev.seyma.business.abstracts.IAnimalService;
import dev.seyma.business.abstracts.ICustomerService;
import dev.seyma.core.config.modelMapper.IModelMapperService;
import dev.seyma.core.exception.DataAlreadyExistException;
import dev.seyma.core.exception.NotFoundException;
import dev.seyma.core.result.ResultData;
import dev.seyma.core.config.ConvertEntityToResponse;
import dev.seyma.core.utilies.Msg;
import dev.seyma.core.utilies.ResultHelper;
import dev.seyma.repository.AnimalRepo;
import dev.seyma.dto.CursorResponse;
import dev.seyma.dto.request.animal.AnimalSaveRequest;
import dev.seyma.dto.request.animal.AnimalUpdateRequest;
import dev.seyma.dto.response.animal.AnimalResponse;
import dev.seyma.entity.Animal;
import dev.seyma.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService implements IAnimalService {
    // Convert entity to response helper
    private final ConvertEntityToResponse<Animal, AnimalResponse> convert;
    private final AnimalRepo animalRepo;
    private final IModelMapperService modelMapperService;
    private final ICustomerService customerService;

    @Override
    public ResultData<AnimalResponse> save(AnimalSaveRequest animalSaveRequest) {
        // Get customer by ID from customer service
        Customer customer = this.customerService.get(animalSaveRequest.getCustomerId());
        // Set customer ID to null in the request
        animalSaveRequest.setCustomerId(null);
        // Map request to Animal entity
        Animal saveAnimal = this.modelMapperService.forRequest().map(animalSaveRequest, Animal.class);
        // Set customer to the animal
        saveAnimal.setCustomer(customer);
        // Check for existing animal with the same name, species, breed, and gender
        List<Animal> animalList = this.findByNameAndSpeciesAndBreedAndGender(
                animalSaveRequest.getName(),
                animalSaveRequest.getSpecies(),
                animalSaveRequest.getBreed(),
                animalSaveRequest.getGender()
        );
        // If an animal already exists, throw an exception
        if (!animalList.isEmpty()){
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Animal.class));
        }
        // Save the animal and return the result
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.animalRepo.save(saveAnimal), AnimalResponse.class));
    }

    @Override
    public Animal get(int id) {
        // Get animal by ID or throw not found exception
        return this.animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public ResultData<CursorResponse<AnimalResponse>> cursor(int page, int pageSize) {
        // Create pageable request
        Pageable pageable = PageRequest.of(page, pageSize);
        // Get paginated result of animals
        Page<Animal> animalPage = this.animalRepo.findAll(pageable);
        // Map animal entities to response objects
        Page<AnimalResponse> animalResponsePage = animalPage.map(animal -> this.modelMapperService.forResponse().map(animal, AnimalResponse.class));
        return ResultHelper.cursor(animalResponsePage);
    }

    @Override
    public ResultData<List<AnimalResponse>> findByName(String name) {
        // Find animals by name
        List<Animal> animalList = this.animalRepo.findByName(name);
        // Convert list of animals to response objects
        List<AnimalResponse> animalResponseList = this.convert.convertToResponseList(animalList, AnimalResponse.class);
        return ResultHelper.success(animalResponseList);
    }

    @Override
    public ResultData<List<AnimalResponse>> findByCustomerId(int id) {
        // Find animals by customer ID
        List<Animal> animalList = this.animalRepo.findByCustomerId(id);
        // Convert list of animals to response objects
        List<AnimalResponse> animalResponseList = this.convert.convertToResponseList(animalList, AnimalResponse.class);
        return ResultHelper.success(animalResponseList);
    }

    @Override
    public List<Animal> findByNameAndSpeciesAndBreedAndGender(String name, String species, String breed, String gender) {
        // Find animals by name, species, breed, and gender
        return this.animalRepo.findByNameAndSpeciesAndBreedAndGender(name, species, breed, gender);
    }

    @Override
    public ResultData<AnimalResponse> update(AnimalUpdateRequest animalUpdateRequest) {
        // Get animal by ID to ensure it exists
        this.get(animalUpdateRequest.getId());
        // Map update request to Animal entity and save updated animal
        Animal updateAnimal = this.modelMapperService.forRequest().map(animalUpdateRequest, Animal.class);
        this.animalRepo.save(updateAnimal);
        return ResultHelper.success(this.modelMapperService.forResponse().map(updateAnimal, AnimalResponse.class));
    }

    @Override
    public boolean delete(int id) {
        // Get animal by ID to ensure it exists and delete
        Animal animal = this.get(id);
        this.animalRepo.delete(animal);
        return true;
    }
}
