package edu.poly.lab.services;

import java.util.List;
import java.util.Optional;

import edu.poly.lab.dtos.StaffDto;
import edu.poly.lab.models.Depart;
import edu.poly.lab.models.Staff;

public interface StaffService {

	void deleteAll();

	void deleteAll(List <Staff> entities);

	void delete(Staff entity);

	void deleteById(Long id);

	long count();

	Iterable<Staff> findAllById(Iterable<Long> ids);

	Iterable<Staff> findAll();

	boolean existsById(Long id);

	Optional<Staff> findById(Long id);

	List<Staff> saveAll(Iterable<Staff> entities);

	Staff save(Staff entity);

	List<Depart> findAllDeparts();
	

}
