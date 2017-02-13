package cs3500.music.view;

import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.IMusicEditorRepeats;
import cs3500.music.model.IRange;
import java.util.List;

/**
 * Created by joeydonovan on 6/27/16.
 */
public class ModelToViewRepeats extends ModelToView {

  private IMusicEditorRepeats model;

  public ModelToViewRepeats(IMusicEditorModel model) {
    super(model);
    this.model = (IMusicEditorRepeats) model;
  }

  @Override
  public IRange getRepeatAt(int beat) {
    return model.getRepeatAt(beat);
  }

  @Override
  public boolean hasRepeatAt(int beat) {
    return model.hasRepeatAt(beat);
  }

  @Override
  public boolean hasRepeatsLeft(IRange r) {
    return model.hasRepeatsLeft(r);
  }

  @Override
  public List<IRange> getRepeats() {
    return model.getRepeats();
  }

  @Override
  public List<IRange> getEndings() {
    return model.getEndings();
  }

  @Override
  public int getRespondingBeat(IRange r) {
    return model.getRespondingBeat(r);
  }

  @Override
  public void decrement(IRange r) {
    model.decrement(r);
  }

  @Override
  public void setCurrentEnding(int ending) {
    model.setCurrentEnding(ending);
  }

  @Override
  public int getCurrentEnding() {
    return model.getCurrentEnding();
  }
}
