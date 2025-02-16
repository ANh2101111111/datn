import React from "react";
import { bestSellerShopData } from "./data";

const BestSellerCard = () => {
  return (
    <div className="">
      <div
        className="relative w-full h-[510px] rounded overflow-hidden bg-cover bg-center  flex flex-col justify-between"
        style={{
          backgroundImage: `url('${bestSellerShopData.imageSrc}')`,
        }}
      >
        <h2 className="mt-[38px]  text-lg font-semibold text-gray-900 drop-shadow-lg">
          {bestSellerShopData.title}
        </h2>
        <button className="flex items-center justify-center ml-[48px] p mb-[48px] w-[108px] bg-green-400 text-white px-2 py-1 rounded-md shadow-md hover:bg-green-500 transition self-start ">
          <p className="mr-1"> Shop Now </p> {bestSellerShopData.icon}
        </button>
      </div>
    </div>
  );
};

export default BestSellerCard;
