package datn.example.datn.config;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Formatter;
import java.util.Map;
import java.util.stream.Collectors;

public class VNPayConfig {
    public static final String vnp_TmnCode = "25BNF1Y5";
    public static final String vnp_HashSecret = "XB3X24QRMXWQ8G9Y40U5B04GQJNEOFB8";
    public static final String vnp_PayUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static final String vnp_ReturnUrl = "http://localhost:8080/api/payment/vnpay_return";

    public static String hmacSHA512(String key, String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(key.getBytes(StandardCharsets.UTF_8));
        return byteArrayToHex(md.digest(data.getBytes(StandardCharsets.UTF_8)));
    }

    private static String byteArrayToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
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

