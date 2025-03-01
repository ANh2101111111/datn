import React from "react";

interface Review {
  rating: number;
  comment: string;
}

interface ReviewListProps {
  reviews: Review[];
}

const ReviewList: React.FC<ReviewListProps> = ({ reviews }) => {
  return (
    <div className="p-6 bg-white shadow-md rounded-lg mt-6">
      {reviews.length === 0 ? (
        <p className="text-gray-500">Chưa có đánh giá nào.</p>
      ) : (
        reviews.map((review, index) => (
          <div key={index} className="border-b py-3">
            <div className="flex items-center space-x-1 mb-2">
              {[...Array(5)].map((_, i) => (
                <span key={i} className={`text-lg ${i < review.rating ? "text-yellow-400" : "text-gray-300"}`}>
                  ★
                </span>
              ))}
            </div>
            <p className="text-gray-700">{review.comment}</p>
          </div>
        ))
      )}
    </div>
  );
};

export default ReviewList;
