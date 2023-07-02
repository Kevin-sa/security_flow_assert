package com.kevinsa.security.service.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.kevinsa.security.service.dao.dto.FlowOriginDTO;

@Mapper
public interface FlowCollectionMapper {

    @Insert("INSERT IGNORE INTO flow_origin_data (`business`, `api_host`, `api_path`, `headers_info`, `request_payload`, " +
            "`request_json_tree`, `request_json_tree_hash`, `response_body`, `response_json_tree`, `response_json_tree_hash`, " +
            "`data_source`, `status`, `version`, `create_time`) VALUES (" +
            " #{business}, #{apiHost}, #{apiPath}, #{headersInfo}, #{requestPayload}, #{requestJsonTree}, #{requestJsonTreeHash}, " +
            "#{responseBody}, #{responseJsonTree}, #{responseJsonTreeHash}, #{dataSource}, #{status}, #{version}, #{createTime})")
    void insertData(FlowOriginDTO flowOriginDTO);

    @Select("SELECT distinct api_host from flow_origin_data WHERE business = #{business} AND status = #{status}")
    List<String> getDistinctHostByBiz(@Param("business") String business, @Param("status") int status);

    @Select("SELECT distinct api_path from flow_origin_data WHERE business = #{business} AND api_host = #{apiHost} " +
            "AND status = #{status}")
    List<String> getDistinctPathByBiz(@Param("business") String business, @Param("status") int status,
                                      @Param("apiHost") String apiHost);

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
    @Select("SELECT * FROM flow_origin_data WHERE status = 1 AND business = #{business} AND api_path = #{path}" +
            " AND api_host = #{host} AND status = #{status}" +
            " ORDER BY version desc LIMIT 1")
    FlowOriginDTO getInfoByBizAndPath(@Param("business") String business,
                                      @Param("host") String host,
                                      @Param("path") String path,
                                      @Param("status") int status);


    @Select("SELECT version FROM flow_origin_data WHERE business = #{business} AND api_host = #{apiHost} AND api_path = #{apiPath}" +
            " AND data_source = #{source} " +
            "ORDER BY version DESC Limit 1")
    Integer selectMaxVersionByApiInfo(@Param("business") String business, @Param("apiHost") String apiHost, @Param("apiPath") String apiPath,
                                      @Param("source") int source);
}
