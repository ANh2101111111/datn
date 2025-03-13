import React from "react";

const CouponForm: React.FC = () => {
  return (
    <div className="border p-4 shadow-md rounded-lg bg-white">
      <h3 className="font-bold text-lg mb-4">Apply Coupon</h3>
      <input type="text" placeholder="Enter coupon code" className="border p-2 w-full rounded" />
      <button className="bg-green-500 text-white w-full p-2 mt-2 rounded">Apply</button>
    </div>
  );
};

export default CouponForm;
