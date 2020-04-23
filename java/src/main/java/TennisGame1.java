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
        if(score < 3) {
            return getStringScoreOf(score) + "-All";
        }
        return "Deuce";
    }
}

public class TennisGame1 implements TennisGame {
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        if (m_score1 == m_score2) {
            return getScoreOnTie();
        } else if (m_score1 >= 4 || m_score2 >= 4) {
            return getScoreGreaterThan4Points();
        } else {
            return getScoreLessThan4Points();
        }
    }

    private String getScoreOnTie() {
        return Score.getStringTieScoreOf(m_score1);
    }

    private String getScoreGreaterThan4Points() {
        int diffScore = m_score1 - m_score2;
        if (diffScore == 1 || diffScore == -1) {
            return "Advantage " + getWinner(m_score1, m_score2);
        } else {
            return "Win for " + getWinner(m_score1, m_score2);
        }
    }

    private String getWinner(int player1Score, int player2Score) {
        return player1Score > player2Score ? player1Name : player2Name;
    }

    private String getScoreLessThan4Points() {
        return Score.getStringScoreOf(m_score1) + "-" + Score.getStringScoreOf(m_score2);
    }
}
