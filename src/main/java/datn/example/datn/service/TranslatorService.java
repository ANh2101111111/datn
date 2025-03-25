package datn.example.datn.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslatorService {
    private final GeminiAIService geminiAIService;

    public TranslatorService(GeminiAIService geminiAIService) {
        this.geminiAIService = geminiAIService;
    }

    public String translateToEnglish(String text) {
        return geminiAIService.translate(text, "en");
    }

}
