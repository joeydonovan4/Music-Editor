package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by joeydonovan on 6/22/16.
 */
public class MouseHandler implements MouseListener {
  private Map<Integer, Runnable> mouseClickedMap = new HashMap<>();
  private Map<Integer, Runnable> mousePressedMap = new HashMap<>();
  private Map<Integer, Runnable> mouseReleasedMap = new HashMap<>();
  private Map<Integer, Runnable> mouseEnteredMap = new HashMap<>();
  private Map<Integer, Runnable> mouseExitedMap = new HashMap<>();

  public void mouseClicked(MouseEvent e) {
    if (mouseClickedMap.containsKey(e.getButton()))
      mouseClickedMap.get(e.getButton()).run();
  }

  public void setMouseClicked(int key, Runnable r) {
    if (!mouseClickedMap.containsKey(key))
      mouseClickedMap.put(key, r);
  }

  public void mousePressed(MouseEvent e) {
    if (mousePressedMap.containsKey(e.getButton()))
      mousePressedMap.get(e.getButton()).run();
  }

  public void setMousePressed(int key, Runnable r) {
    if (!mousePressedMap.containsKey(key))
      mousePressedMap.put(key, r);
  }

  public void mouseReleased(MouseEvent e) {
    if (mouseReleasedMap.containsKey(e.getButton()))
      mouseReleasedMap.get(e.getButton()).run();
  }

  public void setMouseReleased(int key, Runnable r) {
    if (!mouseReleasedMap.containsKey(key))
      mouseReleasedMap.put(key, r);
  }

  public void mouseEntered(MouseEvent e) {
    if (mouseEnteredMap.containsKey(e.getButton()))
      mouseEnteredMap.get(e.getButton()).run();
  }

  public void setMouseEntered(int key, Runnable r) {
    if (!mouseEnteredMap.containsKey(key))
      mouseEnteredMap.put(key, r);
  }

  public void mouseExited(MouseEvent e) {
    if (mouseExitedMap.containsKey(e.getButton()))
      mouseExitedMap.get(e.getButton()).run();
  }

  public void setMouseExited(int key, Runnable r) {
    if (!mouseExitedMap.containsKey(key))
      mouseExitedMap.put(key, r);
  }
}
