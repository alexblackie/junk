defmodule Junk.PicInterceptor do
  @moduledoc """
  Intercepts requests to see if it is a Pic permalink and serves the image
  before the router tries to match on the path and render a prefix index.
  """

  import Plug.Conn

  @default_upstream Application.get_env(:junk, :s3_url)

  def init(url) when is_binary(url), do: [upstream: url]
  def init(_), do: [upstream: @default_upstream]

  def call(%Plug.Conn{path_info: path} = conn, opts)
      when is_list(path) and length(path) > 0 do
    slug = List.last(path)

    case Regex.match?(~r{\A(.*)\.gif|jpe?g|png$}, slug) do
      true ->
        {:ok, _status, _headers, req} =
          :hackney.get(Enum.join([opts[:upstream] | path], "/"), [], "", [])

        {:ok, image_data} = :hackney.body(req)

        conn
        |> put_resp_header("content-type", MIME.from_path(slug))
        |> send_resp(200, image_data)
        |> halt()

      false ->
        conn
    end
  end

  def call(conn, _opts), do: conn
end
