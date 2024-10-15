public abstract class Piece {
    private final PieceColor pieceColor;
    private final char pieceSymbol;
    public boolean isDidntMove = true;

    public abstract boolean canMove(Piece[][] board, int line, int column, int toLine, int toColumn);

    public Piece(PieceColor pieceColor, char whiteSymbol, char blackSymbol) {
        this.pieceColor = pieceColor;
        this.pieceSymbol = pieceColor.equals(PieceColor.white) ? whiteSymbol : blackSymbol;
    }

    public PieceColor getColor() {
        return pieceColor;
    }

    @Override
    public final String toString() {
        return String.valueOf(pieceSymbol);
    }
}