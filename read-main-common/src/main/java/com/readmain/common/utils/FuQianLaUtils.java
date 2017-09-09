//package com.readmain.common.utils;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.creditease.ns.adapter.channel.impl.*;
//import com.creditease.ns.adapter.channel.impl.v1_04.*;
//import com.saf.panpan.api.entity.FuQianLaPayQueryResponseEntity;
//import com.saf.panpan.api.entity.FuQianLaRefundResponseEntity;
//import com.saf.panpan.common.utils.FQLUtil.RSAUtil;
//import com.saf.panpan.common.utils.FQLUtil.SHA1;
//import com.saf.panpan.common.utils.HttpSendUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.servlet.http.HttpServletRequest;
//import java.io.*;
//import java.security.PublicKey;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@Component
//@Slf4j
//public class FuQianLaUtils {
//
//    @Value("${secretKey}")
//    private String secretKey;
//
//    @Value("${channelId}")
//    private String channelId;
//
//    @Value("${merchId}")
//    private String merchId;
//
//    @Value("${app_id}")
//    private String appId;
//
//    @Value("${app_key}")
//    private String appKey;
//
//    @Value("${order_query_url}")
//    private String orderPayQueryUrl;
//
//    @Value("${refund_url}")
//    private String refundUrl;
//
//    @Value("${refund_query_url}")
//    private String refundQueryUrl;
//
//    @Resource
//    private CeVerifyWSProxy verifyWSProxy;
//
//    @Resource
//    private CeFinalPaymentV1_04WSProxy paymentV104WSProxy;
//
//    private static final String NOTIFY_MD5_KEY = "8BB418FCA8A480BC3E00365AE14148A2";
//
//
//    private static final String FUQIANLA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC8YcqjoN9TQ75LwxC0cO1ywbnqRXfvtGTsC9EFh0jtBDNp/jsOvWG0nNB9z3fY9ilRU7rs/AyQt+PTuDB+ZiF+Emq+UteNw5C1RPJpj20Cpkvyf/80C6NSZrWMZjvdo1zPKo0KRz0RvCnfBry6LZZ2hEgXr2n3nDld3liMoZIDhQIDAQAB";
//
//    /**
//     * 身份证二要素鉴权
//     *
//     * @param bizId
//     * @param name
//     * @param idCard
//     * @return
//     * @throws Exception
//     */
//    public CeCertAndAccountVerifyResponse idCardCert(String bizId, String name, String idCard) throws Exception {
//        CeCertAndAccountVerifyRequest verifyRequest = new CeCertAndAccountVerifyRequest();
//        verifyRequest.setVersionNo("1.01");
//        verifyRequest.setAcqId("01"); // WEB
//        verifyRequest.setChannelId(channelId);
//        verifyRequest.setMerchId(merchId);
//        verifyRequest.setBizId(bizId);
//        verifyRequest.setPaymentTp("41"); // 支付类型
//        verifyRequest.setSendTime(new SimpleDateFormat("yyyyMMddHHMMss").format(new Date()));
//        verifyRequest.setAccountName(name); // 姓名
//        verifyRequest.setIdentType("111"); // 证件类型
//        verifyRequest.setIdentNo(idCard); // 身份证号
//        verifyRequest.setSignInfo(generateVerifySignInfo(verifyRequest, "idCard"));
//        return verifyWSProxy.singleCertAndAccountVerify(verifyRequest);
//    }
//
//    /**
//     * 四要素鉴权
//     *
//     * @param bizId
//     * @param name
//     * @param idCard
//     * @param mobile
//     * @param bankCode
//     * @param bankCard
//     * @return
//     * @throws Exception
//     */
//    public CeCertAndAccountVerifyResponse bankCardCert(String bizId, String name, String idCard, String mobile, String bankCode, String bankCard) throws Exception {
//        CeCertAndAccountVerifyRequest verifyRequest = new CeCertAndAccountVerifyRequest();
//        verifyRequest.setVersionNo("1.04");
//        verifyRequest.setAcqId("01");
//        verifyRequest.setChannelId(channelId);
//        verifyRequest.setMerchId(merchId);
//        verifyRequest.setBizId(bizId);
//        verifyRequest.setPaymentTp("43");
//        verifyRequest.setAccountFlag("0");
//        verifyRequest.setBankId(bankCode);
//        verifyRequest.setSendTime(new SimpleDateFormat("yyyyMMddHHMMss").format(new Date()));
//        verifyRequest.setAccountName(name);
//        verifyRequest.setAccountNo(bankCard);
//        verifyRequest.setIdentType("111");
//        verifyRequest.setIdentNo(idCard);
//        verifyRequest.setMobileNum(mobile);
//        verifyRequest.setSignInfo(generateVerifySignInfo(verifyRequest, "bankCard"));
//        return verifyWSProxy.singleCertAndAccountVerify(verifyRequest);
//    }
//
//    /**
//     * 鉴权signInfo生成
//     *
//     * @param verifyRequest 鉴权信息
//     * @param verifyType    鉴权类型
//     * @return
//     * @throws Exception
//     */
//    private String generateVerifySignInfo(CeCertAndAccountVerifyRequest verifyRequest, String verifyType) throws Exception {
//        StringBuilder checkSign = new StringBuilder();
//        if (StringUtils.equals(verifyType, "idCard")) {
//            checkSign.append("accountName=").append(verifyRequest.getAccountName()).append("&")
//                    .append("acqId=").append(verifyRequest.getAcqId()).append("&")
//                    .append("bizId=").append(verifyRequest.getBizId()).append("&")
//                    .append("channelId=").append(verifyRequest.getChannelId()).append("&")
//                    .append("identNo=").append(verifyRequest.getIdentNo()).append("&")
//                    .append("identType=").append(verifyRequest.getIdentType()).append("&")
//                    .append("merchId=").append(verifyRequest.getMerchId()).append("&")
//                    .append("paymentTp=").append(verifyRequest.getPaymentTp()).append("&")
//                    .append("sendTime=").append(verifyRequest.getSendTime()).append("&")
//                    .append("versionNo=").append(verifyRequest.getVersionNo()).append("&");
//        } else if (StringUtils.equals(verifyType, "bankCard")) {
//            checkSign.append("accountFlag=").append(verifyRequest.getAccountFlag()).append("&")
//                    .append("accountName=").append(verifyRequest.getAccountName()).append("&")
//                    .append("accountNo=").append(verifyRequest.getAccountNo()).append("&")
//                    .append("acqId=").append(verifyRequest.getAcqId()).append("&")
//                    .append("bankId=").append(verifyRequest.getBankId()).append("&")
//                    .append("bizId=").append(verifyRequest.getBizId()).append("&")
//                    .append("channelId=").append(verifyRequest.getChannelId()).append("&")
//                    .append("identNo=").append(verifyRequest.getIdentNo()).append("&")
//                    .append("identType=").append(verifyRequest.getIdentType()).append("&")
//                    .append("merchId=").append(verifyRequest.getMerchId()).append("&")
//                    .append("mobileNum=").append(verifyRequest.getMobileNum()).append("&")
//                    .append("paymentTp=").append(verifyRequest.getPaymentTp()).append("&")
//                    .append("sendTime=").append(verifyRequest.getSendTime()).append("&")
//                    .append("versionNo=").append(verifyRequest.getVersionNo()).append("&");
//        }
//        log.info("签名串:{}", checkSign.toString() + secretKey);
//        log.info("MD5:{}", DigestUtils.md5Hex(checkSign.toString() + secretKey));
//        return DigestUtils.md5Hex(checkSign.append(secretKey).toString());
//    }
//
//    /**
//     * 根据bizId生产查询二要素鉴权request
//     *
//     * @param bizId
//     * @return
//     */
//    public CeVerifyQueryResponse queryVerifyResult(String bizId) throws Exception {
//        CeVerifyQueryRequest verifyRequest = new CeVerifyQueryRequest();
//        verifyRequest.setBizId(bizId);
//        verifyRequest.setChannelId(channelId);
//        verifyRequest.setMerchId(merchId);
//        verifyRequest.setSendTime(new SimpleDateFormat("yyyyMMddHHMMss").format(new Date()));
//        verifyRequest.setVersionNo("1.01");
//        verifyRequest.setSignInfo(generateVerifyQurtySignInfo(verifyRequest));
//        return verifyWSProxy.singleCertAndAccountVerifyQuery(verifyRequest);
//    }
//
//    /**
//     * 鉴权查询signInfo生成
//     *
//     * @param verifyQueryRequest 鉴权查询对象
//     * @return
//     */
//    private String generateVerifyQurtySignInfo(CeVerifyQueryRequest verifyQueryRequest) throws Exception{
//        StringBuilder checkSign = new StringBuilder();
//        checkSign.append("bizId=").append(verifyQueryRequest.getBizId()).append("&")
//                .append("channelId=").append(verifyQueryRequest.getChannelId()).append("&")
//                .append("merchId=").append(verifyQueryRequest.getMerchId()).append("&")
//                .append("sendTime=").append(verifyQueryRequest.getSendTime()).append("&")
//                .append("versionNo=").append(verifyQueryRequest.getVersionNo()).append("&");
//        return DigestUtils.md5Hex(checkSign.append(secretKey).toString());
//    }
//
//    /**
//     * 代付
//     *
//     * @param bizId
//     * @param bizTp
//     * @param isPublic
//     * @param bankId
//     * @param bankCard
//     * @param amount
//     * @param idCardNo
//     * @param accountName
//     * @param province
//     * @param city
//     * @param mobile
//     * @param remark
//     * @return
//     * @throws Exception
//     */
//    public SinglePaymentResponse payment(String bizId, String bizTp, Boolean isPublic,
//                                         String bankId, String bankCard, Double amount, String idCardNo, String accountName,
//                                         String province, String city, String mobile, String remark) throws Exception {
//        SinglePaymentRequest request = new SinglePaymentRequest();
//        request.setVersionNo("1.04");
//        request.setAcqId("01");
//        request.setChannelId(channelId);
//        request.setMerchId(merchId);
//        request.setBizId(bizId);
//        request.setPaymentTp("04");
//        request.setBizTp(bizTp);
//        request.setAccountFlag("01");
//        request.setAccountType(isPublic ? "1" : "0");
//        request.setSendTime(new SimpleDateFormat("yyyyMMddHHMMss").format(new Date()));
//        request.setBankId(bankId);
//        request.setAmount(amount);
//        request.setIdentType("111");
//        request.setIdentNo(idCardNo);
//        request.setAccountName(accountName);
//        request.setAccountNo(bankCard);
//        request.setProvince(province);
//        request.setCity(city);
//        request.setPmtChnTp("THIRD");
//        request.setMobileNum(mobile);
//        request.setRemark(remark);
//        request.setSignInfo(generatePaymentSignInfo(request));
//        return paymentV104WSProxy.singlePayment(request);
//    }
//
//    /**
//     * 单笔代付signInfo生成
//     *
//     * @param request 单笔代付对象
//     * @return
//     */
//    public String generatePaymentSignInfo(SinglePaymentRequest request) throws Exception{
//        StringBuilder checkSign = new StringBuilder();
//        checkSign.append("accountFlag=").append(request.getAccountFlag()).append("&")
//                .append("accountName=").append(request.getAccountName()).append("&")
//                .append("accountNo=").append(request.getAccountNo()).append("&")
//                .append("accountType=").append(request.getAccountType()).append("&")
//                .append("acqId=").append(request.getAcqId()).append("&")
//                .append("amount=").append(String.format("%.2f", request.getAmount())).append("&")
//                .append("bankId=").append(request.getBankId()).append("&")
//                .append("bizId=").append(request.getBizId()).append("&")
//                .append("bizTp=").append(request.getBizTp()).append("&")
//                .append("channelId=").append(request.getChannelId()).append("&")
//                .append("city=").append(request.getCity()).append("&")
//                .append("identNo=").append(request.getIdentNo()).append("&")
//                .append("identType=").append(request.getIdentType()).append("&")
//                .append("merchId=").append(request.getMerchId()).append("&")
//                .append("mobileNum=").append(request.getMobileNum()).append("&")
//                .append("paymentTp=").append(request.getPaymentTp()).append("&")
//                .append("pmtChnTp=").append(request.getPmtChnTp()).append("&")
//                .append("province=").append(request.getProvince()).append("&")
//                .append("remark=").append(request.getRemark()).append("&")
//                .append("sendTime=").append(request.getSendTime()).append("&")
//                .append("versionNo=").append(request.getVersionNo()).append("&");
//        log.info("签名串:{}", checkSign.toString() + secretKey);
//        log.info("rsa:{}", RSAUtil.sign(checkSign.toString() + secretKey));
//        return DigestUtils.md5Hex(checkSign.append(secretKey).toString());
//    }
//
//    /**
//     * 单笔代付查询
//     *
//     * @param bizId
//     * @return
//     * @throws Exception
//     */
//    public SinglePaymentQueryResponse paymentQuery(String bizId) throws Exception {
//        SinglePaymentQueryRequest request = new SinglePaymentQueryRequest();
//        request.setBizId(bizId);
//        request.setChannelId(channelId);
//        request.setMerchId(merchId);
//        request.setSendTime(new SimpleDateFormat("yyyyMMddHHMMss").format(new Date()));
//        request.setVersionNo("1.04");
//        request.setSignInfo(generatePaymentQurtySignInfo(request));
//        return paymentV104WSProxy.singlePaymentResultQuery(request);
//    }
//
//    /**
//     * 单笔代付查询signInfo生成
//     *
//     * @param paymentQueryRequest 单笔代付查询对象
//     * @return
//     */
//    private String generatePaymentQurtySignInfo(SinglePaymentQueryRequest paymentQueryRequest) throws Exception{
//        StringBuilder checkSign = new StringBuilder();
//        checkSign.append("bizId=").append(paymentQueryRequest.getBizId()).append("&")
//                .append("channelId=").append(paymentQueryRequest.getChannelId()).append("&")
//                .append("merchId=").append(paymentQueryRequest.getMerchId()).append("&")
//                .append("sendTime=").append(paymentQueryRequest.getSendTime()).append("&")
//                .append("versionNo=").append(paymentQueryRequest.getVersionNo()).append("&");
//        return DigestUtils.md5Hex(checkSign.append(secretKey).toString());
//    }
//
//    /**
//     * 获取支付信息
//     *
//     * @param orderId
//     * @return
//     * @throws Exception
//     */
//    public FuQianLaPayQueryResponseEntity getFuQianLaPayInfo(Long orderId) throws Exception {
//        Map<String, Object> paramMap = new TreeMap<>();
//        paramMap.put("app_id", appId);
//        paramMap.put("charset", "UTF-8");
//        paramMap.put("order_no", orderId);
//        paramMap.put("version", "V2.1.1");
//        paramMap.put("sign_info", createSingInfoRsa(paramMap));
//        paramMap.put("sign_type", "RSA");
//        return getFuQianLaFunResult(orderPayQueryUrl, paramMap, FuQianLaPayQueryResponseEntity.class);
//    }
//
//    /**
//     * 退款
//     *
//     * @param refundId
//     * @param fuqianlaOrderId
//     * @param refundAmount
//     * @param refundReason
//     * @param operatorId
//     * @return
//     * @throws Exception
//     */
//    public FuQianLaRefundResponseEntity refund(Long refundId, String fuqianlaOrderId, Long refundAmount, String refundReason, String operatorId) throws Exception {
//        Map<String, Object> paramMap = new TreeMap<>();
//        paramMap.put("app_id", appId);
//        paramMap.put("charset", "UTF-8");
//        paramMap.put("txn_id", fuqianlaOrderId);
//        paramMap.put("refund_no", refundId);
//        paramMap.put("refund_amount", refundAmount);
//        paramMap.put("refund_reason", refundReason);
//        paramMap.put("currency", "cny");
//        paramMap.put("operator_id", operatorId);
//        paramMap.put("version", "V2.1.1");
//        paramMap.put("sign_info", createSingInfoRsa(paramMap));
//        paramMap.put("sign_type", "RSA");
//        return getFuQianLaFunResult(refundUrl, paramMap, FuQianLaRefundResponseEntity.class);
//    }
//
//    /**
//     * 退款查询
//     *
//     * @param refundId
//     * @param fuqianlaOrderId
//     * @return
//     * @throws Exception
//     */
//    public FuQianLaRefundResponseEntity refundQuery(Long refundId, String fuqianlaOrderId) throws Exception {
//        Map<String, Object> paramMap = new TreeMap<>();
//        paramMap.put("app_id", appId);
//        paramMap.put("charset", "UTF-8");
//        paramMap.put("txn_id", fuqianlaOrderId);
//        paramMap.put("refund_no", refundId);
//        paramMap.put("version", "V2.1.1");
//        paramMap.put("sign_info", createSingInfoRsa(paramMap));
//        paramMap.put("sign_type", "RSA");
//        return getFuQianLaFunResult(refundQueryUrl, paramMap, FuQianLaRefundResponseEntity.class);
//    }
//
//    private <T> T getFuQianLaFunResult(String url, Map<String, Object> paramMap, Class<T> clazz) {
//        log.info("url:{} \n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t param:{}", url, JSON.toJSONString(paramMap));
//        T response = null;
//        try {
//            String result = url.startsWith("https") ? HttpSendUtils.doPostSSL(url, JSON.toJSONString(paramMap)) : HttpSendUtils.doPost(url, JSON.toJSONString(paramMap));
//            log.info("result:{}", result);
//            if (StringUtils.isNotEmpty(result)) {
//                response = JSONObject.parseObject(result, clazz);
//            }
//            return response;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private String createSingTypeMD5(Map<String, Object> paramMap) {
//        StringBuilder checkSign = new StringBuilder();
//        for (String key : paramMap.keySet()) {
//            checkSign.append(key).append("=").append(paramMap.get(key).toString()).append("&");
//        }
//        checkSign.append("key=").append(appKey);
//        return DigestUtils.md5Hex(checkSign.toString());
//    }
//
//
//    private String createSingInfoRsa(Map<String, Object> paramMap) throws Exception {
//        try {
//            StringBuilder checkSign = new StringBuilder();
//            for (String key : paramMap.keySet()) {
//                checkSign.append(key).append("=").append(paramMap.get(key).toString()).append("&");
//            }
//            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>签名之前的串：" + checkSign.toString().replace("V2.1.1&","V2.1.1"));
//            return RSAUtil.privateSignToHexString(checkSign.toString().replace("V2.1.1&","V2.1.1"));
//        } catch (Exception ex) {
//            log.error("createSingTypeRsa ras加密{}", ex);
//            throw ex;
//        }
//    }
//
///********************* 下面是付钱拉校验提供的方法 *******************/
//
//    /**
//     * 请求转成string
//     *
//     * @param request
//     * @return
//     */
//    public static String readReqStr(HttpServletRequest request) {
//        BufferedReader reader = null;
//        StringBuilder sb = new StringBuilder();
//        try {
//            reader = new BufferedReader(new InputStreamReader(
//                    request.getInputStream(), "utf-8"));
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != reader) {
//                    reader.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return sb.toString();
//    }
//
//
//    /**
//     * 验证接收付钱拉通知请求签名方法
//     *
//     * @param md5NotifyContent
//     * @return
//     */
//    public boolean md5Verify(String md5NotifyContent) throws Exception {
//        @SuppressWarnings("unchecked")
//        Map<String, Object> objMap = (Map<String, Object>) JSON.parse(md5NotifyContent);
//        boolean flag = false;
//        try {
//            String hexSign = "";
//            if (objMap.containsKey("sign_info")) {
//                hexSign = String.valueOf(objMap.get("sign_info"));
//            } else {
//                hexSign = String.valueOf(objMap.get("signInfo"));
//            }
//            System.out.println("签名信息:" + hexSign);
//            // 得到待签名数据
//            Map<String, ?> filterMap = paraFilter(objMap);
//            String linkStr = createLinkString(filterMap);
//            System.out.println("签名前字符串" + linkStr);
//            // 拼装md5串 md5.linkStr
//            String templinkStr = linkStr + "&key=" + NOTIFY_MD5_KEY;
//            System.out.println("完整串：" + templinkStr);
//            String md5Hex = DigestUtils.md5Hex(templinkStr.getBytes("UTF-8"));
//            System.out.println("密文串：" + md5Hex);
//            // 验证签名后数据是否相同
//            flag = hexSign.equalsIgnoreCase(md5Hex);
//        } catch (Exception e) {
//            // 验证通知签名信息error
//            log.error("验证接收付钱拉通知请求签名方法 {}", e);
//            throw e;
//        }
//        return flag;
//    }
//
//    /**
//     * 除去数组中的空值和签名参数
//     *
//     * @param sArray 签名参数组
//     * @return 去掉空值与签名参数后的新签名参数组
//     */
//    public static Map<String, ?> paraFilter(Map<String, ?> sArray) {
//        Map<String, Object> result = new HashMap<String, Object>();
//        if ((sArray == null) || (sArray.size() <= 0)) {
//            return result;
//        }
//        for (String key : sArray.keySet()) {
//            Object value = sArray.get(key);
//            if ((value == null) || value.equals("")
//                    || key.equalsIgnoreCase("sign_info")
//                    || key.equalsIgnoreCase("signInfo")
//                    || key.equalsIgnoreCase("sign_type")) {
//                continue;
//            }
//            if (value instanceof Map) {
//                @SuppressWarnings("unchecked")
//                Map<String, ?> m = (Map<String, ?>) value;
//                result.put(key, paraFilter(m));
//            } else if (value instanceof List) {
//                continue;// 不应包含多集合数据
//            } else {
//                result.put(key, value);
//            }
//        }
//        return result;
//    }
//
//
//    /**
//     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
//     *
//     * @param params 需要排序并参与字符拼接的参数组
//     * @return 拼接后字符串
//     */
//    public static String createLinkString(Map<String, ?> params) {
//        List<String> keys = new ArrayList<String>(params.keySet());
//        Collections.sort(keys);
//
//        StringBuffer prestr = new StringBuffer("");
//        for (int i = 0; i < keys.size(); i++) {
//            String key = keys.get(i);
//            Object o = params.get(key);
//            String value = String.valueOf(o);
//            if (o instanceof Map) {
//                @SuppressWarnings("unchecked")
//                Map<String, ?> m = (Map<String, ?>) o;
//                value = "{" + createLinkString(m) + "}";
//            }
//
//            if (i == (keys.size() - 1)) {// 拼接时，不包括最后一个字符
//                prestr.append(key + "=" + value);
//            } else {
//                prestr.append(key + "=" + value + "&");
//            }
//        }
//        return prestr.toString();
//    }
//
//
//    /**
//     * 验证接收付钱拉通知签名入口
//     *
//     * @return
//     * @throws Exception
//     */
//    public static boolean rsaVerify(String requestStr) {
//        boolean flag = false;
//        try {
//            @SuppressWarnings("unchecked")
//            Map<String, Object> objMap = (Map<String, Object>) JSON
//                    .parse(requestStr);
//            String hexSign = "";
//            if (objMap.containsKey("sign_info")) {
//                hexSign = String.valueOf(objMap.get("sign_info"));
//            } else {
//                hexSign = String.valueOf(objMap.get("signInfo"));
//            }
//            System.out.println("签名信息:" + hexSign);
//            // 得到待签名数据
//            Map<String, ?> filterMap = paraFilter(objMap);
//            // String linkStr = createLinkString(filterMap);
//            // 得到待签名数据
//            String linkStr = createLinkString(filterMap);
//            System.out.println("签名前字符串：" + linkStr);
//            String sha1Data = SHA1.getDigestOfString(linkStr.getBytes("UTF-8"));
//            // logger.debug("rsa.sha1Data:["+sha1Data+"]");
//            System.out.println("待验签："+ sha1Data);
//
//            PublicKey pubKey = RSAUtil.getPublicKey(FUQIANLA_PUBLIC_KEY);
//            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//            cipher.init(Cipher.DECRYPT_MODE, pubKey);
//            ByteArrayOutputStream out = doFinal(cipher, hex2byte(hexSign), 128);
//            // 字节数组转换为字符串
//            String sign = byteArraytoHexString(out.toByteArray());
//            System.out.println("密文串："+ sign);
//            // logger.debug("rsa.sign:["+sign+"]");
//            if (sign.length() > sha1Data.length()) {
//                sign = sign.substring(sign.length() - sha1Data.length());
//                // logger.debug("rsa.subSign:["+sign+"]");
//            }
//            flag = sha1Data.equalsIgnoreCase(sign);
//        } catch (Exception e) {
//            log.error("验证通知签名信息error");
//        }
//        return flag;
//    }
//
//    /**
//     * This method convert byte array to String
//     *
//     * @author sgc
//     * @return String
//     * @param  b,int bLen is :b' availability length
//     */
//    private static String byteArraytoHexString(byte[] b) {
//        int iLen = b.length;
//        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
//        StringBuffer sb = new StringBuffer(iLen * 2);
//        for (int i = 0; i < iLen; i++) {
//            int intTmp = b[i];
//            // 把负数转换为正数
//            while (intTmp < 0) {
//                intTmp = intTmp + 256;
//            }
//            // 小于0F的数需要在前面补0
//            if (intTmp < 16) {
//                sb.append("0");
//            }
//            sb.append(Integer.toString(intTmp, 16));
//        }
//        return sb.toString().toUpperCase();
//    }
//
//    private static byte[] hex2byte(String hex) throws IllegalArgumentException {
//        if (hex.length() % 2 != 0) {
//            throw new IllegalArgumentException();
//        }
//        char[] arr = hex.toCharArray();
//        byte[] b = new byte[hex.length() / 2];
//        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
//            String swap = "" + arr[i++] + arr[i];
//            int byteint = Integer.parseInt(swap, 16) & 0xFF;
//            b[j] = new Integer(byteint).byteValue();
//        }
//        return b;
//    }
//
//    /**
//     * 加密解密处理 </br>加解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
//     *
//     * @param cipher
//     * @param content
//     * @return
//     * @throws IllegalBlockSizeException
//     * @throws BadPaddingException
//     * @throws IOException
//     */
//    private static ByteArrayOutputStream doFinal(Cipher cipher, byte[] content,
//                                                 int maxLen) throws IllegalBlockSizeException, BadPaddingException, IOException {
//        InputStream ins = new ByteArrayInputStream(content);
//        ByteArrayOutputStream writer = new ByteArrayOutputStream();
//        byte[] buf = new byte[maxLen];
//        int bufl;
//        while ((bufl = ins.read(buf)) != -1) {
//            byte[] block = null;
//            if (buf.length == bufl) {
//                block = buf;
//            } else {
//                block = new byte[bufl];
//                System.arraycopy(buf, 0, block, 0, bufl);
//            }
//            byte[] eData = cipher.doFinal(block);
//            writer.write(eData);
//        }
//        return writer;
//    }
//}
