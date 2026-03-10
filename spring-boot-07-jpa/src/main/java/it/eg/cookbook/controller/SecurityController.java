package it.eg.cookbook.controller;

import it.eg.cookbook.model.MenuVoice;
import it.eg.cookbook.model.Token;
import it.eg.cookbook.model.User;
import it.eg.cookbook.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityController implements SecurityApi {

    private final JwtService jwtService;

    @Override
    public ResponseEntity<Token> login(User user) {
        Token token = new Token().jwtToken(jwtService.createJWT(user));

        return ResponseEntity.ok(token);
    }

    @Override
    public ResponseEntity<List<MenuVoice>> menu() {
        List<MenuVoice> result = new ArrayList<>();

        result.add(new MenuVoice()
                .description("Dashboard")
                .icon("dashboard")
                .tooltip("Tooltip Dashboard")
                .route("/dashboard"));


        result.add(new MenuVoice()
                .description("Documenti")
                .icon("assignment")
                .tooltip("Tooltip Documenti")
                .route("/documenti"));

        MenuVoice subMenu = new MenuVoice()
                .description("Menu")
                .icon("assignment")
                .tooltip("Tooltip Menu");

        subMenu.getSubMenuVoices().add(new MenuVoice()
                .description("Dashboard")
                .icon("dashboard")
                .tooltip("Tooltip Dashboard")
                .route("/dashboard"));

        subMenu.getSubMenuVoices().add(new MenuVoice()
                .description("Documenti")
                .icon("assignment")
                .tooltip("Tooltip Documenti")
                .route("/documenti"));

        result.add(subMenu);

        return ResponseEntity.ok(result);
    }

//    @Override
//    public ResponseEntity<MenuVoice> menu() {
//        MenuVoice root = new MenuVoice()
//                .description("/");
//
//        root.getSubMenuVoices().add(new MenuVoice()
//                .)
//
//
//
//
//
//        return null;
//    }
}

