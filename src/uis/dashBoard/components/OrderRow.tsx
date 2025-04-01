"use client";

import React from "react";
import { useRouter } from "next/navigation";
import { IOrder } from "@/api/order";
import dayjs from "dayjs";

interface OrderRowProps {
  order: IOrder;
}

const ORDER_STATUS_MAPPING: Record<string, string> = {
  PENDING: "Processing",
  CONFIRMED: "Confirmed",
  CANCELLEd: "Cancelled",
  PAID: "Paid",
  COMPLETED: "Completed",
};

const OrderRow: React.FC<OrderRowProps> = ({ order }) => {
  const router = useRouter();

  const handleGoToReview = () => {
    router.push("/productReview");
  };

  return (
    <tr className="border text-text-medium p-[30px]">
      <td className="py-2 px-4 text-text-body">#{order.orderId}</td>
      <td className="py-2 px-4 text-text-body">
        {dayjs(order.createdAt).format("DD/MM/YYYY HH:mm:ss")}
      </td>
      <td
        className={`py-2 px-4 text-text-medium ${
          order.orderStatus === "PENDING"
            ? "text-colorButton-brand1hover"
            : "text-brand-primary"
        }`}
      >
        {ORDER_STATUS_MAPPING[order.orderStatus]}
      </td>
      <td className="py-2 px-4 text-text-body">
        {order.totalAmount.toLocaleString()} VND for {order.orderDetails.length}{" "}
        item
        {order.orderDetails.length > 1 ? "s" : ""}
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
