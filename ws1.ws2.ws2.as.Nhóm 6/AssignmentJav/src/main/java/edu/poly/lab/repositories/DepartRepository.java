package edu.poly.lab.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.poly.lab.models.Depart;

@Repository
public interface DepartRepository extends CrudRepository<Depart, Integer> {
	List<Depart> findByNameLikeOrderByName(String name);
}
