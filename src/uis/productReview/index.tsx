"use client";

import React from "react";
import ReviewForm from "./components/ReviewForm";

const ProductReview: React.FC = () => {
  return (
    <div className="max-w-2xl mx-auto p-6">
      <ReviewForm onSubmit={(newReview) => console.log(newReview)} />
    </div>
  );
};

export default ProductReview;
