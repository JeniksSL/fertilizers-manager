package com.iba.authservice.controller;



import com.iba.authservice.facade.SecurityFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RestController("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SecurityFacade securityFacade;



   /*@GetMapping({"/login"})
    public RedirectView login() {
       return new RedirectView("http://localhost:3000/");
    }


    @GetMapping("/logout")
    public void logout(final HttpServletResponse response) {
        securityFacade.logout(response);
    }*/

  /*  @PostMapping("/refresh-token")
    @Operation(
            summary = "Exchange refresh token for new refresh and access tokens",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Getting fresh access and refresh tokens", content = {@Content()}),
                    @ApiResponse(responseCode = "401", description = "Refresh token is invalid or expired or user is disabled", content = {@Content()})
            }
    )
    public void refreshToken(@CookieValue(name = REFRESH_TOKEN_COOKIE_NAME, required = false) String refreshToken, final HttpServletResponse response) {
        securityFacade.generateRefreshToken(refreshToken, response);
    }*/


}
