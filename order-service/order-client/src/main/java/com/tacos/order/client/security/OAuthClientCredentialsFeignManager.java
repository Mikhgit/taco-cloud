package com.tacos.order.client.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.registration.ClientRegistration;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static java.util.Objects.isNull;

@Slf4j
public class OAuthClientCredentialsFeignManager {

    private final OAuth2AuthorizedClientManager manager;
    private final ClientRegistration clientRegistration;

    public OAuthClientCredentialsFeignManager(OAuth2AuthorizedClientManager manager, ClientRegistration clientRegistration) {
        this.manager = manager;
        this.clientRegistration = clientRegistration;
    }

    private Authentication createPrincipal() {
        return new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.emptySet();
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return this;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
            }

            @Override
            public String getName() {
                return clientRegistration.getClientId();
            }
        };
    }

    public String getAccessToken() {
        try {
            Authentication principal = Optional.of(SecurityContextHolder.getContext())
                    .map(SecurityContext::getAuthentication)
                    .orElse(createPrincipal());
            OAuth2AuthorizeRequest oAuth2AuthorizeRequest = OAuth2AuthorizeRequest
                    .withClientRegistrationId(clientRegistration.getRegistrationId())
                    .principal(principal)
                    .build();
            OAuth2AuthorizedClient client = manager.authorize(oAuth2AuthorizeRequest);
            if (isNull(client)) {
                throw new IllegalStateException("client credentials flow on " + clientRegistration.getRegistrationId() + " failed, client is null");
            }
            return client.getAccessToken().getTokenValue();
        } catch (Exception exp) {
            log.error("client credentials error " + exp.getMessage());
        }
        return null;
    }
}