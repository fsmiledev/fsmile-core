package com.fsmile.app.language.controllers;

import com.fsmile.core.language.api.LanguageTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project fsmile-core
 * Package com.fsmile.app.language.controllers
 * Author revouna
 * Date 15/06/2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/text-language/public")
public class LanguageTextPublicController {

    private final LanguageTextService languageTextService;

    @GetMapping("getActivatedLanguage")
    public ResponseEntity<?> getLanguage() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(languageTextService.getEnableLanguage());
    }
}
