package com.example.demo;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;
import java.util.TreeMap;

public class RSAUtil {
    // 支付公钥
    public static final String PAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHuFeMRs5k7DZwi6QPYlPVFRsGv7CytIMyK2XvdXH0YRCYM3OZhXH6l3TCrOrGtJax01N0jyVsqxaaiYqfR06IarJGWYqTY9IXI6mvP1Fd33YNmByw0Mm0VsvGoChKlzMI8GV/Wt0lb3oGdBPhvCVuxpN59QjeqekHmgUzwr9OIwIDAQAB";
    // 代付公钥
    public static final String REMIT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDMX7BiAroLRgq7U4zVmIjZ0OS7pMETJ3chAeN3LPK2SIcVhtlog/NtZTUgqF7eV0tb5JqIO9SjiFxY9rAHoCQhocTEi3KWcY7xbOlbqRB8sfLPJbFADCRmXaG8AozaURsWU2yut7WF5sWwHYZsJ0/wWea+2FsEZ1vktM8/fS4VRQIDAQAB";

    // 商户私钥
    public static final String PRIVATE_KEY =
            "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKIE9OWSfOqoxKZTsOmaOXV0KdD4" +
                    "4Bhz0JFxv/NaD36xu1Jfeg4MMb6yqPqckmSFE/+BFU+doHTC3VXqqWZbo6pfAL8r7P9oIWGoX+EJ" +
                    "1MyP/m92y15UXFdtA+DyyT1Rw24Gn/M46Vhg8RhsZ7a8/w/63LXGBVVUBaa2QBSidyB7AgMBAAEC" +
                    "gYEAjlnGhdIwGMjRhsWvgX+0jkLS3r1bv4A6Nx/3I6v0Qz+LMvOaPK9wIXm9x/s7EEBwcqQKgloe" +
                    "+ZegenVkAv/vCkDI7tC0M4F3+dB06UM3zzvr9iRqnFnjVkbgF/nSkUMgd0wqfSaGDyA7Koq3cliW" +
                    "GNRSsz69Tjrp9lxfW/XuamkCQQDjQaphUX685dRA4G4jdZW0LxDIUelhD74vExp9GwzAE4BVnT3S" +
                    "dsdELia1osgujZOheoewpyGtw+xmgIs73hfHAkEAtoL6jaP9KiyxX82eOqqEYhftROTgInee9oCB" +
                    "+/CpAFRRj8PXjbnpdKx0DiPuiv3GTPIpqq5zWXdTkuw2Urx5rQJALT+w5gms+32jgnttUqAh+6t3" +
                    "Yvt0RYA19PePyaMuKbx4TBOv8Iz35ipuSLItu58y6u8Tv2e3u/qLwmkL0BLLlwJBALOh0GbOC6OR" +
                    "33Yyk1ScdbVyZUYUmoFyCNQHAWhtoni/hJMiJyrH233JKmSseuD0C1O6WMYz8zYQGwKVWJYwCPUC" +
                    "QAK2NQ3vJppXA3NydlRqsUUDMrjkymO1n9hcYErfxC9ThDk+/uV6tURZawODR0QvroZjcwDA3ilT" +
                    "PKLcDyB74No=";
    static String merchNo = "LFP201808250000";// 商户号
    static String key = "7D7E768BB4CB7EBCE6E3B067A6351342";// 签名MD5密钥,24位
    static String reqUrl = "http://47.94.6.240:9003/api/pay";// 测试环境
    static String queryUrl="http://47.94.6.240:9003/api/queryPayResult";
    static String version = "V3.6.0.0";// 版本号


    public static void main(String[] args) throws Exception {
        //sign=8543804AD4D97C358132E700B6DB315B
        //metaSignMap+sign={amount=500, goodsName=iPhone配件, merchNo=LFP201808250000, netwayType=ZFB, notifyUrl=http://127.0.0.1/, notifyViewUrl=http://127.0.0.1/view, orderNo=20180827224226307CyisSM, randomNo=WFh1, sign=8543804AD4D97C358132E700B6DB315B}
//        C2kFwc+DSk38IF03oKlk1305di6Cgc1j0SH0E8FDv1luK6p2/vyQWFlCU63RDGbKG8vfPw/ikzQi
//        MrwkN8dCgRCu2x6qFofA2MZRohXHkk4MuHepW09Al7ZTxM7n2qSgshQjSbUBu2qWoHfGqYKg6EpB
//        Ka0gM4Zxmtjam6oTRsZb8H2EB+tfWE4bYBl+w6zT+retJzp41m4VYq0smU7c8FAuMHabMEqdwasC
//        1U5SRES5qZo5z9MxI6XGXW3jvfy63BmSCMO6vCTuz2rm0Hwpn+Nn9fqQNzDlhiaYKOJfvvIeKaal
//        HGF6qof4xzoCZtuzOIH3BX1n2+xrzhqzRGy3oKofAh/kUImCUaGXgHjBRSrBNbQ0E1HkPN3gt8wz
//        W/aj7BWMNIev/P/GYlroNFnRWZiosPTwnkWbcCz91LAEafkFKuDn46My739gQEW36Wde2qucSX0D
//        bM8Qs/p6DFiBLogRlF1mBcgwdhxBVJ7cQ91rCcQaj/pfh9rZUz0Z/NvZ

        Map<String, String> metaSignMap = new TreeMap<String, String>();
        metaSignMap.put("orderNo", "20180827224226307");
        metaSignMap.put("randomNo", "WFh1");// 4位随机数

        metaSignMap.put("merchNo", merchNo);
        metaSignMap.put("netwayType", "ZFB");// WX:微信支付,ZFB:支付宝支付
        metaSignMap.put("amount", "500");// 单位:分
        metaSignMap.put("goodsName", "iPhone配件");// 商品名称：20位
        metaSignMap.put("notifyUrl", "http://127.0.0.1/");// 回调地址
        metaSignMap.put("notifyViewUrl", "http://127.0.0.1/view");// 回显地址
        metaSignMap.put("sign", "7C75732E8CD10C1D81415C80F7D0BF33");// 回显地址
        String signData = JSON.toJSONString(metaSignMap);

        byte[] data = signData.getBytes("UTF-8");
        PublicKey publicKey = getPublicKey(PAY_PUBLIC_KEY);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        int blockSize = cipher.getOutputSize(data.length) - 11;


        int offSet = 0;
        byte[] cache = null;
        int i = 0;
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        while (data.length - offSet > 0) {
            if (data.length - offSet > blockSize) {
                cache = cipher.doFinal(data, offSet, blockSize);
            } else {
                cache = cipher.doFinal(data, offSet, data.length - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * blockSize;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();

        System.out.println(Base64.encodeBase64String(out.toByteArray()));


    }

    public static PublicKey getPublicKey(String publicKey) throws Exception{
        byte[ ] keyBytes=Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec keySpec=new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }


    public static PrivateKey getPrivateKey(String privateKey) throws Exception{
        byte[ ] keyBytes=Base64.decodeBase64(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }
}
