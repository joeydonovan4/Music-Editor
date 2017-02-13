package cs3500.music.model;

import java.util.List;

/**
 * Created by joeydonovan on 6/6/16.
 */

/**
 * Interface that represents a Music Editor and its functionality.
 */
public interface IMusicEditorModel {

  /**
   * Adds a {@link INote} to the {@code IMusicEditorModel}.
   * @param n the {@code note} being added to the {@code IMusicEditorModel}.
   */
  void addNote(INote n);

  /**
   * Adds a variable amount of {@link INote}s to the {@code IMusicEditorModel}.
   * @param notes the {@code note}s being added to the {@code IMusicEditorModel}.
   */
  void addMultipleNotes(INote...notes);

  /**
   * Removes a {@link INote} from the {@code IMusicEditorModel}.
   * @param n the {@code note} that is being removed.
   */
  void removeNote(INote n);

  /**
   * Removes a variable amount of {@link INote}s from the {@code IMusicEditorModel}.
   * @param notes the {@code note}s being removed from the {@code IMusicEditorModel}.
   */
  void removeMultipleNotes(INote...notes);

  /**
   * Moves a {@link INote} in the {@code IMusicEditorModel} by a given
   * number of {@code beat}s.
   * @param n the note being moved in the editor.
   * @param beats the amount of beats the note is being moved.
   */
  void moveNote(INote n, int beats);

  /**
   * Gets list of {@link INote}s at the given {@code beat}.
   * @param beat the {@code beat} that contains the {@link INote}s.
   * @return the list of {@code note}s at the given {@code beat}.
   */
  List<INote> getNotesAtBeat(int beat);

  /**
   * Gets set of all {@link INote}s in the {@code IMusicEditorModel}.
   * @return a list of all {@code note}s, sorted by {@code octave}
   * and {@link Pitch} value from highest to lowest.
   */
  List<INote> getNotes();

  /**
   * Gets the {@code tempo} of the {@code IMusicEditorModel}.
   * @return
   */
  int getTempo();

  /**
   * Gets the length of the measure of the {@code IMusicEditorModel}.
   * @return the length of the measure.
   */
  int getMeasureLength();

  /**
   * Gets the highest {@link INote} in the {@code IMusicEditorModel}.
   * @return the highest {@code note} in the {@code IMusicEditorModel},
   * based on {@code octave} and {@link Pitch}.
   */
  int getHighestNote();

  /**
   * Gets the lowest {@link INote} in the {@code IMusicEditorModel}.
   * @return the lowest {@code note} in the {@code IMusicEditorModel},
   * based on {@code octave} and {@link Pitch}.
   */
  int getLowestNote();

  /**
   * Gets the size of the {@code IMusicEditorModel} in terms of the number of
   * {@link INote}s in the {@code IMusicEditorModel}.
   * @return the size of the {@code IMusicEditorModel}.
   */
  int getTotalNotes();

  /**
   * Gets the length of the {@code IMusicEditorModel} in terms of total beats.
   * @return total beats.
   */
  int getLengthInBeats();

  /**
   * Combines the two musical pieces together to be played simultaneously.
   * @param model the musical piece being combined with the current one.
   */
  void combineMusicalPieces(IMusicEditorModel model);

  /**
   * Appends two musical pieces to be played consecutively..
   * @param model the {@code IMusicEditorModel} to be played consecutively with the
   *          current {@code IMusicEditorModel}.
   */
  void appendMusicalPieces(IMusicEditorModel model);
}
