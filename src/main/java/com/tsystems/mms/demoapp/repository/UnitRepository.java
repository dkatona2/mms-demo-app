package com.tsystems.mms.demoapp.repository;

import com.tsystems.mms.demoapp.domain.OrganisationalUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<OrganisationalUnit, Long> {

}
