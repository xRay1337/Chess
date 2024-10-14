public class ConsoleRenderer {
    public static void render(Piece[][] board) {
        System.out.println();

        String defaultColor = "\u001B[0m";
        String transparentColor = "\u001B[232m";
        String squareColorWhite = "\u001B[47m";
        String squareColorBlack = "\u001B[0;100m";
        String pieceColorWhite = "\u001B[97m";
        String pieceColorBlack = "\u001B[30m";

        for (int rank = 7; rank >= 0; rank--) {
            boolean isWhiteSquare = rank % 2 == 1;
            String line = "";

            for (int file = 0; file <= 7; file++) {
                Piece piece = board[rank][file];
                String square;

                if (piece == null) {
                    square = "    ";
//                    square = " " + ' ' + " ";
                } else {
                    square = " " + (piece.getColor() == PieceColor.white ? pieceColorWhite : pieceColorBlack) + piece + " ";
                }

                line += (isWhiteSquare ? squareColorWhite : squareColorBlack) + square;

                isWhiteSquare = !isWhiteSquare;
            }

            line += defaultColor;
            System.out.println(line);
        }
    }
}