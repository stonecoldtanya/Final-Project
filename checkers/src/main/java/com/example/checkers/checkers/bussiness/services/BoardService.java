package com.example.checkers.checkers.bussiness.services;
import com.example.checkers.checkers.bussiness.Move;
import com.example.checkers.checkers.bussiness.MoveComments;
import com.example.checkers.checkers.bussiness.Piece;
import com.example.checkers.checkers.exceptions.IllegalMoveException;
import com.example.checkers.checkers.repositories.BoardStateRepository;
import com.example.checkers.checkers.models.dto.BoardStateDTO;
import com.example.checkers.checkers.models.entities.BoardState;
import com.example.checkers.checkers.models.entities.MoveRequest;
import org.springframework.stereotype.Service;
import java.awt.*;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardStateRepository boardStateRepository;

    public BoardService(BoardStateRepository boardStateRepository) {
        this.boardStateRepository = boardStateRepository;
    }

    public BoardState init(int length, BoardState bs) {
        Piece[][] newBoard = new Piece[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length / 2 - 1; j++) {
                if (i % 2 == 1 && j % 2 == 0) {
                    newBoard[j][i] = new Piece('b', new Point(j,i));
                } else if (i % 2 == 0 && j % 2 == 1) {
                    newBoard[j][i] = new Piece('b', new Point(j,i));
                }
            }
            for (int j = length - 1; j > length / 2; j--) {
                if (i % 2 == 0 && j % 2 == 1) {
                    newBoard[j][i] = new Piece('w', new Point(j,i));
                } else if (i % 2 == 1 && j % 2 == 0) {
                    newBoard[j][i] = new Piece('w', new Point(j,i));
                }
            }
        }
        bs.setCurrentState(newBoard);
        return bs;
    }

    public Optional<BoardStateDTO> findBoardById(Long id) {
        Optional<BoardState> bs = boardStateRepository.findById(id);
        return bs.map(BoardStateDTO::fromEntity);
    }

    public BoardState updateState(BoardState board, MoveRequest move) {
        board.setBoard(board.getCurrentState());
        if (board.getPiece(move.getNextX(), move.getNextY()) != null) {
            throw new IllegalMoveException(MoveComments.NO_FREE_SPACE);
        }
        Point current = new Point(move.getCurrentX(), move.getCurrentY());
        Point next = new Point(move.getNextX(), move.getNextY());
        board.setCurrentState(board.update(new Move(current, next)).getBoard());
        this.boardStateRepository.save(board);
        return board;
    }
}
