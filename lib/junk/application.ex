defmodule Junk.Application do
  # See https://hexdocs.pm/elixir/Application.html
  # for more information on OTP Applications
  @moduledoc false

  @port 4001

  use Application

  def start(_type, _args) do
    # List all child processes to be supervised
    children = [
      # Starts a worker by calling: Junk.Worker.start_link(arg)
      # {Junk.Worker, arg}
      Plug.Cowboy.child_spec(scheme: :http, plug: Junk.Router, options: [port: @port])
    ]

    # See https://hexdocs.pm/elixir/Supervisor.html
    # for other strategies and supported options
    opts = [strategy: :one_for_one, name: Junk.Supervisor]
    Supervisor.start_link(children, opts)
  end
end
