export interface OrderDetail {
  orderDetailId: number;
  orderId: number;
  productName: string;
  price: number;
  quantity: number;
}

export interface Order {
  orderId: number;
  userId: number;
  totalAmount: number;
  createdAt: string;
  cancel: boolean;
  orderStatus: string;
  orderDetails: OrderDetail[];
}
