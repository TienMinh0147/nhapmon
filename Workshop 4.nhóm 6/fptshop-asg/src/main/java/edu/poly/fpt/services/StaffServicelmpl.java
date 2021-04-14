package edu.poly.fpt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.poly.fpt.models.Depart;
import edu.poly.fpt.models.Staff;
import edu.poly.fpt.repositories.DepartRepository;
import edu.poly.fpt.repositories.StaffRepsitory;

@Service
public class StaffServicelmpl implements StaffService {
	@Autowired
	private StaffRepsitory staffRepsitory;
	
	@Autowired
	private DepartRepository departRepository;
	
	@Override
	public List<Depart> findAllDeparts() {
		return (List<Depart>) departRepository.findAll();
	}
	
	@Override
	public Staff save(Staff entity) {
		return staffRepsitory.save(entity);
	}

	@Override
	public List<Staff> saveAll(List<Staff> entities) {
		return (List<Staff>) staffRepsitory.saveAll(entities);
	}

	@Override
	public Optional<Staff> findById(Long id) {
		return staffRepsitory.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return staffRepsitory.existsById(id);
	}

	@Override
	public List<Staff> findAll() {
		return (List<Staff>) staffRepsitory.findAll();
	}

	@Override
	public List<Staff> findAllById(Iterable<Long> ids) {
		return (List<Staff>) staffRepsitory.findAllById(ids);
	}

	@Override
	public long count() {
		return staffRepsitory.count();
	}

	@Override
	public void deleteById(Long id) {
		staffRepsitory.deleteById(id);
	}

	@Override
	public void delete(Staff entity) {
		staffRepsitory.delete(entity);
	}

	@Override
	public void deleteAll(List<Staff> entities) {
		staffRepsitory.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		staffRepsitory.deleteAll();
	}

	@Override
	public List<Staff> findByNameLikeOrderByName(String name) {
		return staffRepsitory.findByNameLikeOrderByName("%" + name + "%");

	}

}
