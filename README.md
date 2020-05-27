[![Project status](https://badgen.net/badge/project%20status/stable%20%26%20actively%20maintaned?color=green)](https://github.com/homecentr/docker-$$IMAGE_NAME$$/graphs/commit-activity) [![](https://badgen.net/github/label-issues/homecentr/docker-$$IMAGE_NAME$$/bug?label=open%20bugs&color=green)](https://github.com/homecentr/docker-$$IMAGE_NAME$$/labels/bug) [![](https://badgen.net/github/release/homecentr/docker-$$IMAGE_NAME$$)](https://hub.docker.com/repository/docker/homecentr/$$IMAGE_NAME$$)
[![](https://badgen.net/docker/pulls/homecentr/$$IMAGE_NAME$$)](https://hub.docker.com/repository/docker/homecentr/$$IMAGE_NAME$$) 
[![](https://badgen.net/docker/size/homecentr/$$IMAGE_NAME$$)](https://hub.docker.com/repository/docker/homecentr/$$IMAGE_NAME$$)

![CI/CD on master](https://github.com/homecentr/docker-$$IMAGE_NAME$$/workflows/CI/CD%20on%20master/badge.svg)
![Regular Docker image vulnerability scan](https://github.com/homecentr/docker-$$IMAGE_NAME$$/workflows/Regular%20Docker%20image%20vulnerability%20scan/badge.svg)


# HomeCentr - $$IMAGE_NAME$$


## Usage

```yml
version: "3.7"
services:
  $$IMAGE_NAME$$:
    build: .
    image: homecentr/$$IMAGE_NAME$$
```

## Environment variables

| Name | Default value | Description |
|------|---------------|-------------|
| PUID | 7077 | UID of the user $$IMAGE_NAME$$ should be running as. |
| PGID | 7077 | GID of the user $$IMAGE_NAME$$ should be running as. |

## Exposed ports

| Port | Protocol | Description |
|------|------|-------------|
| 80 | TCP | Some useful details |

## Volumes

| Container path | Description |
|------------|---------------|
| /config | Some useful details |

## Security
The container is regularly scanned for vulnerabilities and updated. Further info can be found in the [Security tab](https://github.com/homecentr/docker-$$IMAGE_NAME$$/security).

### Container user
The container supports privilege drop. Even though the container starts as root, it will use the permissions only to perform the initial set up. The $$IMAGE_NAME$$ process runs as UID/GID provided in the PUID and PGID environment variables.

:warning: Do not change the container user directly using the `user` Docker compose property or using the `--user` argument. This would break the privilege drop logic.