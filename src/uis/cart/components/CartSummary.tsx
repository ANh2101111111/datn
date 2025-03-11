import React from "react";
import { cartItems } from "./data";

const CartSummary: React.FC = () => {
  const subtotal = cartItems.reduce((acc, item) => acc + item.price * item.quantity, 0);

  return (
    <div className="border p-4 shadow-md rounded-lg bg-white">
      <h3 className="font-bold text-lg mb-4">Order Summary</h3>
      <div className="flex justify-between mb-2">
        <span>Subtotal:</span>
        <span className="font-bold text-green-600">${subtotal.toFixed(2)}</span>
      </div>
      <div className="flex justify-between mb-2">
        <span>Shipping:</span>
        <span className="text-green-500">Free Shipping</span>
      </div>
      <div className="flex justify-between font-bold text-lg">
        <span>Total:</span>
        <span className="text-green-600">${subtotal.toFixed(2)}</span>
      </div>
      <button className="bg-green-500 text-white w-full p-2 mt-4 rounded">Proceed to Checkout</button>
    </div>
  );
};

export default CartSummary;