/* eslint-disable @next/next/no-img-element */
import React from "react";
import { OrderItem } from "./data";

interface BoxOrdersProps {
  orders: OrderItem[];
}

const BoxOrders: React.FC<BoxOrdersProps> = ({ orders }) => {
  return (
    <div className="border p-6 shadow-lg rounded-lg bg-brand-thrid">
      {/* Header */}
      <div className="flex justify-between items-center border-b pb-3 mb-3">
        <h3 className="font-bold text-heading-4 text-text-heading font-quicksand">
          Your Orders
        </h3>
        <span className="text-text-muted font-quicksand text-sm">Subtotal</span>
      </div>

      <div className="space-y-4">
        {orders.map((item) => (
          <div
            key={item.id}
            className="flex items-center border-b last:border-b-0 pb-3"
          >
            <img
              src={item.image}
              alt={item.name}
              className="w-16 h-16 rounded-lg object-cover shadow-md"
            />

            <div className="flex-1 pl-4">
              <p className=" text-text-medium font-bold font-quicksand text-text-heading leading-tight">
                {item.name}
              </p>
            </div>

            <div className="w-12 text-center flex items-center justify-center font-bold text-heading-4 text-text-muted text-lg">
              x {item.quantity}
            </div>

            <span className="font-bold text-heading-4 font-quicksand text-scale-color5">
              ${item.price.toFixed(2)}
            </span>
          </div>
        ))}
      </div>
    </div>
  );
};

export default BoxOrders;
