package com.example.nationflags.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository; 

import com.example.nationflags.model.Flag;

@Repository
public interface FlagRepository<Flag, String> extends CrudRepository<Flag, String> {
}
