/* eslint-disable @typescript-eslint/no-unused-vars */
"use client";
import React, { useState } from "react";
import Image from "next/image";
import IconDelete from "@/layout/assets/icons/IconDelete";
import { addToCart, OrderDetail } from "@/api/oders";

interface CartItemProps {
  item: OrderDetail;
  userId: number;
}

const CartItemComponent: React.FC<CartItemProps> = ({ item, userId }) => {
  const [quantity, setQuantity] = useState(item.quantity);

  const handleQuantityChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setQuantity(Number(e.target.value));
  };

  return (
    <div className="flex items-center justify-between border-b py-4 px-4 bg-gray-50 rounded-lg shadow-sm mb-4">
      <Image
        src="/bike39.png"
        alt={item.productName || "Bicycle image"}
        width={50}
        height={50}
        className="rounded"
      />
      <div className="flex-1 ml-4">
        <p className="font-semibold">{item.productName}</p>
        <p className="text-gray-500">${item.price.toFixed(2)}</p>
      </div>
      <input
        type="number"
        min="1"
        value={quantity}
        onChange={handleQuantityChange}
        className="w-16 text-center border rounded ml-6"
      />
      <span className="font-bold text-green-600 ml-6">
        ${(item.price * quantity).toFixed(2)}
      </span>
      <button className="text-red-500 ml-6">
        <IconDelete />
      </button>
    </div>
  );
};

export default CartItemComponent;
