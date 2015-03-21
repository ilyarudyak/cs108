package gui;

import tetris.brain.Brain;
import tetris.brain.DefaultBrain;
import tetris.piece.Piece;

import javax.swing.*;

/**
 * Created by ilyarudyak on 3/20/15.
 */
public class JBrainTetris extends JTetris {

    private JCheckBox brainMode;
    private DefaultBrain brain;
    private Brain.Move bestMove;
    private int currentCount;

    /**
     * Creates a new JTetris where each tetris square
     * is drawn with the given number of pixels.
     *
     * @param pixels
     */
    JBrainTetris(int pixels) {
        super(pixels);
        brainMode = new JCheckBox();
        brain = new DefaultBrain();
        bestMove = new Brain.Move();
        currentCount = 0;

    }


    @Override
    public JComponent createControlPanel() {

        JPanel panel = (JPanel) super.createControlPanel();

        JPanel brainPanel = new JPanel();
        brainPanel.add(brainMode);
        brainPanel.add(new JLabel("Brain mode"));

        panel.add(brainPanel);

        return panel;
    }

    @Override
    public void tick(int verb) {

        if (brainMode.isSelected() && verb == DOWN) {

            if(currentCount!= count) {
                currentCount = count;
                board.undo();
                bestMove = brain.bestMove(board, currentPiece, HEIGHT, bestMove);
            }

            if(bestMove != null) {

                // keep rotating once every tick(DOWN) till you get
                // the right orientation
                if(!currentPiece.equals(bestMove.piece)) {
                    currentPiece = currentPiece.fastRotation();
                }

                // move piece to left or right or DROP it depending
                // on the current piece and its location
                if(bestMove.x > currentX)	{ currentX++; }
                else if(bestMove.x < currentX)	{ currentX--; }
                else if(currentPiece.equals(bestMove.piece) &&
                        bestMove.x == currentX &&
                        bestMove.y != currentY) {
                    verb = DROP;
                }
            }
        }

        super.tick(verb);
    }


    public static void main(String[] args) {
        // Set GUI Look And Feel Boilerplate.
        // Do this incantation at the start of main() to tell Swing
        // to use the GUI LookAndFeel of the native platform. It's ok
        // to ignore the exception.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }

        JBrainTetris tetris = new JBrainTetris(16);
        JFrame frame = JBrainTetris.createFrame(tetris);
        frame.setVisible(true);
    }
}






