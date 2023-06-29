package com.kevinsa.security.service.service.common.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kevinsa.security.service.service.common.DataVersionCompareService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DataVersionCompareServiceImpl implements DataVersionCompareService {

    @Override
    public boolean jsonTreeVersionCompare(List<String> JsonTree) {
        return false;
    }
}
