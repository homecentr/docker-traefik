[![Project status](https://badgen.net/badge/project%20status/stable%20%26%20actively%20maintaned?color=green)](https://github.com/homecentr/docker-traefik/graphs/commit-activity) [![](https://badgen.net/github/label-issues/homecentr/docker-traefik/bug?label=open%20bugs&color=green)](https://github.com/homecentr/docker-traefik/labels/bug) [![](https://badgen.net/github/release/homecentr/docker-traefik)](https://hub.docker.com/repository/docker/homecentr/traefik)
[![](https://badgen.net/docker/pulls/homecentr/traefik)](https://hub.docker.com/repository/docker/homecentr/traefik) 
[![](https://badgen.net/docker/size/homecentr/traefik)](https://hub.docker.com/repository/docker/homecentr/traefik)

![CI/CD on master](https://github.com/homecentr/docker-traefik/workflows/CI/CD%20on%20master/badge.svg)


# HomeCentr - traefik
This docker image is a repack of [Traefik](https://github.com/containous/traefik) compliant with the HomeCenter docker images standard (S6 overlay, privilege drop etc.).

## Usage

```yml
version: "3.7"
services:
  traefik:
    image: homecentr/traefik
  ports:
  - "80:80"
```

## Environment variables

| Name | Default value | Description |
|------|---------------|-------------|
| PUID | 7077 | UID of the user traefik should be running as. |
| PGID | 7077 | GID of the user traefik should be running as. |
| PUID_ADDITIONAL_GROUPS | | Dictionary of additional groups the PUID user should be a member of in the container. Expected format is `gid:name,gid:name`. You can use up to 15 groups (unix limit). |
| TRAEFIK_ARGS | | Additional arguments to pass to Traefic. |

## Exposed ports

| Port | Protocol | Description |
|------|------|-------------|
| 80 | TCP | Default HTTP |

> Traefik can expose any configured port (e.g. HTTPS/443) but it needs to configured. The table specifies only the ports explicitly exposed in the Dockerfile.

## Volumes

| Container path | Description |
|------------|---------------|
| /config | Traefik configuration files in case you decide to configure it using a file. The files can also be placed at `/etc/traefik`, but for unification with other homecentr images the default location is /config. |

## Security
The container is regularly scanned for vulnerabilities and updated. Further info can be found in the [Security tab](https://github.com/homecentr/docker-traefik/security).

### Container user
The container supports privilege drop. Even though the container starts as root, it will use the permissions only to perform the initial set up. The traefik process runs as UID/GID provided in the PUID and PGID environment variables.

:warning: Do not change the container user directly using the `user` Docker compose property or using the `--user` argument. This would break the privilege drop logic.