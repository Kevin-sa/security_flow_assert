package main.java.com.kevinsa.assertlog.action;

import main.java.com.kevinsa.assertlog.constant.PluginConfig;
import main.java.com.kevinsa.assertlog.dto.UnloadReportDTO;
import main.java.com.kevinsa.assertlog.utils.HttpClientUtils;
import main.java.com.kevinsa.assertlog.utils.ObjectMapperUtils;

public class UnloadAction {

    private final HttpClientUtils httpClientUtils = new HttpClientUtils();

    public void executor(String uuid) {
        UnloadReportDTO unloadReportDTO = UnloadReportDTO.builder()
                .uuid(uuid)
                .build();
        httpClientUtils.doPost(PluginConfig.SERVER_ADDRESS + PluginConfig.UNLOAD_PATH, ObjectMapperUtils.toJSON(unloadReportDTO));
    }
}
