package com.example.kwordpocket.faq.controller;

import com.example.kwordpocket.faq.dto.*;
import com.example.kwordpocket.faq.service.FaqService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/faqs")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class FaqAdminController {
    private final FaqService faqService;

    @PostMapping
    public ResponseEntity<FaqCreateResponse> createFaq(
            @Valid @RequestBody FaqCreateRequest request
    ) {
        return ResponseEntity.ok(faqService.createFaq(request));
    }

    @PutMapping("/{faqId}")
    public ResponseEntity<FaqUpdateResponse> updateFaq(
            @PathVariable Long faqId,
            @Valid @RequestBody FaqUpdateRequest request
    ){
        return ResponseEntity.ok(faqService.updateFaq(faqId,request));
    }

    @DeleteMapping("/{faqId}")
    public void deleteFaq(@PathVariable Long faqId) {
        faqService.deleteFaq(faqId);
    }
}
