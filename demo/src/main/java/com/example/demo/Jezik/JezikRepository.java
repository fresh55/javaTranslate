package com.example.demo.Jezik;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JezikRepository extends JpaRepository<Jezik, Long> {

    @Query("SELECT s FROM Jezik s WHERE s.ime = ?1")
    Optional<Jezik> findJezikByIme(String ime);

}
