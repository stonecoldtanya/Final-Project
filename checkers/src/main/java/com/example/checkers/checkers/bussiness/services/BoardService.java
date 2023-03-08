package com.example.checkers.checkers.bussiness.services;
import com.example.checkers.checkers.bussiness.MoveComments;
import com.example.checkers.checkers.bussiness.repositories.BoardStateRepository;
import com.example.checkers.checkers.exceptions.IllegalMoveException;
import com.example.checkers.checkers.models.entities.BoardState;
import com.example.checkers.checkers.models.entities.Move;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    BoardStateRepository boardStateRepository;

    public BoardService(BoardStateRepository boardStateRepository) {
        this.boardStateRepository = boardStateRepository;
    }

    public BoardState init(int length){
        BoardState bs = new BoardState(8);
//        bs.setCurrentState(bs.init(8));
        boardStateRepository.save(bs);
        return bs;
    }

    public void updateState(BoardState board, Move move) {
        if (board.getPiece((int) move.next.getX(), (int)  move.next.getY()) != null) {
            throw new IllegalMoveException(MoveComments.NO_FREE_SPACE);
        }
        board.update(move);
    }

}
