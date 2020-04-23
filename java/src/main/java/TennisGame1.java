enum Score {
    LOVE(0, "Love"),
    FIFTEEN(1, "Fifteen"),
    THIRTY(2, "Thirty"),
    FORTY(3, "Forty");

    private final int intScore;
    private final String stringScore;

    Score(int intScore, String stringScore) {
        this.intScore = intScore;
        this.stringScore = stringScore;
    }

    public static String getStringScoreOf(int score) {
        for (Score value : values()) {
            if (value.intScore == score) {
                return value.stringScore;
            }
        }
        throw new UnsupportedOperationException();
    }

    public static String getStringTieScoreOf(int score) {
        if (score < 3) {
            return getStringScoreOf(score) + "-All";
        }
        return "Deuce";
    }
}

public class TennisGame1 implements TennisGame {
    private final Player playerOne;
    private final Player playerTwo;

    public TennisGame1(String player1Name, String player2Name) {
        playerOne = new Player(player1Name);
        playerTwo = new Player(player2Name);
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(playerOne.getName())) {
            playerOne.incrementScore();
        } else {
            playerTwo.incrementScore();
        }
    }

    public String getScore() {
        if (playerOne.getScore() == playerTwo.getScore()) {
            return getScoreOnTie();
        } else if (playerOne.getScore() >= 4 || playerTwo.getScore() >= 4) {
            return getScoreGreaterThan4Points();
        } else {
            return getScoreLessThan4Points();
        }
    }

    private String getScoreOnTie() {
        return Score.getStringTieScoreOf(playerOne.getScore());
    }

    private String getScoreGreaterThan4Points() {
        return getStatus() + getWinner();
    }

    private String getStatus() {
        int diffScore = playerOne.getScore() - playerTwo.getScore();
        return diffScore == 1 || diffScore == -1 ? "Advantage " : "Win for ";
    }

    private String getWinner() {
        return playerOne.getScore() > playerTwo.getScore() ? playerOne.getName() : playerTwo.getName();
    }

    private String getScoreLessThan4Points() {
        return Score.getStringScoreOf(playerOne.getScore()) + "-" + Score.getStringScoreOf(playerTwo.getScore());
    }

    private static class Player {
        private final String name;
        private int score;

        public Player(String name) {
            this.name = name;
            this.score = 0;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        public void incrementScore() {
            score++;
        }
    }
}
