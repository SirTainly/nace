package com.luxoft.techtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luxoft.techtest.model.NaceData;

@Repository
public interface NaceRepository extends JpaRepository<NaceData, String> {

}

