package kz.djdegens.academic.services.implementations;

import kz.djdegens.academic.services.interfaces.KeycloakService;
import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {

    @Override
    public String getPreferredUsername() {
        KeycloakPrincipal principal = (KeycloakPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getKeycloakSecurityContext().getToken().getPreferredUsername().toLowerCase();
    }
}
