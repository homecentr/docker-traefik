FROM traefik:v2.5.1 as traefik

FROM ghcr.io/homecentr/base:3.2.0-alpine

ENV TRAEFIK_ARGS=""
ENV XDG_CONFIG_HOME="/config"

COPY --from=traefik /usr/local/bin/traefik /usr/local/bin/traefik
COPY --from=traefik /etc/ssl/certs /etc/ssl/certs

# Grant the named process to open a well-known port (1-1024) which normally requires root permissions
RUN apk add --no-cache libcap=2.50-r0 && \
    setcap 'cap_net_bind_service=+ep' /usr/local/bin/traefik

COPY ./fs/ /

VOLUME "/config"

EXPOSE 80
EXPOSE 443