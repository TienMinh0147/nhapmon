 package edu.poly.lab.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.poly.lab.models.Staff;
@Repository
public interface StaffRepository extends CrudRepository<Staff, Long>{

}
