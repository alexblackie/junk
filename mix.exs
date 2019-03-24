defmodule Junk.MixProject do
  use Mix.Project

  def project do
    [
      app: :junk,
      version: "0.1.0",
      elixir: "~> 1.8",
      start_permanent: Mix.env() == :prod,
      deps: deps()
    ]
  end

  # Run "mix help compile.app" to learn about applications.
  def application do
    [
      extra_applications: [:logger],
      mod: {Junk.Application, []}
    ]
  end

  # Run "mix help deps" to learn about dependencies.
  defp deps do
    [
      {:plug_cowboy, "~> 2.0"},
      {:sweet_xml, "~> 0.6"},
      {:mime, "~> 1.2"},
      {:hackney, "~> 1.15"}
    ]
  end
end
