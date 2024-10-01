package com.intellibucket.saga.order;

public final class SagaConstants {

    public static final String ORDER_COMPLETED_SAGA_NAME = "OrderCompleted";
    public static final String ORDER_START_DELIVERY_SAGA_NAME = "OrderStartDelivery";
    public static final String ORDER_APPROVE_SAGA_NAME = "OrderApprove";
    public static final String ORDER_PAYMENT_CANCEL_SAGA_NAME = "OrderPaymentCancel";
    public static final String ORDER_COMPANY_CANCEL_SAGA_NAME = "OrderCompanyCancel";

    private SagaConstants() {
    }

}
