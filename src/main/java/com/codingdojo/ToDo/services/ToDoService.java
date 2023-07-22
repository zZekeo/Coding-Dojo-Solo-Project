package com.codingdojo.ToDo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.ToDo.models.ToDo;
import com.codingdojo.ToDo.repositories.ToDoRepository;

import jakarta.validation.Valid;

@Service
public class ToDoService {
	@Autowired
	ToDoRepository todoRepository;
	
	// CREATE
	public ToDo create(ToDo newToDo) {
		return todoRepository.save(newToDo);
	}
	
	// READ ONE
	public ToDo getOne(Long id) {
		return todoRepository.findById(id).orElse(null);
	}
	
	// READ ALL
	public List<ToDo> getAll() {
		return todoRepository.findAll();
	}
	
	// UPDATE
	public ToDo updateToDo(@Valid ToDo updatedToDo) {
		return todoRepository.save(updatedToDo);
	}
	
	// DELETE
	public void deleteToDo(Long id) {
		todoRepository.deleteById(id);
	}
}