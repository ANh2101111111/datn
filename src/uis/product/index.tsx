import productData from "@/layout/PopularProducts/components/data";
import ProductInfo from "./components/ProductInfo";
import RelatedProduct from "./components/RelatedProduct";

const Product = () => {
  const data = productData[0];
  return <div>
    <ProductInfo data={data} />
    <RelatedProduct />
  </div>;
};

export default Product;
