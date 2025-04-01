"use client";

import React from "react";
import CartSummary from "./components/CartSummary";
import CartItemComponent from "./components/CartItem.tsx";
import { removeCart, useGetCarts } from "@/api/cart";
import { useAuth } from "@/app/context";
import { useMutation } from "@tanstack/react-query";
import toast from "react-hot-toast";
import { Route } from "@/types/route";
import { useRouter } from "next/navigation";

const Cart = () => {
  const { userId } = useAuth();

  const router = useRouter();

  const { data, refetch } = useGetCarts({
    variables: Number(userId),
    enabled: !!userId,
    refetchOnMount: true,
  });

  const deleteCartMutation = useMutation(removeCart, {
    onSuccess: () => {
      toast.success("Remove cart successfully");

      refetch();
    },
    onError: () => {
      toast.error("Remove cart failed");
    },
  });

  const onRemoveCart = (cartDetailId: number) => {
    const isConfirm = confirm("Are you sure to remove cart?");
    if (!isConfirm) return;
  
    deleteCartMutation.mutate({ 
      userId: Number(userId), 
      cartDetailId: Number(cartDetailId) 
    });
  };

  return (
    <div className="container mx-auto p-8 grid grid-cols-1 lg:grid-cols-3 gap-8">
      <div className="lg:col-span-2">
        <h2 className="text-2xl font-bold mb-4">Your Cart</h2>
        <div className="border p-4 shadow-md rounded-lg bg-white">
          {data?.cartDetails && data?.cartDetails?.length > 0 ? (
            data.cartDetails.map((item) => (
              <CartItemComponent
                key={item.cartDetailId}
                item={item}
                refetch={refetch}
              />
            ))
          ) : (
            <p className="text-center text-gray-500">Your cart is empty.</p>
          )}
        </div>
        <div className="flex justify-between mt-4">
          <button
            className="bg-yellow-500 text-white px-4 py-2 rounded"
            onClick={() => router.push(Route.HOME)}
          >
            Continue Shopping
          </button>

          {data?.cartDetails && data?.cartDetails?.length > 0 && (
            <button
            className="bg-red-500 text-white px-4 py-2 rounded"
            onClick={() => onRemoveCart(data?.cartDetails[0]?.cartDetailId)}
          >
            Remove Cart
          </button>
          )}
        </div>
      </div>
      <div className="flex flex-col gap-4">
        <CartSummary totalPrice={data?.totalAmount ?? 0} />
      </div>
    </div>
  );
};

export default Cart;
