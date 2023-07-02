package com.kevinsa.security.service.cpompent.utils;

import com.kevinsa.security.service.Application;
import com.kevinsa.security.service.utils.comparator.StringLengthAndCharAt;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class CommonUtilsTest {


    @Test
    public void compareTest() {
        List<String> stringList = Lists.list("result", "total", "error_msg", "size", "requestId", "hint", "orderList[0].logisticsId", "orderList[0].buyerNick", "orderList[0].sellerCoverTax", "orderList[0].remindShipmentSign", "orderList[0].bargain", "orderList[0].expressFee", "orderList[0].closeable", "orderList[0].showAuctionCommissionFee", "orderList[0].payType", "orderList[0].validBalancePayTimeStamp", "orderList[0].province", "orderList[0].expressCode", "orderList[0].addressEditable", "orderList[0].orderProductList[0].itemPicUrl", "orderList[0].orderProductList[0].auto", "orderList[0].orderProductList[0].discountFee", "orderList[0].orderProductList[0].insuranceRefuseReason", "orderList[0].orderProductList[0].num", "orderList[0].orderProductList[0].qualityReportFlag", "orderList[0].orderProductList[0].buyerId", "orderList[0].orderProductList[0].platformFee", "orderList[0].orderProductList[0].productChargesTypeDesc", "orderList[0].orderProductList[0].tid", "orderList[0].orderProductList[0].relItemId", "orderList[0].orderProductList[0].sellerId", "orderList[0].orderProductList[0].price", "orderList[0].orderProductList[0].id", "orderList[0].orderProductList[0].skuId", "orderList[0].orderProductList[0].cpsType");
        stringList.sort(new StringLengthAndCharAt());
        System.out.println(stringList);
    }
}
