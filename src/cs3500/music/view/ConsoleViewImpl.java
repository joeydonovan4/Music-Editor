package cs3500.music.view;

/**
 * Created by joeydonovan on 6/16/16.
 */

/**
 * Represents a view as a String output to the console.
 */
public class ConsoleViewImpl implements EditorView {

  /**
   * Creates a String output of the model in the console.
   * @param mtv the model to be shown in the view.
   */
  @Override
  public void render(IModelToView mtv) {
    System.out.print(mtv.toString());
  }

  @Override
  public void update() {

  }
}
