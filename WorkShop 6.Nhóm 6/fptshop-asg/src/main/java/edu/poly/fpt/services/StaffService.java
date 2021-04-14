package edu.poly.fpt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import edu.poly.fpt.models.Depart;
import edu.poly.fpt.models.Staff;
import edu.poly.fpt.repositories.StaffRepsitory;

public interface StaffService {

	List<Staff> findByNameLikeOrderByName(String name);

	void deleteAll();

	void deleteAll(List<Staff> entities);

	void delete(Staff entity);

	void deleteById(Long id);

	long count();

	List<Staff> findAllById(Iterable<Long> ids);

	List<Staff> findAll();

	boolean existsById(Long id);

	Optional<Staff> findById(Long id);

	List<Staff> saveAll(List<Staff> entities);

	Staff save(Staff entity);


	List<Depart> findAllDeparts();



}
