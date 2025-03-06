import React from "react";
import BestSellerCard from "./components/BoxSellerShop";
import productSellerData from "./components/data";
import BoxListProductSeller from "./components/BoxBestSeller";
import BoxListLable from "../lables/BoxListLable";
import { listLableData } from "../lables/type";

const BestSellers = () => {
  return (
    <div className="grid grid-cols-1 md:grid-cols-12 gap-3 mb-4">
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
            name={productSellerData?.[2]?.name}
            image={productSellerData?.[0]?.imageUrl}
          />
        </div>

        {/* Product Grid */}
        <div className="md:col-span-9 grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
          {productSellerData.slice(0, 4).map((product) => (
            <BoxListProductSeller
              key={product.id}
              image={product.imageUrl}
              name={product.name}
              brand={product.brand}
              rating={product.rating}
              weight={product.weight}
              originalPrice={product.originalPrice}
              discountedPrice={product.discountedPrice}
              sold={product.sold}
              stock={product.stock}
              type={product.type}
              textType={product.textType}
            />
          ))}
        </div>
      </div>
    </div>
  );
};

export default BestSellers;
