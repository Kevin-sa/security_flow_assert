package com.kevinsa.security.service.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.kevinsa.security.service.dao.dto.FlowOriginDTO;

@Mapper
public interface FlowCollectionMapper {

    @Insert("INSERT INTO flow_origin_data (`business`, `api_host`, `api_path`, `headers_info`, `request_payload`, " +
            "`request_json_tree`, `response_body`, `response_json_tree`, `status`, `create_time`) VALUES (" +
            " #{business}, #{apiHost}, #{apiPath}, #{headersInfo}, #{requestPayload}, #{requestJsonTree}, " +
            "#{responseBody}, #{responseJsonTree}, #{status}, #{createTime})")
    void insertData(FlowOriginDTO flowOriginDTO);
}
