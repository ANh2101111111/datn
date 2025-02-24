import React from "react";
import { Order } from "./data";

interface OrderRowProps {
  order: Order;
}

const OrderRow: React.FC<OrderRowProps> = ({ order }) => {
  return (
    <tr className="border text-text-medium p-[30px]">
      <td className="py-2 px-4 text-text-body">#{order.id}</td>
      <td className="py-2 px-4 text-text-body">{order.date}</td>
      <td
        className={`py-2 px-4 text-text-medium ${
          order.status === "Processing" ? "text-colorButton-brand1hover" : "text-brand-primary"
        }`}
      >
        {order.status}
      </td>
      <td className="py-2 px-4 text-text-body">
        {order.total} for {order.items} item{order.items > 1 ? "s" : ""}
      </td>
      <td className="py-2 px-4 text-scale-color5 cursor-pointer hover:underline">
        View
      </td>
    </tr>
  );
};

export default OrderRow;
