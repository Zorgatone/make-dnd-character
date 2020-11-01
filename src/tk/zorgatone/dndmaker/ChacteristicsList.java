package tk.zorgatone.dndmaker;

import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import tk.zorgatone.dndmaker.random.Dices;

public class ChacteristicsList extends JPanel {

  private final CharacteristicBox strength;
  private final CharacteristicBox dexterity;
  private final CharacteristicBox constitution;
  private final CharacteristicBox intelligence;
  private final CharacteristicBox wisdom;
  private final CharacteristicBox charisma;

  public ChacteristicsList() {
    super();

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    strength = new CharacteristicBox("Strength");
    dexterity = new CharacteristicBox("Dexterity");
    constitution = new CharacteristicBox("Constitution");
    intelligence = new CharacteristicBox("Intelligence");
    wisdom = new CharacteristicBox("Wisdom");
    charisma = new CharacteristicBox("Charisma");

    add(new ReordGroup(strength));
    add(new ReordGroup(dexterity));
    add(new ReordGroup(constitution));
    add(new ReordGroup(intelligence));
    add(new ReordGroup(wisdom));
    add(new ReordGroup(charisma));
  }

  public void rollScores() {
    final byte[] scores = Dices.rollCharacteristics();

    strength.setScore(scores[0]);
    dexterity.setScore(scores[1]);
    constitution.setScore(scores[2]);
    intelligence.setScore(scores[3]);
    wisdom.setScore(scores[4]);
    charisma.setScore(scores[5]);
  }

  class ReordGroup extends JPanel {
    private final CharacteristicBox box;

    ReordGroup(CharacteristicBox box) {
      super();

      this.box = box;

      setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

      add(box);

      JButton buttonUp = new JButton("^");
      JButton buttonDown = new JButton("v");

      buttonUp.addActionListener(e -> {
        final Component[] components = ChacteristicsList.this.getComponents();

        int index = getSelfIndex(components);

        if (index > 0) {
          ReordGroup other = (ReordGroup) components[index - 1];
          final byte tmpScore = box.getScore();
          box.setScore(other.box.getScore());
          other.box.setScore(tmpScore);
        }
      });

      buttonDown.addActionListener(e -> {
        final Component[] components = ChacteristicsList.this.getComponents();

        int index = getSelfIndex(components);

        if (index < components.length - 1) {
          ReordGroup other = (ReordGroup) components[index + 1];
          final byte tmpScore = box.getScore();
          box.setScore(other.box.getScore());
          other.box.setScore(tmpScore);
        }
      });

      add(buttonUp);
      add(buttonDown);
    }

    private int getSelfIndex(final Component[] components) {
      for (int i = 0; i < components.length; i++) {
        if (components[i] == this) {
          return i;
        }
      }

      return -1;
    }
  }

}
