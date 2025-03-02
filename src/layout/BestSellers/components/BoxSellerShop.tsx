"use client";
import React, { FC } from "react";
import { useRouter } from "next/navigation";
import { bestSellerShopData } from "./data";
import Button from "@/uis/common/button";

interface IBestSellerShopProps {
  name: string;
  image: string;
}

const BestSellerCard: FC<IBestSellerShopProps> = ({ name, image }) => {
  const router = useRouter();

  const handleShopNowClick = () => {
    router.push("/shop");
  };

  return (
    <div>
      <div
        className="relative w-full h-[510px] rounded overflow-hidden bg-cover bg-center border border-border-color1 flex flex-col justify-between"
        style={{
          backgroundImage: `url('${image || bestSellerShopData.imageSrc}')`,
        }}
      >
        <h2 className="ml-4 mt-[38px] mr-10 text-lg font-quicksand font-bold text-text-heading drop-shadow-lg">
          {name || bestSellerShopData.title}
        </h2>

        <Button
          variant="primary"
          className="flex items-center justify-center ml-4 p mb-[48px] w-[120px] bg-text-brand1 text-brand-thrid px-2 py-1 rounded-md shadow-md hover:bg-colorButton-brand1hover transition self-start"
          onClick={handleShopNowClick}
        >
          <p className="pr-0">Shop Now</p>
          <span>{bestSellerShopData.icon}</span>
        </Button>
      </div>
    </div>
  );
};

export default BestSellerCard;
