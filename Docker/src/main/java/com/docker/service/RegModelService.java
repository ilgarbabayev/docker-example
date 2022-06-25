package com.docker.service;

import com.docker.model.ReqModel;
import com.docker.repository.RegModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegModelService {

    @Autowired
    RegModelRepo regModelRepo;

    public Long saveRegModel(ReqModel reqModel) {
        return regModelRepo.save(reqModel).getId();
    }

    public List<ReqModel> getAllModels(){
        return regModelRepo.findAll();
    }

    public ReqModel findModelByName(String name) {
        return regModelRepo.findByName(name);
    }
}
