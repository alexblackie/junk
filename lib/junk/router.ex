defmodule Junk.Router do
  use Plug.Router

  plug(Junk.PicInterceptor)
  plug(Plug.Static, at: "/_", from: :junk)
  plug(:match)
  plug(:dispatch)

  get "/" do
    ctx = %Junk.PageContext{
      page_title: "everything",
      pics: Junk.Pics.all(),
      prefixes: Junk.Pics.prefixes()
    }

    template = Junk.Template.index(ctx)

    conn
    |> put_resp_header("Content-Type", "text/html")
    |> send_resp(200, template)
  end

  get "/:prefix" do
    ctx = %Junk.PageContext{
      page_title: conn.params["prefix"],
      pics: Junk.Pics.all(conn.params["prefix"]),
      prefixes: Junk.Pics.prefixes()
    }

    template = Junk.Template.index(ctx)

    conn
    |> put_resp_header("Content-Type", "text/html")
    |> send_resp(200, template)
  end

  match _ do
    send_resp(conn, 404, "Error 404: Resource not found.")
  end
end
