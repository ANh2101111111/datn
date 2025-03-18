package datn.example.datn.web.rest.user;
import datn.example.datn.dto.response.ProductResponseDto;
import datn.example.datn.service.AssemblyAIService;
import datn.example.datn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/voice-search")
public class AssemblyAIController {

    private final AssemblyAIService assemblyAIService;

    public AssemblyAIController(AssemblyAIService assemblyAIService) {
        this.assemblyAIService = assemblyAIService;
    }

    @PostMapping("/transcribe")
    public ResponseEntity<String> transcribeAudio(@RequestParam("file") MultipartFile file) {
        try {
            String text = assemblyAIService.transcribeAudio(file);
            return ResponseEntity.ok(text);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Lỗi: " + e.getMessage());
        }
    }

    @GetMapping("/allngrok")
    public List<ProductResponseDto> getAllProducts() {
        return assemblyAIService.getAllProductsFromNgrok();
    }

    @PostMapping("/upload")
    public ResponseEntity<List<ProductResponseDto>> uploadAudio(@RequestParam("file") MultipartFile file) {
        try {
            // Chuyển đổi âm thanh thành văn bản
            String rawText = assemblyAIService.transcribeAudio(file);

            // Phân tích nội dung tìm kiếm và tìm sản phẩm từ ngrok API
            List<ProductResponseDto> result = assemblyAIService.getAllProductsFromNgrok();

            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}



