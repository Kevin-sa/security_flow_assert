package com.kevinsa.security.service.dao.mapper;

import com.kevinsa.security.service.dao.po.SecurityAssetResultPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SecurityAssetResultMapper {

    @Insert("INSERT INTO security_asset_result (`rule_id`, `flow_id`, `replay_flow_id`, " +
            " `response_body`,`diff_value`, `create_time`)" +
            " VALUES (#{ruleId}, #{flowId}, #{replayFlowId}, #{responseBody}, #{diffValue}, #{createTime})")
    void insert(SecurityAssetResultPO securityAssetResultPO);
}



