defmodule Junk.Pic do
  @moduledoc "Holds some data about a Pic, and provides helper functions."

  defstruct name: "",
            prefix: nil

  def url(%Junk.Pic{prefix: nil} = pic), do: pic.name
  def url(pic), do: "#{pic.prefix}/#{pic.name}"
end
