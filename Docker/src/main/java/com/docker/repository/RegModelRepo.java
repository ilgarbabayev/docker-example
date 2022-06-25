package com.docker.repository;

import com.docker.model.ReqModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegModelRepo extends JpaRepository<ReqModel, Long> {

    public ReqModel findByName(String name);
}
