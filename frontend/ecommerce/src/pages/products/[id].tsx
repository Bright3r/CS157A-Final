import "../../types"
import Product from "@/components/product";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import styles from "./Products.module.css";
import Review from "@/components/review";

export default function ProductDetails() {
  let [product, setProduct] = useState<product_t | null>(null);
  let [reviews, setReviews] = useState<review_t[]>([]);

  const router = useRouter();
  const { id } = router.query;

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const req = await fetch(`http://localhost:8080/api/products/${id}`);
        const data: product_t = await req.json();
        console.log("Product: ", data);

        if (product && product !== undefined) {
          setProduct(data);
        }
      } catch (error) {
        console.error("Error fetching products: ", error);
      }
    };

    const fetchReviews = async () => {
      try {
        const req = await fetch(`http://localhost:8080/api/reviews/${id}`);
        const data: review_t[] = await req.json();
        console.log("Review: ", data);

        if (data && data !== undefined) {
          setReviews(data);
        }
      } catch (error) {
        console.error("Error fetching reviews: ", error);
      }
    }

    fetchProduct();
    fetchReviews();
  }, [id]);

  return (
    <>
        <h1 className={styles.header}> Products </h1>
        {product ?
            <Product key={product.productID} {...product} /> :
            <h1 className={styles.header}>Could not find product.</h1>
        }
        {reviews.length > 0 && reviews.map((review) => <Review key={review.reviewID} {...review} />)}
    </>
  );
}