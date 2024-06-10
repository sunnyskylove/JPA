package com.ohgiraffers.associationmapping.section01.manytoone;

import jakarta.persistence.ManyToOne;
import org.springframework.stereotype.Service;

@Service
public class ManyToOneService {


    private ManyToOneRepository manyToOneRepository;

    public ManyToOneService(ManyToOneRepository manyToOneRepository) {
        this.manyToOneRepository = manyToOneRepository;
    }

}
