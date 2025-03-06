export interface OrderItem {
  id: number;
  name: string;
  quantity: number;
  price: number;
  image: string;
}

export interface PaymentMethod {
  id: string;
  name: string;
}

export const orders: OrderItem[] = [
  {
    id: 1,
    name: "Field Roast Chao Cheese",
    quantity: 1,
    price: 2.51,
    image: "/banner.png",
  },
  {
    id: 2,
    name: "Seeds of Change Quinoa",
    quantity: 4,
    price: 5.6,
    image: "/banner.png",
  },
  {
    id: 3,
    name: "Boomchickapop Kettle Corn",
    quantity: 1,
    price: 7.0,
    image: "/banner.png",
  },
];

export const paymentMethods: PaymentMethod[] = [
  { id: "bank", name: "Direct bank transfer" },
  { id: "cash", name: "Cash on delivery" },
  { id: "vnpay", name: "VNPay" },
];
