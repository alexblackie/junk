# junk dot pics v7

This is the application that runs [junk dot pics][1].

[1]: https://junk.pics

## Development

This is an [Elixir][2] project.

Install dependencies:

```
$ mix deps.get
```

Run a dev server:

```
$ iex -S mix
```

[2]: https://elixir-lang.org

## Deployment

The entire process has been automated away using Docker for maximum portability.

To generate a production-ready container:

```
$ docker build -t junk .
```

This uses a multi-stage Docker configuration to ensure the resulting production
image is lean and contains just the compiled release artifacts.

If you really want to build a release locally, you certainly still can! We use
[Distillery][3] to generate OTP releases, so:

```
$ mix release
```

... should do everything you need.

[3]: https://github.com/bitwalker/distillery
