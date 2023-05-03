package com.fsmile.config.web;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Project fsmile-core
 * Package com.fsmile.config.web
 * Author revouna
 * Date 03/05/2023
 */

@Configuration
public class RequestFilter extends OncePerRequestFilter {
    List<Locale> LOCALES = Arrays.asList(
            new Locale("en"),
            new Locale("fr")
    );
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerLang = request.getHeader("Accept-Language");
        if (headerLang != null){
            Locale.setDefault( Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES));
        }
        filterChain.doFilter(request, response);
    }
}
