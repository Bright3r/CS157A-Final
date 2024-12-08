import { useEffect, useState } from "react";
import Product from "@/components/product";
import styles from "./Products.module.css";

type product_t = {
  productID: string;
  productName: string;
  brand: string;
  price: number;
  rating: number;
  imageUrl: string;
  quantity: number;
  listingDate: string;
};

export default function Products() {
  const [products, setProducts] = useState<product_t[]>([]);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const res = await fetch("http://localhost:8080/api/products");
        const data: product_t[] = await res.json();
        setProducts(data);
      } catch (err) {
        console.error("Error fetching products:", err);
      }
    };

    fetchProducts();
  }, []);

  return (
    <>
      <h1 className={styles.header}>Products</h1>
      <div className={styles.productList}>
        {products.map((product) => (
          <Product key={product.productID} {...product} />
        ))}
      </div>
    </>
  );
}
