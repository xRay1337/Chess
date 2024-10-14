public class Horse extends Piece {
    public Horse(PieceColor pieceColor) {
        super(pieceColor, '♘', '♞');
    }

    @Override
    public boolean canMove(Piece[][] board, int line, int column, int toLine, int toColumn) {
        if (!Game.checkBorder(toLine, toColumn)) {
            return false;
        }

        Piece piece = board[line][column];

        if (board[toLine][toColumn] != null && piece.getColor() == board[toLine][toColumn].getColor()) {
            return false;
        }

        if (toColumn == column + 1 || toColumn == column - 1) {
            return toLine == line + 2 || toLine == line - 2;
        } else if (toLine == line + 1 || toLine == line - 1) {
            return toColumn == column + 2 || toColumn == column - 2;
        }

        return false;
    }
}