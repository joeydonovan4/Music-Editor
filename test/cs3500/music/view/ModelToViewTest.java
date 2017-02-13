package cs3500.music.view;

import org.junit.Test;

import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.INote;
import cs3500.music.model.Note;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joeydonovan on 6/19/16.
 */
public class ModelToViewTest {

  private IMusicEditorModel model = new MusicEditorModel(4, 180000);
  private IModelToView mtv = new ModelToView(model);

  @Test
  public void testIsPausedAtBeginning() {
    assert(!mtv.isPaused());
  }

  @Test
  public void testSetPaused() {
    assert(!mtv.isPaused());
    mtv.setIsPaused(true);
    assert(mtv.isPaused());
  }

  @Test
  public void testSelected() {
    List<INote> selected = new ArrayList<>();
    assertEquals(selected, mtv.getSelected());
    mtv.addSelected(new Note(0, 4, 0, 4));
    selected.add(new Note(0, 4, 0, 4));
    assertEquals(selected, mtv.getSelected());
  }

  @Test
  public void testGetMeasureLength() {
    assertEquals(4, mtv.getMeasureLength());
  }

  @Test
  public void testGetNotesAtBeatNoNotes() {
    assertEquals(new ArrayList<>(), mtv.getNotesAtBeat(0));
  }

  @Test
  public void testGetNotesAtBeatHasNotes() {
    model.addNote(new Note(0, 4, 0, 4));
    List<INote> notes = new ArrayList<>();
    notes.add(new Note(0, 4, 0, 4));
    for (int i = 0; i < 4; i++) {
      assertEquals(notes, mtv.getNotesAtBeat(0));
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNotesAtInvalidBeat() {
    mtv.getNotesAtBeat(-5);
  }

  @Test
  public void testGetLengthInBeats() {
    assertEquals(0, mtv.getLengthInBeats());
    model.addNote(new Note(0, 4, 0, 4));
    assertEquals(4, mtv.getLengthInBeats());
  }

  @Test
  public void testGetHighestNote() {
    assertEquals(0, mtv.getHighestNote());
    model.addNote(new Note(0, 4, 0, 4));
    assertEquals(60, mtv.getHighestNote());
  }

  @Test
  public void testHighestNoteUpdatesAfterRemoval() {
    assertEquals(0, mtv.getHighestNote());
    model.addMultipleNotes(new Note(0, 4, 0, 4), new Note(0, 4, 0, 8));
    assertEquals(108, mtv.getHighestNote());
    model.removeNote(new Note(0, 4, 0, 8));
    assertEquals(60, mtv.getHighestNote());
  }

  @Test
  public void testGetLowestNote() {
    assertEquals(127, mtv.getLowestNote());
    model.addNote(new Note(0, 4, 0, 4));
    assertEquals(60, mtv.getLowestNote());
  }

  @Test
  public void testLowestNoteUpdatesAfterRemoval() {
    assertEquals(127, mtv.getLowestNote());
    model.addMultipleNotes(new Note(0, 4, 0, 4), new Note(0, 4, 0, 8));
    assertEquals(60, mtv.getLowestNote());
    model.removeNote(new Note(0, 4, 0, 4));
    assertEquals(108, mtv.getLowestNote());
  }

  @Test
  public void testOnlyNoteInModelIsBothHighestAndLowest() {
    assertEquals(0, mtv.getHighestNote());
    assertEquals(127, mtv.getLowestNote());
    model.addNote(new Note(0, 4, 0, 4));
    assertEquals(60, mtv.getLowestNote());
    assertEquals(60, mtv.getHighestNote());
  }

  @Test
  public void testGetNotes() {
    List<INote> notes = new ArrayList<>();
    assertEquals(notes, mtv.getNotes());
    model.addNote(new Note(0, 4, 0, 4));
    notes.add(new Note(0, 4, 0, 4));
    assertEquals(notes, mtv.getNotes());
  }

  @Test
  public void testGetMultipleNotes() {
    List<INote> notes = new ArrayList<>();
    assertEquals(notes, mtv.getNotes());
    model.addMultipleNotes(new Note(0, 4, 0, 4), new Note(10, 4, 0, 6),
            new Note(6, 4, 0, 2));
    notes.add(new Note(10, 4, 0, 6));
    notes.add(new Note(0, 4, 0, 4));
    notes.add(new Note(6, 4, 0, 2));
    assertEquals(notes, mtv.getNotes());
  }

  @Test
  public void testGetTempo() {
    assertEquals(180000, mtv.getTempo());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTempo() {
    IMusicEditorModel model = new MusicEditorModel(4, 0);
  }

  @Test
  public void testAddNote() {
    assertEquals(0, mtv.getNotes().size());
    assertEquals(0, mtv.getAdded().size());
    mtv.addNote(new Note(0, 4, 0, 4));
    assertEquals(1, mtv.getNotes().size());
    assertEquals(1, mtv.getAdded().size());
  }

  @Test
  public void testAddMultipleNotes() {
    assertEquals(0, mtv.getNotes().size());
    assertEquals(0, mtv.getAdded().size());
    mtv.addMultipleNotes(new Note(0, 4, 0, 4), new Note(4, 4, 0, 4), new Note(4, 4, 4, 4));
    assertEquals(3, mtv.getNotes().size());
    assertEquals(3, mtv.getAdded().size());
  }

  @Test
  public void testAddMultipleNotesToModelWithNotesAlready() {
    IMusicEditorModel model = new MusicEditorModel(4, 120000);
    model.addMultipleNotes(new Note(0, 4, 0, 4), new Note(4, 4, 4, 4));
    IModelToView mtv = new ModelToView(model);
    assertEquals(2, mtv.getNotes().size());
    assertEquals(0, mtv.getAdded().size());
    mtv.addMultipleNotes(new Note(8, 8, 8, 8), new Note(7, 7, 7, 7));
    assertEquals(4, mtv.getNotes().size());
    assertEquals(2, mtv.getAdded().size());
  }

  @Test
  public void testRemoveNote() {
    model.addMultipleNotes(new Note(1, 1, 1, 1), new Note(2, 2, 2, 2));
    assertEquals(2, mtv.getNotes().size());
    assertEquals(0, mtv.getRemoved().size());
    mtv.removeNote(new Note(1, 1, 1, 1));
    assertEquals(1, mtv.getNotes().size());
    assertEquals(1, mtv.getRemoved().size());
  }

  @Test
  public void testRemoveMultipleNotes() {
    model.addMultipleNotes(new Note(1, 1, 1, 1), new Note(2, 2, 2, 2), new Note(3, 3, 3, 3),
            new Note(4, 4, 4, 4), new Note(5, 5, 5, 5));
    assertEquals(5, mtv.getNotes().size());
    assertEquals(0, mtv.getRemoved().size());
    mtv.removeMultipleNotes(new Note(1, 1, 1, 1), new Note(3, 3, 3, 3), new Note(5, 5, 5, 5));
    assertEquals(2, mtv.getNotes().size());
    assertEquals(3, mtv.getRemoved().size());
  }

  @Test
  public void testMoveNote() {
    model.addNote(new Note(1, 1, 1, 1));
    assertEquals(1, mtv.getNotesAtBeat(1).size());
    assertEquals(0, mtv.getNotesAtBeat(11).size());
    mtv.moveNote(new Note(1, 1, 1, 1), 10);
    assertEquals(0, mtv.getNotesAtBeat(1).size());
    assertEquals(1, mtv.getNotesAtBeat(11).size());
  }

  @Test
  public void testGetTotalNotes() {
    mtv.addMultipleNotes(new Note(1, 1, 1, 1), new Note(2, 2, 2, 2), new Note(3, 3, 3, 3),
            new Note(4, 4, 4, 4), new Note(5, 5, 5, 5), new Note(6, 6, 6, 6),
            new Note(7, 7, 7, 7), new Note(8, 8, 8, 8), new Note(9, 9, 9, 9));
    assertEquals(9, mtv.getTotalNotes());
  }

  @Test
  public void testCombineMusicalPieces() {
    IMusicEditorModel model2 = new MusicEditorModel(4, 100000);
    model2.addMultipleNotes(new Note(1, 1, 1, 1), new Note(2, 2, 2, 2));
    model.addMultipleNotes(new Note(3, 3, 3, 3), new Note(4, 4, 4, 4));
    assertEquals(2, mtv.getNotes().size());
    mtv.combineMusicalPieces(model2);
    assertEquals(4, mtv.getNotes().size());
  }

  @Test
  public void testAppendMusicalPieces() {
    IMusicEditorModel model2 = new MusicEditorModel(4, 100000);
    model2.addMultipleNotes(new Note(1, 2, 1, 1), new Note(2, 2, 2, 2));
    model.addMultipleNotes(new Note(3, 3, 3, 3), new Note(4, 4, 4, 4));
    assertEquals(8, mtv.getLengthInBeats());
    mtv.appendMusicalPieces(model2);
    assertEquals(16, mtv.getLengthInBeats());
  }

}