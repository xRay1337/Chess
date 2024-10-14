public class Pawn extends Piece {
    public Pawn(PieceColor pieceColor) {
        super(pieceColor, '♙', '♟');
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

        if (toColumn == column) {
            if (piece.getColor() == PieceColor.white) {
                return Game.targetSquareIsEmpty(board, line + 1, column) && toLine == line + 1
                        || (piece.isDidntMove && Game.targetSquareIsEmpty(board, line + 1, column) && toLine == 3);
            } else {
                return Game.targetSquareIsEmpty(board, line - 1, column) && toLine == line - 1
                        || (piece.isDidntMove && Game.targetSquareIsEmpty(board, line - 1, column) && toLine == 4);
            }
        }

        if (piece.getColor() == PieceColor.white && toLine == line + 1 && board[toLine][toColumn] != null) {
            return piece.getColor() != board[toLine][toColumn].getColor()
                    && (toColumn == column + 1 || toColumn == column - 1);
        }

        if (piece.getColor() == PieceColor.black && toLine == line - 1 && board[toLine][toColumn] != null) {
            return piece.getColor() != board[toLine][toColumn].getColor()
                    && (toColumn == column + 1 || toColumn == column - 1);
        }

        return false;
    }
}