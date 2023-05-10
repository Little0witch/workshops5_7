package com.bsuir.workshops.laba_1.database;


import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<DbEntity, Integer> {
}
