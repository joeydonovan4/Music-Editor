package cs3500.music.model;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Created by joeydonovan on 6/9/16.
 */
public class NoteComparatorTest {

  Comparator noteComp = new NoteComparator();

  @Test
  public void testComparatorLessThan() {
    INote note1 = new Note(5, 4, 3, 3);
    INote note2 = new Note(3, 4, 8, 8);
    assertEquals(1, noteComp.compare(note1, note2));
  }

  @Test
  public void testComparatorEqual() {
    INote note1 = new Note(3, 3, 3, 3);
    INote note2 = new Note(3, 3, 3, 3);
    assertEquals(0, noteComp.compare(note1, note2));
  }

  @Test
  public void testComparatorGreaterThan() {
    INote note1 = new Note(7, 5, 1, 8);
    INote note2 = new Note(7, 1, 4, 4);
    assertEquals(-1, noteComp.compare(note1, note2));
  }

  @Test
  public void testComparatorInSort() {
    INote note1 = new Note(5, 4, 3, 3);
    INote note2 = new Note(3, 4, 8, 8);
    INote note3 = new Note(1, 4, 1, 0);
    List<INote> unsorted = new ArrayList<>();
    unsorted.add(note1);
    unsorted.add(note2);
    unsorted.add(note3);
    Collections.sort(unsorted, noteComp);

    List<INote> sorted = new ArrayList<>();
    sorted.add(note2);
    sorted.add(note1);
    sorted.add(note3);
    assertEquals(unsorted, sorted);

  }

  @Test
  public void testTwoNotesSameValueDifferentBeat() {
    INote note1 = new Note(7, 7, 7, 7);
    INote note2 = new Note(20, 7, 7, 7);
    assertEquals(1, noteComp.compare(note1, note2));
  }

  @Test
  public void testTwoNotesSameValueDifferentBeat2() {
    INote note1 = new Note(20, 7, 7, 7);
    INote note2 = new Note(7, 7, 7, 7);
    assertEquals(-1, noteComp.compare(note1, note2));
  }

}