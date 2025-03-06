"use client";
import React, { useState } from "react";
import ReviewForm from "./components/ReviewForm";
import ReviewList from "./components/ReviewList";
import { initialReviews, Review } from "./components/data";

const ProductReview: React.FC = () => {
  const [reviews, setReviews] = useState<Review[]>(initialReviews);

  const handleNewReview = (review: Review) => {
    setReviews([review, ...reviews]);
  };

  return (
    <div className="max-w-2xl mx-auto p-6">
      <ReviewForm onSubmit={handleNewReview} />
      <ReviewList reviews={reviews} />
    </div>
  );
};

export default ProductReview;
