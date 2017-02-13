package cs3500.music.controller;

import static org.junit.Assert.*;
import org.junit.Test;

import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.view.CompositeViewImpl;
import cs3500.music.view.GuiView;
import cs3500.music.view.GuiViewImpl;
import cs3500.music.view.IModelToView;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.ModelToView;
import cs3500.music.model.Note;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by joeydonovan on 6/25/16.
 */

/**
 * This testing class tests all the lambda methods that are stored by
 * the controller in the controller's GuiView.
 */
public class ControllerTest {
  private IMusicEditorModel model;// = new MusicEditorModel(4, 180000);
  //private MidiViewImpl midi;// = new MidiViewImpl();
  private GuiView gui;// = new GuiViewImpl("");
  //private CompositeViewImpl compositeView;// = new CompositeViewImpl(gui, midi);
  private IController controller;

  private void init() {
    model = new MusicEditorModel(4, 180000);
    model.addMultipleNotes(new Note(0, 4, 0, 4), new Note(4, 4, 4, 4), new Note(10, 30, 4, 4));
    //midi = new MidiViewImpl(new StringBuilder());
    gui = new GuiViewImpl();
    controller = new Controller(model, gui);
  }

  @Test
  public void testLeftArrowKeyHandling() {
    init();
  }
}