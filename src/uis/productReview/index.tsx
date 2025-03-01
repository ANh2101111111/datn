"use client"
import React, { useState } from "react";
import ReviewForm from "./components/ReviewForm";
import ReviewList from "./components/ReviewList";

const ProductReview: React.FC = () => {
  const [reviews, setReviews] = useState<{ rating: number; comment: string }[]>([]);

  const handleNewReview = (review: { rating: number; comment: string }) => {
    setReviews([...reviews, review]);
  };

  return (
    <div className="max-w-2xl mx-auto p-6">
      <ReviewForm onSubmit={handleNewReview} />
      <ReviewList reviews={reviews} />
    </div>
  );
};

export default ProductReview;
