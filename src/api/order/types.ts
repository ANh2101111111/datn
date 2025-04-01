export interface ICreateOrder {
  userId: number;
  fullName: string;
  phoneNumber: string;
  email: string;
  address: string;
  note: string;
  paymentMethod: string;
}

export interface IOrder {
  orderId: number;
  userId: number;
  totalAmount: number;
  orderStatus: "PENDING" | "CONFIRMED" | "CANCELED" | "PAID" | "COMPLETED";
  orderDetails: {
    orderDetailId: number;
    orderId: number;
    productName: string;
    price: number;
    quantity: number;
    image: string;
  }[];
  fullName: string;
  createdAt: string;
}
