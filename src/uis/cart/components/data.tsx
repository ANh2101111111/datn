export interface CartItem {
    id: number;
    name: string;
    quantity: number;
    price: number;
    image: string;
  }
  
  export const cartItems: CartItem[] = [
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
      quantity: 1,
      price: 2.80,
      image: "/banner.png",
    },
    {
      id: 3,
      name: "Boomchickapop Kettle Corn",
      quantity: 2,
      price: 3.50,
      image: "/banner.png",
    },
  ];
  