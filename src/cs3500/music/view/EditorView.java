package cs3500.music.view;

/**
 * Created by joeydonovan on 6/14/16.
 */

/**
 * Represents a view for the {@link cs3500.music.model.IMusicEditorModel}.
 * A view can be represented in different ways.
 */
public interface EditorView {

  /**
   * Renders a view that represents the given model.
   * @param mtv the model to be shown in the view.
   */
  void render(IModelToView mtv);

  /**
   * Updates the GuiView.
   */
  void update();
}
