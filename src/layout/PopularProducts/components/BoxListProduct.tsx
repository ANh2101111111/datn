import React, { FC } from "react";
import IconStar from "@/layout/assets/icons/IconStar";
interface IBoxListProductProps {
  image: React.ReactNode;
  name: string;
  brand: string;
  rating: number;
  weight: string;
  originalPrice: number;
  discountedPrice: number;
}

// Component BoxListProduct
const BoxListProduct: FC<IBoxListProductProps> = ({
  image,
  name,
  brand,
  rating,
  weight,
  originalPrice,
  discountedPrice,
}) => {
  return (
    <div className=" bg-white w-[298px] h-[402px] rounded-lg shadow-lg overflow-hidden">
      <div className="flex justify-center p-4">{image}</div>

      {/* Chi tiết sản phẩm */}
      <div className="p-4">
        <h2 className="text-lg font-semibold text-gray-800">{name}</h2>
        <p className="text-sm text-gray-500">{brand}</p>

        {/* Đánh giá sao */}
        <div className="flex items-center space-x-1 mt-2">
          {[...Array(5)].map((_, index) => (
            <IconStar
              key={index}
              className={`w-5 h-5 ${
                index < rating ? "text-yellow-500" : "text-gray-400"
              }`}
            />
          ))}
        </div>

        {/* Trọng lượng */}
        <p className="text-sm text-gray-500 mt-1">{weight}</p>

        {/* Giá */}
        <div className="flex items-center mt-2">
          <span className="text-xl font-semibold text-green-500">
            ${discountedPrice}
          </span>
          <span className="text-sm text-gray-500 line-through ml-2">
            ${originalPrice}
          </span>
        </div>

        {/* Nút Add */}
        <button className="bg-blue-500 text-white w-full py-2 rounded mt-4">
          Add +
        </button>
      </div>
    </div>
  );
};

export default BoxListProduct;
