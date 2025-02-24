/* eslint-disable @next/next/no-img-element */
import React from "react";
import { OrderItem } from "./data";

interface BoxOrdersProps {
  orders: OrderItem[];
}

const BoxOrders: React.FC<BoxOrdersProps> = ({ orders }) => {
  return (
    <div className="border p-4 shadow-md">
      <h3 className="font-bold text-lg mb-4">Your Orders</h3>
      {orders.map((item) => (
        <div
          key={item.id}
          className="flex justify-between items-center border-b py-2"
        >
          <img src={item.image} alt={item.name} className="w-[90px] h-[90px] rounded" />
          <div className="flex-1 ml-4">
            <p className="font-quicksand text-heading-6 ">{item.name}</p>
            <p className="text-gray-600">x {item.quantity}</p>
          </div>
          <span className="font-bold text-green-500">
            ${item.price.toFixed(2)}
          </span>
        </div>
      ))}
    </div>
  );
};

export default BoxOrders;
