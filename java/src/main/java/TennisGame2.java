import java.util.HashMap;

public class TennisGame2 implements TennisGame {
    private final Player playerOne;
    private final Player playerTwo;

    private final HashMap<Integer, String> SCORE_MAP = new HashMap<>();

    public TennisGame2(String player1Name, String player2Name) {
        this.playerOne = new Player(player1Name);
        this.playerTwo = new Player(player2Name);

        initializeScoreMap();
    }

    private void initializeScoreMap() {
        SCORE_MAP.put(0, "Love");
        SCORE_MAP.put(1, "Fifteen");
        SCORE_MAP.put(2, "Thirty");
        SCORE_MAP.put(3, "Forty");
    }

    public String getScore() {
        int scoreDiff = Math.abs(playerOne.getScorePoints() - playerTwo.getScorePoints());
        if (bothPlayersHaveScoreLessThanThree()) {
            setTextScoreForPlayers();

            if (playersHaveTieScore()) {
                return playerOne.getScorePoints() == 3 ? "Deuce" : playerOne.getScoreText() + "-All";
            }
            return this.playerOne.getScoreText() + "-" + this.playerTwo.getScoreText();
        } else if (scoreDiff > 0) {
            return getLeaderText(scoreDiff);
        } else {
            return "Deuce";
        }
    }

    private void setTextScoreForPlayers() {
        playerOne.setScoreText(SCORE_MAP.get(playerOne.getScorePoints()));
        playerTwo.setScoreText(SCORE_MAP.get(playerTwo.getScorePoints()));
    }

    private String getLeaderText(int scoreDiff) {
        if (scoreDiff == 1) {
            return "Advantage " + getLeader().getName();
        }
        return "Win for " + getLeader().getName();
    }

    private Player getLeader() {
        return playerOne.getScorePoints() > playerTwo.getScorePoints() ? playerOne : playerTwo;
    }

    private boolean bothPlayersHaveScoreLessThanThree() {
        return playerOne.getScorePoints() <= 3 && playerTwo.getScorePoints() <= 3;
    }

    private boolean playersHaveTieScore() {
        return this.playerOne.getScorePoints() == this.playerTwo.getScorePoints();
    }

    public void wonPoint(String player) {
        if (player.equals(playerOne.getName()))
            playerOne.score();
        else
            playerTwo.score();
    }

    private static class Player {
        private final String name;
        private int scorePoints;
        private String scoreText;

        public Player(String name) {
            this.name = name;
            this.scorePoints = 0;
            this.scoreText = "";
        }

        public String getName() {
            return name;
        }

        public int getScorePoints() {
            return scorePoints;
        }

        public void score() {
            this.scorePoints++;
        }

        public String getScoreText() {
            return scoreText;
        }

        public void setScoreText(String scoreText) {
            this.scoreText = scoreText;
        }
    }
}