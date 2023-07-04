package com.kevinsa.security.service.service.common;

import java.util.List;

public interface HashCommonService {

    String getJsonTreeHash(String business, String apiHost, String apiPath, int dataSource,
                           List<String> jsonTreeList);

    String getApiHash(String business, String apiHost, String apiPath, int dataSource, int version);
}
