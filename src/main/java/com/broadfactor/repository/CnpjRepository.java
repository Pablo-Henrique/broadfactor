package com.broadfactor.repository;

import com.broadfactor.model.Cnpj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CnpjRepository extends JpaRepository<Cnpj, Long> {
}