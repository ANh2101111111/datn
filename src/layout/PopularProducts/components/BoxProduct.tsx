/* eslint-disable @next/next/no-img-element */
"use client";
import React, { FC } from "react";
import { useRouter } from "next/navigation"; // Import useRouter
import IconStar from "@/layout/assets/icons/IconStar";
import BoxBadge from "@/layout/Badge/BoxBadge";
import { useAuth } from "@/app/context";
import toast from "react-hot-toast";
import { useMutation } from "@tanstack/react-query";
import { addCart } from "@/api/cart";

interface IBoxListProductProps {
  image: string;
  name: string;
  brand: string;
  rating: number;
  weight: string;
  originalPrice: number;
  discountedPrice: number;
  type?: string;
  textType?: string;
  id: number;
}

// Component BoxProduct
const BoxProduct: FC<IBoxListProductProps> = ({
  id,
  image,
  name,
  rating,
  weight,
  originalPrice,
  discountedPrice,
  type,
  textType,
}) => {
  const { isLogged, userId } = useAuth();

  const router = useRouter();

  const addCartMutation = useMutation(addCart, {
    onSuccess: () => {
      toast.success("Add to cart successfully");
    },
    onError: () => {
      toast.error("Add to cart failed");
    },
  });

  const handleAddCart = () => {
    if (!isLogged) {
      toast.error("Please login to add to cart");
      return;
    }

    addCartMutation.mutate({
      userId: Number(userId),
      payload: [{ bicycleId: id, quantity: 1 }],
    });
  };

  const handleImageClick = () => {
    router.push("/product/" + id);
  };

  const renderStars = (rating: number) => {
    return [...Array(5)].map((_, index) => (
      <IconStar
        key={index}
        className={`w-5 h-5 ${
          index < rating ? "text-brand-secondary" : "text-icon-star"
        }`}
        fill={index < 4 ? "#FDC040" : "#CDCDCD"}
      />
    ));
  };

  const getBadgeColor = (type: string) => {
    const colors: Record<string, string> = {
      HOT: "bg-badge-Danger",
      NEW: "bg-badge-brand-1",
      SALE: "bg-badge-brand-2",
    };
    return colors[type] || "bg-badge-Danger";
  };

  return (
    <div className="relative bg-brand-thrid w-[298px] h-[402px] border border-border-color1 rounded-[4px] overflow-hidden flex flex-col">
      <div
        className="flex justify-center mt-[65px] mb-4 cursor-pointer"
        onClick={handleImageClick}
      >
        <img
          src={image}
          alt="product"
          className="w-[200px] h-[150px] object-cover transition-transform transform hover:scale-105"
        />
      </div>

      {/* Chi tiết sản phẩm */}
      <div className="px-4 flex flex-col gap-1">
        <h2 className="font-quicksand text-sm font-bold text-text-heading">
          {name}
        </h2>

        {/* Đánh giá sao */}
        <div className="flex items-center">
          <div className="flex items-center space-x-1 mr-2">
            {renderStars(rating)}
          </div>
          <p className="text-text-body text-xs font-lato">
            ({rating.toFixed(1)})
          </p>
        </div>

        {/* Trọng lượng */}
        <p className="text-sm text-text-body font-lato">{weight}</p>
      </div>

      {/* Nút Add */}
      <div className="flex flex-col px-5 gap-2">
        <div className="flex items-center">
          <span className="text-xl font-semibold text-text-brand1">
            ${discountedPrice.toFixed(2)}
          </span>
          <span className="text-sm font-bold text-text-body line-through ml-2">
            ${originalPrice.toFixed(2)}
          </span>
        </div>
        <button
          onClick={handleAddCart}
          className="bg-colorButton-brand1 hover:bg-colorButton-brand1hover text-text-brand1 text-sm font-bold py-2 px-4 rounded-[4px] transition-all duration-300"
        >
          Add +
        </button>
      </div>

      {type && (
        <BoxBadge text={textType || type} className={getBadgeColor(type)} />
      )}
    </div>
  );
};

export default BoxProduct;
