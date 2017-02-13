package cs3500.music.view;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joeydonovan on 6/19/16.
 */

public class ViewFactoryTest {

  private EditorView view;

  @Test
  public void testCreateConsoleView() {
    view = ViewFactory.constructView("console");
    assertEquals(true, view instanceof ConsoleViewImpl);
    assertEquals(false, view instanceof MidiViewImpl);
    assertEquals(false, view instanceof GuiViewImpl);
  }

  @Test
  public void testCreateMIDIView() {
    view = ViewFactory.constructView("midi");
    assertEquals(true, view instanceof MidiViewImpl);
    assertEquals(false, view instanceof ConsoleViewImpl);
    assertEquals(false, view instanceof GuiViewImpl);
  }

  @Test
  public void testCreateGUIView() {
    view = ViewFactory.constructView("visual");
    assertEquals(true, view instanceof GuiViewImpl);
    assertEquals(false, view instanceof ConsoleViewImpl);
    assertEquals(false, view instanceof MidiViewImpl);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInputView() {
    view = ViewFactory.constructView("random");
  }
}