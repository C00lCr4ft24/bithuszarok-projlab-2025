package fungorium.view;

import fungorium.GameModel;
import fungorium.model.tecton.Tecton;
import fungorium.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameView extends JPanel {
    private final gameViewText gameViewText = new gameViewText();
    private final MapPanel mapPanel = new MapPanel();

    public GameView() {
        setBackground(Color.DARK_GRAY);
        setLayout(new GridLayout(1, 2));
        add(mapPanel);
        add(gameViewText);
    }

    public gameViewText getGameViewText() { return gameViewText; }
    public MapPanel getMapPanel() { return mapPanel; }

    private static class MapPanel extends JPanel implements Observer {
        private GameModel gameModel;
        private static final int HEX_SIZE = 40;

        public MapPanel() {
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(500, 500));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (gameModel == null) return;

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                RenderingHints.VALUE_ANTIALIAS_ON);

            List<Tecton> tectons = gameModel.getAllTectonsForCurrentPlayer();
            if (tectons == null || tectons.isEmpty()) return;

            drawTectons(g2d, tectons);
        }

        private void drawTectons(Graphics2D g2d, List<Tecton> tectons) {
            int startX = getWidth() / 4;
            int startY = getHeight() / 4;
            int hexWidth = (int)(HEX_SIZE * 1.5);
            int hexHeight = (int)(HEX_SIZE * Math.sqrt(3));
            
            int maxCol = 5; // Adjust based on your map size
            int col = 0;
            int row = 0;

            for (Tecton tecton : tectons) {
                int x = startX + col * hexWidth;
                int y = startY + row * hexHeight;
                if (row % 2 == 1) {
                    x += hexWidth / 2; // Offset odd rows
                }

                drawHexagon(g2d, x, y, tecton);

                col++;
                if (col >= maxCol) {
                    col = 0;
                    row++;
                }
            }
        }

        private void drawHexagon(Graphics2D g2d, int centerX, int centerY, Tecton tecton) {
            int[] xPoints = new int[6];
            int[] yPoints = new int[6];
            
            for (int i = 0; i < 6; i++) {
                double angle = i * Math.PI / 3;
                xPoints[i] = centerX + (int)(HEX_SIZE * Math.cos(angle));
                yPoints[i] = centerY + (int)(HEX_SIZE * Math.sin(angle));
            }

            // Fill based on content
            if (tecton.getMyceliumJunctionOfFungus().getFungus() != null) {
                g2d.setColor(new Color(144, 238, 144));
                g2d.fillPolygon(xPoints, yPoints, 6);
            }

            // Draw outline
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawPolygon(xPoints, yPoints, 6);

            // Draw insects
            if (!tecton.getInsects().isEmpty()) {
                g2d.setColor(Color.RED);
                g2d.fillOval(centerX - 5, centerY - 5, 10, 10);
            }

            // Draw spores
            if (!tecton.getAllSpores().isEmpty()) {
                g2d.setColor(Color.BLUE);
                g2d.fillOval(centerX - 3, centerY - 3, 6, 6);
            }

            // Highlight selected tecton
            if (gameModel.getSelectedTecton() == tecton) {
                g2d.setColor(Color.YELLOW);
                g2d.setStroke(new BasicStroke(3));
                g2d.drawPolygon(xPoints, yPoints, 6);
            }
        }

        @Override
        public void update(GameModel model) {
            this.gameModel = model;
            repaint();
        }
    }

    private static class gameViewText extends JTextPane implements Observer {
        public gameViewText() {
            setEditable(false);
            setFocusable(false);
        }

        @Override
        public void update(GameModel gameModel) {
            if(gameModel == null || gameModel.getSelectedTecton() == null) return;
            setText("Current Tecton: " + gameModel.getSelectedTecton() + "\n" +
                    "Neighbor Tectons: " + gameModel.getSelectedTecton().getNeighborTectons() + "\n" +
                    "Insects: " + gameModel.getSelectedTecton().getInsects() + "\n" +
                    "Spores: " + gameModel.getSelectedTecton().getAllSpores() + "\n" +
                    "Fungus: " + gameModel.getSelectedTecton().getMyceliumJunctionOfFungus().getFungus() + "\n" +
                    "Connections: "
            );
        }
    }
}