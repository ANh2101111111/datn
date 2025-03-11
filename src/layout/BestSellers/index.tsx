'use client';
import React from "react";
import BestSellerCard from "./components/BoxSellerShop";
import BoxListProductSeller from "./components/BoxBestSeller";
import BoxListLable from "../lables/BoxListLable";
import { listLableData } from "../lables/type";
import { useGetProducts } from "@/api/product";

const BestSellers = () => {
    const { data = [] } = useGetProducts();
  
  return (
    <div className="grid grid-cols-1 md:grid-cols-12 gap-3 mb-10">
      <div className="md:col-span-12 flex flex-row items-center justify-between w-full">
        <h2 className="md:text-text-heading-4 text-heading-3 text-text-heading font-quicksand font-bold">
          Best Sellers
        </h2>
        <BoxListLable data={listLableData.slice(0, 2)}/>
      </div>

      <div className="md:col-span-12 grid grid-cols-1 md:grid-cols-12 gap-6">
        {/* Best Seller Card */}
        <div className="md:col-span-3 flex flex-col gap-6">
          <BestSellerCard
            name={data?.[0]?.name}
            image={data?.[0]?.image}
          />
        </div>

        {/* Product Grid */}
        <div className="md:col-span-9 grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
          {data.slice(0, 3).map((product) => (
            <BoxListProductSeller
              key={product.bicycleId}
              image={product.image}
              name={product.name}
              brand={'brand'}
              rating={product.rating}
              weight={String(product.quantity)}
              originalPrice={product.originalPrice}
              discountedPrice={product.discountedPrice}
              sold={0}
              stock={100}
              type={product.type}
              textType={product.type}
            />
          ))}
        </div>
      </div>
    </div>
  );
};

export default BestSellers;
