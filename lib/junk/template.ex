defmodule Junk.Template do
  @moduledoc "Provides compiled templates for HTML rendering."

  @template_dir :code.priv_dir(:junk) ++ "/templates"

  require EEx

  EEx.function_from_file(:def, :index, "#{@template_dir}/index.eex", [:ctx])
end
