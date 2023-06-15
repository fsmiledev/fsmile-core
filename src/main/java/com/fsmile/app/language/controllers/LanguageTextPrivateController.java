package com.fsmile.app.language.controllers;

import com.fsmile.core.language.api.Language;
import com.fsmile.core.language.api.LanguageTextService;
import com.fsmile.core.language.api.Text;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Project fsmile-core
 * Package com.fsmile.app.language.controllers
 * Author revouna
 * Date 15/06/2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/text-language/private")
public class LanguageTextPrivateController {

    private final LanguageTextService languageTextService;

    @GetMapping("list-languages")
    public ResponseEntity<?> getListLanguages() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(languageTextService.getAllLanguage());
    }

    @PostMapping("addText")
    public ResponseEntity<?> addText(Text text) throws Exception {
        languageTextService.addText(text);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("editText")
    public ResponseEntity<?> editText(Text text) throws Exception {
        languageTextService.editText(text);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("addLanguage")
    public ResponseEntity<?> addLanguage(Language language) throws Exception {
        languageTextService.addLanguage(language);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("addLanguage")
    public ResponseEntity<?> enabledOrDisabledLanguage(@RequestBody String languageId) throws Exception {
        languageTextService.enableOrDisableLanguage(languageId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
