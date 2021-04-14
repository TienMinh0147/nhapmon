package edu.poly.fpt.repositories;



import org.springframework.data.repository.CrudRepository;

import edu.poly.fpt.models.User;



public interface UserRepository extends CrudRepository<User, String>{

}
