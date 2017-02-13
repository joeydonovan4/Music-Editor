package cs3500.music.controller;

/**
 * Created by joeydonovan on 6/23/16.
 */

import cs3500.music.view.GuiView;

/**
 * Represents a Controller.
 */
public interface IController {

  /**
   * Creates a {@link KeyboardHandler}.
   * @return a {@link KeyboardHandler}.
   */
  KeyboardHandler createKeyboardHandler();

  /**
   * Creates a standard {@link MouseHandler}.
   * @return a {@link MouseHandler}.
   */
  MouseHandler createStandardMouseHandler();

  /**
   * Creates a {@link MouseHandler} that handles mouse events
   * when the shift key is being held down.
   * @return a {@link MouseHandler}.
   */
  MouseHandler createShiftMouseHandler();

  /**
   * Runs the GUI Controller. Renders the {@link GuiView} and handles
   * updates to the GUI and MIDI.
   */
  void render();
}
