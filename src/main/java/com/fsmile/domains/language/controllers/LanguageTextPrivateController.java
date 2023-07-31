package com.fsmile.domains.language.controllers;

import com.fsmile.core.language.Language;
import com.fsmile.core.language.LanguageTextService;
import com.fsmile.core.language.Text;
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
        return ResponseEntity.status(HttpStatus.OK).body(languageTextService.findAllLanguages());
    }

    @PostMapping("addText")
    public ResponseEntity<?> addText(@RequestBody Text text) throws Exception {
        languageTextService.saveText(text);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("editText")
    public ResponseEntity<?> editText(@RequestBody Text text) throws Exception {
        languageTextService.edit(text);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("addLanguage")
    public ResponseEntity<?> addLanguage(@RequestBody Language language) throws Exception {
        languageTextService.saveLanguage(language);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("enabledOrDisabledLanguage")
    public ResponseEntity<?> enabledOrDisabledLanguage(@RequestBody String languageId) throws Exception {
        languageTextService.enableOrDisableLanguage(languageId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}










