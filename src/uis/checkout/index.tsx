import React from "react";
import BillingDetails from "./components/BillingDetails";
import Payment from "./components/Payment";
import { orders, paymentMethods } from "./components/data";
import BoxOrders from "./components/BoxOders";

const Checkout = () => {
  return (
    <div className="container mx-auto p-8 grid grid-cols-1 lg:grid-cols-2 gap-8">
      <div>
        <h2 className="text-heading-2 text-text-heading font-quicksand font-bold mb-4">Checkout</h2>
        <BillingDetails />
      </div>
      <div className="grid grid-cols-1 ">
        <BoxOrders orders={orders} />
        <Payment methods={paymentMethods} />
      </div>
    </div>
  );
};

export default Checkout;
