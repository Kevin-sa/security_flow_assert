package com.kevinsa.security.service.controller.plugin;

import com.kevinsa.security.service.dto.RequestInfoDTO;
import com.kevinsa.security.service.dto.ResponseInfoDTO;
import com.kevinsa.security.service.dto.UnloadReportDTO;
import com.kevinsa.security.service.enums.ErrorCodeEnum;
import com.kevinsa.security.service.service.collect.BaseExecutor;
import com.kevinsa.security.service.vo.ApiResult;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/plugin/burpsuite")
public class BurpSuitePluginController {
    private final String PREFIX = "BurpSuitePluginController ->";

    @Autowired
    private BaseExecutor baseExecutor;

    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public ApiResult<Boolean> requestInfoController(@RequestBody RequestInfoDTO requestInfoDTO) {
        try {
            log.info("/request");
            baseExecutor.requestExecute(requestInfoDTO);
            return ApiResult.buildSuccess();
        } catch (Exception e) {
            log.error(PREFIX + "requestInfoController error:", e);
            return ApiResult.buildFailure(ErrorCodeEnum.UNKNOWN.getCode(), ErrorCodeEnum.UNKNOWN.getMsg());
        }
    }

    @RequestMapping(value = "/response", method = RequestMethod.POST)
    public ApiResult<Boolean> responseInfoController(@RequestBody ResponseInfoDTO responseInfoDTO) {
        try {
            log.info("/response");
            baseExecutor.responseExecute(responseInfoDTO);
            return ApiResult.buildSuccess();
        } catch (Exception e) {
            log.error(PREFIX + "responseInfoController error:", e);
            return ApiResult.buildFailure(ErrorCodeEnum.UNKNOWN.getCode(), ErrorCodeEnum.UNKNOWN.getMsg());
        }
    }

    @RequestMapping(value = "/status/unload", method = RequestMethod.POST)
    public ApiResult<Boolean> unloadController(@RequestBody UnloadReportDTO unloadReportDTO) {
        try {
            log.info("/status/unload");
            baseExecutor.pluginUnload(unloadReportDTO.getUuid());
            return ApiResult.buildSuccess();
        } catch (Exception e) {
            log.error(PREFIX + "responseInfoController error:", e);
            return ApiResult.buildFailure(ErrorCodeEnum.UNKNOWN.getCode(), ErrorCodeEnum.UNKNOWN.getMsg());
        }
    }
}
