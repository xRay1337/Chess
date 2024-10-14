public class Rook extends Piece {
    public Rook(PieceColor pieceColor) {
        super(pieceColor, '♖', '♜');
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

        if (line == toLine && Game.checkHorizontal(board, line, column, toLine, toColumn)) {
            return true;
        } else if (column == toColumn && Game.checkVertical(board, line, column, toLine, toColumn)){
            return true;
        }

        return false;
    }
}