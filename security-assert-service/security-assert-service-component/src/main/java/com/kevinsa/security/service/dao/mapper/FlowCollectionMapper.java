package com.kevinsa.security.service.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.kevinsa.security.service.dao.dto.FlowOriginDTO;

@Mapper
public interface FlowCollectionMapper {

    @Insert("INSERT IGNORE INTO flow_origin_data (`business`, `api_host`, `api_path`, `headers_info`, `request_payload`, " +
            "`request_json_tree`, `response_body`, `response_json_tree`, `status`, `create_time`) VALUES (" +
            " #{business}, #{apiHost}, #{apiPath}, #{headersInfo}, #{requestPayload}, #{requestJsonTree}, " +
            "#{responseBody}, #{responseJsonTree}, #{status}, #{createTime})")
    void insertData(FlowOriginDTO flowOriginDTO);

    @Select("SELECT distinct api_path from flow_origin_data WHERE status = 1")
    List<String> getDistinctInfoByBiz(String business);

    @Results(
            value = {
                    @Result(property = "apiHost", column = "api_host"),
                    @Result(property = "apiPath", column = "api_path"),
                    @Result(property = "headersInfo", column = "headers_info"),
                    @Result(property = "requestPayload", column = "request_payload"),
                    @Result(property = "requestJsonTree", column = "request_json_tree"),
                    @Result(property = "responseBody", column = "response_body"),
                    @Result(property = "responseJsonTree", column = "response_json_tree"),
                    @Result(property = "dataSource", column = "data_source")
            }
    )
    @Select("SELECT * FROM flow_origin_data WHERE status = 1 ORDER BY version desc LIMIT 1")
    FlowOriginDTO getInfoByBizAndPath(String business, String apiPath);
}
