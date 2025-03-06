import Input from "@/uis/common/input";
import React from "react";

const BillingDetails: React.FC = () => {
  return (
    <div className="border p-4 shadow-md">
      <h3 className="font-bold text-heading-4 font-quicksand  mb-4">
        Billing Details
      </h3>
      <form className="grid grid-cols-2 gap-4">
        <Input
          type="text"
          placeholder="First name *"
          className="border p-2 rounded"
        />
        <Input
          type="text"
          placeholder="Last name *"
          className="border p-2 rounded"
        />
        <Input
          type="text"
          placeholder="Address *"
          className="border p-2 rounded col-span-2"
        />

        <Input
          type="text"
          placeholder="Phone number *"
          className="border p-2 rounded"
        />
        <Input
          type="email"
          placeholder="Email *"
          className="border p-2 rounded col-span-2"
        />
        <textarea
          placeholder="Additional Information"
          className="border p-2 rounded col-span-2"
        ></textarea>
      </form>
    </div>
  );
};

export default BillingDetails;
