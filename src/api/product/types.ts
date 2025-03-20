/* eslint-disable @typescript-eslint/no-explicit-any */
export interface IProductDetail {
  id(id: any): unknown;
  bicycleId: number;
  name: string;
  description: string;
  image: string;
  rating: number;
  type: string;
  originalPrice: number;
  discountedPrice: number;
  quantity: number;
  categoryId: number;
}
