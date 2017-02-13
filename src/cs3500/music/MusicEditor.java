package cs3500.music;

import cs3500.music.controller.Controller;
import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.util.MusicReader;
import cs3500.music.util.CompositionBuilder;

import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import java.io.FileReader;

/**
 * Creates an application that runs an {@link IMusicEditorModel} based on program arguments.
 */
public final class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    CompositionBuilder<IMusicEditorModel> builder = new MusicEditorModel.MusicEditorBuilder();
//    EditorView view = ViewFactory.constructView(args[0]);
//    ModelToView mtv = new ModelToView(MusicReader.parseFile(new FileReader(args[1]), builder));
//    view.render(mtv);
    IMusicEditorModel model = MusicReader.parseFile(new FileReader(args[1]), builder);
    Controller controller = new Controller(model, Controller.ViewFactory.constructView(args[0]));
    controller.render();
  }
}