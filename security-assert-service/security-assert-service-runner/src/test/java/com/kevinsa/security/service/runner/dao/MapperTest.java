package com.kevinsa.security.service.runner.dao;

import com.kevinsa.security.service.dao.dto.FlowOriginDTO;
import com.kevinsa.security.service.dao.mapper.FlowCollectionMapper;
import com.kevinsa.security.service.runner.TaskApplication;
import com.kevinsa.security.service.utils.ObjectMapperUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = TaskApplication.class)
@RunWith(SpringRunner.class)
public class MapperTest {

    @Resource
    private FlowCollectionMapper flowCollectionMapper;

    @Test
    public void originDataInsertTest() {
//        FlowOriginDTO flowOriginDTO = FlowOriginDTO.builder()
//                .apiHost("kevinsa.com")
//                .apiPath("/aaa/aaa")
//                .business("kevinsa-com")
//                .createTime(System.currentTimeMillis() / 1000)
//                .dataSource(1)
//                .status(0)
//                .build();
        String msg = "{\"id\":344,\"business\":\"kwai-shop\",\"apiHost\":\"s.kwaixiaodian.com\",\"apiPath\":\"/rest/app/tts/ks/seller/order/query/v2\",\"method\":\"POST\",\"headersInfo\":\"{\\\"Kpf\\\": \\\"PC_WEB\\\", \\\"Kpn\\\": \\\"\\\", \\\"Accept\\\": \\\"application/json;charset=UTF-8\\\", \\\"Cookie\\\": \\\"_did=web_94497837391561E0; did=web_6cxiiorb4c9jkgewimoz3il6rbkd0lty; userId=2050303696; sid=kuaishou.shop.b; KS-CSRF-Token=D874476DF5EE45B503B91A70B7C55005; kuaishou.shop.im_st=ChNrdWFpc2hvdS5zaG9wLmltLnN0EqABTQ7aA7kHoULWl4buegMHdFfe53anXPfWJsiDIpgxA1BH6MIMwh7N8_gs4T8t-84E8oVBuzT4SYK5ZkqmjxGA-Kt9i3KTN6rKpOs9mAWIDimDM99MRc7CozLZ2_C5kNbhLV66Cu7oZheiEayv7vhH-c5wsrTXQcSpPkqV4wXykDyNqvOnBZ9KSh3a-KD8lzVcuQoHi_L5YtK4GsR4ovzkJBoSuNcvxLNkGcxgncXcDA8V5k3BIiBn83iJ0kKMuKGeE3ZWl2tkCTS4pr9dP2CRg5wfpAf6aCgFMAE; kNginxAB=false; kuaishou.shop.b_st=ChJrdWFpc2hvdS5zaG9wLmIuc3QSoAHEeybrRIE4ML4xhZfZyVqM8ANy7huMvSaO9v7KQy-HzTVUMaXfW6eRZ3mRSvKmo7QFpR7lZHopTeCREmzRVbDIQ_BWoqVDxIQEM33jbnU-XctW5mDDgPxQey8C75ZZhLlFl--JbQ6nhW9YFpYnaZVVSHbW26sn8pgdIpyZHsZ1azugh6a0FxvQ_T3tcnpH4vp1OSSI3_kB_cYR3alDFCM_GhJxLmZe4hrNJlIURRIhhs2U8_MiIPSkZtGdql7W7zojPgxf62PwhBesDejszasXHBZ5U-clKAUwAQ; kuaishou.shop.b_ph=be2a7df8e323691892ea5298e298e8310e81; merchantSessionKey=1688307382387_761fe8f6ed64b7d15de1b251f7ca285c\\\", \\\"Trace-Id\\\": \\\"1.0.0.1688307594482.12\\\", \\\"Sec-Ch-Ua\\\": \\\"\\\\\\\"Not.A/Brand\\\\\\\";v=\\\\\\\"8\\\\\\\", \\\\\\\"Chromium\\\\\\\";v=\\\\\\\"114\\\\\\\", \\\\\\\"Google Chrome\\\\\\\";v=\\\\\\\"114\\\\\\\"\\\", \\\"User-Agent\\\": \\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36\\\", \\\"Content-Type\\\": \\\"application/json;charset=UTF-8\\\", \\\"Ks-Csrf-Token\\\": \\\"D874476DF5EE45B503B91A70B7C55005\\\", \\\"Content-Length\\\": \\\"774\\\", \\\"Sec-Ch-Ua-Mobile\\\": \\\"?0\\\", \\\"Merchantsessionkey\\\": \\\"1688307382387_761fe8f6ed64b7d15de1b251f7ca285c\\\", \\\"Sec-Ch-Ua-Platform\\\": \\\"\\\\\\\"Windows\\\\\\\"\\\"}\",\"requestPayload\":\"{\\\"orderEsSearchParam\\\": {\\\"oid\\\": \\\"\\\", \\\"limit\\\": 10, \\\"offset\\\": 0, \\\"status\\\": 0, \\\"cpsType\\\": 0, \\\"endTime\\\": 1688313599999, \\\"roleType\\\": 0, \\\"sortType\\\": 1, \\\"buyerNick\\\": \\\"\\\", \\\"expressNo\\\": \\\"\\\", \\\"itemTitle\\\": \\\"\\\", \\\"startTime\\\": 1672761600000, \\\"tradeType\\\": 0, \\\"buyerMixed\\\": \\\"\\\", \\\"checkMerge\\\": true, \\\"activityType\\\": 0, \\\"flagTagCodes\\\": [], \\\"postSaleType\\\": 0, \\\"consigneeName\\\": \\\"\\\", \\\"extendReceipt\\\": 0, \\\"insuranceType\\\": 0, \\\"consigneePhone\\\": \\\"\\\", \\\"remindShipment\\\": 0, \\\"reportTypeList\\\": [], \\\"refundByDeposit\\\": 0, \\\"priorityDelivery\\\": 0, \\\"delayDeliveryType\\\": 0, \\\"distributeRoleName\\\": \\\"\\\", \\\"searchForOrderList\\\": 1, \\\"commissionRateRange\\\": {\\\"max\\\": \\\"\\\", \\\"min\\\": \\\"\\\"}, \\\"distributeRoleMixed\\\": \\\"\\\", \\\"estimatedIncomeRange\\\": {\\\"max\\\": \\\"\\\", \\\"min\\\": \\\"\\\"}, \\\"timeoutOfDeliveryType\\\": 0, \\\"privacyAuthorizeStatus\\\": 0, \\\"promiseDeliveryEndTime\\\": 0, \\\"promiseDeliveryStartTime\\\": 0}, \\\"frontPublishCacheSourceType\\\": 1}\",\"requestJsonTree\":\"[\\\"orderEsSearchParam.oid\\\", \\\"orderEsSearchParam.limit\\\", \\\"orderEsSearchParam.offset\\\", \\\"orderEsSearchParam.status\\\", \\\"orderEsSearchParam.cpsType\\\", \\\"orderEsSearchParam.endTime\\\", \\\"frontPublishCacheSourceType\\\", \\\"orderEsSearchParam.roleType\\\", \\\"orderEsSearchParam.sortType\\\", \\\"orderEsSearchParam.buyerNick\\\", \\\"orderEsSearchParam.expressNo\\\", \\\"orderEsSearchParam.itemTitle\\\", \\\"orderEsSearchParam.startTime\\\", \\\"orderEsSearchParam.tradeType\\\", \\\"orderEsSearchParam.buyerMixed\\\", \\\"orderEsSearchParam.checkMerge\\\", \\\"orderEsSearchParam.activityType\\\", \\\"orderEsSearchParam.postSaleType\\\", \\\"orderEsSearchParam.consigneeName\\\", \\\"orderEsSearchParam.extendReceipt\\\", \\\"orderEsSearchParam.insuranceType\\\", \\\"orderEsSearchParam.consigneePhone\\\", \\\"orderEsSearchParam.remindShipment\\\", \\\"orderEsSearchParam.refundByDeposit\\\", \\\"orderEsSearchParam.priorityDelivery\\\", \\\"orderEsSearchParam.delayDeliveryType\\\", \\\"orderEsSearchParam.distributeRoleName\\\", \\\"orderEsSearchParam.searchForOrderList\\\", \\\"orderEsSearchParam.distributeRoleMixed\\\", \\\"orderEsSearchParam.timeoutOfDeliveryType\\\", \\\"orderEsSearchParam.privacyAuthorizeStatus\\\", \\\"orderEsSearchParam.promiseDeliveryEndTime\\\", \\\"orderEsSearchParam.commissionRateRange.max\\\", \\\"orderEsSearchParam.commissionRateRange.min\\\", \\\"orderEsSearchParam.estimatedIncomeRange.max\\\", \\\"orderEsSearchParam.estimatedIncomeRange.min\\\", \\\"orderEsSearchParam.promiseDeliveryStartTime\\\"]\",\"requestJsonTreeHash\":\"2116F2760A904773E17CE2AA049B5C37\",\"responseBody\":\"{\\\"result\\\":1,\\\"total\\\":2,\\\"error_msg\\\":\\\"\\\",\\\"size\\\":2,\\\"requestId\\\":\\\"688397542569758363\\\",\\\"hint\\\":\\\"\\\",\\\"orderList\\\":[{\\\"logisticsId\\\":0,\\\"buyerNick\\\":\\\"安静一会儿\\\",\\\"sellerCoverTax\\\":false,\\\"remindShipmentSign\\\":0,\\\"bargain\\\":false,\\\"expressFee\\\":0,\\\"closeable\\\":false,\\\"showAuctionCommissionFee\\\":false,\\\"payType\\\":1,\\\"validBalancePayTimeStamp\\\":0,\\\"province\\\":\\\"北京市\\\",\\\"expressCode\\\":0,\\\"addressEditable\\\":false,\\\"orderProductList\\\":[{\\\"itemPicUrl\\\":\\\"https://u2-201.ecukwai.com/ufile/adsocial/1dc759e9-7c62-4ca8-9444-11891bdcbaa1.jpg\\\",\\\"auto\\\":0,\\\"discountFee\\\":0,\\\"insuranceRefuseReason\\\":\\\"\\\",\\\"num\\\":1,\\\"qualityReportFlag\\\":false,\\\"buyerId\\\":1893200559,\\\"platformFee\\\":0,\\\"productChargesTypeDesc\\\":\\\"\\\",\\\"tid\\\":2310800100800559,\\\"relItemId\\\":1482258265696,\\\"sellerId\\\":2050303696,\\\"price\\\":1,\\\"id\\\":69512533731559,\\\"skuId\\\":1482258268696,\\\"cpsType\\\":1,\\\"distributorId\\\":0,\\\"itemId\\\":1482258265696,\\\"productDenominationType\\\":0,\\\"roleDesc\\\":\\\"自营\\\",\\\"sourceType\\\":99,\\\"attached\\\":0,\\\"itemLinkUrl\\\":\\\"https://app.kwaixiaodian.com/merchant/shop/detail?id=1482258265696&hyId=kwaishop&layoutType=4\\\",\\\"skuNick\\\":\\\"\\\",\\\"roleName\\\":\\\"董淑华（商家）\\\",\\\"skuTotalFee\\\":1,\\\"firstOrderGuaranteeFlag\\\":false,\\\"shelfItemType\\\":0,\\\"estimatedIncome\\\":0,\\\"platformFeeRatio\\\":0,\\\"itemCdnPicUrl\\\":\\\"https://tx-ec.static.yximgs.com/ufile/adsocial/1dc759e9-7c62-4ca8-9444-11891bdcbaa1.jpg\\\",\\\"freightProviderExplainDesc\\\":\\\"\\\",\\\"commissionRate\\\":0,\\\"agentType\\\":\\\"\\\",\\\"originalPrice\\\":1,\\\"itemTitle\\\":\\\"测试商品勿拍不发货自建鲜花\\\",\\\"replied\\\":0,\\\"showInsuranceRefuseTag\\\":false,\\\"refundList\\\":[{\\\"refundFee\\\":1,\\\"statusViewDesc\\\":\\\"\\\",\\\"createTime\\\":1681819320074,\\\"negotiateStatus\\\":1,\\\"refundFeeSource\\\":1,\\\"refundId\\\":2310800110454559,\\\"statusView\\\":0,\\\"status\\\":60}],\\\"cpsDesc\\\":\\\"自营\\\",\\\"oid\\\":2310800100800559,\\\"roleType\\\":1,\\\"memberNameType\\\":0,\\\"distributorName\\\":\\\"\\\",\\\"industryShowInfos\\\":[],\\\"refundOrderList\\\":[{\\\"refundFee\\\":1,\\\"statusViewDesc\\\":\\\"退款成功\\\",\\\"createTime\\\":0,\\\"negotiateStatus\\\":1,\\\"refundFeeSource\\\":0,\\\"refundId\\\":2310800110454559,\\\"statusView\\\":8,\\\"status\\\":60}],\\\"fromType\\\":0,\\\"relSkuId\\\":0,\\\"productRegionTypeDesc\\\":\\\"\\\",\\\"discountType\\\":0,\\\"showServiceRuleLabel\\\":false,\\\"skuDesc\\\":\\\"规格\\\",\\\"stockAcquireStatus\\\":3,\\\"productDenominationTypeDesc\\\":\\\"\\\",\\\"hasCert\\\":0,\\\"commented\\\":0,\\\"showUploadCert\\\":0,\\\"useFreight\\\":false,\\\"serviceRule\\\":\\\"{\\\\\\\"refundRule\\\\\\\":\\\\\\\"1\\\\\\\",\\\\\\\"depositRule\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"deliverGoodsIntervalTime\\\\\\\":2851200,\\\\\\\"saleFlag\\\\\\\":false,\\\\\\\"promiseDeliveryTime\\\\\\\":-1,\\\\\\\"theDayOfDeliverGoodsTime\\\\\\\":0,\\\\\\\"immediatelyOnOfflineFlag\\\\\\\":0,\\\\\\\"deliveryMethod\\\\\\\":\\\\\\\"logistics\\\\\\\",\\\\\\\"supportVerification\\\\\\\":-1,\\\\\\\"certMerchantCode\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"certExpireType\\\\\\\":0,\\\\\\\"certStartTime\\\\\\\":0,\\\\\\\"certEndTime\\\\\\\":0,\\\\\\\"certExpDays\\\\\\\":0,\\\\\\\"orderPurchaseLimitType\\\\\\\":0,\\\\\\\"minOrderCount\\\\\\\":0,\\\\\\\"maxOrderCount\\\\\\\":0,\\\\\\\"servicePromise\\\\\\\":{\\\\\\\"freshRotRefund\\\\\\\":true,\\\\\\\"brokenRefund\\\\\\\":true,\\\\\\\"allergyRefund\\\\\\\":false},\\\\\\\"unavailableTimeRule\\\\\\\":null,\\\\\\\"qualityInspection\\\\\\\":0}\\\",\\\"kwaiMoneyUserId\\\":0,\\\"activityUserId\\\":0,\\\"roleId\\\":2050303696,\\\"updateTime\\\":1681905736080,\\\"generalInfo\\\":\\\"\\\",\\\"itemTitleLabels\\\":[],\\\"kwaiMoneyUserNickName\\\":\\\"\\\",\\\"freightProviderType\\\":0,\\\"tripleForOneFake\\\":false,\\\"productRegionType\\\":0,\\\"stockReduceType\\\":1,\\\"createTime\\\":1681812121140,\\\"memberNameTypeDesc\\\":\\\"\\\",\\\"faceValue\\\":0,\\\"activityUserNickName\\\":\\\"\\\",\\\"productChargesType\\\":0,\\\"serviceRuleLabels\\\":[]}],\\\"ticketDeliveryStatus\\\":0,\\\"timeoutOfDeliveryInterval\\\":10368000000,\\\"appendPackage\\\":{\\\"enable\\\":false,\\\"disabled\\\":false,\\\"disabledTips\\\":\\\"\\\",\\\"mergeDeliverable\\\":false,\\\"sceneCode\\\":\\\"\\\",\\\"showOrderStatusList\\\":[]},\\\"logisticsState\\\":0,\\\"itemId\\\":1482258265696,\\\"orderStatusLabelList\\\":[],\\\"crossBorderTypeText\\\":\\\"\\\",\\\"customizeTimeoutOfDeliveryInterval\\\":2851200000,\\\"showLogistics\\\":false,\\\"refundStatusDesc\\\":\\\"\\\",\\\"initialTotalFee\\\":1,\\\"status\\\":6,\\\"city\\\":\\\"北京市\\\",\\\"paymentEditable\\\":false,\\\"refundStatus\\\":60,\\\"depositFee\\\":0,\\\"realUserName\\\":\\\"\\\",\\\"clearanceResultText\\\":\\\"\\\",\\\"orderViewStatusAttachDesc\\\":\\\"\\\",\\\"balancePayTime\\\":0,\\\"rechargeAccount\\\":\\\"\\\",\\\"actualPayment\\\":1,\\\"validDepositPayTimeStamp\\\":0,\\\"showConfirmArrivalButton\\\":0,\\\"flagParam\\\":{\\\"flagTagCode\\\":\\\"grey_flag_tag_order\\\",\\\"imageUrl\\\":\\\"https://w2.eckwai.com/kos/nlav12333/trade-assets/flag_grey.92c88c8ec01a2fbe.png\\\"},\\\"rechargeAccountList\\\":[],\\\"consigneePhone\\\":0,\\\"extendedReceiptType\\\":0,\\\"initialOrderPayAmount\\\":1,\\\"tradeType\\\":1,\\\"platformDelay\\\":false,\\\"rechargeAccountTypeDesc\\\":\\\"\\\",\\\"address\\\":\\\"\\\",\\\"itemNumEditable\\\":false,\\\"promiseExpiredTimeOfDelivery\\\":0,\\\"crossBorderType\\\":0,\\\"depositRefundStatus\\\":0,\\\"unBlockTimeStamp\\\":0,\\\"clearanceSubStatusCode\\\":0,\\\"systemOrderStateChangeDelay\\\":{\\\"detail\\\":[]},\\\"preSaleStatus\\\":0,\\\"buyerMessage\\\":\\\"\\\",\\\"promotions\\\":[],\\\"depositEndTime\\\":0,\\\"newBuyer\\\":0,\\\"createTime\\\":1681812121140,\\\"initialExpressFee\\\":0,\\\"refundButtonViews\\\":[{\\\"buttonDesc\\\":\\\"同意退款\\\",\\\"notShowReason\\\":\\\"\\\",\\\"show\\\":false,\\\"type\\\":4}],\\\"delayDeliveryReportStatus\\\":0,\\\"supportUpdatePayAmount\\\":0,\\\"itemImageUrl\\\":\\\"https://u2-201.ecukwai.com/ufile/adsocial/1dc759e9-7c62-4ca8-9444-11891bdcbaa1.jpg\\\",\\\"depositPayTime\\\":0,\\\"payTime\\\":1681812135223,\\\"townCode\\\":110105022,\\\"num\\\":1,\\\"deliverable\\\":false,\\\"hasRefundByDeposit\\\":0,\\\"skuPrice\\\":1,\\\"showInstantDeliveryLabel\\\":0,\\\"delayDeliveryTimeStamp\\\":0,\\\"promiseTimeoutOfDeliveryInterval\\\":0,\\\"buyerId\\\":1893200559,\\\"sellerId\\\":2050303696,\\\"orderQCStatus\\\":0,\\\"refundRule\\\":\\\"1\\\",\\\"balanceFee\\\":0,\\\"payInfoList\\\":[{\\\"color\\\":\\\"\\\",\\\"text\\\":\\\"在线支付\\\",\\\"fontWeight\\\":\\\"\\\"},{\\\"color\\\":\\\"#000000\\\",\\\"text\\\":\\\"￥0.01\\\",\\\"fontWeight\\\":\\\"500\\\"}],\\\"couponDesc\\\":\\\"\\\",\\\"crossBorderDeliverStatus\\\":0,\\\"validPromiseShipmentTimeStamp\\\":1684663335223,\\\"validShipmentTimeStamp\\\":0,\\\"platformAllowance\\\":0,\\\"town\\\":\\\"高碑店地区\\\",\\\"orderDbStatus\\\":80,\\\"distributorAllowance\\\":0,\\\"clearanceResultTextColor\\\":\\\"\\\",\\\"couponDiscount\\\":0,\\\"expireTime\\\":0,\\\"skuEditable\\\":false,\\\"district\\\":\\\"朝阳区\\\",\\\"idCardNumber\\\":\\\"\\\",\\\"mergeDeliverable\\\":false,\\\"taxAmount\\\":0,\\\"orderViewStatusDesc\\\":\\\"\\\",\\\"shopPreferentialAllowance\\\":0,\\\"districtCode\\\":110105,\\\"postSaleType\\\":1,\\\"expressNo\\\":\\\"\\\",\\\"itemTitle\\\":\\\"测试商品勿拍不发货自建鲜花\\\",\\\"cityCode\\\":1101,\\\"csButtons\\\":[{\\\"code\\\":\\\"sendRemittance\\\",\\\"text\\\":\\\"小额打款\\\"}],\\\"oid\\\":2310800100800559,\\\"consigneeName\\\":\\\"\\\",\\\"industryType\\\":\\\"\\\",\\\"showExtendReceipt\\\":0,\\\"industrySubStatus\\\":\\\"\\\",\\\"orderNoteList\\\":[],\\\"skuDesc\\\":\\\"规格\\\",\\\"needQualityControl\\\":false,\\\"existsMerge\\\":false,\\\"delayTypeDesc\\\":\\\"\\\",\\\"orderSource\\\":0,\\\"showSellerOvertimeRefund\\\":false,\\\"changeReason\\\":121,\\\"csSkuEditable\\\":false,\\\"flagTagCode\\\":\\\"grey_flag_tag_order\\\",\\\"provinceCode\\\":11,\\\"extendedReceiptTime\\\":0,\\\"orderButtonList\\\":[],\\\"preSaleOrder\\\":false,\\\"finalValidPromiseShipmentTimeStamp\\\":1684663335223,\\\"coType\\\":0,\\\"auctionCommissionFee\\\":0,\\\"crossBorderClearanceReason\\\":\\\"\\\",\\\"blockDeliveryStatus\\\":0,\\\"activityType\\\":1,\\\"consigneePhoneStr\\\":\\\"\\\",\\\"rechargeAccountType\\\":0},{\\\"logisticsId\\\":0,\\\"buyerNick\\\":\\\"安静1486683337\\\",\\\"sellerCoverTax\\\":false,\\\"remindShipmentSign\\\":0,\\\"bargain\\\":false,\\\"expressFee\\\":0,\\\"closeable\\\":false,\\\"showAuctionCommissionFee\\\":false,\\\"payType\\\":1,\\\"validBalancePayTimeStamp\\\":0,\\\"province\\\":\\\"北京市\\\",\\\"expressCode\\\":0,\\\"addressEditable\\\":false,\\\"orderProductList\\\":[{\\\"itemPicUrl\\\":\\\"https://u2-201.ecukwai.com/ufile/adsocial/1dc759e9-7c62-4ca8-9444-11891bdcbaa1.jpg\\\",\\\"auto\\\":0,\\\"discountFee\\\":0,\\\"insuranceRefuseReason\\\":\\\"\\\",\\\"num\\\":1,\\\"qualityReportFlag\\\":false,\\\"buyerId\\\":1486683337,\\\"platformFee\\\":0,\\\"productChargesTypeDesc\\\":\\\"\\\",\\\"tid\\\":2307300049226337,\\\"relItemId\\\":1482258265696,\\\"sellerId\\\":2050303696,\\\"price\\\":1,\\\"id\\\":66539142488337,\\\"skuId\\\":1482258268696,\\\"cpsType\\\":1,\\\"distributorId\\\":0,\\\"itemId\\\":1482258265696,\\\"productDenominationType\\\":0,\\\"roleDesc\\\":\\\"自营\\\",\\\"sourceType\\\":99,\\\"attached\\\":0,\\\"itemLinkUrl\\\":\\\"https://app.kwaixiaodian.com/merchant/shop/detail?id=1482258265696&hyId=kwaishop&layoutType=4\\\",\\\"skuNick\\\":\\\"\\\",\\\"roleName\\\":\\\"董淑华（商家）\\\",\\\"skuTotalFee\\\":1,\\\"firstOrderGuaranteeFlag\\\":false,\\\"shelfItemType\\\":0,\\\"estimatedIncome\\\":0,\\\"platformFeeRatio\\\":0,\\\"itemCdnPicUrl\\\":\\\"https://tx-ec.static.yximgs.com/ufile/adsocial/1dc759e9-7c62-4ca8-9444-11891bdcbaa1.jpg\\\",\\\"freightProviderExplainDesc\\\":\\\"\\\",\\\"commissionRate\\\":0,\\\"agentType\\\":\\\"\\\",\\\"originalPrice\\\":1,\\\"itemTitle\\\":\\\"测试商品勿拍不发货自建鲜花\\\",\\\"replied\\\":0,\\\"showInsuranceRefuseTag\\\":false,\\\"refundList\\\":[{\\\"refundFee\\\":1,\\\"statusViewDesc\\\":\\\"\\\",\\\"createTime\\\":1678779587918,\\\"negotiateStatus\\\":1,\\\"refundFeeSource\\\":1,\\\"refundId\\\":2307300049543337,\\\"statusView\\\":0,\\\"status\\\":60}],\\\"cpsDesc\\\":\\\"自营\\\",\\\"oid\\\":2307300049226337,\\\"roleType\\\":1,\\\"memberNameType\\\":0,\\\"distributorName\\\":\\\"\\\",\\\"industryShowInfos\\\":[],\\\"refundOrderList\\\":[{\\\"refundFee\\\":1,\\\"statusViewDesc\\\":\\\"退款成功\\\",\\\"createTime\\\":0,\\\"negotiateStatus\\\":1,\\\"refundFeeSource\\\":0,\\\"refundId\\\":2307300049543337,\\\"statusView\\\":8,\\\"status\\\":60}],\\\"fromType\\\":0,\\\"relSkuId\\\":0,\\\"productRegionTypeDesc\\\":\\\"\\\",\\\"discountType\\\":0,\\\"showServiceRuleLabel\\\":false,\\\"skuDesc\\\":\\\"规格\\\",\\\"stockAcquireStatus\\\":3,\\\"productDenominationTypeDesc\\\":\\\"\\\",\\\"hasCert\\\":0,\\\"commented\\\":0,\\\"showUploadCert\\\":0,\\\"useFreight\\\":false,\\\"serviceRule\\\":\\\"{\\\\\\\"refundRule\\\\\\\":\\\\\\\"1\\\\\\\",\\\\\\\"depositRule\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"deliverGoodsIntervalTime\\\\\\\":2851200,\\\\\\\"saleFlag\\\\\\\":false,\\\\\\\"promiseDeliveryTime\\\\\\\":-1,\\\\\\\"theDayOfDeliverGoodsTime\\\\\\\":0,\\\\\\\"immediatelyOnOfflineFlag\\\\\\\":0,\\\\\\\"deliveryMethod\\\\\\\":\\\\\\\"logistics\\\\\\\",\\\\\\\"supportVerification\\\\\\\":-1,\\\\\\\"certMerchantCode\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"certExpireType\\\\\\\":0,\\\\\\\"certStartTime\\\\\\\":0,\\\\\\\"certEndTime\\\\\\\":0,\\\\\\\"certExpDays\\\\\\\":0,\\\\\\\"orderPurchaseLimitType\\\\\\\":0,\\\\\\\"minOrderCount\\\\\\\":0,\\\\\\\"maxOrderCount\\\\\\\":0,\\\\\\\"servicePromise\\\\\\\":{\\\\\\\"freshRotRefund\\\\\\\":true,\\\\\\\"brokenRefund\\\\\\\":true,\\\\\\\"allergyRefund\\\\\\\":false},\\\\\\\"unavailableTimeRule\\\\\\\":null,\\\\\\\"qualityInspection\\\\\\\":0}\\\",\\\"kwaiMoneyUserId\\\":0,\\\"activityUserId\\\":0,\\\"roleId\\\":2050303696,\\\"updateTime\\\":1678866003562,\\\"generalInfo\\\":\\\"\\\",\\\"itemTitleLabels\\\":[],\\\"kwaiMoneyUserNickName\\\":\\\"\\\",\\\"freightProviderType\\\":0,\\\"tripleForOneFake\\\":false,\\\"productRegionType\\\":0,\\\"stockReduceType\\\":1,\\\"createTime\\\":1678779489338,\\\"memberNameTypeDesc\\\":\\\"\\\",\\\"faceValue\\\":0,\\\"activityUserNickName\\\":\\\"\\\",\\\"productChargesType\\\":0,\\\"serviceRuleLabels\\\":[]}],\\\"ticketDeliveryStatus\\\":0,\\\"timeoutOfDeliveryInterval\\\":10368000000,\\\"appendPackage\\\":{\\\"enable\\\":false,\\\"disabled\\\":false,\\\"disabledTips\\\":\\\"\\\",\\\"mergeDeliverable\\\":false,\\\"sceneCode\\\":\\\"\\\",\\\"showOrderStatusList\\\":[]},\\\"logisticsState\\\":0,\\\"itemId\\\":1482258265696,\\\"orderStatusLabelList\\\":[],\\\"crossBorderTypeText\\\":\\\"\\\",\\\"customizeTimeoutOfDeliveryInterval\\\":2851200000,\\\"showLogistics\\\":false,\\\"refundStatusDesc\\\":\\\"\\\",\\\"initialTotalFee\\\":1,\\\"status\\\":6,\\\"city\\\":\\\"北京市\\\",\\\"paymentEditable\\\":false,\\\"refundStatus\\\":60,\\\"depositFee\\\":0,\\\"realUserName\\\":\\\"\\\",\\\"clearanceResultText\\\":\\\"\\\",\\\"orderViewStatusAttachDesc\\\":\\\"\\\",\\\"balancePayTime\\\":0,\\\"rechargeAccount\\\":\\\"\\\",\\\"actualPayment\\\":1,\\\"validDepositPayTimeStamp\\\":0,\\\"showConfirmArrivalButton\\\":0,\\\"flagParam\\\":{\\\"flagTagCode\\\":\\\"grey_flag_tag_order\\\",\\\"imageUrl\\\":\\\"https://w2.eckwai.com/kos/nlav12333/trade-assets/flag_grey.92c88c8ec01a2fbe.png\\\"},\\\"rechargeAccountList\\\":[],\\\"consigneePhone\\\":0,\\\"extendedReceiptType\\\":0,\\\"initialOrderPayAmount\\\":1,\\\"tradeType\\\":1,\\\"platformDelay\\\":false,\\\"rechargeAccountTypeDesc\\\":\\\"\\\",\\\"address\\\":\\\"\\\",\\\"itemNumEditable\\\":false,\\\"promiseExpiredTimeOfDelivery\\\":0,\\\"crossBorderType\\\":0,\\\"depositRefundStatus\\\":0,\\\"unBlockTimeStamp\\\":0,\\\"clearanceSubStatusCode\\\":0,\\\"systemOrderStateChangeDelay\\\":{\\\"detail\\\":[]},\\\"preSaleStatus\\\":0,\\\"buyerMessage\\\":\\\"\\\",\\\"promotions\\\":[],\\\"depositEndTime\\\":0,\\\"newBuyer\\\":0,\\\"createTime\\\":1678779489338,\\\"initialExpressFee\\\":0,\\\"refundButtonViews\\\":[{\\\"buttonDesc\\\":\\\"同意退款\\\",\\\"notShowReason\\\":\\\"\\\",\\\"show\\\":false,\\\"type\\\":4}],\\\"delayDeliveryReportStatus\\\":0,\\\"supportUpdatePayAmount\\\":0,\\\"itemImageUrl\\\":\\\"https://u2-201.ecukwai.com/ufile/adsocial/1dc759e9-7c62-4ca8-9444-11891bdcbaa1.jpg\\\",\\\"depositPayTime\\\":0,\\\"payTime\\\":1678779499699,\\\"townCode\\\":110108022,\\\"num\\\":1,\\\"deliverable\\\":false,\\\"hasRefundByDeposit\\\":0,\\\"skuPrice\\\":1,\\\"showInstantDeliveryLabel\\\":0,\\\"delayDeliveryTimeStamp\\\":0,\\\"promiseTimeoutOfDeliveryInterval\\\":0,\\\"buyerId\\\":1486683337,\\\"sellerId\\\":2050303696,\\\"orderQCStatus\\\":0,\\\"refundRule\\\":\\\"1\\\",\\\"balanceFee\\\":0,\\\"payInfoList\\\":[{\\\"color\\\":\\\"\\\",\\\"text\\\":\\\"在线支付\\\",\\\"fontWeight\\\":\\\"\\\"},{\\\"color\\\":\\\"#000000\\\",\\\"text\\\":\\\"￥0.01\\\",\\\"fontWeight\\\":\\\"500\\\"}],\\\"couponDesc\\\":\\\"\\\",\\\"crossBorderDeliverStatus\\\":0,\\\"validPromiseShipmentTimeStamp\\\":1681630699699,\\\"validShipmentTimeStamp\\\":0,\\\"platformAllowance\\\":0,\\\"town\\\":\\\"上地街道\\\",\\\"orderDbStatus\\\":80,\\\"distributorAllowance\\\":0,\\\"clearanceResultTextColor\\\":\\\"\\\",\\\"couponDiscount\\\":0,\\\"expireTime\\\":0,\\\"skuEditable\\\":false,\\\"district\\\":\\\"海淀区\\\",\\\"idCardNumber\\\":\\\"\\\",\\\"mergeDeliverable\\\":false,\\\"taxAmount\\\":0,\\\"orderViewStatusDesc\\\":\\\"\\\",\\\"shopPreferentialAllowance\\\":0,\\\"districtCode\\\":110108,\\\"postSaleType\\\":1,\\\"expressNo\\\":\\\"\\\",\\\"itemTitle\\\":\\\"测试商品勿拍不发货自建鲜花\\\",\\\"cityCode\\\":1101,\\\"csButtons\\\":[],\\\"oid\\\":2307300049226337,\\\"consigneeName\\\":\\\"\\\",\\\"industryType\\\":\\\"\\\",\\\"showExtendReceipt\\\":0,\\\"industrySubStatus\\\":\\\"\\\",\\\"orderNoteList\\\":[],\\\"skuDesc\\\":\\\"规格\\\",\\\"needQualityControl\\\":false,\\\"existsMerge\\\":false,\\\"delayTypeDesc\\\":\\\"\\\",\\\"orderSource\\\":0,\\\"showSellerOvertimeRefund\\\":false,\\\"changeReason\\\":121,\\\"csSkuEditable\\\":false,\\\"flagTagCode\\\":\\\"grey_flag_tag_order\\\",\\\"provinceCode\\\":11,\\\"extendedReceiptTime\\\":0,\\\"orderButtonList\\\":[],\\\"preSaleOrder\\\":false,\\\"finalValidPromiseShipmentTimeStamp\\\":1681630699699,\\\"coType\\\":0,\\\"auctionCommissionFee\\\":0,\\\"crossBorderClearanceReason\\\":\\\"\\\",\\\"blockDeliveryStatus\\\":0,\\\"activityType\\\":1,\\\"consigneePhoneStr\\\":\\\"\\\",\\\"rechargeAccountType\\\":0}],\\\"serverTime\\\":1688397542754}\",\"responseJsonTree\":\"[\\\"hint\\\", \\\"size\\\", \\\"total\\\", \\\"result\\\", \\\"error_msg\\\", \\\"requestId\\\", \\\"serverTime\\\", \\\"orderList[0].num\\\", \\\"orderList[0].oid\\\", \\\"orderList[0].city\\\", \\\"orderList[0].town\\\", \\\"orderList[0].coType\\\", \\\"orderList[0].itemId\\\", \\\"orderList[0].status\\\", \\\"orderList[0].address\\\", \\\"orderList[0].bargain\\\", \\\"orderList[0].buyerId\\\", \\\"orderList[0].payTime\\\", \\\"orderList[0].payType\\\", \\\"orderList[0].skuDesc\\\", \\\"orderList[0].cityCode\\\", \\\"orderList[0].district\\\", \\\"orderList[0].newBuyer\\\", \\\"orderList[0].province\\\", \\\"orderList[0].sellerId\\\", \\\"orderList[0].skuPrice\\\", \\\"orderList[0].townCode\\\", \\\"orderList[0].buyerNick\\\", \\\"orderList[0].closeable\\\", \\\"orderList[0].expressNo\\\", \\\"orderList[0].itemTitle\\\", \\\"orderList[0].taxAmount\\\", \\\"orderList[0].tradeType\\\", \\\"orderList[0].balanceFee\\\", \\\"orderList[0].couponDesc\\\", \\\"orderList[0].createTime\\\", \\\"orderList[0].depositFee\\\", \\\"orderList[0].expireTime\\\", \\\"orderList[0].expressFee\\\", \\\"orderList[0].refundRule\\\", \\\"orderList[0].deliverable\\\", \\\"orderList[0].existsMerge\\\", \\\"orderList[0].expressCode\\\", \\\"orderList[0].flagTagCode\\\", \\\"orderList[0].logisticsId\\\", \\\"orderList[0].orderSource\\\", \\\"orderList[0].skuEditable\\\", \\\"orderList[0].activityType\\\", \\\"orderList[0].buyerMessage\\\", \\\"orderList[0].changeReason\\\", \\\"orderList[0].districtCode\\\", \\\"orderList[0].idCardNumber\\\", \\\"orderList[0].industryType\\\", \\\"orderList[0].itemImageUrl\\\", \\\"orderList[0].postSaleType\\\", \\\"orderList[0].preSaleOrder\\\", \\\"orderList[0].provinceCode\\\", \\\"orderList[0].realUserName\\\", \\\"orderList[0].refundStatus\\\", \\\"orderList[0].actualPayment\\\", \\\"orderList[0].consigneeName\\\", \\\"orderList[0].csSkuEditable\\\", \\\"orderList[0].delayTypeDesc\\\", \\\"orderList[0].orderDbStatus\\\", \\\"orderList[0].orderQCStatus\\\", \\\"orderList[0].platformDelay\\\", \\\"orderList[0].preSaleStatus\\\", \\\"orderList[0].showLogistics\\\", \\\"orderList[0].balancePayTime\\\", \\\"orderList[0].consigneePhone\\\", \\\"orderList[0].couponDiscount\\\", \\\"orderList[0].depositEndTime\\\", \\\"orderList[0].depositPayTime\\\", \\\"orderList[0].logisticsState\\\", \\\"orderList[0].sellerCoverTax\\\", \\\"orderList[0].addressEditable\\\", \\\"orderList[0].crossBorderType\\\", \\\"orderList[0].initialTotalFee\\\", \\\"orderList[0].itemNumEditable\\\", \\\"orderList[0].paymentEditable\\\", \\\"orderList[0].rechargeAccount\\\", \\\"orderList[0].mergeDeliverable\\\", \\\"orderList[0].refundStatusDesc\\\", \\\"orderList[0].unBlockTimeStamp\\\", \\\"orderList[0].consigneePhoneStr\\\", \\\"orderList[0].csButtons[0].code\\\", \\\"orderList[0].csButtons[0].text\\\", \\\"orderList[0].industrySubStatus\\\", \\\"orderList[0].initialExpressFee\\\", \\\"orderList[0].platformAllowance\\\", \\\"orderList[0].showExtendReceipt\\\", \\\"orderList[0].flagParam.imageUrl\\\", \\\"orderList[0].hasRefundByDeposit\\\", \\\"orderList[0].needQualityControl\\\", \\\"orderList[0].remindShipmentSign\\\", \\\"orderList[0].blockDeliveryStatus\\\", \\\"orderList[0].clearanceResultText\\\", \\\"orderList[0].crossBorderTypeText\\\", \\\"orderList[0].depositRefundStatus\\\", \\\"orderList[0].extendedReceiptTime\\\", \\\"orderList[0].extendedReceiptType\\\", \\\"orderList[0].orderViewStatusDesc\\\", \\\"orderList[0].payInfoList[0].text\\\", \\\"orderList[0].rechargeAccountType\\\", \\\"orderList[0].appendPackage.enable\\\", \\\"orderList[0].auctionCommissionFee\\\", \\\"orderList[0].distributorAllowance\\\", \\\"orderList[0].payInfoList[0].color\\\", \\\"orderList[0].ticketDeliveryStatus\\\", \\\"orderList[0].flagParam.flagTagCode\\\", \\\"orderList[0].initialOrderPayAmount\\\", \\\"orderList[0].appendPackage.disabled\\\", \\\"orderList[0].clearanceSubStatusCode\\\", \\\"orderList[0].delayDeliveryTimeStamp\\\", \\\"orderList[0].orderProductList[0].id\\\", \\\"orderList[0].supportUpdatePayAmount\\\", \\\"orderList[0].validShipmentTimeStamp\\\", \\\"orderList[0].appendPackage.sceneCode\\\", \\\"orderList[0].orderProductList[0].num\\\", \\\"orderList[0].orderProductList[0].oid\\\", \\\"orderList[0].orderProductList[0].tid\\\", \\\"orderList[0].rechargeAccountTypeDesc\\\", \\\"orderList[0].clearanceResultTextColor\\\", \\\"orderList[0].crossBorderDeliverStatus\\\", \\\"orderList[0].orderProductList[0].auto\\\", \\\"orderList[0].showAuctionCommissionFee\\\", \\\"orderList[0].showConfirmArrivalButton\\\", \\\"orderList[0].showInstantDeliveryLabel\\\", \\\"orderList[0].showSellerOvertimeRefund\\\", \\\"orderList[0].validBalancePayTimeStamp\\\", \\\"orderList[0].validDepositPayTimeStamp\\\", \\\"orderList[0].delayDeliveryReportStatus\\\", \\\"orderList[0].orderProductList[0].price\\\", \\\"orderList[0].orderProductList[0].skuId\\\", \\\"orderList[0].orderViewStatusAttachDesc\\\", \\\"orderList[0].payInfoList[0].fontWeight\\\", \\\"orderList[0].refundButtonViews[0].show\\\", \\\"orderList[0].refundButtonViews[0].type\\\", \\\"orderList[0].shopPreferentialAllowance\\\", \\\"orderList[0].timeoutOfDeliveryInterval\\\", \\\"orderList[0].appendPackage.disabledTips\\\", \\\"orderList[0].crossBorderClearanceReason\\\", \\\"orderList[0].orderProductList[0].itemId\\\", \\\"orderList[0].orderProductList[0].roleId\\\", \\\"orderList[0].orderProductList[0].buyerId\\\", \\\"orderList[0].orderProductList[0].cpsDesc\\\", \\\"orderList[0].orderProductList[0].cpsType\\\", \\\"orderList[0].orderProductList[0].hasCert\\\", \\\"orderList[0].orderProductList[0].replied\\\", \\\"orderList[0].orderProductList[0].skuDesc\\\", \\\"orderList[0].orderProductList[0].skuNick\\\", \\\"orderList[0].orderProductList[0].attached\\\", \\\"orderList[0].orderProductList[0].fromType\\\", \\\"orderList[0].orderProductList[0].relSkuId\\\", \\\"orderList[0].orderProductList[0].roleDesc\\\", \\\"orderList[0].orderProductList[0].roleName\\\", \\\"orderList[0].orderProductList[0].roleType\\\", \\\"orderList[0].orderProductList[0].sellerId\\\", \\\"orderList[0].promiseExpiredTimeOfDelivery\\\", \\\"orderList[0].orderProductList[0].agentType\\\", \\\"orderList[0].orderProductList[0].commented\\\", \\\"orderList[0].orderProductList[0].faceValue\\\", \\\"orderList[0].orderProductList[0].itemTitle\\\", \\\"orderList[0].orderProductList[0].relItemId\\\", \\\"orderList[0].validPromiseShipmentTimeStamp\\\", \\\"orderList[0].appendPackage.mergeDeliverable\\\", \\\"orderList[0].orderProductList[0].createTime\\\", \\\"orderList[0].orderProductList[0].itemPicUrl\\\", \\\"orderList[0].orderProductList[0].sourceType\\\", \\\"orderList[0].orderProductList[0].updateTime\\\", \\\"orderList[0].orderProductList[0].useFreight\\\", \\\"orderList[0].orderProductList[0].discountFee\\\", \\\"orderList[0].orderProductList[0].generalInfo\\\", \\\"orderList[0].orderProductList[0].itemLinkUrl\\\", \\\"orderList[0].orderProductList[0].platformFee\\\", \\\"orderList[0].orderProductList[0].serviceRule\\\", \\\"orderList[0].orderProductList[0].skuTotalFee\\\", \\\"orderList[0].refundButtonViews[0].buttonDesc\\\", \\\"orderList[0].orderProductList[0].discountType\\\", \\\"orderList[0].promiseTimeoutOfDeliveryInterval\\\", \\\"orderList[0].orderProductList[0].distributorId\\\", \\\"orderList[0].orderProductList[0].itemCdnPicUrl\\\", \\\"orderList[0].orderProductList[0].originalPrice\\\", \\\"orderList[0].orderProductList[0].shelfItemType\\\", \\\"orderList[0].customizeTimeoutOfDeliveryInterval\\\", \\\"orderList[0].finalValidPromiseShipmentTimeStamp\\\", \\\"orderList[0].orderProductList[0].activityUserId\\\", \\\"orderList[0].orderProductList[0].commissionRate\\\", \\\"orderList[0].orderProductList[0].memberNameType\\\", \\\"orderList[0].orderProductList[0].showUploadCert\\\", \\\"orderList[0].refundButtonViews[0].notShowReason\\\", \\\"orderList[0].orderProductList[0].distributorName\\\", \\\"orderList[0].orderProductList[0].estimatedIncome\\\", \\\"orderList[0].orderProductList[0].kwaiMoneyUserId\\\", \\\"orderList[0].orderProductList[0].stockReduceType\\\", \\\"orderList[0].orderProductList[0].platformFeeRatio\\\", \\\"orderList[0].orderProductList[0].tripleForOneFake\\\", \\\"orderList[0].orderProductList[0].productRegionType\\\", \\\"orderList[0].orderProductList[0].qualityReportFlag\\\", \\\"orderList[0].orderProductList[0].memberNameTypeDesc\\\", \\\"orderList[0].orderProductList[0].productChargesType\\\", \\\"orderList[0].orderProductList[0].stockAcquireStatus\\\", \\\"orderList[0].orderProductList[0].freightProviderType\\\", \\\"orderList[0].orderProductList[0].activityUserNickName\\\", \\\"orderList[0].orderProductList[0].refundList[0].status\\\", \\\"orderList[0].orderProductList[0].showServiceRuleLabel\\\", \\\"orderList[0].orderProductList[0].insuranceRefuseReason\\\", \\\"orderList[0].orderProductList[0].kwaiMoneyUserNickName\\\", \\\"orderList[0].orderProductList[0].productRegionTypeDesc\\\", \\\"orderList[0].orderProductList[0].productChargesTypeDesc\\\", \\\"orderList[0].orderProductList[0].refundList[0].refundId\\\", \\\"orderList[0].orderProductList[0].showInsuranceRefuseTag\\\", \\\"orderList[0].orderProductList[0].firstOrderGuaranteeFlag\\\", \\\"orderList[0].orderProductList[0].productDenominationType\\\", \\\"orderList[0].orderProductList[0].refundList[0].refundFee\\\", \\\"orderList[0].orderProductList[0].refundList[0].createTime\\\", \\\"orderList[0].orderProductList[0].refundList[0].statusView\\\", \\\"orderList[0].orderProductList[0].refundOrderList[0].status\\\", \\\"orderList[0].orderProductList[0].freightProviderExplainDesc\\\", \\\"orderList[0].orderProductList[0].productDenominationTypeDesc\\\", \\\"orderList[0].orderProductList[0].refundOrderList[0].refundId\\\", \\\"orderList[0].orderProductList[0].refundList[0].statusViewDesc\\\", \\\"orderList[0].orderProductList[0].refundOrderList[0].refundFee\\\", \\\"orderList[0].orderProductList[0].refundList[0].negotiateStatus\\\", \\\"orderList[0].orderProductList[0].refundList[0].refundFeeSource\\\", \\\"orderList[0].orderProductList[0].refundOrderList[0].createTime\\\", \\\"orderList[0].orderProductList[0].refundOrderList[0].statusView\\\", \\\"orderList[0].orderProductList[0].refundOrderList[0].statusViewDesc\\\", \\\"orderList[0].orderProductList[0].refundOrderList[0].negotiateStatus\\\", \\\"orderList[0].orderProductList[0].refundOrderList[0].refundFeeSource\\\"]\",\"responseJsonTreeHash\":\"2116F2760A904773E17CE2AA049B5C37\",\"status\":1,\"dataSource\":1,\"version\":2,\"apiHash\":\"109B1D9AD957A2CDB438FA51F10AB1B1\",\"createTime\":1688397543}";
        FlowOriginDTO flowOriginDTO = ObjectMapperUtils.fromJSON(msg, FlowOriginDTO.class);
        flowCollectionMapper.insertData(flowOriginDTO);
    }

    @Test
    public void selectMaxVersionByApiInfoTest() {
        Integer version = flowCollectionMapper.selectMaxVersionByApiInfo("kevinsa-com",
                "kevinsa.com", "/aaa/aaa", 1);
        System.out.println(version);
    }
}
