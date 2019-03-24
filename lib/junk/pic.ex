defmodule Junk.Pic do
  @moduledoc "Holds some data about a Pic, and provides helper functions."

  defstruct name: "",
            prefix: nil

  def url(%Junk.Pic{prefix: nil} = pic), do: pic.name
  def url(pic), do: "#{pic.prefix}/#{pic.name}"

  @doc "Instantiate a Pic struct from a full path."
  def create(key) when is_list(key) do
    key |> to_string |> create()
  end
  def create(key) do
    case String.split(key, "/") do
      [name] -> %__MODULE__{name: name}
      [prefix, name] ->
        %__MODULE__{
          name: name,
          prefix: prefix
        }
    end
  end
end
