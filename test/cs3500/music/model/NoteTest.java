package cs3500.music.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joeydonovan on 6/9/16.
 */
public class NoteTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidOctave() {
    INote note = new Note(0, 2, 0, -5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPitch() {
    INote note = new Note(0, 2, -1, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBeats() {
    INote note = new Note(0, -1, 0, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartBeat() {
    INote note = new Note(-1, 2, 0, 5);
  }

  @Test
  public void testGetPitch() {
    INote note = new Note(1, 2, 3, 4);
    assertEquals(3, note.getPitch());
  }

  @Test
  public void testSetPitch() {
    INote note = new Note(3, 4, 5, 6);
    int initialPitch = note.getPitch();
    note.setPitch(8);
    int updatedPitch = note.getPitch();
    assertNotEquals(initialPitch, updatedPitch);
  }

  @Test
  public void testGetOctave() {
    INote note = new Note(5, 1, 8, 3);
    assertEquals(3, note.getOctave());
  }

  @Test
  public void testSetOctave() {
    INote note = new Note(9, 8, 2, 7);
    int initialOctave = note.getOctave();
    note.setOctave(5);
    int updatedOctave = note.getOctave();
    assertNotEquals(initialOctave, updatedOctave);
  }

  @Test
  public void testToString() {
    INote note = new Note(6, 1, 0, 2);
    assertEquals("C2", note.toString());
  }

  @Test
  public void testNoteEquals() {
    INote note1 = new Note(1, 2, 3, 4);
    INote note2 = new Note(1, 2, 3, 4);
    assertEquals(true, note1.equals(note2));
  }

  @Test
  public void testNoteNotEquals() {
    INote note1 = new Note(4, 3, 3, 4);
    INote note2 = new Note(1, 2, 3, 4);
    assertEquals(false, note1.equals(note2));
  }

  @Test
  public void testGetValue() {
    INote note = new Note(8, 6, 4, 2);
    assertEquals(40, note.getValue());
  }

  @Test
  public void testGetStartBeat() {
    INote note = new Note(1, 7, 9, 3);
    assertEquals(1, note.getStartBeat());
  }

  @Test
  public void testSetStartBeat() {
    INote note = new Note(8, 1, 9, 2);
    int initialStartBeat = note.getStartBeat();
    note.setStartBeat(3);
    int updatedStartBeat = note.getStartBeat();
    assertNotEquals(initialStartBeat, updatedStartBeat);
  }

  @Test
  public void testGetBeats() {
    INote note = new Note(6, 1, 2, 9);
    assertEquals(1, note.getBeats());
  }

  @Test
  public void testSetBeats() {
    INote note = new Note(7, 8, 1, 9);
    int initialBeats = note.getBeats();
    note.setBeats(2);
    int updatedBeats = note.getBeats();
    assertNotEquals(initialBeats, updatedBeats);
  }

  @Test
  public void testGetEndBeat() {
    INote note = new Note(8, 4, 4, 2);
    assertEquals(11, note.getEndBeat());
  }

  @Test
  public void testSetEndBeat() {
    INote note = new Note(9, 6, 7, 2);
    int initialEndBeat = note.getEndBeat();
    note.setEndBeat(25);
    int updatedEndBeat = note.getEndBeat();
    assertNotEquals(initialEndBeat, updatedEndBeat);
  }
}