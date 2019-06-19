package project.vendingmachine.data_model;

/**
 * <b>Request processing error types.</b>
 */
public enum RequestError {
    VALIDATION_ERROR("Invalid Parameters"),
    ITEM_DOES_NOT_EXISTS_ERROR("Item Does not exists"),
    VENDING_MACHINE_IS_FULL_ERROR("Wending machine is full"),
    OUT_OF_ITEMS_ERROR("N/A");

    private String message;

    RequestError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
