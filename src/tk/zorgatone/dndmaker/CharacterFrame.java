package tk.zorgatone.dndmaker;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class CharacterFrame extends JFrame {

  public CharacterFrame() throws HeadlessException {
    this.build();
  }

  @SuppressWarnings("unused")
  public CharacterFrame(GraphicsConfiguration gc) {
    super(gc);

    this.build();
  }

  @SuppressWarnings("unused")
  public CharacterFrame(String title) throws HeadlessException {
    super(title);

    this.build();
  }

  @SuppressWarnings("unused")
  public CharacterFrame(String title, GraphicsConfiguration gc) {
    super(title, gc);

    this.build();
  }

  private void build() {
    String title = getTitle();

    if (title == null || title.trim().length() < 1) {
      this.setTitle("D&D Character Maker");
    }

    setLocationRelativeTo(null);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setLayout(new FlowLayout());
    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    addChildren();

    pack();

    Dimension size = getSize();

    setMinimumSize(new Dimension(Math.max(size.width, 220), size.height));
  }

  private void addChildren() {
    ChacteristicsList list = new ChacteristicsList();

    add(list);

    JButton button = new JButton("Roll");
    button.addActionListener(e -> {
      list.rollScores();
      button.setEnabled(false);
    });

    add(button);
  }

}
