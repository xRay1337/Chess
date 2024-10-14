public class Queen extends Piece {
    public Queen(PieceColor pieceColor) {
        super(pieceColor, '♕', '♛');
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

        if (column - toColumn == line - toLine) {
            return Game.checkStraightDiagonal(board, line, column, toLine, toColumn);
        } else if (toColumn - column == line - toLine) {
            return Game.checkReverseDiagonal(board, line, column, toLine, toColumn);
        } else if (line == toLine && Game.checkHorizontal(board, line, column, toLine, toColumn)) {
            return true;
        } else if (column == toColumn && Game.checkVertical(board, line, column, toLine, toColumn)){
            return true;
        }

        return false;
    }
}