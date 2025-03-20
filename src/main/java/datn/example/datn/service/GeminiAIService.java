package datn.example.datn.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.*;

@Service
public class GeminiAIService {
    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash:generateContent";
    private static final String API_KEY = "AIzaSyC90BtBsTYf-zt5DsMVSOap3W9zGhAt8BY"; // Thay API key đúng

    public String askGeminiAI(String message) {
        // Nếu có câu hỏi đơn giản, trả về thông điệp tương ứng
        if (message.toLowerCase().contains("chào") || message.toLowerCase().contains("hello")) {
            return "Xin chào! Tôi có thể giúp gì cho bạn?";
        }
        if (message.toLowerCase().contains("có bao nhiêu sản phẩm")) {
            return "Hiện tại có 62 sản phẩm.";
        }
        if (message.toLowerCase().contains("có bao nhiêu danh mục")) {
            return "Hiện tại có 9 danh mục.";
        }

        // Nếu không phải là câu hỏi đơn giản, gọi đến API
        return callGeminiAPI(message);
    }

    private String callGeminiAPI(String message) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> requestBody = new HashMap<>();
        List<Map<String, Object>> contents = new ArrayList<>();
        Map<String, Object> userMessage = new HashMap<>();
        userMessage.put("role", "user");

        List<Map<String, String>> parts = new ArrayList<>();
        parts.add(Map.of("text", message));
        userMessage.put("parts", parts);

        contents.add(userMessage);
        requestBody.put("contents", contents);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-goog-api-key", API_KEY);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.exchange(GEMINI_API_URL, HttpMethod.POST, requestEntity, Map.class);

        Map<String, Object> responseBody = response.getBody();
        if (responseBody != null && responseBody.containsKey("candidates")) {
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) responseBody.get("candidates");
            if (!candidates.isEmpty() && candidates.get(0).containsKey("content")) {
                return candidates.get(0).get("content").toString();
            }
        }
        return "Xin lỗi, tôi không thể trả lời câu hỏi này.";
    }
}