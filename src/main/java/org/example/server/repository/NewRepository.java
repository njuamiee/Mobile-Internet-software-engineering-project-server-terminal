package org.example.server.repository;

import org.example.server.enums.NewType;
import org.example.server.po.New;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewRepository extends JpaRepository<New, Integer> {

    New findNewById(Integer newId);
    List<New> findByType(NewType type);
}
