////package datn.example.datn.service;
////
////import org.springframework.stereotype.Service;
////import org.springframework.web.client.RestTemplate;
////import org.springframework.http.*;
////
////import java.util.*;
////
////@Service
////public class GeminiAIService {
////    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash:generateContent";
////    private static final String API_KEY = "AIzaSyC90BtBsTYf-zt5DsMVSOap3W9zGhAt8BY"; // Thay API key đúng
////
////    public String askGeminiAI(String message) {
////        return callGeminiAPI(message);
////    }
////
////    private String callGeminiAPI(String message) {
////        RestTemplate restTemplate = new RestTemplate();
////
////        Map<String, Object> requestBody = new HashMap<>();
////        List<Map<String, Object>> contents = new ArrayList<>();
////        Map<String, Object> userMessage = new HashMap<>();
////        userMessage.put("role", "user");
////
////        List<Map<String, String>> parts = new ArrayList<>();
////        parts.add(Map.of("text", message));
////        userMessage.put("parts", parts);
////
////        contents.add(userMessage);
////        requestBody.put("contents", contents);
////
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_JSON);
////        headers.set("x-goog-api-key", API_KEY);
////
////        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
////        ResponseEntity<Map> response = restTemplate.exchange(GEMINI_API_URL, HttpMethod.POST, requestEntity, Map.class);
////
////        Map<String, Object> responseBody = response.getBody();
////        if (responseBody != null && responseBody.containsKey("candidates")) {
////            List<Map<String, Object>> candidates = (List<Map<String, Object>>) responseBody.get("candidates");
////            if (!candidates.isEmpty() && candidates.get(0).containsKey("content")) {
////                return candidates.get(0).get("content").toString();
////            }
////        }
////        return "Xin lỗi, tôi không thể trả lời câu hỏi này.";
////    }
////}
//package datn.example.datn.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.http.*;
//
//import java.util.*;
//
//@Service
//public class GeminiAIService {
//    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash:generateContent";
//    private static final String API_KEY = "AIzaSyC90BtBsTYf-zt5DsMVSOap3W9zGhAt8BY"; // Thay API key đúng
//
//    public String askGeminiAI(String message) {
//        return callGeminiAPI(message, null);
//    }
//
//    public String translate(String text, String targetLang) {
//        String prompt = String.format("Translate this text to %s: %s", targetLang, text);
//        return callGeminiAPI(prompt, targetLang);
//    }
//
//    private String callGeminiAPI(String message, String targetLang) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        Map<String, Object> requestBody = new HashMap<>();
//        List<Map<String, Object>> contents = new ArrayList<>();
//        Map<String, Object> userMessage = new HashMap<>();
//        userMessage.put("role", "user");
//
//        List<Map<String, String>> parts = new ArrayList<>();
//        parts.add(Map.of("text", message));
//        userMessage.put("parts", parts);
//
//        contents.add(userMessage);
//        requestBody.put("contents", contents);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("x-goog-api-key", API_KEY);
//
//        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
//        ResponseEntity<Map> response = restTemplate.exchange(GEMINI_API_URL, HttpMethod.POST, requestEntity, Map.class);
//
//        return extractResponse(response.getBody());
//    }
//
//    private String extractResponse(Map<String, Object> responseBody) {
//        if (responseBody != null && responseBody.containsKey("candidates")) {
//            List<Map<String, Object>> candidates = (List<Map<String, Object>>) responseBody.get("candidates");
//            if (!candidates.isEmpty()) {
//                Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
//                if (content != null && content.containsKey("parts")) {
//                    List<Map<String, String>> parts = (List<Map<String, String>>) content.get("parts");
//                    if (!parts.isEmpty() && parts.get(0).containsKey("text")) {
//                        return parts.get(0).get("text");
//                    }
//                }
//            }
//        }
//        return "Xin lỗi, tôi không thể trả lời câu hỏi này.";
//    }
//}

package datn.example.datn.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.*;

@Service
public class GeminiAIService {
    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash:generateContent";
    private static final String API_KEY = "AIzaSyC90BtBsTYf-zt5DsMVSOap3W9zGhAt8BY"; // Thay bằng API Key thật

    public String askGeminiAI(String message, String lang) {
        String response = callGeminiAPI(message);
        return translate(response, lang);
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

        return extractResponse(response.getBody());
    }

    public String translate(String text, String targetLang) {
        String prompt = "Translate this text to " + targetLang + ": " + text;
        return callGeminiAPI(prompt);
    }

    private String extractResponse(Map<String, Object> responseBody) {
        if (responseBody != null && responseBody.containsKey("candidates")) {
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) responseBody.get("candidates");
            if (!candidates.isEmpty()) {
                return (String) candidates.get(0).get("text");
            }
        }
        return "Translation failed.";
    }
}
