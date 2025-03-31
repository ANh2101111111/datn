"use client";
import React from "react";
import { useRouter } from "next/navigation";
import { Route } from "@/types/route";

interface ICartSummaryProps {
  totalPrice: number;
}

const CartSummary: React.FC<ICartSummaryProps> = ({ totalPrice }) => {
  const router = useRouter();

  return (
    <div className="border p-4 shadow-md rounded-lg bg-white">
      <h3 className="font-bold text-lg mb-4">Order Summary</h3>
      <div className="flex justify-between mb-2">
        <span>Subtotal:</span>
        <span className="font-bold text-green-600">
          ${totalPrice.toFixed(2)}
        </span>
      </div>
      <div className="flex justify-between mb-2">
        <span>Shipping:</span>
        <span className="text-green-500">Free Shipping</span>
      </div>
      <div className="flex justify-between font-bold text-lg">
        <span>Total:</span>
        <span className="text-green-600">${totalPrice.toFixed(2)}</span>
      </div>
      <button
        className="bg-green-500 text-white w-full p-2 mt-4 rounded"
        onClick={() => router.push(Route.CHECKOUT)}
      >
        Proceed to Checkout
      </button>
    </div>
  );
};

export default CartSummary;
