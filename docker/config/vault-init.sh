#! /bin/sh

set -e

export VAULT_ADDR=http://vault:8200

# give some time for Vault to start and be ready
sleep 3

# login with root token at $VAULT_ADDR
vault login root

vault kv put secret/application springdoc.swagger-ui.oauth.clientSecret=GcZ989HnNcBHyWkNO5JzmTGjKoauqNFZ

vault kv put secret/boot-admin-server spring.security.oauth2.client.registration.keycloak.client-secret=J7BR28O9gOXx8lx3kR2TyYhdDk031lP8

vault kv put secret/ingredient-service spring.security.oauth2.client.registration.keycloak.client-secret=TIPukDCkZsWo6SQHfDxdrvs45gYcigkJ

vault kv put secret/kitchen-service spring.security.oauth2.client.registration.keycloak-authorization-code.client-secret=ry3MuVFdhs7khhKeNkkKLjcOEDZjT1af spring.security.oauth2.client.registration.keycloak.client-secret=ry3MuVFdhs7khhKeNkkKLjcOEDZjT1af

vault kv put secret/order-service spring.security.oauth2.client.registration.keycloak.client-secret=cds1T8QBbQpOaVrmH20yv56vwePEXHUV

vault kv put secret/taco-service spring.security.oauth2.client.registration.keycloak.client-secret=nAsNAysab8Duj89dZBu9ue10bkRWth7X

