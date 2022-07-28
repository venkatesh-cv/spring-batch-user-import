package com.athenahealth.collector.rules.imports.users.repository;

import com.athenahealth.collector.rules.imports.users.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<Title,Integer> {
}
