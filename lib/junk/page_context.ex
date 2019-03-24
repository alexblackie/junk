defmodule Junk.PageContext do
  @moduledoc "Holds data for rendering the HTML templates."

  defstruct page_title: "untitled",
            prefix: "/",
            pics: [],
            prefixes: []
end
