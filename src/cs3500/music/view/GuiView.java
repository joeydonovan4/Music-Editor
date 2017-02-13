package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Created by joeydonovan on 6/20/16.
 */
public interface GuiView extends EditorView {

  /**
   * Handles the clicking of the space bar by a client.
   */
  void handleSpaceBar();

  /**
   * Handles the clicking of a left arrow by a client.
   */
  void handleLeftArrow();

  /**
   * Handles the clicking of a right arrow by a client.
   */
  void handleRightArrow();

  /**
   * Handles the clicking of a up arrow by a client.
   */
  void handleUpArrow();

  /**
   * Handles the clicking of a down arrow by a client.
   */
  void handleDownArrow();

  /**
   * Handles the clicking of the home button by a client.
   */
  void handleHome();

  /**
   * Handles the clicking of the end button by a client.
   */
  void handleEnd();

  /**
   * Handles the clicking of the escape button by a client.
   */
  void handleEscape();

  /**
   * Handles the clicking of the backspace button by a client.
   */
  void handleRemove();

  /**
   * Adds a {@link KeyListener} to the GuiView.
   * @param keyListener the {@code KeyListener} to be added.
   */
  void addKeyListener(KeyListener keyListener);

  /**
   * Adds a {@link MouseListener} to the GuiView.
   * @param mouseListener the {@code MouseListener} to be added.
   */
  void addMouseListener(MouseListener mouseListener);

  /**
   * Removes a {@link MouseListener} from a GuiView.
   * @param mouseListener the {@code MouseListener} to be removed.
   */
  void removeMouseListener(MouseListener mouseListener);

  /**
   * Handles the clicking of the left mouse button by a client.
   */
  void handleLeftClick();

  /**
   * Handles the clicking of the left mouse button by a client while
   * the SHIFT key is being held down.
   */
  void handleShiftLeftClick();

  /**
   * Handles the clicking of the 'A' key by a client.
   */
  void handleAKey();

  /**
   * Gets the data from the model.
   * @return model-to-view data.
   */
  IModelToView getMTV();
}
