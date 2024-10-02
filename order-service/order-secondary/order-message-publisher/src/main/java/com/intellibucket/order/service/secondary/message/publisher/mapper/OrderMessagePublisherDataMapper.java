package com.intellibucket.order.service.secondary.message.publisher.mapper;

import com.intellibucket.kafka.order.avro.model.company.*;
import com.intellibucket.kafka.order.avro.model.delivery.DeliveryAvroAddress;
import com.intellibucket.kafka.order.avro.model.delivery.OrderStartDeliveryRequestAvroModel;
import com.intellibucket.kafka.order.avro.model.payment.PaymentRefundAvroModel;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.company.OrderCompanyApproveEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.company.OrderCompanyApproveEventProduct;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.company.OrderCompanyRefundEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.company.OrderCompletedEventProduct;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.delivery.OrderDeliveryCompletedEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.delivery.OrderStartDeliveryEventAddress;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.delivery.OrderStartDeliveryEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.payment.OrderPaymentRefundEventPayload;
import org.springframework.stereotype.Component;

@Component
public class OrderMessagePublisherDataMapper {

    public PaymentRefundAvroModel orderPaymentRefundEventPayloadToPaymentRefundAvroModel(OrderPaymentRefundEventPayload payload) {
        return PaymentRefundAvroModel.newBuilder()
                .setOrderId(payload.getOrderId())
                .setCreatedAt(payload.getCreatedAt().toInstant())
                .setCustomerId(payload.getCustomerId())
                .setPrice(payload.getPrice())
                .build();
    }

    public OrderCompletedRequestAvroModel orderCompetedEventToOrderCompletedRequestAvroModel(OrderDeliveryCompletedEventPayload payload) {

        return OrderCompletedRequestAvroModel.newBuilder()
                .setOrderId(payload.getOrderId())
                .setCustomerId(payload.getCustomerId())
                .setCreatedAt(payload.getCreatedAt().toInstant())
                .setProducts(payload.getProducts().stream().map(this::orderCompanyCompletedEventProductToCompletedAvroProducts).toList())
                .build();
    }

    public OrderStartDeliveryRequestAvroModel orderPaymentEventToOrderStartDeliveryRequestAvroModel(OrderStartDeliveryEventPayload payload) {
        return OrderStartDeliveryRequestAvroModel.newBuilder()
                .setOrderId(payload.getOrderId())
                .setCustomerId(payload.getCustomerId())
                .setDeliveryAddress(orderStartDeliveryEventAddressToDeliveryAvroAddress(payload.getAddress()))
                .setCreatedAt(payload.getCreatedAt().toInstant())
                .build();
    }

    public CompanyOrderApproveRequestAvroModel orderCompanyApproveEventToCompanyOrderApproveRequestAvroModel(OrderCompanyApproveEventPayload payload) {
        return CompanyOrderApproveRequestAvroModel.newBuilder()
                .setOrderId(payload.getOrderId())
                .setProducts(payload.getProducts().stream().map(this::orderCompanyApproveEventProductToCompanyApprovalAvroProduct).toList())
                .setCreatedAt(payload.getCreatedAt().toInstant())
                .build();
    }

    public CompanyOrderRefundRequestAvroModel orderCompanyRefundEventPayloadToCompanyOrderRefundRequestAvroModel(OrderCompanyRefundEventPayload payload) {

        return CompanyOrderRefundRequestAvroModel.newBuilder()
                .setOrderId(payload.getOrderId())
                .setCustomerId(payload.getCustomerId())
                .setProducts(payload.getProducts().stream().map(this::orderCompanyApproveEventProductToCompanyRefundAvroProduct).toList())
                .setCreatedAt(payload.getCreatedAt().toInstant())
                .build();
    }


    private CompletedAvroProducts orderCompanyCompletedEventProductToCompletedAvroProducts(OrderCompletedEventProduct orderCompletedEventProduct) {
        return CompletedAvroProducts.newBuilder()
                .setProductId(orderCompletedEventProduct.getProductId())
                .setPrice(orderCompletedEventProduct.getPrice())
                .setSubTotal(orderCompletedEventProduct.getSubTotal())
                .setCompanyId(orderCompletedEventProduct.getCompanyId())
                .build();
    }

    private DeliveryAvroAddress orderStartDeliveryEventAddressToDeliveryAvroAddress(OrderStartDeliveryEventAddress address) {
        return DeliveryAvroAddress.newBuilder()
                .setCity(address.getCity())
                .setState(address.getState())
                .setStreet(address.getStreet())
                .build();
    }


    private CompanyApprovalAvroProduct orderCompanyApproveEventProductToCompanyApprovalAvroProduct(OrderCompanyApproveEventProduct orderCompanyApproveEventProduct) {
        return CompanyApprovalAvroProduct.newBuilder()
                .setProductId(orderCompanyApproveEventProduct.getProductId())
                .setCompanyId(orderCompanyApproveEventProduct.getCompanyId())
                .setQuantity(orderCompanyApproveEventProduct.getQuantity())
                .build();
    }

    private CompanyRefundProduct orderCompanyApproveEventProductToCompanyRefundAvroProduct(OrderCompanyApproveEventProduct orderCompanyApproveEventProduct) {
        return CompanyRefundProduct.newBuilder()
                .setProductId(orderCompanyApproveEventProduct.getProductId())
                .setQuantity(orderCompanyApproveEventProduct.getQuantity())
                .setCompanyId(orderCompanyApproveEventProduct.getCompanyId())
                .build();

    }

}
