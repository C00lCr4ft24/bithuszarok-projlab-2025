package fungorium.view;

import fungorium.GameModel;
import fungorium.model.Insect;
import fungorium.model.mycelium.MyceliumConnection;
import fungorium.model.mycelium.MyceliumJunction;
import fungorium.model.player.Player;
import fungorium.model.tecton.Tecton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class MapPanel extends JPanel {
    private static final int HEX_RADIUS = 50;
    private static final int HEX_FULL_WIDTH = (int) (HEX_RADIUS * Math.sqrt(3));
    private static final int HEX_HALF_WIDTH = HEX_FULL_WIDTH / 2;
    private static final int HEX_HEIGHT = HEX_RADIUS * 2;
    private static final int levelStartY = 70;
    // Player colors for fungi
    private static final Color[] FUNGUS_COLORS = {
            new Color(255, 153, 153, 220), // Player 1 (Fungus)
            new Color(255, 153, 255, 220), // Player 2 (Fungus)
            new Color(255, 255, 153, 220), // Player 3 (Fungus)
            new Color(153, 153, 255, 220), // Player 4 (Fungus)
            new Color(153, 255, 153, 220), // Player 5 (Fungus)
            new Color(153, 255, 255, 220), // Player 6 (Fungus)
    };
    // Insect colors by player
    private static final Color[] INSECT_COLORS = {
            new Color(255, 0, 0, 255), // Player 1 (Insect)
            new Color(255, 0, 128, 255), // Player 2 (Insect)
            new Color(255, 128, 0, 255), // Player 3 (Insect)
            new Color(0, 128, 255, 255), // Player 4 (Insect)
            new Color(255, 255, 0, 255), // Player 5 (Insect)
            new Color(128, 255, 0, 255), // Player 6 (Insect)
    };
    // Visual elements
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 250);
    private static final Color HEX_BORDER_COLOR = new Color(60, 60, 70);
    private static final Color HEX_ID_COLOR = new Color(40, 40, 50);
    private static final Color SELECTION_COLOR = new Color(255, 255, 0, 200);
    private static final Color JUNCTION_COLOR = new Color(170, 170, 170, 100);
    private static final Color SPORE_COLOR = new Color(76, 153, 0, 255);
    private static GameModel gameModel;
    private static BufferedImage fungusImg;
    private static BufferedImage insectImg;

    public MapPanel() {
        setBackground(BACKGROUND_COLOR);
        setOpaque(true);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 90), 2),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        try {
            fungusImg = ImageIO.read(getClass().getResourceAsStream("/fungus.png"));
            insectImg = ImageIO.read(getClass().getResourceAsStream("/insect.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void setGameModel(GameModel model) {
        gameModel = model;
    }

    @Override
    public Dimension getPreferredSize() {
        if (gameModel == null || GameModel.tectonArrayList.isEmpty()) {
            return new Dimension(900, 700);
        }

        Map<Integer, List<Tecton>> tectonsByLevel = organizeTectonsByLevel();
        int levels = tectonsByLevel.size();
        int maxTectonsInLevel = tectonsByLevel.values().stream()
                .mapToInt(List::size)
                .max().orElse(0);

        return new Dimension(
                (maxTectonsInLevel + 2) * HEX_FULL_WIDTH + 100,
                (levels + 3) * HEX_HEIGHT + 100);
    }

    private Map<Integer, List<Tecton>> organizeTectonsByLevel() {
        Map<Integer, List<Tecton>> tectonsByLevel = new TreeMap<>();
        for (Tecton tecton : GameModel.tectonArrayList) {
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

        GradientPaint gradient = new GradientPaint(
                0, 0, new Color(230, 230, 240),
                getWidth(), getHeight(), new Color(210, 210, 220));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        if (gameModel == null || GameModel.tectonArrayList.isEmpty()) {
            drawCenteredMessage(g2d);
            return;
        }

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        drawHexagonalMap(g2d);
        drawMyceliumConnections(g2d);

    }

    private void drawMyceliumConnections(Graphics2D g2d) {
        for (MyceliumConnection connection : GameModel.connectionArrayList) {
            Point p1 = getCenterPoint(connection.getJunctionA().getPosition());
            Point p2 = getCenterPoint(connection.getJunctionB().getPosition());

            Player owner = connection.getJunctionA().getPlayer();
            int colorIndex = GameModel.playerArrayList.indexOf(owner) % FUNGUS_COLORS.length;

            Color ogColor = FUNGUS_COLORS[colorIndex].darker();
            g2d.setColor(new Color(ogColor.getRed(), ogColor.getGreen(), ogColor.getBlue(), 180));
            g2d.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);

            int midX = (p1.x + p2.x) / 2;
            int midY = (p1.y + p2.y) / 2;
            g2d.setColor(JUNCTION_COLOR);
            g2d.fillOval(midX - 5, midY - 5, 10, 10);
            g2d.setColor(Color.WHITE);
            int lifetime = connection.getLifetime();
            if (lifetime > 0) {
                g2d.drawString(String.valueOf(connection.getLifetime()), midX - 3, midY + 3);
            }
        }
    }

    private Point getCenterPoint(Tecton tecton) {
        Map<Integer, List<Tecton>> tectonsByLevel = organizeTectonsByLevel();

        for (Map.Entry<Integer, List<Tecton>> entry : tectonsByLevel.entrySet()) {
            int level = entry.getKey();
            ArrayList<Tecton> tectons = (ArrayList<Tecton>) entry.getValue();

            int levelWidth = 0;

            for (Tecton t : tectons) {
                if (!t.isBroken()) {
                    levelWidth += HEX_FULL_WIDTH;
                }
                if (t.isBroken()) {
                    levelWidth += HEX_HALF_WIDTH;
                }
            }

            int centerOfPrevTecton = (getWidth() - levelWidth) / 2;
            int y = levelStartY + level * (HEX_HEIGHT - (HEX_RADIUS / 2) + 1);
            boolean sign = false;
            for (int i = 0; i < tectons.size(); i++) {
                Tecton t = tectons.get(i);
                if (!t.isBroken()) {
                    centerOfPrevTecton += HEX_FULL_WIDTH;
                    if (tectons.get(i) == tecton) {
                        return new Point(centerOfPrevTecton, y);
                    }
                }
                if (t.isBroken() && !sign) {
                    centerOfPrevTecton += HEX_FULL_WIDTH;
                    sign = true;
                    if (tectons.get(i) == tecton) {
                        return new Point(centerOfPrevTecton - 22, y);
                    }
                    continue;
                }
                if (t.isBroken() && sign) {
                    sign = false;
                    if (tectons.get(i) == tecton) {
                        return new Point(centerOfPrevTecton + 22, y);
                    }
                }
            }
        }
        return new Point(0, 0);
    }

    private void drawCenteredMessage(Graphics2D g2d) {
        g2d.setColor(new Color(80, 80, 90));
        g2d.setFont(new Font("SansSerif", Font.BOLD, 24));
        FontMetrics fm = g2d.getFontMetrics();
        g2d.drawString("Pálya betöltése...",
                (getWidth() - fm.stringWidth("Pálya betöltése...")) / 2,
                (getHeight() - fm.getHeight()) / 2 + fm.getAscent());
    }

    private void drawHexagonalMap(Graphics2D g2d) {
        Map<Integer, List<Tecton>> tectonsByLevel = organizeTectonsByLevel();

        for (Map.Entry<Integer, List<Tecton>> entry : tectonsByLevel.entrySet()) {
            int level = entry.getKey();
            ArrayList<Tecton> tectons = (ArrayList<Tecton>) entry.getValue();

            drawLevel(g2d, level, tectons);

            /*
            int startY = 65;
            int levelWidth = 0;
            for (Tecton t : tectons) {
                if(!t.isBroken()) { levelWidth += HEX_FULL_WIDTH; }
                if( t.isBroken()) { levelWidth += HEX_HALF_WIDTH; }
            }
            //int levelWidth = tectons.size() * HEX_FULL_WIDTH;
            int levelStartX = (getWidth() - levelWidth) / 2;


            boolean signalHalfTecton = false;
            int i = 0;
            for (Tecton t : tectons) {
                int x = 0;
                if(!t.isBroken()) { x = (levelStartX + i * HEX_FULL_WIDTH); i++; }
                if( t.isBroken() && !signalHalfTecton) { x = (levelStartX + i * HEX_HALF_WIDTH); signalHalfTecton = true; i++; }
                if( t.isBroken() &&  signalHalfTecton) { x = (levelStartX + i * HEX_HALF_WIDTH); signalHalfTecton = false; }
                int y = startY + level * (HEX_HEIGHT - (HEX_RADIUS / 2) + 1);
                drawTecton(g2d, x, y, t);
            }

            for (int i = 0; i < tectons.size(); i++) {
                Tecton t = tectons.get(i);
                int x = 0;
                if(!t.isBroken()) { x = levelStartX + i * HEX_FULL_WIDTH; }
                if( t.isBroken()) { x = levelStartX + (i - 1) * HEX_HALF_WIDTH; }
                int y = startY + level * (HEX_HEIGHT - (HEX_RADIUS / 2) + 1);
                drawTecton(g2d, x, y, t);
            }
            */
        }
    }

    private void drawLevel(Graphics2D g2d, int level, ArrayList<Tecton> tectons) {

        int levelWidth = 0;

        for (Tecton t : tectons) {
            if (!t.isBroken()) {
                levelWidth += HEX_FULL_WIDTH;
            }
            if (t.isBroken()) {
                levelWidth += HEX_HALF_WIDTH;
            }
        }

        int centerOfPrevTecton = (getWidth() - levelWidth) / 2;
        int y = levelStartY + level * (HEX_HEIGHT - (HEX_RADIUS / 2) + 1);
        boolean sign = false;
        for (int i = 0; i < tectons.size(); i++) {
            Tecton t = tectons.get(i);
            if (!t.isBroken()) {
                centerOfPrevTecton += HEX_FULL_WIDTH;
                drawTecton(g2d, centerOfPrevTecton, y, t);
            }
            if (t.isBroken() && !sign) {
                centerOfPrevTecton += HEX_FULL_WIDTH;
                sign = true;
                drawTecton(g2d, centerOfPrevTecton, y, t);
                continue;
            }
            if (t.isBroken() && sign) {
                sign = false;
                drawTecton(g2d, centerOfPrevTecton, y, t);
            }
        }
    }

    private void createTectonPolygonPoints(Polygon tectonPolygon, int centerX, int centerY, Tecton tecton) {
        if (tecton.getId().contains("-S")) {
            for (int i = 0; i < 4; i++) {
                double angle_deg = Math.toRadians((i * 60) + 270);
                int x = (int) (centerX + HEX_RADIUS * Math.cos(angle_deg));
                int y = (int) (centerY + HEX_RADIUS * Math.sin(angle_deg));
                tectonPolygon.addPoint(x, y);
            }
        } else if (tecton.isBroken()) {
            for (int i = 0; i < 4; i++) {
                double angle_deg = Math.toRadians((i * 60) + 90);
                int x = (int) (centerX + HEX_RADIUS * Math.cos(angle_deg));
                int y = (int) (centerY + HEX_RADIUS * Math.sin(angle_deg));
                tectonPolygon.addPoint(x, y);
            }
        } else {
            for (int i = 0; i < 6; i++) {
                double angle_deg = Math.toRadians((i * 60) + 30);
                int x = (int) (centerX + HEX_RADIUS * Math.cos(angle_deg));
                int y = (int) (centerY + HEX_RADIUS * Math.sin(angle_deg));
                tectonPolygon.addPoint(x, y);
            }
        }
    }

    private void drawTecton(Graphics2D g2d, int centerX, int centerY, Tecton tecton) {
        // Create hexagon shape
        Polygon tectonPolygon = new Polygon();
        createTectonPolygonPoints(tectonPolygon, centerX, centerY, tecton);

        FontMetrics fm = g2d.getFontMetrics();

        //Ures Tecton
        if (tecton.getMyceliumJunctions().isEmpty()) {
            GradientPaint emptyHexGradient = new GradientPaint(
                    centerX - HEX_RADIUS, centerY - HEX_RADIUS, new Color(250, 250, 255),
                    centerX + HEX_RADIUS, centerY + HEX_RADIUS, new Color(230, 230, 235));
            g2d.setPaint(emptyHexGradient);
            g2d.fillPolygon(tectonPolygon);
        }

        //Nem ures Tecton
        if (!tecton.getMyceliumJunctions().isEmpty()) {
            MyceliumJunction junctionOfFungus = tecton.getMyceliumJunctionOfFungus();
            //Van Fungus
            if (junctionOfFungus != null) {
                Player owner = junctionOfFungus.getPlayer();
                int colorIndex = GameModel.playerArrayList.indexOf(owner) % FUNGUS_COLORS.length;
                g2d.setColor(FUNGUS_COLORS[colorIndex]);
                g2d.fillPolygon(tectonPolygon);

                String ownerShortName = owner.toString().replaceAll("^([A-Za-zÁá]).*?(\\d+)$", "$1$2");
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("SansSerif", Font.BOLD, 13));

                if (!tecton.isBroken()) {
                    g2d.drawImage(fungusImg, centerX - (fungusImg.getWidth() / 2), centerY - (fungusImg.getWidth() / 2), null);
                    g2d.drawString(ownerShortName, centerX - (fm.stringWidth(ownerShortName) / 2), centerY - 2);
                }
                if (tecton.isBroken() && !tecton.getId().contains("-S")) {
                    g2d.drawImage(fungusImg, centerX - (HEX_HALF_WIDTH / 2) - (fungusImg.getWidth() / 2), centerY - (fungusImg.getWidth() / 2), null);
                    g2d.drawString(ownerShortName, centerX - (HEX_HALF_WIDTH / 2) - (fm.stringWidth(ownerShortName) / 2), centerY - 2);
                }
                if (tecton.isBroken() && tecton.getId().contains("-S")) {
                    g2d.drawImage(fungusImg, centerX + (HEX_HALF_WIDTH / 2) + (fungusImg.getWidth() / 2), centerY - (fungusImg.getWidth() / 2), null);
                    g2d.drawString(ownerShortName, centerX + (HEX_HALF_WIDTH / 2) + (fm.stringWidth(ownerShortName) / 2), centerY - 2);
                }

            }
            //Nincs Fungus
            if (junctionOfFungus == null) {
                g2d.setColor(JUNCTION_COLOR);
                g2d.fillPolygon(tectonPolygon);
            }
        }

        //Korvonal
        g2d.setColor(HEX_BORDER_COLOR);
        g2d.setStroke(new BasicStroke(1.5f));
        g2d.drawPolygon(tectonPolygon);

        // Draw spores
        if (!tecton.getAllSpores().isEmpty()) {
            int sporeCount = tecton.getAllSpores().size();

            g2d.setFont(new Font("SansSerif", Font.BOLD, 11));
            g2d.setColor(SPORE_COLOR);

            if (!tecton.isBroken() || (tecton.isBroken() && !tecton.getId().contains("-S"))) {
                g2d.fillOval(centerX - 40, centerY + 10, 16, 16);
                g2d.setColor(Color.WHITE);
                g2d.drawString(String.valueOf(sporeCount), centerX - 32 - (fm.stringWidth(String.valueOf(sporeCount)) / 2), centerY + 18 + (fm.stringWidth(String.valueOf(sporeCount)) / 2));
            }
            if (tecton.isBroken() && tecton.getId().contains("-S")) {
                g2d.fillOval(centerX + 24, centerY + 10, 16, 16);
                g2d.setColor(Color.WHITE);
                g2d.drawString(String.valueOf(sporeCount), centerX + 32 - (fm.stringWidth(String.valueOf(sporeCount)) / 2), centerY + 18 + (fm.stringWidth(String.valueOf(sporeCount)) / 2));
            }

        }

        //TectonNev
        g2d.setColor(HEX_ID_COLOR);
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 10));
        String shortId = tecton.getId();

        if (!tecton.isBroken()) {
            g2d.drawString(shortId, centerX - (fm.stringWidth(shortId) / 2), centerY + 30);
        }
        if (tecton.isBroken() && !tecton.getId().contains("-S")) {
            g2d.drawString(shortId, centerX - (HEX_HALF_WIDTH / 2) - (fm.stringWidth(shortId) / 2), centerY + 30);
        }
        if (tecton.isBroken() && tecton.getId().contains("-S")) {
            g2d.drawString(shortId, centerX + (HEX_HALF_WIDTH / 2) - (fm.stringWidth(shortId) / 2), centerY + 30);
        }

        //Insect
        if (!tecton.getInsects().isEmpty()) {
            Set<Player> displayedPlayers = new HashSet<>();
            int displayIndex = 0;

            for (Insect insect : tecton.getInsects()) {
                if (!displayedPlayers.contains(insect.getPlayer())) {
                    int playerIndex = GameModel.playerArrayList.indexOf(insect.getPlayer());
                    Color insectColor = INSECT_COLORS[playerIndex % INSECT_COLORS.length];

                    int offsetX = -40 + (displayIndex % 3) * 12;
                    int offsetY = -25 + (displayIndex / 3) * 12;

                    g2d.setColor(insectColor);
                    g2d.setFont(new Font("SansSerif", Font.BOLD, 11));
                    Player owner = insect.getPlayer();
                    String ownerShortName = owner.toString().replaceAll("^([A-Za-zÁá]).*?(\\d+)$", "$1$2");

                    if (!tecton.isBroken()) {
                        g2d.drawImage(insectImg, centerX + offsetX, centerY + offsetY, null);
                        g2d.drawString(ownerShortName, centerX + offsetX + 10 - (fm.stringWidth(ownerShortName) / 2), centerY + offsetY + 15);
                    }
                    if (tecton.isBroken() && !tecton.getId().contains("-S")) {
                        g2d.drawImage(insectImg, centerX + offsetX, centerY + offsetY, null);
                        g2d.drawString(ownerShortName, centerX + offsetX + 10 - (fm.stringWidth(ownerShortName) / 2), centerY + offsetY + 15);
                    }
                    if (tecton.isBroken() && tecton.getId().contains("-S")) {
                        g2d.drawImage(insectImg, centerX + offsetX + HEX_HALF_WIDTH, centerY + offsetY, null);
                        g2d.drawString(ownerShortName, centerX + offsetX + 10 + HEX_HALF_WIDTH - (fm.stringWidth(ownerShortName) / 2), centerY + offsetY + 15);
                    }

                    displayedPlayers.add(insect.getPlayer());
                    displayIndex++;

                    // Max 9 insects displayed (3x3 grid)
                    if (displayIndex >= 9)
                        break;
                }
            }
        }

        // Highlight selected tecton
        Tecton t = GameModel.getSelectedTecton();
        if (t != null && t.equals(tecton)) {
            g2d.setColor(SELECTION_COLOR);
            g2d.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.drawPolygon(tectonPolygon);
        }
    }
}