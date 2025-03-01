import React, { useState } from "react";
import RatingStars from "./RatingStars";

interface ReviewFormProps {
  onSubmit: (review: { rating: number; comment: string }) => void;
}

const ReviewForm: React.FC<ReviewFormProps> = ({ onSubmit }) => {
  const [rating, setRating] = useState(0);
  const [comment, setComment] = useState("");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (rating === 0 || comment.trim() === "") {
      alert("Vui lòng chọn số sao và nhập đánh giá.");
      return;
    }
    onSubmit({ rating, comment });
    setRating(0);
    setComment("");
  };

  return (
    <div className="p-6 bg-white shadow-md rounded-lg">
      <h2 className="text-lg font-bold mb-4">Để lại đánh giá</h2>
      <RatingStars rating={rating} onRatingChange={setRating} />
      <textarea
        className="w-full p-3 border rounded-md mt-4"
        placeholder="Nhập bình luận..."
        value={comment}
        onChange={(e) => setComment(e.target.value)}
      />
      <button
        className="mt-4 bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600"
        onClick={handleSubmit}
      >
        Gửi đánh giá
      </button>
    </div>
  );
};

export default ReviewForm;
