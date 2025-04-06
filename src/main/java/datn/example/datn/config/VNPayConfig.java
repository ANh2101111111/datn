package datn.example.datn.config;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Formatter;
import java.util.Map;
import java.util.stream.Collectors;

public class VNPayConfig {
    public static final String vnp_TmnCode = "25BNF1Y5"; // Mã thương mại
    public static final String vnp_HashSecret = "XB3X24QRMXWQ8G9Y40U5B04GQJNEOFB8"; // Bí mật
    public static final String vnp_PayUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static final String vnp_ReturnUrl = "https://apt-electric-oriole.ngrok-free.app/api/payment/vnpay_return";

    public static String hmacSHA512(String key, String data) throws Exception {
        Mac hmac = Mac.getInstance("HmacSHA512");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hmac.init(secretKey);

        byte[] hash = hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));

        // Chuyển đổi sang hex
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }

    public static String getQueryString(Map<String, String> params, String secret) throws Exception {
        String queryString = params.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        String secureHash = hmacSHA512(secret, queryString);
        return queryString + "&vnp_SecureHash=" + secureHash;
    }
}