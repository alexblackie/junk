defmodule Junk.Pics do
  @moduledoc "Provides access to the Pics collection data."

  @base_url Application.get_env(:junk, :s3_url)

  require Logger
  use GenServer
  import SweetXml

  # ===========================================================================
  # Public Interface
  # ===========================================================================

  def start_link(_opts) do
    GenServer.start_link(__MODULE__, [], name: __MODULE__)
  end

  def all(), do: all(nil)

  def all(prefix) do
    GenServer.call(__MODULE__, {:all, prefix})
  end

  def prefixes do
    GenServer.call(__MODULE__, :prefixes)
  end

  # ===========================================================================
  # Callbacks
  # ===========================================================================

  @impl true
  def init([]) do
    send(self(), :populate)
    {:ok, []}
  end

  @impl true
  def handle_call({:all, prefix}, _caller, state) do
    pics =
      case prefix do
        nil ->
          state[:pics]

        _ ->
          Enum.filter(state[:pics], fn p -> p.prefix == prefix end)
      end

    {:reply, pics, state}
  end

  @impl true
  def handle_call(:prefixes, _caller, state) do
    {:reply, state[:prefixes], state}
  end

  @impl true
  def handle_info(:populate, _state) do
    Logger.info("Populating Pics database")

    {:ok, _status, _headers, req} = :hackney.get(@base_url, [], "", [])
    {:ok, bucket_xml} = :hackney.body(req)

    pics = bucket_xml
           |> xpath(~x"//ListBucketResult/Contents"l, name: ~x"./Key/text()")
           |> Enum.map(fn p -> Junk.Pic.create(p[:name]) end)

    prefixes =
      Enum.map(pics, fn p -> p.prefix end)
      |> Enum.uniq()
      |> Enum.reject(&is_nil/1)

    new_state = [pics: pics, prefixes: prefixes]

    {:noreply, new_state}
  end
end
