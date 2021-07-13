FROM traefik:v2.4.9 as traefik

FROM homecentr/base:2.4.3-alpine

ENV TRAEFIK_ARGS=""
ENV XDG_CONFIG_HOME="/config"

COPY --from=traefik /usr/local/bin/traefik /usr/local/bin/traefik
COPY --from=traefik /etc/ssl/certs /etc/ssl/certs

# Grant the named process to open a well-known port (1-1024) which normally requires root permissions
RUN apk add --no-cache libcap=2.27-r0 && \
    setcap 'cap_net_bind_service=+ep' /usr/local/bin/traefik

COPY ./fs/ /

VOLUME "/config"

EXPOSE 80
EXPOSE 443