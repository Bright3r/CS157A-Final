import "../../types";
import { useEffect, useState } from "react";
import Product from "@/components/product";
import axios from "axios";
import styles from "./Products.module.css";


export default function Products() {
  const [products, setProducts] = useState<product_t[]>([]);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const res = await axios.get<product_t[]>("http://localhost:8080/api/products");
        console.log(res);
        setProducts(res.data);
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
