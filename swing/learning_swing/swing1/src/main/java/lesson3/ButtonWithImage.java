package lesson3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by ilyarudyak on 6/12/16.
 */
public class ButtonWithImage {

    public static void main(String[] argv) {
        SwingUtilities.invokeLater( () -> new ButtonWithImage().startup() );
    }

    public void startup() {

        JButton buttonCut = new JButton( "Cut", new ImageIcon("src/main/resources/buttons/Cut16.gif") );
        buttonCut.setHorizontalTextPosition(JButton.CENTER);
        buttonCut.setVerticalTextPosition(JButton.BOTTOM);

        JButton buttonCopy = new JButton( "Copy", new ImageIcon("src/main/resources/buttons/Copy16.gif") );
        buttonCopy.setHorizontalTextPosition(JButton.CENTER);
        buttonCopy.setVerticalTextPosition(JButton.BOTTOM);

        JButton buttonPaste = new JButton( "Paste", new ImageIcon("src/main/resources/buttons/Paste16.gif") );
        buttonPaste.setHorizontalTextPosition(JButton.CENTER);
        buttonPaste.setVerticalTextPosition(JButton.BOTTOM);

        buttonPaste.setFocusPainted(false);
        buttonPaste.setToolTipText("this is the tooltip text");

        // action button
//        AbstractAction theAction = new BeepAction();
//        JButton buttonBeep = new JButton(theAction);
//        theAction.putValue(Action.NAME, "Siren");
//        theAction.setEnabled(false);

        JFrame frame = new JFrame("More Pressing Matters");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        // add buutons to frame
        frame.getContentPane().add(buttonCut);
        frame.getContentPane().add(buttonCopy);
        frame.getContentPane().add(buttonPaste);
//        frame.getContentPane().add(buttonBeep);

        frame.setSize(500, 125);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    static class BeepAction extends AbstractAction {
        private static final long serialVersionUID = 1L;

        public BeepAction() {
            putValue(NAME, "Beep");
            putValue(MNEMONIC_KEY, KeyEvent.VK_B);
            //putValue(DISPLAYED_MNEMONIC_INDEX_KEY, 0);
            putValue(SMALL_ICON, new ImageIcon(getClass().getResource("jlfgr/Volume16.gif")));
            putValue(LARGE_ICON_KEY, new ImageIcon(getClass().getResource("jlfgr/Volume24.gif")));
            putValue(SHORT_DESCRIPTION, "this is tooltip text, in an Action");
            putValue(LONG_DESCRIPTION, "Cause a beep sound to be generated.");
            //putValue(ACTION_COMMAND_KEY, "something");
        }

        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
        }
    }
}
