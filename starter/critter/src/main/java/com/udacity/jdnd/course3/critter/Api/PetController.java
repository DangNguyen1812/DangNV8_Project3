package com.udacity.jdnd.course3.critter.Api;

import com.udacity.jdnd.course3.critter.DTO.PetDTO;
import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Utils.ConverterUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.udacity.jdnd.course3.critter.Service.PetService;
import com.udacity.jdnd.course3.critter.Service.CustomerService;

import java.util.List;
import java.util.Optional;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private static final Logger log = LogManager.getLogger(PetController.class);

    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ConverterUtils dtoService;

    @GetMapping
    public ResponseEntity<List<PetDTO>> getPets() {
        log.info("Get all pets");
        List<Pet> pets = petService.getAllPets();
        return ResponseEntity.ok(dtoService.convertToPetDTOList(pets));
    }

    @PostMapping
    public ResponseEntity<PetDTO> savePet(@RequestBody PetDTO petDTO) {
        Pet pet = petService.save(dtoService.convertToEntity(petDTO));
        Customer customerDto = customerService.getCustomerById(petDTO.getOwnerId());
        List<Pet> petDtoList = petService.getPetsByCustomerId(customerDto.getCustomerId());
        if (petDtoList != null) {
            if (customerDto.getPets() != null) {
                petDtoList.add(pet);
                customerDto.setPets(petDtoList);
            } else {
                customerDto.setPets(petDtoList);
            }
            customerService.save(customerDto);
        }
        return ResponseEntity.ok(dtoService.convertToDTO(pet));

    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetDTO> getPet(@PathVariable long petId) {
        log.info("Get pet by ID: {}", petId);

        try {
            Optional<Pet> optional = Optional.ofNullable(petService.getPetById(petId));
            return optional.map(pet -> ResponseEntity.ok(dtoService.convertToDTO(pet)))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception ex) {
            log.error("Error when getting pet: ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<PetDTO>> getPetsByOwner(@PathVariable long ownerId) {
        log.info("Get pets by owner ID: " + ownerId);

        try {
            List<Pet> petList = petService.getPetsByCustomerId(ownerId);
            if (petList == null) {
                log.warn("No pets found for owner with id: " + ownerId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            log.info("Pets for owner with id: " + ownerId);
            List<PetDTO> petsDto = dtoService.convertToPetDTOList(petList);
            return ResponseEntity.ok((petsDto));
        } catch (Exception ex) {
            log.error("Error when getting pet: ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
