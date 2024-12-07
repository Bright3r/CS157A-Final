import "../../types"
import Product from "@/components/product";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import styles from "./Products.module.css";

export default function ProductDetails() {
  let [product, setProduct] = useState<product_t | null>(null);

  const router = useRouter();
  const { id } = router.query;

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const req = await fetch('http://localhost:3000/products.json');
        const data: product_t[] = await req.json();

        const pageProduct = data.find((product) => product.productID === Number(id));
        if (pageProduct) {
            setProduct(pageProduct);
        }
      } catch (error) {
        console.error("Error fetching products: ", error);
      }
    };

    fetchProduct();
  }, [id]);

  return (
    <>
        <h1 className={styles.header}> Products </h1>
        {product ?
            <Product key={product.productID} {...product} /> :
            <p>Could not find product.</p>
        }
    </>
  );
}