package edu.poly.lab.services;

import java.util.List;
import java.util.Optional;

import edu.poly.lab.models.Staff;
import edu.poly.lab.models.records;

public interface RecordService {

	void deleteAll();

	void deleteAll(List<records> entities);

	void delete(records entity);

	void deleteById(Integer id);

	long count();

	Iterable<records> findAllById(Iterable<Integer> ids);

	Iterable<records> findAll();

	boolean existsById(Integer id);

	Optional<records> findById(Integer id);

	List<records> saveAll(Iterable<records> entities);

	records save(records entity);

	List<Staff> findAllRecords();

}
