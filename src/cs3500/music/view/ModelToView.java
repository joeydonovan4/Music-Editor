package cs3500.music.view;

import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.INote;
import cs3500.music.model.IRange;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by joeydonovan on 6/14/16.
 */

/**
 * Represents a Model. Used by a View to get the information
 * it needs to render itself.
 */
public class ModelToView implements IModelToView {
  private IMusicEditorModel model;
  private boolean isPaused;
  private List<INote> selected, added, removed;

  /**
   * Constructor for a ModelToView.
   * @param model the model that is being presented in the view.
   */
  public ModelToView(IMusicEditorModel model) {
    this.model = model;
    this.isPaused = false;
    this.selected = new ArrayList<>();
    this.added = new ArrayList<>();
    this.removed = new ArrayList<>();
  }

  @Override
  public boolean isPaused() {
    return isPaused;
  }

  @Override
  public void setIsPaused(boolean state) {
    isPaused = state;
  }

  @Override
  public List<INote> getSelected() {
    return selected;
  }

  @Override
  public void addSelected(INote n) {
    selected.add(n);
  }

  @Override
  public int getMeasureLength() {
    return model.getMeasureLength();
  }

  @Override
  public List<INote> getNotesAtBeat(int beat) {
    return model.getNotesAtBeat(beat);
  }

  @Override
  public int getLengthInBeats() {
    return model.getLengthInBeats();
  }

  @Override
  public int getHighestNote() {
    return model.getHighestNote();
  }

  @Override
  public int getLowestNote() {
    return model.getLowestNote();
  }

  @Override
  public List<INote> getNotes() {
    return model.getNotes();
  }

  @Override
  public int getTempo() {
    return model.getTempo();
  }

  @Override
  public void addNote(INote n) {
    model.addNote(n);
    added.add(n);
  }

  @Override
  public void addMultipleNotes(INote...notes) {
    model.addMultipleNotes(notes);
    for (INote n : notes) {
      added.add(n);
    }
  }

  @Override
  public void removeNote(INote n) {
    model.removeNote(n);
    removed.add(n);
  }

  @Override
  public void removeMultipleNotes(INote...notes) {
    model.removeMultipleNotes(notes);
    for (INote n : notes) {
      removed.add(n);
    }
  }

  @Override
  public void moveNote(INote n, int beats) {
    model.moveNote(n, beats);
  }

  @Override
  public int getTotalNotes() {
    return model.getTotalNotes();
  }

  @Override
  public void combineMusicalPieces(IMusicEditorModel model) {
    this.model.combineMusicalPieces(model);
  }

  @Override
  public void appendMusicalPieces(IMusicEditorModel model) {
    this.model.appendMusicalPieces(model);
  }

  @Override
  public List<INote> getAdded() {
    return added;
  }

  @Override
  public List<INote> getRemoved() {
    return removed;
  }

  @Override
  public String toString() {
    return model.toString();
  }

  public IRange getRepeatAt(int beat) {
    return null;
  }

  public boolean hasRepeatAt(int beat) {
    return false;
  }

  public boolean hasRepeatsLeft(IRange r) {
    return false;
  }

  public int getRespondingBeat(IRange r) {
    return 0;
  }

  public void decrement(IRange r) {

  }

  public List<IRange> getRepeats() {
    return new ArrayList<>();
  }

  public List<IRange> getEndings() {
    return new ArrayList<>();
  }

  public void setCurrentEnding(int ending) {

  }

  public int getCurrentEnding() {
    return 1;
  }
}