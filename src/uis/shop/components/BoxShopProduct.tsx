import BoxListLable from "@/layout/lables/BoxListLable";
import { listLableData } from "@/layout/lables/type";
import BoxProduct from "@/layout/PopularProducts/components/BoxProduct";
import productData from "@/layout/PopularProducts/components/data";

const BoxShopProduct = () => {
  return (
    <div className="mt-3">
      <div className=" flex items-center justify-between mb-3">
        <h2 className="text-heading-3 font-quicksand font-bold">Categoty</h2>
        <BoxListLable data={listLableData}/>
      </div>
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-2  mb-8">
        {Array.from({ length: 12 }).map((_, index) => {
          const product = productData[index % productData.length];
          return (
            <BoxProduct
              key={index}
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
          );
        })}
      </div>
    </div>
  );
};

export default BoxShopProduct;
