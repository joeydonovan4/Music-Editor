package cs3500.music.view;

import java.awt.*;
import javax.swing.*;
import cs3500.music.model.INote;
import cs3500.music.model.IRange;
import cs3500.music.model.Pitch;
import java.awt.geom.Ellipse2D;

/**
 * Draws a Sheet of Music with Notes on it, as well as the corresponding
 * pitches on the left.
 */
class MusicSheetPanel extends JPanel {
  private final IModelToView mtv;
  private final int LEFTOFFSET = 50;
  private JPanel panel;

  /**
   * Constructor for a Sheet of Music.
   * @param mtv the {@link cs3500.music.model.IMusicEditorModel} to be painted.
   */
  MusicSheetPanel(IModelToView mtv) {
    this.mtv = mtv;
    this.panel = new JPanel(new BorderLayout());
    setPreferredSize(new Dimension(mtv.getLengthInBeats() * GuiViewImpl.BLOCKSIZE,
            (mtv.getHighestNote() - mtv.getLowestNote() + 5) * GuiViewImpl.BLOCKSIZE));
  }

  /**
   * Paints the components of a sheet of music.
   * @param g graphics.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (!mtv.getEndings().isEmpty())
      paintEndings(g);
    paintAllNotes(g);
    paintSheet(g);
    paintPitches(g);
    if (!mtv.getSelected().isEmpty())
      paintAllSelectedNotes(g);
    paintRepeatSign(g);
    paintRedLine(g);
    repaint();
  }

  /**
   * Paints the blank sheet of music.
   * @param g graphics.
   */
  private void paintSheet(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.BLACK);
    int range = mtv.getHighestNote() - mtv.getLowestNote() + 1;
    for (int i = 0; i <= range; i++) {
      if ((mtv.getHighestNote() - i + 1) % 12 == 0)
        g2.setStroke(new BasicStroke(4));
      else
        g2.setStroke(new BasicStroke(2));

      g2.drawLine(LEFTOFFSET, i * GuiViewImpl.BLOCKSIZE,
              mtv.getLengthInBeats() * GuiViewImpl.BLOCKSIZE + LEFTOFFSET,
              i * GuiViewImpl.BLOCKSIZE);
    }

    g2.setStroke(new BasicStroke(2));
    for (int i = 0; i <= mtv.getLengthInBeats(); i += mtv.getMeasureLength()) {
      g2.drawLine(i * GuiViewImpl.BLOCKSIZE + LEFTOFFSET, 0,
              i * GuiViewImpl.BLOCKSIZE + LEFTOFFSET,
              range * GuiViewImpl.BLOCKSIZE);
    }

    if (mtv.getLengthInBeats() % mtv.getMeasureLength() != 0) {
      g2.drawLine(mtv.getLengthInBeats() * GuiViewImpl.BLOCKSIZE + LEFTOFFSET, 0,
              mtv.getLengthInBeats() * GuiViewImpl.BLOCKSIZE + LEFTOFFSET,
              range * GuiViewImpl.BLOCKSIZE);
    }
  }

  /**
   * Paints all the notes in a {@link cs3500.music.model.IMusicEditorModel}.
   * @param g graphics.
   */
  private void paintAllNotes(Graphics g) {
    for (INote n : mtv.getNotes()) {
      paintNote(n, g);
    }
  }

  private void paintAllSelectedNotes(Graphics g) {
    for (INote n : mtv.getSelected()) {
      paintSelectedNote(g, n);
    }
  }

  /**
   * Paints a single {@link INote}.
   * @param n the {@code note} to be painted.
   * @param g graphics.
   */
  private void paintNote(INote n, Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.BLACK);
    g2.fillRect((n.getStartBeat() * GuiViewImpl.BLOCKSIZE) + LEFTOFFSET,
            (mtv.getHighestNote() - n.getValue()) * GuiViewImpl.BLOCKSIZE,
            GuiViewImpl.BLOCKSIZE, GuiViewImpl.BLOCKSIZE);
    g2.setColor(Color.BLUE);
    g2.fillRect((n.getStartBeat() + 1) * GuiViewImpl.BLOCKSIZE + LEFTOFFSET,
            (mtv.getHighestNote() - n.getValue()) * GuiViewImpl.BLOCKSIZE,
            (n.getBeats() - 1) * GuiViewImpl.BLOCKSIZE, GuiViewImpl.BLOCKSIZE);
  }

  /**
   * Paints the red ticker line on the GUI, which represents the current
   * beat of the musical piece.
   * @param g graphics.
   */
  private void paintRedLine(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.RED);
    g2.setStroke(new BasicStroke(2));
    g2.drawLine((CompositeViewImpl.currentBeat * GuiViewImpl.BLOCKSIZE) + LEFTOFFSET, 0,
            (CompositeViewImpl.currentBeat * GuiViewImpl.BLOCKSIZE) + LEFTOFFSET,
            ((mtv.getHighestNote() - mtv.getLowestNote()) + 1) * GuiViewImpl.BLOCKSIZE);
  }

  /**
   * Paints a red border box around a selected {@link INote} in the GUI.
   * @param g graphics.
   * @param n the {@link INote} to be painted.
   */
  private void paintSelectedNote(Graphics g, INote n) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.RED);
    g2.setStroke(new BasicStroke(2));
    g2.drawRect(n.getStartBeat() * GuiViewImpl.BLOCKSIZE + LEFTOFFSET,
              (mtv.getHighestNote() - n.getValue()) * GuiViewImpl.BLOCKSIZE,
              n.getBeats() * GuiViewImpl.BLOCKSIZE, GuiViewImpl.BLOCKSIZE);
  }

  /**
   * Paints the pitches on the {@link JPanel}.
   * @param g graphics.
   */
  private void paintPitches(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    int topOffset = 9;
    int leftPadding = LEFTOFFSET / 5;
    int bottomPadding = topOffset * 3 / 5;

    for (int i = mtv.getHighestNote(); i >= mtv.getLowestNote(); i--) {
      int currentY = (mtv.getHighestNote() - i) * GuiViewImpl.BLOCKSIZE + topOffset;
      String str = Pitch.toPitch(i % 12).toString() + ((i / 12) - 1);
      g2.setColor(Color.BLACK);
      g2.setFont(new Font("default", Font.BOLD, 12));
      g2.drawString(str, leftPadding, currentY + bottomPadding);
    }
  }

  private JPanel valuesPanel() {
    JPanel values = new JPanel();
    values.setLayout(new BoxLayout(values, BoxLayout.Y_AXIS));
    for (int i = mtv.getHighestNote(); i >= mtv.getLowestNote(); i--) {
      Pitch pitch = Pitch.toPitch(i % 12);
      JLabel val = new JLabel(pitch.toString() + (i / 12 - 1));
      val.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, GuiViewImpl.BLOCKSIZE));
      values.add(val);
      val.setAlignmentX(Component.LEFT_ALIGNMENT);
      values.add(Box.createVerticalStrut(GuiViewImpl.BLOCKSIZE / 5));
    }
    return values;
  }

  private void paintRepeatSign(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.BLACK);
    for (IRange r : mtv.getRepeats()) {
      if (r.getStartBeat() != 0) {
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(r.getStartBeat() * GuiViewImpl.BLOCKSIZE + LEFTOFFSET, 0,
                r.getStartBeat() * GuiViewImpl.BLOCKSIZE + LEFTOFFSET,
                ((mtv.getHighestNote() - mtv.getLowestNote()) + 1) * GuiViewImpl.BLOCKSIZE);
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(r.getStartBeat() * GuiViewImpl.BLOCKSIZE + LEFTOFFSET + 6, 0,
                r.getStartBeat() * GuiViewImpl.BLOCKSIZE + LEFTOFFSET + 6,
                ((mtv.getHighestNote() - mtv.getLowestNote()) + 1) * GuiViewImpl.BLOCKSIZE);
        Ellipse2D.Double circle = new Ellipse2D.Double(
                r.getStartBeat() * GuiViewImpl.BLOCKSIZE + LEFTOFFSET + 10,
                (((mtv.getHighestNote() - mtv.getLowestNote()) + 1) / 2) * GuiViewImpl.BLOCKSIZE,
                10, 10);
        Ellipse2D.Double circle2 = new Ellipse2D.Double(
                r.getStartBeat() * GuiViewImpl.BLOCKSIZE + LEFTOFFSET + 10,
                ((mtv.getHighestNote() - mtv.getLowestNote()) / 2) * GuiViewImpl.BLOCKSIZE,
                10, 10);
        g2.fill(circle);
        g2.fill(circle2);
      }
      g2.setStroke(new BasicStroke(5));
      g2.drawLine(r.getEndBeat() * GuiViewImpl.BLOCKSIZE + LEFTOFFSET, 0,
              r.getEndBeat() * GuiViewImpl.BLOCKSIZE + LEFTOFFSET,
              ((mtv.getHighestNote() - mtv.getLowestNote()) + 1) * GuiViewImpl.BLOCKSIZE);
      g2.setStroke(new BasicStroke(2));
      g2.drawLine(r.getEndBeat() * GuiViewImpl.BLOCKSIZE + LEFTOFFSET - 6, 0,
              r.getEndBeat() * GuiViewImpl.BLOCKSIZE + LEFTOFFSET - 6,
              ((mtv.getHighestNote() - mtv.getLowestNote()) + 1) * GuiViewImpl.BLOCKSIZE);
      Ellipse2D.Double circle = new Ellipse2D.Double(
              r.getEndBeat() * GuiViewImpl.BLOCKSIZE + LEFTOFFSET - 18,
              (((mtv.getHighestNote() - mtv.getLowestNote()) + 1) / 2) * GuiViewImpl.BLOCKSIZE,
              10, 10);
      Ellipse2D.Double circle2 = new Ellipse2D.Double(
              r.getEndBeat() * GuiViewImpl.BLOCKSIZE + LEFTOFFSET - 18,
              ((mtv.getHighestNote() - mtv.getLowestNote()) / 2) * GuiViewImpl.BLOCKSIZE,
              10, 10);
      g2.fill(circle);
      g2.fill(circle2);
    }
  }

  private void paintEndings(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
      for (int i = 0; i < mtv.getEndings().size(); i++) {
        IRange ending = mtv.getEndings().get(i);
        if (mtv.getCurrentEnding() == i)
          g2.setColor(Color.GREEN.darker());
        else
          g2.setColor(Color.RED.darker());

        g2.fillRect(ending.getStartBeat() * GuiViewImpl.BLOCKSIZE + LEFTOFFSET,
                0, (ending.getEndBeat() - ending.getStartBeat() + 2) * GuiViewImpl.BLOCKSIZE,
                (mtv.getHighestNote() - mtv.getLowestNote() + 1) * GuiViewImpl.BLOCKSIZE);
      }
  }
}