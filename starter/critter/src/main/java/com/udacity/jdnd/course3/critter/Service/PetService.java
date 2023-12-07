package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Exception.PetException;
import com.udacity.jdnd.course3.critter.Repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get a list of all pets and their details.
     *
     * @return A list of PetDTOs containing information about all pets, including owner's IDs.

     */

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    /**
     * Get a list of pets associated with a specific customer.
     *
     * @param customerId
     * @return A list of PetDTOs containing information about pets
     * @throws PetException If no pets are found for the specified customer.
     */

    /**
     * Save a pet's information including its owner.
     *
     * @param pet The PetDTO object
     * @return A PetDTO object representing the saved pet.
     * @throws PetException If the owner (Customer) is not found with the specified
     *                      ID.
     */

    public Pet save(Pet pet) {
        Optional<Customer> customer = customerRepository.findById(pet.getCustomer().getCustomerId());
        if (customer.isPresent()) {
            pet.setCustomer(customer.get());
            return petRepository.saveAndFlush(pet);
        } else {
            throw new PetException("Not found Customer with id:" + pet.getCustomer().getCustomerId());
        }
    }

    /**
     * Get pet information by its unique ID.
     *
     * @param id The ID of the pet
     * @return A PetDTO object containing pet details, including the owner's ID.
     * @throws PetException If no pet with the specified id is found.
     */

    public Pet getPetById(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()) {
            return pet.get();
        } else {
            throw new PetException("Not found Pet with id:" + id);
        }
    }

    public List<Pet> getPetsByCustomerId(Long customerId) {
        return petRepository.findByCustomerId(customerId);
    }

    /**
     * Get a list of pets by their unique id.
     *
     * @param petIds
     * @return A list of PetDTOs containing information about the selected pets.
     */

    public List<Pet> getPetsByPetIds(List<Long> petIds) {
        return petRepository.findByPetIdIn(petIds);
    }

}
