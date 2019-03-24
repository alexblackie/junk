defmodule Junk.Router do
  use Plug.Router

  plug :match
  plug :dispatch

  match _ do
    send_resp(conn, 404, "Error 404: Resource not found.")
  end

end
