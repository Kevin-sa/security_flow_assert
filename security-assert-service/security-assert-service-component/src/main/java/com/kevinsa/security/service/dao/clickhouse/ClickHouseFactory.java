package com.kevinsa.security.service.dao.clickhouse;

import com.kevinsa.security.service.constant.ClickHouseConf;
import com.kevinsa.security.service.constant.SwitchConf;
import com.kevinsa.security.service.dao.po.ClickHouseBaseInfoPO;
import com.kevinsa.security.service.dao.po.ClickHouseExtendsInfoPO;
import com.kevinsa.security.service.service.collect.BaseExecutor;
import com.kevinsa.security.service.utils.ObjectMapperUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.yandex.clickhouse.ClickHouseDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

@Component
public class ClickHouseFactory {

    private static final String PREFIX = "BaseExecutor ->";
    private static final Logger logger = LoggerFactory.getLogger(BaseExecutor.class);


    private ClickHouseDataSource dataSource = null;

    private ClickHouseDataSource getConnect() {
        try {
            if (!SwitchConf.CLICKHOUSE_COLLECT) {
                return null;
            }
            String url = String.format("jdbc:clickhouse://%s?max_execution=5&socket_timeout=5000", ClickHouseConf.HOST);
            Properties properties = new Properties();
            if (!Strings.isBlank(ClickHouseConf.USER)) {
                properties.setProperty("user", ClickHouseConf.USER);
            }
            if (!Strings.isBlank(ClickHouseConf.PASSWD)) {
                properties.setProperty("password", ClickHouseConf.PASSWD);
            }
            return new ClickHouseDataSource(url, properties);
        }catch (Exception e) {
            logger.error(PREFIX + "getConnect error", e);
        }
        return null;
    }

    private ClickHouseFactory() throws ClassNotFoundException {
        Class.forName("ru.yandex.clickhouse.ClickHouseDriver");
        dataSource = getConnect();
    }

    private ClickHouseDataSource getInstance() {
        if (dataSource == null) {
            dataSource = getConnect();
        }
        return dataSource;
    }

    public void insertFlowRecord(ClickHouseBaseInfoPO clickHouseBaseInfoPO, ClickHouseExtendsInfoPO clickHouseExtendsInfoPO) {
        try {
            if (!SwitchConf.CLICKHOUSE_COLLECT) {
                return;
            }
            Connection conn = getInstance().getConnection();
            String sql = "INSERT INTO security_flow_assert.flow_data_record (" +
                    "create_time, base_info, extend_info" +
                    ") VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, System.currentTimeMillis());
            ps.setString(2, ObjectMapperUtils.toJSON(clickHouseBaseInfoPO));
            ps.setString(3, ObjectMapperUtils.toJSON(clickHouseExtendsInfoPO));
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            logger.error(PREFIX + "insertFlowRecord error:", e);
        }
    }


}
