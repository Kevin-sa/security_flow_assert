package com.kevinsa.security.service.service.common.impl;

import com.kevinsa.security.service.service.common.HashCommonService;
import com.kevinsa.security.service.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HashCommonServiceImpl implements HashCommonService {

    @Autowired
    private HashUtils hashUtils;

    @Override
    public String getJsonTreeHash(String business, String apiHost, String apiPath, int dataSource, List<String> jsonTreeList) {
        String message = business + "|" + apiHost + "|" + apiPath + "|" + dataSource + "|"
                + String.join(",", jsonTreeList);
        return hashUtils.md5(message);
    }

    @Override
    public String getApiHash(String business, String apiHost, String apiPath, int dataSource, int version) {
        String message = business + "|" + apiHost + "|" + apiPath + "|" + dataSource + "|" + version;
        return hashUtils.md5(message);
    }
}
