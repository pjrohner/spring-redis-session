package ch.rohner.peter.springredissession.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class WelcomeController {

    private static final String WELCOME_PAGE_HITS = "WELCOME_PAGE_HITS";

    @GetMapping("/")
    public String getHome(Principal principal, HttpSession httpSession) {
        addPageHits(httpSession);
        return "<h1>Welcome " + principal.getName() + "</h1>" +
                "<h3>Session ID: " + httpSession.getId() + "</h3>" +
                "<h5>Number of Page Hits: " + getPageHits(httpSession)+ "</h>"
                ;
    }

    private void addPageHits(HttpSession httpSession) {
        int hits = getPageHits(httpSession);
        httpSession.setAttribute(WELCOME_PAGE_HITS, ++hits);
    }

    private static int getPageHits(HttpSession httpSession) {
        return httpSession.getAttribute(WELCOME_PAGE_HITS) == null ? 0 : (Integer) httpSession.getAttribute(WELCOME_PAGE_HITS);
    }
}
