import React from "react";
import productData from "./components/data";
import BoxProduct from "./components/BoxProduct";
import BoxListLable from "../lables/BoxListLable";
import { listLableData } from "../lables/type";

const PopularProducts = () => {
  return (
    <div className="grid grid-cols-1 sm:grid-cols-3 lg:grid-cols-5 gap-[24px] mt-4 mb-4">
      <div className="col-span-full flex flex-row items-center justify-between w-full">
        <h2 className="md:text-text-heading-4 text-heading-3 text-text-heading font-quicksand font-bold">
          Popular Products
        </h2>
        <BoxListLable data={listLableData}/>
      </div>
      {productData.map((product) => (
        <div key={product.id} className="flex justify-center">
          <BoxProduct
            image={product.imageUrl}
            name={product.name}
            brand={product.brand}
            rating={product.rating}
            weight={product.weight}
            originalPrice={product.originalPrice}
            discountedPrice={product.discountedPrice}
            type={product.type}
            textType={product.textType}
          />
        </div>
      ))}
    </div>
  );
};

export default PopularProducts;
