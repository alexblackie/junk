defmodule Junk.PicTest do
  use ExUnit.Case

  alias Junk.Pic

  test "url/1 without a prefix" do
    pic = %Pic{prefix: nil, name: "boop.gif"}
    assert "boop.gif" == Pic.url(pic)
  end

  test "url/1 with a prefix" do
    pic = %Pic{prefix: "ad", name: "juice.gif"}
    assert "ad/juice.gif" == Pic.url(pic)
  end

  test "create/1 with a string key and a prefix" do
    assert %Pic{name: "juice.gif", prefix: "ad"} == Pic.create("ad/juice.gif")
  end

  test "create/1 with a string key and no prefix" do
    assert %Pic{name: "boop.gif", prefix: nil} == Pic.create("boop.gif")
  end

  test "create/1 with a charlist" do
    assert %Pic{name: "boop.gif"} == Pic.create('boop.gif')
  end

  test "create/1 with a charlist and prefix" do
    assert %Pic{name: "juice.gif", prefix: "ad"} == Pic.create('ad/juice.gif')
  end
end
