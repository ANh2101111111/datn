import React from "react";
import OrderRow from "./OrderRow";
import { orders } from "./data";

const OrderTable: React.FC = () => {
  return (
    <div className="w-full bg-white ">
      <h2 className=" text-text-heading text-heading-3 font-quicksand font-bold mb-4">Recent Orders</h2>
      <table className="w-full ">
        <thead>
          <tr className="bg-gray-100 text-left text-text-medium font-quicksand text-text-heading">
            <th className="py-2 px-4">Order ID</th>
            <th className="py-2 px-4">Date</th>
            <th className="py-2 px-4">Status</th>
            <th className="py-2 px-4">Total</th>
            <th className="py-2 px-4">Actions</th>
          </tr>
        </thead>
        <tbody>
          {orders.map((order) => (
            <OrderRow key={order.id} order={order} />
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default OrderTable;
