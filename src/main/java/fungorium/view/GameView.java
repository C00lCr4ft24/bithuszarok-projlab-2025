package fungorium.view;

import fungorium.GameModel;
import fungorium.model.tecton.Tecton;
import fungorium.observer.Observer;
import fungorium.model.player.Player;
import fungorium.model.mycelium.*;
import fungorium.model.Insect;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GameView extends JPanel {
    private final MapPanel mapPanel = new MapPanel();

    public GameView() {
        setBackground(new Color(30, 30, 40));
        setLayout(new BorderLayout());
        add(mapPanel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    public MapPanel getMapPanel() { return mapPanel; }

    public static class MapPanel extends JPanel implements Observer {
        private GameModel gameModel;
        private static final int HEX_RADIUS = 50;
        
        // Player colors for fungi
        private static final Color[] PLAYER_COLORS = {
            new Color(255, 100, 100, 220),  // Player 1 (Fungus)
            new Color(100, 180, 255, 220),  // Player 2 (Fungus)
            new Color(120, 255, 120, 220),  // Player 3 (Fungus)
            new Color(255, 200, 100, 220),   // Player 4 (Fungus)
            new Color(100, 80, 30, 220),   // Player 5 (Fungus)
            new Color(50, 10, 30, 220)   // Player 6 (Fungus)
        };
        
        // Insect colors by player
        private static final Color[] INSECT_COLORS = {
            new Color(200, 50, 50, 240),    // Player 1 (Insect)
            new Color(50, 120, 200, 240),   // Player 2 (Insect)
            new Color(50, 200, 50, 240),    // Player 3 (Insect)
            new Color(200, 150, 50, 240),    // Player 4 (Insect)
            new Color(100, 80, 30, 240),   // Player 5 (Fungus)
            new Color(50, 10, 30, 240)    // Player 6 (Insect)

        };

        // Visual elements
        private static final Color BACKGROUND_COLOR = new Color(245, 245, 250);
        private static final Color HEX_BORDER_COLOR = new Color(60, 60, 70);
        private static final Color HEX_ID_COLOR = new Color(40, 40, 50);
        private static final Color SELECTION_COLOR = new Color(255, 255, 0, 200);
        private static final Color MYCELIUM_COLOR = new Color(150, 100, 50, 180);
        private static final Color JUNCTION_COLOR = new Color(180, 150, 100, 200);
        private static final Color FUNGUS_COLOR = new Color(100, 60, 30, 220);
        private static final Color SPORE_COLOR = new Color(30, 30, 200, 220);

        public MapPanel() {
            setBackground(BACKGROUND_COLOR);
            setOpaque(true);
            setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 90), 2),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
            ));
        }

        @Override
        public Dimension getPreferredSize() {
            if (gameModel == null || gameModel.tectonArrayList.isEmpty()) {
                return new Dimension(900, 700);
            }
            
            Map<Integer, List<Tecton>> tectonsByLevel = organizeTectonsByLevel();
            int levels = tectonsByLevel.size();
            int maxTectonsInLevel = tectonsByLevel.values().stream()
                .mapToInt(List::size)
                .max().orElse(0);
            
            int hexHeight = HEX_RADIUS * 2;
            int hexWidth  = (int)(HEX_RADIUS * Math.sqrt(3));
            
            return new Dimension(
                (maxTectonsInLevel + 2) * hexWidth + 100,
                (levels + 3) * hexHeight + 100
            );
        }

        private Map<Integer, List<Tecton>> organizeTectonsByLevel() {
            Map<Integer, List<Tecton>> tectonsByLevel = new TreeMap<>();
            for (Tecton tecton : gameModel.tectonArrayList) {
                String[] parts = tecton.getId().split("-");
                int level = Integer.parseInt(parts[0].substring(2));
                tectonsByLevel.computeIfAbsent(level, k -> new ArrayList<>()).add(tecton);
            }
            return tectonsByLevel;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            
            // Draw background
            GradientPaint gradient = new GradientPaint(
                0, 0, new Color(230, 230, 240), 
                getWidth(), getHeight(), new Color(210, 210, 220));
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            
            if (gameModel == null || gameModel.tectonArrayList.isEmpty()) {
                drawCenteredMessage(g2d, "Loading map...");
                return;
            }

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // First draw connections between hexes
            drawMyceliumConnections(g2d);
            
            // Then draw the hexes themselves
            drawHexagonalMap(g2d);
        }

        private void drawMyceliumConnections(Graphics2D g2d) {
            g2d.setColor(MYCELIUM_COLOR);
            g2d.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            
            for (MyceliumConnection connection : gameModel.connectionArrayList) {
                Point p1 = getCenterPoint(connection.getJunctionA().getPosition());
                Point p2 = getCenterPoint(connection.getJunctionB().getPosition());
                
                // Draw connection line
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
                
                // Draw connection strength indicator
                int midX = (p1.x + p2.x) / 2;
                int midY = (p1.y + p2.y) / 2;
                g2d.setColor(JUNCTION_COLOR);
                g2d.fillOval(midX - 5, midY - 5, 10, 10);
                g2d.setColor(Color.WHITE);
                g2d.drawString(String.valueOf(connection.getLifetime()), midX - 3, midY + 3);
            }
        }

        private Point getCenterPoint(Tecton tecton) {
            Map<Integer, List<Tecton>> tectonsByLevel = organizeTectonsByLevel();
            int hexWidth = (int)(HEX_RADIUS * 1.5);
            int hexHeight = (int)(HEX_RADIUS * Math.sqrt(3));
            int startY = 50;
            
            for (Map.Entry<Integer, List<Tecton>> entry : tectonsByLevel.entrySet()) {
                int level = entry.getKey();
                List<Tecton> tectons = entry.getValue();
                int levelWidth = tectons.size() * hexWidth;
                if (level % 2 == 1) levelWidth += hexWidth / 2;
                int levelStartX = (getWidth() - levelWidth) / 2;
                
                for (int i = 0; i < tectons.size(); i++) {
                    if (tectons.get(i) == tecton) {
                        int x = levelStartX + i * hexWidth;
                        int y = startY + level * hexHeight;
                        if (level % 2 == 1) x += hexWidth / 2;
                        return new Point(x, y);
                    }
                }
            }
            return new Point(0, 0);
        }

        private void drawCenteredMessage(Graphics2D g2d, String message) {
            g2d.setColor(new Color(80, 80, 90));
            g2d.setFont(new Font("SansSerif", Font.BOLD, 24));
            FontMetrics fm = g2d.getFontMetrics();
            g2d.drawString(message, 
                (getWidth() - fm.stringWidth(message)) / 2,
                (getHeight() - fm.getHeight()) / 2 + fm.getAscent());
        }

        private void drawHexagonalMap(Graphics2D g2d) {
            Map<Integer, List<Tecton>> tectonsByLevel = organizeTectonsByLevel();
            int hexWidth = (int)(HEX_RADIUS * Math.sqrt(3));
            int hexHeight = HEX_RADIUS * 2;
            int startY = 100;

            for (Map.Entry<Integer, List<Tecton>> entry : tectonsByLevel.entrySet()) {
                int level = entry.getKey();
                List<Tecton> tectons = entry.getValue();
                int levelWidth = tectons.size() * hexWidth;
                int levelStartX = (getWidth() - levelWidth) / 2;

                for (int i = 0; i < tectons.size(); i++) {
                    Tecton tecton = tectons.get(i);
                    int x = levelStartX + i * hexWidth;
                    int y = startY + level * (hexHeight - (HEX_RADIUS / 2) + 1);
                    drawHexagon(g2d, x, y, tecton);
                }
            }
        }

        private void drawHexagon(Graphics2D g2d, int centerX, int centerY, Tecton tecton) {
            // Create hexagon shape
            Polygon hexagon = new Polygon();
            for (int i = 0; i < 6; i++) {
                double angle_deg = Math.toRadians((i * 60) + 30);
                int x = (int) (centerX + HEX_RADIUS * Math.cos(angle_deg));
                int y = (int) (centerY + HEX_RADIUS * Math.sin(angle_deg));
                hexagon.addPoint(x, y);
            }

            // Fill hex based on content
            if (!tecton.getMyceliumJunctions().isEmpty()) {
                MyceliumJunction junction = tecton.getMyceliumJunctions().get(0);
                if (junction.getFungus() != null) {
                    Player owner = junction.getFungus().getPlayer();
                    int colorIndex = gameModel.playerArrayList.indexOf(owner) % PLAYER_COLORS.length;
                    g2d.setColor(PLAYER_COLORS[colorIndex]);
                    g2d.fillPolygon(hexagon);
                    
                    // Draw fungus visualization
                    g2d.setColor(FUNGUS_COLOR);
                    g2d.fillOval(centerX - 10, centerY - 10, 20, 20);
                } else {
                    // Just junction without fungus
                    g2d.setColor(JUNCTION_COLOR);
                    g2d.fillPolygon(hexagon);
                }
            } else {
                // Empty hex
                GradientPaint emptyHexGradient = new GradientPaint(
                    centerX - HEX_RADIUS, centerY - HEX_RADIUS, new Color(250, 250, 255),
                    centerX + HEX_RADIUS, centerY + HEX_RADIUS, new Color(230, 230, 235));
                g2d.setPaint(emptyHexGradient);
                g2d.fillPolygon(hexagon);
            }

            // Draw outline
            g2d.setColor(HEX_BORDER_COLOR);
            g2d.setStroke(new BasicStroke(1.5f));
            g2d.drawPolygon(hexagon);

            // Draw tecton ID
            g2d.setColor(HEX_ID_COLOR);
            g2d.setFont(new Font("SansSerif", Font.PLAIN, 10));
            String shortId = tecton.getId();
            FontMetrics fm = g2d.getFontMetrics();
            g2d.drawString(shortId, centerX - fm.stringWidth(shortId)/2, centerY + fm.getAscent()/2 + 10);

            // Draw only one insect per player per tecton
            if (!tecton.getInsects().isEmpty()) {
                Set<Player> displayedPlayers = new HashSet<>();
                int displayIndex = 0;
                
                for (Insect insect : tecton.getInsects()) {
                    if (!displayedPlayers.contains(insect.getPlayer())) {
                        int playerIndex = gameModel.playerArrayList.indexOf(insect.getPlayer());
                        Color insectColor = INSECT_COLORS[playerIndex % INSECT_COLORS.length];
                        
                        int offsetX = -12 + (displayIndex % 3) * 12;
                        int offsetY = -25 + (displayIndex / 3) * 12;
                        
                        g2d.setColor(insectColor);
                        g2d.fillOval(centerX + offsetX - 6, centerY + offsetY - 6, 12, 12);
                        g2d.setColor(Color.BLACK);
                        g2d.setFont(new Font("SansSerif", Font.BOLD, 8));
                        String insectNumber = "R" + (playerIndex-GameSettingsToolBar.getFungusPlayersCount()+1);
                        g2d.drawString(insectNumber, centerX + offsetX - 4, centerY + offsetY + 2);
                
                        
                        displayedPlayers.add(insect.getPlayer());
                        displayIndex++;
                        
                        // Max 9 insects displayed (3x3 grid)
                        if (displayIndex >= 9) break;
                    }
                }
            }

            // Draw spores
            if (!tecton.getAllSpores().isEmpty()) {
                int sporeCount = tecton.getAllSpores().size();
                g2d.setColor(SPORE_COLOR);
                g2d.fillOval(centerX - 8, centerY + 15, 16, 16);
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("SansSerif", Font.BOLD, 10));
                g2d.drawString(String.valueOf(sporeCount), centerX - 4, centerY + 23);
            }

            // Highlight selected tecton
            if (gameModel.getSelectedTecton() == tecton) {
                g2d.setColor(SELECTION_COLOR);
                g2d.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2d.drawPolygon(hexagon);
            }
        }

        @Override
        public void update(GameModel model) {
            this.gameModel = model;
            revalidate();
            repaint();
        }
    }
}