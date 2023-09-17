package com.backend.dao;

import com.backend.entity.Matomo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatomoRepository extends JpaRepository<Matomo, Long> {
    Optional<Matomo> findByNameAndNamespace(String name, String namespace);
}
