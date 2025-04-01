/* eslint-disable @next/next/no-img-element */
"use client";
import React, { useEffect, useState } from "react";
import { ICartDetail, removeCart, updateQuantity } from "@/api/cart";
import { useMutation } from "@tanstack/react-query";
import { useAuth } from "@/app/context";
import toast from "react-hot-toast";
import IconDelete from "@/layout/assets/icons/IconDelete";

interface CartItemProps {
  item: ICartDetail;
  refetch: VoidFunction;
}

const CartItemComponent: React.FC<CartItemProps> = ({ item, refetch }) => {
  const { userId } = useAuth();

  const [quantity, setQuantity] = useState<number>(1);

  const updateQuantityMutation = useMutation(updateQuantity, {
    onSuccess: refetch,
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

  useEffect(() => {
    setQuantity(item.quantity);
  }, [item.quantity]);

  const handleIncrease = () => {
    updateQuantityMutation.mutate({
      userId: Number(userId),
      data: {
        bicycleId: item.bicycleId,
        quantity: quantity + 1,
      },
    });
  };

  const handleDecrease = () => {
    if (quantity > 1) {
      updateQuantityMutation.mutate({
        userId: Number(userId),
        data: {
          bicycleId: item.bicycleId,
          quantity: quantity - 1,
        },
      });
    }
  };

  const onRemoveCart = (cartDetailId: number) => {
    const isConfirm = confirm("Are you sure to remove cart?");
    if (!isConfirm) return;

    deleteCartMutation.mutate({
      userId: Number(userId),
      cartDetailId: Number(cartDetailId),
    });
  };

  return (
    <div className="flex items-center justify-between border-b py-4 px-4 bg-gray-50 rounded-lg shadow-sm mb-4">
      {/* <input type="checkbox" className="mr-4" /> */}
      <img
        src={item.image}
        alt={item.productName}
        width={50}
        height={50}
        className="rounded"
      />
      <div className="flex-1 ml-4">
        <p className="font-semibold">{item.productName}</p>
        <p className="text-gray-500">${item.price.toFixed(2)}</p>
      </div>
      <div className="flex items-center">
        <button
          onClick={handleDecrease}
          className="px-3 py-1 bg-gray-200 rounded-l hover:bg-gray-300"
        >
          -
        </button>
        <p className="w-12 text-center border-t border-b outline-none h-8 flex items-center justify-center flex-col">
          {quantity}
        </p>
        <button
          onClick={handleIncrease}
          className="px-3 py-1 bg-gray-200 rounded-r hover:bg-gray-300"
        >
          +
        </button>
      </div>
      <span className="font-bold text-green-600 ml-6">
        ${(item.price * item.quantity).toFixed(2)}
      </span>

      <div
        className="ml-3 cursor-pointer"
        onClick={() => onRemoveCart(item.cartDetailId)}
      >
        <IconDelete />
      </div>
    </div>
  );
};

export default CartItemComponent;
