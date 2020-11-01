package tk.zorgatone.dndmaker;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CharacteristicBox extends JPanel {

  private final JLabel label;
  private final JTextField textField;

  public CharacteristicBox(final String name) {
    super();

    label = new JLabel(name);
    textField = new JTextField(2);
    textField.setEditable(false);

    add(label);
    add(textField);
  }

  public void setName(final String name) {
    label.setText(name);
  }

  public String getName() {
    return label.getText();
  }

  public void setScore(byte score) {
    if (score < 3 || score > 18) {
      throw new IllegalArgumentException("Invalid score argument must be between 3 and 18!");
    }

    textField.setText(Byte.toString(score));
  }

  public byte getScore() {
    final String text = textField.getText();

    if (text == null || text.trim().length() < 1) {
      return -1;
    }

    return Byte.parseByte(text);
  }

}
