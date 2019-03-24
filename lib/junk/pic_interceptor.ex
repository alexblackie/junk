defmodule Junk.PicInterceptor do
  @moduledoc """
  Intercepts requests to see if it is a Pic permalink and serves the image
  before the router tries to match on the path and render a prefix index.
  """

  import Plug.Conn

  @base_url Application.get_env(:junk, :s3_url)

  def init(_opts), do: []

  def call(%Plug.Conn{path_info: path} = conn, _opts)
      when is_list(path) and length(path) > 0 do
    slug = List.last(path)

    case Regex.match?(~r{\A(.*)\.gif|jpe?g|png$}, slug) do
      true ->
        {:ok, _status, _headers, req} =
          :hackney.get(Enum.join([@base_url | path], "/"), [], "", [])

        {:ok, image_data} = :hackney.body(req)

        conn
        |> put_resp_header("Content-Type", MIME.from_path(slug))
        |> send_resp(200, image_data)
        |> halt()

      false ->
        conn
    end
  end

  def call(conn, _opts), do: conn
end
