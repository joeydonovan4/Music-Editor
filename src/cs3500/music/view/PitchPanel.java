package cs3500.music.view;

import java.awt.*;
import cs3500.music.model.Pitch;
import javax.swing.*;

/**
 * Created by joeydonovan on 6/30/16.
 */
public class PitchPanel extends JPanel {

  private final IModelToView mtv;

  PitchPanel(IModelToView mtv) {
    this.mtv = mtv;
    valuesPanel();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

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
}
