FROM ubuntu:bionic
ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update && apt-get install -y openssl libssl-dev && apt-get clean

ADD _build/prod/rel/junk/ /app

EXPOSE 4001

CMD ["/app/bin/junk", "foreground"]
