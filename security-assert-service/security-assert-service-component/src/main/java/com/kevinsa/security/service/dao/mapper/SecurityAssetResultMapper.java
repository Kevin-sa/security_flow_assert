package com.kevinsa.security.service.dao.mapper;

import com.kevinsa.security.service.dao.dto.SecurityAssetResultDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SecurityAssetResultMapper {

    @Insert("INSERT INTO security_asset_result (`rule_id`, `flow_id`, `replay_flow_id`, " +
            " `response_body`,`diff_vale`, `create_time`)" +
            " VALUES (#{ruleId}, #{flowId}, #{replayFlowId}, #{responseBody}, #{diffValue}, #{createTime})")
    void insert(SecurityAssetResultDTO securityAssetResultDTO);
}



