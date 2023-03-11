package toys_raffle.exceptions;

import toys_raffle.views.Msg;

/**
 * Exception that should be thrown when you try to get an element from empty queue
 */
public class EmptyQueueException extends Exception{
    public EmptyQueueException() {
        super(Msg.emptyQueueMsg);
    }    
}
