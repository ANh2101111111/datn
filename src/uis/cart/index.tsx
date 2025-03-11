import React from "react";
import CartItemComponent from "./components/CartItem";
import CartSummary from "./components/CartSummary";
import CouponForm from "./components/CouponForm";
import { cartItems } from "./components/data";

const Cart = () => {
  return (
    <div className="container mx-auto p-8 grid grid-cols-1 lg:grid-cols-3 gap-8">
      <div className="lg:col-span-2">
        <h2 className="text-2xl font-bold mb-4">Your Cart</h2>
        <div className="border p-4 shadow-md rounded-lg bg-white">
          {cartItems.length > 0 ? (
            cartItems.map((item) => <CartItemComponent key={item.id} item={item} />)
          ) : (
            <p className="text-center text-gray-500">Your cart is empty.</p>
          )}
        </div>
        <div className="flex justify-between mt-4">
          <button className="bg-yellow-500 text-white px-4 py-2 rounded">Continue Shopping</button>
          <button className="bg-green-500 text-white px-4 py-2 rounded">Update Cart</button>
        </div>
      </div>
      <div className="flex flex-col gap-4">
        <CartSummary />
        <CouponForm />
      </div>
    </div>
  );
};

export default Cart;
