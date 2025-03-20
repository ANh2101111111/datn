package datn.example.datn.web.rest.user;

import datn.example.datn.dto.request.ChatbotRequestDto;
import datn.example.datn.dto.response.ChatbotResponseDto;
import datn.example.datn.entity.Product;
import datn.example.datn.service.ChatbotService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/user/chatbot")
public class ChatbotController {
    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    // ✅ API hỏi chatbot (GET + tìm kiếm sản phẩm)
    @GetMapping(value = "/ask", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChatbotResponseDto> askChatbot(@RequestParam String message) {
        return ResponseEntity.ok(chatbotService.getChatbotResponse(message));
    }

    // ✅ API hỏi chatbot (POST + tìm kiếm sản phẩm)
    @PostMapping(value = "/ask", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChatbotResponseDto> askChatbot(@RequestBody ChatbotRequestDto request) {
        return ResponseEntity.ok(chatbotService.getChatbotResponse(request.getMessage()));
    }
}
