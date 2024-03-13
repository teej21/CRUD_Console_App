package org.teej21.crud_console_app.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.teej21.crud_console_app.enity.Student;

import java.util.List;
@Repository
public class StudentDAO_Implement implements StudentDAO {
	private final EntityManager entityManager;
	@Autowired
	public StudentDAO_Implement(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public void save(Student theStudent) {
		entityManager.persist(theStudent);
	}
	
	@Override
	public Student findById(Integer id) {
		return this.entityManager.find(Student.class, id);
	}
	
	@Override
	public List<Student> findAll() {
		TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);
		return theQuery.getResultList();
	}
	
	@Override
	public List<Student> findByLastName(String theLastName) {
		TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:lastName", Student.class);
		theQuery.setParameter("lastName", theLastName);
		return theQuery.getResultList();
	}
	
	@Override
	@Transactional
	public void update(Student theStudent) {
		entityManager.merge(theStudent); // merge() is used to update an existing entity in the
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {
		Student theStudent = this.findById(id);
		entityManager.remove(theStudent);
	
	}
	
	@Override
	@Transactional
	public int deleteAll() {
		TypedQuery<Student> theQuery = entityManager.createQuery("DELETE FROM Student", Student.class);
		return theQuery.executeUpdate(); // executeUpdate() returns the number of rows affected
	}
}
