package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Created by joeydonovan on 6/22/16.
 */

/**
 * Represents a Composite View for an {@link cs3500.music.model.IMusicEditorModel}.
 * The Composite View consists of a {@link GuiView} and a {@link MidiViewImpl}.
 * The two views run simultaneously, and the notes played through the MIDI should
 * correspond with the ticker on the GUI.
 */
public class CompositeViewImpl implements GuiView {
  private final GuiView gui;
  private final MidiViewImpl midi;
  private IModelToView mtv;
  public static int currentBeat;

  /**
   * Constructor for a Composite View.
   * @param gui the GUI that will be shown to the client.
   * @param midi the MIDI that will play the musical piece.
   */
  public CompositeViewImpl(GuiView gui, MidiViewImpl midi) {
    this.gui = gui;
    this.midi = midi;
    currentBeat = (int) midi.getSequencer().getTickPosition();
  }

  /**
   * Renders a Composite View. By doing this, both a MIDI view and a
   * GUI view are rendered simultaneously.
   * @param mtv the model to be shown in the view.
   */
  @Override
  public void render(IModelToView mtv) {
    this.mtv = mtv;
    gui.render(mtv);
    midi.render(mtv);
  }

  /**
   * Updates the state of the Composite View.
   * The current beat is synced with the MIDI player, and the GUI updates
   * its position if it is required.
   */
  @Override
  public void update() {
    CompositeViewImpl.currentBeat = (int) midi.getSequencer().getTickPosition();
    gui.update();
    midi.update();
  }

  @Override
  public IModelToView getMTV() {
    return mtv;
  }

  @Override
  public void handleSpaceBar() {
    if (mtv.isPaused()) {
      mtv.setIsPaused(false);
      play();
    }
    else {
      mtv.setIsPaused(true);
      pause();
    }
  }

  @Override
  public void addMouseListener(MouseListener mouseListener) {
    this.gui.addMouseListener(mouseListener);
  }

  @Override
  public void removeMouseListener(MouseListener mouseListener) {
    this.gui.removeMouseListener(mouseListener);
  }

  @Override
  public void handleLeftArrow() {
    gui.handleLeftArrow();
  }

  @Override
  public void handleRightArrow() {
    gui.handleRightArrow();
  }

  @Override
  public void handleUpArrow() {
    gui.handleUpArrow();
  }

  @Override
  public void handleDownArrow() {
    gui.handleDownArrow();
  }

  @Override
  public void handleHome() {
    gui.handleHome();
  }

  @Override
  public void handleEnd() {
    gui.handleEnd();
  }

  @Override
  public void handleEscape() {
    gui.handleEscape();
  }

  /**
   * Handles the pressing of the backspace button by a client. The Composite View
   * refreshes the MIDI sequencer and delegates control to the GUI view to do what
   * is necessary to update the GUI.
   */
  @Override
  public void handleRemove() {
    gui.handleRemove();
    midi.refreshMIDI(mtv);
  }

  /**
   * Handles the pressing of the 'A' button by a client. The Composite View
   * adds a new {@link cs3500.music.model.INote} to the MIDI if one has been added,
   * and updates the GUI accordingly.
   */
  @Override
  public void handleAKey() {
    gui.handleAKey();
    midi.addToMIDI(mtv);
  }

  @Override
  public void handleLeftClick() {
    gui.handleLeftClick();
  }

  @Override
  public void handleShiftLeftClick() {
    gui.handleShiftLeftClick();
  }

  @Override
  public void addKeyListener(KeyListener keyListener) {
    gui.addKeyListener(keyListener);
  }

  /**
   * Pauses the Composite View by pausing the MIDI sequencer.
   */
  private void pause() {
    if (midi.getSequencer().isRunning())
      midi.getSequencer().stop();
  }

  /**
   * Plays the Composite View by playing/resuming the MIDI sequencer.
   */
  private void play() {
    if (!midi.getSequencer().isRunning()) {
      mtv.getSelected().clear();
      midi.getSequencer().start();
      midi.getSequencer().setTempoInMPQ(mtv.getTempo());
    }
  }
}
