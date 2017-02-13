package cs3500.music.controller;

import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.MusicEditorModelRepeats;
import cs3500.music.view.CompositeViewImpl;
import cs3500.music.view.GuiView;
import cs3500.music.view.GuiViewImpl;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.ModelToView;
import cs3500.music.view.MidiViewRepeat;
import cs3500.music.view.ModelToViewRepeats;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by joeydonovan on 6/20/16.
 */

/**
 * Represents a Controller that handles user input and passes
 * information to the model and view.
 */
public final class Controller implements IController {
  private final IMusicEditorModel model;
  private GuiView view;
  private final Timer timer;
  private final KeyboardHandler kh;
  private MouseHandler mh;

  /**
   * Constructor for a GUI Controller.
   * @param model the {@link IMusicEditorModel}.
   * @param view the {@link GuiView}.
   */
  public Controller(IMusicEditorModel model, GuiView view) {
    this.model = model;
    this.view = view;
    this.timer = new Timer();
    this.kh = createKeyboardHandler();
    this.mh = createStandardMouseHandler();
  }

/**
 * Creates and sets a keyboard listener for the view. In effect it creates snippets of code as
 * Runnable object, one for each time a key is typed, pressed and released, only for those that
 * the program needs.
 */
  @Override
  public KeyboardHandler createKeyboardHandler() {

    KeyboardHandler kh = new KeyboardHandler();

    kh.setKeyPressed(KeyEvent.VK_SPACE, () ->
      this.view.handleSpaceBar());

    kh.setKeyPressed(KeyEvent.VK_LEFT, () ->
      this.view.handleLeftArrow());

    kh.setKeyPressed(KeyEvent.VK_RIGHT, () ->
      this.view.handleRightArrow());

    kh.setKeyPressed(KeyEvent.VK_UP, () ->
      this.view.handleUpArrow());

    kh.setKeyPressed(KeyEvent.VK_DOWN, () ->
      this.view.handleDownArrow());

    kh.setKeyPressed(KeyEvent.VK_HOME, () ->
      this.view.handleHome());

    kh.setKeyPressed(KeyEvent.VK_END, () ->
      this.view.handleEnd());

    kh.setKeyReleased(KeyEvent.VK_ESCAPE, () ->
      this.view.handleEscape());

    kh.setKeyPressed(KeyEvent.VK_BACK_SPACE, () ->
      this.view.handleRemove());

    kh.setKeyPressed(KeyEvent.VK_A, () ->
      this.view.handleAKey());

    kh.setKeyPressed(KeyEvent.VK_SHIFT, () -> {
      this.view.removeMouseListener(mh);
      this.mh = createShiftMouseHandler();
    });

    kh.setKeyReleased(KeyEvent.VK_SHIFT, () -> {
      this.view.removeMouseListener(mh);
      this.mh = createStandardMouseHandler();
    });

    view.addKeyListener(kh);

    return kh;
  }

  /**
   * Creates the standard {@link MouseHandler}, which handles mouse
   * inputs while no other special circumstances are occurring.
   * @return the standard {@link MouseHandler}.
   */
  @Override
  public MouseHandler createStandardMouseHandler() {

    MouseHandler mh = new MouseHandler();

    mh.setMouseClicked(MouseEvent.BUTTON1, () ->
      this.view.handleLeftClick());

    view.addMouseListener(mh);

    return mh;
  }

  /**
   * Creates the SHIFT {@link MouseHandler}, which handles mouse
   * inputs while the SHIFT key is being held down.
   * @return the SHIFT {@link MouseHandler}.
   */
  @Override
  public MouseHandler createShiftMouseHandler() {

    MouseHandler mh = new MouseHandler();

    mh.setMouseClicked(MouseEvent.BUTTON1, () ->
      this.view.handleShiftLeftClick());

    view.addMouseListener(mh);

    return mh;
  }

  @Override
  public void render() {

    if (model instanceof MusicEditorModelRepeats)
      view.render(new ModelToViewRepeats(model));
    else
      view.render(new ModelToView(model));

    this.timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        if (!view.getMTV().isPaused()) {
          view.update();
        }
        if (CompositeViewImpl.currentBeat >= model.getLengthInBeats()) {
          timer.cancel();
          try {
            TimeUnit.SECONDS.sleep(2);
            System.exit(0);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }, 0, model.getTempo() / 8000);
  }

  /**
   * Factory class for creating different types of {@link GuiView}s.
   * A user can create either a {@link CompositeViewImpl} or a {@link GuiViewImpl}.
   */
  public static final class ViewFactory {

    public static GuiView constructView(String view) {
      switch (view) {
        case "composite":
          return new CompositeViewImpl(new GuiViewImpl(), new MidiViewRepeat());
        case "visual":
          return new GuiViewImpl();
        default:
          throw new IllegalArgumentException("Invalid view type.");
      }
    }
  }
}