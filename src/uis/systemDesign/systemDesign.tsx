import React from "react";
import BestSellers from "@/layout/BestSellers";
import PopularProducts from "@/layout/PopularProducts";
import Banner from "@/layout/banner";
import ProductReview from "../productReview";

export default function SystemDesign() {
  return (
    <div>
      <ProductReview/>
      <Banner />
      <PopularProducts />
      <BestSellers />
    </div>
  );
}
