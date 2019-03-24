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

We use [Distillery][3] to generate OTP releases. Two container images are
provided for maximum compatibility:

  1. `Dockerfile.build` provides an image suitable for compiling and releasing
     the app
  2. `Dockerfile` provides a minimal image meant to run only the Erlang/OTP
     release binary.

Both images are based on Ubuntu 18.04; using the build image ensures that the
generated release is linked and compatible with the deployment image.

To generate a release:

```bash
$ docker build -t junk-build -f Dockerfile.build .
$ docker run --rm -v $(pwd):/app -w /app junk-build mix release
```

Then to build the deployable container:

```
$ docker build -t junk.pics:latest .
```

[3]: https://github.com/bitwalker/distillery
