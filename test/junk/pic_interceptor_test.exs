defmodule Junk.PicInterceptorTest do
  use ExUnit.Case
  use Plug.Test

  alias Junk.PicInterceptor

  setup do
    bypass = Bypass.open()
    opts = PicInterceptor.init("http://127.0.0.1:#{bypass.port}")

    {:ok, bypass: bypass, opts: opts}
  end

  test "call/2", %{bypass: bypass, opts: opts} do
    Bypass.expect(bypass, "GET", "/boop.gif", fn conn ->
      Plug.Conn.resp(conn, 200, "pretend this is a gif")
    end)

    conn =
      conn(:get, "/boop.gif")
      |> PicInterceptor.call(opts)

    assert Plug.Conn.get_resp_header(conn, "content-type") == ["image/gif"]
    assert conn.resp_body == "pretend this is a gif"
  end

  test "call/2 does not modify for a not-image", %{opts: opts} do
    original_conn = conn(:get, "/boop.txt")
    conn = PicInterceptor.call(original_conn, opts)

    assert conn == original_conn
  end
end
