package io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice.service;

import io.eventuate.examples.tram.ordersandcustomers.commondomain.Money;
import io.eventuate.examples.tram.ordersandcustomers.commondomain.OrderState;
import io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice.domain.CustomerViewRepository;
import io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice.domain.OrderViewRepository;

public class OrderHistoryViewService {

  private CustomerViewRepository customerViewRepository;
  private OrderViewRepository orderViewRepository;

  public OrderHistoryViewService(CustomerViewRepository customerViewRepository,
                                 OrderViewRepository orderViewRepository) {
    this.customerViewRepository = customerViewRepository;
    this.orderViewRepository = orderViewRepository;
  }

  public void createCustomer(Long customerId, String customerName, Money creditLimit) {
    customerViewRepository.addCustomer(customerId, customerName, creditLimit);
  }

  public void addOrder(Long customerId, Long orderId, Money orderTotal) {
    customerViewRepository.addOrder(customerId, orderId, orderTotal);
    orderViewRepository.addOrder(orderId, orderTotal);
  }

  public void approveOrder(Long customerId, Long orderId) {
    customerViewRepository.updateOrderState(customerId, orderId, OrderState.APPROVED);
    orderViewRepository.updateOrderState(orderId, OrderState.APPROVED);
  }

  public void rejectOrder(Long customerId, Long orderId) {
    customerViewRepository.updateOrderState(customerId, orderId, OrderState.REJECTED);
    orderViewRepository.updateOrderState(orderId, OrderState.REJECTED);
  }
}
