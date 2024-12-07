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

    const fetchReviews = async () => {
      try {
        const req = await fetch('http://localhost:3000/reviews.json');
        const data: review_t[] = await req.json();

        const pageReviews = data.filter((review) => review.product.productID === Number(id));
        pageReviews.forEach((review) => review.datePosted = new Date(review.datePosted));
        setReviews(pageReviews);
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
            <p>Could not find product.</p>
        }
        {reviews.map((review) => <Review key={review.reviewID} {...review} />)}
    </>
  );
}