package cs3500.music.view;

import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.INote;
import cs3500.music.model.IRange;

import java.util.List;

/**
 * Created by joeydonovan on 6/23/16.
 */
public interface IModelToView extends IMusicEditorModel {

  /**
   * Gets state of the {@link IMusicEditorModel}.
   * @return true if {@link IMusicEditorModel} is paused. False otherwise.
   */
  boolean isPaused();

  /**
   * Sets the state of the {@link IMusicEditorModel}.
   * @param state the desired state.
   */
  void setIsPaused(boolean state);

  /**
   * Gets the selected {@link INote}s in the GUI.
   * @return a list of {@link INote}s.
   */
  List<INote> getSelected();

  /**
   * Adds an {@link INote} to the list of selected notes.
   * @param n the {@link INote} to be added.
   */
  void addSelected(INote n);

  /**
   * Gets the list of {@link INote}s that have been added to
   * the {@link IMusicEditorModel} by the client.
   * @return list of added notes.
   */
  List<INote> getAdded();

  /**
   * Gets the list of {@link INote}s that have been removed from
   * the {@link IMusicEditorModel} by the client.
   * @return list of removed notes.
   */
  List<INote> getRemoved();

  IRange getRepeatAt(int beat);

  boolean hasRepeatAt(int beat);

  boolean hasRepeatsLeft(IRange r);

  int getRespondingBeat(IRange r);

  void decrement(IRange r);

  List<IRange> getRepeats();

  List<IRange> getEndings();

  void setCurrentEnding(int ending);

  int getCurrentEnding();
}
