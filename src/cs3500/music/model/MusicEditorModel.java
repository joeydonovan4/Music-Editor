package cs3500.music.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.TreeSet;

import cs3500.music.util.CompositionBuilder;

/**
 * Created by joeydonovan on 6/10/16.
 */

/**
 * Represents a Music Editor. The instantiation of a music editor also represents
 * the creation of a musical piece that is being added to the editor.
 */
public class MusicEditorModel implements IMusicEditorModel {

  /**
   * Class invariants:
   * measureLength > 0
   * 0 <= highestNote < 128
   * 0 <= lowestNote < 128
   * totalNotes >= 0
   * lengthInBeats >= 0
   */
  private final int measureLength;
  private final int tempo; // represented as microseconds per beat.
  private int highestNote = 0, lowestNote = 127, totalNotes = 0, lengthInBeats = 0;
  private Map<Integer, List<INote>> notesAtBeat;
  private TreeSet<INote> notes;

  /**
   * Constructor
   * @param measureLength the length of the measure of the musical piece.
   * @param tempo the tempo of the musical piece.
   */
  public MusicEditorModel(int measureLength, int tempo) {
    if (measureLength < 1)
      throw new IllegalArgumentException("Invalid measure length.");
    if (tempo < 1)
      throw new IllegalArgumentException("Invalid tempo.");

    this.tempo = tempo;
    this.measureLength = measureLength;
    this.notesAtBeat = new HashMap<>();
    this.notes = new TreeSet<>(new NoteComparator());
  }

  /**
   * Constructor used for the builder. Includes a list of {@link INote}s that are
   * to be added to the music editor.
   * @param notes list of notes that are going to be added to the editor.
   * @param measureLength the length of the measure of the musical piece.
   * @param tempo the tempo of the musical piece.
   */
  public MusicEditorModel(List<INote> notes, int measureLength, int tempo) {
    this(measureLength, tempo);
    for (INote n : notes) {
      addNote(n);
    }
  }

  public void addNote(INote n) {
    Objects.requireNonNull(n);

    if (containsNote(n))
      return;

    for (int i = n.getStartBeat(); i <= n.getEndBeat(); i++) {
      notesAtBeat.putIfAbsent(i, new ArrayList<>());
      notesAtBeat.get(i).add(n);
    }
    isHighestOrLowestNote(n);
    notes.add(n);
    totalNotes++;
    if (n.getEndBeat() > lengthInBeats)
      setLengthInBeats(n.getEndBeat() + 1);
  }

  public void addMultipleNotes(INote...notes) {
    for (INote n : notes) {
      Objects.requireNonNull(n);
      addNote(n);
    }
  }

  public void removeNote(INote n) {
    Objects.requireNonNull(n);
    if (!containsNote(n))
      throw new IllegalArgumentException("Note is not in the music editor." +
              " Cannot remove a note that is not already there.");
    for (int i = n.getStartBeat(); i <= n.getEndBeat(); i++) {
      notesAtBeat.get(i).remove(n);
    }
    notes.remove(n);
    totalNotes--;
    removeEmptyBeats();

    if (n.getValue() == highestNote)
      updateHighestNote();
    if (n.getValue() == lowestNote)
      updateLowestNote();
  }

  public void removeMultipleNotes(INote...notes) {
    for (INote n : notes) {
      Objects.requireNonNull(n);
      removeNote(n);
    }
  }

  public void moveNote(INote n, int beats) {
    Objects.requireNonNull(n);
    if (n.getStartBeat() + beats < 0) {
      throw new IllegalArgumentException("Invalid number of beats. The note cannot" +
              " be moved to a negative starting beat.");
    }
    removeNote(n);

    n.setStartBeat(n.getStartBeat() + beats);
    n.setEndBeat(n.getEndBeat() + beats);

    addNote(n);
  }

  public List<INote> getNotes() {
    List<INote> tempArray = new ArrayList<>();
    tempArray.addAll(notes);

    return tempArray;
  }

  public int getMeasureLength() {
    return measureLength;
  }

  public List<INote> getNotesAtBeat(int beat) {
    if (beat < 0)
      throw new IllegalArgumentException("Invalid beat.");

    List<INote> tempArray = new ArrayList<>();

    if (notesAtBeat.containsKey(beat))
      tempArray.addAll(notesAtBeat.get(beat));

    return tempArray;
  }

  public int getHighestNote() {
    return highestNote;
  }

  public int getLowestNote() {
    return lowestNote;
  }

  public int getTotalNotes() {
    return totalNotes;
  }

  public void combineMusicalPieces(IMusicEditorModel model) {
    Objects.requireNonNull(model);

    for (INote n : model.getNotes())
      this.addNote(n);
  }

  public void appendMusicalPieces(IMusicEditorModel model) {
    Objects.requireNonNull(model);
    int lengthOfFirstModel = lengthInBeats;
    IMusicEditorModel temp = model;
    for (INote n : temp.getNotes()) {
      n.setStartBeat(n.getStartBeat() + lengthOfFirstModel + 1);
      n.setEndBeat(n.getEndBeat() + lengthOfFirstModel + 1);
      addNote(n);
    }
    int modelLength = temp.getLengthInBeats();
    this.lengthInBeats += modelLength;
  }

  public int getTempo() { return tempo; }

  public int getLengthInBeats() {
    return lengthInBeats;
  }

  /**
   * Represents the state of the music editor as a String.
   * @return a String of the current state of the music editor.
   */
  @Override
  public String toString() {
    if (notes.isEmpty())
      return "There are no current notes in the music editor.";

    int padding = Integer.toString(lengthInBeats).length();
    String model = String.format("%" + padding + "s", "");

    for (int i = lowestNote; i <= highestNote; i++) {
      Pitch pitch = Pitch.toPitch(i % 12);
      String pitchAndOctave = pitch.toString() + (i / 12 - 1);
      model += String.format("%" + 5 + "s", pitchAndOctave);
    }

    for (int i = 0; i < lengthInBeats; i++)
      model += String.format("\n%" + padding + "d ", i) + placeNotes(i);

    return model + "\n";
  }

  /**
   * Sets highest/lowest notes in the {@code IMusicEditorModel} if the given
   * {@link Note} is the highest or lowest {@link Note}.
   * @param n the {@code note} to be checked for highest or lowest.
   */
  private void isHighestOrLowestNote(INote n) {
    Objects.requireNonNull(n);
    if (n.getValue() > getHighestNote())
      highestNote = n.getValue();

    if (n.getValue() < getLowestNote())
      lowestNote = n.getValue();
  }

  /**
   * Places the notes in their corresponding column in the String output.
   * @param beat the row that is currently being placed.
   * @return the String of the beats in their corresponding column.
   */
  private String placeNotes(int beat) {
    int rowLength = (5 * (highestNote - lowestNote)) + 2;
    StringBuilder row = new StringBuilder(String.format("%" + rowLength + "s", ""));
    for (INote n : getNotesAtBeat(beat)) {
      int beatPlacement = (5 * (n.getValue() - lowestNote)) + 1;
      if (n.getStartBeat() == beat)
        row.setCharAt(beatPlacement, 'X');
      else
        row.setCharAt(beatPlacement, '|');
    }
    return row.toString();
  }

  /**
   * Checks if the music editor already contains the note.
   * @param n the note being checked for duplicates.
   * @return true if the music editor already contains the note, false otherwise.
   */
  private boolean containsNote(INote n) {
    Objects.requireNonNull(n);
    return notes.contains(n);
  }

  /**
   * Removes {@code beats} from the map of {@link Note}s if its value
   * is an empty list.
   */
  private void removeEmptyBeats() {
    Iterator<Map.Entry<Integer, List<INote>>> iterator = notesAtBeat.entrySet().iterator();
    while(iterator.hasNext()) {
      Map.Entry<Integer, List<INote>> entry = iterator.next();
      if (entry.getValue().isEmpty())
        iterator.remove();
    }
  }

  /**
   * Updates the highest {@link Note} in the music editor.
   */
  private void updateHighestNote() {
    try {
      INote newHighest = notes.first();
      highestNote = newHighest.getValue();
    }
    catch (NoSuchElementException e) {
      highestNote = 0;
    }
  }

  /**
   * Updates the lowest {@link Note} in the music editor.
   */
  private void updateLowestNote() {
    try {
      INote newLowest = notes.last();
      lowestNote = newLowest.getValue();
    }
    catch (NoSuchElementException e) {
      lowestNote = 127;
    }
  }

  /**
   * Sets the length of the {@code MusicEditorModel}, in terms of beats elapsed.
   * @param length the given length.
   */
  private void setLengthInBeats(int length) {
    lengthInBeats = length;
  }

  /**
   * Represents a Builder class for a {@link IMusicEditorModel}.
   */
  public static final class MusicEditorBuilder implements CompositionBuilder<IMusicEditorModel> {
    private int tempo;
    private final int measureLength = 4;
    private List<INote> notes = new ArrayList<>();
    private List<IRange> repeats = new ArrayList<>();
    private List<IRange> endings = new ArrayList<>();
    private boolean repeat = false;

    public IMusicEditorModel build() {
      if (!repeat)
        return new MusicEditorModel(notes, measureLength, tempo);
      else
        return new MusicEditorModelRepeats(notes, repeats, endings, measureLength, tempo);
    }

    public CompositionBuilder<IMusicEditorModel> setTempo(int tempo)
            throws IllegalArgumentException {
      if (tempo < 1)
        throw new IllegalArgumentException("Invalid tempo");

      this.tempo = tempo;
      return this;
    }

    public CompositionBuilder<IMusicEditorModel> addNote(int start, int end,
                                                         int instrument, int pitch, int volume)
            throws IllegalArgumentException {
      if (pitch > 127 || pitch < 0)
        throw new IllegalArgumentException("Invalid pitch.");

      notes.add(new Note(start, end - start, pitch % 12, (pitch / 12) - 1, instrument, volume));

      return this;
    }

    public CompositionBuilder<IMusicEditorModel> addRepeat(int startBeat, int endBeat)
      throws IllegalArgumentException {
      if (startBeat < 0 || endBeat < 0)
        throw new IllegalArgumentException("Invalid repeat.");

      repeat = true;
      repeats.add(new Range(startBeat, endBeat, 1));

      return this;
    }

    public CompositionBuilder<IMusicEditorModel> addEnding(int startBeat, int endBeat)
      throws IllegalArgumentException {
      if (startBeat < 0 || endBeat < 0)
        throw new IllegalArgumentException("Invalid ending.");

      endings.add(new Range(startBeat, endBeat, 1));

      return this;
    }
  }
}