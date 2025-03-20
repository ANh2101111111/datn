"use client";
import { ICategory, useGetCategories } from "@/api/categories";
import { IProductDetail, useGetProducts } from "@/api/product";
import BoxListLable from "@/layout/lables/BoxListLable";
import BoxProduct from "@/layout/PopularProducts/components/BoxProduct";

const BoxShopProduct = () => {
  const { data = [] } = useGetProducts();
  const productData = data.slice(0, 50);

  const { data: categories = [] } = useGetCategories();
  const listLableData = categories.map((category: ICategory) => ({
    id: category.categoryId,
    name: category.name,
  }));

  return (
    <div className="mt-3">
      <div className=" flex items-center justify-between mb-3">
        <h2 className="text-heading-3 font-quicksand font-bold">
          All Products
        </h2>
        <BoxListLable data={listLableData.slice(0, 8)} />
      </div>
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-2  mb-8">
        {productData.map((product: IProductDetail) => (
          <div key={product.bicycleId} className="flex justify-center">
            <BoxProduct
              id={product.bicycleId}
              image={product.image}
              name={product.name}
              brand={String(product.categoryId)}
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
    </div>
  );
};

export default BoxShopProduct;
