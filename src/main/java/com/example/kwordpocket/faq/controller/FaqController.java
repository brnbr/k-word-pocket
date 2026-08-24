package com.example.kwordpocket.faq.controller;

import com.example.kwordpocket.faq.dto.*;
import com.example.kwordpocket.faq.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faqs")
@RequiredArgsConstructor
public class FaqController {
    private final FaqService faqService;

    @GetMapping
    public ResponseEntity<List<FaqGetResponse>> getAllFaq() {
        return ResponseEntity.ok(faqService.getAllFaq());
    }

    @GetMapping("/{faqId}")
    public ResponseEntity<FaqGetResponse> getOneFaq(
            @PathVariable Long faqId
    ) {
        return ResponseEntity.ok(faqService.getOneFaq(faqId));
    }
}
