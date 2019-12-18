# -----------------------------------------------------------------------------
# Build Image
# -----------------------------------------------------------------------------
FROM alpine:3.10 as buildenv

ENV MIX_ENV=prod \
    ELIXIR_VERSION=1.9.4

RUN apk --no-cache add \
      # install full-ass erlang in Alpine
      erlang-xmerl erlang-dialyzer erlang-parsetools erlang-sasl erlang-stdlib erlang-ssh erlang-erl-docgen erlang-eunit erlang erlang-inets erlang-tools erlang-snmp erlang-et erlang-dev erlang-debugger erlang-asn1 erlang-erl-interface erlang-hipe erlang-odbc erlang-reltool erlang-crypto erlang-common-test erlang-ssl erlang-mnesia erlang-compiler erlang-os-mon erlang-erts erlang-public-key erlang-syntax-tools erlang-edoc erlang-kernel erlang-eldap erlang-megaco erlang-diameter erlang-runtime-tools \
      curl git make g++ unzip

RUN curl -sLo /tmp/elixir.zip "https://github.com/elixir-lang/elixir/releases/download/v$ELIXIR_VERSION/Precompiled.zip" && \
    unzip -d /usr /tmp/elixir.zip && rm /tmp/elixir.zip && \
    mix local.hex --force && \
    mix local.rebar --force

ADD . /app
WORKDIR /app

RUN mix deps.get && mix release


# -----------------------------------------------------------------------------
# Production Image
# -----------------------------------------------------------------------------

FROM alpine:3.10

# Resolves issues with ^g
ENV TERM=xterm

RUN apk --no-cache add bash ca-certificates

RUN addgroup -S deploy && adduser -S deploy -G deploy && \
    mkdir /app && chown deploy:deploy /app

WORKDIR /app
COPY --from=buildenv --chown=deploy:deploy /app/_build/prod/rel/junk/ /app/

USER deploy

CMD ["/app/bin/junk", "start"]
