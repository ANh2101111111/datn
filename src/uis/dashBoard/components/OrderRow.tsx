"use client";

import React from "react";
import { Order } from "./data";
import { useRouter } from "next/navigation";

interface OrderRowProps {
  order: Order;
}

const OrderRow: React.FC<OrderRowProps> = ({ order }) => {
  const router = useRouter();

  const handleGoToReview = () => {
    router.push("/productReview");
  };

  return (
    <tr className="border text-text-medium p-[30px]">
      <td className="py-2 px-4 text-text-body">#{order.id}</td>
      <td className="py-2 px-4 text-text-body">{order.date}</td>
      <td
        className={`py-2 px-4 text-text-medium ${
          order.status === "Processing"
            ? "text-colorButton-brand1hover"
            : "text-brand-primary"
        }`}
      >
        {order.status}
      </td>
      <td className="py-2 px-4 text-text-body">
        {order.total} for {order.items} item{order.items > 1 ? "s" : ""}
      </td>
      <td
        onClick={handleGoToReview}
        className="py-2 px-4 text-badge-brand-1 cursor-pointer hover:underline"
        role="button"
      >
        Go To Review
      </td>
    </tr>
  );
};

export default OrderRow;
