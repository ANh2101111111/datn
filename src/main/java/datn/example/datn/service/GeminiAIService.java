package datn.example.datn.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.*;

@Service
public class GeminiAIService {
    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/tunedModels/products-vw0a9gr18icc:streamGenerateContent";
    private static final String API_KEY = "AIzaSyB4iCJcNBs4-4KiJnhLqMwfnLhXLXpyMsM"; // ⚠️ Thay bằng API Key thật

    public String askGeminiAI(String message) {
        String response = callGeminiAPI(message);
        return response;
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

        try {
            ResponseEntity<Map> response = restTemplate.exchange(GEMINI_API_URL, HttpMethod.POST, requestEntity, Map.class);
            return extractResponse(response.getBody());
        } catch (HttpClientErrorException e) {
            // Xử lý lỗi 404 hoặc các lỗi khác
            System.err.println("Error calling Gemini API: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return "Error: " + e.getStatusCode(); // Trả về lỗi nếu gặp phải
        } catch (Exception e) {
            // Xử lý các lỗi khác
            System.err.println("An unexpected error occurred: " + e.getMessage());
            return "An unexpected error occurred.";
        }
    }

    public String translate(String text, String targetLang) {
        if (targetLang.equalsIgnoreCase("en")) return text; // Không cần dịch nếu là tiếng Anh

        String prompt = "Translate this text to " + targetLang + ": " + text;
        return callGeminiAPI(prompt);
    }

    private String extractResponse(Map<String, Object> responseBody) {
        if (responseBody != null && responseBody.containsKey("candidates")) {
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) responseBody.get("candidates");
            if (!candidates.isEmpty()) {
                Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
                if (content != null && content.containsKey("parts")) {
                    List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
                    if (!parts.isEmpty() && parts.get(0).containsKey("text")) {
                        return (String) parts.get(0).get("text");
                    }
                }
            }
        }
        return "No response from Gemini AI.";
    }
}