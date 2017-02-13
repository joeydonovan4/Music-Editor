package cs3500.music.view;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;

import cs3500.music.model.Note;
import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;

/**
 * Created by joeydonovan on 6/19/16.
 */
public class ConsoleViewImplTest {

  private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
  private ConsoleViewImpl view = new ConsoleViewImpl();
  private IMusicEditorModel model = new MusicEditorModel(4, 180000);
  private ModelToView mtv = new ModelToView(model);

  @Test
  public void testEmptySong() {
    System.setOut(new PrintStream(outStream));
    view.render(mtv);
    assertEquals("There are no current notes in the music editor.", outStream.toString());
  }

  @Test
  public void testAddCoupleNotes() {
    System.setOut(new PrintStream(outStream));
    model.addMultipleNotes(new Note(0, 4, 0, 4), new Note(4, 4, 0, 4));
    view.render(mtv);
    assertEquals("    C4\n" +
            "0  X\n" +
            "1  |\n" +
            "2  |\n" +
            "3  |\n" +
            "4  X\n" +
            "5  |\n" +
            "6  |\n" +
            "7  |\n", outStream.toString());
  }

  @Test
  public void testOutputFromTextFile() {
    System.setOut(new PrintStream(outStream));
    CompositionBuilder<IMusicEditorModel> builder = new MusicEditorModel.MusicEditorBuilder();
    try {
      ModelToView mtv = new ModelToView(MusicReader.parseFile(
              new FileReader("mary-little-lamb.txt"), builder));
      view.render(mtv);
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    assertEquals("     E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   F4  " +
    "F#4   G4\n 0                 X                                            X               \n" +
    " 1                 |                                            |               \n" +
    " 2                 |                                  X                         \n" +
    " 3                 |                                  |                         \n" +
    " 4                 |                        X                                   \n" +
    " 5                 |                        |                                   \n" +
    " 6                 |                                  X                         \n" +
    " 7                                                    |                         \n" +
    " 8                 X                                            X               \n" +
    " 9                 |                                            |               \n" +
    "10                 |                                            X               \n" +
    "11                 |                                            |               \n" +
    "12                 |                                            X               \n" +
    "13                 |                                            |               \n" +
    "14                 |                                            |               \n" +
    "15                                                                              \n" +
    "16                 X                                  X                         \n" +
    "17                 |                                  |                         \n" +
    "18                 |                                  X                         \n" +
    "19                 |                                  |                         \n" +
    "20                 |                                  X                         \n" +
    "21                 |                                  |                         \n" +
    "22                 |                                  |                         \n" +
    "23                 |                                  |                         \n" +
    "24                 X                                            X               \n" +
    "25                 |                                            |               \n" +
    "26                                                                             X\n" +
    "27                                                                             |\n" +
    "28                                                                             X\n" +
    "29                                                                             |\n" +
    "30                                                                             |\n" +
    "31                                                                             |\n" +
    "32                 X                                            X               \n" +
    "33                 |                                            |               \n" +
    "34                 |                                  X                         \n" +
    "35                 |                                  |                         \n" +
    "36                 |                        X                                   \n" +
    "37                 |                        |                                   \n" +
    "38                 |                                  X                         \n" +
    "39                 |                                  |                         \n" +
    "40                 X                                            X               \n" +
    "41                 |                                            |               \n" +
    "42                 |                                            X               \n" +
    "43                 |                                            |               \n" +
    "44                 |                                            X               \n" +
    "45                 |                                            |               \n" +
    "46                 |                                            X               \n" +
    "47                 |                                            |               \n" +
    "48                 X                                  X                         \n" +
    "49                 |                                  |                         \n" +
    "50                 |                                  X                         \n" +
    "51                 |                                  |                         \n" +
    "52                 |                                            X               \n" +
    "53                 |                                            |               \n" +
    "54                 |                                  X                         \n" +
    "55                 |                                  |                         \n" +
    "56  X                                       X                                   \n" +
    "57  |                                       |                                   \n" +
    "58  |                                       |                                   \n" +
    "59  |                                       |                                   \n" +
    "60  |                                       |                                   \n" +
    "61  |                                       |                                   \n" +
    "62  |                                       |                                   \n" +
    "63  |                                       |                                   \n"
    , outStream.toString());
  }
}