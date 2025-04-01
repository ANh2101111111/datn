"use client";

import React from "react";

import OrderTable from "./components/OrderTable";

const Dashboard: React.FC = () => {
  return (
    <div className="container mx-auto p-8">
      <h1 className="text-heading-1 font-bold font-quicksand text-text-heading ">
        Dashboard
      </h1>
      <div className=" w-full h-[1px] bg-text-body mb-7"></div>
      <OrderTable />
    </div>
  );
};

export default Dashboard;
