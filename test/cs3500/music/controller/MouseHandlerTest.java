package cs3500.music.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by joeydonovan on 6/25/16.
 */
public class MouseHandlerTest {

  private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
  private MouseHandler mh = new MouseHandler();

  @Test
  public void testMouseClicked() {
    System.setOut(new PrintStream(outStream));

    Runnable mouseClick1 = () -> System.out.print("button 1 clicked ");
    Runnable mouseClick2 = () -> System.out.print("button 2 clicked ");
    Runnable mouseClick3 = () -> System.out.print("button 3 clicked");

    mh.setMouseClicked(MouseEvent.BUTTON1, mouseClick1);
    mh.setMouseClicked(MouseEvent.BUTTON2, mouseClick2);
    mh.setMouseClicked(MouseEvent.BUTTON3, mouseClick3);

    mh.mouseClicked(new MouseEvent(new Container(), 0, 0, 0, 0, 0, 0, false, MouseEvent.BUTTON1));
    assertEquals("button 1 clicked ", outStream.toString());

    mh.mouseClicked(new MouseEvent(new Container(), 0, 0, 0, 0, 0, 0, false, MouseEvent.BUTTON2));
    assertEquals("button 1 clicked button 2 clicked ", outStream.toString());

    mh.mouseClicked(new MouseEvent(new Container(), 0, 0, 0, 0, 0, 0, false, MouseEvent.BUTTON3));
    assertEquals("button 1 clicked button 2 clicked button 3 clicked", outStream.toString());
  }

  @Test
  public void testMousePressed() {
    System.setOut(new PrintStream(outStream));

    Runnable mousePress1 = () -> System.out.print("button 1 pressed ");
    Runnable mousePress2 = () -> System.out.print("button 2 pressed ");
    Runnable mousePress3 = () -> System.out.print("button 3 pressed");

    mh.setMousePressed(MouseEvent.BUTTON1, mousePress1);
    mh.setMousePressed(MouseEvent.BUTTON2, mousePress2);
    mh.setMousePressed(MouseEvent.BUTTON3, mousePress3);

    mh.mousePressed(new MouseEvent(new Container(), 0, 0, 0, 0, 0, 0, false, MouseEvent.BUTTON1));
    assertEquals("button 1 pressed ", outStream.toString());

    mh.mousePressed(new MouseEvent(new Container(), 0, 0, 0, 0, 0, 0, false, MouseEvent.BUTTON2));
    assertEquals("button 1 pressed button 2 pressed ", outStream.toString());

    mh.mousePressed(new MouseEvent(new Container(), 0, 0, 0, 0, 0, 0, false, MouseEvent.BUTTON3));
    assertEquals("button 1 pressed button 2 pressed button 3 pressed", outStream.toString());
  }

  @Test
  public void testMouseReleased() {
    System.setOut(new PrintStream(outStream));

    Runnable mouseRelease1 = () -> System.out.print("button 1 released ");
    Runnable mouseRelease2 = () -> System.out.print("button 2 released ");
    Runnable mouseRelease3 = () -> System.out.print("button 3 released");

    mh.setMouseReleased(MouseEvent.BUTTON1, mouseRelease1);
    mh.setMouseReleased(MouseEvent.BUTTON2, mouseRelease2);
    mh.setMouseReleased(MouseEvent.BUTTON3, mouseRelease3);

    mh.mouseReleased(new MouseEvent(new Container(), 0, 0, 0, 0, 0, 0, false, MouseEvent.BUTTON1));
    assertEquals("button 1 released ", outStream.toString());

    mh.mouseReleased(new MouseEvent(new Container(), 0, 0, 0, 0, 0, 0, false, MouseEvent.BUTTON2));
    assertEquals("button 1 released button 2 released ", outStream.toString());

    mh.mouseReleased(new MouseEvent(new Container(), 0, 0, 0, 0, 0, 0, false, MouseEvent.BUTTON3));
    assertEquals("button 1 released button 2 released button 3 released", outStream.toString());
  }

  @Test
  public void testMouseEntered() {
    System.setOut(new PrintStream(outStream));

    Runnable mouseEnter1 = () -> System.out.print("button 1 entered ");
    Runnable mouseEnter2 = () -> System.out.print("button 2 entered ");
    Runnable mouseEnter3 = () -> System.out.print("button 3 entered");

    mh.setMouseEntered(MouseEvent.BUTTON1, mouseEnter1);
    mh.setMouseEntered(MouseEvent.BUTTON2, mouseEnter2);
    mh.setMouseEntered(MouseEvent.BUTTON3, mouseEnter3);

    mh.mouseEntered(new MouseEvent(new Container(), 0, 0, 0, 0, 0, 0, false, MouseEvent.BUTTON1));
    assertEquals("button 1 entered ", outStream.toString());

    mh.mouseEntered(new MouseEvent(new Container(), 0, 0, 0, 0, 0, 0, false, MouseEvent.BUTTON2));
    assertEquals("button 1 entered button 2 entered ", outStream.toString());

    mh.mouseEntered(new MouseEvent(new Container(), 0, 0, 0, 0, 0, 0, false, MouseEvent.BUTTON3));
    assertEquals("button 1 entered button 2 entered button 3 entered", outStream.toString());
  }

  @Test
  public void testMouseExited() {
    System.setOut(new PrintStream(outStream));

    Runnable mouseExit1 = () -> System.out.print("button 1 exited ");
    Runnable mouseExit2 = () -> System.out.print("button 2 exited ");
    Runnable mouseExit3 = () -> System.out.print("button 3 exited");

    mh.setMouseExited(MouseEvent.BUTTON1, mouseExit1);
    mh.setMouseExited(MouseEvent.BUTTON2, mouseExit2);
    mh.setMouseExited(MouseEvent.BUTTON3, mouseExit3);

    mh.mouseExited(new MouseEvent(new Container(), 0, 0, 0, 0, 0, 0, false, MouseEvent.BUTTON1));
    assertEquals("button 1 exited ", outStream.toString());

    mh.mouseExited(new MouseEvent(new Container(), 0, 0, 0, 0, 0, 0, false, MouseEvent.BUTTON2));
    assertEquals("button 1 exited button 2 exited ", outStream.toString());

    mh.mouseExited(new MouseEvent(new Container(), 0, 0, 0, 0, 0, 0, false, MouseEvent.BUTTON3));
    assertEquals("button 1 exited button 2 exited button 3 exited", outStream.toString());
  }
}