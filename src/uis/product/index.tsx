"use client";

import ProductInfo from "./components/ProductInfo";
import RelatedProduct from "./components/RelatedProduct";
import { useGetProductDetail } from "@/api/product";
import { useParams } from "next/navigation";

const Product = () => {
  const { id } = useParams();
  const { data } = useGetProductDetail({
    variables: Number(id),
  });
  return (
    <div>
      {data && <ProductInfo data={data} />}
      <RelatedProduct />
    </div>
  );
};

export default Product;
