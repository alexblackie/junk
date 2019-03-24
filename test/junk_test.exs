defmodule JunkTest do
  use ExUnit.Case
  doctest Junk

  test "greets the world" do
    assert Junk.hello() == :world
  end
end
