import java.util.Scanner;

public final class Game {
    private final Piece[][] board = new Piece[8][8];

    public Game() {
        restart();
    }

    public void Run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                Чтобы проверить игру надо вводить команды:
                'exit' - для выхода
                'restart' - для перезапуска игры
                'castlingShort' или 'castlingLong' - для короткой и длинной рокировки соответственно
                'move 1 1 2 3' - для передвижения фигуры с позиции 1 1 на 2 3(поле это двумерный массив от 0 до 7)""");

        System.out.println();

        ConsoleRenderer.render(board);

        boolean isWhiteMove = true;

        while (true) {
            System.out.println(isWhiteMove ? "Ход белых:" : "Ход чёрных:");
            String s = scanner.nextLine();

            if (s.equals("exit")) break;
            else if (s.equals("restart")) {
                System.out.println("Заново");
                restart();
                ConsoleRenderer.render(board);
            } else {
                if (s.equals("castlingShort")) {
                    if (castlingShort(isWhiteMove)) {
                        System.out.println("Рокировка удалась");
                        ConsoleRenderer.render(board);
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.equals("castlingLong")) {
                    if (castlingLong(isWhiteMove)) {
                        System.out.println("Рокировка удалась");
                        ConsoleRenderer.render(board);
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.contains("move")) {
                    String[] a = s.split(" ");
                    try {
                        int line = Integer.parseInt(a[1]);
                        int column = Integer.parseInt(a[2]);
                        int toLine = Integer.parseInt(a[3]);
                        int toColumn = Integer.parseInt(a[4]);

                        PieceColor color = isWhiteMove ? PieceColor.white : PieceColor.black;

                        if (board[line][column].getColor() != color) {
                            System.out.println("Не ваш ход");
                        } else if (Game.moveToPosition(board, line, column, toLine, toColumn)) {
                            System.out.println("Ход выполнен");
                            ConsoleRenderer.render(board);
                            isWhiteMove = !isWhiteMove;
                        } else System.out.println("Невозможный ход");
                    } catch (Exception e) {
                        System.out.println("Вы что-то ввели не так, попробуйте ещё раз");
                    }
                }
            }
        }
    }

    private void movePiece(int line, int column, int toLine, int toColumn) {
        Piece piece = board[line][column];
        board[line][column] = null;
        board[toLine][toColumn] = piece;
    }

    private boolean castlingLong(boolean isWhiteMove) {
        if (isWhiteMove) {
            Piece king = board[0][4];
            Piece rook = board[0][0];

            if (king.isDidntMove && rook.isDidntMove) {
                if (Game.moveToPosition(board, 0, 0, 0, 3)) {
                    movePiece(0, 4, 0, 2);
                    return true;
                }

            }
        } else {
            Piece king = board[7][4];
            Piece rook = board[7][0];

            if (king.isDidntMove && rook.isDidntMove) {
                if (Game.moveToPosition(board, 7, 0, 7, 3)) {
                    movePiece(7, 4, 7, 2);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean castlingShort(boolean isWhiteMove) {
        if (isWhiteMove) {
            Piece king = board[0][4];
            Piece rook = board[0][7];

            if (king.isDidntMove && rook.isDidntMove) {
                if (Game.moveToPosition(board, 0, 7, 0, 5)) {
                    movePiece(0, 4, 0, 6);
                    return true;
                }

            }
        } else {
            Piece king = board[7][4];
            Piece rook = board[7][7];

            if (king.isDidntMove && rook.isDidntMove) {
                if (Game.moveToPosition(board, 7, 7, 7, 5)) {
                    movePiece(7, 4, 7, 6);
                    return true;
                }
            }
        }

        return false;
    }

    private void restart() {
        board[0][0] = new Rook(PieceColor.white);
        board[0][1] = new Horse(PieceColor.white);
        board[0][2] = new Bishop(PieceColor.white);
        board[0][3] = new Queen(PieceColor.white);
        board[0][4] = new King(PieceColor.white);
        board[0][5] = new Bishop(PieceColor.white);
        board[0][6] = new Horse(PieceColor.white);
        board[0][7] = new Rook(PieceColor.white);
        board[1][0] = new Pawn(PieceColor.white);
        board[1][1] = new Pawn(PieceColor.white);
        board[1][2] = new Pawn(PieceColor.white);
        board[1][3] = new Pawn(PieceColor.white);
        board[1][4] = new Pawn(PieceColor.white);
        board[1][5] = new Pawn(PieceColor.white);
        board[1][6] = new Pawn(PieceColor.white);
        board[1][7] = new Pawn(PieceColor.white);

        board[7][0] = new Rook(PieceColor.black);
        board[7][1] = new Horse(PieceColor.black);
        board[7][2] = new Bishop(PieceColor.black);
        board[7][3] = new Queen(PieceColor.black);
        board[7][4] = new King(PieceColor.black);
        board[7][5] = new Bishop(PieceColor.black);
        board[7][6] = new Horse(PieceColor.black);
        board[7][7] = new Rook(PieceColor.black);
        board[6][0] = new Pawn(PieceColor.black);
        board[6][1] = new Pawn(PieceColor.black);
        board[6][2] = new Pawn(PieceColor.black);
        board[6][3] = new Pawn(PieceColor.black);
        board[6][4] = new Pawn(PieceColor.black);
        board[6][5] = new Pawn(PieceColor.black);
        board[6][6] = new Pawn(PieceColor.black);
        board[6][7] = new Pawn(PieceColor.black);
    }

    public static boolean checkStraightDiagonal(Piece[][] board, int line, int column, int toLine, int toColumn) {
        int minColumn = Math.min(column, toColumn) + 1;
        int minLine = Math.min(line, toLine) + 1;

        int maxColumn = Math.max(column, toColumn) - 1;
        int maxLine = Math.max(line, toLine) - 1;

        while (minColumn <= maxColumn) {
            if (board[minLine][minColumn] != null) {
                return false;
            }

            minColumn++;
            minLine++;
        }

        return true;
    }

    public static boolean checkReverseDiagonal(Piece[][] board, int line, int column, int toLine, int toColumn) {
        int maxColumn = Math.max(column, toColumn) - 1;
        int minLine = Math.min(line, toLine) + 1;

        int minColumn = Math.min(column, toColumn) + 1;
        int maxLine = Math.max(line, toLine) - 1;

        while (minColumn < maxColumn) {
            if (board[minLine][maxColumn] != null) {
                return false;
            }

            maxColumn--;
            minLine++;
        }

        return true;
    }

    private static boolean moveToPosition(Piece[][] board, int line, int column, int toLine, int toColumn) {
        Piece piece = board[line][column];

        if (piece != null && piece.canMove(board, line, column, toLine, toColumn)) {
            board[line][column] = null;
            board[toLine][toColumn] = piece;
            piece.isDidntMove = false;

            if (piece instanceof Pawn && (toLine == 0 || toLine == 7)) {
                board[toLine][toColumn] = new Queen(piece.getColor());
            }

            return true;
        }

        return false;

    }

    public static boolean checkBorder(int line, int column) {
        return (line >= 0 && line < 8 && column >= 0 && column < 8);
    }

    public static boolean targetSquareIsEmpty(Piece[][] board, int line, int column) {
        return board[line][column] == null;
    }

    public static boolean checkVertical(Piece[][] board, int line, int column, int toLine, int toColumn) {
        if (column != toColumn || line == toLine) {
            return false;
        }

        int min = Math.min(line, toLine);
        int max = Math.max(line, toLine);

        for (int i = min + 1; i < max; i++) {
            if (board[i][toColumn] != null) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkHorizontal(Piece[][] board, int line, int column, int toLine, int toColumn) {
        if (line != toLine || column == toColumn) {
            return false;
        }

        int min = Math.min(column, toColumn);
        int max = Math.max(column, toColumn);

        for (int i = min + 1; i < max; i++) {
            if (board[line][i] != null) {
                return false;
            }
        }

        return true;
    }
}