package main.java.com.kevinsa.assertlog.action;

import main.java.com.kevinsa.assertlog.dto.UnloadReportDTO;
import main.java.com.kevinsa.assertlog.utils.HttpClientUtils;
import main.java.com.kevinsa.assertlog.utils.ObjectMapperUtils;

public class UnloadAction {

    private final HttpClientUtils httpClientUtils = new HttpClientUtils();

    public void executor(String uuid) {
        UnloadReportDTO unloadReportDTO = UnloadReportDTO.builder()
                .uuid(uuid)
                .build();
        httpClientUtils.doPost("http://127.0.0.1:8088/plugin/burpsuite/status/unload", ObjectMapperUtils.toJSON(unloadReportDTO));
    }
}
