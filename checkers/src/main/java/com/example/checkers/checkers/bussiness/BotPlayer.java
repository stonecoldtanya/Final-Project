package com.example.checkers.checkers;

/**
 * The type Bot player.
 */
public class BotPlayer implements Player{
    //algorithm will be here
    private int depth;
    private String player;
    private Difficulty difficulty;

    /**
     * Instantiates a new Bot player.
     *
     * @param depth  the depth
     */
    public BotPlayer(int depth) {
    }

    /**
     * Instantiates a new Bot player.
     */
    public BotPlayer() {
        depth = difficulty.getDepthDifficulty();
        player = getName();
    }

    /**
     * Get next move move.
     *
     * @return the move
     */
    public Move getNextMove(){
        return null;
    }

    @Override
    public String getName() {
        return "Otto the AI";
    }

    @Override
    public Move getNextMove(Board board) {
        return null;
    }


//    private int minimax(BoardState node, int depth, int alpha, int beta){
//        if (depth == 0 || node.isGameOver()){
//            return node.computeHeuristic(this.player);
//        }
//        // MAX player = player
//        if (node.getTurn() == player){
//            // player tries to maximize this value
//            int v = Integer.MIN_VALUE;
//            for (BoardState child : node.getSuccessors()){
//                v = Math.max(v, minimax(child, depth-1, alpha, beta));
//                alpha = Math.max(alpha, v);
//                // prune
//                if (alpha >= beta){
//                    break;
//                }
//            }
//            return v;
//        }
//        // MIN player = opponent
//        if (node.getTurn() == player.getOpposite()){
//            // opponent tries to minimize this value
//            int v = Integer.MAX_VALUE;
//            for (BoardState child : node.getSuccessors()){
//                v = Math.min(v,minimax(child, depth-1, alpha, beta));
//                beta = Math.min(beta, v);
//                // prune
//                if (alpha >= beta){
//                    break;
//                }
//            }
//            return v;
//        }
//        throw new RuntimeException("Error in minimax algorithm");
//    }
//}
}
