export interface ICart {
  cartId: number;
  userId: number;
  cartDetails: ICartDetail[];
  totalAmount: number;
}

export interface ICartDetail {
  cartDetailId: number;
  bicycleId: number;
  productName: string;
  quantity: number;
  image: string;
  totalAmount: number;
}

export interface IAddCart {
  bicycleId: number;
  quantity: number;
}
