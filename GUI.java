import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI extends Grid {
    
    private static void generateButton(JButton button, JFrame frame, String text, boolean isWinner, int[] flatCountGrid, boolean[] flatBombGrid) {
        button.setBackground(Color.BLACK);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                button.setEnabled(false);
                button.setText(text);
                if(!isWinner) {
                    int option = JOptionPane.showConfirmDialog(null, "You Clicked On Bomb! Retry?", "Mine Sweeper", JOptionPane.YES_NO_OPTION);
                        if(option == JOptionPane.YES_NO_OPTION) {
                            frame.dispose();
                            generateGame(flatCountGrid, flatBombGrid);
                        }
                    }
                }            
            }
        );
    }

    protected static void generateGame(int[] flatCountGrid, boolean[] flatBombGrid) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        Grid grid = new Grid(10, 10, 25);
        int rowSize = grid.getNumRows() * 50;
        int columnSize = grid.getNumColumns() * 50;
        int numSquares = grid.getNumRows() * grid.getNumColumns();
        final int BORDER = 10;

        for (int i = 0; i < numSquares; i++) {
            String text = String.valueOf(flatCountGrid[i]);
            JButton button = new JButton();
            if (!flatBombGrid[i]) { generateButton(button, frame, text, true, flatCountGrid, flatBombGrid); } 
            else { generateButton(button, frame, "BOMB", false, flatCountGrid, flatBombGrid); }
            panel.add(button);
        }

        frame.add(panel);

        panel.setLayout(new GridLayout(grid.getNumRows(), grid.getNumColumns()));
        panel.setSize(rowSize, columnSize);;
        frame.setSize(rowSize + BORDER, columnSize + BORDER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Grid grid = new Grid(10, 10, 25);
        int[][] countGrid = grid.getCountGrid();
        boolean[][] bombGrid = grid.getBombGrid();

        ArrayList<Integer> flatCountList = new ArrayList<>();
        for (int i = 0; i < countGrid.length; i++) {
            for (int j = 0; j < countGrid.length; j++) { flatCountList.add(countGrid[i][j]); }
        }
        int[] flatCountGrid = new int[flatCountList.size()];
        for (int k = 0; k < flatCountGrid.length; k++) { flatCountGrid[k] = flatCountList.get(k); }

        ArrayList<Boolean> flatBombList = new ArrayList<>();
        for (int i = 0; i < bombGrid.length; i++) {
            for (int j = 0; j < bombGrid.length; j++) { flatBombList.add(bombGrid[i][j]); }
        }
        boolean[] flatBombGrid = new boolean[flatBombList.size()];
        for (int k = 0; k < flatBombGrid.length; k++) { flatBombGrid[k] = flatBombList.get(k); }

        for (int i = 0; i < flatBombGrid.length; i++) {
            System.out.println(flatBombGrid[i]);
            System.out.println(flatCountGrid[i]);
        }

        generateGame(flatCountGrid, flatBombGrid);
    }
}
