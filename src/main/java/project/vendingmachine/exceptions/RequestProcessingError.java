package project.vendingmachine.exceptions;


import project.vendingmachine.data_model.RequestError;

public class RequestProcessingError extends Exception {
    private RequestError error;

    public RequestProcessingError(RequestError error) {
        this.error = error;
    }

    public RequestError getError() {
        return error;
    }
}
