package cs3500.music.model;

import java.util.List;
import java.util.ArrayList;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joeydonovan on 6/9/16.
 */
public class MusicEditorModelTest {

  private IMusicEditorModel mem = new MusicEditorModel(4, 120);
  private INote note1 = new Note(0, 4, 0, 4);
  private INote note2 = new Note(4, 4, 0, 4);
  private INote note3 = new Note(2, 8, 6, 4);

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMeasure() {
    IMusicEditorModel m = new MusicEditorModel(0, 120);
  }

  @Test
  public void testAddMultipleNotes() {
    int initialSize = mem.getTotalNotes();
    mem.addMultipleNotes(note1, note2, note3);
    int updatedSize = mem.getTotalNotes();
    assertEquals(0, initialSize);
    assertEquals(3, updatedSize);
  }

  @Test
  public void testToString() {
    mem.addNote(new Note(0, 4, 3, 8));
    assertEquals("   D#8\n" +
                 "0  X\n" +
                 "1  |\n" +
                 "2  |\n" +
                 "3  |\n",mem.toString());
  }

  @Test(expected = NullPointerException.class)
  public void testAddNullNote() {
    mem.addNote(null);
  }

  @Test
  public void testAddNote4BeatsLong() {
    int initialSounds = mem.getTotalNotes();
    mem.addNote(new Note(0, 4, 2, 7));
    int updatedSounds = mem.getTotalNotes();
    assertEquals(1 + initialSounds, updatedSounds);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNoteNotInThePiece() {
    mem.addMultipleNotes(new Note(5, 5, 5, 5), new Note(1, 1, 1, 1));
    mem.removeNote(new Note(3, 3, 3, 3));
  }

  @Test
  public void testRemoveNote() {
    mem.addMultipleNotes(new Note(5, 5, 5, 5), new Note(3, 3, 3, 3));
    int initialSize = mem.getTotalNotes();
    mem.removeNote(new Note(5, 5, 5, 5));
    int updatedSize = mem.getTotalNotes();
    assertNotEquals(initialSize, updatedSize);
  }

  @Test
  public void testRemoveMultipleNotes() {
    mem.addMultipleNotes(note1, note2, note3);
    assertEquals(3, mem.getTotalNotes());
    mem.removeMultipleNotes(note1, note2, note3);
    assertEquals(0, mem.getTotalNotes());
  }

  @Test(expected = NullPointerException.class)
  public void testRemoveNullNote() {
    mem.removeNote(null);
  }

  @Test
  public void testUpdateHighestValueAfterRemoval() {
    mem.addMultipleNotes(new Note(0, 4, 4, 4), new Note(0, 4, 5, 4),
            new Note(0, 4, 4, 6));
    int initialHighestVal = mem.getHighestNote();
    mem.removeNote(new Note(0, 4, 4, 6));
    int updatedHighestVal = mem.getHighestNote();
    assertNotEquals(initialHighestVal, updatedHighestVal);
  }

  @Test
  public void testUpdateLowestValueAfterRemoval() {
    mem.addMultipleNotes(new Note(0, 4, 4, 4), new Note(0, 4, 5, 4),
            new Note(0, 4, 4, 6));
    int initialLowestVal = mem.getLowestNote();
    mem.removeNote(new Note(0, 4, 4, 4));
    int updateLowestVal = mem.getLowestNote();
    assertNotEquals(initialLowestVal, updateLowestVal);
  }

  @Test
  public void testMoveNote() {
    mem.addMultipleNotes(note1, note2, note3);
    int initialStartBeat = note3.getStartBeat();
    int initialEndBeat = note3.getEndBeat();
    mem.moveNote(note3, 4);
    int updatedStartBeat = note3.getStartBeat();
    int updatedEndBeat = note3.getEndBeat();
    assertEquals(initialEndBeat - initialStartBeat, updatedEndBeat - updatedStartBeat);
    assertNotEquals(initialStartBeat, updatedStartBeat);
    assertNotEquals(initialEndBeat, updatedEndBeat);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveNoteInvalidBeats() {
    mem.addMultipleNotes(note1, note2, note3);
    mem.moveNote(note2, -10);
  }

  @Test(expected = NullPointerException.class)
  public void testMoveNullSound() {
    mem.moveNote(null, 0);
  }

  @Test
  public void testGetNotesAtBeat() {
    mem.addMultipleNotes(note1, note2);
    List<INote> notesAtBeat = new ArrayList<>();
    notesAtBeat.add(note1);
    assertEquals(notesAtBeat, mem.getNotesAtBeat(0));
    mem.removeNote(note1);
    assertEquals(new ArrayList<>(), mem.getNotesAtBeat(0));
  }

  @Test
  public void testUpdateHighestNoteToZeroAfterOneSoundRemoved() {
    mem.addNote(note1);
    assertEquals(60, mem.getHighestNote());
    mem.removeNote(note1);
    assertEquals(0, mem.getHighestNote());
  }

  @Test
  public void testUpdateLowestNoteTo127AfterOneSoundRemoved() {
    mem.addNote(note2);
    assertEquals(60, mem.getLowestNote());
    mem.removeNote(note2);
    assertEquals(127, mem.getLowestNote());
  }

  @Test
  public void testHighestAndLowestNotesRemainTheSame() {
    mem.addMultipleNotes(note1, note2, note3);
    assertEquals(60, mem.getLowestNote());
    assertEquals(66, mem.getHighestNote());
    mem.removeNote(note2);
    assertEquals(60, mem.getLowestNote());
    assertEquals(66, mem.getHighestNote());
  }

  @Test
  public void testCombineMusicalPieces() {
    mem.addMultipleNotes(new Note(0, 4, 10, 0), new Note(0, 4, 3, 1), new Note(0, 4, 7, 1),
            new Note(0, 4, 2, 1));
    int initialSize = mem.getTotalNotes();
    IMusicEditorModel mem2 = new MusicEditorModel(4, 120);
    mem2.addNote(new Note(0, 12, 3, 0));
    mem.combineMusicalPieces(mem2);
    int updatedSize = mem.getTotalNotes();
    assertEquals(initialSize + 1, updatedSize);
  }

  @Test
  public void testAppendMusicalPieces() {
    mem.addMultipleNotes(new Note(0, 4, 10, 0), new Note(0, 4, 3, 1), new Note(0, 4, 7, 1),
            new Note(0, 4, 2, 2));
    int initialLength = mem.getLengthInBeats();
    IMusicEditorModel mem2 = new MusicEditorModel(4, 120);
    mem2.addMultipleNotes(new Note(0, 4, 10, 0), new Note(0, 4, 3, 1), new Note(0, 4, 7, 1),
            new Note(0, 4, 2, 2));
    mem.appendMusicalPieces(mem2);
    int updatedLength = mem.getLengthInBeats();
    assertNotEquals(initialLength, updatedLength);
  }

  @Test
  public void testToStringNoNotesInEditor() {
    assertEquals("There are no current notes in the music editor.", mem.toString());
  }

  @Test
  public void testGetMeasureLength() {
    assertEquals(4, mem.getMeasureLength());
  }

  @Test
  public void testAddDuplicateSounds() {
    mem.addMultipleNotes(note1, note1);
    assertEquals(1, mem.getTotalNotes());
    mem.addNote(note2);
    assertEquals(2, mem.getTotalNotes());
  }

  @Test
  public void testCreateEmptyModel() {
    IMusicEditorModel model = new MusicEditorModel(4, 120);
    assertEquals(0, model.getTotalNotes());
  }

  @Test
  public void testCreateMelody() {
    mem.addMultipleNotes(new Note(0, 4, 0, 5), new Note(4, 2, 1, 5),
            new Note(6, 2, 4, 6));
    for (int i = 0; i < mem.getLengthInBeats(); i++) {
      assertEquals(1, mem.getNotesAtBeat(i).size());
    }
  }

  @Test
  public void testModelContainsNote() {
    mem.addMultipleNotes(note1, note2, note3);
    assertEquals(true, mem.getNotes().contains(note1));
    assertEquals(true, mem.getNotes().contains(note2));
    assertEquals(true, mem.getNotes().contains(note3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTempo() {
    IMusicEditorModel model = new MusicEditorModel(4, 0);
  }

}