package com.kevinsa.security.service.dao.mapper;

import com.kevinsa.security.service.dao.po.AssetJsonPathRulePO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AssetActionRuleMapper {

    @Insert("INSERT INTO assert_action_rule (`business`, `api_host`, `api_path`, `data`, `type`, `status`," +
            " `create_time`, `update_time`) VALUES (#{business}, #{apiHost}, #{apiPath}, #{data}, #{type}, #{status}," +
            " #{create_time}, #{update_time})")
    void insertRule(AssetJsonPathRulePO assetJsonPathRuleDTO);

    @Results(
            value = {
                    @Result(property = "apiHost", column = "api_host"),
                    @Result(property = "apiPath", column = "api_path"),
            }
    )
    @Select("SELECT * FROM assert_action_rule WHERE status = #{status}" +
            " AND type = #{type} AND business = #{business}" +
            " AND api_host = #{apiHost} AND api_path = #{apiPath} LIMIT 1")
    AssetJsonPathRulePO getRuleByTypeAndApiInfo(@Param("status") Integer status, @Param("type") Integer type,
                                                 @Param("business") String business, @Param("apiHost") String apiHost,
                                                 @Param("apiPath") String apiPath);

    @Select("SELECT id FROM assert_action_rule WHERE type=#{type}")
    Long getIdByType(@Param("type") int type);
}
