package project.vendingmachine.exceptions;


import project.vendingmachine.data_model.RequestError;

/**
 * <b>Custom error for logic constraint violations</b>
 *
 */
public class RequestProcessingError extends Exception {
    private RequestError error;

    public RequestProcessingError(RequestError error) {
        this.error = error;
    }

    public RequestError getError() {
        return error;
    }
}
