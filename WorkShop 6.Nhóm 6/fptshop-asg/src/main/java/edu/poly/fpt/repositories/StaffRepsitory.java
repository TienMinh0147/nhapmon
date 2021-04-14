package edu.poly.fpt.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.poly.fpt.models.Staff;



@Repository
public interface StaffRepsitory extends CrudRepository<Staff, Long> {

	List<Staff> findByNameLikeOrderByName(String name);

}
