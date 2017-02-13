package cs3500.music.view;

/**
 * Created by joeydonovan on 6/16/16.
 */

/**
 * Factory class for creating different types of views.
 * The three types of views that can be created are:
 * 1) A visual GUI view that is a sheet of music.
 * 2) A console view that represents the music editor as a String printed to the console.
 * 3) A MIDI sound output that plays the given musical piece.
 */
public class ViewFactory {

  public static EditorView constructView(String view) {
    switch (view) {
      case "console":
        return new ConsoleViewImpl();
      case "visual":
        return new GuiViewImpl();
      case "midi":
        return new MidiViewImpl();
      case "composite":
        return new CompositeViewImpl(new GuiViewImpl(), new MidiViewImpl());
      default:
        throw new IllegalArgumentException("Invalid view type.");
    }
  }
}
