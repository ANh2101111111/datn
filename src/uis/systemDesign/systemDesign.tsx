import React from "react";
import BestSellers from "@/layout/BestSellers";
import PopularProducts from "@/layout/PopularProducts";
import Banner from "@/layout/banner";

export default function SystemDesign() {
  return (
    <div>
      <Banner />
      <PopularProducts />
      <BestSellers />
    </div>
  );
}
