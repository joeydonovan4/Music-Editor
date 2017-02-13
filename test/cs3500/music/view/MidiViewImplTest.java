package cs3500.music.view;

import org.junit.Test;

import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.util.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by joeydonovan on 6/18/16.
 */
public class MidiViewImplTest {

  private StringBuilder log = new StringBuilder();
  private MidiViewImpl midi = new MidiViewImpl(log);

  @Test
  public void testMIDIAddNotes() {
    IMusicEditorModel model = new MusicEditorModel(4, 1000);
    model.addMultipleNotes(new Note(0, 4, 5, 4), new Note(4, 4, 0, 4));

    ModelToView mtv = new ModelToView(model);
    midi.render(mtv);
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {

    }
    assertEquals("note OFF 0\n" +
            "note OFF 1\n" +
            "note OFF 2\n" +
            "note OFF 3\n" +
            "note OFF 4\n" +
            "note OFF 5\n" +
            "note OFF 6\n" +
            "note OFF 7\n" +
            "note OFF 8\n" +
            "note OFF 9\n" +
            "note OFF 10\n" +
            "note OFF 11\n" +
            "note OFF 12\n" +
            "note OFF 13\n" +
            "note OFF 14\n" +
            "note OFF 15\n" +
            "note ON 65\n" +
            "note OFF 65\n" +
            "note ON 60\n" +
            "note OFF 60\n" +
            "note OFF 123\n" +
            "note OFF 64\n" +
            "note OFF 121\n" +
            "note OFF 123\n" +
            "note OFF 64\n" +
            "note OFF 121\n" +
            "note OFF 123\n" +
            "note OFF 64\n" +
            "note OFF 121\n" +
            "note OFF 123\n" +
            "note OFF 64\n" +
            "note OFF 121\n" +
            "note OFF 123\n" +
            "note OFF 64\n" +
            "note OFF 121\n" +
            "note OFF 123\n" +
            "note OFF 64\n" +
            "note OFF 121\n" +
            "note OFF 123\n" +
            "note OFF 64\n" +
            "note OFF 121\n" +
            "note OFF 123\n" +
            "note OFF 64\n" +
            "note OFF 121\n" +
            "note OFF 123\n" +
            "note OFF 64\n" +
            "note OFF 121\n" +
            "note OFF 123\n" +
            "note OFF 64\n" +
            "note OFF 121\n" +
            "note OFF 123\n" +
            "note OFF 64\n" +
            "note OFF 121\n" +
            "note OFF 123\n" +
            "note OFF 64\n" +
            "note OFF 121\n" +
            "note OFF 123\n" +
            "note OFF 64\n" +
            "note OFF 121\n" +
            "note OFF 123\n" +
            "note OFF 64\n" +
            "note OFF 121\n" +
            "note OFF 123\n" +
            "note OFF 64\n" +
            "note OFF 121\n" +
            "note OFF 123\n" +
            "note OFF 64\n" +
            "note OFF 121\n" +
            "note OFF 15\n" +
            "note OFF 0\n" +
            "note OFF 64\n", log.toString());
  }

  @Test
  public void testEmptySong() {
    IMusicEditorModel model = new MusicEditorModel(4, 180000);
    ModelToView mtv = new ModelToView(model);
    midi.render(mtv);

    assertEquals("", log.toString());
  }

  @Test
  public void testMIDIMaryLittleLamb() {
    CompositionBuilder<IMusicEditorModel> builder = new MusicEditorModel.MusicEditorBuilder();
    try {
      ModelToView mtv = new ModelToView(MusicReader.parseFile(
              new FileReader("mary-little-lamb.txt"), builder));
      midi.render(mtv);
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {

      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    assertEquals("note OFF 0\n" +
            "note OFF 1\n" +
            "note OFF 2\n" +
            "note OFF 3\n" +
            "note OFF 4\n" +
            "note OFF 5\n" +
            "note OFF 6\n" +
            "note OFF 7\n" +
            "note OFF 8\n" +
            "note OFF 9\n" +
            "note OFF 10\n" +
            "note OFF 11\n" +
            "note OFF 12\n" +
            "note OFF 13\n" +
            "note OFF 14\n" +
            "note OFF 15\n" +
            "note ON 64\n" +
            "note ON 55\n", log.toString());
  }

  @Test
  public void testMystery1() {
    CompositionBuilder<IMusicEditorModel> builder = new MusicEditorModel.MusicEditorBuilder();
    try {
      ModelToView mtv = new ModelToView(MusicReader.parseFile(
              new FileReader("mystery-1.txt"), builder));
      midi.render(mtv);
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {

      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    assertEquals("note OFF 0\n" +
            "note OFF 1\n" +
            "note OFF 2\n" +
            "note OFF 3\n" +
            "note OFF 4\n" +
            "note OFF 5\n" +
            "note OFF 6\n" +
            "note OFF 7\n" +
            "note OFF 8\n" +
            "note OFF 9\n" +
            "note OFF 10\n" +
            "note OFF 11\n" +
            "note OFF 12\n" +
            "note OFF 13\n" +
            "note OFF 14\n" +
            "note OFF 15\n" +
            "note ON 76\n" +
            "note ON 66\n" +
            "note ON 50\n" +
            "note ON 42\n" +
            "note OFF 76\n" +
            "note OFF 66\n" +
            "note OFF 50\n", log.toString());
  }
}