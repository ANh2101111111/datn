/* eslint-disable @next/next/no-img-element */

"use client";

import React from "react";
import { useAuth } from "@/app/context";
import { useGetCarts } from "@/api/cart";

const BoxOrders: React.FC = () => {
  const { userId } = useAuth();

  const { data } = useGetCarts({
    variables: Number(userId),
    enabled: !!userId,
    refetchOnMount: true,
  });

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
        {data?.cartDetails.map((item) => (
          <div
            key={item.cartDetailId}
            className="flex items-center border-b last:border-b-0 pb-3"
          >
            <img
              src={item.image}
              alt={item.productName}
              className="w-16 h-16 rounded-lg object-cover shadow-md"
            />

            <div className="flex-1 pl-4">
              <p className=" text-text-medium font-bold font-quicksand text-text-heading leading-tight">
                {item.productName}
              </p>
            </div>

            <div className="w-12 text-center flex items-center justify-center font-bold text-heading-4 text-text-muted text-lg">
              x {item.quantity}
            </div>

            <span className="font-bold text-heading-4 font-quicksand text-scale-color5">
              ${(item.price * item.quantity).toFixed(2)}
            </span>
          </div>
        ))}
      </div>
    </div>
  );
};

export default BoxOrders;
