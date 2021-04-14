package edu.poly.lab.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.poly.lab.models.Staff;
import edu.poly.lab.models.records;
import edu.poly.lab.repositories.RecordRepository;
import edu.poly.lab.repositories.StaffRepository;
@Service
public class RecordServiceImpl implements RecordService {
	@Autowired
	private RecordRepository recordRepository;

	@Autowired
	private StaffRepository staffRepository;

	@Override
	public List<Staff> findAllRecords() {
		return (List<Staff>) staffRepository.findAll();
	}

	@Override
	public records save(records entity) {
		return recordRepository.save(entity);
	}

	@Override
	public List<records> saveAll(Iterable<records> entities) {
		return (List<records>) recordRepository.saveAll(entities);
	}

	@Override
	public Optional<records> findById(Integer id) {
		return recordRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return recordRepository.existsById(id);
	}

	@Override
	public Iterable<records> findAll() {
		return recordRepository.findAll();
	}

	@Override
	public Iterable<records> findAllById(Iterable<Integer> ids) {
		return recordRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return recordRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		recordRepository.deleteById(id);
	}

	@Override
	public void delete(records entity) {
		recordRepository.delete(entity);
	}

	@Override
	public void deleteAll(List<records> entities) {
		recordRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		recordRepository.deleteAll();
	}

}
