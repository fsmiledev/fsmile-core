package com.fsmile;

import com.fsmile.config.Security.PasswordEncoderImpl;
import com.fsmile.config.Security.RsaKeyConfig;
import com.fsmile.core.language.Language;
import com.fsmile.core.language.LanguageTextService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@AllArgsConstructor
@SpringBootApplication
@EnableConfigurationProperties(RsaKeyConfig.class)
public class FsmileApplication {

	private final LanguageTextService languageTextService;

	public static void main(String[] args) {
		SpringApplication.run(FsmileApplication.class, args);
	}

	@Bean
	public void init(){
		List<Language> allLanguage = languageTextService.findEnableLanguages();
		if (allLanguage.isEmpty()) {
			Language fr = Language.builder()
					.code("FR")
					.enabled(true)
					.wording("Français")
					.logoUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Flag_of_France_%281794%E2%80%931815%2C_1830%E2%80%931974%2C_2020%E2%80%93present%29.svg/langfr-225px-Flag_of_France_%281794%E2%80%931815%2C_1830%E2%80%931974%2C_2020%E2%80%93present%29.svg.png")
					.locale(Locale.FRENCH)
					.build();
			Language en = Language.builder()
					.code("EN")
					.enabled(true)
					.logoUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Flag_of_the_United_Kingdom_%283-5%29.svg/langfr-225px-Flag_of_the_United_Kingdom_%283-5%29.svg.png")
					.wording("English")
					.locale(Locale.ENGLISH)
					.build();
			languageTextService.saveLanguage(fr);
			languageTextService.saveLanguage(en);
		}

	}

}
