'use client';
import React from "react";
import BoxProduct from "./components/BoxProduct";
import BoxListLable from "../lables/BoxListLable";
import { useGetProducts } from "@/api/product";
import { IProductDetail } from "@/api/product";
import { ICategory, useGetCategories } from "@/api/categories";

const PopularProducts = () => {
  const { data = [] } = useGetProducts();
  const productData = data.slice(0, 5);
  const { data: categories = [] } = useGetCategories();
  const listLableData = categories.map((category: ICategory) => ({
    id: category.categoryId,
    name: category.name,

  
  }));
  return (
    <div className="grid grid-cols-1 sm:grid-cols-3 lg:grid-cols-5 gap-[24px] mt-4 mb-4">
      <div className="col-span-full flex flex-row items-center justify-between w-full">
        <h2 className="md:text-text-heading-4 text-heading-3 text-text-heading font-quicksand font-bold">
          Popular Products
        </h2>
        <BoxListLable data={listLableData}/>
      </div>
      {productData.map((product: IProductDetail) => (
        <div key={product.bicycleId} className="flex justify-center">
          <BoxProduct
            id={product.bicycleId}
            image={product.image}
            name={product.name}
            brand={'Brand'}
            rating={product.rating}
            weight={String(product.quantity)}
            originalPrice={product.originalPrice}
            discountedPrice={product.discountedPrice}
            type={product.type}
            textType={product.type}
          />
        </div>
      ))}
    </div>
  );
};

export default PopularProducts;
