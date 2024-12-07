import "../../types"
import Product from "@/components/product";
import { useEffect, useState } from "react";
import styles from "./Products.module.css";

export default function Products() {
  let [products, setProducts] = useState<product_t[]>([]);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const req = await fetch('http://localhost:3000/products.json');
        const data = await req.json();

        setProducts(data);
      } catch (error) {
        console.error("Error fetching products: ", error);
      }
    };

    fetchProducts();
  }, []);

  return (
    <>
      <h1 className={styles.header}> Products </h1>
      <div className={styles.productList}>
        {products.map((product) => <Product key={product.productID} {...product} />)}
      </div>
    </>
  );
}